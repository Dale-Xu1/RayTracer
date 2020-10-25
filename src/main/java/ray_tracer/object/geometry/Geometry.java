package ray_tracer.object.geometry;

import ray_tracer.material.Material;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.object.Transformation;

public abstract class Geometry extends Transformation
{

    private final Material material;


    protected Geometry(Material material)
    {
        this.material = material;
    }


    protected Material getMaterial()
    {
        return material;
    }

    public abstract Intersection isIntersecting(Ray ray);

}
