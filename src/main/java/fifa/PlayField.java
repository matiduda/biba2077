package fifa;

import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

import static java.lang.Math.sqrt;

import javafx.scene.paint.Color;


public class PlayField {

    private enum GoalType { BLUE, YELLOW, RED };
    
    public static Polyline goalB;
    public static Polyline goalY;
    public static Polyline goalR;

    public static Circle ground;
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

        ground = new Circle(width /2, height /2, height /2, Color.GRAY);

        field = new Polygon();
        field.getPoints().addAll(width /2 - height /2, height /2,
                width /2 - height /4, height /2 + (height /2 * sqrt(3))/2,
                width /2 + height /4, height /2 + (height /2 * sqrt(3))/2,
                width /2 + height /2, height /2,
                width /2 + height /4, height /2 - (height /2 * sqrt(3)/2),
                width /2 - height /4, height /2 - (height /2 * sqrt(3))/2);
        field.setFill(Color.web("#008000",1.0));
        field.setStroke(Color.WHITE);
        field.setStrokeWidth(5);

        Circle circle = new Circle(width /2, height /2, height/20, Color.TRANSPARENT);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(2);

        Line leftLine = new Line(width/2, height/2, midpointX(width /2 - height /2, width /2 - height /4), midpointY(height /2, height /2 - (height /2 * sqrt(3))/2));
        Line rightLine = new Line(width/2, height/2, midpointX(width /2 + height /2, width /2 + height /4), midpointY(height /2, height /2 - (height /2 * sqrt(3))/2));
        Line downLine = new Line(width/2, height/2, midpointX(width /2 + height /4, width /2 - height /4), height /2 + (height /2 * sqrt(3)/2));
        leftLine.setStroke(Color.WHITE);
        leftLine.setStrokeWidth(2);
        rightLine.setStroke(Color.WHITE);
        rightLine.setStrokeWidth(2);
        downLine.setStroke(Color.WHITE);
        downLine.setStrokeWidth(2);

        int ARCLENGTH = 180;

        Arc leftArc = new Arc(midpointX(width /2 - height /2, width /2 - height /4), midpointY(height /2, height /2 - (height /2 * sqrt(3))/2),width/8,width/8,240,width/6.7);
        leftArc.setFill(Color.TRANSPARENT);
        leftArc.setStroke(Color.WHITE);
        leftArc.setStrokeWidth(2);
        leftArc.setLength(ARCLENGTH);

        Arc rightArc = new Arc(midpointX(width /2 + height /2, width /2 + height /4), midpointY(height /2, height /2 - (height /2 * sqrt(3))/2),width/8,width/8,120,width/6.7);
        rightArc.setFill(Color.TRANSPARENT);
        rightArc.setStroke(Color.WHITE);
        rightArc.setStrokeWidth(2);
        rightArc.setLength(ARCLENGTH);

        Arc downArc = new Arc(midpointX(width /2 + height /4, width /2 - height /4), height /2 + (height /2 * sqrt(3)/2),width/8,width/8,0,width/6.7);
        downArc.setFill(Color.TRANSPARENT);
        downArc.setStroke(Color.WHITE);
        downArc.setStrokeWidth(2);
        downArc.setLength(ARCLENGTH);


        list.add(ground);
        list.add(field);
        
        list.add(circle);
        list.add(leftLine);
        list.add(rightLine);
        list.add(downLine);
        list.add(leftArc);
        list.add(rightArc);
        list.add(downArc);
        
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
