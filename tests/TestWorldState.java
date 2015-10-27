import static org.junit.Assert.*;

import org.junit.Test;


public class TestWorldState
{

    @Test
    public void testStoresLiteralsNoActors()
    {
        WorldState bindings = new WorldState();
        Literal literal = new Literal(1);
        bindings.addLiteral(literal);
        Literal checker = new Literal(1);
        assertTrue(bindings.hasLiteral(checker));
        checker = new Literal(2);
        assertFalse(bindings.hasLiteral(checker));     
    }
    
    @Test
    public void testStoresLiteralsWithActors()
    {
        WorldState bindings = new WorldState();
        Literal literal = new Literal(1);
        Actor actor = new Actor(1,2);
        literal.addActor(actor);
        bindings.addLiteral(literal);
        assertEquals(1,bindings.getNumActors());
        Actor[] actors = bindings.getActors();
        assertEquals(1,actors.length);
        assertEquals(actor,actors[0]);
    }
    
    @Test
    public void testRmoveLiteralsWithActors()
    {
        WorldState bindings = new WorldState();
        Literal literal = new Literal(1);
        Actor actor = new Actor(1,2);
        literal.addActor(actor);
        bindings.addLiteral(literal);
        
        Literal lit2 = new Literal(1);
        Actor act2 = new Actor(1,2);
        lit2.addActor(act2);
        bindings.removeLiteral(lit2);
        
        Actor[] actors = bindings.getActors();
        assertEquals(0,bindings.getNumActors());
        assertEquals(0,actors.length);
        assertFalse(bindings.hasLiteral(literal));
    }
    
    @Test
    public void testRemvoesLiteralWithActorsButActorsShouldStay()
    {
        WorldState bindings = new WorldState();
        Literal literal = new Literal(1);
        Actor actor = new Actor(1,2);
        literal.addActor(actor);
        bindings.addLiteral(literal);
        
        Literal literal1 = new Literal(2);
        Actor actor1 = new Actor(1,2);
        literal1.addActor(actor1);
        bindings.addLiteral(literal1);
        
        Literal lit2 = new Literal(1);
        Actor act2 = new Actor(1,2);
        lit2.addActor(act2);
        bindings.removeLiteral(lit2);
        
        Actor[] actors = bindings.getActors();
        assertEquals(1,bindings.getNumActors());
        assertEquals(1,actors.length);
        assertEquals(actor,actors[0]);
        assertFalse(bindings.hasLiteral(literal));
        assertTrue(bindings.hasLiteral(literal1));
    }
    
    @Test
    public void testGetBindings()
    {
        WorldState bindings = new WorldState();
        Literal literal = new Literal(1);
        Actor actor = new Actor(1,2);
        literal.addActor(actor);
        bindings.addLiteral(literal);
        
        Literal literal1 = new Literal(2);
        Actor actor1 = new Actor(1,2);
        literal1.addActor(actor1);
        bindings.addLiteral(literal1);
        
        Literal[] literals = bindings.getBindings();
        assertEquals(2,literals.length);
        assertEquals(literal,literals[0]);
        assertEquals(literal1,literals[1]);
    }

}
