# War Card Game — Java OOP

A console-based multiplayer War Card Game developed using **Java** and **Object-Oriented Programming (OOP)** principles.

This project simulates a card battle where players compete by drawing cards from their individual decks. The player with the highest-ranking card wins each round and collects the played cards. The game continues until one player collects all 52 cards.

The project was designed to demonstrate logical problem-solving, object-oriented design, and the implementation of card game algorithms using Java.

## Features

- Supports **2 to 8 players**
- Uses a standard **52-card deck**
- Loads playing cards from `input.txt`
- Implements a **perfect shuffle algorithm**
- Distributes cards among players
- Automatically compares card ranks and suits
- Determines the winner of each round
- Handles player elimination
- Tracks card collection and game progress
- Declares the overall winner
- Supports restarting the game
- Displays colored card suits in compatible terminals

## Technologies Used

- **Programming Language:** Java
- **Programming Paradigm:** Object-Oriented Programming (OOP)
- **Development Environment:** Visual Studio Code
- **Java Development Kit:** JDK 21
- **Interface:** Command-Line Interface (CLI)

## Project Structure

```text
WarCardGame/
│
├── src/
│   └── cardgame/
│       ├── WarCardGame.java
│       ├── Card.java
│       ├── Deck.java
│       ├── Player.java
│       ├── Play.java
│       ├── Game.java
│       ├── ConsoleUI.java
│       ├── Rank.java
│       └── Suit.java
│
├── input.txt
├── RUN_GAME_FIXED.bat
└── README.md
```

## Class Descriptions

| Class | Responsibility |
|---|---|
| `WarCardGame.java` | Main entry point that initializes and starts the game. |
| `Card.java` | Represents individual playing cards and handles card comparisons. |
| `Deck.java` | Manages card collections and implements the perfect shuffle algorithm. |
| `Player.java` | Represents each player and manages their individual deck. |
| `Play.java` | Associates a player with the card played during a round. |
| `Game.java` | Controls the overall gameplay, card distribution, rounds, and winner determination. |
| `ConsoleUI.java` | Handles console output, game messages, and card displays. |
| `Rank.java` | Defines the available card ranks. |
| `Suit.java` | Defines the four playing card suits. |

## Game Logic

The program follows these main steps:

1. **Load the Deck:** Read the 52 playing cards from `input.txt`.
2. **Player Setup:** Accept and validate the number of players.
3. **Shuffle:** Perform the requested number of perfect shuffles.
4. **Card Distribution:** Distribute cards among the players.
5. **Start the Round:** Each active player plays their top card.
6. **Compare Cards:** Determine the highest-ranking card.
7. **Collect Cards:** The round winner collects the played cards.
8. **Check Players:** Eliminate players who have no remaining cards.
9. **Determine the Winner:** Continue until a player collects all 52 cards.
10. **Replay:** Allow the user to start another game.

## Card Ranking System

The card ranks follow this order:

**A > K > Q > J > 10 > 9 > 8 > 7 > 6 > 5 > 4 > 3 > 2**

If two cards have the same rank, the program uses a custom suit priority:

**Diamond > Heart > Spade > Club**

This suit-based comparison is a project-specific rule used to resolve ties.

## Perfect Shuffle Algorithm

The perfect shuffle divides the deck into two halves and interleaves cards from each half in alternating order.

Unlike a random shuffle, the perfect shuffle follows a deterministic pattern.

The user can specify how many times the deck should be shuffled before card distribution.

## Object-Oriented Programming Concepts

This project applies several OOP principles:

### Encapsulation
Each class manages its own data and behavior, such as the cards stored in a deck or the information associated with a player.

### Abstraction
Complex operations, including shuffling and card comparison, are organized into dedicated methods that can be called without repeating their implementation.

### Polymorphism
The `Card` class implements the `Comparable<Card>` interface and provides its own `compareTo()` method for comparing playing cards.

### Modular Design
The program separates responsibilities across multiple classes, making the source code easier to understand, maintain, and extend.

## How to Run

### Requirements

- Windows PC
- Java Development Kit (JDK 21 or compatible version)
- Terminal with UTF-8 and Unicode support

### Option 1: Automatic Launcher

1. Download or clone this repository.
2. Open the main project folder.
3. Double-click `RUN_GAME_FIXED.bat`.
4. Follow the instructions displayed in the terminal.

The launcher handles Java compilation and starts the program automatically when a compatible JDK is available.

### Option 2: Manual Execution

Open PowerShell in the project root directory and run:

```powershell
javac -encoding UTF-8 -d out (Get-ChildItem .\src\cardgame\*.java).FullName
java -cp out cardgame.WarCardGame
```

If Unicode card symbols appear as question marks, the terminal and Java output encoding may need to be configured for UTF-8.

## Project Objectives

- Apply Object-Oriented Programming principles in Java.
- Develop a functional console-based multiplayer card game.
- Implement a deterministic perfect shuffle algorithm.
- Practice input validation, loops, conditional statements, and collections.
- Improve problem-solving and program organization through modular class design.

## Project Status

**Functional — Developed for academic learning and programming practice.**

## Learning Outcomes

Through this project, I practiced designing Java classes, managing objects and collections, implementing algorithms, debugging runtime issues, and organizing a program into reusable components.

The project also helped strengthen my understanding of how separate classes communicate to build one complete application.

---
