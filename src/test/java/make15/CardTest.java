package make15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {
    @Test
    void testCardCreation() {
        Card card = new Card("Hearts", "Ace", 12);
        assertEquals("Hearts", card.getSuit());
        assertEquals("Ace", card.getRank());
        assertEquals(12, card.getValue());
    }

    @Test
    void testToString() {
        Card card = new Card("Spades", "King", 11);
        assertEquals("King of Spades", card.toString());
    }
}
