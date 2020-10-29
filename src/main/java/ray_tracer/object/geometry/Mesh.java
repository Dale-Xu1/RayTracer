package ray_tracer.object.geometry;

import ray_tracer.material.Material;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mesh extends Sphere
{

    private static double calculateRadius(Triangle[] triangles)
    {
        double maxRadius = 0;

        for (Triangle triangle : triangles)
        {
            // Get farthest vertex
            double v1 = triangle.getVertex1().magSq();
            double v2 = triangle.getVertex2().magSq();
            double v3 = triangle.getVertex3().magSq();

            double radius = Math.max(v1, Math.max(v2, v3));

            // Test if vertex is the farthest
            if (radius > maxRadius)
            {
                maxRadius = radius;
            }
        }

        return Math.sqrt(maxRadius);
    }


    private final List<Triangle> triangles;


    public Mesh(Triangle... triangles)
    {
        super(null, calculateRadius(triangles));
        this.triangles = new ArrayList<>(Arrays.asList(triangles));
    }


    public List<Triangle> getTriangles()
    {
        return triangles;
    }

    public void addTriangle(Triangle triangle)
    {
        triangles.add(triangle);
    }

    public void removeTriangle(Triangle triangle)
    {
        triangles.remove(triangle);
    }


    @Override
    public Intersection isIntersecting(Ray ray)
    {
        // If ray doesn't intersect sphere, it doesn't intersect mesh
        if (super.isIntersecting(ray) == null) return null;

        // Get closest triangle
        Intersection minIntersection = null;

        for (Triangle triangle : triangles)
        {
            Intersection intersection = triangle.isIntersecting(ray);

            // Get closest intersection
            if (intersection != null)
            {
                double distance = intersection.getDistance();

                // Replace if no intersection exists or distance is smaller
                if (distance > Vector3.EPSILON && (minIntersection == null || distance < minIntersection.getDistance()))
                {
                    minIntersection = intersection;
                }
            }
        }

        return minIntersection;
    }

}
