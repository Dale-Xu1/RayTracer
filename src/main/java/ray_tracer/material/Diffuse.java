package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.renderer.Scene;

public class Diffuse extends Material
{

    public Diffuse(Color color)
    {
        super(color);
    }


    @Override
    public Color shader(Scene scene, Intersection intersection)
    {
        // Sample indirect lighting
        int samples = scene.getSamples();
        Color indirect = Color.BLACK;

        for (int i = 0; i < samples; i++)
        {
            Vector3 sample = scene.randomInSphere();

            // Calculate ray
            Vector3 position = intersection.getPosition();
            Vector3 direction = intersection.getNormal().add(sample).normalize();

            int depth = intersection.getRay().getDepth() + 1;
            indirect = indirect.add(scene.traceRay(new Ray(position, direction, depth)));
        }

        // Take average of samples
        return getColor().mult(indirect.div(samples));
    }

}
