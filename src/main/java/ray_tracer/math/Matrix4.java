package ray_tracer.math;

import java.util.Arrays;

public class Matrix4
{

    public static Matrix4 IDENTITY = new Matrix4(new double[][]
    {
        { 1, 0, 0, 0 },
        { 0, 1, 0, 0 },
        { 0, 0, 1, 0 },
        { 0, 0, 0, 1 }
    });

    public static Matrix4 translate(double x, double y, double z)
    {
        return new Matrix4(new double[][]
        {
            { 1, 0, 0, x },
            { 0, 1, 0, y },
            { 0, 0, 1, z },
            { 0, 0, 0, 1 }
        });
    }

    public static Matrix4 rotateX(double a)
    {
        double s = Math.sin(a);
        double c = Math.cos(a);

        return new Matrix4(new double[][]
        {
            { 1, 0, 0, 0 },
            { 0, c,-s, 0 },
            { 0, s, c, 0 },
            { 0, 0, 0, 1 }
        });
    }

    public static Matrix4 rotateY(double a)
    {
        double s = Math.sin(a);
        double c = Math.cos(a);

        return new Matrix4(new double[][]
        {
            { c, 0, s, 0 },
            { 0, 1, 0, 0 },
            {-s, 0, c, 0 },
            { 0, 0, 0, 1 }
        });
    }

    public static Matrix4 rotateZ(double a)
    {
        double s = Math.sin(a);
        double c = Math.cos(a);

        return new Matrix4(new double[][]
        {
            { c,-s, 0, 0 },
            { s, c, 0, 0 },
            { 0, 0, 1, 0 },
            { 0, 0, 0, 1 }
        });
    }

    public static Matrix4 scale(double x, double y, double z)
    {
        return new Matrix4(new double[][]
        {
            { x, 0, 0, 0 },
            { 0, y, 0, 0 },
            { 0, 0, z, 0 },
            { 0, 0, 0, 1 }
        });
    }

    public static Matrix4 lookAt(Vector3 position, Vector3 target, Vector3 up)
    {
        Vector3 z = target.sub(position).normalize();
        Vector3 x = up.normalize().cross(z);
        Vector3 y = z.cross(x);

        return new Matrix4(new double[][]
        {
            { x.x, y.x, z.x, position.x },
            { x.y, y.y, z.y, position.y },
            { x.z, y.z, z.z, position.z },
            { 0,   0,   0,   1          }
        });
    }

    public static Matrix4 lookAt(Vector3 position, Vector3 target)
    {
        return lookAt(position, target, Vector3.UP);
    }


    private final double[][] data;


    public Matrix4(double[][] data)
    {
        this.data = data;
    }


    public double get(int i, int j)
    {
        return data[i][j];
    }


