package cardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class WarCardGame {

    public static List<Card> loadCards(String filename)
        throws IOException {

        List<Card> cards = new ArrayList<>();
        Set<Card> uniqueCards = new HashSet<>();

        try (
            BufferedReader reader =
                Files.newBufferedReader(Paths.get(filename))
        ) {
            String line;

            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer =
                    new StringTokenizer(line, ", \t\r\n");

                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    String[] parts = token.split("-", -1);

                    if (parts.length != 2) {
                        throw new IllegalArgumentException(
                            "Invalid card format: " + token
                        );
                    }

                    Suit suit = Suit.fromCode(parts[0]);
                    Rank rank = Rank.fromCode(parts[1]);
                    Card card = new Card(suit, rank);

                    if (!uniqueCards.add(card)) {
                        throw new IllegalArgumentException(
                            "Duplicate card found: " + card
                        );
                    }

                    cards.add(card);
                }
            }
        }

        if (cards.size() != 52) {
            throw new IllegalArgumentException(
                "Invalid deck! Expected 52 cards, found " +
                cards.size()
            );
        }

        return cards;
    }

    public static int readNumber(
        Scanner scanner,
        String message,
        int minimum,
        int maximum
    ) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            if (!input.matches("[0-9]+")) {
                System.out.println(
                    "Invalid input! Enter numbers only."
                );
                System.out.println(
                    "Spaces, letters, and symbols are not allowed."
                );
                System.out.println();
                continue;
            }

            try {
                int number = Integer.parseInt(input);

                if (number >= minimum &&
                    number <= maximum) {
                    return number;
                }

            } catch (NumberFormatException exception) {
                System.out.println("The number is too large.");
                System.out.println();
                continue;
            }

            System.out.println(
                "Invalid number! Please enter a value from " +
                minimum + " to " + maximum + "."
            );
            System.out.println();
        }
    }

    public static boolean askPlayAgain(Scanner scanner) {
        while (true) {
            System.out.print(
                "Do you want to play again? (YES/NO): "
            );

            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("YES")) {
                return true;
            }

            if (answer.equalsIgnoreCase("NO")) {
                return false;
            }

            System.out.println(
                "Invalid input! Please enter YES or NO."
            );
        }
    }

    public static void main(String[] args) {
        ConsoleUI.welcome();

        List<Card> originalDeck;

        try {
            originalDeck = loadCards("input.txt");

        } catch (
            IOException |
            IllegalArgumentException exception
        ) {
            System.out.println(
                "ERROR: Unable to load input.txt."
            );
            System.out.println(exception.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            ConsoleUI.heading("GAME SETUP");
            System.out.println();

            int numberOfPlayers = readNumber(
                scanner,
                "Enter number of players (2-8): ",
                2,
                8
            );

            int numberOfShuffles = readNumber(
                scanner,
                "Enter number of perfect shuffles (1 or more): ",
                1,
                Integer.MAX_VALUE
            );

            Game game = new Game(
                originalDeck,
                numberOfPlayers,
                numberOfShuffles
            );

            game.start();

            ConsoleUI.playAgainHeading();
            playAgain = askPlayAgain(scanner);

        } while (playAgain);

        ConsoleUI.goodbye();
        scanner.close();
    }
}
