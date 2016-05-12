import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestReasoner
{
    Literal lit1, lit2, wLit1, wLit2, wLit3;

    @Before
    public void setup()
    {
        lit1 = new Literal(1);
        Actor act1 = new Actor();
        lit1.addActor(act1);
        
        lit2 = new Literal(2);
        Actor act2b = new Actor();
        lit2.addActor(act1);
        lit2.addActor(act2b);
        
        wLit1 = new Literal(1);
        Actor wAct1 = new Actor(1,2);
        wLit1.addActor(wAct1);
        
        Actor wAct2a, wAct2b;
        
        wLit2 = new Literal(2);
        wAct2a = new Actor(1,2);
        wAct2b = new Actor(2,3);
        wLit2.addActor(wAct2a); 
        wLit2.addActor(wAct2b);
        
        wLit3 = new Literal(3);
        wLit3.addActor(wAct2a); 
        wLit3.addActor(wAct2b);
    }

    /**
     * Move tests from Effect or Requirement to here.  Need a test that will 
     * do a complex case where not unbinding while looking for a match can cause a fail.
     */
    @Test
    public void testRequirementMet()
    {
        Literal[] litList1 = {lit1, lit2};
        
        Literal[] litList2 = {};
        
        assertFalse(Reasoner.requirementMet(litList1, litList2));
        
        Literal[] litList3 = { wLit1 };
        
        assertFalse(Reasoner.requirementMet(litList1, litList3));
        
        Literal[] litList4 = { wLit1, wLit2 };
        assertTrue(Reasoner.requirementMet(litList1, litList4));
    }
    
    @Test
    public void testRequirementMetForRemoveGroup()
    { 
        Literal[] litList1 = {lit1, lit2};
        
        Literal[] litList2 = {};
        
        assertTrue(Reasoner.requirementMetForRemoveGroup(litList1, litList2));
        
        Literal[] litList3 = { wLit1 };
        
        assertFalse(Reasoner.requirementMetForRemoveGroup(litList1, litList3));
        
        Literal[] litList4 = { wLit2 };
        assertFalse(Reasoner.requirementMetForRemoveGroup(litList1, litList4));
        
        Literal[] litList5 = { wLit3 };
        assertTrue(Reasoner.requirementMetForRemoveGroup(litList1, litList5));
    }

}
