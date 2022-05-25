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
    public boolean shooting = false;

    public Vector pos;
    public Vector vel;

    final int size = 20;

    public Circle ball;

    public String name = "ball";
    protected boolean canShoot;

    public boolean makeShootable = false;


    public Ball() {
    };

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

    public void update() {
        ball.setCenterX(pos.x);
        ball.setCenterY(pos.y);
    }

    private void move(double dx, double dy) {

            // If ball somehow escapes, spawn it at screen center
            
            if (CollisionDetection.getDistance(center, pos) > maxDistance) {

                pos.x = App.WIDTH / 2;
                pos.y = App.HEIGHT / 2;
                vel.x = 0;
                vel.y = 0;
            }
            //

        pos.x += dx;
        pos.y += dy;
    }

    public void shoot(Vector from, double strength) {
        vel.x *= strength;
        vel.y *= strength;
    }

    public boolean isShooting() {
        return false;
    }

    // --------- Single use methods ---------

    public void setCenter(Circle field) {
        center = new Vector(field.getCenterX(), field.getCenterY());
        maxDistance = field.getRadius() - ball.getRadius();
    }

    protected void setEventsAndTimers() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (vel.x > 0)
                    vel.x -= airResistance;
                if (vel.x < 0)
                    vel.x += airResistance;
                if (vel.y > 0)
                    vel.y -= airResistance;
                if (vel.y < 0)
                    vel.y += airResistance;

                move(vel.x, vel.y);
                update();
            }
        };
        timer.start();
    }

    public void makeShootable(boolean value) {
        makeShootable = value;
    }
}