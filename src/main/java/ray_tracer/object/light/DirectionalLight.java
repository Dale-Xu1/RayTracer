package ray_tracer.object.light;

import ray_tracer.material.Emission;
import ray_tracer.math.Matrix4;
import ray_tracer.math.Vector3;
import ray_tracer.object.Ray;

public class DirectionalLight extends Light
{

    private Vector3 direction = Vector3.DOWN;


    public DirectionalLight(Emission emission)
    {
        super(emission);
    }


    @Override
    public Ray createRay(Vector3 position)
    {
        Vector3 dir = direction.negate().add(Vector3.randomInSphere().mult(0.1)).normalize();
        return new Ray(position, dir);
    }

    @Override
    public void setTransform(Matrix4 transform)
    {
        super.setTransform(transform);
        direction = getNormalTransform().mult(Vector3.DOWN).normalize();
    }

}
