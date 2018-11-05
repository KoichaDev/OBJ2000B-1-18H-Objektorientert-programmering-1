package Figures;

import javafx.scene.shape.Shape;

/**
 * @author Khoi Nguyen Hoang: 162749
 */

// Interface figuren der det er kontroll og struktur for å aksessere å tegne figurene
public interface Figure {
    
    // Returnerer Shapen av figuren som skal bli tegnet på panelet
    public abstract Shape getShape();
    
    // Setter opp ett annet punkt til figuren (x,y) 
    public abstract void addNextPoint(double x, double y);

    // Move the last added point to (x,y) in local coordinates    
    public abstract void moveLastPoint(double x, double y);
    
    // Returnerer "sant" hvis det er ikke noe flere punkter som kan bli lagt på figuren
    public abstract boolean isFigureComplete();
    
    // Kaller opp når det siste punktet der det er garantert at det ikke flytter seg 
    public abstract void completePoint();
    
    // Figuren er ferdig når det er ikke flere punkter å adde. Da er figuren ferdig. 
    public abstract void completeFigure();
    
    // Returnerer totale side lengden på alle figurene
    public abstract double getTotalSideLength();

    // Returnerer figurene til arealet, eller - 1 hvis det er ikke noe areal i det hele tatt
    // Eksempel er da rett linje
    public abstract double getArea();

    // Returer figurens navn
    public abstract String getName();

    // flytter alle figur punktene i bruk av vektor (x,y)
    public abstract void moveFigure(double x, double y);

}
