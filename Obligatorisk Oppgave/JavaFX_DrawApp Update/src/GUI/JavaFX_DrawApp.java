package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFX_DrawApp extends Application {

/**
 * @author Khoi Nguyen Hoang: 162749
 */
   
    public static void main(String[] args) {
        launch(args);
    }

 
    @Override
    public void start(Stage stage) throws Exception
    {
        // Oppretter for å kjøre panelet
        MainFrame topLevelPane = new MainFrame();

        
        Scene scene = new Scene(topLevelPane);

        // Her Legges det til nøkkelhendelsefilter til scenen. Vi legger til det her slik at det brukes til alle
        // node i scenen og ikke bare til hoved panelet, men til alle sine child også
        // Vi bruker filter i stedet for handler for å fange hendelser og ikke la passere til andre handlers
        // Dette gjør det mulig å unngå ting, f.eks. tab-fokus på kontroller panelet, 
        // og også ved å flytte med piler.
        
         
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                    switch (e.getCode()) { // Prossess for å håndtere etter trykke knapp for piler
                        case LEFT: topLevelPane.handleMovement(-1, 0); break;
                        case RIGHT:topLevelPane.handleMovement(1, 0); break;
                        case DOWN: topLevelPane.handleMovement(0, 1); break;
                        case UP: topLevelPane.handleMovement(0, -1); break;
                    }
                    e.consume(); // Dette preventerer eventen fra å gå til andre handlers
        });
        stage.setScene(scene);
        stage.setTitle("Khoi Nguyen Hoang: 162749 - Obligatorisk Oppgave");
        stage.show();
    }

}
