package fifa;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    // Confirure how the game starts
    boolean STARTS_FULLSCREEN = false;
    boolean IS_RESIZABLE = false;

    int width = 800;
    int height = 800;
    //

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Automatically called by Application.launch()
        
        // Add a Scene
        Group root = new Group();

        Scene scene = new Scene(root, width, height, Color.TRANSPARENT);

        Player player = new Player(stage, scene, 500, 500);

        root.getChildren().add(player.getPlayer());

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