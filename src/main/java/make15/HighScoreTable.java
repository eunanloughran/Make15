package make15;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Represents a table that stores and manages the high scores for the game
public class HighScoreTable {
    static final int MAX_ENTRIES = 5; // Maximum number of entries allowed in the table
    private final List<ScoreEntry> highScores = new ArrayList<>(); // List of high scores
    private final Path file; // File the scores are saved to

    // Uses highscores.dat in the current folder
    public HighScoreTable() {
        this(Path.of("highscores.dat"));
    }

    // Uses the given file, so tests can point at a temporary file
    public HighScoreTable(Path file) {
        this.file = file;
        loadHighScores(); // Load high scores from the file when initialized
    }

    // Adds a new score entry to the high score table
    public void addScore(String name, int score) {
        highScores.add(new ScoreEntry(name, score));
        highScores.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed()); // Sort scores in descending order
        if (highScores.size() > MAX_ENTRIES) {
            highScores.remove(highScores.size() - 1); // Remove lowest score if the table exceeds max entries
        }
        saveHighScores(); // Save the updated high scores to the file
    }

    // Returns a copy of the current high scores, highest first
    public List<ScoreEntry> getScores() {
        return List.copyOf(highScores);
    }

    // Displays the high scores to the console
    public void displayScores() {
        System.out.println("High Scores:");
        for (ScoreEntry entry : highScores) {
            System.out.println(entry);
        }
    }

    // Loads high scores from the file if it exists
    @SuppressWarnings("unchecked")
    private void loadHighScores() {
        if (!Files.exists(file)) return; // No saved scores yet
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            highScores.addAll((List<ScoreEntry>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load high scores, starting with an empty table.");
        }
    }

    // Saves the high scores to the file
    private void saveHighScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            oos.writeObject(new ArrayList<>(highScores));
        } catch (IOException e) {
            System.out.println("Could not save high scores: " + e.getMessage());
        }
    }
}
