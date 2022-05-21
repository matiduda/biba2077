package fifa;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import static java.lang.Math.sqrt;


/**
 * JavaFX App
 */
public class App extends Application {

    // Confirure how the game starts
    boolean STARTS_FULLSCREEN = false;
    boolean IS_RESIZABLE = false;

    final static double width = 1200;
    final static double height = 800;
    //

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();

        Scene scene = new Scene(root, width, height, Color.TRANSPARENT);

        Circle ground = new Circle(width/2,height/2,height/2, Color.SADDLEBROWN);

        Polygon field = new Polygon();
        field.getPoints().addAll(width/2 - height/2,height/2,
               width/2 - height/4, height/2 + (height/2 * sqrt(3))/2,
               width/2 + height/4, height/2 + (height/2 * sqrt(3))/2,
               width/2 + height/2, height/2,
               width/2 + height/4, height/2 - (height/2 * sqrt(3)/2),
               width/2 - height/4, height/2 - (height/2 * sqrt(3))/2);
        field.setFill(Color.LAWNGREEN);
        field.setStroke(Color.WHITE);
        field.setStrokeWidth(5);

        Polyline goal1 = createGoal(1);
        Polyline goal2 = createGoal(2);
        Polyline goal3 = createGoal(3);
        

        Player player = new Player(stage, scene, 50, 50);
        Ball ball = new Ball((int) width/2,(int) height/2);
        new CollisionDetection(ball, player);
        root.getChildren().addAll(ground, field, goal1, goal2, goal3, player.ball, ball.ball);

        prepareGameWindow(stage, scene, "/icon/icon.png");
    }

    private Polyline createGoal(int type){
        Polyline goal = new Polyline();
        switch (type) {
            case 1:
                goal.getPoints().addAll(midpointX(width/2 - height/2,midpointX(width/2 - height/2, width/2 - height/4))+height/20,  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40,
                        midpointX(width/2 - height/2,midpointX(width/2 - height/2, width/2 - height/4)),  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 - height/4,midpointX(width/2 - height/2, width/2 - height/4)),  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 - height/4,midpointX(width/2 - height/2, width/2 - height/4))+height/20,  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40);
                goal.setFill(Color.BLUE);
                goal.setStrokeWidth(5);
                break;
            case 2:
                goal.getPoints().addAll(midpointX(width/2 + height/2,midpointX(width/2 + height/2, width/2 + height/4))-height/20,  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40,
                        midpointX(width/2 + height/2,midpointX(width/2 + height/2, width/2 + height/4)),  midpointY(height/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 + height/4,midpointX(width/2 + height/2, width/2 + height/4)),  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2)),
                        midpointX(width/2 + height/4,midpointX(width/2 + height/2, width/2 + height/4))-height/20,  midpointY(height/2 - (height/2 * sqrt(3))/2, midpointY(height/2,height/2 - (height/2 * sqrt(3))/2))+height/40);
                goal.setFill(Color.YELLOW);
                goal.setStrokeWidth(5);
                break;
            case 3:
                goal.getPoints().addAll(midpointX(width/2 + height/4,midpointX(width/2 + height/4, width/2 - height/4)),  height/2 + (height/2 * sqrt(3)/2) - height/20,
                        midpointX(width/2 + height/4,midpointX(width/2 + height/4, width/2 - height/4)),  height/2 + (height/2 * sqrt(3)/2),
                        midpointX(width/2 - height/4,midpointX(width/2 + height/4, width/2 - height/4)),  height/2 + (height/2 * sqrt(3)/2),
                        midpointX(width/2 - height/4,midpointX(width/2 + height/4, width/2 - height/4)),  height/2 + (height/2 * sqrt(3)/2) -height/20);
                goal.setFill(Color.RED);
                goal.setStrokeWidth(5);
                break;
        }
        return goal;
    }

    private double midpointX(double x1, double x2) {
       return (x1+x2)/2;
    }
    private double midpointY(double y1, double y2) {
        return (y1+y2)/2;
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