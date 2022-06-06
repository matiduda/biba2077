package fifa;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void startGame(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        new Game(stage);
    }

    public void switchToGameOptions(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/menu/gameOptions.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/menu/start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHelp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/menu/help.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void resumeGame(ActionEvent event) {
        Logic.resumeGame();
    }

    public void resetGame(ActionEvent event) {
        Logic.pauseResetGame();
    }
}
