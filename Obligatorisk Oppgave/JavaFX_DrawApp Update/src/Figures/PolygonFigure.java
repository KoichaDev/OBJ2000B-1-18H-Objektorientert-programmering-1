package Figures;

import java.util.ArrayList;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

/**
 *
 * @author Khoi Nguyen Hoang: 162749
 */

public class PolygonFigure implements Figure {
    
    private static final int ORIGIN_X_INDEX = 0;
    private static final int ORIGIN_Y_INDEX = 1;
    
    private final ArrayList<Double> points;
    // Polygon Check punkt - flag
    private boolean completion = false;
    // Siste punkt som har blitt flyttet ferdig flag
    private boolean lastPointComplete;
    
    // Legger til det opprinnelige og først bevegelsene til ArrayList
    public PolygonFigure(double startX, double startY, double endX, double endY) {
        points = new ArrayList<>(4);
        points.add(startX);
        points.add(startY);
        points.add(endX);
        points.add(endY);

    }

    @Override
    public Shape getShape() {
        Polyline poly = new Polyline();
        poly.getPoints().addAll(points);
        return poly;
    }

    // Tilsetter ett ny punkt inn til listen
    @Override
    public void addNextPoint(double x, double y) {
        points.add(x);
        points.add(y);
        lastPointComplete = false; // markerer at det er bevegelig
    }

    @Override
    public void moveLastPoint(double x, double y) {
        // Punktene kan flyttes mens polygon er ufullstendig der og punktet har ikke er blitt plassert ennå/enda
        if (!isFigureComplete() && !lastPointComplete) {
            int size = points.size(); // Henter Array size
            // Fjerner gamle punkter - de siste to koordinatene fra Arrayen
            points.remove(size - 1);
            points.remove(size - 2);
            // Setter inn nye koordinatene
            points.add(x);
            points.add(y);
        }
    }
    
    // Punktene er ferdig. Det kan ikke flyttes mer 
    @Override
    public void completePoint() {
        checkCompletion();
        lastPointComplete = true;
    }
    
    // Tvinger polygonen til å lukke
    @Override
    public void completeFigure() {
        if (!isFigureComplete()) {
            // Vi setter i tillegg siste punktene lik original punktene
            points.add(points.get(ORIGIN_X_INDEX));
            points.add(points.get(ORIGIN_Y_INDEX));
            completion = true; // mark as complete
        }
    }

    @Override
    public boolean isFigureComplete() {
        return completion;
    }

    @Override
    public double getTotalSideLength() {
        // Vi bruker Euclidian distanse formelen for å finne distane mellom hver av par av 
        // punktene og deretter summerer vi det opp
        double length = 0;
        for (int i = 0; i < points.size() - 2; i += 2) {
            // i - det er x koordinatene i nå værende punktene
            // i+1 - y er nå værende punktene
            // i+2 - x er neste punkt
            // i+3 - y er neste punkt
            length += Math.pow((Math.pow(points.get(i) - points.get(i + 2), 2)
                    + Math.pow(points.get(i + 1) - points.get(i + 3), 2)), 0.5);
        }
        return length;
    }

    @Override
    public double getArea() {
        // -1 hvis det ikke er noe areal
        return -1;
    }

    @Override
    public String getName() {
        return "Polygon";
    }
    
    @Override
    public void moveFigure(double x, double y) {
        for (int i = 0; i < points.size(); i += 2) {
            points.set(i, points.get(i) + x); // x-koordinaten er holdes i elementene
            points.set(i + 1, points.get(i + 1) + y); // y-koordinaten i de ulike 
        }
    }

    // kontrollerer ferdigstillingsbetingelsene 
    private void checkCompletion() {
        int size = points.size(); // // henter liste størrelsen
        
        // Henter det siste punktene
        double endX = points.get(size - 2);
        double endY = points.get(size - 1);
         // Sjekker ferdig tilstandet 
        if (Math.abs(endX - points.get(ORIGIN_X_INDEX)) <= 5
                && Math.abs(endY - points.get(ORIGIN_Y_INDEX)) <= 5)
        {
            // Fjerner det gamle verdiene
            points.remove(size - 1);
            points.remove(size - 2);
            // Deretter erstatter vi dem med det originale verdiene
            points.add(points.get(ORIGIN_X_INDEX));
            points.add(points.get(ORIGIN_Y_INDEX));
            completion = true; // Markerer at det er ferdig
        }
    }

}
