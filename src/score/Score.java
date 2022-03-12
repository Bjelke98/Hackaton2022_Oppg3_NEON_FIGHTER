package score;

import character.Player;
import game.Settings;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import miscs.Sizeable;

public class Score extends VBox implements Sizeable {


    private ScoreBoard scoreBoard = new ScoreBoard();
    private LeaderBoard leaderBoard = new LeaderBoard();

    public Score(){
        setPrefWidth(getPaneWidth());
        setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().addAll(scoreBoard, leaderBoard);
    }

    public void leaderBoardEntry(int player, int score){
        leaderBoard.addScore("Player "+player, score);
    }

    @Override
    public double getPaneWidth() {
        return Settings.BASE_SCALE<=2 ? 80*3 : 80* Settings.BASE_SCALE;
    }

    @Override
    public double getPaneHeight() {
        throw new UnsupportedOperationException("This pane does not have a set height");
    }
}
