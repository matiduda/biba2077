package fifa;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {
    private Stage stage;
    private Parent root;
    
    private String css = this.getClass().getResource("/styling/style.css").toExternalForm();

    private Game game;

    public void startGame(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        TextField name1 = (TextField)stage.getScene().lookup("#namePicker1");
        TextField name2 = (TextField)stage.getScene().lookup("#namePicker2");
        TextField name3 = (TextField)stage.getScene().lookup("#namePicker3");
        
        ColorPicker color1 = (ColorPicker)stage.getScene().lookup("#colorPicker1");
        ColorPicker color2 = (ColorPicker)stage.getScene().lookup("#colorPicker2");
        ColorPicker color3 = (ColorPicker)stage.getScene().lookup("#colorPicker3");

        String[] names = new String[3];
        Color[] colors = new Color[3];

        names[0] = name1.getText();
        names[1] = name2.getText();
        names[2] = name3.getText();

        colors[0] = color1.getValue();
        colors[1] = color2.getValue();
        colors[2] = color3.getValue();

        game = new Game(stage, names, colors);
    }

    public void switchToGameOptions(ActionEvent event) throws IOException {
        setScene("/menu/gameOptions.fxml", event);
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        setScene("/menu/start.fxml", event);
    }

    public void switchToHelp(ActionEvent event) throws IOException {
        setScene("/menu/help.fxml", event);
    }

    private void setScene(String fxml, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxml));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.getScene().getStylesheets().add(css);
        stage.show();
    }
    
    public void handleCloseButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void resumeGame(ActionEvent event) {
        game.resumeGame();
    }

    public void resetGame(ActionEvent event) {
        game.pauseResetGame();
    }
}