    public Matrix4 mult(Matrix4 matrix)
    {
        double[][] a = data;
        double[][] b = matrix.data;
        double[][] data = new double[4][4];

        data[0][0] = (a[0][0] * b[0][0]) + (a[0][1] * b[1][0]) + (a[0][2] * b[2][0]) + (a[0][3] * b[3][0]);
        data[0][1] = (a[0][0] * b[0][1]) + (a[0][1] * b[1][1]) + (a[0][2] * b[2][1]) + (a[0][3] * b[3][1]);
        data[0][2] = (a[0][0] * b[0][2]) + (a[0][1] * b[1][2]) + (a[0][2] * b[2][2]) + (a[0][3] * b[3][2]);
        data[0][3] = (a[0][0] * b[0][3]) + (a[0][1] * b[1][3]) + (a[0][2] * b[2][3]) + (a[0][3] * b[3][3]);

        data[1][0] = (a[1][0] * b[0][0]) + (a[1][1] * b[1][0]) + (a[1][2] * b[2][0]) + (a[1][3] * b[3][0]);
        data[1][1] = (a[1][0] * b[0][1]) + (a[1][1] * b[1][1]) + (a[1][2] * b[2][1]) + (a[1][3] * b[3][1]);
        data[1][2] = (a[1][0] * b[0][2]) + (a[1][1] * b[1][2]) + (a[1][2] * b[2][2]) + (a[1][3] * b[3][2]);
        data[1][3] = (a[1][0] * b[0][3]) + (a[1][1] * b[1][3]) + (a[1][2] * b[2][3]) + (a[1][3] * b[3][3]);

        data[2][0] = (a[2][0] * b[0][0]) + (a[2][1] * b[1][0]) + (a[2][2] * b[2][0]) + (a[2][3] * b[3][0]);
        data[2][1] = (a[2][0] * b[0][1]) + (a[2][1] * b[1][1]) + (a[2][2] * b[2][1]) + (a[2][3] * b[3][1]);
        data[2][2] = (a[2][0] * b[0][2]) + (a[2][1] * b[1][2]) + (a[2][2] * b[2][2]) + (a[2][3] * b[3][2]);
        data[2][3] = (a[2][0] * b[0][3]) + (a[2][1] * b[1][3]) + (a[2][2] * b[2][3]) + (a[2][3] * b[3][3]);

        data[3][0] = (a[3][0] * b[0][0]) + (a[3][1] * b[1][0]) + (a[3][2] * b[2][0]) + (a[3][3] * b[3][0]);
        data[3][1] = (a[3][0] * b[0][1]) + (a[3][1] * b[1][1]) + (a[3][2] * b[2][1]) + (a[3][3] * b[3][1]);
        data[3][2] = (a[3][0] * b[0][2]) + (a[3][1] * b[1][2]) + (a[3][2] * b[2][2]) + (a[3][3] * b[3][2]);
        data[3][3] = (a[3][0] * b[0][3]) + (a[3][1] * b[1][3]) + (a[3][2] * b[2][3]) + (a[3][3] * b[3][3]);

        return new Matrix4(data);
    }

    public Vector3 mult(Vector3 vector)
    {
        double x = (data[0][0] * vector.x) + (data[0][1] * vector.y) + (data[0][2] * vector.z) + data[0][3];
        double y = (data[1][0] * vector.x) + (data[1][1] * vector.y) + (data[1][2] * vector.z) + data[1][3];
        double z = (data[2][0] * vector.x) + (data[2][1] * vector.y) + (data[2][2] * vector.z) + data[2][3];

        return new Vector3(x, y, z);
    }


    public Matrix4 transpose()
    {
        return new Matrix4(new double[][]
        {
            { data[0][0], data[1][0], data[2][0], data[3][0] },
            { data[0][1], data[1][1], data[2][1], data[3][1] },
            { data[0][2], data[1][2], data[2][2], data[3][2] },
            { data[0][3], data[1][3], data[2][3], data[3][3] }
        });
    }


