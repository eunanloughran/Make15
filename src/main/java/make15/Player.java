package make15;

import java.util.ArrayList;
import java.util.List;

// Represents a player in the game, holding their hand of cards and tracking their score
public class Player {
    private final List<Card> hand = new ArrayList<>(); // The player's hand of cards
    private int score = 0; // The player's score

    // Adds a card to the player's hand
    public void addCard(Card card) {
        if (card != null) hand.add(card);
    }

    // Removes a specified card from the player's hand
    public void removeCard(Card card) {
        hand.remove(card);
    }

    // Checks if the player has a card of the specified suit in their hand
    public boolean hasCardOfSuit(String suit) {
        return hand.stream().anyMatch(card -> card.getSuit().equals(suit));
    }

    // Checks if the player has a card that matches the given value
    public boolean hasCardWithValue(int target) {
        return hand.stream().anyMatch(card -> card.getValue() == target);
    }

    // Displays all cards currently in the player's hand
    public void displayHand() {
        System.out.println("Your hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i));
        }
    }

    // Returns the player's hand as a list of cards
    public List<Card> getHand() {
        return hand;
    }

    // Returns the player's current score
    public int getScore() {
        return score;
    }

    // Increments the player's score by 1
    public void incrementScore() {
        score++;
    }

    // Checks if the player has any card that can form a valid play with the computer's card
    public boolean hasPlayableCard(Card computerCard) {
        return hand.stream().anyMatch(card -> Make15.isValidPlay(card, computerCard));
    }
}
