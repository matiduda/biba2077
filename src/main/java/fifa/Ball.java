package fifa;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

    // Object behaviour attributes
    int maxSpeed = 3;
    double airResistance = 0.02;
    double acceleration = 0.2;
    double mass = 10;
    //
    protected static Vector center; 
    protected double maxDistance;

    public boolean IS_BALL;

    public boolean lockX;
    public boolean lockY;

    public Vector pos;
    public Vector vel;

    final int size = 20;

    public Circle ball;

    public Ball() {};

    public Ball(Elements list, int startX, int startY, Circle field) {
        IS_BALL = true;
        vel = new Vector(0, 0);
        pos = new Vector(startX, startY);

        ball = new Circle(0f, 0f, size);
        ball.setFill(Color.WHITE);
        list.add(ball);

        setCenter(field);

        setEventsAndTimers();
    }

    protected void setEventsAndTimers() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(vel.x > 0) vel.x -= airResistance;
                if(vel.x < 0) vel.x += airResistance;
                if(vel.y > 0) vel.y -= airResistance;
                if(vel.y < 0) vel.y += airResistance;

                move(vel.x, vel.y);
                update();
            }
        };
        timer.start();
    }

    public void setCenter(Circle field) {
        center = new Vector(field.getCenterX(), field.getCenterY());
        maxDistance = field.getRadius() - ball.getRadius();
    }

    public void update() {
        ball.setCenterX(pos.x);
        ball.setCenterY(pos.y);
    }

    private void move(double dx, double dy) {
        Vector newPosX = new Vector(pos.x + dx, pos.y);
        Vector newPosY = new Vector(pos.x, pos.y + dy);

        double distX = Math.abs(CollisionDetection.getDistance(center, newPosX));
        double distY = Math.abs(CollisionDetection.getDistance(center, newPosY));

        double effect = 0.7;

        if(distX < maxDistance)
            pos.x = newPosX.x;
        else
            vel.x *= -effect;

        if(distY < maxDistance)
            pos.y = newPosY.y;
        else
            vel.y *= -effect;
    }

    public Vector getVelocity() {
        return vel;
    }
}