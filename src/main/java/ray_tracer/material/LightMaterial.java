package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Intersection;
import ray_tracer.object.light.Light;
import ray_tracer.renderer.Scene;

public abstract class LightMaterial extends Material
{

    protected LightMaterial(Color color)
    {
        super(color);
    }


    @Override
    public Color shader(Scene scene, Intersection intersection)
    {
        Color direct = direct(scene, intersection);
        Color indirect = indirect(scene, intersection);

        return getColor().mult(direct.add(indirect));
    }

    private Color direct(Scene scene, Intersection intersection)
    {
        // Sum direct lighting
        Color direct = Color.BLACK;
        for (Light light : scene.getLights())
        {
            direct = direct.add(light.shader(scene, intersection, this));
        }

        return direct;
    }

    public abstract double light(Intersection intersection, Vector3 light);

}
