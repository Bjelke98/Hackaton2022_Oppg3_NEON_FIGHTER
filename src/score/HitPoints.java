package score;

import game.Settings;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HitPoints extends Label {

    private static final String ff = "Arial";
    private static final double fs = 8* Settings.BASE_SCALE;
    private static final FontWeight fw = FontWeight.EXTRA_BOLD;


    private static final Font FONT = Font.font(ff, fw, fs);

    private String playerTekst;
    private int player;
    public HitPoints(int player){
        this.player = player;
        if(player==1 || player==2){
            this.playerTekst = "Player "+player+": ";
        } else {
            this.playerTekst = "Time: ";
        }
        setFont(FONT);
        setTextFill(switch (player){
            case 1 -> Color.CYAN;
            case 2 -> Color.LIMEGREEN;
            default -> Color.WHITE;
        });
    }

    public void update(int hp){
        setText(playerTekst+hp);
    }
}
