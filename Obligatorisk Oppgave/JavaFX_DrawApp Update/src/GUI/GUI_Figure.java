package GUI;

import Figures.Figure;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author Khoi Nguyen Hoang: 162749
 */

public class GUI_Figure {
    
    // Det skal oppstå etter GUI CheckBox for disse figurene
    private final boolean colorStates[];
    private final Figure fig; // original figure objekt

   
    public GUI_Figure(Figure fig) {
        this.fig = fig;
        colorStates = new boolean[6];
    }

    // Setter opp farge Tilstand
    public void setColorState(boolean newState[]) {
        for (int i = 0; i < 6; i++) {
            colorStates[i] = newState[i];
        }
    }

    // returnerer farge tilstanden
    public boolean[] getColorStete() {
        return colorStates;
    }
    // Her skal det returneres figur Shape objektet med dekorasjoner av farger og stil
    public Shape getDecoratedShape() {
        Shape shp = fig.getShape();
        decorateShape(shp);
        return shp;
    }

    public Figure getFigure() {
        return fig;
    }

    // Transform color states in boloean to color object for fill
    // First three color states correspondsto fill
    private Color getFillColor() {
        double r, g, b;
        if (colorStates[0]) {
            r = 1.0;
        } else {
            r = 0.0;
        }
       
        if (colorStates[1]) {
            g = 1.0;
        } else {
            g = 0.0;
        }
       
        if (colorStates[2]) {
            b = 1.0;
        } else {
            b = 0.0;
        }
        // Vi bruker kombinasjonene fra fargene (rgb) til å få ulike farger
        // Alpha er alltid 1.0
        return new Color(r, g, b, 1.0);
    }

   
    private Color getLineColor() {
        double r,g,b;
        if (colorStates[3]){
            r = 1.0;
        } else{
            r = 0.0;
        }
        
        if (colorStates[4]) {
            g = 1.0;
        } else{
            g = 0.0;
        }
        if (colorStates[5]) {
            b = 1.0;
        } else {
            b = 0.0;
        }
        return new Color(r, g, b, 1.0);
    }
    
    // Her skal det sette GUI specifikke for å dekorere Shapen der det skal bli gitt til figurene
    private void decorateShape(Shape orig) {
        orig.setFill(getFillColor()); // Fill Farger
        orig.setStroke(getLineColor()); // Strek Farger
        orig.setStrokeWidth(4); // Strek tykkelsen (bredden) 
        orig.setStrokeLineCap(StrokeLineCap.ROUND); //Strek form
    }

}
