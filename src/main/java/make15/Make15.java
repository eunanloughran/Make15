package make15;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Main class that implements the Make15 game logic
public class Make15 {
    static final int TARGET = 15;    // The total a play must reach to score
    static final int HAND_SIZE = 5;  // Cards dealt to the player at the start

    private final Deck deck; // Deck used in the game
    private final Player player; // The human player
    private final HighScoreTable highScoreTable; // High score table to track top scores
    private final List<String> replayLog = new ArrayList<>(); // Log to store replay details

    // Constructor to initialize the game components
    public Make15(Deck deck, HighScoreTable highScoreTable) {
        this.deck = deck;
        this.player = new Player();
        this.highScoreTable = highScoreTable;
        for (int i = 0; i < HAND_SIZE; i++) {
            player.addCard(deck.deal()); // Deal initial cards to the player
        }
    }

    // A card scores if it adds up to 15 with the computer's card
    static boolean makes15(Card playerCard, Card computerCard) {
        return playerCard.getValue() + computerCard.getValue() == TARGET;
    }

    // A play is valid if it makes 15 or matches the computer's suit
    static boolean isValidPlay(Card playerCard, Card computerCard) {
        return makes15(playerCard, computerCard) || playerCard.getSuit().equals(computerCard.getSuit());
    }

    Player getPlayer() {
        return player;
    }

    // Plays one card against the computer's card; returns false if the play is not allowed
    boolean playCard(Card selectedCard, Card computerCard) {
        if (!isValidPlay(selectedCard, computerCard)) {
            return false;
        }
        boolean scored = makes15(selectedCard, computerCard);
        if (scored) {
            player.incrementScore();
        }
        player.removeCard(selectedCard);
        player.addCard(deck.deal());
        replayLog.add("Player played " + selectedCard + ", Computer played " + computerCard
                + (scored ? ". Scored 1 point." : ". No points."));
        return true;
    }

    // Main game logic for one game
    public void play(Scanner scanner) {
        highScoreTable.displayScores(); // Show high scores at the start

        while (true) {
            if (deck.isEmpty() || player.getHand().isEmpty()) {
                // End game if no more cards can be played
                System.out.println("The deck is empty or you have no more cards to play. Game over!");
                break;
            }

            // Computer plays its card
            Card computerCard = deck.deal();
            System.out.println("Computer plays: " + computerCard);

            // Check if player has any valid moves
            if (!player.hasPlayableCard(computerCard)) {
                System.out.println("No playable cards in your hand. Game over!");
                break;
            }

            player.displayHand();

            boolean validPlay = false;
            while (!validPlay) {
                int choice = readChoice(scanner, player.getHand().size());
                Card selectedCard = player.getHand().get(choice);
                boolean scored = makes15(selectedCard, computerCard);
                validPlay = playCard(selectedCard, computerCard);
                if (!validPlay) {
                    System.out.println("Invalid play. Try again.");
                } else if (scored) {
                    System.out.println("You made 15! You score a point.");
                } else {
                    System.out.println("You played a card of the same suit. No points scored, but the game continues.");
                }
            }
        }

        // End of the game; display the final score
        System.out.println("Game over! Your final score: " + player.getScore());
        System.out.print("Enter your name for the high score table: ");
        highScoreTable.addScore(scanner.nextLine().trim(), player.getScore());

        if (askYesNo(scanner, "Do you want to view the replay? (yes/no): ")) {
            System.out.println("Replay of the game:");
            replayLog.forEach(System.out::println);
        }
    }

    // Reads a card number between 1 and handSize; returns it as a 0-based index
    private static int readChoice(Scanner scanner, int handSize) {
        while (true) {
            System.out.print("Select a card to play (1-" + handSize + "): ");
            String line = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(line) - 1;
                if (choice >= 0 && choice < handSize) {
                    return choice;
                }
                System.out.println("Invalid choice. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static boolean askYesNo(Scanner scanner, String question) {
        System.out.print(question);
        return scanner.nextLine().trim().equalsIgnoreCase("yes");
    }

    // Main method: plays games in a loop until the player stops
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            HighScoreTable highScores = new HighScoreTable();
            do {
                new Make15(new Deck(), highScores).play(scanner);
            } while (askYesNo(scanner, "Do you want to play again? (yes/no): "));
            System.out.println("Thank you for playing!");
        }
    }
}
