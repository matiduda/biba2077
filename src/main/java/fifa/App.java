package fifa;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {

    // Confirure how the game starts
    boolean STARTS_FULLSCREEN = false;
    boolean IS_RESIZABLE = false;

    final static double WIDTH = 720;
    final static double HEIGHT = 720;

    //final static double WIDTH = Screen.getPrimary().getBounds().getWidth();
    //final static double HEIGHT = Screen.getPrimary().getBounds().getHeight();
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

        Player player1 = new Player(elm, stage, scene, (int) WIDTH /2 - offset, (int) HEIGHT /2 - offset, Color.BLUE, PlayField.ground);
        Player player2 = new Player(elm, stage, scene, (int) WIDTH /2, (int) HEIGHT /2 + 2 * offset, Color.RED, PlayField.ground);
        Player player3 = new Player(elm, stage, scene, (int) WIDTH /2 + offset, (int) HEIGHT /2 + offset, Color.GREEN, PlayField.ground);

        new KeyboardInput();

        Ball ball = new Ball(elm, (int) WIDTH /2,(int) HEIGHT /2, PlayField.ground);

        CollisionDetection system = new CollisionDetection(PlayField.ground);

        ObservableList<Double> ptsR = PlayField.goalR.getPoints();

        // Slupki do systemu kolizji (nwm jak sa po ang)
        double slupekGrubosc = 20;
        double slupekHeight = 60;

        Color bramkaColor = Color.TRANSPARENT;

        double R_offset_x = -10;
        double R_offset_y = 0;
        
            Rectangle leftR = new Rectangle(ptsR.get(6) + R_offset_x, ptsR.get(7) + R_offset_y, slupekGrubosc, slupekHeight);
            leftR.setFill(bramkaColor);
            elm.add(leftR);

            Rectangle rightR = new Rectangle(ptsR.get(0) + R_offset_x, ptsR.get(7) + R_offset_y, slupekGrubosc, slupekHeight);
            rightR.setFill(bramkaColor);
            elm.add(rightR);

            Rectangle bottom = new Rectangle(ptsR.get(4), ptsR.get(5) + R_offset_y, ptsR.get(0) - ptsR.get(6), slupekGrubosc);
            bottom.setFill(bramkaColor);
            elm.add(bottom);

            
            system.addStatic(leftR);
            system.addStatic(rightR);
            system.addStatic(bottom);
            
            //
        
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

        stage.setFullScreenExitHint("Press 'q' to exit full screen mode");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
    }

}