package ray_tracer.object.geometry;

import ray_tracer.material.Material;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;

public class Sphere extends Geometry
{

    private double radius;


    public Sphere(Material material, double radius)
    {
        super(material);
        this.radius = radius;
    }


    public double getRadius()
    {
        return radius;
    }

    public void setRadius(double radius)
    {
        this.radius = radius;
    }


    @Override
    public Intersection isIntersecting(Ray ray)
    {
        // Get data
        Vector3 position = getPosition();

        Vector3 pos = ray.getPosition();
        Vector3 dir = ray.getDirection();

        Vector3 l = pos.sub(position);

        double a = dir.magSq();
        double b = 2 * l.dot(dir);
        double c = l.magSq() - (radius * radius);

        double discriminant = (b * b) - (4 * a * c);
        if (discriminant < 0) return null;

        double distance = (-b - Math.sqrt(discriminant)) / (2 * a);

        // Calculate intersection position and normal
        Vector3 intersection = pos.add(dir.mult(distance));
        Vector3 normal = intersection.sub(position).normalize();

        return new Intersection(ray, intersection, normal, distance, getMaterial());
    }

}
