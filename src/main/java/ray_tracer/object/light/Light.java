package ray_tracer.object.light;

import ray_tracer.material.Emission;
import ray_tracer.math.Vector3;
import ray_tracer.object.Ray;
import ray_tracer.object.Transformation;

public abstract class Light extends Transformation
{

    private Emission emission;
    private double size;


    protected Light(Emission emission, double size)
    {
        this.emission = emission;
        this.size = size;
    }


    public Emission getEmission()
    {
        return emission;
    }

    public void setEmission(Emission emission)
    {
        this.emission = emission;
    }

    public double getSize()
    {
        return size;
    }

    protected void setSize(double size)
    {
        this.size = size;
    }


    public abstract Ray createRay(Vector3 position);

}
