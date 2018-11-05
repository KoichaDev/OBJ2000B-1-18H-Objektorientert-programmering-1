package GUI;

import Figures.CircleFigure;
import Figures.Figure;
import Figures.LineFigure;
import Figures.PolygonFigure;
import Figures.RectangleFigure;
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

public class MainFrame extends BorderPane {

    // Vindu Størrelsen til Appen
   
    private final static int WIDTH = 1200;
    private final static int HEIGHT = 800;
    
    // Figur Type
    private static final int FIGURE_LINE = 1;
    private static final int FIGURE_RECTANGLE = 2;
    private static final int FIGURE_CIRCLE = 3;
    private static final int FIGURE_POLY = 4;
    private static final int FIGURE_NONE = 0;
    
    // GUI kontroll struktur for at vi skal lagres og bruke av klasse metoder.
    private final CheckBox cbLineRed;
    private final CheckBox cbLineGreen;
    private final CheckBox cbLineBlue;
    private final CheckBox cbFillRed;
    private final CheckBox cbFillGreen;
    private final CheckBox cbFillBlue;
    private final RadioButton rbLine;
    private final RadioButton rbRectangle;
    private final RadioButton rbCircle;
    private final RadioButton rbPoly;
    private final Pane pane;
    private final ListView<String> list;
    
    // Lister der vi ønsker å beholde når vi skal tegne figurene
    private final ArrayList<GUI_Figure> guiFigures;
    private GUI_Figure tempFig; // Figurene som har blitt tegnet
    
    private boolean drawingOn = false; // flag-sjekk for at det blir tegnet
    private final ObservableList<String> guiListItems; // Lister av String for informasjons panelet til list figurene
    private int selectedFigureIndex; // selekterte figurer som vi har valgt

    private int currType; // type av figurer som blir selektert via radio knapper

