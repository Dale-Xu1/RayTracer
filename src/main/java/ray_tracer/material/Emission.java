package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.object.Intersection;
import ray_tracer.renderer.Scene;

public class Emission extends Material
{

    public Emission(Color color, double strength)
    {
        super(color.mult(strength));
    }

    public Emission(Color color)
    {
        super(color);
    }


    @Override
    public Color shader(Scene scene, Intersection intersection)
    {
        return getColor();
    }

}
