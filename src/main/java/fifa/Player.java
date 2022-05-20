package fifa;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Player {

    double circleRadious = 20;

    int x, y;
    double xVel, yVel;

    boolean none, running, goNorth, goSouth, goEast, goWest;

    double shiftMultiplier = 1.2;
    int maxSpeed = 3;
    double airResistance = 0.07;
    double acceleration = 0.2;

    int W = 1280;
    int H = 720;

    Circle player;

    public Player(Stage s, Scene scene, int x, int y) {

        player = new Circle(x, y, circleRadious);

        this.x = x;
        this.y = y;

        xVel = 0;
        yVel = 0;

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
                
                if(Math.abs(yVel) < maxSpeed) {

                    if (goNorth) yVel -= acceleration;
                    if (goSouth) yVel += acceleration;
                }
                if(Math.abs(xVel) < maxSpeed) {

                    if (goEast)  xVel += acceleration;
                    if (goWest)  xVel -= acceleration;
                }

                // if (running) { xVel *= shiftMultiplier; yVel *= shiftMultiplier; }

                if(none) {
                    if(xVel > 0) xVel -= airResistance;
                    if(xVel < 0) xVel += airResistance;
                    if(yVel > 0) yVel -= airResistance;
                    if(yVel < 0) yVel += airResistance;
                }

                int dx = (int) xVel;
                int dy = (int) yVel;

                movePlayer(dx, dy);
            }
        };
        timer.start();
    }

    // Bound checking

    // private void updatePlaty(int dx, int dy) {
    //     movePlayer(dx, dy);
        // if (dx == 0 && dy == 0) return;
 
        // final double cx = player.getBoundsInLocal().getWidth()  / 2;
        // final double cy = player.getBoundsInLocal().getHeight() / 2;
 
        // double x = cx + player.getLayoutX() + dx;
        // double y = cy + player.getLayoutY() + dy;
 
        // movePlayerTo(x, y);
    // }
 
    // private void movePlayerTo(double x, double y) {
    //     final double cx = player.getBoundsInLocal().getWidth()  / 2;
    //     final double cy = player.getBoundsInLocal().getHeight() / 2;
 
    //     if (x - cx >= 0 &&
    //         x + cx <= W &&
    //         y - cy >= 0 &&
    //         y + cy <= H) {
    //         player.relocate(x - cx, y - cy);
    //     }
    // }

    private void movePlayer(int dx, int dy) {
        x += dx;
        y += dy;
        player.relocate(x, y);

    }

    public Circle getPlayer() {
        return player;
    }

}
