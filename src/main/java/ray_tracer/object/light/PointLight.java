package ray_tracer.object.light;

import ray_tracer.material.Emission;
import ray_tracer.math.Vector3;
import ray_tracer.object.Ray;

public class PointLight extends Light
{

    public PointLight(Emission emission)
    {
        super(emission);
    }


    @Override
    public Ray createRay(Vector3 position)
    {
        return null;
    }

}
