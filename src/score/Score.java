package score;

import game.Settings;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import miscs.Sizeable;

/**
 * Boks som holder på leaderboard, men kan utvides i ettertid til å inneholde mer nyttig informasjon eller annen funksjonalitet.
 * Eksempler:
 *      La spillere velge navn.
 *      La spillere velge 1 spiller istede for 2.
 */
public class Score extends VBox implements Sizeable {

    private final LeaderBoard leaderBoard = new LeaderBoard();

    public Score(){
        setPrefWidth(getPaneWidth());
        setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        getChildren().add(leaderBoard);
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
