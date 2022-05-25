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

    static double WIDTH = 720;
    static double HEIGHT = 720;
    //

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();

        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.TRANSPARENT);
        Elements elm = new Elements();

        new PlayField(elm);

        int offset = 100;

        Player player1 = new Player(elm, stage, scene, (int) WIDTH / 2 - offset, (int) HEIGHT / 2 - offset, Color.BLUE,
                PlayField.ground);
        Player player2 = new Player(elm, stage, scene, (int) WIDTH / 2, (int) HEIGHT / 2 + 2 * offset, Color.RED,
                PlayField.ground);
        Player player3 = new Player(elm, stage, scene, (int) WIDTH / 2 + offset, (int) HEIGHT / 2 + offset, Color.GREEN,
                PlayField.ground);

        new KeyboardInput();

        Ball ball = new Ball(elm, (int) WIDTH / 2, (int) HEIGHT / 2, PlayField.ground);

        Hitboxes hitboxes = new Hitboxes();

        if (SHOW_HITBOX)
            hitboxes.showHitboxes(elm.getElements());

        CollisionDetection system = new CollisionDetection(hitboxes.border);

        system.addStatic(hitboxes.getElementsCollection());

        system.addDynamic(ball);
        system.addDynamic(player1);
        system.addDynamic(player2);
        system.addDynamic(player3);

        root.getChildren().addAll(elm.getElements());
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

        if (STARTS_FULLSCREEN) {
            WIDTH = javafx.stage.Screen.getPrimary().getBounds().getWidth();
            HEIGHT = javafx.stage.Screen.getPrimary().getBounds().getHeight();
        }

        stage.setFullScreenExitHint("Press 'q' to exit full screen mode");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
    }

}