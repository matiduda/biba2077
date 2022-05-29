package fifa;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


import static fifa.App.HEIGHT;
import static fifa.App.WIDTH;

public class ControlScreen {
    public ControlScreen(Elements list){
        Text textSter = new Text( WIDTH/2 - WIDTH/10, HEIGHT/12, "Sterowanie");
        textSter.setFont(Font.font ("Verdana", HEIGHT/18));
        textSter.setFill(Color.BLACK);

        Image image = new Image(getClass().getResource("/images/keyboard.png").toString());
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0 + HEIGHT/4);
        imageView.setFitHeight(HEIGHT/1.5);
        imageView.setFitWidth(WIDTH-20);

        list.add(textSter);
        list.add(imageView);
    }
}
