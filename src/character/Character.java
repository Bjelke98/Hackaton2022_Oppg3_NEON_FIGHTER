package character;

import cell.Point;
import game.Settings;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import map.Map;
import miscs.EntityOptions;

public abstract class Character extends ImageView {

    private static final Duration MOVEMENT_INTERVAL = Duration.millis(20*Settings.SPEED_MULTIPLIER);

    private final Timeline MOVEMENT;

    protected final Map map;

    protected int hitPoints = 100;

    protected int lives = 1;

    EventHandler<ActionEvent> handleMovement = e-> update();

    protected Character(Image image, Map map, Point point){
        super(image);
        this.map = map;
        setX(point.sx());
        setY(point.sy());
        setFitWidth(Settings.CHARACTER_SIZE);
        setFitHeight(Settings.CHARACTER_SIZE);
        setViewport(EntityOptions.VIEWS[0]);
        MOVEMENT = new Timeline(new KeyFrame(MOVEMENT_INTERVAL, handleMovement));
        MOVEMENT.setCycleCount(Timeline.INDEFINITE);
        MOVEMENT.play();
    }

    protected abstract void update();
}
