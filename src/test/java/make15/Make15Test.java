package make15;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Make15Test {
    @TempDir
    Path tempDir;

    private Make15 newGame() {
        return new Make15(new Deck(new Random(7)), new HighScoreTable(tempDir.resolve("scores.dat")));
    }

    @Test
    void testInitialSetupDealsFiveCards() {
        assertEquals(5, newGame().getPlayer().getHand().size());
    }

    @Test
    void testMakes15() {
        Card computerCard = new Card("Hearts", "8", 8);
        assertTrue(Make15.makes15(new Card("Spades", "7", 7), computerCard));
        assertFalse(Make15.makes15(new Card("Spades", "6", 6), computerCard));
    }

    @Test
    void testValidPlayBySuitOrMake15() {
        Card computerCard = new Card("Hearts", "8", 8);
        assertTrue(Make15.isValidPlay(new Card("Clubs", "7", 7), computerCard));   // makes 15
        assertTrue(Make15.isValidPlay(new Card("Hearts", "2", 2), computerCard));  // same suit
        assertFalse(Make15.isValidPlay(new Card("Clubs", "9", 9), computerCard));  // neither
    }

    @Test
    void testScoringPlayAddsPointAndRefillsHand() {
        Make15 game = newGame();
        Card playerCard = new Card("Clubs", "7", 7);
        game.getPlayer().addCard(playerCard);

        assertTrue(game.playCard(playerCard, new Card("Hearts", "8", 8)));
        assertEquals(1, game.getPlayer().getScore());
        assertFalse(game.getPlayer().getHand().contains(playerCard));
        assertEquals(6, game.getPlayer().getHand().size()); // played card replaced from the deck
    }

    @Test
    void testInvalidPlayIsRejected() {
        Make15 game = newGame();
        Card playerCard = new Card("Clubs", "9", 9);
        game.getPlayer().addCard(playerCard);

        assertFalse(game.playCard(playerCard, new Card("Hearts", "8", 8)));
        assertEquals(0, game.getPlayer().getScore());
        assertTrue(game.getPlayer().getHand().contains(playerCard));
    }
}
