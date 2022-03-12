package map;

import game.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Vise infoskjerm ved WinState.
 * Gir brukeren mulighet til å starte på nytt.
 */
public class WinScreen extends VBox {
    public WinScreen(Map map, int winner, int score){
        setPrefWidth(map.getPaneWidth());
        setPrefHeight(map.getPrefHeight());
        WinText wellPlayedText = new WinText("Well played!", winner);
        WinText victoryText = new WinText("Victory Player "+winner, winner);
        WinText scoreText = new WinText("Final Score: "+score+"!!!", winner);
        WinTextNew newGameText = new WinTextNew("Click anywhere to play again");
        getChildren().addAll(wellPlayedText, victoryText, scoreText, newGameText);
        setAlignment(Pos.CENTER);
        setSpacing(Settings.BASE_SCALE);
        setBackground(new Background(new BackgroundFill(Color.color(0, 0, 0, .5), CornerRadii.EMPTY, Insets.EMPTY)));
        setOnMouseClicked(e->map.restart(true, this));
    }
}
