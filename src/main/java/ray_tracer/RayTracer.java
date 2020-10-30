package ray_tracer;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import ray_tracer.material.Diffuse;
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
        new Thread(this::render).start();
    }


    private void render() // TODO: Refraction and fresnel
    {
        Color gray = new Color(0.85, 0.85, 0.85);

        // Create scene
        Emission background = new Emission(gray);
        Scene scene = new Scene(background, 20, 0, 4);

        scene.addObject(new Triangle(
            new Emission(Color.WHITE, 2),
            new Vector3(-1, 0, -1),
            new Vector3(1, 0, -1),
            new Vector3(1, 0, 1)
        ));

        scene.addObject(new Mesh(
            new Triangle(
                new Diffuse(gray),
                new Vector3(-0.7, 0.1, -1.3),
                new Vector3(1.3, 0.1, 0.7),
                new Vector3(-0.7, 0.1, 0.7)
            ),
            new Triangle(
                new Diffuse(new Color(1, 0.35, 0.35)),
                new Vector3(0, 0, 0),
                new Vector3(1, 1, 0),
                new Vector3(1, 0, 0)
            )
        ));

        scene.addObject(new Triangle(
            new Glossy(gray, 0),
            new Vector3(0.7, 0, 0.1),
            new Vector3(-0.3, 0, -0.2),
            new Vector3(-0.3, 1, -0.2)
        ));

        Sphere sphere = new Sphere(new Glossy(new Color(0.7, 0.8, 1)), 0.5);
        sphere.translate(new Vector3(-0.6, 0.1, -0.5));
        scene.addObject(sphere);

        Camera camera = new Camera(WIDTH, HEIGHT, 70);
        camera.setTransform(Matrix4.lookAt(new Vector3(1, 1.3, -1.3), Vector3.ZERO));

        try
        {
            // Render scene
            byte[] pixels = camera.render(scene);

            // Draw image
            PixelFormat<ByteBuffer> format = PixelFormat.getByteRgbInstance();
            writer.setPixels(0, 0, WIDTH, HEIGHT, format, pixels, 0, WIDTH * 3);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
