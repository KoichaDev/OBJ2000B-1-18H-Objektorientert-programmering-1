package communication.between.windows;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class App extends Application {
    
    Stage window;
    Button button;
    
    
    @Override
    public void start(Stage primaryStage) {
      window = primaryStage;
      window.setTitle("The New Boston");
      
      button = new Button("Click Me!");
      
      button.setOnAction(e -> {
         boolean result = ConfirmBox.display("Title of Window", "Are you Sure you want to send naked pics?");
         System.out.println(result);
      });
      
      StackPane layout = new StackPane();
      layout.getChildren().add(button);
      Scene scene = new Scene(layout, 300, 250);
      window.setScene(scene);
      window.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
