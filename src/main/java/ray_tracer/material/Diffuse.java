package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.renderer.Scene;

public class Diffuse extends LightMaterial
{

    private double roughness;


    public Diffuse(Color color, double roughness)
    {
        super(color);
        this.roughness = roughness;
    }

    public Diffuse(Color color)
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
        Color direct = direct(scene, intersection);
        Color indirect = indirect(scene, intersection);

        return getColor().mult(direct.add(indirect));
    }

    private Color indirect(Scene scene, Intersection intersection)
    {
        // Sample indirect lighting
        int samples = scene.getSamples();
        Color indirect = Color.BLACK;

        for (int i = 0; i < samples; i++)
        {
            Vector3 sample = Vector3.randomInSphere();

            // Calculate ray
            Vector3 position = intersection.getPosition();
            Vector3 direction = intersection.getNormal().add(sample).normalize();

            int depth = intersection.getRay().getDepth() + 1;
            indirect = indirect.add(scene.traceRay(new Ray(position, direction, depth)));
        }

        // Take average of samples
        return indirect.div(samples);
    }


    @Override
    protected double shade(Intersection intersection, Vector3 light)
    {
        Vector3 view = intersection.getRay().getDirection();
        Vector3 normal = intersection.getNormal();

        double vn = view.dot(normal);
        double ln = light.dot(normal);

        double tr = Math.acos(vn);
        double ti = Math.acos(ln);
        double cp = view.sub(normal.mult(vn)).normalize().dot(light.sub(normal.mult(ln)).normalize());

        double alpha = Math.max(ti, tr);
        double beta = Math.min(ti, tr);
        double sigma = roughness * roughness;

        double a = 1 - (0.5 * sigma / (sigma + 0.33));
        double b = 0.45 * (sigma / (sigma + 0.09));

        b *= (cp > 0) ? cp * Math.sin(alpha) * Math.tan(beta) : 0;
        return Math.max(0, ln * (a + b) / Math.PI);
    }

}
