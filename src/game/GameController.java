package game;

import input.InputController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import map.Map;
import score.Score;

import java.io.File;
import java.net.URL;

/**
 * Hovedklasse for spillet som inneholder hele applikasjonen. Siden spillet bare bruker en scene så fungerer den mer som en samleplass.
 */
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

        File songFile = new File("NEON_FIGHTER_THEME.mp3");
        Media songMedia = new Media(songFile.toURI().toString());
        MediaPlayer player = new MediaPlayer(songMedia);
        player.setOnEndOfMedia(()->{
            player.seek(Duration.ZERO);
        });
        player.play();

        stage.setScene(scene);
        stage.setTitle("Hackathon oppgave 3");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
