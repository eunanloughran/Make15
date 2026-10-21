# Make 15

A console card game in Java. The computer turns over a card each round and you play a card from your hand against it. Make 15 and you score a point; match its suit and the game carries on without a point. Play anything else and the move is rejected. The game ends when the deck runs out or you have no card you can play.

## Rules

- You start with 5 cards. Number cards are worth their number, Jack, Queen and King are worth 11, and Ace is worth 12.
- A card that adds up to 15 with the computer's card scores 1 point.
- A card of the same suit is a valid play but scores nothing.
- After each play you draw a new card from the deck.
- The top 5 scores are saved between games, and you can view a replay of every move at the end.

## Running it

Requires Java 17+ and Maven.

```bash
mvn compile exec:java   # play the game
mvn test                # run the unit tests
```

## Design

| Class | Responsibility |
| --- | --- |
| `Card` | Suit, rank and value of one card |
| `Deck` | Builds and shuffles 52 cards and deals from the top |
| `Player` | The hand and score, and whether any card in the hand can be played |
| `Make15` | Game rules (`makes15`, `isValidPlay`, `playCard`) and the console game loop |
| `HighScoreTable` / `ScoreEntry` | Top 5 scores, saved to `highscores.dat` |

The game rules are kept separate from console input so they can be unit tested directly. The tests use a seeded `Random` for a predictable deck and a temporary folder for the high score file, so they give the same result on every run.
