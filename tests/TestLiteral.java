import static org.junit.Assert.*;

import org.junit.Test;


public class TestLiteral
{

    @Test
    public void testCompareWithNoActors()
    {
        Literal lit1 = new Literal(1);
        Literal lit2 = new Literal(1);
        assertTrue(lit1.equals(lit2));
        lit2 = new Literal(2);
        assertFalse(lit1.equals(lit2));
    }
    
    @Test
    public void testAddingActors()
    {
        Literal lit1 = new Literal(1);
        assertEquals(0,lit1.getNumActors());
        Actor actor1 = new Actor(1,2);
        lit1.addActor(actor1);
        
        assertEquals(1,lit1.getNumActors());
        Actor actor2 = new Actor(1,2);
        assertTrue(lit1.hasActor(actor2));
        actor2 = new Actor(1,3);
        assertFalse(lit1.hasActor(actor2));
        
        Actor[] actors = lit1.getActors();
        assertEquals(1,actors.length);
        assertEquals(actor1,actors[0]);
    }
    
    @Test
    public void testCompareWithActors()
    {
        Literal lit1 = new Literal(1);
        Actor actor1 = new Actor(1,2);
        lit1.addActor(actor1);
        
        Literal lit2 = new Literal(1);
        Actor actor2 = new Actor(1,2);
        lit2.addActor(actor2);
        
        assertTrue(lit1.equals(lit2));
        
        lit2 = new Literal(1);
        actor2 = new Actor(2,1);
        assertFalse(lit1.equals(lit2));
    }
    
    @Test
    public void testUnbindActors()
    {
        Literal lit1 = new Literal(1);
        Actor actor1 = new Actor(1,2);
        lit1.addActor(actor1);
        lit1.unbindActors();
        assertEquals("-1:-1",actor1.getKey());
    }
    
    @Test
    public void testMatch()
    {
        Literal wLit1 = new Literal(1);
        Actor wAct1 = new Actor(1,2);
        wLit1.addActor(wAct1);
        
        Literal wLit2 = new Literal(2);
        Actor wAct2a = new Actor(1,2);
        Actor wAct2b = new Actor(2,3);
        wLit2.addActor(wAct2a);
        wLit2.addActor(wAct2b);
    
        assertFalse(wLit1.match(wLit2));
        assertTrue(wLit1.match(wLit1));
        
        Literal lit1 = new Literal(1);
        Actor act1 = new Actor();
        lit1.addActor(act1);
        
        assertFalse(lit1.match(wLit2));
        assertTrue(lit1.match(wLit1));
        
        Literal lit2 = new Literal(2);
        Actor act2a = new Actor();
        Actor act2b = new Actor();
        lit2.addActor(act2a);
        lit2.addActor(act2b);
        
        assertFalse(lit2.match(wLit1));
        assertTrue(lit2.match(wLit2));
        
    }

}
