package obligatorisk.oppgave;


import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

/**
 *
 * @author Khoi Nguyen Hoang: 162749
 */
public class GUI_Figure {
    private boolean fargeTilstand[];
    private Figure figur; //Original figurene
    
    public GUI_Figure(Figure fig) {
        this.figur = fig;
        fargeTilstand = new boolean[6];
    }
    
    public void setFargeTilstand(boolean nyTilstand[]) {
        for(int i = 0; i < 6; i++) {
            if(nyTilstand.length <= i) {
                break;
            }
            fargeTilstand[i] = nyTilstand[i];
        }
    }
    
    public boolean [] getFargeTilstand() {
        return fargeTilstand;
    }
    
    public Figure getFigur() {
        return figur;
    }
    
    private Color getFyllFarge() {
        double r,g,b;
        
        if(fargeTilstand[0]) {
            r = 1.0;
        } else {
            r = 0.0;
        }
        
        if(fargeTilstand[1]) {
            g = 1.0;
        } else {
            g = 0.0;
        }
        
        if(fargeTilstand[2]) {
            b = 1.0;
        } else {
            b = 0.0;
        }
        return new Color(r, g, b, 1.0);
    }
    
    // Forandrer farge tilstanden til farge objeketene for rett linje
    
    public Color getLinjeFarge() {
         double r,g,b;
        
        if(fargeTilstand[3]) {
            r = 1.0;
        } else {
            r = 0.0;
        }
        
        if(fargeTilstand[4]) {
            g = 1.0;
        } else {
            g = 0.0;
        }
        
        if(fargeTilstand[5]) {
            b = 1.0;
        } else {
            b = 0.0;
        }
        return new Color(r, g, b, 1.0);
    }
    
    public Shape getDecorasjonShape() {
        Shape shape = (Shape) figur.getShape();
        dekorereShapen(shape);
        return shape;
    }
    
    private void dekorereShapen(Shape original) {
        original.setFill(getFyllFarge()); // Fyller fargene
        original.setStroke(getLinjeFarge()); // Fyller linjefarge
        original.setStrokeWidth(4); // Angir linje tykkelsen av bredden
        original.setStrokeLineCap(StrokeLineCap.ROUND); 
        original.setMouseTransparent(true); // Sørger for at shapen ikke skal hindre muse eventen.
    }
    
   
}
