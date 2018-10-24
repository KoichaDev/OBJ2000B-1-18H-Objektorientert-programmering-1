package ObligtoriskOppgave;

import java.awt.Desktop.Action;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import java.util.*;
import javafx.event.ActionEvent;

import javafx.stage.Stage;
import obligatorisk.oppgave.figur.Knapper;
import obligatorisk.oppgave.figur.RettLinje;

/**
 * @author Khoi Nguyen Hoang - 162749
 */
public class App extends Application {

    Stage window;
    Group root;

    @Override

    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("Obligatorisk Oppgave");

        BorderPane pane = new BorderPane();

        pane.setPadding(new Insets(10, 10, 10, 10));

        // Overskrift 
        HBox radioButtons = Knapper.radioButton(pane);
        pane.setTop(radioButtons);
     

        // Kaller opp CheckBox Metoden for linjeFarge
        VBox checkLinjeFarge = Knapper.getChckLinjeFargeBoks();
        pane.setRight(checkLinjeFarge);

        // Kaller opp CheckBox metoden for fylleFarge
        VBox checkFylleFarge = Knapper.getFylleFarge();
        pane.setLeft(checkFylleFarge);

        Scene scene = new Scene(pane, 800, 800);
        window.setScene(scene);

        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
