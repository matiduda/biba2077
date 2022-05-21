package fifa;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;

import javafx.stage.Stage;


import java.util.Collection;
import java.util.LinkedList;


public class App extends Application {

    // Confirure how the game starts
    boolean STARTS_FULLSCREEN = false;
    boolean IS_RESIZABLE = false;

    final static double width = 720;
    final static double height = 720;
    //

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();

        Scene scene = new Scene(root, width, height, Color.TRANSPARENT);

        PlayField field = new PlayField(width, height);

        Player player = new Player(stage, scene, 50, 50);
        Ball ball = new Ball((int) width/2,(int) height/2);
        new CollisionDetection(ball, player);
        
        Collection<Node> elements = new LinkedList<Node>();
        elements.add(field.ground);
        elements.add(field.field);
        elements.add(field.goal1);
        elements.add(field.goal2);
        elements.add(field.goal3);
        elements.add(player.ball);
        elements.add(ball.ball);

        root.getChildren().addAll(elements);

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