package Figures;

import javafx.scene.shape.Shape;

// Figure interface through which controller access drawn figures
public interface Figure {

    // Return  shape representation of a figure to be drawn on pane
    public abstract Shape getShape();

    // Add another point to a figure at (x,y) in local coordinates
    public abstract void addNextPoint(double x, double y);

    // Move the last added point to (x,y) in local coordinates
    // Equivavlent to removing last added point and then adding a new one at (x,y)
    public abstract void moveLastPoint(double x, double y);

    // Returns true if no more points can be added to the figure
    public abstract boolean isFigureComplete();

    // Called when last added point is  guranteed to not be moving after
    public abstract void completePoint();

    // Caleed when no more points would be added after. Makes figure complete itself
    // on already added points (f. e. makes polygon close itself)
    public abstract void completeFigure();

}
