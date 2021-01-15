package ray_tracer.object.light;

import ray_tracer.material.Emission;
import ray_tracer.material.LightMaterial;
import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.object.Transformation;
import ray_tracer.object.geometry.Geometry;
import ray_tracer.renderer.Scene;

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


    public Color shader(Scene scene, Intersection intersection)
    {
        Vector3 position = intersection.getPosition();
        LightMaterial material = (LightMaterial) intersection.getMaterial();

        // Only one sample is required if size is 0
        int samples = (size == 0) ? 1 : scene.getSamples();
        Color light = Color.BLACK;

        for (int i = 0; i < samples; i++)
        {
            // Create ray
            Ray ray = createRay(position);
            if (intersectsObjects(scene, ray)) continue;

            // Get color and brightness
            Color color = emission.shader(scene, null);
            double brightness = material.light(intersection, ray.getDirection());

            light = light.add(color.mult(brightness));
        }

        // Take average of samples
        return light.div(samples);
    }

    public abstract Ray createRay(Vector3 position);

    private boolean intersectsObjects(Scene scene, Ray ray)
    {
        // Test if ray hits object on path to light
        for (Geometry object : scene.getObjects())
        {
            Intersection intersection = object.isIntersecting(ray);
            if (intersection != null && intersection.getDistance() > Vector3.EPSILON) return true;
        }

        return false;
    }

}
