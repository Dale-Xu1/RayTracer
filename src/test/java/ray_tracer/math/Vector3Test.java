package ray_tracer.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test
{

    @Test
    public void testAddition()
    {
        Vector3 a = new Vector3(2.5, 3, 1);
        Vector3 b = new Vector3(-2, 1, 2);

        assertEquals(new Vector3(0.5, 4, 3), a.add(b));
        assertEquals(new Vector3(4.5, 5, 3), a.add(2));
    }

    @Test
    public void testSubtraction()
    {
        Vector3 a = new Vector3(2.5, 2, 1);
        Vector3 b = new Vector3(2, 1, -2);

        assertEquals(new Vector3(0.5, 1, 3), a.sub(b));
        assertEquals(new Vector3(1.5, 1, 0), a.sub(1));
    }

    @Test
    public void testMultiplication()
    {
        Vector3 a = new Vector3(1.5, 2, 1);
        Vector3 b = new Vector3(2, 0.5, -1);

        assertEquals(new Vector3(3, 1, -1), a.mult(b));
        assertEquals(new Vector3(3, 4, 2), a.mult(2));
    }

    @Test
    public void testNegation()
    {
        Vector3 a = new Vector3(1, -3, 2);
        assertEquals(new Vector3(-1, 3, -2), a.negate());
    }

    @Test
    public void testDivision()
    {
        Vector3 a = new Vector3(3, 1, -5);
        Vector3 b = new Vector3(2, -1, 2);

        assertEquals(new Vector3(1.5, -1, -2.5), a.div(b));
        assertEquals(new Vector3(-1.5, -0.5, 2.5), a.div(-2));
    }

    @Test
    public void testDotProduct()
    {
        Vector3 a = new Vector3(1, 2, 3);
        Vector3 b = new Vector3(3, 2, 1);

        assertEquals(10, a.dot(b));
    }

    @Test
    public void testCrossProduct()
    {
        Vector3 a = new Vector3(1, 2, 3);
        Vector3 b = new Vector3(3, 2, 1);

        assertEquals(new Vector3(-4, 8, -4), a.cross(b));
    }

    @Test
    public void testMagnitude()
    {
        Vector3 a = new Vector3(3, 2, 6);

        double magSq = a.magSq();
        double mag = a.mag();

        assertEquals(magSq, 49);
        assertEquals(mag, 7);

        assertEquals(magSq, mag * mag);
    }

    @Test
    public void testNormalization()
    {
        Vector3 a = new Vector3(3, 2, 6);
        assertEquals(a.normalize().mag(), 1, 0.01);

        Vector3 b = new Vector3(0, 5, 0);
        assertEquals(new Vector3(0, 1, 0), b.normalize());
    }

}