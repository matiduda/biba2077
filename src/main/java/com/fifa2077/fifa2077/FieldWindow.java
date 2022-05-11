package com.fifa2077.fifa2077;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FieldWindow extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        stage.getScene().setRoot(FXMLLoader.load(FieldWindow.class.getResource("main_game.fxml")));
    }

    public static void main(String[] args) {
        launch();
    }
}