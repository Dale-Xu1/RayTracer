package ray_tracer.object.light;

import ray_tracer.material.Emission;
import ray_tracer.object.Transformation;

public abstract class Light extends Transformation
{

    private Emission emission;


    protected Light(Emission emission)
    {
        this.emission = emission;
    }


    public Emission getEmission()
    {
        return emission;
    }

    public void setEmission(Emission emission)
    {
        this.emission = emission;
    }

}
