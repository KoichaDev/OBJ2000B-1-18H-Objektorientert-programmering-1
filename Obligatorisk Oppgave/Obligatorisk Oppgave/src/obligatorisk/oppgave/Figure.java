package obligatorisk.oppgave;

import java.awt.Shape;

/**
 * @author Khoi Nguyen Hoang: 162749
 */
public interface Figure {
    
    // returnerer tilstanden til Shape figuren der det skal tegnes på panelen
    public abstract Shape getShape();
    
    // Tilsetter neste punkt til figuren av (x,y) koordinaten
    public abstract void settNestePunkt(double x, double y);
    
    // Flytter til den siste tilførte punkte til (x,y). 
    public abstract void flyttSistePunkt(double x, double y);
    
    // Skal returnere "sant" hvis det er ikke flere punkter i figuren
    public abstract boolean erFigurenFerdig();
    
    // henter den siste punktet, og det skal være garantert at det ikke skal flytte etterpå.
    public abstract void punktFullført();
    
    // 
    public abstract void ferdigFigur();
    
    public abstract double getTotalSideLengde();
    
    public abstract double getAreal();
    
    // Returnerer navnet til figuren
    public abstract String getNavnFigur();
    
    public abstract void flyttFigur(double x, double y);   
    
}
