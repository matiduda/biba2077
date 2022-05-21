package fifa;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import static java.lang.Math.sqrt;
import javafx.scene.paint.Color;

public class PlayField {
    
    public Polyline goal1;
    public Polyline goal2;
    public Polyline goal3;
    public Circle ground;
    public Polyline goal;
    public Polygon field;
    private double width;
    private double height;

    public PlayField(double width, double height) {
        this.width = width;
        this.height = height;

        goal1 = createGoal(1);
        goal2 = createGoal(2);
        goal3 = createGoal(3);

        ground = new Circle(width/2,height/2,height/2, Color.SADDLEBROWN);

        field = new Polygon();
        field.getPoints().addAll(width/2 - height/2,height/2,
               width/2 - height/4, height/2 + (height/2 * sqrt(3))/2,
               width/2 + height/4, height/2 + (height/2 * sqrt(3))/2,
               width/2 + height/2, height/2,
               width/2 + height/4, height/2 - (height/2 * sqrt(3)/2),
               width/2 - height/4, height/2 - (height/2 * sqrt(3))/2);
        field.setFill(Color.LAWNGREEN);
        field.setStroke(Color.WHITE);
        field.setStrokeWidth(5);
    }

    private Polyline createGoal(int type){
        goal = new Polyline();
        switch (type) {
            case 1:
                goal.getPoints().addAll(midpointX(width/2 - height/2,midpointX(width/2 - height/2, width/2 - height/4))+height/20,  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40,
                        midpointX(width/2 - height/2,midpointX(width/2 - height/2, width/2 - height/4)),  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 - height/4,midpointX(width/2 - height/2, width/2 - height/4)),  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 - height/4,midpointX(width/2 - height/2, width/2 - height/4))+height/20,  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40);
                goal.setFill(Color.BLUE);
                goal.setStrokeWidth(5);
                break;
            case 2:
                goal.getPoints().addAll(midpointX(width/2 + height/2,midpointX(width/2 + height/2, width/2 + height/4))-height/20,  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40,
                        midpointX(width/2 + height/2,midpointX(width/2 + height/2, width/2 + height/4)),  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 + height/4,midpointX(width/2 + height/2, width/2 + height/4)),  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 + height/4,midpointX(width/2 + height/2, width/2 + height/4))-height/20,  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40);
                goal.setFill(Color.YELLOW);
                goal.setStrokeWidth(5);
                break;
            case 3:
                goal.getPoints().addAll(midpointX(width/2 + height/4,midpointX(width/2 + height/4, width/2 - height/4)),  height/2 + (height/2 * sqrt(3)/2) - height/20,
                        midpointX(width/2 + height/4,midpointX(width/2 + height/4, width/2 - height/4)),  height/2 + (height/2 * sqrt(3)/2),
                        midpointX(width/2 - height/4,midpointX(width/2 + height/4, width/2 - height/4)),  height/2 + (height/2 * sqrt(3)/2),
                        midpointX(width/2 - height/4,midpointX(width/2 + height/4, width/2 - height/4)),  height/2 + (height/2 * sqrt(3)/2) -height/20);
                goal.setFill(Color.RED);
                goal.setStrokeWidth(5);
                break;
        }
        return goal;
    }

    private double midpointX(double x1, double x2) {
       return (x1+x2)/2;
    }
    private double midpointY(double y1, double y2) {
        return (y1+y2)/2;
    }
}
