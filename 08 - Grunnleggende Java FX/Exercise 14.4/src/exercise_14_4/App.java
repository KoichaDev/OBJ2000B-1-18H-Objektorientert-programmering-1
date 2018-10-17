/******************************************************************************************
 * Skriv eit program som viser teksten "JavaFX" i fem utgåver, vertikalt, i ulike farger. *
 * Alle med Times New Roman, feit, kursiv, 24 pt                                          *
*******************************************************************************************/
package exercise_14_4;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // HBox skal sørge for å horisontal rader
        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        
        for(int i = 0; i < 5; i++) {
            // Lage en tekst
            Text text = new Text("Java");
            text.setFont(Font.font("Times New Roman", FontWeight.LIGHT, FontPosture.ITALIC, 30));
            // Sette farger
            text.setFill(new Color(Math.random(), Math.random(), Math.random(), Math.random()));
            hBox.getChildren().add(text);
        }
        
        
        Scene scene = new Scene(hBox);
        primaryStage.setTitle("Exercise 14.4");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

  
    public static void main(String[] args) {
        launch(args);
    }
    
}
