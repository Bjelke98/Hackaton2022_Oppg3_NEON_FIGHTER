package character;

import cell.Point;
import javafx.scene.image.Image;
import map.Map;

/**
 * Delte opp spillere i 2 forskjellige klasser for å enkelt kunne konfigurere sprite og forskjellige taster for å bevege karakteren.
 */
public class PlayerOne extends Player{
    public static final Image IMAGE = new Image("Player1_5.png");
    public PlayerOne(Map map, Point point) {
        super(IMAGE, map, point);
        playerNumber = 1;
    }
}
