package score;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Klasse for å vise frem, legge til og lagre scores
 */
public class LeaderBoard extends VBox {
    private static final File SCORE_FILE = new File("scores.ser");
    private ArrayList<ScoreContainer> scores;

    public LeaderBoard(){
        if(!SCORE_FILE.exists()){
            scores = new ArrayList<>();
            serialize();
        }
        deSerialize();
        update();
    }

    public void addScore(String name, int score){
        scores.add(new ScoreContainer(name, score));
        serialize();
        update();
    }

    private void update(){
        getChildren().clear();
        getChildren().addAll(new LeaderBoardLabel("HighScores:"), new Label());
        if(scores!=null){
            Collections.sort(scores);
            int displayAmount = Math.min(scores.size(), 7);
            for (int i = 0; i < displayAmount; i++) {
                getChildren().add(new LeaderBoardLabel(scores.get(i).toString()));
            }
        }
    }

    private void serialize(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SCORE_FILE))){
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void deSerialize(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SCORE_FILE))){
            scores = (ArrayList<ScoreContainer>) ois.readObject();
        } catch (IOException | ClassNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}
