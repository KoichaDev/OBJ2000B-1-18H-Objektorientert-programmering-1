package obligatorisk.oppgave;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Khoi Nguyen Hoang: 162749
 */
public class Knapper {

    // Metode for Linje Farge CheckBoks
    public static HBox linjeFargeBoks(EventHandler<ActionEvent> dekorasjonsHandler, VBox vTopBoks, HBox linjeFargeBoks,
            CheckBox red, CheckBox green, CheckBox blue) {

        Label linjeLbl = new Label("Linje Farge");
        vTopBoks.getChildren().add(linjeLbl);

        linjeFargeBoks = new HBox();
        linjeFargeBoks.setAlignment(Pos.CENTER);
        linjeFargeBoks.setPrefSize(300, 50);
        vTopBoks.getChildren().add(linjeFargeBoks);

        red = new CheckBox();
        red.setText("Rød");
        red.setPadding(new Insets(10, 10, 10, 10));
        red.setOnAction(dekorasjonsHandler); // EventHandler 
        linjeFargeBoks.getChildren().add(red);

        green = new CheckBox();
        green.setText("Grønn");
        green.setPadding(new Insets(10, 10, 10, 10));
        green.setOnAction(dekorasjonsHandler); //EventHandler
        linjeFargeBoks.getChildren().add(green);

        blue = new CheckBox();
        blue.setText("Blå");
        blue.setPadding(new Insets(10, 10, 10, 10)); // Gir litt "luft" mellom boksene
        blue.setOnAction(dekorasjonsHandler); // EventHandler 
        linjeFargeBoks.getChildren().add(blue);
        return linjeFargeBoks;
    }

    // Metode for Fylle Farge CheckBoks
    public static HBox fylleFargeBoks(EventHandler<ActionEvent> dekorasjonsHandler, VBox hTopBoks, HBox fylleBoks,
            CheckBox red, CheckBox green, CheckBox blue) {
        Label fylleLbl = new Label("Fylle Figur Farge");
        hTopBoks.setAlignment(Pos.CENTER);
        hTopBoks.setPrefSize(300, 50); // Gir litt "luft" mellom boksene
        hTopBoks.getChildren().add(fylleLbl);

        fylleBoks = new HBox();
        fylleBoks.setAlignment(Pos.CENTER);
        fylleBoks.setPrefSize(300, 50);
        hTopBoks.getChildren().add(fylleBoks);

        red = new CheckBox();
        red.setText("Rød");
        red.setPadding(new Insets(10, 10, 10, 10)); // Gir litt "luft" mellom boksene
        red.setOnAction(dekorasjonsHandler); // EventHandler
        fylleBoks.getChildren().add(red);

        green = new CheckBox();
        green.setText("Grønn");
        green.setPadding(new Insets(10, 10, 10, 10)); // Gir litt "luft" mellom boksene
        green.setOnAction(dekorasjonsHandler); // EventHandler
        fylleBoks.getChildren().add(green);

        blue = new CheckBox();
        blue.setText("Blå");
        blue.setPadding(new Insets(10, 10, 10, 10)); // Gir litt "luft" mellom boksene
        blue.setOnAction(dekorasjonsHandler); // EventHandler
        fylleBoks.getChildren().add(blue);
        return fylleBoks;
    }

    // Metode for Tips til javaFx!
    public static void tips(HBox bunnBox) {
        Label lblTips = new Label("Tips: "
                + "\n - Velg figuren fra høyre siden av menyet etter at det har blitt tegnet "
                + "\n - Museklikk: Venstre Klikk for å tegne "
                + "\n - Arrow Keys: Trykk tastaturet for å flytte figurene");
        bunnBox.setMargin(lblTips, new Insets(0, 75, 0, 0)); // Vi forsyver Label og knappen mellom
        bunnBox.setAlignment(Pos.CENTER);
        bunnBox.getChildren().add(lblTips);
    }

    //Metode for å fjerne Knappen
    public static Button annullerKnapp(HBox bunnBox, Button clrBtn) {

        clrBtn = new Button();
        clrBtn.setText("Annuller Tegningene");

        clrBtn.setOnAction(e -> {
            // handlerClrBtn(); 
        });
        bunnBox.getChildren().add(clrBtn);
        return clrBtn;
    }

    public static void figurerRadioKnapper(EventHandler<ActionEvent> radioHandler,ToggleGroup figurGruppe, VBox vBox, 
                                                  RadioButton linje, RadioButton rektangel, 
                                                  RadioButton sirkel, RadioButton polygon) {
        figurGruppe = new ToggleGroup();

        // Det opprettes fire radio knapper for figurene
        linje = new RadioButton();
        linje.setText("Rett Linje");
        linje.setToggleGroup(figurGruppe);
        linje.setOnAction(radioHandler);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().add(linje);

        rektangel = new RadioButton();
        rektangel.setText("Rektangel");
        rektangel.setToggleGroup(figurGruppe);
        rektangel.setOnAction(radioHandler);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().add(rektangel);

        sirkel = new RadioButton();
        sirkel.setText("Sirkel");
        sirkel.setToggleGroup(figurGruppe);
        sirkel.setOnAction(radioHandler);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().add(sirkel);

        polygon = new RadioButton();
        polygon.setText("Polygon");
        polygon.setToggleGroup(figurGruppe);
        polygon.setOnAction(radioHandler);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().add(polygon);
    }
    
    public static void infoTittel(VBox hBox) {
        Label lblTittel = new Label("Info Figurer:"); 
        lblTittel.setStyle("-fx-font-size: 20pt ;"); // Font størrelse endring via CSS
        hBox.setPadding(new Insets(0, 20, 0, 20)); 
        hBox.getChildren().add(lblTittel); // Setter inn i VBox
    }
}
