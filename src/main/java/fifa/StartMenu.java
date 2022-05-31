package fifa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class StartMenu extends Application {

    public MediaPlayer setMenuMusic(String musicPath) {
        Media menuMusic = new Media(new File(musicPath).toURI().toString());
        MediaPlayer menuPlayer = new MediaPlayer(menuMusic);

        menuPlayer.setCycleCount(999999999);
        menuPlayer.setVolume(0.5);
        menuPlayer.setAutoPlay(true);

        return menuPlayer;
    }

    public Image setImg(String Path) throws FileNotFoundException {
        InputStream iconStram = new FileInputStream(Path);
        return new Image(iconStram);
    }

    public ImageView setUpImgView(String Path, double fitWidth) throws FileNotFoundException {
        ImageView imageView = new ImageView();
        imageView.setImage(setImg(Path));

        imageView.setFitWidth(fitWidth);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    @Override
    public void start(Stage primaryStage)
            throws Exception {
        // Paths for StartMenu
        String logoPath = "src/main/resources/logo/biba_white_shadow_transparent.png";
        String iconPath = "src/main/resources/icon/icon.png";
        String musicPath = "src/main/resources/sound/menu_music.mp3";

        // settin up music
        MediaPlayer menuPlayer = setMenuMusic(musicPath);

        // setting up icon img
        Image icon = setImg(iconPath);

        // setting up logo img
        ImageView imageView = setUpImgView(logoPath, App.WIDTH / 2);

        // setting up buttons
        Button button1 = new Button("START");
        Button button2 = new Button("CONTROL");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    showLoginScreen();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ToggleButton button3 = new ToggleButton("STOP MUSIC");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            int btn_counter = 0;
            @Override
            public void handle(ActionEvent event) {
                if(btn_counter % 2 == 1) {
                    menuPlayer.play();
                    button3.setText("STOP MUSIC");
                }
                else {
                    menuPlayer.stop();
                    button3.setText("PLAY MUSIC");
                }
                btn_counter++;
            }
        });
        button1.setMinSize(0.7 * App.WIDTH, 40);
        button2.setMinSize(0.7 * App.WIDTH, 40);
        button3.setMinSize(0.1 * App.WIDTH, 40);

        // setting up vbox
        VBox vbox = new VBox(20, imageView, button1, button2, button3);
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(Background.fill(Color.BROWN));

        // setting up main scene
        Scene scene = new Scene(vbox, App.WIDTH, App.HEIGHT);
        scene.setFill(Color.BROWN);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("BIBA 2077");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void showLoginScreen() throws FileNotFoundException {

        // path to keyboard img
        String keyboardPath = "src/main/resources/images/keyboard.png";

        // setting up keyboard img
        ImageView imageView = setUpImgView(keyboardPath, App.WIDTH * 0.8);

        //creating new pop-up stage
        Stage stage = new Stage();
        VBox box = new VBox(10, imageView);
        box.setBackground(Background.fill(Color.BROWN));
        box.setAlignment(Pos.CENTER);

        // I don't know if this button is needed :((
        Button btnLogin = new Button();
        btnLogin.setText("CLOSE");

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        box.getChildren().add(btnLogin);
        Scene scene = new Scene(box, App.WIDTH, App.HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
