package map;

import abilities.Ability;
import cell.Wall;
import character.Character;
import character.Player;
import character.PlayerOne;
import character.PlayerTwo;
import game.GameController;
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
import powerup.AntiHeal;
import powerup.Heal;
import powerup.PowerUp;
import powerup.Witch;
import score.PlayerStatus;
import score.Score;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Kart klassen, muligens den klassen som har flest arbeidsoppgaver.
 * Skulle vel helst hatt delt den litt opp, men tiden lar seg ikke gjøre.
 */
public class Map extends Pane implements Sizeable {
    private static final File MAP1 = new File("map1.csv");
    private static final File MAP2 = new File("map2.csv");
    public final HashMap<Point, Cell> cells = new HashMap<>();
    public final LinkedList<Cell> walls = new LinkedList<>();
    private int cellCountWidth;
    private int cellCountHeight;

    public Player playerOne;
    public Player playerTwo;

    Point p1Start;
    Point p2Start;

    public static PlayerStatus playerStatus;

    public static final int START_TIME = 1000;

    private int time = START_TIME;
    private static final Duration TIME_INTERVAL = Duration.millis(250);
    private static final Duration POWERUP_INTERVAL = Duration.millis(9000);

    private Timeline TIME;

    EventHandler<ActionEvent> handleTime = e-> {
        time--;
        playerStatus.updateHP(3, time);
    };

    public ArrayList<PowerUp> activePowerups = new ArrayList<>();

    private Timeline POWERUP;

    EventHandler<ActionEvent> handlePowerUp = e-> {
        int randX = (int)(Math.random()*cellCountWidth);
        int randY = (int)(Math.random()*cellCountHeight);
        int randType = (int)(Math.random()*PowerUp.countTypes);
        Point p = new Point(randX, randY);
        Cell cell = cells.get(p);
        if(!cell.isCollidable()){
            PowerUp pu;
            switch (randType){
                case 0 -> pu = new Heal(p);
                case 1 -> pu = new AntiHeal(p);
                case 2 -> pu = new Witch(p);
                default -> pu = null;
            }
            if(pu!=null){
                activePowerups.add(pu);
                getChildren().add(pu);
            }
        }
    };

    public Map(){
        loadMap();
        setPrefWidth(getPaneWidth());
        setPrefHeight(getPaneHeight());
        restart();
    }

    public void endGame(int loser){
        int winner = (loser==1 ? 2 : 1);
        Player p = (winner==1 ? playerOne : playerTwo);
        TIME.stop();
        playerOne.stop();
        playerTwo.stop();
        POWERUP.stop();
        int finalScore = ((p.getHP()+(p.getLives()-1)*Character.START_HP*time)/100);
        GameController.score.leaderBoardEntry(winner, finalScore);
        getChildren().add(new WinScreen(this, winner, finalScore));
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
            case 0,2,3 -> new Floor(p);
            case 1 -> new Wall(p);
            default -> throw new IllegalStateException("Illegal cell type: " + n);
        };
        if(n==2){
            p1Start=p;
        }
        if(n==3){
            p2Start=p;
        }
        if (c.isCollidable())walls.add(c);
        cells.put(p, c);
        getChildren().add(c);
    }

    public void restart(){
        playerStatus = new PlayerStatus(2, getPaneWidth());
        playerOne = new PlayerOne(this, p1Start);
        playerTwo = new PlayerTwo(this, p2Start);
        getChildren().addAll(playerOne, playerTwo);
        for(Ability b : Ability.ABILITY_LIST){
            getChildren().add(b);
            b.setEnemy(b.getPlayer().playerNumber==2 ? playerOne : playerTwo);
        }
        getChildren().add(playerStatus);
        playerStatus.updateHP(1, playerOne.getHP());
        playerStatus.updateHP(2, playerTwo.getHP());
        playerStatus.updateHP(3, START_TIME);
        time = START_TIME;
        TIME = new Timeline(new KeyFrame(TIME_INTERVAL, handleTime));
        TIME.setCycleCount(START_TIME);
        TIME.play();
        POWERUP = new Timeline(new KeyFrame(POWERUP_INTERVAL, handlePowerUp));
        POWERUP.setCycleCount(Timeline.INDEFINITE);
        POWERUP.play();
    }
    public void restart(boolean full, WinScreen ws){
        getChildren().remove(playerOne);
        getChildren().remove(playerTwo);
        for (Ability b : Ability.ABILITY_LIST){
            getChildren().remove(b);
        }
        Ability.ABILITY_LIST.clear();
        getChildren().remove(playerStatus);
        getChildren().remove(ws);
        for (PowerUp p : activePowerups) {
            getChildren().remove(p);
        }
        activePowerups.clear();
        restart();
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
