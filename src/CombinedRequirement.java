import java.util.ArrayList;


public class CombinedRequirement implements Requirement
{
    ArrayList<Literal> m_literals = new ArrayList<Literal>();

    /**
     * 
     * @param literals
     */
    public CombinedRequirement(Literal[] literals)
    {
        for (Literal literal : literals)
        {
            m_literals.add(literal);
        }
    }

    /**
     * Checks to see if all the literals match with the present
     * 
     * Algorithm
     *  Pre-Test: For each Literal in the Requirement see if in the world.
     *  1. Take the first Literal from the Requirement
     *  2. Map it's actors to the first Literal that matches it in the world.
     *  3. Take the next literal from the Requirement
     *  4. Map that literal's actor to the first literal that matches it in the world 
     *      -Cannot be the same literal from steps 1 and 2.
     *  5. Continue steps 3 and 4 until all literals matched or there is a failed match.
     *  
     *  Failed Match: If there is a failed match go back to a previous match, unmatch them
     *                and see if there is another literal in the world that matches
     *                
     *  Question: Should we have a way to mark a literal as being in use by a match?
     *  Question: Should we find all possible ways this requirement can be met?
     *  Question: In the planning algorithm we have states we want to reach so does this
     *            mean we already have the real actors the requirement would have to match?
     *   
     *   We will need to be able to bind and unbind, but not in the way expected.  So using the
     *   out comes of an Operator would determine the bindings.
     *   
     *   We start with a start state and an end state (what literals we want true at the end)
     *   Using the operators we can see what apply to the start state which looking at what
     *   requirements match with the world (see above algorithm)
     *   
     *   If we go from the end state we need to map it to to the outcome of an operator (can
     *   use the same algorithm as above) and then map that to the requirements.  That would 
     *   then be used to create a Casual Link.
     * 
     * @param world state of the world.
     */
    @Override
    public boolean requirementMet(WorldState world)
    {
        boolean result = false;
        unbindRequirements();
        result = Reasoner.requirementMet(this.getRequirements(), world.getBindings());
        return result;
    }
    
    /**
     * Goes through all the Requirements and unbinds them.  Needed
     * before we check to see if they are met in the world.
     */
    private void unbindRequirements()
    {
        for (Literal reqLit : m_literals)
        {
            reqLit.unbindActors();
        }
        
    }

    /**
     * Transfers the list of required literals into an array.
     * @return the array of required literals.
     */
    public Literal[] getRequirements()
    {
        Literal[] literals = new Literal[m_literals.size()];
        
        int loc = 0;
        for (Literal literal : m_literals)
        {
            literals[loc] = literal;
            loc++;
        }
        return literals;
    }
    
    

}
