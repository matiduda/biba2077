package fifa;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    // Confirure how the game starts

    boolean STARTS_FULLSCREEN = false;
    boolean IS_RESIZABLE = false;
    boolean SHOW_HITBOX = true;

    static double WIDTH = 1280;
    static double HEIGHT = 720;

    public final static Color PLAYERS[] = { Color.BLUE, Color.RED, Color.YELLOW };
    public final static String NAMES[] = { "NIEB", "CZER", "ZOLC" };

    //

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        if (STARTS_FULLSCREEN) {
            WIDTH = javafx.stage.Screen.getPrimary().getBounds().getWidth();
            HEIGHT = javafx.stage.Screen.getPrimary().getBounds().getHeight();
        }

        new Sound();

        Group root = new Group();

        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.TRANSPARENT);
        Elements elm = new Elements();
        Elements con = new Elements(); // TODO: To ma być w osobnej klasie

        new PlayField(elm);
        new ControlScreen(con); // TODO: To ma być w osobnej klasie odpowiedzialnej za controlsy

        final double PLAYER_DISTANCE_FROM_CENTER = 200;

        Vector posDefault = new Vector(PlayField.ground.getCenterX(), PlayField.ground.getCenterY());

        Vector pos1 = new Vector(0, PLAYER_DISTANCE_FROM_CENTER);
        Vector pos2 = CollisionDetection.rotate(pos1, 2 * Math.PI / 3);
        Vector pos3 = CollisionDetection.rotate(pos1, -2 * Math.PI / 3);

        pos1.add(posDefault);
        pos2.add(posDefault);
        pos3.add(posDefault);

        Player player1 = new Player(elm, stage, scene, pos1, PLAYERS[0],
                PlayField.ground, NAMES[0]);
        Player player2 = new Player(elm, stage, scene, pos2, PLAYERS[1],
                PlayField.ground, NAMES[1]);
        Player player3 = new Player(elm, stage, scene, pos3, PLAYERS[2],
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

        // root.getChildren().addAll(con.getElements()); // Po chuj dodawać to do pola
        // gry??

        prepareGameWindow(stage, scene, "/icon/icon.png");
    }

    private void prepareGameWindow(Stage stage, Scene scene, String pathToIcon) {

        Image icon = new Image(getClass().getResource(pathToIcon).toString());

        stage.getIcons().add(icon);
        stage.setTitle("BIBA 2077");

        stage.setWidth(scene.getWidth());
        stage.setHeight(scene.getHeight());

        stage.setResizable(IS_RESIZABLE);
        stage.setFullScreen(STARTS_FULLSCREEN);

        stage.setFullScreenExitHint("Press 'q' to exit full screen mode");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
    }

}