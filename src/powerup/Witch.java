package powerup;

import cell.Point;
import character.Player;
import javafx.scene.image.Image;
import map.Map;

public class Witch extends PowerUp {
    private static final Image IMAGE = new Image("Witch1.png");
    public Witch(Point point){
        super(point);
        setImage(IMAGE);
    }

    @Override
    public void effect(Player player) {
        player.witchHP();
        Map.playerStatus.updateHP(player.playerNumber, player.getHP());
    }
}
