package fifa;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Logic {

    private long timer;

    private final double PANEL_X = -330;
    private final double PANEL_Y = 0;

    private final double PANEL_SCALE = 0.5;

    private final static int GAME_TIME_MULTIPLIER = 60; // Frames per second

    private final static int AFTER_GOAL_DELAY_IN_SEC = 5;
    private final static int START_ROUND_FREEZE_TIME_IN_SEC = 3;

    // -------- Elements --------

    private static Player p1;
    private static Player p3;
    private static Player p2;

    private static Ball b;

    private Text gameTime;
    private int minutes;
    private int seconds;

    private Rectangle colorPlayer1;
    private Rectangle colorPlayer2;
    private Rectangle colorPlayer3;

    private Label idPlayer1;
    private Label idPlayer2;
    private Label idPlayer3;

    private static Text scorePlayer1;
    private static Text scorePlayer2;
    private static Text scorePlayer3;

    private AnimationTimer animation;

    private GridPane panel;

    // ----- Game stats -----

    private static int currentRound;
    private static int nextRoundDelay;
    private static int startFreeze;
    private static boolean wait;

    public Logic(double roundTime, Elements list, Player p1, Player p2, Player p3, Ball b) {
        loadGameBar();
        loadElements();

        Logic.p1 = p1;
        Logic.p2 = p2;
        Logic.p3 = p3;
        Logic.b = b;

        setColorsAndNames();
        resetScores();

        list.add(panel);

        setEventsAndTimers();
        reset();
    }

    private void reset() {
        timer = 0;
        currentRound = 0;
        wait = false;
        startFreeze = GAME_TIME_MULTIPLIER * START_ROUND_FREEZE_TIME_IN_SEC;
        lockPlayers();
    }

    public static void goalDetected(int angle) {

        // Check if the round is already over
        if (nextRoundDelay > 0)
            return;

        nextRoundDelay = GAME_TIME_MULTIPLIER * AFTER_GOAL_DELAY_IN_SEC;

        // Finish round

        currentRound++;

        updateScores(angle);

        wait = true;
    }

    private void lockPlayers() {
        p1.lockMovement = true;
        p2.lockMovement = true;
        p3.lockMovement = true;
    }

    private void unlockPlayers() {
        p1.lockMovement = false;
        p2.lockMovement = false;
        p3.lockMovement = false;
    }

    private void resetScores() {
        scorePlayer1.setText(String.valueOf(p1.score));
        scorePlayer2.setText(String.valueOf(p2.score));
        scorePlayer3.setText(String.valueOf(p3.score));
    }

    private static void updateScores(int player) {
        switch (player) {
            case 0:
                p1.score++;
                scorePlayer1.setText(String.valueOf(p1.score));

                break;
            case 1:
                p2.score++;
                scorePlayer2.setText(String.valueOf(p2.score));
                break;
            case 2:
                p3.score++;
                scorePlayer3.setText(String.valueOf(p3.score));
                break;
        }

        int minScore = p1.score;

        if (p2.score < minScore)
            minScore = p2.score;

        if (p3.score < minScore)
            minScore = p3.score;

        scorePlayer1.setFill(Color.RED);
        scorePlayer2.setFill(Color.RED);
        scorePlayer3.setFill(Color.RED);

        if (p1.score == minScore)
            scorePlayer1.setFill(Color.LIGHTGREEN);

        if (p2.score == minScore)
            scorePlayer2.setFill(Color.LIGHTGREEN);

        if (p3.score == minScore)
            scorePlayer3.setFill(Color.LIGHTGREEN);
    }

    // ---------------- Events and timers ----------------

    private void setEventsAndTimers() {
        animation = new AnimationTimer() {
            @Override
            public void handle(long now) {

                // Timer

                gameTime.setText(String.format("%02d:%02d", minutes, seconds));

                if (timer++ % GAME_TIME_MULTIPLIER == 0) {
                    // A second has passed in game time
                    seconds++;
                }

                if (seconds == GAME_TIME_MULTIPLIER) {
                    seconds = 0;
                    minutes++;
                }

                // Round logic

                if (nextRoundDelay > 0) {
                    nextRoundDelay--;
                    System.out.println("Next round in " + nextRoundDelay + " seconds...");
                } else if (wait) {
                    p1.resetPos();
                    p2.resetPos();
                    p3.resetPos();
                    b.resetPos();

                    wait = false;

                    startFreeze = GAME_TIME_MULTIPLIER * START_ROUND_FREEZE_TIME_IN_SEC;

                    lockPlayers();
                }

                if (startFreeze > 0) {
                    startFreeze--;
                    System.out.println("Starting round in " + startFreeze + " seconds...");
                } else {
                    unlockPlayers();
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
        colorPlayer1 = (Rectangle) panel.lookup("#colorPlayer1");
        colorPlayer2 = (Rectangle) panel.lookup("#colorPlayer2");
        colorPlayer3 = (Rectangle) panel.lookup("#colorPlayer3");

        idPlayer1 = (Label) panel.lookup("#idPlayer1");
        ;
        idPlayer2 = (Label) panel.lookup("#idPlayer2");
        ;
        idPlayer3 = (Label) panel.lookup("#idPlayer3");
        ;

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
