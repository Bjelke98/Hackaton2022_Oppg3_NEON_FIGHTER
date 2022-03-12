package powerup;

import cell.Point;
import character.Player;
import javafx.scene.image.Image;
import map.Map;

public class AntiHeal extends PowerUp {
    private static final Image IMAGE = new Image("Antiheal1.png");
    public AntiHeal(Point point){
        super(point);
        setImage(IMAGE);
    }

    @Override
    public void effect(Player player) {
        player.antiHP();
        Map.playerStatus.updateHP(player.playerNumber, player.getHP());
    }
}
