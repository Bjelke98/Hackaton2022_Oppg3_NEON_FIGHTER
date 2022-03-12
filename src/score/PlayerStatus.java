package score;

import game.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Spaghetti kode for å oppdatere topp panelet.
 */
public class PlayerStatus extends HBox {
    private HitPoints p1HP;
    private HitPoints timePoints;
    private HitPoints p2HP;

    private LifePane p1Life;
    private LifePane p2Life;

    public PlayerStatus(int players, double prefWidth){
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundFill(Color.color(0, 0, 0, .3), CornerRadii.EMPTY, Insets.EMPTY)));
        Insets insets = new Insets(0, Settings.CHARACTER_SIZE, 0, Settings.CHARACTER_SIZE);

        p1HP = new HitPoints(1);
        p1Life = new LifePane(1);
        VBox p1Box = new VBox();
        p1Box.getChildren().addAll(p1HP, p1Life);
        p1Box.setPadding(insets);

        timePoints = new HitPoints(3);
        VBox time = new VBox();
        time.getChildren().add(timePoints);
        time.setPadding(insets);

        VBox p2Box = new VBox();
        if(players>=2){
            p2HP = new HitPoints(2);
            p2Life = new LifePane(2);
            p2Box.getChildren().addAll(p2HP, p2Life);
            p2Box.setPadding(insets);
        }
        getChildren().addAll(p1Box, time, p2Box);
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
    public void updateLife(int player, int lives){
        if(player==1){
            p1Life.update(lives);
        } else {
            p2Life.update(lives);
        }
    }
}
