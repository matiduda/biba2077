package fifa;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

// TODO: Add round system (cooldown time and players' position reset) 

public class Logic {

    private long timer;

    private final double PANEL_X = -330;
    private final double PANEL_Y = 0;

    private final double PANEL_SCALE = 0.5;

    private final int GAME_TIME_MULTIPLIER = 60; // Frames per second

    // -------- Elements --------

    private Player p1;
    private Player p2;
    private Player p3;

    private Text gameTime;
    private int minutes;
    private int seconds;

    private Rectangle colorPlayer1;
    private Rectangle colorPlayer2;
    private Rectangle colorPlayer3;

    private Label idPlayer1;
    private Label idPlayer2;
    private Label idPlayer3;

    private Text scorePlayer1;
    private Text scorePlayer2;
    private Text scorePlayer3;

    private AnimationTimer animation;
    
    private GridPane panel;

    public Logic(double roundTime, Elements list, Player p1, Player p2, Player p3) {
        loadGameBar();
        loadElements();

        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

        setColorsAndNames();
        resetScores();

        list.add(panel);

        setEventsAndTimers();
        reset();
    }
    
    private void reset() {
        timer = 0;
    }

    private void resetScores() {
        scorePlayer1.setText(String.valueOf(p1.score));
        scorePlayer2.setText(String.valueOf(p2.score));
        scorePlayer3.setText(String.valueOf(p3.score));
    }

    // ---------------- Events and timers ----------------

    private void setEventsAndTimers() {
        animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameTime.setText(String.format("%02d:%02d", minutes, seconds));

                if(timer++ % GAME_TIME_MULTIPLIER == 0) {
                    // A second has passed in game time
                    seconds++;
                }
                if(seconds == GAME_TIME_MULTIPLIER) {
                    seconds = 0;
                    minutes++;
                }
            }
        };
        animation.start();
    }

    // ---------------- FXML Object loading ----------------

    private void loadGameBar() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gamebar/bar.fxml"));
        try {
            panel = (GridPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel.setLayoutX(PANEL_X);
        panel.setLayoutY(PANEL_Y);

        panel.setScaleX(PANEL_SCALE);
        panel.setScaleY(PANEL_SCALE);
    }

    private void loadElements() {
        colorPlayer1 = (Rectangle)panel.lookup("#colorPlayer1");
        colorPlayer2 = (Rectangle)panel.lookup("#colorPlayer2");
        colorPlayer3 = (Rectangle)panel.lookup("#colorPlayer3");
        
        idPlayer1 = (Label) panel.lookup("#idPlayer1");;
        idPlayer2 = (Label) panel.lookup("#idPlayer1");;
        idPlayer3 = (Label) panel.lookup("#idPlayer1");;
        
        scorePlayer1 = (Text) panel.lookup("#scorePlayer1");
        scorePlayer2 = (Text) panel.lookup("#scorePlayer2");
        scorePlayer3 = (Text) panel.lookup("#scorePlayer3");
        gameTime = (Text) panel.lookup("#gameTime");
    }

    private void setColorsAndNames() {
        colorPlayer1.setFill(p1.ball.getFill());
        colorPlayer2.setFill(p2.ball.getFill());
        colorPlayer3.setFill(p3.ball.getFill());
        
        idPlayer1.setText(p1.name);
        idPlayer2.setText(p2.name);
        idPlayer3.setText(p3.name);
    }

    // --------------------------

}
