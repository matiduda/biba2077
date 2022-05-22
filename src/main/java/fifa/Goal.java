package fifa;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

import static fifa.App.*;
import static fifa.App.HEIGHT;
import static fifa.App.WIDTH;
import static java.lang.Math.sqrt;

public class Goal {
    private final GoalType goalType;

    public Polyline goal;

    public Goal(GoalType goalType) {
        this.goalType = goalType;

        Polyline bramka = new Polyline();
        switch (goalType) {
            case BLUE:
                bramka.getPoints().addAll(midpointX(WIDTH /2 - HEIGHT /2,midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4))+ HEIGHT /20,  midpointY(HEIGHT /2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2))+ HEIGHT /40,
                        midpointX(WIDTH /2 - HEIGHT /2,midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4)),  midpointY(HEIGHT /2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2)),
                        midpointX(WIDTH /2 - HEIGHT /4,midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4)),  midpointY(HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2)),
                        midpointX(WIDTH /2 - HEIGHT /4,midpointX(WIDTH /2 - HEIGHT /2, WIDTH /2 - HEIGHT /4))+ HEIGHT /20,  midpointY(HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2))+ HEIGHT /40);
                bramka.setFill(Color.BLUE);
                bramka.setStrokeWidth(5);
                break;
            case YELLOW:
                bramka.getPoints().addAll(midpointX(WIDTH /2 + HEIGHT /2,midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4))- HEIGHT /20,  midpointY(HEIGHT /2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2))+ HEIGHT /40,
                        midpointX(WIDTH /2 + HEIGHT /2,midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4)),  midpointY(HEIGHT /2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2)),
                        midpointX(WIDTH /2 + HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4)),  midpointY(HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2)),
                        midpointX(WIDTH /2 + HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /2, WIDTH /2 + HEIGHT /4))- HEIGHT /20,  midpointY(HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2, midpointY(HEIGHT /2, HEIGHT /2 - (HEIGHT /2 * sqrt(3))/2))+ HEIGHT /40);
                bramka.setFill(Color.YELLOW);
                bramka.setStrokeWidth(5);
                break;
            case RED:
                bramka.getPoints().addAll(midpointX(WIDTH /2 + HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4)),  HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2) - HEIGHT /20,
                        midpointX(WIDTH /2 + HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4)),  HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2),
                        midpointX(WIDTH /2 - HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4)),  HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2),
                        midpointX(WIDTH /2 - HEIGHT /4,midpointX(WIDTH /2 + HEIGHT /4, WIDTH /2 - HEIGHT /4)),  HEIGHT /2 + (HEIGHT /2 * sqrt(3)/2) - HEIGHT /20);
                bramka.setFill(Color.RED);
                bramka.setStrokeWidth(5);
                break;
        }

        this.goal = bramka;

    }

    private double midpointX(double x1, double x2) {
        return (x1+x2)/2;
    }
    private double midpointY(double y1, double y2) {
        return (y1+y2)/2;
    }
}
