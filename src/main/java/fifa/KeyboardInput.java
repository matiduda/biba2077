package fifa;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;

public class KeyboardInput {

    private static ArrayList<Player> players = new ArrayList<Player>();

    private static KeyCode[][] keys = new KeyCode[3][5];

    public KeyboardInput() {
        for (int i = 0; i < players.size(); i++)
            fillKeycodeArray(i);
    }

    public static void setInputOnKeyPressed(KeyCode code) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            player.none = true;

            if (code == keys[i][0]) { // UP
                player.goNorth = true;
                player.none = false;
            }

            if (code == keys[i][1]) { // DOWN
                player.goSouth = true;
                player.none = false;
            }

            if (code == keys[i][2]) { // LEFT
                player.goWest = true;
                player.none = false;
            }

            if (code == keys[i][3]) { // RIGHT
                player.goEast = true;
                player.none = false;
            }

            if (code == keys[i][4]) { // SHOOT
                player.shooting = true;
                player.none = false;
            }
        }
    }

    public static void setInputOnKeyReleased(KeyCode code) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);

            player.none = true;

            if (code == keys[i][0]) { // UP
                player.goNorth = false;
                player.none = true;
            }

            if (code == keys[i][1]) { // DOWN
                player.goSouth = false;
                player.none = true;
            }

            if (code == keys[i][2]) { // LEFT
                player.goWest = false;
                player.none = true;
            }

            if (code == keys[i][3]) { // RIGHT
                player.goEast = false;
                player.none = true;
            }

            if (code == keys[i][4]) { // SHOOT
                player.shooting = false;
                player.none = true;
            }
        }
    }

    public static void add(Player p) {
        players.add(p);
    }

    private void fillKeycodeArray(int i) {
        switch (i) {
            case 0:
                keys[i][0] = KeyCode.UP;
                keys[i][1] = KeyCode.DOWN;
                keys[i][2] = KeyCode.LEFT;
                keys[i][3] = KeyCode.RIGHT;
                keys[i][4] = KeyCode.SHIFT;
                break;
            case 1:
                keys[i][0] = KeyCode.W;
                keys[i][1] = KeyCode.S;
                keys[i][2] = KeyCode.A;
                keys[i][3] = KeyCode.D;
                keys[i][4] = KeyCode.SPACE;
                break;
            case 2:
                keys[i][0] = KeyCode.I;
                keys[i][1] = KeyCode.K;
                keys[i][2] = KeyCode.J;
                keys[i][3] = KeyCode.L;
                keys[i][4] = KeyCode.ENTER;
                break;
        }
    }
}
