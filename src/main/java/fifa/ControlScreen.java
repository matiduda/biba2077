package fifa;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControlScreen {
    public ControlScreen(Elements list) {
        Text textSter = new Text(App.WIDTH / 2 - App.WIDTH / 10, App.HEIGHT / 12, "Sterowanie");
        textSter.setFont(Font.font("Verdana", App.HEIGHT / 18));
        textSter.setFill(Color.BLACK);

        Image image = new Image(getClass().getResource("/images/keyboard.png").toString());
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0 + App.HEIGHT / 4);
        imageView.setFitHeight(App.HEIGHT / 1.5);
        imageView.setFitWidth(App.WIDTH - 20);

        list.add(textSter);
        list.add(imageView);
    }
}
