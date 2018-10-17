package spørre.skjema;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Label firstName = new Label("First Name:");
        Label lastName = new Label("Last Name: ");
        Label age = new Label("Age: ");
        Label gender = new Label("Gender: ");
        Button submitBtn = new Button("Submit");
        Button resetBtn = new Button("Reset");
                
        GridPane pane = new GridPane();
       
        pane.setPadding(new Insets(10, 10, 10, 10));
        
        // Avstand mellom noder
        pane.setHgap(5); 
        pane.setVgap(5);        
        
        pane.add(firstName, 0, 0);
        pane.add(new TextField(), 1, 0); // Oppretter Anonym klasse inne i parameteren til add
        pane.add(lastName, 0, 1);
        pane.add(new TextField(), 1, 1);
        pane.add(age, 0, 2);
        pane.add(new TextField(), 1, 2);
        pane.add(gender, 0, 3);
        pane.add(new TextField(), 1, 3);
        pane.add(submitBtn, 1, 4);
        pane.add(resetBtn, 1, 4);
        GridPane.setHalignment(submitBtn, HPos.LEFT);
        GridPane.setHalignment(resetBtn, HPos.RIGHT);
        
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Spørre Skjema");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
