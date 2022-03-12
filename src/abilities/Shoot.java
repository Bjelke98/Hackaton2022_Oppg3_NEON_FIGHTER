package abilities;

import character.Player;
import game.Settings;
import javafx.scene.image.Image;

/**
 * Class for the shooting ability. will trigger when holding L2
 */
public class Shoot extends Ability{
    private final static Image IMAGE = new Image("Shoot2.png");
    public Shoot(Player player) {
        super(player);
        setImage(IMAGE);
    }

    @Override
    protected void update() {

        double centerOffsett = Settings.CHARACTER_SIZE/2-Settings.BASE_CELL;
        double centerWH = Settings.BASE_CELL*2;

        double deltaX = switch(animationDirection-10){
            case 0, 1, 2 -> 0;
            case 3 -> -Settings.CHARACTER_SIZE;
            case 4 -> Settings.CHARACTER_SIZE;
            default -> throw new IllegalStateException("Unexpected value: " + animationDirection);
        };
        double deltaY = switch (animationDirection-10){
            case 0, 3, 4 -> 0;
            case 1 -> -Settings.CHARACTER_SIZE;
            case 2 -> Settings.CHARACTER_SIZE;
            default -> throw new IllegalStateException("Unexpected value: " + animationDirection);
        };
        if(animationState==1){
            setX(player.getX()+deltaX);
            setY(player.getY()+deltaY);
        } else {
            setX(getX() + deltaX*0.3);
            setY(getY() + deltaY*0.3);
        }


        if(!isHit){
            if (enemy.getLayoutBounds().intersects(getX()+centerOffsett, getY()+centerOffsett, centerWH, centerWH)){
                isHit = true;
                enemy.shootHit();
            }
        }
    }

    @Override
    protected void lastTick() {

    }
}
