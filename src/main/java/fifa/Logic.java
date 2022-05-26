package fifa;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

public class Logic {

    private Label gameTime;
    private int timeInSeconds = 0;
    private double roundTime;

    private final double timerX = 20;
    private final double timerY = 30;
    
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
            CornerRadii.EMPTY, Insets.EMPTY
    );
        gameTime.setBackground(new Background(fill));
        list.add(gameTime);

        setEventsAndTimers();
    }

    // ---------------- Events and timers ----------------

    private void setEventsAndTimers() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                timeInSeconds++;

                int milis = timeInSeconds % 60;
                int seconds = timeInSeconds / 60;
                int minutes = timeInSeconds / 3600;
                gameTime.setText(String.format("%02d:%02d:%02d", minutes, seconds, milis));
            }
        };
        timer.start();
    }
}
