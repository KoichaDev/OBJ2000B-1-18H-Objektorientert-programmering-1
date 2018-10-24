package drawtriangle;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class DrawTriangle extends Application {

    Group root;

    @Override

    public void start(Stage primaryStage) {

        AnchorPane anchorPane = new AnchorPane();
        root = new Group();
        anchorPane.setOnMouseDragged(mouseHandler);
        anchorPane.setOnMousePressed(mouseHandler);
        anchorPane.setOnMouseReleased(mouseHandler);


        anchorPane.getChildren().add(root);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(anchorPane,400, 400, Color.ALICEBLUE));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    double x[] = new double[3];
    double y[] = new double[3];
    int count = 0;
    boolean drawShape = true;
    Polygon triangle;
    List<Double> values = new ArrayList<Double>();
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                if (drawShape) {
                    x[count] = mouseEvent.getX();
                    y[count] = mouseEvent.getY();
                    System.out.println("X:" + mouseEvent.getX());
                    System.out.println("Y:" + mouseEvent.getY());
                }
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                if (drawShape) {
                    triangle = new Polygon();
                    values.add(x[count]);
                    values.add(y[count]);
                    count++;
                    triangle.getPoints().addAll(values);

                    triangle.setStroke(Color.FORESTGREEN);
                    triangle.setStrokeWidth(4);
                    triangle.setStrokeLineCap(StrokeLineCap.ROUND);
                    triangle.setFill(Color.CORNSILK.deriveColor(0, 1.2, 1, 0.6));
                    root.getChildren().clear();
                    root.getChildren().add(triangle);
                    //root.getChildren().addAll(createControlAnchorsFor(triangle.getPoints()));
                    
                    if (count == 2) {
                        drawShape = false;
                        count = 0;
                        calculateAngle();
                        
                    }

                }

            }

        }

    };

    double getLineDistance(double x1, double y1, double x2, double y2) {

        double s1 = Math.pow((x2 - x1), 2);
        double s2 = Math.pow((y2 - y1), 2);
        double s3 = Math.sqrt(s1 + s2);
        return s3;
    }

    

    int angels[] = new int[3];

    public void calculateAngle() {

        double x1 = triangle.getPoints().get(0);
        double y1 = triangle.getPoints().get(1);
        double x2 = triangle.getPoints().get(2);
        double y2 = triangle.getPoints().get(3);
        double x3 = triangle.getPoints().get(4);
        double y3 = triangle.getPoints().get(5);
        
        double a = getLineDistance(x1, y1, x2, y2);
        double b = getLineDistance(x2, y2, x3, y3);
        double c = getLineDistance(x3, y3, x1, y1);


        double pi = Math.PI;
        double angleA = Math.acos((b * b + c * c - a * a) / (2.0 * b * c)) * (180.0 / pi);
        double angleB = Math.acos((a * a + c * c - b * b) / (2.0 * a * c)) * (180.0 / pi);
        double angleC = (180.0 - angleA - angleB);

        System.out.println("=================================");
        System.out.println("angleA:" + angleA);
        System.out.println("angleB:" + angleB);
        System.out.println("angleC:" + angleC);

        angels[0] = (int) Math.round(angleA);
        angels[2] = (int) Math.round(angleB);
        angels[1] = (int) Math.round(angleC);

    }

}