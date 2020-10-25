package ray_tracer.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest
{

    @Test
    public void testAddition()
    {
        Color a = new Color(2.5, 3, 1);
        Color b = new Color(-2, 1, 2);

        assertEquals(new Color(0.5, 4, 3), a.add(b));
        assertEquals(new Color(4.5, 5, 3), a.add(2));
    }

    @Test
    public void testSubtraction()
    {
        Color a = new Color(2.5, 2, 1);
        Color b = new Color(2, 1, -2);

        assertEquals(new Color(0.5, 1, 3), a.sub(b));
        assertEquals(new Color(1.5, 1, 0), a.sub(1));
    }

    @Test
    public void testMultiplication()
    {
        Color a = new Color(1.5, 2, 1);
        Color b = new Color(2, 0.5, -1);

        assertEquals(new Color(3, 1, -1), a.mult(b));
        assertEquals(new Color(3, 4, 2), a.mult(2));
    }

    @Test
    public void testDivision()
    {
        Color a = new Color(3, 1, -5);
        Color b = new Color(2, -1, 2);

        assertEquals(new Color(1.5, -1, -2.5), a.div(b));
        assertEquals(new Color(-1.5, -0.5, 2.5), a.div(-2));
    }

}