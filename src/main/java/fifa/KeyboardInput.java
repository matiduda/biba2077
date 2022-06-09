package fifa;

import javafx.scene.input.KeyCode;

public class KeyboardInput {

    private Player[] players = new Player[3];

    private KeyCode[][] keys = new KeyCode[3][5];

    private Logic logic;

    public KeyboardInput(Logic logic, Player[] players) {
        this.logic = logic;
        this.players = players;
        for (int i = 0; i < 3; i++)
            fillKeycodeArray(i);
    }

    public void setInputOnKeyPressed(KeyCode code) {
        for (int i = 0; i < 3; i++) {

            players[i].none = true;

            if (code == keys[i][0]) { // UP
                players[i].goNorth = true;
                players[i].none = false;
            }

            if (code == keys[i][1]) { // DOWN
                players[i].goSouth = true;
                players[i].none = false;
            }

            if (code == keys[i][2]) { // LEFT
                players[i].goWest = true;
                players[i].none = false;
            }

            if (code == keys[i][3]) { // RIGHT
                players[i].goEast = true;
                players[i].none = false;
            }

            if (code == keys[i][4]) { // SHOOT
                players[i].shooting = true;
                players[i].none = false;
            }

            if(code == KeyCode.P)
                logic.displayPause();
        }
    }

    public void setInputOnKeyReleased(KeyCode code) {
        for (int i = 0; i < 3; i++) {


            players[i].none = true;

            if (code == keys[i][0]) { // UP
                players[i].goNorth = false;
                players[i].none = true;
            }

            if (code == keys[i][1]) { // DOWN
                players[i].goSouth = false;
                players[i].none = true;
            }

            if (code == keys[i][2]) { // LEFT
                players[i].goWest = false;
                players[i].none = true;
            }

            if (code == keys[i][3]) { // RIGHT
                players[i].goEast = false;
                players[i].none = true;
            }

            if (code == keys[i][4]) { // SHOOT
                players[i].shooting = false;
                players[i].none = true;
            }
        }
    }

    private void fillKeycodeArray(int i) {
        switch (i) {
            case 0:
                keys[i][0] = KeyCode.UP;
                keys[i][1] = KeyCode.DOWN;
                keys[i][2] = KeyCode.LEFT;
                keys[i][3] = KeyCode.RIGHT;
                keys[i][4] = KeyCode.ENTER;
                break;
            case 1:
                keys[i][0] = KeyCode.W;
                keys[i][1] = KeyCode.S;
                keys[i][2] = KeyCode.A;
                keys[i][3] = KeyCode.D;
                keys[i][4] = KeyCode.CONTROL;
                break;
            case 2:
                keys[i][0] = KeyCode.I;
                keys[i][1] = KeyCode.K;
                keys[i][2] = KeyCode.J;
                keys[i][3] = KeyCode.L;
                keys[i][4] = KeyCode.SPACE;
                break;
        }
    }
}
