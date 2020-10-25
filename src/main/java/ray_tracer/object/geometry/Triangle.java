package ray_tracer.object.geometry;

import ray_tracer.material.Material;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.Ray;

public class Triangle extends Geometry
{

    private final Vector3 base1;
    private final Vector3 base2;
    private final Vector3 base3;
    private final Vector3 baseNormal;

    private Vector3 v1;
    private Vector3 v2;
    private Vector3 v3;
    private Vector3 normal;


    public Triangle(Material material, Vector3 v1, Vector3 v2, Vector3 v3)
    {
        super(material);

        this.v1 = base1 = v1;
        this.v2 = base2 = v2;
        this.v3 = base3 = v3;

        // Calculate normal
        Vector3 e1 = v2.sub(v1);
        Vector3 e2 = v3.sub(v1);

        this.normal = baseNormal = e1.cross(e2).normalize();
    }


    @Override
    public Intersection isIntersecting(Ray ray)
    {
        Vector3 pos = ray.getPosition();
        Vector3 dir = ray.getDirection();

        Vector3 e1 = v2.sub(v1);
        Vector3 e2 = v3.sub(v1);

        Vector3 h = dir.cross(e2);
        double a = e1.dot(h);

        if (Math.abs(a) < 1e-5) return null;

        double f = 1 / a;

        Vector3 s = pos.sub(v1);
        double u = f * s.dot(h);

        if (u < 0 || u > 1) return null;

        Vector3 q = s.cross(e1);
        double v = f * dir.dot(q);

        if (v < 0 || u + v > 1) return null;

        double distance = f * e2.dot(q);
        Vector3 position = pos.add(dir.mult(distance));

        return new Intersection(ray, position, normal, distance, getMaterial());
    }

    @Override
    protected void transform()
    {
        // Transform vertices
        v1 = getTransform().mult(base1);
        v2 = getTransform().mult(base2);
        v3 = getTransform().mult(base3);

        // Transform normal
        normal = getNormalTransform().mult(baseNormal).normalize();
    }

}
