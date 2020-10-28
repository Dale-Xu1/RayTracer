package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.object.Intersection;
import ray_tracer.renderer.Scene;

public abstract class Material
{

    private Color color;


    protected Material(Color color)
    {
        this.color = color;
    }


    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }


    public abstract Color shader(Scene scene, Intersection intersection);

}
