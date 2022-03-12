package cell;

import game.Settings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @Override
    public String toString() {
        return point.toString();
    }
}
