package miscs;

import game.Settings;
import javafx.geometry.Rectangle2D;

public class EntityOptions {
    public static final Rectangle2D[] VIEWS = new Rectangle2D[]{
            new Rectangle2D(0, 0, Settings.SPRITE_SIZE, Settings.SPRITE_SIZE),
            new Rectangle2D(Settings.SPRITE_SIZE, 0, Settings.SPRITE_SIZE, Settings.SPRITE_SIZE),
            new Rectangle2D(Settings.SPRITE_SIZE*2, 0, Settings.SPRITE_SIZE, Settings.SPRITE_SIZE),
            new Rectangle2D(Settings.SPRITE_SIZE*3, 0, Settings.SPRITE_SIZE, Settings.SPRITE_SIZE),
            new Rectangle2D(Settings.SPRITE_SIZE*4, 0, Settings.SPRITE_SIZE, Settings.SPRITE_SIZE)
    };
}
