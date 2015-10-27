import static org.junit.Assert.*;

import org.junit.Test;


public class TestActor
{

    @Test
    public void testConstructedCorrectly()
    {
        Actor a = new Actor(1,2);
        assertEquals(1,a.getType());
        assertEquals(2,a.getValue());
        
        a = new Actor();
        assertEquals(-1,a.getType());
        assertEquals(-1,a.getValue());
    }
    
    @Test
    public void testCompareTwoActors()
    {
        Actor a = new Actor(2,3);
        Actor b = new Actor(2,3);
        assertTrue(a.equals(b));
        b = new Actor(1,3);
        assertFalse(a.equals(b));
        b = new Actor(2,2);
        assertFalse(a.equals(b));
    }
    
    @Test
    public void testKeyGeneration()
    {
        Actor a = new Actor(3,4);
        assertEquals("3:4",a.getKey());
    }
    
    @Test
    public void testBindAndUnBind()
    {
        Actor a = new Actor(2,3);
        a.unbind();
        assertEquals("-1:-1",a.getKey());
        assertFalse(a.bound());
        Actor b = new Actor(2,3);
        a.bindTo(b);
        assertEquals("2:3",a.getKey());
        assertTrue(a.bound());
    }
    

}
