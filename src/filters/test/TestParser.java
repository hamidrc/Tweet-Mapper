package filters.test;

import filters.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test the parser.
 */
public class TestParser {

    @Test
    public void testAnd() throws SyntaxError {
        Filter f = new Parser("trump and Ivanka").parse();
        assertTrue(f instanceof AndFilter);
        assertEquals(f.toString(), "(trump and Ivanka)");
        Filter g = new Parser("trump and Ivanka and Melania").parse();
        assertTrue(g instanceof AndFilter);
        assertEquals(g.toString(), "((trump and Ivanka) and Melania)");
    }

    @Test
    public void testOr() throws SyntaxError {
        Filter f = new Parser("trump or Biden").parse();
        assertTrue(f instanceof OrFilter);
        assertEquals(f.toString(), "(trump or Biden)");
        Filter g = new Parser("trump or Biden or Clinton").parse();
        assertTrue(g instanceof OrFilter);
        assertEquals(g.toString(), "((trump or Biden) or Clinton)");
    }

    @Test
    public void testBasic() throws SyntaxError {
        Filter f = new Parser("trump").parse();
        assertTrue(f instanceof BasicFilter);
        assertEquals(((BasicFilter) f).getWord(), "trump");
    }

    @Test
    public void testHairy() throws SyntaxError {
        Filter x = new Parser("trump and (evil or blue) and red or green and not not purple").parse();
        assertEquals(x.toString(), "(((trump and (evil or blue)) and red) or (green and not not purple))");
    }
}
