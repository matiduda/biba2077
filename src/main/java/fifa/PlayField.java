package fifa;

import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;

import static fifa.App.HEIGHT;
import static fifa.App.WIDTH;
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

    public PlayField(Elements list) {

        goalB = createGoal(GoalType.BLUE);
        goalY = createGoal(GoalType.YELLOW);
        goalR = createGoal(GoalType.RED);

        ground = new Circle(WIDTH /2, HEIGHT /2, HEIGHT /2, Color.GRAY);

        field = new Polygon();
        field.getPoints().addAll(WIDTH /2 - HEIGHT /2, HEIGHT /2,
                WIDTH /2 - HEIGHT /4, HEIGHT /2 + (HEIGHT /2 * sqrt(3))/2,
                WIDTH /2 + HEIGHT /4, HEIGHT /2 + (HEIGHT /2 * sqrt(3))/2,
                WIDTH /2 + HEIGHT /2, HEIGHT /2,
                WIDTH /2 + HEIGHT /4, HEIGHT /2 - (HEIGHT /2 * sqrt(3)/2),
                WIDTH /2 - HEIGHT /4, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2);
        field.setFill(Color.web("#008000",1.0));
        field.setStroke(Color.WHITE);
        field.setStrokeWidth(5);

        Circle circle = new Circle(WIDTH /2, HEIGHT /2, HEIGHT/20, Color.TRANSPARENT);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(2);

        Line leftLine = new Line(WIDTH/2, HEIGHT/2, midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4), midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2));
        Line rightLine = new Line(WIDTH/2, HEIGHT/2, midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4), midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2));
        Line downLine = new Line(WIDTH/2, HEIGHT/2, midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4), HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2));
        leftLine.setStroke(Color.WHITE);
        leftLine.setStrokeWidth(2);
        rightLine.setStroke(Color.WHITE);
        rightLine.setStrokeWidth(2);
        downLine.setStroke(Color.WHITE);
        downLine.setStrokeWidth(2);

        int ARCLENGTH = 180;

        Arc leftArc = new Arc(midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4), midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2),HEIGHT/5,HEIGHT/5,240,WIDTH/6.7);
        leftArc.setFill(Color.TRANSPARENT);
        leftArc.setStroke(Color.WHITE);
        leftArc.setStrokeWidth(2);
        leftArc.setLength(ARCLENGTH);

        Arc rightArc = new Arc(midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4), midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2),HEIGHT/5,HEIGHT/5,120,WIDTH/6.7);
        rightArc.setFill(Color.TRANSPARENT);
        rightArc.setStroke(Color.WHITE);
        rightArc.setStrokeWidth(2);
        rightArc.setLength(ARCLENGTH);

        Arc downArc = new Arc(midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4), HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2),HEIGHT/5,HEIGHT/5,0,WIDTH/6.7);
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
                goal.getPoints().addAll(midpointX(WIDTH /2 - HEIGHT /2,midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4))+ HEIGHT /20,  midpointY(HEIGHT /2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2))+ HEIGHT /40,
                        midpointX(WIDTH /2 - HEIGHT /2,midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4)),  midpointY(HEIGHT /2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2)),
                        midpointX(WIDTH /2 - HEIGHT /4,midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4)),  midpointY(HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2)),
                        midpointX(WIDTH /2 - HEIGHT /4,midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4))+ HEIGHT /20,  midpointY(HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2))+ HEIGHT /40);
                goal.setFill(Color.BLUE);
                goal.setStrokeWidth(5);
                break;
            case YELLOW:
                goal.getPoints().addAll(midpointX(WIDTH /2 + HEIGHT /2,midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4))- HEIGHT /20,  midpointY(HEIGHT /2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2))+ HEIGHT /40,
                        midpointX(WIDTH /2 + HEIGHT /2,midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4)),  midpointY(HEIGHT /2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2)),
                        midpointX(WIDTH /2 + HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4)),  midpointY(HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2)),
                        midpointX(WIDTH /2 + HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4))- HEIGHT /20,  midpointY(HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2))+ HEIGHT /40);
                goal.setFill(Color.YELLOW);
                goal.setStrokeWidth(5);
                break;
            case RED:
                goal.getPoints().addAll(midpointX(WIDTH /2 + HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4)),  HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2) - HEIGHT /20,
                        midpointX(WIDTH /2 + HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4)),  HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2),
                        midpointX(WIDTH /2 - HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4)),  HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2),
                        midpointX(WIDTH /2 - HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4)),  HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2) - HEIGHT /20);
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
