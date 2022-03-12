package cell;

import game.Settings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Super klasse for alle celler.
 */
public abstract class Cell extends ImageView {
    private final Point point;
    protected Cell(Point point, Image image){
        super(image);
        this.point = point;
        setX(point.sx());
        setY(point.sy());
        setFitWidth(Settings.CELL_SIZE);
        setFitHeight(Settings.CELL_SIZE);
        if(Settings.DEBUG){
            setOnMouseClicked(e-> System.out.println(this));
        }
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

    public boolean isCollidable(){
        return false;
    }

    @Override
    public String toString() {
        return point.toString();
    }
}
