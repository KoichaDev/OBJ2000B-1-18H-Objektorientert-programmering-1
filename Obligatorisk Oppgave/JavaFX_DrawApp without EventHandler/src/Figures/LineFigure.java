package Figures;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineFigure extends TwoPointsFigure {

    // We use constructor to just initialise the points
    public LineFigure(double startX, double startY, double endX, double endY)
    {
        this.initPoints(startX, startY, endX, endY);
    }

    // Here we convert a pair of points into the line shape
    @Override
    public Shape getShape()
    {
        return new Line(originX, originY, secondPointX, secondPointY);
    }

}
