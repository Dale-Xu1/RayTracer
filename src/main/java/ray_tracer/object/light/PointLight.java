package ray_tracer.object.light;

import ray_tracer.material.Emission;
import ray_tracer.math.Vector3;
import ray_tracer.object.Ray;

public class PointLight extends Light
{

    public PointLight(Emission emission, double size)
    {
        super(emission, size);
    }

    public PointLight(Emission emission)
    {
        this(emission, 0.05);
    }


    @Override
    public void setSize(double size)
    {
        super.setSize(size);
    }

    @Override
    public Ray createRay(Vector3 position)
    {
        return null;
    }

}
