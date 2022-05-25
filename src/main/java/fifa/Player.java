package fifa;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import static fifa.App.HEIGHT;

public class Player extends Ball {

    // Object behaviour attributes
    int maxSpeed = 3;
    double airResistance = 0.07;
    double acceleration = 0.2;
    double mass = 10;
    //

    public boolean none, running, goNorth, goSouth, goEast, goWest;
    public double size = HEIGHT/26;

    private static int playerIndex;

    public Player(Elements list, Stage s, Scene scene, int startX, int startY, Paint color, Circle field) {
        IS_BALL = false;

        vel = new Vector(0, 0);
        pos = new Vector(startX, startY);

        ball = new Circle(0f, 0f, size);
        ball.setFill(color);
        ball.setStroke(Color.BLACK);
        list.add(ball);

        setCenter(field);

        System.out.println("Player " + playerIndex);

        playerIndex += 1;

        KeyboardInput.add(this);

        setEventsAndTimers(scene, s);
    }


    protected void setEventsAndTimers(Scene scene, Stage s) {

        // Add event handlers to the scene
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // Set boolean values for the players
                KeyCode code = event.getCode();
                KeyboardInput.setInputOnKeyPressed(code);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                KeyboardInput.setInputOnKeyReleased(code);
            }
        });

        s.setScene(scene);
        s.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

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

                // if (running) { vel.x *= shiftMultiplier; vel.y *= shiftMultiplier; }

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

                move(vel.x, vel.y);
                update();
            }
        };

        timer.start();
        return;
    }

    // Make player behaviour different from ball behaviour (mostly to fix collision at circle border)

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

}
