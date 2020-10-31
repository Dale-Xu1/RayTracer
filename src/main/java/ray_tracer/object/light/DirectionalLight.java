package ray_tracer.object.light;

import ray_tracer.material.Emission;
import ray_tracer.math.Matrix4;
import ray_tracer.math.Vector3;
import ray_tracer.object.Ray;

public class DirectionalLight extends Light
{

    private Vector3 direction = Vector3.UP;
    private double softness;


    public DirectionalLight(Emission emission, double softness)
    {
        super(emission);
        this.softness = softness;
    }

    public DirectionalLight(Emission emission)
    {
        this(emission, 0.05);
    }


    public double getSoftness()
    {
        return softness;
    }

    public void setSoftness(double softness)
    {
        this.softness = softness;
    }


    @Override
    public Ray createRay(Vector3 position)
    {
        // Create shadow ray
        Vector3 shadow = direction.add(Vector3.randomInSphere().mult(softness)).normalize();
        return new Ray(position, shadow);
    }

    @Override
    public void setTransform(Matrix4 transform)
    {
        super.setTransform(transform);
        direction = getNormalTransform().mult(Vector3.UP).normalize();
    }

}
