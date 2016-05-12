import static org.junit.Assert.*;

import org.junit.Test;


public class TestCombinedEffect
{

    /**
     * Something is not right with my tests here...need to start back at beginning and
     * rethink what I should be testing.
     */
    @Test
    public void testCauseEffectSimple()
    {
        Literal lit1 = new Literal(1);
        Actor act1 = new Actor();
        lit1.addActor(act1);
        
        Literal[] literals = { lit1 };
        CombinedEffect effect = new CombinedEffect(literals);
        
        WorldState world = new WorldState();
        
        // Should fail as Actor in the effect is not bound.
        assertFalse(effect.causeEffect(world));
        
        // I need this Literal to see if it exists after the effect is caused.
        Literal wLit1 = new Literal(1);
        Actor wAct1 = new Actor(1,2);
        wLit1.addActor(wAct1);
        assertFalse(world.hasLiteral(wLit1));
        
        //  Bind a concrete actor to the literal in the effect so it can be caused.
        act1.bindTo(wAct1);
        
        // Should work as actor now bound.
        assertTrue(effect.causeEffect(world));
        assertTrue(world.hasLiteral(wLit1));
    }
    
    @Test
    public void testCauseEffectSimpleRemovesLiteralFromWorld()
    {
        Literal lit1 = new Literal(1);
        Actor act1 = new Actor();
        lit1.addActor(act1);
        
        Literal lit2 = new Literal(2);
        lit2.addActor(act1);
        
        Literal[] createLits = { lit1 };
        Literal[] removeLits = { lit2 };
        CombinedEffect effect = new CombinedEffect(createLits,removeLits);
        
        WorldState world = new WorldState();
        
        // Add the literal that is to be removed to the world.
        Literal wLit2 = new Literal(2);
        Actor wAct2 = new Actor(1,2);
        wLit2.addActor(wAct2);
        world.addLiteral(wLit2);
        
        // Should failed, actor not bound.
        assertFalse(effect.causeEffect(world));
        assertTrue(world.hasLiteral(wLit2));
        
        // We need this to see if the literal in the effect is added.
        Literal wLit1 = new Literal(1);
        Actor wAct1 = new Actor(1,2);
        wLit1.addActor(wAct1);
        assertFalse(world.hasLiteral(wLit1));
        
        // Bind a concrete actor to the actor for the two literals 
        act1.bindTo(wAct1);
        // Should work as actor now bound.
        assertTrue(effect.causeEffect(world));
        assertTrue(world.hasLiteral(wLit1));
        assertFalse(world.hasLiteral(wLit2));
        assertFalse("Not done",true);
    }
    
    @Test
    public void testDoesEffectAlreadyExistSimple()
    {
        Literal lit1 = new Literal(1);
        Actor act1 = new Actor();
        lit1.addActor(act1);
        
        Literal[] literals = { lit1 };
        CombinedEffect effect = new CombinedEffect(literals);
        
        WorldState world = new WorldState();
        Literal wLit1 = new Literal(1);
        Actor wAct1 = new Actor(1,2);
        wLit1.addActor(wAct1);
        
        assertFalse(effect.hasOccurred(world));
        
        world.addLiteral(wLit1);
        
        assertTrue(effect.hasOccurred(world));
    }
    
    @Test
    public void testDoesEffectAlreadyExistComplex()
    {
        Literal lit1 = new Literal(1);
        Actor act1 = new Actor();
        lit1.addActor(act1);
        
        Literal lit2 = new Literal(2);
        Actor act2b = new Actor();
        lit2.addActor(act1);
        lit2.addActor(act2b);
        
        Literal[] literals = {lit1, lit2};
        CombinedEffect req = new CombinedEffect(literals);
        
        WorldState world = new WorldState();
        Literal wLit1 = new Literal(1);
        Actor wAct1 = new Actor(1,2);
        wLit1.addActor(wAct1);
        
        assertFalse(req.hasOccurred(world));
        
        Literal wLit2 = new Literal(2);
        Actor wAct2a = new Actor(1,2);
        Actor wAct2b = new Actor(2,3);
        wLit2.addActor(wAct2a); 
        wLit2.addActor(wAct2b);
        world.addLiteral(wLit1);
        
        assertFalse(req.hasOccurred(world));
        
        world.addLiteral(wLit2);

        assertTrue(req.hasOccurred(world));
    }
    
    @Test
    public void testDoesEffectExistWithRemove()
    {
        Literal lit1 = new Literal(1);
        Actor act1 = new Actor();
        lit1.addActor(act1);
        
        Literal lit2 = new Literal(2);
        lit2.addActor(act1);
        
        Literal[] createLits = { lit1 };
        Literal[] removeLits = { lit2 };
        CombinedEffect effect = new CombinedEffect(createLits,removeLits);
        
        WorldState world = new WorldState();
        assertFalse(effect.hasOccurred(world));
        // Add the literal that should be in the world.
        Literal wLit1 = new Literal(1);
        Actor wAct1 = new Actor(1,2);
        wLit1.addActor(wAct1);
        world.addLiteral(wLit1);
        
        assertTrue(effect.hasOccurred(world));
        
     // Add the literal that should be removed to the world.
        Literal wLit2 = new Literal(2);
        Actor wAct2 = new Actor(1,2);
        wLit2.addActor(wAct2);
        world.addLiteral(wLit2);
        
        assertFalse(effect.hasOccurred(world));
    }

}
