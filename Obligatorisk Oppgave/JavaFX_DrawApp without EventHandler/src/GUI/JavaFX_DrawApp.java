package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFX_DrawApp extends Application {

    // Application entry point
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    //On application launch
    @Override
    public void start(Stage stage) throws Exception
    {
        // Create a top level pane
        BorderPane topLevelPane = new MainFrame();

        // Add it to scene
        Scene scene = new Scene(topLevelPane);
        //  Set the scene on the given application stage
        stage.setScene(scene);
        // And display it to user
        stage.show();
    }

}
