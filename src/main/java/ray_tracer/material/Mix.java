package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.object.Intersection;
import ray_tracer.renderer.Scene;

public class Mix extends Material
{

    private Material a;
    private Material b;

    private double factor;


    public Mix(Material a, Material b, double factor)
    {
        super(null);

        this.a = a;
        this.b = b;

        this.factor = factor;
    }


    public Material getA()
    {
        return a;
    }

    public void setA(Material a)
    {
        this.a = a;
    }

    public Material getB()
    {
        return b;
    }

    public void setB(Material b)
    {
        this.b = b;
    }

    public double getFactor()
    {
        return factor;
    }

    public void setFactor(double factor)
    {
        this.factor = factor;
    }


    @Override
    public Color shader(Scene scene, Intersection intersection)
    {
        Color colorA = a.shader(scene, intersection);
        Color colorB = b.shader(scene, intersection);

        // Linearly interpolate two colors
        return colorA.mult(1 - factor).add(colorB.mult(factor));
    }

    @Override
    protected Color indirect(Scene scene, Intersection intersection)
    {
        return null;
    }

}
