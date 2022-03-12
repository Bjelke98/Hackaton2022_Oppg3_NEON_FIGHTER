package map;

import game.Settings;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Enkel klasse for å slippe å sette for 3 ganger.
 */
public class WinText extends Label {
    protected static final String ff = "Arial";
    protected static final double fs = 16* Settings.BASE_SCALE;
    protected static final FontWeight fw = FontWeight.EXTRA_BOLD;
    private static final Font FONT = Font.font(ff, fw, fs);
    public WinText(String text){
        super(text);
        setFont(FONT);
    }
    public WinText(String text, int winner){
        this(text);
        setTextFill(winner==1 ? Color.CYAN : Color.LIMEGREEN);
    }
}
