import java.util.ArrayList;


/**
 * Is a list of Requirements for which only a group of need to be
 * true.
 * @author cdgira
 *
 */
public class OptionalRequirementList implements Requirement
{
    /**
     * The list of Literals that can be used to meet the requirement.
     */
    ArrayList<Literal> m_requirements = new ArrayList<Literal>();
    
    /**
     * How many requirements must be true for the Requirement needs to be met.
     */
    int numReq = 0;
    
    
    /**
     * Checks to see if the number of required requirements has been
     * met for this Requirement to be considered true.
     */
    @Override
    public boolean requirementMet(WorldState bindings)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
