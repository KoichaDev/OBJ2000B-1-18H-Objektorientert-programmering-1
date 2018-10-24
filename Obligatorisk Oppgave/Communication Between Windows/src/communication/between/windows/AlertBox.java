package communication.between.windows;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

    public static void display(String title, String message) {
        Stage window = new Stage();

        // Block Events to other Windows
        
        Label label = new Label();
        
        Button closeBtn = new Button("Close this window");
        closeBtn.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().add(0, closeBtn);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
