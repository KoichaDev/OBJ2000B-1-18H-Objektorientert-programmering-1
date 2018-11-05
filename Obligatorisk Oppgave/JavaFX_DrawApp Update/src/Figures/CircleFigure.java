package Figures;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

/**
 * @author Khoi Nguyen Hoang: 162749
 */

public class CircleFigure extends TwoPointsFigure {

    // Vi bruker konstrukutør for å initiere punktene
    public CircleFigure(double startX, double startY, double endX, double endY) {
        this.initPoints(startX, startY, endX, endY);
    }

    @Override
    public Shape getShape() {
         // For å lage en ellipse fra to motsatte siden
        // Først, så må vi lage en senter koordinat som en gjennomsnittet av hjørnen til koordinaten
        // og to radius sin en halv distanse mellom hjørnene:
        // Halve av bredde og høyden
        return new Ellipse((originX + secondPointX) / 2, (originY + secondPointY) / 2,
                Math.abs(secondPointX - originX) / 2, Math.abs(secondPointY - originY) / 2);
    }

    @Override
    public double getTotalSideLength() {
        // using ellipse length formula pi*(a+b), where a and b are radi of the ellipse
        return (Math.abs(originX - secondPointX) / 2 + Math.abs(originY - secondPointY) / 2) * Math.PI;
    }

    @Override
    public double getArea() {
        // Vi bruker ved hjelp av ellipse lengde formel pi * (a + b), hvor a og b er ellipsens radius
        return Math.abs(originX - secondPointX) / 2 * Math.abs(originY - secondPointY) / 2 * Math.PI;
    }

    @Override
    public String getName() {
        return "Ellipse";
    }

}
