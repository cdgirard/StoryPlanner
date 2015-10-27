import java.util.ArrayList;


public class LiteralFactory
{
    
    public static Literal constructLiteral(int name, ArrayList<Actor> actors)
    {
        Literal literal = new Literal(name);
        
        return literal;
    }

}
