package Figures;

import java.util.ArrayList;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

public class PolygonFigure implements Figure {

    // Constants - indices of first point X and Y coordinates.
    // To not use magic numbers 0 and 1
    private static final int ORIGIN_X_INDEX = 0;
    private static final int ORIGIN_Y_INDEX = 1;
    // Flat array of points coordinates. Odd elements are X coordinaters,
    // even are Y. Such format is choosen for compatibility with
    // Polyline and Polygon classes, who accepts their points in such format
    private final ArrayList<Double> points;
    // Polygon completion flag
    private boolean completion = false;
    // Last point movement completion flag
    private boolean lastPointComplete;

    // Add origin and first movable point to array list
    public PolygonFigure(double startX, double startY, double endX, double endY)
    {
        points = new ArrayList<>(4);
        points.add(startX);
        points.add(startY);
        points.add(endX);
        points.add(endY);

    }

    @Override
    public Shape getShape()
    {
        // Transform our point list into the polyline. (Polyline is choosen
        // to provide ability todisplay open, non-complete polygon in the middle of the drawing)
        Polyline poly = new Polyline();
        poly.getPoints().addAll(points);
        return poly;
    }

    // Add point into the list
    @Override
    public void addNextPoint(double x, double y)
    {
        points.add(x);
        points.add(y);
        lastPointComplete = false; // mark it as movable
    }

    @Override
    public void moveLastPoint(double x, double y)
    {
        // Point only can be moved while polygon incomplete and point hasn't been placedyet
        if (!isFigureComplete() && !lastPointComplete)
        {
            int size = points.size(); // get array size
            // remove old point - last two coordinates in array
            points.remove(size - 1);
            points.remove(size - 2);
            // add its new coordinates
            points.add(x);
            points.add(y);
        }
    }

    // Mark point as unmovable
    @Override
    public void completePoint()
    {
        checkCompletion();
        lastPointComplete = true;
    }

    // Force polygon enclosing
    @Override
    public void completeFigure()
    {
        if (!isFigureComplete()) // If it wasn't close already
        {
            // Add aditional last point equal to origin point
            points.add(points.get(ORIGIN_X_INDEX));
            points.add(points.get(ORIGIN_Y_INDEX));
            completion = true; // mark as complete
        }
    }

    @Override
    public boolean isFigureComplete()
    {
        return completion;
    }

    // Check the completion conditions and snap end point to origin
    private void checkCompletion()
    {
        int size = points.size(); // get list size
        // Get last point
        double endX = points.get(size - 2);
        double endY = points.get(size - 1);
        // Check if it close enough to origin
        if (Math.abs(endX - points.get(ORIGIN_X_INDEX)) <= 5
                && Math.abs(endY - points.get(ORIGIN_Y_INDEX)) <= 5)
        {
            // if yes - snap it to origin
            // remove its old values
            points.remove(size - 1);
            points.remove(size - 2);
            // and replace them with an origin values
            points.add(points.get(ORIGIN_X_INDEX));
            points.add(points.get(ORIGIN_Y_INDEX));
            completion = true; // mark as complete
        }
    }

}
