package obligatorisk.oppgave;

import java.awt.Rectangle;
import java.awt.Shape;

/**
 * @author Khoi Nguyen Hoang: 162749
 */
public class Rektangel extends ToPunkterFigurer {

    // Vi lager konstruktør for å initere punktene
    public Rektangel(double startX, double startY, double sluttX, double sluttY) {
        this.initierePunkt(startX, startY, sluttX, sluttY);
    }
    
    @Override
    public Shape getShape() {
        
        // Først, så må vi finne top venstre hjørnen til koordinaten 
        // Vi tar hensyn av både punktene x og y
        
       double x;
       double y;
       double bredde = Math.abs(originalX - andrePunktX);
       double høyde = Math.abs(originalY - andrePunktY);
       
       if (originalX < andrePunktX) {
           x = originalX;
       } else {
           x = andrePunktX;
       }
       
       if(originalY < andrePunktY) {
           y = originalY;
       } else {
           y =  andrePunktY;
       }
       
       // Når punktene av koordinatene er funnet og bredde/høyde (distanse mellom punktene på hver av 
       // koordinat aksene) fir å opprette en rektangel

       return new Rectangle((int) x, (int) y, (int) bredde, (int) høyde);
    }

    @Override
    public double getTotalSideLengde() {
        // Vi bruker ved hjelp av rektangel perimeter formel: 2a + 2b, hvor a og b er rektangel sider
        return 2 * Math.abs(originalX - andrePunktX) + 2 * Math.abs(originalY - andrePunktY);
    }

    @Override
    public double getAreal() {
        // Vi bruker ved hjelp av rektangelområdet formel: a * b, hvor a og b er rektangel sider
        return Math.abs(originalX - andrePunktX) * Math.abs(originalY - andrePunktY);
    }
    
    @Override
    public String getNavnFigur() {
        return "Rektangel";
    }
    
}
