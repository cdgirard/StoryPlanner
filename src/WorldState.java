import java.util.ArrayList;
import java.util.HashMap;


/**
 * Contains a list of all things that are presently true
 * in the world.
 * @author cdgira
 *
 */
public class WorldState
{
    HashMap<String,Actor> m_actors = new HashMap<String,Actor>();
    ArrayList<Literal> m_bindings = new ArrayList<Literal>();
    
    /**
     * Constructs a new empty WorldState.
     */
    public WorldState()
    {
        
    }
    
    /**
     * Constructs a new WorldState by initializing it from a
     * list of provided Literals.
     */
    public WorldState(ArrayList<Literal> bindings)
    {
        for (Literal literal : bindings)
        {
            m_bindings.add(literal);
            Actor[] actors = literal.getActors();
            for (Actor a : actors)
            {
                String key = a.getType()+":"+a.getValue();
                if (!m_actors.containsKey(key))
                     m_actors.put(key,a);
            }
        }
    }
    
    /**
     * Adds a Literal to the list of present
     * bindings for this state of the world.
     * @param literal
     */
    public void addLiteral(Literal literal)
    {
        m_bindings.add(literal);
        Actor[] actors = literal.getActors();
        for (Actor a : actors)
        {
            String key = a.getKey();
            if (!m_actors.containsKey(key))
                 m_actors.put(key,a);
        }
    }

    /**
     * Checks to see if this literal is contained in the WorldState
     * @param literal
     * @return
     */
    public boolean hasLiteral(Literal literal)
    {
        for (Literal item : m_bindings)
        {
            if (item.equals(literal))
                return true;
        }
        return false;
    }

    /**
     * How many actors are presently represented in this world.
     * @return
     */
    public int getNumActors()
    {
        return m_actors.size();
    }

    public Actor[] getActors()
    {
        Actor[] actors = new Actor[m_actors.size()];
        
        int x = 0;
        for (Actor a : m_actors.values())
        {
            actors[x] = a;
            x++;
        }
        return actors;
    }

    /**
     * Removes a Literal from the world and if no other
     * literals use those actors, removes them as well.
     * @param literal
     */
    public void removeLiteral(Literal literal)
    {
        if (m_bindings.remove(literal))
        {
            Actor[] actors = literal.getActors();
            for (Actor a : actors)
            {
                boolean remove = true;
                for (Literal lit : m_bindings)
                {
                    if (lit.hasActor(a))
                    {
                        remove = false;
                        break;
                    }
                }
                if (remove)
                    m_actors.remove(a.getKey());
            }
        }
        
    }

    /**
     * 
     * @return an array of all the Literals presently in this WorldState.
     */
    public Literal[] getBindings()
    {
        Literal[] literals = new Literal[m_bindings.size()];
        
        int loc = 0;
        for (Literal literal : m_bindings)
        {
            literals[loc] = literal;
            loc++;
        }
        return literals;
    }

}
