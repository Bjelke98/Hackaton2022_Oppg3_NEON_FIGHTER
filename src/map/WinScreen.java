package map;

import game.GameController;
import game.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WinScreen extends VBox {
    private static final String ff = "Arial";
    private static final double fs = 16* Settings.BASE_SCALE;
    private static final double fss = 8* Settings.BASE_SCALE;
    private static final FontWeight fw = FontWeight.EXTRA_BOLD;
    private static final Font FONT = Font.font(ff, fw, fs);
    private static final Font SMALL = Font.font(ff, fw, fss);
    public WinScreen(Map map, int winner, int score){
        setPrefWidth(map.getPaneWidth());
        setPrefHeight(map.getPrefHeight());
        Label label1 = new Label("Well played!");
        Label label2 = new Label("Wictory Player "+winner);
        Label label3 = new Label("Final Score: "+score+"!!!");
        Label label4 = new Label("Click anywhere to play again");
        label1.setFont(FONT);
        label2.setFont(FONT);
        label3.setFont(FONT);
        label4.setFont(SMALL);
        label1.setTextFill(winner==1 ? Color.CYAN : Color.LIMEGREEN);
        label2.setTextFill(winner==1 ? Color.CYAN : Color.LIMEGREEN);
        label3.setTextFill(winner==1 ? Color.CYAN : Color.LIMEGREEN);
        label4.setTextFill(Color.WHITE);
        getChildren().addAll(label1, label2, label3, label4);
        setAlignment(Pos.CENTER);
        setSpacing(Settings.BASE_SCALE);
        setBackground(new Background(new BackgroundFill(Color.color(0, 0, 0, .5), CornerRadii.EMPTY, Insets.EMPTY)));
        setOnMouseClicked(e->map.restart(true, this));
    }
}
