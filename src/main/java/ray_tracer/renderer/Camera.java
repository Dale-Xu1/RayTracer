package ray_tracer.renderer;

import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import ray_tracer.math.Color;
import ray_tracer.math.Vector3;
import ray_tracer.object.Ray;
import ray_tracer.object.Transformation;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Camera extends Transformation
{

    private final PixelWriter writer;

    private final int width;
    private final int height;

    private final int batch;
    private final double fov;


    public Camera(PixelWriter writer, int width, int height, int batch, double fov)
    {
        this.writer = writer;

        this.width = width;
        this.height = height;

        this.batch = batch;
        this.fov = fov;
    }

    public Camera(PixelWriter writer, int width, int height)
    {
        this(writer, width, height, 16, 50);
    }


    public void render(Scene scene)
    {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < width; i += batch)
        {
            for (int j = 0; j < height; j += batch)
            {
                // Add batch to pool
                int x = i;
                int y = j;

                pool.submit(() -> renderBatch(scene, x, y));
            }
        }
    }

    private void renderBatch(Scene scene, int x, int y) // TODO: Anti-aliasing and depth of field
    {
        // Calculate batch size
        int width = Math.min(this.width - x, batch);
        int height = Math.min(this.height - y, batch);

        // Render pixels
        byte[] pixels = new byte[width * height * 3];

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                // Calculate index in array
                Color color = renderPixel(scene, x + i, y + j);
                int index = (j * width * 3) + (i * 3);

                pixels[index] = (byte) Math.min(color.r * 255, 255); // TODO: HDR?
                pixels[index + 1] = (byte) Math.min(color.g * 255, 255);
                pixels[index + 2] = (byte) Math.min(color.b * 255, 255);
            }
        }

        // Synchronize because PixelWriter can't do multiple things at once
        synchronized (writer)
        {
            // Draw image
            PixelFormat<ByteBuffer> format = PixelFormat.getByteRgbInstance();
            writer.setPixels(x, y, width, height, format, pixels, 0, width * 3);
        }
    }

    private Color renderPixel(Scene scene, int i, int j)
    {
        // Convert pixel to world space
        double scale = Math.tan((fov * 0.5) * Math.PI / 180);
        double aspectRatio = (double) width / height;

        double x = (2 * (i + 0.5) / width - 1) * aspectRatio * scale;
        double y = (1 - 2 * (j + 0.5) / height) * scale;

        // Calculate direction
        Vector3 direction = getNormalTransform().mult(new Vector3(x, y, 1)).normalize();
        Ray ray = new Ray(getPosition(), direction);

        // Trace ray
        return scene.traceRay(ray);
    }

}
