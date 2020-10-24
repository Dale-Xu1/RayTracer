package ray_tracer.math;

import java.util.Arrays;

public class Matrix4
{

    public static Matrix4 identity = new Matrix4(new double[][]
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


    private final double[][] data;


    public Matrix4(double[][] data)
    {
        this.data = data;
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
        double w = (data[3][0] * vector.x) + (data[3][1] * vector.y) + (data[3][2] * vector.z) + data[3][3];

        return new Vector3(x / w, y / w, z / w);
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
