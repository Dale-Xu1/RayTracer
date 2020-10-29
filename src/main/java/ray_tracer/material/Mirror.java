package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;
import ray_tracer.renderer.Scene;

public class Mirror extends Material
{

    public Mirror(Color color)
    {
        super(color);
    }


    @Override
    public Color shader(Scene scene, Intersection intersection)
    {
        // Get data
        Ray ray = intersection.getRay();

        Vector3 direction = ray.getDirection();
        Vector3 normal = intersection.getNormal();

        // Calculate reflection ray
        Vector3 reflection = direction.sub(normal.mult(2 * normal.dot(direction)));
        Ray reflectionRay = new Ray(intersection.getPosition(), reflection, ray.getDepth() + 1);

        return getColor().mult(scene.traceRay(reflectionRay));
    }

}
