package ray_tracer.renderer;

import ray_tracer.math.Color;
import ray_tracer.object.Ray;

public class Scene
{

    private final int maxDepth;


    public Scene(int maxDepth)
    {
        this.maxDepth = maxDepth;
    }

    public Scene()
    {
        this(4);
    }


    public Color traceRay(Ray ray, int depth)
    {
        // This is black because light can't come from nowhere
        if (depth > maxDepth)
        {
            return Color.BLACK;
        }

        return Color.PURPLE;
    }

    public Color traceRay(Ray ray)
    {
        return traceRay(ray, 0);
    }

}
