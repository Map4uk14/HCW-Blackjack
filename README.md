# ♠️ JavaFX Blackjack - Uni Project

This is a JavaFX-based Blackjack game built with Gradle.
**Team:** Dopler, Shappert, Kocyigit, Yusupov.

## 🎮 Game Rules (From Project Backlog)
* **Minimum Bet:** 10€.
* **Dealer Logic:** The Dealer must draw cards (While-loop) until their score is at least 17.
* **Double Down (2X):** Only available as the first action; gives exactly one card then ends the turn.
* **Bust Logic:** Automatically lose if the score exceeds 21.

## 🎨 Assets & Credits
This project uses external graphical assets to enhance the UI:
* **Card Sprites:** [Playing Cards Set](https://opengameart.org/content/cards-set) by *Byron Knolls* via OpenGameArt.org.
* **License:** Public Domain / CC0 (Free for personal and commercial use).

## 🛠 Setup & Requirements
* **JDK:** 17 or higher
* **Build System:** Gradle

### How to Run
To start the application, use the Gradle wrapper in your terminal:
```bash
./gradlew run
