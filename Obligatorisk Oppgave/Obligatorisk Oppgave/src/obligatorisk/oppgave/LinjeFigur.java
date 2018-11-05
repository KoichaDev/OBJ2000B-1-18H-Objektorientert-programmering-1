package obligatorisk.oppgave;

import java.awt.Shape;
import javafx.scene.shape.Line;

/**
 *
 * @author Khoi Nguyen Hoang: 162749
 */

public class LinjeFigur extends ToPunkterFigurer {

    // Her ønsker vi å bruke konstruktør for å initiere punktene.
    public LinjeFigur(double startX, double startY, double sluttX, double sluttY) {
        this.initierePunkt(startX, startY, sluttX, sluttY);
    }
    
    @Override
    public Shape getShape() {
        return (Shape) new Line(originalX, originalY, andrePunktX, andrePunktY);
    }
    
    @Override
    public double getTotalSideLengde() {
        return Math.pow((Math.pow(originalX - andrePunktX, 2) + Math.pow(originalY - andrePunktY, 2)), 0.5);
    }

    
    @Override
    public double getAreal() {
        // Vanskelig å finne arealet av denne figuren pga. konverterings prosess fra px skjerm til 
      return 0;
    }

    @Override
    public String getNavnFigur() {
        return "Linje";
    }

   
    
    
    
}
