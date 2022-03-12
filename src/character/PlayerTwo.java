package character;

import cell.Point;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import map.Map;

public class PlayerTwo extends Player{
    private static final Image IMAGE = new Image("Player2_3.png");
    public PlayerTwo(Map map, Point point) {
        super(IMAGE, map, point);
        keyBindings = new KeyCode[]{
                KeyCode.W,
                KeyCode.S,
                KeyCode.A,
                KeyCode.D,
                KeyCode.Z,
                KeyCode.X,
                KeyCode.C,
                KeyCode.V,
                KeyCode.B,
                KeyCode.N
        };
        playerNumber = 2;
    }
}
