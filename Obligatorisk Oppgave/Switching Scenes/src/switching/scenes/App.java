package switching.scenes;

import java.awt.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        Label label = new Label("Welcome to the first Scene");
        Button button1 = new Button("Go to Scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        VBox layout1 = new VBox(20);

        layout1.getChildren().add(0, button1);
        scene1 = new Scene(layout1, 200, 200);

        Button button2 = new Button("This scene sucks, go back to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        StackPane layout2 = new StackPane();
        layout2.getChildren().add(0, button2);
        scene2 = new Scene(layout2, 600, 300);

        window.setScene(scene1);
        window.setTitle("Test");
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
