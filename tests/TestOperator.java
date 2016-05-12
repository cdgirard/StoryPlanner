import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestOperator
{
    Literal lit1, lit2, lit3, wLit1, wLit2, wLit3;

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
        
        Actor act2a = new Actor();
        
        lit3 = new Literal(3);
        lit3.addActor(act1);
        lit3.addActor(act2a);
        
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

    @Test
    public void testInitialize()
    {
//        Consent con = new Consent();
//        
//        Literal[] reqLiterals = {lit1, lit2};
//        CombinedRequirement req = new CombinedRequirement(reqLiterals);
//        
//        // Set it up to remove lit1.
//        
//        Literal[] effLiterals = {lit3};
//        CombinedEffect eff = new CombinedEffect(effLiterals);
//        
//        Operator op = new Operator(con,req,eff);
//        
//        assertEquals(req,)
    }

}
