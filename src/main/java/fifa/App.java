package fifa;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
        Elements elm = new Elements();
        
        new PlayField(elm, width, height);

        Player player1 = new Player(elm, stage, scene, 200, 200, Color.BLUE);
        // Player player2 = new Player(elm, stage, scene, 200, 500, Color.RED);
        // Player player3 = new Player(elm, stage, scene, 600, 200, Color.GREEN);

        Ball ball = new Ball(elm, (int) width/2,(int) height/2);

        CollisionDetection system = new CollisionDetection(PlayField.ground);

        ObservableList<Double> ptsR = PlayField.goalR.getPoints();

        // Slupki do systemu kolizji (nwm jak sa po ang)
        double slupekGrubosc = 20;
        double slupekHeight = 100;

        double R_offset_x = -10;
        double R_offset_y = 0;
        
            Rectangle leftR = new Rectangle(ptsR.get(6) + R_offset_x, ptsR.get(7) + R_offset_y, slupekGrubosc, slupekHeight);
            leftR.setFill(Color.WHITE);
            leftR.setOpacity(0.2);
            elm.add(leftR);

            Rectangle rightR = new Rectangle(ptsR.get(0) + R_offset_x, ptsR.get(7) + R_offset_y, slupekGrubosc, slupekHeight);
            rightR.setFill(Color.WHITE);
            rightR.setOpacity(0.2);
            elm.add(rightR);

        //

        system.addStatic(leftR);
        system.addStatic(rightR);
        // system.addStatic(rightG);
        // system.addStatic(leftY);
        // system.addStatic(rightY);

        system.addDynamic(ball);
        system.addDynamic(player1);

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