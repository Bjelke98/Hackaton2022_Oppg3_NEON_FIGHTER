package score;

import game.Settings;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LeaderBoardLabel extends Label {
    private static final String ff = "Arial";
    private static final double fs = 6* Settings.BASE_SCALE;
    private static final FontWeight fw = FontWeight.BOLD;
    private static final Font FONT = Font.font(ff, fw, fs);
    public LeaderBoardLabel(){
        setFont(FONT);
    }
    public LeaderBoardLabel(String text){
        this();
        setText(text);
    }
}
