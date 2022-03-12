package score;

import game.Settings;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Litt unødven dig klasse, men gjør det lettere å holde styr på tekst innholdet i topp panelet.
 */
public class HitPoints extends Label {

    private static final String ff = "Arial";
    private static final double fs = 8* Settings.BASE_SCALE;
    private static final FontWeight fw = FontWeight.EXTRA_BOLD;


    private static final Font FONT = Font.font(ff, fw, fs);

    private final String playerText;
    private int player;
    public HitPoints(int player){
        this.player = player;
        if(player==1 || player==2){
            this.playerText = "Player "+player+" HP: ";
        } else {
            this.playerText = "Time: ";
        }
        setFont(FONT);
        setTextFill(switch (player){
            case 1 -> Color.CYAN;
            case 2 -> Color.LIMEGREEN;
            default -> Color.WHITE;
        });
    }

    public void update(int hp){
        setText(playerText +hp);
    }
}
