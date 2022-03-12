package character;

import cell.Point;
import javafx.scene.image.Image;
import map.Map;

public class PlayerOne extends Player{
    private static final Image IMAGE = new Image("Player1_3.png");
    public PlayerOne(Map map, Point point) {
        super(IMAGE, map, point);
        playerNumber = 1;
    }
}