    MainFrame() {
       
        this.setPrefSize(WIDTH, HEIGHT); // resize hele vinduet
        
        // Top posisjon plassert på toppen av panelet
        HBox topbox = new HBox(); // Top er organisert i horisontalt posisjon
        topbox.setAlignment(Pos.CENTER); // Vi ønsker å posisjonere elementene våre i midten
        this.setTop(topbox); // Setter topBox på BorderPane 

       // Her ønsker vi å dele opp HBox til to Vbokser 
       // 1) For å fylle linje - Venstre plassering 
        VBox leftTopBox = new VBox();
        leftTopBox.setAlignment(Pos.CENTER);
        topbox.getChildren().add(leftTopBox); // Adder det til HBox

        // 2) for å fylle figuren - Høyre Plassering
        VBox rightTopBox = new VBox();
        rightTopBox.setAlignment(Pos.CENTER);
        topbox.getChildren().add(rightTopBox); // Adder det til HBox

        // Fyller Venstre Top Box
        Label lineLbl = new Label("Linje Farge"); // Label med tekst
        leftTopBox.getChildren().add(lineLbl); //  Adder det til VBox

        HBox linebox = new HBox(); 
        linebox.setAlignment(Pos.CENTER); 
        linebox.setPrefSize(300, 50);
        leftTopBox.getChildren().add(linebox);
        
        // Fyller til høyre Top Boks
        Label fillLbl = new Label("Farge Fyll"); 
        rightTopBox.getChildren().add(fillLbl); 

        HBox fillbox = new HBox(); 
        fillbox.setAlignment(Pos.CENTER); 
        fillbox.setPrefSize(300, 50);
        rightTopBox.getChildren().add(fillbox);
        
        EventHandler<ActionEvent> DecorHandler = (ActionEvent e) -> {
                    handleDecorChange(); // Kaller opp klasse metode på sjekk Boks når det blir forandret
        };

        // Fyller linje boks
        // Her opprettes det tre check bokser
        cbLineRed = new CheckBox();
        cbLineRed.setSelected(false); // Det blir laget som default at ingenting blir selektert
        cbLineRed.setText("Rød"); // Beskrivelsen til check Boksen
        cbLineRed.setOnAction(DecorHandler); // Handler
        linebox.setPadding(new Insets(10, 10, 10, 10));
        linebox.getChildren().add(cbLineRed); // Add det til VBox

        cbLineGreen = new CheckBox();
        cbLineGreen.setSelected(false);
        cbLineGreen.setText("Grønn");
        cbLineGreen.setOnAction(DecorHandler);
        linebox.setPadding(new Insets(10, 10, 10, 10));
        linebox.getChildren().add(cbLineGreen);

        cbLineBlue = new CheckBox();
        cbLineBlue.setSelected(false);
        cbLineBlue.setText("Blå");
        cbLineBlue.setOnAction(DecorHandler);
        linebox.setPadding(new Insets(10, 10, 10, 10));
        linebox.getChildren().add(cbLineBlue);

        // Filling fillbox
        cbFillRed = new CheckBox();
        cbFillRed.setSelected(false);
        cbFillRed.setText("Rød");
        cbFillRed.setOnAction(DecorHandler);
        fillbox.setPadding(new Insets(10, 10, 10, 10));
        fillbox.getChildren().add(cbFillRed);

        cbFillGreen = new CheckBox();
        cbFillGreen.setSelected(false);
        cbFillGreen.setText("Grønn");
        cbFillGreen.setOnAction(DecorHandler);
        fillbox.setPadding(new Insets(10, 10, 10, 10));
        fillbox.getChildren().add(cbFillGreen);

        cbFillBlue = new CheckBox();
        cbFillBlue.setSelected(false);
        cbFillBlue.setText("Blå");
        cbFillBlue.setOnAction(DecorHandler);
        fillbox.setPadding(new Insets(10, 10, 10, 10));
        fillbox.getChildren().add(cbFillBlue);

        // Bunn posisjon for panelet
        HBox bottombox = new HBox(); 
        bottombox.setAlignment(Pos.CENTER); 
        bottombox.setFillHeight(false); 
        bottombox.setPrefSize(100, 100);
        this.setBottom(bottombox); 

        // label tips!
        Label lblTip = new Label("Tips: "
                + "\n - Velg figuren fra høyre siden av menyet etter at det har blitt tegnet "
                + "\n - Museklikk: Venstre/Høyre Klikk for å tegne "
                + "\n - Arrow Keys: Trykk tastaturet for å flytte figurene");
        bottombox.setMargin(lblTip, new Insets(0, 75, 0, 0)); // Lager en mellomrom for Label og fjern annullere knappen
        bottombox.getChildren().add(lblTip); 

        // Knapp for å annulere tegningen
        Button btClear = new Button();
        btClear.setText("Anuller Tegningen"); 
        btClear.setOnAction((ActionEvent e) -> {
            handleClearButtonAction(); 
        });
        bottombox.getChildren().add(btClear); 

        // Venstre vertikal posisjon
        VBox leftbox = new VBox();  
        leftbox.setAlignment(Pos.CENTER_LEFT); 
        leftbox.setFillWidth(false);  // bare kun senter panel er lov til å fylle 
        this.setLeft(leftbox);   // Vi setter det på borderPanen

        // En gruppe for radio knapper. Her skal vi sørge for at det er kun en knapp som blir selektert om gangen
        ToggleGroup figuresGroup = new ToggleGroup();

    
        EventHandler<ActionEvent> RadioHandler = (ActionEvent e) -> {
                    handleRadioButtonChange(); 
        };
        
        // her opprettes det fire radio knapper
        rbLine = new RadioButton();
        rbLine.setSelected(true); // RettLinje starter som en default knapp
        rbLine.setText("Rett Linje"); 
        rbLine.setToggleGroup(figuresGroup); // Vi adder det inn på toggle gruppe
        leftbox.setMargin(rbLine, new Insets(0, 20, 0, 20)); 
        rbLine.setOnAction(RadioHandler);
        leftbox.getChildren().add(rbLine); 

        currType = FIGURE_LINE;

        rbRectangle = new RadioButton();
        rbRectangle.setSelected(false);
        rbRectangle.setText("Rektangel");
        rbRectangle.setToggleGroup(figuresGroup);
        leftbox.setMargin(rbRectangle, new Insets(0, 20, 0, 20)); 
        rbRectangle.setOnAction(RadioHandler);
        leftbox.getChildren().add(rbRectangle);

        rbCircle = new RadioButton();
        rbCircle.setSelected(false);
        rbCircle.setText("Sirkel");
        rbCircle.setToggleGroup(figuresGroup);
        leftbox.setMargin(rbCircle, new Insets(0, 20, 0, 20));
        rbCircle.setOnAction(RadioHandler);
        leftbox.getChildren().add(rbCircle);

        rbPoly = new RadioButton();
        rbPoly.setSelected(false);
        rbPoly.setText("Polygon");
        rbPoly.setToggleGroup(figuresGroup);
        leftbox.setMargin(rbPoly, new Insets(0, 20, 0, 20)); 
        rbPoly.setOnAction(RadioHandler);
        leftbox.getChildren().add(rbPoly);

        // Høyere
        VBox rightbox = new VBox();  
        rightbox.setAlignment(Pos.CENTER_LEFT); 
        rightbox.setFillWidth(false);  
        this.setRight(rightbox);   

        // Info panel tittel
        Label lblTitle = new Label("Info:");
        lblTitle.setStyle("-fx-font-size: 20pt ;"); // Foranre font Størrelse via CSS
        rightbox.setMargin(lblTitle, new Insets(0, 20, 0, 20)); 
        rightbox.getChildren().add(lblTitle); 

        // Info liste
        list = new ListView<>();
        list.setPrefSize(320, 600); // Setter høyre informasjons siden etter størrelse vindu
        guiListItems = FXCollections.observableArrayList(); // Oppretter figur liste
        list.setItems(guiListItems); // kobler gui listen til listView
        
        // Adde muse event handler der det skal reagere på vi skal selektere på listen forandres 
        list.setOnMouseClicked((MouseEvent event)-> {
                    if (guiListItems.size() > 0) { // Hvis vi har noen figurer 
                        // Hvis det er noe forskjellige fra det nåværende figurene vi har valgt 
                        if (list.getSelectionModel().getSelectedIndex() != selectedFigureIndex) {
                            // Oppdatere det selekterte via indeksen 
                            selectedFigureIndex = list.getSelectionModel().getSelectedIndex(); 
                            handleListSelectionChange();
                        }
                    }
        });
        rightbox.setMargin(list, new Insets(0, 20, 0, 20)); // 
        rightbox.getChildren().add(list);

        // Senter
        pane = new Pane();
        pane.setStyle("-fx-border-color: BLACK;"); // Adder rammen utenfor senteret rundt via CSS
       
        
        // Vi lager en begresninger til panelet ved hjelp av clipping. 
        // Dette er for at vi kan tegne kun i senteret av panelet.
        // Pane gjør det ikke for oss
        final Rectangle outputClip = new Rectangle();
        outputClip.setWidth(pane.getWidth());
        outputClip.setHeight(pane.getHeight());
        // Addet dette det på panelet
        pane.setClip(outputClip);
        // Deretter binder vi fast etter panel størrelsen
        pane.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
                    outputClip.setWidth(newValue.getWidth());
                    outputClip.setHeight(newValue.getHeight());
        }
        );

        // Mouse Event Handler
        pane.setOnMousePressed((MouseEvent e) -> {
            handleMousePress(e);
        });
        pane.setOnMouseReleased((MouseEvent e) -> {
            handleMouseRelease(e);
        });
        pane.setOnMouseDragged((MouseEvent e) -> {
            handleMouseDrag(e);
        }
        );

        this.setCenter(pane); // Setter panelet i senteret

        //Everything else
        guiFigures = new ArrayList<>(20);
        selectedFigureIndex = -1; // Ingen figurer= ikke noe selekterte figurer fra indeksen
    }
    
    
    // Rensk Knappen til handleren 
    public void handleClearButtonAction() {
        drawingOn = false; // slutt på nåværende tegningen
        tempFig = null;
        // Fjerner permanenter fra listen
        guiFigures.clear();
        // Fjerner fra GUI Listen
        guiListItems.clear();
        // Fjerner det selekterte fra indeksen
        selectedFigureIndex = -1;
        // Fjerner alle children fra panen
        pane.getChildren().clear();
    }
    
    // Her brukeren prøver å bevege figurene
    public void handleMovement(double xdir, double ydir) {
        if (selectedFigureIndex >= 0) { // Hvis det er noe figurer 
            if (selectedFigureIndex < guiFigures.size()) { // og ikke midlertidig valgt
                // Flytte figuren fra listen 
                this.guiFigures.get(selectedFigureIndex).getFigure().moveFigure(xdir, ydir);
            } else {
                // eller flytt midleritdige det figuren 
                tempFig.getFigure().moveFigure(xdir, ydir);
            }
            refreshCurrentShape(); // og oppdatere tegningen på skjermen 
        }
    }

    // Checkboxes tilstanden som skal forandre seg 
    public void handleDecorChange() {
        if (selectedFigureIndex >= 0) { // Hvis det er noe figurer
            if (selectedFigureIndex == guiFigures.size()) { // og er midlertidig valgt 
                updateColorState(tempFig); // Oppdatere farge tilstanden
            } else {
                // Oppdatere farge fra listen 
                updateColorState(guiFigures.get(selectedFigureIndex)); 
            }
            refreshCurrentShape(); // og oppdatere tegnignen på skjermen
        }
    }
    
    public void handleRadioButtonChange() {
        // Iterer tilstanden til radio knappene.

        if (rbLine.isSelected()) { // Hvis knappene er valgt
            if (currType != FIGURE_LINE) { // Hvis det ikke har valgt før av knappen
                currType = FIGURE_LINE; // Oppdateres til en ny type 
                forceFigureCompletion(); // og bryter ned tegne modusen 
            }
        } else if (rbRectangle.isSelected()) {
            if (currType != FIGURE_RECTANGLE) {
                currType = FIGURE_RECTANGLE;
                forceFigureCompletion();
            }
        } else if (rbCircle.isSelected()) {
            if (currType != FIGURE_CIRCLE) {
                currType = FIGURE_CIRCLE;
                forceFigureCompletion();
            }
        } else if (rbPoly.isSelected()) {
            if (currType != FIGURE_POLY) {
                currType = FIGURE_POLY;
                forceFigureCompletion();
            }
        } else {
            // Hvis brukeren velger å deselektere alle radio knappene
            currType = FIGURE_NONE; 
        }
    }
    
    // Når det selekterte figurene forandrer seg 
    public void handleListSelectionChange() {
        if (drawingOn) { // Hvis vi er på tegne modus
            forceFigureCompletion(); // Slutt på tegne modus!
        }

       
        // Oppdaterer tilstanden til Checkboks gitt av figurer:
        boolean colorState[] = guiFigures.get(selectedFigureIndex).getColorStete(); // Trekker ut farge tilstanden
        // konvereterer det til checkBox tilstand
        cbFillRed.setSelected(colorState[0]);
        cbFillGreen.setSelected(colorState[1]);
        cbFillBlue.setSelected(colorState[2]);
        cbLineRed.setSelected(colorState[3]);
        cbLineGreen.setSelected(colorState[4]);
        cbLineBlue.setSelected(colorState[5]);
    }

