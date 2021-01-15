package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.object.Intersection;
import ray_tracer.renderer.Scene;

public class Mix extends Material
{

    private Material material1;
    private Material material2;

    private double factor;


    public Mix(Material material1, Material material2, double factor)
    {
        super(null);

        this.material1 = material1;
        this.material2 = material2;

        this.factor = factor;
    }


    public Material getMaterial1()
    {
        return material1;
    }

    public void setMaterial1(Material material1)
    {
        this.material1 = material1;
    }

    public Material getMaterial2()
    {
        return material2;
    }

    public void setMaterial2(Material material2)
    {
        this.material2 = material2;
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
        Color color1 = material1.shader(scene, intersection);
        Color color2 = material2.shader(scene, intersection);

        return color1.mult(factor).add(color2.mult(1 - factor));
    }

    @Override
    protected Color indirect(Scene scene, Intersection intersection)
    {
        return null;
    }

}
