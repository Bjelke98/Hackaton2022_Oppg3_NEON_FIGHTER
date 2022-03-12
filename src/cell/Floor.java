package cell;

import game.Settings;
import javafx.scene.image.Image;

public class Floor extends Cell{
    private static final Image IMAGE = new Image("Floor1"+ (Settings.DEBUG ? "DEBUG":"") +".png");
    public Floor(Point point) {
        super(point, IMAGE);
        setRotate(getRandomRotate());
    }
    public double getRandomRotate(){
        return switch (random4()){
            case 0-> 0;
            case 1-> 90;
            case 2-> 180;
            case 3-> 270;
            default -> throw new IllegalStateException("Ulåvelig rettning");
        };
    }
    public int random4(){
        return (int)(Math.random()*3);
    }
}
