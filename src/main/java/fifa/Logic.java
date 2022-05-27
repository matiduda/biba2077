package fifa;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

public class Logic {

    private Label gameTime;
    private long timeInSeconds = 0;

    private final double timerX = 20;
    private final double timerY = 30;

    // private GridPane panel;

    public Logic(double roundTime, Elements list) {
        gameTime = new Label("00:00");
        gameTime.setLayoutX(timerX);
        gameTime.setLayoutY(timerY);
        gameTime.setFont(Font.font("Verdana", 30));

        BackgroundFill fill = new BackgroundFill(
                new RadialGradient(
                        0, 0, 0.5, 0.5, 0.5, true,
                        CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#FA432233")),
                        new Stop(1, Color.web("#00000033"))),
                CornerRadii.EMPTY, Insets.EMPTY);
        gameTime.setBackground(new Background(fill));
        list.add(gameTime);
        // list.add(panel);

        // loadGameBar();
        setEventsAndTimers();
    }

    // ---------------- Events and timers ----------------

    private void setEventsAndTimers() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timeInSeconds++;
                gameTime.setText(String.format("%02d:%02d", timeInSeconds / 3600, timeInSeconds / 60));
            }
        };
        timer.start();
    }

    // private void loadGameBar() {
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("/gamebar/bar.fxml"));
    //     try {
    //         panel = (GridPane) loader.load();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}
