package fifa;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;

public class Ball {

    // Object behaviour attributes
    int maxSpeed = 7;
    double airResistance = 0.07;
    double acceleration = 0.2;
    double mass = 1.2;
    //

    public IntVec pos;
    public Vector vel;

    final int size = 20;

    public Circle ball;

    public Ball() {};

    public Ball(int startX, int startY) {
        vel = new Vector(0, 0);
        pos = new IntVec(startX, startY);

        ball = new Circle();
        ball.setCenterX(startX);
        ball.setCenterY(startY);
        ball.setRadius(size);

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

                pos.x += vel.x;
                pos.y += vel.y;

                update();
            }
        };
        timer.start();
    }

    public void move(double dx, double dy) {
        pos.x += dx;
        pos.y += dy;
    }

    public void update() {
        ball.setCenterX(pos.x);
        ball.setCenterY(pos.y);
    }

    public Vector getVelocity() {
        return vel;
    }
}