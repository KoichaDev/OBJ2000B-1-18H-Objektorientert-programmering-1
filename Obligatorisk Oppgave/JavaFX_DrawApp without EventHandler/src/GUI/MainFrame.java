package GUI;

import Figures.CircleFigure;
import Figures.Figure;
import Figures.LineFigure;
import Figures.PolygonFigure;
import Figures.RectangleFigure;
import java.util.ArrayList;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;

public class MainFrame extends BorderPane {

    // To avoid using magic numbers, lets replace them with named constants
    private final static int PREF_WIDTH = 800;
    private final static int PREF_HEIGHT = 800;

    // This GUI controls actually used in class methods and so stored as a field
    private final CheckBox cbLineRed;
    private final CheckBox cbLineGreen;
    private final CheckBox cbLineBlue;
    private final CheckBox cbFillRed;
    private final CheckBox cbFillGreen;
    private final CheckBox cbFillBlue;
    private final RadioButton rbLine;
    private final RadioButton rbRectangle;
    private final RadioButton rbCircle;
    private final RadioButton rbPoly;
    private final Pane canvasPane;

    // List to keep fully drawn figures
    private ArrayList<Figure> figures;
    private Figure tempFig; // Curently drawn figure
    private Shape tempShp; // Decorated shape of currently drawn figure
    private boolean drawingOn = false; // Drawing flag

    private FigureType currType; // type of figure selected via radio buttons in enum

