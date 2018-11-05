package Figures;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleFigure extends TwoPointsFigure {

    // We use constructor to just initialise the points
    public RectangleFigure(double startX, double startY, double endX, double endY)
    {
        this.initPoints(startX, startY, endX, endY);
    }

    @Override
    public Shape getShape()
    {
        // First we need to find top left corner coordinates by taking min of two points
        // coordinates on each axis
        double x;
        if (originX < secondPointX)
        {
            x = originX;
        } else
        {
            x = secondPointX;
        }
        double y;
        if (originY < secondPointY)
        {
            y = originY;
        } else
        {
            y = secondPointY;
        }
        // Then we use those coordinates and width/height (which are found as an
        // distances between points on each coordinate axis) to create rectangle
        return new Rectangle(x, y,
                Math.abs(originX - secondPointX), Math.abs(originY - secondPointY));
    }
}
