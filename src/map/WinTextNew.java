package map;

import game.Settings;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WinTextNew extends WinText{
    private static final double fss = 8* Settings.BASE_SCALE;
    private static final Font FONT = Font.font(ff, fw, fss);
    public WinTextNew(String text){
        super(text);
        setFont(FONT);
        setTextFill(Color.WHITE);
    }
}