    MainFrame()
    {
        super(); // call ancestor constructor
        this.setPrefSize(PREF_WIDTH, PREF_HEIGHT); // resize the frame

        // Now let's create controls andd fill them in the pane
        // Top
        HBox topbox = new HBox(); // Top is organised horisontaly, so in HBox
        topbox.setAlignment(Pos.CENTER); // Align elements in the center of the box
        topbox.setFillHeight(false); // Only Center Pane is allowed to fill
        topbox.setPrefSize(100, 50);
        this.setTop(topbox); // Add it to the bordered pane

        // A group for radio buttons, which makes sure, that only one button is selected in the same time
        ToggleGroup figuresGroup = new ToggleGroup();

        // Named handler to be added to each radio button
        EventHandler<ActionEvent> RadioHandler = (ActionEvent e)
                ->
                {
                    handleRadioButtonChange(); // Calls class method on radio button selection change
        };

        // Here we create four radio buttons
        rbLine = new RadioButton();
        rbLine.setSelected(true); // This one is selected by default
        rbLine.setText("Straight lines"); // Caption
        rbLine.setToggleGroup(figuresGroup); // Add it to the toggle group
        rbLine.setPadding(new Insets(0, 5, 0, 5)); // To properly space buttons on the pane
        rbLine.setOnAction(RadioHandler);
        topbox.getChildren().add(rbLine); // Add it to HBox

        currType = FigureType.LINE;

        rbRectangle = new RadioButton();
        rbRectangle.setSelected(false);
        rbRectangle.setText("Rectangles");
        rbRectangle.setToggleGroup(figuresGroup);
        rbRectangle.setPadding(new Insets(0, 5, 0, 5));
        rbRectangle.setOnAction(RadioHandler);
        topbox.getChildren().add(rbRectangle);

        rbCircle = new RadioButton();
        rbCircle.setSelected(false);
        rbCircle.setText("Circles");
        rbCircle.setToggleGroup(figuresGroup);
        rbCircle.setPadding(new Insets(0, 5, 0, 5));
        rbCircle.setOnAction(RadioHandler);
        topbox.getChildren().add(rbCircle);

        rbPoly = new RadioButton();
        rbPoly.setSelected(false);
        rbPoly.setText("Polygons");
        rbPoly.setToggleGroup(figuresGroup);
        rbPoly.setPadding(new Insets(0, 5, 0, 5));
        rbPoly.setOnAction(RadioHandler);
        topbox.getChildren().add(rbPoly);

        // Bottom
        HBox bottombox = new HBox(); // Bottom is organised horisontaly, so in HBox
        bottombox.setAlignment(Pos.CENTER); // Align element in the center
        bottombox.setFillHeight(false); // Only Center Pane is allowed to fill
        bottombox.setPrefSize(100, 50);
        this.setBottom(bottombox); // Add it to the bordered pane

        // Here we setup a clear figures button
        Button btClear = new Button();
        btClear.setText("Clear drawings"); // Caption
        btClear.setOnAction((ActionEvent e) // On click handler
                ->
                {
                    handleClearButtonAction(); // Calls corresponding class method
        });
        bottombox.getChildren().add(btClear); // Add it to the HBox

        // Left
        VBox leftbox = new VBox();  // Left is organised vertically, so in VBox
        leftbox.setAlignment(Pos.CENTER_LEFT); // We want elements to be aligned by left side, and so use this aligment
        leftbox.setFillWidth(false);  // Only Center Pane is allowed to fill
        this.setLeft(leftbox);   // Add it to the bordered pane

        Label leftLbl = new Label("Fill color"); // Label with text
        leftbox.setMargin(leftLbl, new Insets(0, 20, 0, 20)); // Use margin to make space between the label and VBox border
        leftbox.getChildren().add(leftLbl); //  Add it to VBox

        // Named handler to be added to each checkbox
        EventHandler<ActionEvent> DecorHandler = (ActionEvent e)
                ->
                {
                    handleDecorChange(); // Calls class method on checkbox selection change
        };

        // Here we create three checkboex
        cbFillRed = new CheckBox();
        cbFillRed.setSelected(false); // None is selected by default
        cbFillRed.setText("Red"); // Caption
        cbFillRed.setOnAction(DecorHandler); // Handler
        leftbox.setMargin(cbFillRed, new Insets(0, 20, 0, 20)); // Margin to make space, same as for Label
        leftbox.getChildren().add(cbFillRed); // Add it to the VBox

        cbFillGreen = new CheckBox();
        cbFillGreen.setSelected(false);
        cbFillGreen.setText("Green");
        cbFillGreen.setOnAction(DecorHandler);
        leftbox.setMargin(cbFillGreen, new Insets(0, 20, 0, 20));
        leftbox.getChildren().add(cbFillGreen);

        cbFillBlue = new CheckBox();
        cbFillBlue.setSelected(false);
        cbFillBlue.setText("Blue");
        cbFillBlue.setOnAction(DecorHandler);
        leftbox.setMargin(cbFillBlue, new Insets(0, 20, 0, 20));
        leftbox.getChildren().add(cbFillBlue);

        // Right
        // Almost identical to the left
        VBox rightbox = new VBox();
        rightbox.setAlignment(Pos.CENTER_LEFT);
        rightbox.setFillWidth(false);
        this.setRight(rightbox);

        Label rightLbl = new Label("Line color");
        rightbox.setMargin(rightLbl, new Insets(0, 20, 0, 20));
        rightbox.getChildren().add(rightLbl);

        cbLineRed = new CheckBox();
        cbLineRed.setSelected(false);
        cbLineRed.setText("Red");
        cbLineRed.setOnAction(DecorHandler);
        rightbox.setMargin(cbLineRed, new Insets(0, 20, 0, 20));
        rightbox.getChildren().add(cbLineRed);

        cbLineGreen = new CheckBox();
        cbLineGreen.setSelected(false);
        cbLineGreen.setText("Green");
        cbLineGreen.setOnAction(DecorHandler);

        rightbox.setMargin(cbLineGreen, new Insets(0, 20, 0, 20));
        rightbox.getChildren().add(cbLineGreen);

        cbLineBlue = new CheckBox();
        cbLineBlue.setSelected(false);
        cbLineBlue.setText("Blue");
        cbLineBlue.setOnAction(DecorHandler);

        rightbox.setMargin(cbLineBlue, new Insets(0, 20, 0, 20));
        rightbox.getChildren().add(cbLineBlue);

        //Center
        canvasPane = new Pane();
        canvasPane.setMinSize(0, 0); // to make sure pane shrinks as much as it can
        canvasPane.setStyle("-fx-border-color: BLACK;"); // Add border via CSS
        // create a clipping rectangle, since Pane don't do it by self.
        final Rectangle outputClip = new Rectangle();
        outputClip.setWidth(canvasPane.getWidth());
        outputClip.setHeight(canvasPane.getHeight());
        // Add it to the pane
        canvasPane.setClip(outputClip);
        // Bind its size to the pane size
        canvasPane.layoutBoundsProperty().addListener((ov, oldValue, newValue)
                ->
                {
                    outputClip.setWidth(newValue.getWidth());
                    outputClip.setHeight(newValue.getHeight());
        }
        );

        // Add mouse events handler
        // Each calls corresponding class method
        canvasPane.setOnMousePressed(
                (MouseEvent e)
                ->
                {
                    handleMousePress(e);
        }
        );
        canvasPane.setOnMouseReleased(
                (MouseEvent e)
                ->
                {
                    handleMouseRelease(e);
        }
        );
        canvasPane.setOnMouseDragged(
                (MouseEvent e)
                ->
                {
                    handleMouseDrag(e);
        }
        );

        this.setCenter(canvasPane); // Add pane to the frame center

        //Everything else
        figures = new ArrayList<>(); // initialise our list
    }

// Clear button press handler
    public void handleClearButtonAction()
    {
        drawingOn = false; // end current drawing, if any
        tempFig = null;
        tempShp = null;
        // Clear permanent list
        figures.clear();
        // And clear all children from canvas pane
        canvasPane.getChildren().clear();
    }

