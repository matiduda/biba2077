package com.fifa2077.fifa2077;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {

    @FXML
    private Button btnControls;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnQuit;

    @FXML
    void openControls(ActionEvent event) {
        ControlsWindow controlsWindow = new ControlsWindow();
        try {
            controlsWindow.start((Stage)((Node)event.getSource()).getScene().getWindow());
        } catch (Exception e){
            e.printStackTrace();
        };
    }

    @FXML
    void openGame(ActionEvent event) {
        FieldWindow fieldWindow = new FieldWindow();
        try {
            fieldWindow.start((Stage) ((Node) event.getSource()).getScene().getWindow());
        } catch (Exception e){
            e.printStackTrace();
        };
    }

    @FXML
    void quitGame(ActionEvent event) {
        Stage stage = (Stage) btnQuit.getScene().getWindow();
        stage.close();
    }
}