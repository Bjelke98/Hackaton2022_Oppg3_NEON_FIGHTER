package cell;

import game.Settings;
import javafx.scene.image.Image;

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
