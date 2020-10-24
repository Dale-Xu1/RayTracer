package ray_tracer.math;

public class Matrix4
{

    private final double[][] data;


    public Matrix4(double[][] data)
    {
        this.data = data;
    }


    public double get(int i, int j)
    {
        return data[i][j];
    }

}
