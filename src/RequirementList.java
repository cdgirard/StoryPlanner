import java.util.ArrayList;


/**
 * Contains a list of Literals that must be true for the
 * requirement to be met.
 * @author cdgira
 *
 */
public class RequirementList implements Requirement
{
    ArrayList<Literal> m_requirements = new ArrayList<Literal>();
    
    public RequirementList(ArrayList<Literal> requirements)
    {
        m_requirements = requirements;
    }

    /**
     * Checks the world to make sure that all the requirements are met and
     * are true.
     */
    @Override
    public boolean requirementMet(WorldState bindings)
    {
        // TODO Auto-generated method stub
        return false;
    }

}
