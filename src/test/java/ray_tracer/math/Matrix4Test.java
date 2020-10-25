package ray_tracer.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Matrix4Test
{

    @Test
    public void testMultiplication()
    {
        Matrix4 a = new Matrix4(new double[][]
        {
            { 1, 2, 1, 0 },
            { 0, 5, 0, 2 },
            { 2, 0, 4, 1 },
            { 1, 1, 0, 3 }
        });

        Matrix4 b = new Matrix4(new double[][]
        {
            { 2, 5, 1, 1 },
            { 6, 3, 0, 2 },
            { 0, 8, 1, 2 },
            { 1, 5, 1, 5 }
        });

        assertEquals(new Matrix4(new double[][]
        {
            { 14, 19, 2, 7 },
            { 32, 25, 2, 20 },
            { 5, 47, 7, 15 },
            { 11, 23, 4, 18 }
        }), a.mult(b));
    }

    @Test
    public void testVectorMultiplication()
    {
        Matrix4 a = new Matrix4(new double[][]
        {
            { 1, 2, 1, 0 },
            { 0, 5, 0, 2 },
            { 2, 0, 4, 1 },
            { 0, 0, 0, 1 }
        });

        Vector3 b = new Vector3(2, 1, 3);

        assertEquals(new Vector3(7, 7, 17), a.mult(b));
    }

    @Test
    public void testTransposition()
    {
        Matrix4 a = new Matrix4(new double[][]
        {
            { 1, 2, 1, 0 },
            { 0, 5, 0, 2 },
            { 2, 0, 4, 1 },
            { 1, 1, 0, 3 }
        });

        assertEquals(new Matrix4(new double[][]
        {
            { 1, 0, 2, 1 },
            { 2, 5, 0, 1 },
            { 1, 0, 4, 0 },
            { 0, 2, 1, 3 }
        }), a.transpose());
    }

    @Test
    public void testInversion()
    {
        Matrix4 a = new Matrix4(new double[][]
        {
            { 2, 5, 1, 1 },
            { 6, 3, 0, 2 },
            { 0, 8, 1, 2 },
            { 1, 5, 1, 5 }
        });

        Matrix4 b = a.inverse().mult(a);

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                assertEquals(Matrix4.IDENTITY.get(i, j), b.get(i, j), 0.01);
            }
        }
    }

}