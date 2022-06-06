package fifa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class App extends Application {

    public static boolean STARTS_FULLSCREEN = false;
    public static boolean IS_RESIZABLE = false;

    public static double WIDTH = 1280;
    public static double HEIGHT = 720;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/menu/start.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        prepareGameWindow(stage, scene, "/icon/icon.png");
    }

    private void prepareGameWindow(Stage stage, Scene scene, String pathToIcon) {

        Image icon = new Image(getClass().getResource(pathToIcon).toString());

        stage.getIcons().add(icon);
        stage.setTitle("BIBA 2077");

        stage.setResizable(IS_RESIZABLE);
        stage.setFullScreen(STARTS_FULLSCREEN);

        stage.setFullScreenExitHint("Press 'q' to exit full screen mode");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
    }

}