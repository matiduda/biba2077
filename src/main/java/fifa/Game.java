package fifa;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {

    boolean SHOW_HITBOX = false;
    private Scene scene;

    private Player[] players = new Player[3];
    private Vector[] startingPositions = new Vector[3];

    private CollisionDetection CDsystem;
    private Ball ball;
    private KeyboardInput kbInput;
    private Logic gameLogic;

    public Game(Stage stage, String[] names, Color[] colors) {

        Group root = new Group();

        Elements elm = new Elements();
        PlayField field = new PlayField(elm, colors);

        final double PLAYER_DISTANCE_FROM_CENTER = App.HEIGHT / 3;

        Vector posDefault = new Vector(field.ground.getCenterX(), field.ground.getCenterY());

        startingPositions[0] = new Vector(0, PLAYER_DISTANCE_FROM_CENTER);
        startingPositions[1] = Utils.rotate(startingPositions[0], 2 * Math.PI / 3);
        startingPositions[2] = Utils.rotate(startingPositions[0], -2 * Math.PI / 3);

        startingPositions[0].add(posDefault);
        startingPositions[1].add(posDefault);
        startingPositions[2].add(posDefault);

        for(int i = 0; i < 3; i++)
            players[i] = new Player(elm, startingPositions[i], colors[i],
            field.ground, names[i]);


        ball = new Ball(elm, posDefault, field.ground);

        Hitboxes hitboxes = new Hitboxes(field);

        if (SHOW_HITBOX)
            hitboxes.showHitboxes(elm.getElements());

        gameLogic = new Logic(10, elm, players[0], players[1], players[2], ball);
        kbInput = new KeyboardInput(gameLogic, players);

        CDsystem = new CollisionDetection(hitboxes.border, gameLogic, hitboxes);

        CDsystem.addStatic(hitboxes.getElementsCollection());

        CDsystem.addDynamic(ball);
        CDsystem.addDynamic(players[0]);
        CDsystem.addDynamic(players[1]);
        CDsystem.addDynamic(players[2]);


        root.getChildren().addAll(elm.getElements());

        this.scene = stage.getScene();
        stage.getScene().setRoot(root);
        stage.show();

        setGameLoop();
    }

    private void setGameLoop() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // Set boolean values for the players
                KeyCode code = event.getCode();
                kbInput.setInputOnKeyPressed(code);
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                kbInput.setInputOnKeyReleased(code);
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                for(Player p: players) {
                    p.update();
                }

                CDsystem.collisionChecks();
                gameLogic.resolveLogic();
                ball.update();
            }
        };

        timer.start();
        return;
    }

    public void pauseResetGame() {

    }

    public void resumeGame() {

    }
}
