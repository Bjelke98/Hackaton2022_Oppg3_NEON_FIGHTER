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


/**
 * Super klasse for alle karakterer. Det var planlagt ikke vennlige programmstyrte fiender.
 */
public abstract class Character extends ImageView {

    private static final Duration MOVEMENT_INTERVAL = Duration.millis(20*Settings.SPEED_MULTIPLIER);

    public static final int START_HP = 100;

    private final Timeline MOVEMENT;

    protected final Map map;

    protected int hitPoints = START_HP;

    protected final Point START_POINT;

    protected int lives = 1;

    EventHandler<ActionEvent> handleMovement = e-> update();

    protected Character(Image image, Map map, Point point){
        super(image);
        this.map = map;
        this.START_POINT = point;
        setX(point.sx());
        setY(point.sy());
        setFitWidth(Settings.CHARACTER_SIZE);
        setFitHeight(Settings.CHARACTER_SIZE);
        setViewport(EntityOptions.VIEWS[0]);
        MOVEMENT = new Timeline(new KeyFrame(MOVEMENT_INTERVAL, handleMovement));
        MOVEMENT.setCycleCount(Timeline.INDEFINITE);
        MOVEMENT.play();
    }

    public int getHP(){
        return hitPoints;
    }
    public void resetHP(){
        hitPoints = START_HP;
    }
    public void antiHP(){
        hitPoints = Math.max(10, hitPoints-10);
    }
    public void witchHP(){
        hitPoints = 10;
    }
    public int getLives(){
        return lives;
    }
    public void stop(){
        MOVEMENT.stop();
    }

    protected abstract void update();
}
