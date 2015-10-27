
/**
 * Use a -1 to designate an unbound value.  Provide a function to bind
 * and unbind values to the Actor.  Literals that "share" actors in a
 * Requirement can simply point to the same instance of that actor 
 * when setting up the requirement.
 * @author cdgira
 *
 */
public class Actor
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
    private int m_value;
    private int m_type;
    
    public Actor()
    {
        this(-1,-1);
    }
    
    /**
     * Creates an Actor of a specific type and value.
     * @param type
     * @param value
     */
    public Actor(int type, int value)
    {
        m_value = value;
        m_type = type;
    }
    
    /**
     * 
     * @return A unique individual of a certain type.
     */
    public int getValue()
    {
        return m_value;
    }
    
    /**
     * 
     * @return What type this Actor is.
     */
    public int getType()
    {
        return m_type;
    }
    
    /**
     * Checks to see if two actors are equal by
     * comparing their type and value.
     * @param obj The Actor to compare this Actor to.
     */
    @Override
    public boolean equals(Object obj)
    {
        Actor actor = (Actor)obj;
        if ((actor.getType() == m_type) && (actor.getValue() == m_value))
            return true;
        return false;
    }

    /**  
     * Intended for storing the Actor in a Hashmap.
     * @return the key to be used to uniquely identify the Actor.
     */
    public String getKey()
    {
        return m_type+":"+m_value;
    }

    /**
     * Unbinds this actor to any type or value by setting
     * both to -1.
     */
    public void unbind()
    {
        m_value = -1;
        m_type = -1;    
    }

    /**
     * Binds this Actor to having the same m_value and m_type as
     * the actor provided.  This is used mainly by the Requirement
     * classes to temporarily bind actors in the world to it's list
     * of requirements to see if all requirements are met.
     * @param actor
     */
    public void bindTo(Actor actor)
    {
        m_value = actor.getValue();
        m_type = actor.getType();   
    }

    /**
     * 
     * @return True if the Actor is bound to a type and value, false otherwise.
     */
    public boolean bound()
    {
        return (m_value != -1) && (m_type != -1);
    }
}
