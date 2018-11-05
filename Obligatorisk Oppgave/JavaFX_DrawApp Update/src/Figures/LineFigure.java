package Figures;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

/**
 * @author Khoi Nguyen Hoang: 162749
 */

public class LineFigure extends TwoPointsFigure {
    
    // Vi bruker konstrukutør for å initiere punktene
    public LineFigure(double startX, double startY, double endX, double endY) {
        this.initPoints(startX, startY, endX, endY);
    }
   
    // Her konverterer vi ett par av punktene til linje Shape
    @Override
    public Shape getShape() {
        return new Line(originX, originY, secondPointX, secondPointY);
    }

    @Override
    public double getTotalSideLength() {
        // Brukes Euclidian disanse formel for å finne mellom punktene
        return Math.pow((Math.pow(originX - secondPointX, 2) + Math.pow(originY - secondPointY, 2)), 0.5);
    }

    @Override
    public double getArea() {
        // Arelaet skal ikke vise frem på listen  hvis det ikke er noe areal
        return -1;
    }

    @Override
    public String getName() {
        return "Linje";
    }

}
