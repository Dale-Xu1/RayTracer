package ray_tracer.math;

public class Vector3
{

    public final double x;
    public final double y;
    public final double z;


    public Vector3(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public Vector3 add(Vector3 vector)
    {
        return new Vector3(x + vector.x, y + vector.y, z + vector.z );
    }

    public Vector3 add(double value)
    {
        return new Vector3(x + value, y + value, z + value);
    }


    public Vector3 sub(Vector3 vector)
    {
        return new Vector3(x - vector.x, y - vector.y, z - vector.z);
    }

    public Vector3 sub(double value)
    {
        return new Vector3(x - value, y - value, z - value);
    }


    public Vector3 mult(Vector3 vector)
    {
        return new Vector3(x * vector.x, y * vector.y, z * vector.z);
    }

    public Vector3 mult(double value)
    {
        return new Vector3(x * value, y * value, z * value);
    }

    public Vector3 negate()
    {
        return new Vector3(-x, -y, -z);
    }


    public Vector3 div(Vector3 vector)
    {
        return new Vector3(x / vector.x, y / vector.y, z / vector.z);
    }

    public Vector3 div(double value)
    {
        return new Vector3(x / value, y / value, z / value);
    }


    public double dot(Vector3 vector)
    {
        return (x * vector.x) + (y * vector.y) + (z * vector.z);
    }

    public Vector3 cross(Vector3 vector)
    {
        return new Vector3 (
            (y * vector.z) - (z * vector.y),
            (z * vector.x) - (x * vector.z),
            (x * vector.y) - (y * vector.x)
        );
    }


    public double magSq()
    {
        return (x * x) + (y * y) + (z * z);
    }

    public double mag()
    {
        return Math.sqrt(magSq());
    }

    public Vector3 normalize()
    {
        double magSq = magSq();
        return (magSq > 0) ? div(Math.sqrt(magSq)) : this;
    }


    @Override
    public String toString()
    {
        return "[" + x + ", " + y + ", " + z + "]";
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Vector3 vector = (Vector3) object;

        return (vector.x == x) && (vector.y == y) && (vector.z == z);
    }

}
