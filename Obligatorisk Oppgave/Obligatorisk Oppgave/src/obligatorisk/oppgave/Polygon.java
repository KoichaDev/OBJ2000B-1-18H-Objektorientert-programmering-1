package obligatorisk.oppgave;

import java.awt.Shape;
import java.util.*;
import javafx.scene.shape.Polyline;

/**
 *
 * @author Khoi Nguyen Hoang: 162749
 */
public class Polygon implements Figure {
    
    private static final int ORIGINAL_X_INDEX = 0;
    private static final int ORIGINAL_Y_INDEX = 1;
    
    private final ArrayList<Double> punkter;
    
    private boolean komplett = false;
    private boolean sistePunktKomplett;
    
    public Polygon (double startX, double startY, double sluttX, double sluttY) {
        punkter = new ArrayList<>(4);
        punkter.add(startX);
        punkter.add(startY);
        punkter.add(sluttX);
        punkter.add(sluttY);
    }

    @Override
    public Shape getShape() {
        Polyline polygon = new Polyline();
        polygon.getPoints().addAll(punkter);
        return (Shape) polygon;
    }

    @Override
    public void settNestePunkt(double x, double y) {
    punkter.add(x);
    punkter.add(y);
    sistePunktKomplett = false;
    }

    @Override
    public void flyttSistePunkt(double x, double y) {
        if(!erFigurenFerdig() && !sistePunktKomplett) {
            int størrelse = punkter.size(); // Henter Array size
            // Fjerner gamle punkter - de siste to koordinatene fra Arrayen
            punkter.remove(størrelse -1);
            punkter.remove(størrelse -2);
            
            // Adder de nye koordinatene
            punkter.add(x);
            punkter.add(y);
        }
    }

    
    @Override
    public boolean erFigurenFerdig() {
        return komplett;
    }

    // Markerer at punktene kan ikke beveges
    @Override
    public void punktFullført() {
        ferdig();
        sistePunktKomplett = true;
    }
    
    // Tvinger polygon til å bli ferdig
    @Override
    public void ferdigFigur() {
        if(!erFigurenFerdig()) {
            punkter.add(punkter.get(ORIGINAL_X_INDEX));
            punkter.add(punkter.get(ORIGINAL_Y_INDEX));
            komplett = true; // Markerer det som komplett
        }
    }

    @Override
    public double getTotalSideLengde() {
        
        // VI bruker Euclidian distanse formelen for å finne distane mellom hver av par av 
        // punktene og deretter summerer vi det opp
        
        double lengden = 0;
        for(int i = 0; i < punkter.size() - 2; i += 2) {
            // i - det er x koordinatene i nå værende punktene
            // i+1 - y er nå værende punktene
            // i+2 - x er neste punkt
            // i+3 - y er neste punkt
           lengden += Math.pow((Math.pow(punkter.get(i) - punkter.get(i + 2), 2)
                    + Math.pow(punkter.get(i + 1) - punkter.get(i + 3), 2)), 0.5);
        }
        return lengden;
    }

    @Override
    public double getAreal() {
       return 0;
    }

    @Override
    public String getNavnFigur() {
        return "Polygon";
    }

    @Override
    public void flyttFigur(double x, double y) {
        for(int i = 0; i < punkter.size(); i+= 2) {
            punkter.set(i, punkter.get(i) + x); // x-koordinaten er holdes i elementene
            punkter.set(i + 1, punkter.get(i + 1) + y); // y-koordinaten i de ulike 
        }
    }

    // // kontrollerer ferdigstillingsbetingelsene 
    private void ferdig() {
        int størrelse = punkter.size(); // henter liste størrelsen
        
        // Henter det siste punktene
        double sluttX = punkter.get(størrelse - 2);
        double sluttY = punkter.get(størrelse - 1);
        
        // Sjekker ferdig tilstandet og snap end punktene til det originale
       if (Math.abs(sluttX - punkter.get(ORIGINAL_X_INDEX)) <= 5
                && Math.abs(sluttY - punkter.get(ORIGINAL_Y_INDEX)) <= 5) {
           
           // Hvis ja - så snapper det til det originalet
           // Fjerner det gamle verdiene
           punkter.remove(størrelse - 1);
           punkter.remove(størrelse - 2);
           
           // Deretter erstatter vi dem med det originale verdiene
           punkter.add(punkter.get(ORIGINAL_X_INDEX));
           punkter.add(punkter.get(ORIGINAL_Y_INDEX));
           komplett = true; // Markerer at det er ferdig
       }
    }
}
