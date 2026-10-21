package make15;

// Represents a single playing card with attributes for suit, rank, and value
public class Card {
    private final String suit; // Suit of the card: Hearts, Diamonds, Clubs, Spades
    private final String rank; // Rank of the card such as 2, 3, King, Ace
    private final int value;   // Value of the card used in scoring

    // Constructor to initialize a card with its suit, rank, and value
    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    // Returns the suit of the card
    public String getSuit() {
        return suit;
    }

    // Returns the rank of the card
    public String getRank() {
        return rank;
    }

    // Returns the value of the card
    public int getValue() {
        return value;
    }

    // Provides a string representation of the card
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
