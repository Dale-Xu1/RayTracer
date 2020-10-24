package ray_tracer.object;

import ray_tracer.math.Vector3;

public class Ray
{

    private final Vector3 position;
    private final Vector3 direction;


    public Ray(Vector3 position, Vector3 direction)
    {
        this.position = position;
        this.direction = direction;
    }


    public Vector3 getPosition()
    {
        return position;
    }

    public Vector3 getDirection()
    {
        return direction;
    }

}