// Mouse press handler
    public void handleMousePress(MouseEvent mouseEvent) {
        // Sørger for at vi kun trykeker på venstre knapp på musen vår
        if (mouseEvent.getButton() != MouseButton.PRIMARY) {
            
        }
        
        if (!drawingOn) { // Hvis vi ikke tegner
            // Da lager vi en ny figur
            Figure fig = getSelectedFigure(mouseEvent.getX(), mouseEvent.getY());
            if (fig != null) { // Hvis det er suksessfull opprettet 
                tempFig = new GUI_Figure(fig); // pakker det opp i GUI figure
                updateColorState(tempFig); // Setter opp fargen
                drawingOn = true; // Går inn i tegne modusen
                selectedFigureIndex = guiListItems.size(); // Den valgte indeksen skal samsvare fra listen
                refreshCurrentShape(); // og deretter tegne det på skjermen 
                refreshCurrentGUIListEntry(); // og adder det på GUI listen
            }
        } else { // Hvis vi tegner
            // Vi adder nye punkter på det nåværende koordinat posisjonen ved muse klikk
            tempFig.getFigure().addNextPoint(mouseEvent.getX(), mouseEvent.getY());
            refreshCurrentShape();  // og deretter tegner shapen på nytt
            refreshCurrentGUIListEntry(); // og oppdaterer det på vår GUI liste 
        }
    }

    // Mouse release handler
    public void handleMouseRelease(MouseEvent mouseEvent) {
        
        // Sørger for at vi kun kan trykke på venstre knapp
        if (mouseEvent.getButton() != MouseButton.PRIMARY) {
        }

        if (drawingOn) { // Bare gjør kun dette når det er på tegne modus
            tempFig.getFigure().completePoint(); //  Sørger for at flyttingen til figuren er ferdig
            refreshCurrentShape();  // Tegner shapen på nytt etter det 
            refreshCurrentGUIListEntry(); // og oppdaterer GUI listen 
            if (tempFig.getFigure().isFigureComplete()) { // Hvis figuren er ferdig 
                drawingOn = false; // slutt på tegne modus
                guiFigures.add(tempFig); // Deretter adder vi tegne figuren til vår liste 
                tempFig = null;
            }
        }
    }

    // Mouse drag handler
    public void handleMouseDrag(MouseEvent mouseEvent) {
        
        if (drawingOn) {
            // Flytt til det siste nåværende punktet til det nye koordinatet
            tempFig.getFigure().moveLastPoint(mouseEvent.getX(), mouseEvent.getY());
            refreshCurrentShape(); // Regner shapen på nytt etter det 
            refreshCurrentGUIListEntry(); // og oppdaterer det inn på gui listen
        }
    }

    // Forsøker på å bryte tegne prosessen
    private void forceFigureCompletion() {
        if (drawingOn) { // Blir kalt opp hvis det skal være under tegne prossessen
        
            tempFig.getFigure().completeFigure(); // Tvinger det nåværende figuren å bli ferdig
            refreshCurrentShape(); // og deretter tegne shapen på nytt
            drawingOn = false; // slutt på tegne prosessen!
            guiFigures.add(tempFig); // Flytter figuren til permanent listen
            tempFig = null;
        }
    }
    
    // Her skal det tegnes det nåværende selekterte figuren på skjermen
    private void refreshCurrentShape() {
        if (selectedFigureIndex >= 0) {   // Hvis det er noe figurer
            if (selectedFigureIndex == guiFigures.size()) { // og midlertidig er selektert 
                if (selectedFigureIndex >= pane.getChildren().size()) { // og det er ikke blitt tegnet enda
                    pane.getChildren().add(tempFig.getDecoratedShape()); // Addet shapen på skjermen
                } else {
                    pane.getChildren().set(selectedFigureIndex,
                            tempFig.getDecoratedShape()); // eller bare oppdatere shapen
                }
            } else {
                // Ellers oppdaterer shapen av det selekterte figuren fra permanent listen
                pane.getChildren().set(selectedFigureIndex, guiFigures.get(selectedFigureIndex).getDecoratedShape());
            }
        }
    }

    // Figure factory.
    private Figure getSelectedFigure(double originX, double originY) {
        // Itererer over mulige figur typer
        switch (currType) {
            case FIGURE_LINE: return new LineFigure(originX, originY, originX, originY);
            case FIGURE_RECTANGLE: return new RectangleFigure(originX, originY, originX, originY);
            case FIGURE_CIRCLE: return new CircleFigure(originX, originY, originX, originY);
            case FIGURE_POLY: return new PolygonFigure(originX, originY, originX, originY);
        }
        return null; 
    }

   
    // Oppdaterer gitt av figur farge tilstand i hensyn av checkboksene for linje og fylle farge til figurene
    private void updateColorState(GUI_Figure fig) {
        boolean newState[] = new boolean[6]; 
        newState[0] = cbFillRed.isSelected();
        newState[1] = cbFillGreen.isSelected();
        newState[2] = cbFillBlue.isSelected();
        newState[3] = cbLineRed.isSelected();
        newState[4] = cbLineGreen.isSelected();
        newState[5] = cbLineBlue.isSelected();
        fig.setColorState(newState); // setter fargene til figuren
    }

    
    // Oppdaterer nåværende figurer på GUI Listen
    private void refreshCurrentGUIListEntry() {
        if (selectedFigureIndex >= 0) { // Hvis det er noe figurer 
            if (selectedFigureIndex == guiFigures.size()) { // Og er midlertidig selektert
                String desc = getShapeDescription(tempFig.getFigure()); // Henter beskrivelsen fra figuren
                if (selectedFigureIndex >= guiListItems.size()) { // og adder beskrivelsen gitt av det ikke har blitt settet noe på listen 
                    guiListItems.add(desc); 
                } else {
                    guiListItems.set(selectedFigureIndex, desc); // eller oppdaterer det
                }
            } else {
                // ellers oppdateres beskrivelsen for den valgte figuren fra listen
                String desc = getShapeDescription(guiFigures.get(selectedFigureIndex).getFigure());
                guiListItems.set(selectedFigureIndex, desc);
            }
        }
       
        list.scrollTo(selectedFigureIndex); // Scrolle for å oppdatere det valgte figuren fra indeksen
        list.getSelectionModel().select(selectedFigureIndex); // selekteres det 
        list.getFocusModel().focus(selectedFigureIndex); // og deretter fokusere på det
    }
    
    // Dette er shape informasjons beskrivelsene til figuren som skal vise til listen på skjermen
    private String getShapeDescription(Figure fig) {
        String result = fig.getName() + ": "; 
        double length = fig.getTotalSideLength(); // Henter lengden
        double area = fig.getArea(); // og arealet
        DecimalFormat df = new DecimalFormat("0.0#"); // setter format verdiene på double
        if (length >= 0) { // Hvis lengden eksisterer for det figuren 
            result += "Lengde = " + df.format(length) + " px"; // add it to string
        }
        if (length >= 0 && area >= 0) { // Hvis både lengden og arealet eksisterer 
            result += ", "; 
        }
        if (area >= 0) {
            result += "areal = " + df.format(length) + " px^2";
        }
        return result;
    }

}
