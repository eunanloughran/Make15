package make15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// Represents a deck of playing cards and provides functionality to shuffle, deal and check if it's empty
public class Deck {
    private final List<Card> cards = new ArrayList<>(); // List of cards in the deck

    // Constructor to initialize a shuffled deck of 52 standard cards
    public Deck() {
        this(new Random());
    }

    // Constructor with a given Random, so tests can create a predictable deck
    public Deck(Random random) {
        // Define the possible suits, ranks, and values for the cards
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11, 11, 12};

        // Populate the deck with all combinations of suits and ranks
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(suit, ranks[i], values[i]));
            }
        }
        Collections.shuffle(cards, random); // Shuffle the deck after creating it
    }

    // Deals the top card from the deck, or null if the deck is empty
    public Card deal() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    // Checks if the deck is empty
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Returns the number of cards left in the deck
    public int size() {
        return cards.size();
    }
}