    // checkboxes state changed
    public void handleDecorChange()
    {
        if (drawingOn) // if we are in the drawing mode
        {
            decorateShape(tempShp); // update current figure shape decoration withnew fill/stroke
        }
    }

    // Converts radio buttons state into enum
    // And breaks the drawing mode, when radio buttons state changes
    public void handleRadioButtonChange()
    {
        // Iterate over radio buttons state.

        if (rbLine.isSelected()) // If button is selected now
        {
            if (currType != FigureType.LINE) // And it wasn't selected befpre (enum show different type)
            {
                currType = FigureType.LINE; // Update to a new type
                forceFigureCompletion(); // And break drawing mode
            }
        } else if (rbRectangle.isSelected()) // Same for other radio buttons
        {
            if (currType != FigureType.RECTANGLE)
            {
                currType = FigureType.RECTANGLE;
                forceFigureCompletion();
            }
        } else if (rbCircle.isSelected())
        {
            if (currType != FigureType.CIRCLE)
            {
                currType = FigureType.CIRCLE;
                forceFigureCompletion();
            }
        } else if (rbPoly.isSelected())
        {
            if (currType != FigureType.POLY)
            {
                currType = FigureType.POLY;
                forceFigureCompletion();
            };
        } else // This shouldn't be reachable
        {
            currType = FigureType.NONE; // but for the sake of inner consistency, in case of user somehow deselecting all radiobuttons
        }
    }

    // Attempts to break drawing process
    private void forceFigureCompletion()
    {
        if (drawingOn) // if called during the drawing process
        {
            tempFig.completeFigure(); // Force current figure to complete self
            updateTemporaryShape(); // and redraw its shape
            drawingOn = false; // end drawing process
            figures.add(tempFig); // move figure into the permanent list
            tempFig = null;
            tempShp = null;
        }
    }

// Mouse press handler
    public void handleMousePress(MouseEvent mouseEvent)
    {
        // Make sure that we only process left button presses
        if (mouseEvent.getButton() != MouseButton.PRIMARY)
        {
            return;
        }

        // if we aren't drawing
        if (!drawingOn)
        {
            // then we create a new figure
            tempFig = getSelectedFigure(mouseEvent.getX(), mouseEvent.getY());
            if (tempFig != null) // if it was succesfully created
            {
                tempShp = tempFig.getShape(); // we extract current shape
                decorateShape(tempShp); // add to it our decorations
                canvasPane.getChildren().add(tempShp); // draw it on the pane
                drawingOn = true; // and enter the drawing mode
            }
        } else // if we ARE drawing
        {
            // We add a new point to the current figure in coordinates of a mouse button press
            tempFig.addNextPoint(mouseEvent.getX(), mouseEvent.getY());
            updateTemporaryShape(); // and redraw its shape
        }
    }

