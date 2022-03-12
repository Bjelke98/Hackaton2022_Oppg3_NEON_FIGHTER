package cell;

import javafx.scene.image.Image;

/**
 * Vegg med tilfeldig roterende sprite
 */
public class Wall extends Cell {
    private static final Image IMAGE = new Image("Wall1.png");
    public Wall(Point point) {
        super(point, IMAGE);
        setRotate(getRandomRotate());
    }

    @Override
    public boolean isCollidable() {
        return true;
    }
}
