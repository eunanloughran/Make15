package make15;

import java.io.Serializable;

// Represents a single entry in the high score table
public class ScoreEntry implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String name; // The player's name
    private final int score;   // The player's score

    public ScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + ": " + score;
    }
}
