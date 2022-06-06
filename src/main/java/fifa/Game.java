package fifa;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {
    // Confirure how the game starts
    boolean SHOW_HITBOX = false;

    public final static Color PLAYERS[] = { Color.BLUE, Color.RED, Color.YELLOW };
    public final static String NAMES[] = { "Wojta", "Mati", "Janek" };

    public Game(Stage stage) {

        if (App.STARTS_FULLSCREEN) {
            App.WIDTH = javafx.stage.Screen.getPrimary().getBounds().getWidth();
            App.HEIGHT = javafx.stage.Screen.getPrimary().getBounds().getHeight();
        }

        new Sound();

        Group root = new Group();
        
        Scene scene = new Scene(root, App.WIDTH, App.HEIGHT, Color.TRANSPARENT);
        Elements elm = new Elements();
        new PlayField(elm);

        final double PLAYER_DISTANCE_FROM_CENTER = 200;

        Vector posDefault = new Vector(PlayField.ground.getCenterX(), PlayField.ground.getCenterY());

        Vector pos1 = new Vector(0, PLAYER_DISTANCE_FROM_CENTER);
        Vector pos2 = CollisionDetection.rotate(pos1, 2 * Math.PI / 3);
        Vector pos3 = CollisionDetection.rotate(pos1, -2 * Math.PI / 3);

        pos1.add(posDefault);
        pos2.add(posDefault);
        pos3.add(posDefault);

        Player player1 = new Player(elm, scene, pos1, PLAYERS[0],
                PlayField.ground, NAMES[0]);
        Player player2 = new Player(elm, scene, pos2, PLAYERS[1],
                PlayField.ground, NAMES[1]);
        Player player3 = new Player(elm, scene, pos3, PLAYERS[2],
                PlayField.ground, NAMES[2]);

        new KeyboardInput();

        Ball ball = new Ball(elm, posDefault, PlayField.ground);

        Hitboxes hitboxes = new Hitboxes();

        if (SHOW_HITBOX)
            hitboxes.showHitboxes(elm.getElements());

        CollisionDetection system = new CollisionDetection(hitboxes.border);

        system.addStatic(hitboxes.getElementsCollection());

        system.addDynamic(ball);
        system.addDynamic(player1);
        system.addDynamic(player2);
        system.addDynamic(player3);

        new Logic(10, elm, player1, player2, player3, ball);

        root.getChildren().addAll(elm.getElements());

        stage.setScene(scene);
        stage.show();
    }
}
