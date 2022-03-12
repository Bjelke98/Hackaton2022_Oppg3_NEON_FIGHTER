package powerup;

import cell.Point;
import character.Player;
import javafx.scene.image.Image;
import map.Map;

public class Heal extends PowerUp{
    private static final Image IMAGE = new Image("Heal1.png");
    public Heal(Point point){
        super(point);
        setImage(IMAGE);
    }

    @Override
    public void effect(Player player) {
        player.resetHP();
        Map.playerStatus.updateHP(player.playerNumber, player.getHP());
    }
}
