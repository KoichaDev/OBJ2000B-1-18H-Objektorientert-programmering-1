package Figures;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class CircleFigure extends TwoPointsFigure {

    // We use constructor to just initialise the points
    public CircleFigure(double startX, double startY, double endX, double endY)
    {
        this.initPoints(startX, startY, endX, endY);
    }

    @Override
    public Shape getShape()
    {
        // To build an ellipse from two opposite corners of a bounding box,
        // we find center coordinates as an average of corners coordinates
        // and two radi as a half-distances between corners: halves of width and height of a bounding box
        return new Ellipse((originX + secondPointX) / 2, (originY + secondPointY) / 2,
                Math.abs(secondPointX - originX) / 2, Math.abs(secondPointY - originY) / 2);
    }

}
