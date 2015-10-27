
/**
 * Requires that a single Literal be true for the Requirement to be met.
 * @author cdgira
 *
 */
public class SingleRequirement implements Requirement
{
    
    Literal m_literal;
    
    public SingleRequirement(Literal literal)
    {
        m_literal = literal;
    }

    /**
     * Checks the world to see if the requirement has been met in the
     * world.
     */
    @Override
    public boolean requirementMet(WorldState world)
    {
        Literal[] literals = world.getBindings();
        m_literal.unbindActors();
        for (Literal literal : literals)
        {
            if (m_literal.getID() == literal.getID())
            {
                Actor[] worldActors = literal.getActors();
                Actor[] reqActors = m_literal.getActors();
                for (int x=0;x<worldActors.length;x++)
                {
                    reqActors[x].bindTo(worldActors[x]);
                }
                return true;
            }
        }
        return false;
    }

}
