package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.renderer.Scene;

public class Glossy extends Material
{

    private double roughness;


    public Glossy(Color color, double roughness)
    {
        super(color);
        this.roughness = roughness;
    }

    public Glossy(Color color)
    {
        this(color, 0.5);
    }


    public double getRoughness()
    {
        return roughness;
    }

    public void setRoughness(double roughness)
    {
        this.roughness = roughness;
    }


    @Override
    public Color shader(Scene scene, Intersection intersection)
    {
        // Get data
        Ray ray = intersection.getRay();

        Vector3 direction = ray.getDirection();
        Vector3 normal = intersection.getNormal();

        // Sample indirect lighting
        int samples = scene.getSamples();
        Color indirect = Color.BLACK;

        for (int i = 0; i < samples; i++)
        {
            // Sample roughness value
            Vector3 sample = scene.randomInSphere().mult(roughness);

            // Calculate reflection ray
            Vector3 reflection = direction.sub(normal.mult(2 * normal.dot(direction))).add(sample);
            Ray reflectionRay = new Ray(intersection.getPosition(), reflection, ray.getDepth() + 1);

            indirect = indirect.add(scene.traceRay(reflectionRay));
        }

        // Take average of samples
        return getColor().mult(indirect.div(samples));
    }

}
