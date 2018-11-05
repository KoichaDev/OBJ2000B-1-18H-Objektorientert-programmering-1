package obligatorisk.oppgave;

import java.util.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author Khoi Nguyen Hoang: 162749
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

       GUIFrame guiFrame = new GUIFrame();
        
        
        
        Scene scene = new Scene(guiFrame);
        primaryStage.setTitle("Obligatorisk Oppgave");
        primaryStage.setScene(scene);
        primaryStage.show();
        

    }

    public static void main(String[] args) {
        launch(args);
    }

}
