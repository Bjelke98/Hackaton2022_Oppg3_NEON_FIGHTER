package score;

import character.Player;
import game.Settings;
import javafx.scene.layout.HBox;

/**
 * Viser hvor mange liv spilleren har gjenstående.
 */
public class LifePane extends HBox {
    int player;
    public LifePane(int player){
        this.player = player;
        update(Player.START_LIVES);
        setSpacing(Settings.CELL_SIZE/4);
    }

    public void update(int lives){
        getChildren().clear();
        for (int i = 0; i < lives; i++) {
            getChildren().add(new LifeMark(player));
        }
    }
}