    public Matrix4 inverse()
    {
        double[][] m = data;
        double[][] data = new double[4][4];

        double a2323 = (m[2][2] * m[3][3]) - (m[2][3] * m[3][2]);
        double a1323 = (m[2][1] * m[3][3]) - (m[2][3] * m[3][1]);
        double a1223 = (m[2][1] * m[3][2]) - (m[2][2] * m[3][1]);
        double a0323 = (m[2][0] * m[3][3]) - (m[2][3] * m[3][0]);
        double a0223 = (m[2][0] * m[3][2]) - (m[2][2] * m[3][0]);
        double a0123 = (m[2][0] * m[3][1]) - (m[2][1] * m[3][0]);
        double a2313 = (m[1][2] * m[3][3]) - (m[1][3] * m[3][2]);
        double a1313 = (m[1][1] * m[3][3]) - (m[1][3] * m[3][1]);
        double a1213 = (m[1][1] * m[3][2]) - (m[1][2] * m[3][1]);
        double a2312 = (m[1][2] * m[2][3]) - (m[1][3] * m[2][2]);
        double a1312 = (m[1][1] * m[2][3]) - (m[1][3] * m[2][1]);
        double a1212 = (m[1][1] * m[2][2]) - (m[1][2] * m[2][1]);
        double a0313 = (m[1][0] * m[3][3]) - (m[1][3] * m[3][0]);
        double a0213 = (m[1][0] * m[3][2]) - (m[1][2] * m[3][0]);
        double a0312 = (m[1][0] * m[2][3]) - (m[1][3] * m[2][0]);
        double a0212 = (m[1][0] * m[2][2]) - (m[1][2] * m[2][0]);
        double a0113 = (m[1][0] * m[3][1]) - (m[1][1] * m[3][0]);
        double a0112 = (m[1][0] * m[2][1]) - (m[1][1] * m[2][0]);

        double determinant = 1 / (m[0][0] * ((m[1][1] * a2323) - (m[1][2] * a1323) + (m[1][3] * a1223))
                                - m[0][1] * ((m[1][0] * a2323) - (m[1][2] * a0323) + (m[1][3] * a0223))
                                + m[0][2] * ((m[1][0] * a1323) - (m[1][1] * a0323) + (m[1][3] * a0123))
                                - m[0][3] * ((m[1][0] * a1223) - (m[1][1] * a0223) + (m[1][2] * a0123)));

        data[0][0] =  determinant * ((m[1][1] * a2323) - (m[1][2] * a1323) + (m[1][3] * a1223));
        data[0][1] = -determinant * ((m[0][1] * a2323) - (m[0][2] * a1323) + (m[0][3] * a1223));
        data[0][2] =  determinant * ((m[0][1] * a2313) - (m[0][2] * a1313) + (m[0][3] * a1213));
        data[0][3] = -determinant * ((m[0][1] * a2312) - (m[0][2] * a1312) + (m[0][3] * a1212));

        data[1][0] = -determinant * ((m[1][0] * a2323) - (m[1][2] * a0323) + (m[1][3] * a0223));
        data[1][1] =  determinant * ((m[0][0] * a2323) - (m[0][2] * a0323) + (m[0][3] * a0223));
        data[1][2] = -determinant * ((m[0][0] * a2313) - (m[0][2] * a0313) + (m[0][3] * a0213));
        data[1][3] =  determinant * ((m[0][0] * a2312) - (m[0][2] * a0312) + (m[0][3] * a0212));

        data[2][0] =  determinant * ((m[1][0] * a1323) - (m[1][1] * a0323) + (m[1][3] * a0123));
        data[2][1] = -determinant * ((m[0][0] * a1323) - (m[0][1] * a0323) + (m[0][3] * a0123));
        data[2][2] =  determinant * ((m[0][0] * a1313) - (m[0][1] * a0313) + (m[0][3] * a0113));
        data[2][3] = -determinant * ((m[0][0] * a1312) - (m[0][1] * a0312) + (m[0][3] * a0112));

        data[3][0] = -determinant * ((m[1][0] * a1223) - (m[1][1] * a0223) + (m[1][2] * a0123));
        data[3][1] =  determinant * ((m[0][0] * a1223) - (m[0][1] * a0223) + (m[0][2] * a0123));
        data[3][2] = -determinant * ((m[0][0] * a1213) - (m[0][1] * a0213) + (m[0][2] * a0113));
        data[3][3] =  determinant * ((m[0][0] * a1212) - (m[0][1] * a0212) + (m[0][2] * a0112));

        return new Matrix4(data);
    }


    @Override
    public String toString()
    {
        return "[" + data[0][0] + ", " + data[0][1] + ", " + data[0][2] + ", " + data[0][3] + "\n" +
               " " + data[1][0] + ", " + data[1][1] + ", " + data[1][2] + ", " + data[1][3] + "\n" +
               " " + data[2][0] + ", " + data[2][1] + ", " + data[2][2] + ", " + data[2][3] + "\n" +
               " " + data[3][0] + ", " + data[3][1] + ", " + data[3][2] + ", " + data[3][3] + "]";
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Matrix4 matrix = (Matrix4) object;
        return Arrays.deepEquals(data, matrix.data);
    }

}
