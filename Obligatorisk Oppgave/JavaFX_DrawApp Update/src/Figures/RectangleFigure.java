package Figures;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Khoi Nguyen Hoang: 162749
 */

public class RectangleFigure extends TwoPointsFigure {

    // Vi bruker konstrukutør for å initiere punktene
    public RectangleFigure(double startX, double startY, double endX, double endY) {
        this.initPoints(startX, startY, endX, endY);
    }

    @Override
    public Shape getShape() {
        
        // Først, så må vi finne top venstre hjørnen til koordinaten 
        // Vi tar hensyn av både punktene x og y
        double x, y;
        if (originX < secondPointX){
            x = originX;
        } else{
            x = secondPointX;
        }
        
        if (originY < secondPointY) {
            y = originY;
        } else {
            y = secondPointY;
        }
        // Når punktene av koordinatene er funnet og bredde/høyde (distanse mellom punktene på hver av 
       // koordinat aksene) fir å opprette en rektangel
        return new Rectangle(x, y,
                Math.abs(originX - secondPointX), Math.abs(originY - secondPointY));
    }

    @Override
    public double getTotalSideLength() {
         // Vi bruker ved hjelp av rektangel perimeter formel: 2a + 2b, hvor a og b er rektangel sider
        return 2 * Math.abs(originX - secondPointX) + 2 * Math.abs(originY - secondPointY);
    }

    @Override
    public double getArea() {
        // Vi bruker ved hjelp av rektangelområdet formel: a * b, hvor a og b er rektangel sider
        return Math.abs(originX - secondPointX) * Math.abs(originY - secondPointY);
    }

    @Override
    public String getName() {
        return "Rektangel";
    }
}
