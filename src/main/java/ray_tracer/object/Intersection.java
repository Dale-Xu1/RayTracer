package ray_tracer.object;

import ray_tracer.material.Material;
import ray_tracer.math.Vector3;

public class Intersection
{

    private final Ray ray;
    private final Material material;

    private final Vector3 position;
    private final Vector3 normal;

    private final double distance;


    public Intersection(Ray ray, Material material, Vector3 position, Vector3 normal, double distance)
    {
        this.ray = ray;
        this.material = material;

        this.position = position;
        this.normal = normal;

        this.distance = distance;
    }


    public Ray getRay()
    {
        return ray;
    }

    public Material getMaterial()
    {
        return material;
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

}
