package score;

import java.io.Serializable;

/**
 * Enkel konteiner for å lagre spiller poeng.
 */
public record ScoreContainer(String playerName, int score) implements Comparable<ScoreContainer>, Serializable {

    @Override
    public int compareTo(ScoreContainer o) {
        return o.score()-score();
    }

    @Override
    public String toString() {
        return playerName()+": "+score();
    }

}
