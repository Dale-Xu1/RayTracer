package ray_tracer;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import ray_tracer.material.Diffuse;
import ray_tracer.material.Emission;
import ray_tracer.material.Glossy;
import ray_tracer.material.Mix;
import ray_tracer.math.Color;
import ray_tracer.math.Matrix4;
import ray_tracer.math.Vector3;
import ray_tracer.object.geometry.Geometry;
import ray_tracer.object.geometry.Mesh;
import ray_tracer.object.geometry.Sphere;
import ray_tracer.object.geometry.Triangle;
import ray_tracer.object.light.DirectionalLight;
import ray_tracer.object.light.Light;
import ray_tracer.renderer.Camera;
import ray_tracer.renderer.Scene;

import java.util.List;

public class RayTracer extends Parent
{

    private static final int WIDTH = 720;
    private static final int HEIGHT = 480;


    private final PixelWriter writer;


    public RayTracer()
    {
        // Create canvas
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        getChildren().add(canvas);

        writer = canvas.getGraphicsContext2D().getPixelWriter();
    }


    public void render() // TODO: Refraction and fresnel
    {
        // Create scene
        Emission background = new Emission(new Color(0.75, 0.75, 0.75));
        Scene scene = new Scene(background, 16, 4);

        List<Geometry> objects = scene.getObjects();
        List<Light> lights = scene.getLights();

        objects.add(new Triangle(
            new Emission(Color.WHITE, 2),
            new Vector3(-1, 0, -1),
            new Vector3(1, 0, -1),
            new Vector3(1, 0, 1)
        ));

        objects.add(new Mesh(
            new Triangle(
                new Diffuse(new Color(0.7, 0.7, 0.7)),
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

        objects.add(new Triangle(
            new Glossy(new Color(0.85, 0.85, 0.85), 0),
            new Vector3(0.7, 0, 0.1),
            new Vector3(-0.3, 0, -0.2),
            new Vector3(-0.3, 1, -0.2)
        ));

        Sphere sphere = new Sphere(
            new Mix(
                new Diffuse(new Color(0.7, 0.8, 1)),
                new Glossy(new Color(0.85, 0.85, 0.85), 0.1),
                0.1
            ),
            0.5
        );
        sphere.translate(new Vector3(-0.6, 0.1, -0.5));
        objects.add(sphere);

        DirectionalLight light1 = new DirectionalLight(new Emission(Color.WHITE, 0.5));
        lights.add(light1);
        light1.rotateX(0.5);

        DirectionalLight light2 = new DirectionalLight(new Emission(Color.WHITE, 0.5), 0.1);
        lights.add(light2);
        light2.rotateZ(0.5);

        Camera camera = new Camera(writer, WIDTH, HEIGHT);
        camera.setTransform(Matrix4.lookAt(new Vector3(1.1, 1.7, -1.7), Vector3.ZERO));

        // Render scene
        camera.render(scene);
    }

}
