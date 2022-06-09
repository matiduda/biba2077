package fifa;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Player extends Ball {

    // Object behaviour attributes
    int maxSpeed = 3;
    double airResistance = 0.07;
    double acceleration = 0.2;
    double mass = 10;
    //

    public boolean none, shooting, goNorth, goSouth, goEast, goWest;

    public double size = 27;
    public int score = 0;

    public Player(Elements list, Vector startPos, Paint color, Circle field, String name) {
        IS_BALL = false;

        this.name = name;

        pos = new Vector(startPos.x, startPos.y);
        vel = new Vector(0, 0);
        this.startPosition = startPos;

        ball = new Circle(0f, 0f, size);
        ball.setFill(color);
        ball.setStroke(Color.BLACK);
        ball.setStrokeWidth(2.0);
        list.add(ball);

        setCenter(field);
    }

    // --------- Single use methods ---------

    public void update() {
        
        if (Math.abs(vel.y) < maxSpeed) {

            if (goNorth)
                vel.y -= acceleration;
            if (goSouth)
                vel.y += acceleration;
        }
        if (Math.abs(vel.x) < maxSpeed) {

            if (goEast)
                vel.x += acceleration;
            if (goWest)
                vel.x -= acceleration;
        }

        if (none) {
            if (vel.x > 0)
                vel.x -= airResistance;
            if (vel.x < 0)
                vel.x += airResistance;
            if (vel.y > 0)
                vel.y -= airResistance;
            if (vel.y < 0)
                vel.y += airResistance;
        }

        if (makeShootable) {
            ball.setStroke(Color.DARKORANGE);
        } else if (!shooting) {
            ball.setStroke(Color.BLACK);
        } else {
            ball.setStroke(Color.WHITE);
        }

        move(vel.x, vel.y);
        draw();
    }

    public boolean isShooting() {
        return shooting;
    }
}
