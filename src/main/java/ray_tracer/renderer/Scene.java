package ray_tracer.renderer;

import ray_tracer.material.Emission;
import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.object.geometry.Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scene
{

    private Emission background;
    private final List<Geometry> objects = new ArrayList<>(); // TODO: Lights

    private Random random;

    private int samples;
    private int seed;
    private int maxDepth;


    public Scene(Emission background, int samples, int seed, int maxDepth)
    {
        this.background = background;
        this.samples = samples;
        this.maxDepth = maxDepth;

        setSeed(seed);
    }

    public Scene(Emission background)
    {
        this(background, 8, 0, 4);
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

    public Vector3 randomInCircle()
    {
        double a = random.nextDouble() * (Math.PI * 2);
        double r = Math.sqrt(random.nextDouble());

        return new Vector3(Math.cos(a) * r, Math.sin(a) * r, 0);
    }

    public Vector3 randomInSphere()
    {
        // Sample random location within a sphere
        double u = random.nextDouble();
        double v = random.nextDouble();

        double theta = u * (Math.PI * 2);
        double phi = Math.acos((v * 2) - 1);

        double r = Math.cbrt(random.nextDouble());

        return new Vector3(
            r * Math.sin(phi) * Math.cos(theta),
            r * Math.sin(phi) * Math.sin(theta),
            r * Math.cos(phi)
        );
    }


    public void add(Geometry object)
    {
        objects.add(object);
    }

    public void remove(Geometry object)
    {
        objects.remove(object);
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

    public int getSeed()
    {
        return seed;
    }

    public void setSeed(int seed)
    {
        this.seed = seed;
        this.random = new Random(seed);
    }

    public int getMaxDepth()
    {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth)
    {
        this.maxDepth = maxDepth;
    }

}
