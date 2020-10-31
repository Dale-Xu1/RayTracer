package ray_tracer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage)
    {
        // Create application
        RayTracer rayTracer = new RayTracer();

        // Initialize stage
        stage.setTitle("Ray Tracer");
        stage.setScene(new Scene(rayTracer));
        stage.show();

        rayTracer.render();
    }

}
