package Figures;

// This abstract class provides basis for all figures, which are defined by
// exactly two points: be it two ends of a line or two opposite corner of a bounding box
public abstract class TwoPointsFigure implements Figure {

    // Coordinates pairs fot both origin (fixed) and a second(movable) points
    protected double originX, originY;
    protected double secondPointX, secondPointY;
    // This flag is set up when second point of the figure is guaranteed to not be moving again
    private boolean editingComplete;

    // We add both of the points of the figure during creation
    // So we ignore all attempts to add another one.
    @Override
    public void addNextPoint(double x, double y)
    {
    }

    // Checks if the points is still allowed to be moved, if yes
    // udpates the second point coordinates accordingly
    @Override
    public void moveLastPoint(double x, double y)
    {
        if (!editingComplete)
        {
            secondPointX = x;
            secondPointY = y;
        }
    }

    // Two points figure are complete at creation
    @Override
    public boolean isFigureComplete()
    {
        return true;
    }

    // Set up flag, after caller gives us a gurantee
    @Override
    public void completePoint()
    {
        editingComplete = true;
    }

    // Obviously if we complete the figure drawing, than we also complete a second point moving
    @Override
    public void completeFigure()
    {
        editingComplete = true;
    }

    // Helper method for a quick points initialisation
    protected final void initPoints(double startX, double startY, double endX, double endY)
    {
        originX = startX;
        originY = startY;
        secondPointX = endX;
        secondPointY = endY;
    }

}
