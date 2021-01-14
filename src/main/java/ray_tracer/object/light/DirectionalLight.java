package ray_tracer.object.light;

import ray_tracer.material.Emission;
import ray_tracer.math.Matrix4;
import ray_tracer.math.Vector3;
import ray_tracer.object.Ray;

public class DirectionalLight extends Light
{

    private Vector3 direction = Vector3.UP;

    private double angle;
    private double size;


    public DirectionalLight(Emission emission, double angle)
    {
        super(emission);
        setAngle(angle);
    }

    public DirectionalLight(Emission emission)
    {
        this(emission, 0.01);
    }


    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
        this.size = Math.tan(angle);
    }


    @Override
    public Ray createRay(Vector3 position)
    {
        // Create shadow ray
        Vector3 shadow = direction.add(Vector3.randomInSphere().mult(size)).normalize();
        return new Ray(position, shadow);
    }

    @Override
    public void setTransform(Matrix4 transform)
    {
        super.setTransform(transform);
        direction = getNormalTransform().mult(Vector3.UP).normalize();
    }

}
