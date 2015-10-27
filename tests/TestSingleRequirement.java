import static org.junit.Assert.*;

import org.junit.Test;


public class TestSingleRequirement
{

    @Test
    public void testGrantsPermission()
    {
        WorldState bindings = new WorldState();
        Literal literal = new Literal(1);
        SingleRequirement req = new SingleRequirement(literal);
        assertFalse(req.requirementMet(bindings));
        Literal literal2 = new Literal(1);
        bindings.addLiteral(literal2);
        assertTrue(req.requirementMet(bindings));
    }

}
