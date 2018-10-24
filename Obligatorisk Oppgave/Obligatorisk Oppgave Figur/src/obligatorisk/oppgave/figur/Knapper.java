package obligatorisk.oppgave.figur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

public class Knapper extends StackPane {

    StackPane pane;

    public static HBox radioButton(BorderPane border) {

        HBox hBox = new HBox(20);
        ToggleGroup group = new ToggleGroup();
        Region center = new Region();

        hBox.setPadding(new Insets(10, 10, 10, 10));

        // Opprette RadioKnapper og sette Toggle Group
        RadioButton rettRadio = new RadioButton("Rett Linje");
        rettRadio.setToggleGroup(group);
        RadioButton rekRadio = new RadioButton("Rektangel");
        rekRadio.setToggleGroup(group);
        RadioButton sirkelRadio = new RadioButton("Sirkel");
        sirkelRadio.setToggleGroup(group);
        RadioButton polyRadio = new RadioButton("Polygon");
        polyRadio.setToggleGroup(group);

        hBox.getChildren().addAll(rettRadio, rekRadio, sirkelRadio, polyRadio);
        hBox.setAlignment(Pos.TOP_CENTER);

        Text txt = new Text("Trykk på Radioboks");
        txt.setStyle("-fx-font: 24 arial;");
        border.setCenter(txt);
        border.setStyle("-fx-background-color: #f2f3f4");

        rettRadio.setOnAction(e -> {
            
            setBackground(center, Color.valueOf("#e3e6ea"));
            border.setCenter(center);
           
            
        });

        //rettBtn.setOnAction(e -> RettLinje.displayRettLinje(root, ));
        Text hovedLabel = new Text("Obligatorisk Oppgave");

        hovedLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        //paneForButtons.setAlignment(Pos.CENTER);
        //paneForButtons.setAlignment(hovedLabel, Pos.TOP_LEFT);
        //pane.setTop(hovedLabel);

        return hBox;
    }

    public static VBox getChckLinjeFargeBoks() {

        VBox paneBoks = new VBox(20);
        Color farge;
        ToggleGroup group = new ToggleGroup();

        CheckBox rødBoks = new CheckBox("Rød");
        CheckBox grønnBoks = new CheckBox("Grønn");
        CheckBox blåBoks = new CheckBox("Blå");

        paneBoks.getChildren().add(new Text("Fylle Linje:"));
        paneBoks.getChildren().addAll(rødBoks, grønnBoks, blåBoks);
        paneBoks.setAlignment(Pos.CENTER_RIGHT);

        paneBoks.setOnMouseClicked(e -> {
            if (rødBoks.isSelected()) {
                // ToDo Rød farge strek senere

            } else if (grønnBoks.isSelected()) {
                // ToDo Grønn farge strek senere
            } else if (blåBoks.isSelected()) {
                // ToDo Blå farge strek senere
            }
        });
        return paneBoks;
    }

    public static VBox getFylleFarge() {
        VBox paneBoks = new VBox(20);
        CheckBox rødBoks = new CheckBox("Rød");
        CheckBox grønnBoks = new CheckBox("Grønn");
        CheckBox blåBoks = new CheckBox("Blå");

        paneBoks.getChildren().add(new Text("Fylle Farge:"));

        paneBoks.getChildren().addAll(rødBoks, grønnBoks, blåBoks);
        paneBoks.setAlignment(Pos.CENTER_LEFT);

        paneBoks.setOnMouseClicked(e -> {
            if (rødBoks.isSelected()) {
                // ToDo Rød farge strek senere

            } else if (grønnBoks.isSelected()) {
                // ToDo Grønn farge strek senere
            } else if (blåBoks.isSelected()) {
                // ToDo Blå farge strek senere
            }
        });
        return paneBoks;

    }

    private static void setBackground(Region region, Color color) {
        region.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
