package powerup;

import cell.Point;
import character.Player;
import game.Settings;
import javafx.scene.image.ImageView;

public abstract class PowerUp extends ImageView {
    public static final int countTypes = 3;
    protected PowerUp(Point point){
        setX(point.sx());
        setY(point.sy());
        setFitWidth(Settings.CELL_SIZE);
        setFitHeight(Settings.CELL_SIZE);
    }
    public abstract void effect(Player player);
}
