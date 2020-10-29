package ray_tracer;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import ray_tracer.material.Emission;
import ray_tracer.material.Glossy;
import ray_tracer.math.Color;
import ray_tracer.math.Matrix4;
import ray_tracer.math.Vector3;
import ray_tracer.object.geometry.Mesh;
import ray_tracer.object.geometry.Sphere;
import ray_tracer.object.geometry.Triangle;
import ray_tracer.renderer.Camera;
import ray_tracer.renderer.Scene;

import java.nio.ByteBuffer;

public class RayTracer extends Parent
{

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;


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
        Color gray = new Color(.85, .85, .85);

        // Create scene
        Emission background = new Emission(gray);
        Scene scene = new Scene(background);

        scene.add(new Triangle(
            new Emission(Color.WHITE, 3),
            new Vector3(-1, 0, -1),
            new Vector3(1, 0, -1),
            new Vector3(1, 0, 1)
        ));

        scene.add(new Mesh(
            new Triangle(
                new Emission(gray, 0.8),
                new Vector3(-.7, .1, -1.3),
                new Vector3(1.3, .1, .7),
                new Vector3(-.7, .1, .7)
            ),
            new Triangle(
                new Emission(Color.ORANGE),
                new Vector3(0, 0, 0),
                new Vector3(1, 1, 0),
                new Vector3(1, 0, 0)
            )
        ));

        scene.add(new Triangle(
            new Glossy(gray),
            new Vector3(.7, 0, .1),
            new Vector3(-.3, 0, -.2),
            new Vector3(-.3, 1, -.2)
        ));

        Sphere sphere = new Sphere(new Glossy(new Color(.7, .8, 1)), 0.5);
        sphere.translate(new Vector3(-.6, .1, -.5));
        scene.add(sphere);

        Camera camera = new Camera(WIDTH, HEIGHT, 70);
        camera.setTransform(Matrix4.lookAt(new Vector3(1, 1.3, -1.3), Vector3.ZERO));

        // Render scene
        byte[] pixels = camera.render(scene);

        // Draw image
        PixelFormat<ByteBuffer> format = PixelFormat.getByteRgbInstance();
        writer.setPixels(0, 0, WIDTH, HEIGHT, format, pixels, 0, WIDTH * 3);
    }

}
