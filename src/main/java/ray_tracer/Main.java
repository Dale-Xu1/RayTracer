package ray_tracer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
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
        // Create root
        Group root = new Group();

        // Create canvas
        Canvas canvas = new Canvas(1280, 720);
        root.getChildren().add(canvas);

        // Initialize stage
        stage.setTitle("Ray Tracer");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
