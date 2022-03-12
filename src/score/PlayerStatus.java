package score;

import character.Player;
import game.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import map.Map;

public class PlayerStatus extends HBox {
    private Insets insets;
    private HitPoints p1HP;
    private HitPoints timePoints;
    private HitPoints p2HP;
    private VBox p1Box = new VBox();
    private VBox time = new VBox();
    private VBox p2Box = new VBox();
    public PlayerStatus(int players, double prefWidth){
        setAlignment(Pos.CENTER);

        insets = new Insets(0, Settings.CHARACTER_SIZE, 0, Settings.CHARACTER_SIZE);

        p1HP = new HitPoints(1);
        p1Box.getChildren().add(p1HP);
        p1Box.setPadding(insets);

        timePoints = new HitPoints(3);
        time.getChildren().add(timePoints);
        time.setPadding(insets);

        if(players>=2){
            p2HP = new HitPoints(2);
            p2Box.getChildren().add(p2HP);
            p2Box.setPadding(insets);
        }
        getChildren().addAll(p1Box,time,p2Box);
        setPrefWidth(prefWidth);
    }
    public void updateHP(int player, int hp){
        if(player==1){
            p1HP.update(hp);
        } else if(player==2 && p2HP!=null){
            p2HP.update(hp);
        } else {
            timePoints.update(hp);
        }
    }
}
