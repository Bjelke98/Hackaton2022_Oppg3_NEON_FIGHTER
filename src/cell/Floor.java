package cell;

import game.Settings;
import javafx.scene.image.Image;

/**
 * Gulv med tilfeldig roterende sprite
 */
public class Floor extends Cell{
    private static final Image IMAGE = new Image("Floor1.png");
    public Floor(Point point) {
        super(point, IMAGE);
        setRotate(getRandomRotate());
    }
}
