package ray_tracer.renderer;

import ray_tracer.material.Emission;
import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.object.geometry.Geometry;
import ray_tracer.object.light.Light;

import java.util.ArrayList;
import java.util.List;

public class Scene
{

    private Emission background;

    private final List<Geometry> objects = new ArrayList<>();
    private final List<Light> lights = new ArrayList<>();

    private int samples;
    private int bounces;


    public Scene(Emission background, int samples, int bounces)
    {
        this.background = background;
        this.samples = samples;
        this.bounces = bounces;
    }

    public Scene(Emission background)
    {
        this(background, 8, 4);
    }


    public Color traceRay(Ray ray)
    {
        // This is black because light can't come from nowhere
        if (ray.getDepth() > bounces) return Color.BLACK;

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
                if (distance > Vector3.EPSILON && (minIntersection == null || distance < minIntersection.getDistance()))
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


    public List<Geometry> getObjects()
    {
        return objects;
    }

    public List<Light> getLights()
    {
        return lights;
    }


    public Emission getBackground()
    {
        return background;
    }

    public void setBackground(Emission background)
    {
        this.background = background;
    }

    public int getSamples()
    {
        return samples;
    }

    public void setSamples(int samples)
    {
        this.samples = samples;
    }

    public int getBounces()
    {
        return bounces;
    }

    public void setBounces(int bounces)
    {
        this.bounces = bounces;
    }

}
