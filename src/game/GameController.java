package game;

import input.InputController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import map.Map;
import score.Score;

public class GameController extends Application {
    public static Score score;
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Map map = new Map();

        score = new Score();

        root.setCenter(map);
        root.setRight(score);

        Scene scene = new Scene(root, map.getPaneWidth()+score.getPaneWidth(), map.getPaneHeight());

        scene.setOnKeyPressed(e->InputController.press(e.getCode()));
        scene.setOnKeyReleased(e->InputController.release(e.getCode()));

        stage.setScene(scene);
        stage.setTitle("Hackaton oppgave 3");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
