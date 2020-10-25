package ray_tracer;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import ray_tracer.renderer.Camera;
import ray_tracer.renderer.Scene;

import java.nio.ByteBuffer;

public class RayTracer extends Parent
{

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;


    private final PixelWriter writer;


    public RayTracer()
    {
        // Create canvas
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        getChildren().add(canvas);

        writer = canvas.getGraphicsContext2D().getPixelWriter();
        render();
    }


    private void render()
    {
        // Create scene
        Scene scene = new Scene();
        Camera camera = new Camera(WIDTH, HEIGHT);

        // Render scene
        byte[] pixels = camera.render(scene);

        // Draw image
        PixelFormat<ByteBuffer> format = PixelFormat.getByteRgbInstance();
        writer.setPixels(0, 0, WIDTH, HEIGHT, format, pixels, 0, WIDTH * 3);
    }

}
