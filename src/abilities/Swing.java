package abilities;

import character.Player;
import game.Settings;
import javafx.scene.image.Image;

public class Swing extends Ability{

    private static final Image IMAGE = new Image("Swing2.png");

    public Swing(Player player){
        super(player);
        setImage(IMAGE);
    }

    @Override
    protected void update() {
        double deltaX = switch(animationDirection){
            case 0, 1, 2 -> 0;
            case 3 -> -Settings.CHARACTER_SIZE;
            case 4 -> Settings.CHARACTER_SIZE;
            default -> throw new IllegalStateException("Unexpected value: " + animationDirection);
        };
        double deltaY = switch (animationDirection){
            case 0, 3, 4 -> 0;
            case 1 -> -Settings.CHARACTER_SIZE;
            case 2 -> Settings.CHARACTER_SIZE;
            default -> throw new IllegalStateException("Unexpected value: " + animationDirection);
        };
        setX(player.getX()+deltaX);
        setY(player.getY()+deltaY);
    }

    @Override
    protected void lastTick() {
        if(getBoundsInLocal().intersects(enemy.getBoundsInLocal())){
            enemy.swingHit();
        }
    }
}
