package obligatorisk.oppgave;

import java.awt.Shape;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class GUIFrame extends BorderPane {

    final int BREDDE = 1200;
    final int HØYDE = 800;

    Pane pane;
    GUI_Figure tempFig; // For tiden tegnet figur
    Shape tempShape;

    // Brukes til figur valg til switch
    final int INGENTING = 0;
    final int FIGUR_LINJE = 1;
    final int FIGUR_REKTANGEL = 2;
    final int FIGUR_SIRKEL = 3;
    final int FIGUR_POLYGON = 4;

    // Lager radioknapper og Checkboks
    CheckBox chckBoksRødLinje;
    CheckBox chckBoksGrønnLinje;
    CheckBox chckBoksBlåLinje;

    CheckBox chckBoksRødFyll;
    CheckBox chckBoksGrønnFyll;
    CheckBox chckBoksBlåFyll;

    RadioButton rbLinje;
    RadioButton rbRektangel;
    RadioButton rbSirkel;
    RadioButton rbPolygon;

    HBox linjeFargeBoks;
    HBox fylleBoks;
    HBox bunnBox;
    VBox vTopBoks; //  top-Venstre
    VBox hTopBoks; // top-høyre
    VBox vBox; // venstre
    VBox hBox; // Høyre

    ToggleGroup figurGruppe = new ToggleGroup();

    Button clrButton;

    // Liste for å beholde figurer
    ArrayList<Figure> figurList;
    ListView<String> listView;
    ArrayList <GUI_Figure> guiFigur;
    ObservableList<String> guiListFigur;
    private boolean drawOn;
    int valgtFigurIndeks;
    int nåværendeType;
    
    int currentType; // Figuren typen som blir selektert via radio knappen
    GUIFrame() {

        this.setPrefSize(BREDDE, HØYDE); // Resizer vinduet til scenen

        // Her ønsker vi å lage kontroll struktur og fylle på panelene 
        // Oppretter top posisjon til panelen
        HBox topBox = new HBox(); // Top skal være organisert horisontalt
        topBox.setAlignment(Pos.CENTER); // Elementene skal være centrert 
        topBox.setFillHeight(false); // Kun senter panel er lovlig til å fylle
        topBox.setPrefSize(300, 50);
        this.setTop(topBox); // setter top til Pane panelen

        // Her ønsker vi å dele opp HBox til to Vbokser 
        // 1) For å fylle linje - Venstre plassering
        vTopBoks = new VBox();
        vTopBoks.setAlignment(Pos.CENTER);
        vTopBoks.setPrefSize(300, 50);
        topBox.getChildren().add(vTopBoks);

        // 2) for å fylle figuren - Høyre Plassering
        hTopBoks = new VBox();
        hTopBoks.setAlignment(Pos.CENTER);
        hTopBoks.setPrefSize(300, 50);
        topBox.getChildren().add(hTopBoks);

        // Oppretter Venstre posisjon for venstre panelet
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        this.setLeft(vBox);

        // Oppretter høyre posisjon for høyre panelet
        hBox = new VBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        this.setRight(hBox);

          

        // Oppretter Bunn posisjonen for bunn panelet
        bunnBox = new HBox();
        bunnBox.setAlignment(Pos.CENTER);
        bunnBox.setFillHeight(false); // Kun senter panel er lov til å settes
        bunnBox.setPrefSize(100, 100);
        this.setBottom(bunnBox);

        // Action Event Handler for Checkboksene
        EventHandler<ActionEvent> dekorasjonsHandler = (ActionEvent e) -> {
            forandreDekorasjonHandling();
        };

        EventHandler<ActionEvent> radioHandler = (ActionEvent e) -> {
            handlerRadioFigurKnapp();
        };

        // Kaller opp Metode for linjeFarge Boks fra klassen "Knapper"
        Knapper.linjeFargeBoks(dekorasjonsHandler, vTopBoks,
                linjeFargeBoks, chckBoksRødLinje, chckBoksRødLinje, chckBoksRødLinje);

        // Kaller opp Metoden for linjeFargeBoks fra klassen "Knapper"
        Knapper.fylleFargeBoks(dekorasjonsHandler, hTopBoks,
                fylleBoks, chckBoksRødFyll, chckBoksRødFyll, chckBoksRødFyll);

        Knapper.tips(bunnBox);

        // Kaller opp metode knapp for å fjerne figurene fra listen 
        Knapper.annullerKnapp(bunnBox, clrButton);

        // Kaller opp Metoden for radio knapper til å selektere figurene
        Knapper.figurerRadioKnapper(radioHandler, figurGruppe, vBox, rbLinje, rbRektangel, rbSirkel, rbPolygon);

        // Kaller opp metoden for info figur tittel
        Knapper.infoTittel(hBox);

        // Info Liste
        listView = new ListView();
        listView.setPrefSize(320, 600);
        guiListFigur = FXCollections.observableArrayList(); // Lager for å oppdatere figur listen
        listView.setItems(guiListFigur); // kobler til listView
        listView.setFocusTraversable(false); // Passer på at det er ikke er tastatur tast
        listView.setOnMouseClicked((MouseEvent e) -> { // Her lager vi muse event for selekterte figurene fra listen
       
            if(guiListFigur.size() > 0) { // Hvis vi har noe figurer
                if(listView.getSelectionModel().getSelectedIndex() != valgtFigurIndeks) { // og forskjellen fra noe nåværende selekterte figurer
                    valgtFigurIndeks = listView.getSelectionModel().getSelectedIndex(); // Oppdatere selekterte indeksen
                    handleListSelectionChange(); // Kaller opp klasse metoden for det selekterte figuren som skal forandres
                }
            }           
        });
        
          //senter panel
        pane = new Pane();
        pane.setMinSize(0, 0); // to make sure pane shrinks as much as it can
        pane.setStyle("-fx-border-color: BLACK;"); // Add border via CSS
        // create a clipping rectangle, since Pane don't do it by self.
        final Rectangle outputClip = new Rectangle();
        outputClip.setWidth(pane.getWidth());
        outputClip.setHeight(pane.getHeight());
        // Add it to the pane
        pane.setClip(outputClip);
        // Bind its size to the pane size
        pane.layoutBoundsProperty().addListener((ov, oldValue, newValue)
                ->
                {
                    outputClip.setWidth(newValue.getWidth());
                    outputClip.setHeight(newValue.getHeight());
        }
        );
        
        
        
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.getChildren().add(listView);
        
        // Her adder vi muse Event handler
        // Hver blir kalt opp etter korrespondert etter klasse metoden
       
        pane.setOnMousePressed((MouseEvent e) -> {
            handleMousePress(e);
        });
        
        pane.setOnMouseReleased((MouseEvent e) -> {
            handleMouseRelease(e);
        });
        
        pane.setOnMouseDragged((MouseEvent e) -> {
            handleMouseDragged(e);
        });
        
     

    }

    public void handleMousePress(MouseEvent mouseEvent) {

        // Sørger for at det er kun venstre knappen til musen skal fungere
        if (mouseEvent.getButton() != MouseButton.PRIMARY) {
            return;
        }
        // Hvis vi ikke tegner
        if (!drawOn) {
            // da lager vi en ny figur
            Figure figur = hentSelekterteFigur(mouseEvent.getX(), mouseEvent.getY());
            if (figur != null) { // Hvis det var suksessfull opprettet
                tempFig = new GUI_Figure(figur);
                oppdatereFargeTilstand((GUI_Figure) tempFig); // Setter opp fargene
                drawOn = true; // Starter å tegne
                valgtFigurIndeks = guiListFigur.size(); // setter opp det selekterte indeksen
                refreshNåVærendeShape();
                refreshNåVærendeGUIListe();

            } else { // hvis vi skal tegne
                // Her adder vi ny punkt for nåværende figur i kooridnatet fra muse klikk
                
                tempFig.getFigur().settNestePunkt(mouseEvent.getX(), mouseEvent.getY());
                refreshNåVærendeShape();
                refreshNåVærendeGUIListe();
            }
        }

    }

    public Figure hentSelekterteFigur(double originalX, double originalY) {

        switch (nåværendeType) {
            case FIGUR_LINJE:
                return new LinjeFigur(originalX, originalY, originalX, originalY);
            case FIGUR_REKTANGEL:
                return new Rektangel(originalX, originalY, originalX, originalY);
            case FIGUR_SIRKEL:
                return new Sirkel(originalX, originalY, originalX, originalY);
            // case FIGUR_Polygon: return new Polygon();
        }
        // Vanligvis, så skal ikke dette skje i normalt situasjon, fordi brukeren kan ikke deselektere 
        // Alle knappene, but for å gjøre det enkelt å forstå, så returnerer det null bare.
        return null;
    }

    // Oppdatere gitt av figur farge tilstand i hensyn etter checkbox.
    public void oppdatereFargeTilstand(GUI_Figure figur) {
        boolean[] nyfargeTilstand = new boolean[6];
        nyfargeTilstand[0] = chckBoksRødFyll.isSelected();
        nyfargeTilstand[1] = chckBoksGrønnFyll.isSelected();
        nyfargeTilstand[2] = chckBoksBlåFyll.isSelected();
        nyfargeTilstand[3] = chckBoksRødLinje.isSelected();
        nyfargeTilstand[4] = chckBoksGrønnLinje.isSelected();
        nyfargeTilstand[5] = chckBoksBlåLinje.isSelected();

        figur.setFargeTilstand(nyfargeTilstand);
    }

    private void handleListSelectionChange() {
         // Hvis vi er i tegne modus
        if(drawOn) {
            tvingFigurTegningen(); // Slutter med tegne modus
        } 
        
        // Oppdaterer check box tilstanden git av figuren:
        boolean fargeTilstand[] = guiFigur.get(valgtFigurIndeks).getFargeTilstand(); 
        
        chckBoksRødFyll.setSelected(fargeTilstand[0]);
        chckBoksGrønnFyll.setSelected(fargeTilstand[1]);
        chckBoksBlåFyll.setSelected(fargeTilstand[2]);
        
        chckBoksRødLinje.setSelected(fargeTilstand[3]);
        chckBoksGrønnLinje.setSelected(fargeTilstand[4]);
        chckBoksBlåLinje.setSelected(fargeTilstand[5]);
    }
    
    // Prøver å bryte ned tegne prosessen
    public void tvingFigurTegningen() {
        if(drawOn) { // Hvis kalt opp under tegne prosessen
            tempFig.getFigur().ferdigFigur(); // Tvinger å nåværende figur til å tegne selv
            refreshNåVærendeShape(); // og deretter tegner på nytt figuren
            drawOn = false; // Slutt med tegne prosessen
            guiFigur.add(tempFig);
            tempFig = null;
        }
    }
    
    // Skal tegnes på nytt når figuren har blitt valgt shapen fra skjermen
    public void refreshNåVærendeShape() {
        if(valgtFigurIndeks >= 0) { // Hvis det er noen figurer
            if(valgtFigurIndeks == figurList.size()) { // når det er midlertidig selektert på listen
                if(valgtFigurIndeks >= pane.getChildren().size()) { // Hvis det har ikke blitt tegnet enda
                    pane.getChildren().add(tempFig.getDecorasjonShape());
                } else {
                    pane.getChildren().set(valgtFigurIndeks, tempFig.getDecorasjonShape()); // Vi setter shapen på skjermen
                }
            } else {
                // eller så oppdatere vi figuren bare.
                pane.getChildren().set(valgtFigurIndeks, guiFigur.get(valgtFigurIndeks).getDecorasjonShape()); 
            }
        }
    }
    
    // Brukes for å oppdatere nåværende figur GUI listen
    public void refreshNåVærendeGUIListe() {
        if(valgtFigurIndeks >= 0) { // Hvis det er noe figurer
            if(valgtFigurIndeks == guiListFigur.size()) { // og midlertidig er selektert 
                String desc = getShapeBeskrivelse(tempFig.getFigur()); // Henter beskrivelsen til shape figuren
                if(valgtFigurIndeks >= guiListFigur.size()) { // og beskrivelsen for figuren når det er ikke tilsettet
                    guiListFigur.add(desc);
                } else {
                    guiListFigur.set(valgtFigurIndeks, desc); //vi setter figurene inn
                }
            } else {
                // Ellers, så oppdaterer vi beskrivelsen for det selekterte figuren fra beskrivelse listen
                String desc = getShapeBeskrivelse(guiFigur.get(valgtFigurIndeks).getFigur()); // eller oppdaterer den
                guiListFigur.set(valgtFigurIndeks, desc);
            }
        }
        
        listView.scrollTo(valgtFigurIndeks); // skrolle for å oppdatere valgt liste
        listView.getSelectionModel().select(valgtFigurIndeks); // her selekteres det fra listen
        listView.getFocusModel().focus(valgtFigurIndeks); // deretter fokuserer vi på det selekterte
    }

    // Lager beskrivelsene til navn iguren til listen
    public String getShapeBeskrivelse(Figure figur) {
        String resultat = figur.getNavnFigur() + ": ";
        double lengden = figur.getTotalSideLengde();
        double areal = figur.getAreal();
        DecimalFormat df = new DecimalFormat("0.0#"); //Setter formatene til double verdi:
        
        if(lengden >= 0 ) { // Hvis lengden eksisterer for figuren
            resultat += "Lengden = " + df.format(lengden) + " px."; // Adder det på stringen
        }
        
        if(lengden >= 0 && areal >= 0) { // Hvis både lengden og arealet eksisterer
            resultat += ", "; // Lager sepererasjon hvis båden lengden og arealet eksisterer
        }
        
        if(areal >= 0) { // Hvis arealet eksisterer og setter det til Stringen
            resultat += "Arealet = " + df.format(lengden) + " px^2";
        }
        return resultat;
        
    }
    
    // Checkbox tilstand som skal forandres
    public void forandreDekorasjonHandling() {
        if(valgtFigurIndeks >= 0) { // Hvis det er noe figurer
            if(valgtFigurIndeks == guiFigur.size()) { // og det blir midlertidig selektert
                oppdatereFargeTilstand(tempFig); // Oppdatere farge tilstanden til det figuret
            } else {
                oppdatereFargeTilstand(guiFigur.get(valgtFigurIndeks)); // ellers oppdatere farge figuren for en fra listen
            }
            refreshNåVærendeShape(); // og oppdatere tegningen på skjermen
        }
    }

    public void handleMouseRelease(MouseEvent mouseEvent) {
       
         // Sørger for at det er kunn venstre knapp på musen
        if (mouseEvent.getButton() != MouseButton.PRIMARY) {
            return;
        }

        if (drawOn) // Særger for at det skal være i tegnemodus hvis det skjer 
        {
            tempFig.getFigur().punktFullført();// Sørger at figuren sitt flytte punktet er ferdig
            refreshNåVærendeShape();  // Tegner på nytt av shapen figuren etter det 
            refreshNåVærendeGUIListe(); // Deretter skal det oppdatere si GUI listen
            if (tempFig.getFigur().erFigurenFerdig()) // Hvis figuren er ferdig da
            {
                drawOn = false; // Slutter tegne modusen
                guiFigur.add(tempFig); // Flytter fituren til arrayListen 
                tempFig = null;
            }
        }
    }

    public void handleMouseDragged(MouseEvent mouseEvent) {
        
        // Her vil vi at det er kun venstre muse klikk som skal reagere.
        // Systemet er designet slikt at det gjelder kun den siste muse klikk
        // Dette betyr at vi vil at det skal at tilstanden skal gjelde for muse klikk til venstre knapp
        
        if(!mouseEvent.isPrimaryButtonDown()) {
            return;
        }
        
        if(drawOn) { 
            // Flytter til den siste punkt lokasjon av nåvæernde figuren til det nye koordinasjonen
            tempFig.getFigur().flyttSistePunkt(mouseEvent.getX(), mouseEvent.getY());
            refreshNåVærendeShape(); // Tegner shapen på nytt 
            refreshNåVærendeGUIListe(); // og oppdatere det på GUI Listen av ArrayListen
        }
    }

    public void handlerRadioFigurKnapp() {
        if(rbLinje.isSelected()) { // Hvis denne knappen blir valgt
            if(currentType != FIGUR_LINJE) { // Oppdatere to denne nye figur linje typen
                currentType = FIGUR_LINJE; // og deretter bryte ned tegne modusen
                tvingFigurTegningen();
            }
        } else if (rbRektangel.isSelected()) { // Samme oppsett for de andre radio knappene
            if(currentType != FIGUR_REKTANGEL) {
                currentType = FIGUR_REKTANGEL;
                tvingFigurTegningen();
            }
        } else if(rbSirkel.isSelected()) {
            if(currentType != FIGUR_REKTANGEL) {
                currentType = FIGUR_REKTANGEL;
                tvingFigurTegningen();
            } 
        } else if (rbPolygon.isSelected()) {
            if(currentType != FIGUR_POLYGON) {
                currentType = FIGUR_POLYGON;
                tvingFigurTegningen();
            }
        } else { 
            // Bare for sikkerhet at hvis brukeren velger å deselektere alle radio knappene
            currentType = INGENTING;
        }
    }
    
    

}
