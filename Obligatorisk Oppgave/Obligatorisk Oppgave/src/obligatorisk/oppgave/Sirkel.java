package obligatorisk.oppgave;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javafx.scene.shape.Ellipse;

/**
 *
 * @author Khoi Nguyen Hoang
 */
public class Sirkel extends ToPunkterFigurer {

    public Sirkel(double startX, double startY, double sluttX, double sluttY) {
        this.initierePunkt(startX, startY, sluttX, sluttY);
    }

    @Override
    public Shape getShape() {
        
        // For å lage en ellipse fra to motsatte siden
        // Først, så må vi lage en senter koordinat som en gjennomsnittet av hjørnen til koordinaten
        // og to radius sin en halv distanse mellom hjørnene:
        // Halve av bredde og høyden
         return (Shape) new Ellipse((originalX + andrePunktX) / 2, (originalY + andrePunktY) / 2,
                Math.abs(andrePunktX - originalX) / 2, Math.abs(andrePunktY - originalY) / 2);
    }

    @Override
    public double getTotalSideLengde() {
        // Vi bruker ved hjelp av ellipse lengde formel pi * (a + b), hvor a og b er ellipsens radius
        return (Math.abs(originalX - andrePunktX) / 2 + Math.abs(originalY - andrePunktY) / 2) * Math.PI;
    }

    @Override
    public double getAreal() {
        // Vi bruker ved hjelp av ellipseområdet formel pi * a * b, hvor a og b er ellipseens radiusen
        return Math.abs(originalX - andrePunktX) / 2 * Math.abs(originalY - andrePunktY) / 2 * Math.PI;
    }

    @Override
    public String getNavnFigur() {
        return "Ellipse";
    }
    
}
