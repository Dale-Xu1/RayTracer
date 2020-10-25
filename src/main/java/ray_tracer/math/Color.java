package ray_tracer.math;

public class Color
{

    public static Color RED     = new Color(1, 0, 0);
    public static Color ORANGE  = new Color(1, 0.5, 0);
    public static Color YELLOW  = new Color(1, 1, 0);
    public static Color GREEN   = new Color(0, 1, 0);
    public static Color AQUA    = new Color(0, 1, 1);
    public static Color BLUE    = new Color(0, 0, 1);
    public static Color PURPLE  = new Color(0.5, 0, 1);
    public static Color MAGENTA = new Color(1, 0, 1);

    public static Color BLACK   = new Color(0, 0, 0);
    public static Color GRAY    = new Color(0.5, 0.5, 0.5);
    public static Color WHITE   = new Color(1, 1, 1);


    public final double r;
    public final double g;
    public final double b;


    public Color(double r, double g, double b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }


    public Color add(Color color)
    {
        return new Color(r + color.r, g + color.g, b + color.b);
    }

    public Color add(double value)
    {
        return new Color(r + value, g + value, b + value);
    }


    public Color sub(Color color)
    {
        return new Color(r - color.r, g - color.g, b - color.b);
    }

    public Color sub(double value)
    {
        return new Color(r - value, g - value, b - value);
    }


    public Color mult(Color color)
    {
        return new Color(r * color.r, g * color.g, b * color.b);
    }

    public Color mult(double value)
    {
        return new Color(r * value, g * value, b * value);
    }


    public Color div(Color color)
    {
        return new Color(r / color.r, g / color.g, b / color.b);
    }

    public Color div(double value)
    {
        return new Color(r / value, g / value, b / value);
    }


    @Override
    public String toString()
    {
        return "[" + (r * 255) + ", " + (g * 255) + ", " + (b * 255) + "]";
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Color color = (Color) object;

        return (color.r == r) && (color.g == g) && (color.b == b);
    }

}
