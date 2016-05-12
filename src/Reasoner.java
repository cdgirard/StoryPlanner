
public class Reasoner
{
    /**
     * Checks to see if a group of literals can be mapped
     * to the literals in the world.  If it can then the Actors in group
     * are updated to be bound to specific values.  If it can't, then all
     * actors are unbound.
     * 
     * @param group
     * @param world
     * @return
     */
    public static boolean requirementMet(Literal[] group, Literal[] world)
    {
        boolean result = false;
        // Pre-Test
        boolean preTest = true;
        for (Literal reqLit : group)
        {
            boolean match = false;
            for (Literal worldLit : world)
            {
                if (reqLit.getID() == worldLit.getID())
                {
                    match = true;
                    break;
                }
            }
            if (!match)
            {
                preTest = false;
                break;
            }
        }
        
        if (preTest)
        {
            boolean success = true;
            for (Literal reqLit : group)
            {
                boolean foundMatch = false;
                for (Literal worldLit : world)
                { 
                    if (reqLit.match(worldLit))
                    {
                        foundMatch = true;
                        break;
                    }
                }
                if (!foundMatch)
                {
                    success = false;
                    break;
                }
            }
            if (success)
            {
                result = true;
            }
        }
        if (!result)
            unbindRequirements(group);
        return result;
    }
    
    /**
     * This is for checking if certain literals are not in the world.
     * Checks to see if any literals in a group of literals can be mapped
     * to the literals in the world.  If any can then the method returns false.
     * If none can be mapped it returns true.  Any bound actors are unbound
     * after the call.
     * 
     * @param group
     * @param world
     * @return
     */
    public static boolean requirementMetForRemoveGroup(Literal[] group, Literal[] world)
    {
        boolean result = true;
        // Pre-Test - If there is at least one match we need to do a more in-depth check.
        boolean preTest = true;
        for (Literal reqLit : group)
        {
            boolean match = false;
            for (Literal worldLit : world)
            {
                if (reqLit.getID() == worldLit.getID())
                {
                    match = true;
                    break;
                }
            }
            if (match)
            {
                preTest = true;
                result = false;
                break;
            }
        }
        
        if (preTest)
        {
            boolean success = true;
            for (Literal reqLit : group)
            {
                boolean foundMatch = false;
                for (Literal worldLit : world)
                { 
                    if (reqLit.match(worldLit))
                    {
                        foundMatch = true;
                        break;
                    }
                }
                // If any find a match then we failed.
                if (foundMatch)
                {
                    success = false;
                    break;
                }
            }
            if (success)
            {
                result = true;
            }
        }
        if (!result)
            unbindRequirements(group);
        return result;
    } 
    
    /**
     * Goes through all the Requirements and unbinds them.  Needed
     * before we check to see if they are met in the world.
     */
    private static void unbindRequirements(Literal[] literals)
    {
        for (Literal reqLit : literals)
        {
            reqLit.unbindActors();
        }
        
    }

}
