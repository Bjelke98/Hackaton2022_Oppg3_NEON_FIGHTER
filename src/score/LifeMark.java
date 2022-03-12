package score;

import character.PlayerOne;
import character.PlayerTwo;
import game.Settings;
import javafx.scene.image.ImageView;
import miscs.EntityOptions;

public class LifeMark extends ImageView {
    public LifeMark(int player){
        setImage(player==1? PlayerOne.IMAGE: PlayerTwo.IMAGE);
        setViewport(EntityOptions.VIEWS[0]);
        setFitWidth(Settings.CELL_SIZE);
        setFitHeight(Settings.CELL_SIZE);
    }
}
