package score;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

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

    private void update(){
        getChildren().clear();
        getChildren().addAll(new Label("HighScores:"), new Label());
        if(scores!=null){
            Collections.sort(scores);
            int displayAmount = Math.min(scores.size(), 7);
            for (int i = 0; i < displayAmount; i++) {
                getChildren().add(new Label(scores.get(i).toString()));
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
