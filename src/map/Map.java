package map;

import abilities.Ability;
import cell.Wall;
import character.Player;
import character.PlayerOne;
import character.PlayerTwo;
import game.Settings;
import cell.Cell;
import cell.Floor;
import cell.Point;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import miscs.Sizeable;
import score.PlayerStatus;
import score.Score;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Map extends Pane implements Sizeable {
    private static final File MAP1 = new File("map1.csv");
    private static final File MAP2 = new File("map2.csv");
    public final HashMap<Point, Cell> cells = new HashMap<>();
    public final LinkedList<Cell> walls = new LinkedList<>();
    private int cellCountWidth;
    private int cellCountHeight;

    public Player playerOne;
    public Player playerTwo;

    public static PlayerStatus playerStatus;

    public static final int START_TIME = 1000;

    private int time = START_TIME;
    private static final Duration TIME_INTERVAL = Duration.millis(250);

    private Timeline TIME;

    EventHandler<ActionEvent> handleTime = e-> {
        time--;
        playerStatus.updateHP(3, time);
    };

    public Map(){
        loadMap();
        setPrefWidth(getPaneWidth());
        setPrefHeight(getPaneHeight());
        playerStatus = new PlayerStatus(2, getPaneWidth());
        playerOne = new PlayerOne(this, new Point(3, 3));
        playerTwo = new PlayerTwo(this, new Point(10, 10));
        getChildren().addAll(playerOne, playerTwo);
        for(Ability b : Ability.ABILITY_LIST){
            getChildren().add(b);
            b.setEnemy(b.getPlayer().playerNumber==2 ? playerOne : playerTwo);
        }
        getChildren().add(playerStatus);
        playerStatus.updateHP(1, playerOne.getHP());
        playerStatus.updateHP(2, playerTwo.getHP());
        playerStatus.updateHP(3, START_TIME);
        TIME = new Timeline(new KeyFrame(TIME_INTERVAL, handleTime));
        TIME.setCycleCount(START_TIME);
        TIME.play();
    }

    public void endGame(int loser){

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
            case 1 -> new Wall(p);
            default -> throw new IllegalStateException("Illegal cell type: " + n);
        };
        if (c.isCollidable())walls.add(c);
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
