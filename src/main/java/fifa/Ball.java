package fifa;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

    private static final double KICK_VOLUME = 0.7;
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
    public boolean lockMovement = false;

    public Vector pos;
    public Vector vel;

    protected int startX, startY;

    final int size = 20;
    private final double centerOffset = 6d;

    public Circle ball;

    public String name = "ball";
    protected boolean canShoot;

    public boolean makeShootable = false;
    protected Vector startPosition;

    public Ball() {
    };

    public Ball(Elements list, Vector startPosition, Circle field) {
        IS_BALL = true;

        pos = new Vector(startPosition.x, startPosition.y);
        vel = new Vector(0, 0);
        this.startPosition = startPosition;

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

    protected void move(double dx, double dy) {

        if (lockMovement)
            return;

        Vector newPosX = new Vector(pos.x + dx, pos.y);
        Vector newPosY = new Vector(pos.x, pos.y + dy);

        double distX = Math.abs(CollisionDetection.getDistance(center, newPosX));
        double distY = Math.abs(CollisionDetection.getDistance(center, newPosY));

        double effect = 0.7;

        if (distX < maxDistance)
            pos.x = newPosX.x;
        else
            vel.x *= -effect;

        if (distY < maxDistance)
            pos.y = newPosY.y;
        else
            vel.y *= -effect;
    }

    public void shoot(Vector from, double strength) {

        // Play shooting sound
        Sound.kick(KICK_VOLUME);

        // Create a new normalized vector from 'from' to ball 'pos'
        double Xcomponent = from.x - pos.x;
        double Ycomponent = from.y - pos.y;

        double length = Math.sqrt(Xcomponent * Xcomponent + Ycomponent * Ycomponent);

        Xcomponent /= length;
        Ycomponent /= length;

        vel.x = -Xcomponent * strength;
        vel.y = -Ycomponent * strength;
    }

    public boolean isShooting() {
        return false;
    }

    public void resetPos() {
        pos = new Vector(startPosition.x, startPosition.y);
        vel = new Vector(0, 0);
    }

    // --------- Single use methods ---------

    public void setCenter(Circle field) {
        center = new Vector(field.getCenterX(), field.getCenterY());
        maxDistance = field.getRadius() - ball.getRadius() - centerOffset;
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