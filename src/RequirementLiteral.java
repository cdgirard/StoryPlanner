import java.util.ArrayList;

/**
 * Used by requirements to store the framework for a Literal.
 * For checking against a WorldLiteral requires pairing up the
 * placeholder actors with actual actors.
 * @author cdgira
 *
 */
public class RequirementLiteral
{
    /**
     * The id should match the WorldLiteral Id it is mirroring.
     */
    int m_id;
    ArrayList<Actor> m_actors = new ArrayList<Actor>();
    

}
