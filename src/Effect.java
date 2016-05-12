
public interface Effect
{

    /**
     * Determines if the present state of the world has this
     * effect already apart of it.  Used create a world
     * were the effect does not exist, but the requirements would.
     * 
     * @param world
     * @return
     */
    boolean hasOccurred(WorldState world);

    /**
     * If the actors for all literals are bound then add clones 
     * of those literals to the world.
     * 
     * @param world
     * @return
     */
    boolean causeEffect(WorldState world);
    
    

}
