package ray_tracer.object;

import ray_tracer.material.Material;
import ray_tracer.math.Vector3;

public class Intersection
{

    private final Ray ray;

    private final Vector3 position;
    private final Vector3 normal;

    private final double distance;

    private final Material material;


    public Intersection(Ray ray, Vector3 position, Vector3 normal, double distance, Material material)
    {
        this.ray = ray;
        this.position = position;
        this.normal = normal;
        this.distance = distance;
        this.material = material;
    }


    public Ray getRay()
    {
        return ray;
    }

    public Vector3 getPosition()
    {
        return position;
    }

    public Vector3 getNormal()
    {
        return normal;
    }

    public double getDistance()
    {
        return distance;
    }

    public Material getMaterial()
    {
        return material;
    }

}
