package make15;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeckTest {
    @Test
    void testDeckHas52UniqueCards() {
        Deck deck = new Deck(new Random(1));
        Set<String> seen = new HashSet<>();
        while (!deck.isEmpty()) {
            seen.add(deck.deal().toString());
        }
        assertEquals(52, seen.size());
    }

    @Test
    void testDealRemovesTopCard() {
        Deck deck = new Deck(new Random(1));
        Card dealtCard = deck.deal();
        assertNotNull(dealtCard);
        assertEquals(51, deck.size());
    }

    @Test
    void testDealFromEmptyDeckReturnsNull() {
        Deck deck = new Deck(new Random(1));
        for (int i = 0; i < 52; i++) {
            deck.deal();
        }
        assertTrue(deck.isEmpty());
        assertNull(deck.deal());
    }

    @Test
    void testSameSeedGivesSameOrder() {
        Deck first = new Deck(new Random(42));
        Deck second = new Deck(new Random(42));
        for (int i = 0; i < 52; i++) {
            assertEquals(first.deal().toString(), second.deal().toString());
        }
    }
}
