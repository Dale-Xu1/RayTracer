package ray_tracer.object;

import ray_tracer.math.Matrix4;
import ray_tracer.math.Vector3;

public abstract class Transformation
{

    private Matrix4 transform = Matrix4.identity;
    private Matrix4 normalTransform = Matrix4.identity;


    protected Matrix4 getTransform()
    {
        return transform;
    }

    protected Matrix4 getNormalTransform()
    {
        return normalTransform;
    }


    public void translate(double x, double y, double z)
    {
        setTransform(transform.mult(Matrix4.translate(x, y, z)));
    }

    public void translate(Vector3 vector)
    {
        translate(vector.x, vector.y, vector.z);
    }


    public void rotateX(double a)
    {
        setTransform(transform.mult(Matrix4.rotateX(a)));
    }

    public void rotateY(double a)
    {
        setTransform(transform.mult(Matrix4.rotateY(a)));
    }

    public void rotateZ(double a)
    {
        setTransform(transform.mult(Matrix4.rotateZ(a)));
    }


    public void scale(double x, double y, double z)
    {
        setTransform(transform.mult(Matrix4.scale(x, y, z)));
    }

    public void scale(double s)
    {
        setTransform(transform.mult(Matrix4.scale(s, s, s)));
    }

    public void scale(Vector3 vector)
    {
        scale(vector.x, vector.y, vector.z);
    }


    public void setTransform(Matrix4 transform)
    {
        this.transform = transform;
        normalTransform = transform.inverse().transpose();

        // Transform data
        transform();
    }

    protected abstract void transform();

}
