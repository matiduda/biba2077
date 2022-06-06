package fifa;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game {
    // Confirure how the game starts
    boolean SHOW_HITBOX = false;

    static Color[] colors;

    public Game(Stage stage, String[] names, Color[] colors) {
        Sound.menuStop();
        Game.colors = colors;

        if (App.STARTS_FULLSCREEN) {
            App.WIDTH = javafx.stage.Screen.getPrimary().getBounds().getWidth();
            App.HEIGHT = javafx.stage.Screen.getPrimary().getBounds().getHeight();
        }

        Group root = new Group();

        Elements elm = new Elements();
        new PlayField(elm);

        final double PLAYER_DISTANCE_FROM_CENTER = App.HEIGHT / 3;

        Vector posDefault = new Vector(PlayField.ground.getCenterX(), PlayField.ground.getCenterY());

        Vector pos1 = new Vector(0, PLAYER_DISTANCE_FROM_CENTER);
        Vector pos2 = CollisionDetection.rotate(pos1, 2 * Math.PI / 3);
        Vector pos3 = CollisionDetection.rotate(pos1, -2 * Math.PI / 3);

        pos1.add(posDefault);
        pos2.add(posDefault);
        pos3.add(posDefault);

        Player player1 = new Player(elm, stage.getScene(), pos1, colors[0],
                PlayField.ground, names[0]);
        Player player2 = new Player(elm, stage.getScene(), pos2, colors[1],
                PlayField.ground, names[1]);
        Player player3 = new Player(elm, stage.getScene(), pos3, colors[2],
                PlayField.ground, names[2]);

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

        stage.getScene().setRoot(root);
        stage.show();
    }
}
