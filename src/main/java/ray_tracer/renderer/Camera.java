package ray_tracer.renderer;

import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Ray;
import ray_tracer.object.Transformation;

public class Camera extends Transformation
{

    private static final Vector3 ORIGIN = new Vector3(0, 0, 0);


    private final int width;
    private final int height;

    private final double fov;

    private Vector3 position = ORIGIN;


    public Camera(int width, int height, double fov)
    {
        this.width = width;
        this.height = height;
        this.fov = fov;
    }

    public Camera(int width, int height)
    {
        this(width, height, 90);
    }


    public byte[] render(Scene scene)
    {
        byte[] pixels = new byte[width * height * 3];

        // Calculate scales
        double scale = Math.tan((fov * 0.5) * Math.PI / 180);
        double aspectRatio = (double) width / height;

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                // Convert pixel to world space
                double x = (2 * (i + 0.5) / width - 1) * aspectRatio * scale;
                double y = (1 - 2 * (j + 0.5) / height) * scale;

                // Calculate direction
                Vector3 direction = getNormalTransform().mult(new Vector3(x, y, -1)).normalize();
                Ray ray = new Ray(position, direction);

                // Trace ray
                Color color = scene.traceRay(ray);
                int index = (j * width * 3) + (i * 3);

                pixels[index] = (byte) Math.min(color.r * 255, 255);
                pixels[index + 1] = (byte) Math.min(color.g * 255, 255);
                pixels[index + 2] = (byte) Math.min(color.b * 255, 255);
            }
        }

        return pixels;
    }


    @Override
    protected void transform()
    {
        position = getTransform().mult(ORIGIN);
    }

}
