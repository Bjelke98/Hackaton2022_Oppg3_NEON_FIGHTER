package map;

import abilities.Ability;
import character.Player;
import character.PlayerOne;
import character.PlayerTwo;
import game.Settings;
import cell.Cell;
import cell.Floor;
import cell.Point;
import javafx.scene.layout.Pane;
import miscs.Sizeable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Map extends Pane implements Sizeable {
    private static final File MAP1 = new File("map1.csv");
    private static final File MAP2 = new File("map2.csv");
    private final HashMap<Point, Cell> cells = new HashMap<>();
    private int cellCountWidth;
    private int cellCountHeight;

    private Player playerOne;
    private Player playerTwo;

    public Map(){
        loadMap();
        setPrefWidth(getPaneWidth());
        setPrefHeight(getPaneHeight());
        playerOne = new PlayerOne(this, new Point(3, 3));
        playerTwo = new PlayerTwo(this, new Point(10, 10));
        getChildren().addAll(playerOne, playerTwo);
        for(Ability b : Ability.ABILITY_LIST){
            getChildren().add(b);
        }
    }

    private void loadMap(){
        try {
            Scanner row = new Scanner(MAP2);
            String[] dim = row.nextLine().split(";");
            cellCountWidth = Integer.parseInt(dim[0]);
            cellCountHeight = Integer.parseInt(dim[1]);
            for (int i = 0; i < cellCountHeight; i++) {
                Scanner col = new Scanner(row.nextLine());
                col.useDelimiter(";");
                for (int j = 0; j < cellCountWidth; j++) {
                    putCell(j, i, col.nextInt());
                }
                col.close();
            }
            row.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void putCell(int x, int y, int n){
        Point p = new Point(x, y);
        Cell c = switch (n){
            case 0 -> new Floor(p);
            default -> throw new IllegalStateException("Illegal cell type: " + n);
        };
        cells.put(p, c);
        getChildren().add(c);
    }

    public int getCellCountWidth() {
        return cellCountWidth;
    }
    public int getCellCountHeight() {
        return cellCountHeight;
    }

    @Override
    public double getPaneWidth(){
        return cellCountWidth* Settings.CELL_SIZE;
    }
    @Override
    public double getPaneHeight(){
        return cellCountHeight* Settings.CELL_SIZE;
    }

}
