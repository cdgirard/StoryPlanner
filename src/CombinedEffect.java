import java.util.ArrayList;


/**
 * Is the effect caused by an Action or step being taken.
 * Should be tied directly to a requirement so that the
 * actors can be bound correctly if the requirement
 * is met.
 * 
 * @author cdgira
 *
 */
public class CombinedEffect implements Effect
{
    /**
     * For an effect to be valid these must exist in the World.
     */
    ArrayList<Literal> m_createLiterals = new ArrayList<Literal>();
    /**
     * An effect should cause these to be removed from the world, it
     * is assumed the Requirement this Effect is attached to has ensured
     * the Literal is there to be removed.
     */
    ArrayList<Literal> m_removeLiterals = new ArrayList<Literal>();

    /**
     * Constructs an effect that causes the list of literals to be brought into 
     * existence.
     * @param createLits
     */
    public CombinedEffect(Literal[] createLits)
    {
        for (Literal literal : createLits)
        {
            m_createLiterals.add(literal);
        }
    }

    public CombinedEffect(Literal[] createLits, Literal[] removeLits)
    {
        this(createLits);
        
        for (Literal literal : removeLits)
        {
            m_removeLiterals.add(literal);
        }
    }

    /**
     * Goes through all the Requirements and unbinds them.  Needed
     * before we check to see if they are met in the world.
     */
    private void unbindRequirements()
    {
        for (Literal reqLit : m_createLiterals)
        {
            reqLit.unbindActors();
        }
        
    }
    
    /**
     * Determines if the present state of the world has this
     * effect already apart of it.  Used create a world
     * were the effect does not exist, but the requirements would.  If the
     * effect exists then it maps values to the Actors.
     * 
     * @param world
     * @return
     */
    @Override
    public boolean hasOccurred(WorldState world)
    {
        boolean result = false;
        unbindRequirements();
        result = Reasoner.requirementMet(this.getEffect(), world.getBindings());
        return result;
    }

    /**
     * If the actors for all literals are bound then add clones 
     * of those literals to the world.
     * 
     * @param world
     */
    @Override
    public boolean causeEffect(WorldState world)
    {
        for (Literal literal : m_createLiterals)
        {
             if (!literal.isBound())
                 return false;
        }
        
        for (Literal literal : m_createLiterals)
        {
            Literal cloneLit = (Literal)literal.clone();
            world.addLiteral(cloneLit);
        }
        
        return true;
    }
    
    /**
     * Transfers the list of required literals into an array.
     * @return the array of required literals.
     */
    public Literal[] getEffect()
    {
        Literal[] literals = new Literal[m_createLiterals.size()];
        
        int loc = 0;
        for (Literal literal : m_createLiterals)
        {
            literals[loc] = literal;
            loc++;
        }
        return literals;
    }

}
