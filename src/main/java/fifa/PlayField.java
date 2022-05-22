package fifa;

import javafx.scene.shape.*;

import static fifa.App.HEIGHT;
import static fifa.App.WIDTH;
import static java.lang.Math.sqrt;

import javafx.scene.paint.Color;

public class PlayField {

    public Circle ground;
    public Polygon field;


    public PlayField(Elements list) {
        Circle ground = new Circle(WIDTH /2, HEIGHT /2, HEIGHT /2, Color.GRAY);

        Polygon field = new Polygon();
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

        Arc leftArc = new Arc(midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4), midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2),WIDTH/8,WIDTH/8,240,WIDTH/6.7);
        leftArc.setFill(Color.TRANSPARENT);
        leftArc.setStroke(Color.WHITE);
        leftArc.setStrokeWidth(2);

        Arc rightArc = new Arc(midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4), midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2),WIDTH/8,WIDTH/8,120,WIDTH/6.7);
        rightArc.setFill(Color.TRANSPARENT);
        rightArc.setStroke(Color.WHITE);
        rightArc.setStrokeWidth(2);

        Arc downArc = new Arc(midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4), HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2),WIDTH/8,WIDTH/8,0,WIDTH/6.7);
        downArc.setFill(Color.TRANSPARENT);
        downArc.setStroke(Color.WHITE);
        downArc.setStrokeWidth(2);

        list.add(ground);
        list.add(field);
        list.add(circle);
        list.add(leftLine);
        list.add(rightLine);
        list.add(downLine);
        list.add(leftArc);
        list.add(rightArc);
        list.add(downArc);

        // Add lines here using list.add(lineX);
    }

    private double midpointX(double x1, double x2) {
        return (x1+x2)/2;
    }
    private double midpointY(double y1, double y2) {
        return (y1+y2)/2;
    }

}
