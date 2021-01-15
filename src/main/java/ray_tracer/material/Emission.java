package ray_tracer.material;

import ray_tracer.math.Color;
import ray_tracer.object.Intersection;
import ray_tracer.renderer.Scene;

public class Emission extends Material
{

    private double strength;


    public Emission(Color color, double strength)
    {
        super(color);
        this.strength = strength;
    }

    public Emission(Color color)
    {
        this(color, 1);
    }


    public double getStrength()
    {
        return strength;
    }

    public void setStrength(double strength)
    {
        this.strength = strength;
    }


    @Override
    public Color shader(Scene scene, Intersection intersection)
    {
        return getColor().mult(strength);
    }

    @Override
    protected Color indirect(Scene scene, Intersection intersection)
    {
        return null;
    }

}
