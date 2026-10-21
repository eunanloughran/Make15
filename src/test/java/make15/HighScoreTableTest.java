package make15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HighScoreTableTest {
    @TempDir
    Path tempDir;

    @Test
    void testAddScore() {
        HighScoreTable table = new HighScoreTable(tempDir.resolve("scores.dat"));
        table.addScore("Alice", 20);
        assertEquals(1, table.getScores().size());
    }

    @Test
    void testScoresSortedAndCappedAtFive() {
        HighScoreTable table = new HighScoreTable(tempDir.resolve("scores.dat"));
        int[] scores = {3, 9, 1, 7, 5, 8};
        for (int s : scores) {
            table.addScore("P" + s, s);
        }
        List<ScoreEntry> top = table.getScores();
        assertEquals(5, top.size());
        assertEquals(9, top.get(0).getScore());
        assertEquals(3, top.get(4).getScore()); // lowest score (1) was dropped
    }

    @Test
    void testScoresAreSavedAndReloaded() {
        Path file = tempDir.resolve("scores.dat");
        new HighScoreTable(file).addScore("Bob", 4);
        HighScoreTable reloaded = new HighScoreTable(file);
        assertEquals(1, reloaded.getScores().size());
        assertEquals("Bob", reloaded.getScores().get(0).getName());
    }
}
