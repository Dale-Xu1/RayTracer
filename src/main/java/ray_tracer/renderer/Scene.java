package ray_tracer.renderer;

import ray_tracer.material.Emission;
import ray_tracer.math.Color;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.object.geometry.Geometry;

import java.util.ArrayList;
import java.util.List;

public class Scene
{

    private final Emission background;
    private final List<Geometry> objects = new ArrayList<>();

    private final int maxDepth;


    public Scene(Emission background, int maxDepth)
    {
        this.background = background;
        this.maxDepth = maxDepth;
    }

    public Scene(Emission background)
    {
        this(background, 4);
    }


    public Color traceRay(Ray ray)
    {
        // This is black because light can't come from nowhere
        if (ray.getDepth() > maxDepth) return Color.BLACK;

        Intersection minIntersection = null;

        // Test intersection with all objects
        for (Geometry object : objects)
        {
            Intersection intersection = object.isIntersecting(ray);

            // Get closest intersection
            if (intersection != null)
            {
                double distance = intersection.getDistance();

                // Replace if no intersection exists or distance is smaller
                if (distance > 1e-5 && (minIntersection == null || distance < minIntersection.getDistance()))
                {
                    minIntersection = intersection;
                }
            }
        }

        if (minIntersection != null)
        {
            // Apply shader
            return minIntersection.getMaterial().shader(this, minIntersection);
        }

        // Default to background color
        return background.shader(this, null);
    }


    public void add(Geometry object)
    {
        objects.add(object);
    }

    public void remove(Geometry object)
    {
        objects.remove(object);
    }

}