    // Mouse release handler
    public void handleMouseRelease(MouseEvent mouseEvent)
    {
        // Make sure that we only process left button presses
        if (mouseEvent.getButton() != MouseButton.PRIMARY)
        {
            return;
        }

        if (drawingOn) // only do something in the drawing mode
        {
            tempFig.completePoint(); // assure figure that movement is complete
            updateTemporaryShape(); // redraw its shape after that
            if (tempFig.isFigureComplete()) // if figure is done
            {
                drawingOn = false; // end the drawing mode
                figures.add(tempFig); // move figure into permanent list
                tempFig = null;
                tempShp = null;
            }
        }
    }

    // Mouse drag handler
    public void handleMouseDrag(MouseEvent mouseEvent)
    {
        // Here the check is done differently, since we want to react on multiple buttons drag events
        // if one of the button is left. The system would designate a as current button the last one pressed
        // so we just check for the left button state
        if (!mouseEvent.isPrimaryButtonDown())
        {
            return;
        }

        if (drawingOn) // / only do something in the drawing mode
        {
            // Move last point of current figure into the new coordinates
            tempFig.moveLastPoint(mouseEvent.getX(), mouseEvent.getY());
            updateTemporaryShape(); // redraw its shape after that
        }
    }

    // Figure factory.
    private Figure getSelectedFigure(double originX, double originY)
    {
        // Iterate over radio buttons state.
        // For one that is selected return a created figure
        switch (currType)
        {
            case LINE:
                return new LineFigure(originX, originY, originX, originY);
            case RECTANGLE:
                return new RectangleFigure(originX, originY, originX, originY);
            case CIRCLE:
                return new CircleFigure(originX, originY, originX, originY);
            case POLY:
                return new PolygonFigure(originX, originY, originX, originY);
        }
        return null; // This shouldn't be reached in normal situation, since
        // user can't deselect all buttons. But for the sake clarity we would return null
        // if he somehow does
    }

    // Transform checkbox states in color object for fill
    private Color getFillColor()
    {
        double r;
        if (cbFillRed.isSelected())  // for each checkbox assume its component to 0.0 or 1.0
        {
            r = 1.0;
        } else
        {
            r = 0.0;
        }
        double g;
        if (cbFillGreen.isSelected())  // for each checkbox assume its component to 0.0 or 1.0
        {
            g = 1.0;
        } else
        {
            g = 0.0;
        }
        double b;
        if (cbFillBlue.isSelected())  // for each checkbox assume its component to 0.0 or 1.0
        {
            b = 1.0;
        } else
        {
            b = 0.0;
        }
        return new Color(r, g, b, 1.0);// combine three components into the color (alpha is always 1.0)
    }

    // Transform checkbox states in color object for line
    private Color getLineColor()
    {
        double r;
        if (cbLineRed.isSelected())  // for each checkbox assume its component to 0.0 or 1.0
        {
            r = 1.0;
        } else
        {
            r = 0.0;
        }
        double g;
        if (cbLineGreen.isSelected())  // for each checkbox assume its component to 0.0 or 1.0
        {
            g = 1.0;
        } else
        {
            g = 0.0;
        }
        double b;
        if (cbLineBlue.isSelected())  // for each checkbox assume its component to 0.0 or 1.0
        {
            b = 1.0;
        } else
        {
            b = 0.0;
        }
        return new Color(r, g, b, 1.0);// combine three components into the color (alpha is always 1.0)
    }

    // Here we add GUI specific decorations to the shape created by figure
    private void decorateShape(Shape orig)
    {
        orig.setFill(getFillColor()); // Fill color
        orig.setStroke(getLineColor()); // Stroke color
        orig.setStrokeWidth(4); // Stroke width
        orig.setStrokeLineCap(StrokeLineCap.ROUND); // Stroke type at the line end
        // And last one so that shape don't interfere with mouse events
        orig.setMouseTransparent(true);
    }

    // Here we update the shape associated with current figure
    private void updateTemporaryShape()
    {
        canvasPane.getChildren().remove(tempShp); // first we remove old shape
        tempShp = tempFig.getShape(); // than extract updated one
        decorateShape(tempShp); // decorate it
        canvasPane.getChildren().add(tempShp); // and put it on to the pane

    }

    enum FigureType { // Possible figure types
        LINE, RECTANGLE, CIRCLE, POLY, NONE
    }
}
