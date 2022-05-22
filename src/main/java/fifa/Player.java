package fifa;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class Player extends Ball {

    // Object behaviour attributes
    int maxSpeed = 3;
    double airResistance = 0.07;
    double acceleration = 0.2;
    double mass = 10;
    //

    boolean none, running, goNorth, goSouth, goEast, goWest;
    public int size = 27;

    public Player(Elements list, Stage s, Scene scene, int startX, int startY, Paint color) {
        IS_BALL = false;

        vel = new Vector(0, 0);
        pos = new Vector(startX, startY);

        ball = new Circle(0f, 0f, size);
        ball.setFill(color);
        list.add(ball);
        
        setEventsAndTimers(scene, s);
    }

    protected void setEventsAndTimers(Scene scene, Stage s) {

        // Add event handlers to the scene
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goNorth = true;
                        none = false;
                        break;
                    case DOWN:
                        goSouth = true;
                        none = false;
                        break;
                    case LEFT:
                        goWest = true;
                        none = false;
                        break;
                    case RIGHT:
                        goEast = true;
                        none = false;
                        break;
                    case SHIFT:
                        running = true;
                        none = false;
                        break;
                    default:
                        none = true;
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goNorth = false;
                        none = true;
                        break;
                    case DOWN:
                        goSouth = false;
                        none = true;
                        break;
                    case LEFT:
                        goWest = false;
                        none = true;
                        break;
                    case RIGHT:
                        goEast = false;
                        none = true;
                        break;
                    case SHIFT:
                        running = false;
                        none = true;
                        break;
                    default:
                        break;
                }
            }
        });

        
        s.setScene(scene);
        s.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                if(Math.abs(vel.y) < maxSpeed) {

                    if (goNorth) vel.y -= acceleration;
                    if (goSouth) vel.y += acceleration;
                }
                if(Math.abs(vel.x) < maxSpeed) {

                    if (goEast)  vel.x += acceleration;
                    if (goWest)  vel.x -= acceleration;
                }

                // if (running) { vel.x *= shiftMultiplier; vel.y *= shiftMultiplier; }

                if(none) {
                    if(vel.x > 0) vel.x -= airResistance;
                    if(vel.x < 0) vel.x += airResistance;
                    if(vel.y > 0) vel.y -= airResistance;
                    if(vel.y < 0) vel.y += airResistance;
                }

                move(vel.x, vel.y);
                update();
            }
        };

        timer.start();
        return;
    }

}
