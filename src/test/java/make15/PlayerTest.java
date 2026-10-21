package make15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    @Test
    void testAddCard() {
        Player player = new Player();
        Card card = new Card("Hearts", "5", 5);
        player.addCard(card);
        assertTrue(player.getHand().contains(card));
    }

    @Test
    void testRemoveCard() {
        Player player = new Player();
        Card card = new Card("Clubs", "10", 10);
        player.addCard(card);
        player.removeCard(card);
        assertFalse(player.getHand().contains(card));
    }

    @Test
    void testAddNullCardIsIgnored() {
        Player player = new Player();
        player.addCard(null);
        assertTrue(player.getHand().isEmpty());
    }

    @Test
    void testHasPlayableCardBySuitOrMake15() {
        Card computerCard = new Card("Hearts", "8", 8);

        Player sameSuit = new Player();
        sameSuit.addCard(new Card("Hearts", "2", 2));
        assertTrue(sameSuit.hasPlayableCard(computerCard));

        Player makes15 = new Player();
        makes15.addCard(new Card("Clubs", "7", 7));
        assertTrue(makes15.hasPlayableCard(computerCard));

        Player neither = new Player();
        neither.addCard(new Card("Clubs", "9", 9));
        assertFalse(neither.hasPlayableCard(computerCard));
    }
}
