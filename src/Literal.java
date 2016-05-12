import java.util.ArrayList;


/**
 * Represents the present condition of one or more Actors.  From
 * example a Literal can also consist of actors and other states.
 * 
 * Literals of the same type need to have their Actors added in the same order.
 * 
 * @author Dr. Girard
 *
 */
public class Literal implements Cloneable
{
    /**
     * How to make this more efficient like ints, but 
     * also preserve the identification that Stings give.
     * 
     * Constants, but then for each new context would need
     * it's own set of constants, same for enums.  Also
     * how can I add new constants at run time.  I could
     * use a Hashtable of int/String pairings.
     */
    int m_id;
    ArrayList<Actor> m_actors = new ArrayList<Actor>();
    
    /**
     * Creates a new Literal instance with the specific name.
     */
    public Literal (int id)
    {
        m_id = id;
    }
    
    /**
     * Add and actor to the literal.
     * 
     * @param actor
     */
    public void addActor(Actor actor)
    {
        m_actors.add(actor);
    }
    
    /**
     * Returns the name for this Literal.
     * @return
     */
    public int getID()
    {
        return m_id;
    }
    
    /**
     * Compares two literals together to see if they are the same.
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean same = true;
        Literal lit = (Literal)obj;
        if (m_id != lit.getID())
        {
            same = false;
        }
        else if (m_actors.size() != lit.getNumActors())
        {
            same = false;
        }
        else
        {
            same = true;
            for (int loc=0;loc<m_actors.size() && same;loc++)
            {
                if (!lit.hasActor(m_actors.get(loc)))
                    same = false;
            }
        }
        return same;
    }

    /**
     * How many actors make up this literal.
     * @return
     */
    public int getNumActors()
    {
        return m_actors.size();
    }

    /**
     * Does this literal have this actor as part of the 
     * Literal.
     * @param actor
     * @return
     */
    public boolean hasActor(Actor actor)
    {
        for (Actor a : m_actors)
        {
            if (a.equals(actor))
                return true;
        }
        return false;
    }

    /**
     * Get me a list of the actors without exposing the ArrayList.
     * @return
     */
    public Actor[] getActors()
    {
        Actor[] actors = new Actor[m_actors.size()];
        
        int x = 0;
        for (Actor a : m_actors)
        {
            actors[x] = a;
            x++;
        }
        return actors;
    }

    /**
     * Goes through all the actors in the Literal and unbinds them
     * from any concrete values.
     */
    public void unbindActors()
    {
        for (Actor a : m_actors)
        {
            a.unbind();
        }
    }
    
    /**
     * Attempts to match this Literal to a Literal
     * that has all bound Actors.
     * @param lit The Literal with the bound Actors
     * @return true if successful, false otherwise.
     */
    public boolean match(Literal lit)
    {
        boolean goodMatch = false;
        if (lit.getID() == m_id)
        {
            if (this.equals(lit))
            {
                goodMatch = true;
            }
            else
            {
                // Some Actors may not be bound and some may be.
                Actor[] wrldActors = lit.getActors();
                
                if (actorsCanMatch(lit.getActors()))
                {
                    Actor[] reqActors = this.getActors();
                    
                    for (int x = 0; x < wrldActors.length; x++)
                    {
                        if (!reqActors[x].bound())
                        {
                            reqActors[x].bindTo(wrldActors[x]);
                        }
                    }
                    
                    goodMatch = true;
                }
            }
        }
        return goodMatch;
    }
    
    /**
     * Makes sure two groups of actors will line up correctly.
     * @param wrldActors The list of Actors that are presently bound to something.
     * @return true if possible, false otherwise.
     */
    private boolean actorsCanMatch(Actor[] wrldActors)
    {
        Actor[] reqActors = this.getActors();
        boolean canMatch = true;
        
        for (int x = 0; x < wrldActors.length; x++)
        {
            if (!reqActors[x].bound())
            {
                // Can Match fine.
            }
            else if (reqActors[x].equals(wrldActors[x]))
            {
                // Can Match fine.
            }
            else
            {
                canMatch = false;
                break;
            }
        }
        return canMatch;
    }

    /**
     * Returns true if all actors that are part of the Literal are bound
     * to a concert value, false otherwise.
     * 
     * @return
     */
    public boolean isBound()
    {
        boolean bound = true;
        for (Actor a : m_actors)
        {
            if (!a.bound())
            {
                bound = false;
                break;
            }
        }
        
        return bound;
    }
    
    /**
     * Creates a copy of the Literal and the actors inside the Literal.
     * The actors are new instances with the same type and value of
     * the original.
     * @return
     */
    @Override
    public Object clone()
    {
        Literal lit = new Literal(m_id);
        for (Actor a : m_actors)
        {
            Actor cloneOfA = new Actor(a.getType(),a.getValue());
            lit.addActor(cloneOfA);
        }
        return lit;    
    }

}
