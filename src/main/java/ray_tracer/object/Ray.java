package ray_tracer.object;

import ray_tracer.math.Vector3;

public class Ray
{

    private final Vector3 position;
    private final Vector3 direction;

    private final int depth;


    public Ray(Vector3 position, Vector3 direction, int depth)
    {
        this.position = position;
        this.direction = direction;
        this.depth = depth;
    }

    public Ray(Vector3 position, Vector3 direction)
    {
        this(position, direction, 0);
    }


    public Vector3 getPosition()
    {
        return position;
    }

    public Vector3 getDirection()
    {
        return direction;
    }

    public int getDepth()
    {
        return depth;
    }

}
