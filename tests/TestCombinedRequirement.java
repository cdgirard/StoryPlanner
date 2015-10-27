import static org.junit.Assert.*;

import org.junit.Test;


public class TestCombinedRequirement
{

    @Test
    public void testCreateCombinedRequirement()
    {
        Literal lit1 = new Literal(1);
        Actor act1 = new Actor();
        lit1.addActor(act1);
        
        Literal lit2 = new Literal(2);
        Actor act2a = new Actor();
        Actor act2b = new Actor();
        lit2.addActor(act2a);
        lit2.addActor(act2b);
        
        Literal[] literals = {lit1, lit2};
        CombinedRequirement req = new CombinedRequirement(literals);
        
        Literal[] reqLiterals = req.getRequirements();
        assertEquals(2,reqLiterals.length);
        assertEquals(lit1,reqLiterals[0]);
        assertEquals(lit2,reqLiterals[1]);
    }
    
    @Test
    public void testMeetsRequirements()
    {
        WorldState world = new WorldState();
        Literal wLit1 = new Literal(1);
        Actor wAct1 = new Actor(1,2);
        wLit1.addActor(wAct1);
        
        Literal wLit2 = new Literal(2);
        Actor wAct2a = new Actor(1,2);
        Actor wAct2b = new Actor(2,3);
        wLit2.addActor(wAct2a);
        wLit2.addActor(wAct2b);
        world.addLiteral(wLit1);
        world.addLiteral(wLit2);
        
        Literal lit1 = new Literal(1);
        Actor act1 = new Actor();
        lit1.addActor(act1);
        
        Literal lit2 = new Literal(2);
        Actor act2a = new Actor();
        Actor act2b = new Actor();
        lit2.addActor(act2a);
        lit2.addActor(act2b);
        
        Literal[] literals = {lit1, lit2};
        CombinedRequirement req = new CombinedRequirement(literals);
        
        assertTrue(req.requirementMet(world));
        
    }

}
