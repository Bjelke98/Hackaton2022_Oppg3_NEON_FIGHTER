package abilities;

import character.Player;
import game.Settings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import miscs.EntityOptions;

import java.util.ArrayList;

public abstract class Ability extends ImageView {

    public static final ArrayList<Ability> ABILITY_LIST = new ArrayList<>();

    private static final Duration ANIMATION_DURATION = Duration.millis(20);

    private Timeline ANIMATION;
    protected Player player;
    protected Player enemy;
    protected int animationState = 0;
    protected int animationDirection = 0;

    EventHandler<ActionEvent> handleAnimation = e-> spriteUpdate();

    protected Ability(Player player){
        this.player = player;
        ANIMATION = new Timeline(new KeyFrame(ANIMATION_DURATION, handleAnimation));
        ANIMATION.setCycleCount(14);
        setX(-Settings.CHARACTER_SIZE);
        setFitWidth(Settings.CHARACTER_SIZE);
        setFitHeight(Settings.CHARACTER_SIZE);
        ABILITY_LIST.add(this);
    }

    private void spriteUpdate(){
        if(animationState<10){
            setViewport(EntityOptions.VIEWS[animationState/2]);
            if(animationDirection!=0){
                setRotate(switch (animationDirection){
                    case 1 -> 0;
                    case 4 -> 90;
                    case 2 -> 180;
                    case 3 -> 270;
                    default -> throw new IllegalStateException("Unexpected value: " + animationDirection);
                });
            }
        }
        animationState++;
        if(animationState<13){
            update();
        }
        if(animationState>=14){
            lastTick();
            setX(-Settings.CHARACTER_SIZE);
        }
    }

    protected abstract void update();
    protected abstract void lastTick();

    public void reset(){
        animationState = 0;
        animationDirection = 0;
        setX(-Settings.CHARACTER_SIZE);
    }

    public void play(int side){
        animationDirection = side;
        ANIMATION.play();
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public Animation.Status getStatus(){
        return ANIMATION.getStatus();
    }
}
