package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.object.geometry.Geometry;
import ray_tracer.object.light.Light;
import ray_tracer.renderer.Scene;

public abstract class LightMaterial extends Material
{

    protected LightMaterial(Color color)
    {
        super(color);
    }


    protected Color direct(Scene scene, Intersection intersection)
    {
        // Sample direct lighting
        int samples = scene.getSamples();
        Color direct = Color.BLACK;

        Vector3 position = intersection.getPosition();

        for (Light light : scene.getLights())
        {
            Color emission = Color.BLACK;

            for (int i = 0; i < samples; i++)
            {
                // Create ray
                Ray ray = light.createRay(position);
                if (intersectsObjects(scene, ray)) continue;

                // Get color and brightness
                Color color = light.getEmission().shader(scene, null);
                double brightness = shade(intersection, ray.getDirection());

                emission = emission.add(color.mult(brightness));
            }

            // Take average of samples
            direct = direct.add(emission.div(samples));
        }

        return direct;
    }

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

    protected abstract double shade(Intersection intersection, Vector3 light);

}
