package fifa;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

import static java.lang.Math.sqrt;

import javafx.scene.paint.Color;

public class PlayField {

    // TODO: Add lines to the field

    private enum GoalType {
        BLUE, YELLOW, RED
    }
    
    public static Polyline goalB;
    public static Polyline goalY;
    public static Polyline goalR;

    public Circle ground;
    public Polyline goal;
    public Polygon field;

    private double width;
    private double height;

    public PlayField(Elements list, double width, double height) {
        this.width = width;
        this.height = height;

        goalB = createGoal(GoalType.BLUE);
        goalY = createGoal(GoalType.YELLOW);
        goalR = createGoal(GoalType.RED);

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

        list.add(ground);
        list.add(field);
        list.add(goalB);
        list.add(goalY);
        list.add(goalR);

        // Add lines here using list.add(lineX);
    }

    private Polyline createGoal(GoalType type){
        goal = new Polyline();
        switch (type) {
            case BLUE:
                goal.getPoints().addAll(midpointX(width/2 - height/2,midpointX(width/2 - height/2, width/2 - height/4))+height/20,  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40,
                        midpointX(width/2 - height/2,midpointX(width/2 - height/2, width/2 - height/4)),  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 - height/4,midpointX(width/2 - height/2, width/2 - height/4)),  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 - height/4,midpointX(width/2 - height/2, width/2 - height/4))+height/20,  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40);
                goal.setFill(Color.BLUE);
                goal.setStrokeWidth(5);
                break;
            case YELLOW:
                goal.getPoints().addAll(midpointX(width/2 + height/2,midpointX(width/2 + height/2, width/2 + height/4))-height/20,  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40,
                        midpointX(width/2 + height/2,midpointX(width/2 + height/2, width/2 + height/4)),  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 + height/4,midpointX(width/2 + height/2, width/2 + height/4)),  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 + height/4,midpointX(width/2 + height/2, width/2 + height/4))-height/20,  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40);
                goal.setFill(Color.YELLOW);
                goal.setStrokeWidth(5);
                break;
            case RED:
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
