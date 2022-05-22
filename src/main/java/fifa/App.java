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

    final static double WIDTH = 720;
    final static double HEIGHT = 720;
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

        Goal goalBlue = new Goal(GoalType.BLUE);
        Goal goalYellow = new Goal(GoalType.YELLOW);
        Goal goalRed = new Goal(GoalType.RED);

        elm.add(goalRed.goal);
        elm.add(goalYellow.goal);
        elm.add(goalBlue.goal);


        Player player1 = new Player(elm, stage, scene, 200, 200, Color.BLUE);
        // Player player2 = new Player(elm, stage, scene, 200, 500, Color.RED);
        // Player player3 = new Player(elm, stage, scene, 600, 200, Color.GREEN);

        Ball ball = new Ball(elm, (int) WIDTH /2,(int) HEIGHT /2);

        CollisionDetection system = new CollisionDetection();

        ObservableList<Double> pts = goalRed.goal.getPoints();

        System.out.println(pts);


        // Slupki do systemu kolizji (nwm jak sa po ang)
        double slupekGrubosc = 20;
        double slupekOffset = 10;
        double slupekHeight = 100;

            Rectangle left = new Rectangle(pts.get(6) - slupekOffset, pts.get(7), slupekGrubosc, slupekHeight);
            left.setFill(Color.WHITE);
            left.setOpacity(0.2);
            elm.add(left);

            Rectangle right = new Rectangle(pts.get(0) - slupekOffset, pts.get(7), slupekGrubosc, slupekHeight);
            right.setFill(Color.WHITE);
            right.setOpacity(0.2);
            elm.add(right);

            system.addStatic(left);
            system.addStatic(right);
        //

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