
package cardgame;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleUI {

    public static void line() {
        System.out.println("====================================================");
    }

    public static void separator() {
        System.out.println("----------------------------------------------------");
    }

    public static void welcome() {
        System.out.println();
        System.out.println("          WELCOME TO WAR CARD GAME!");
        System.out.println();
        System.out.println("GAME RULES:");
        System.out.println("1. Each active player plays their top card.");
        System.out.println("2. The highest card wins the round.");
        System.out.println("3. The winner collects all played cards.");
        System.out.println("4. The first player to collect all 52 cards wins!");
        System.out.println();
    }

    public static void heading(String title) {
        System.out.println();
        line();
        System.out.println("             " + title);
        line();
    }

    public static void displayCards(List<Card> cards) {
        if (cards.isEmpty()) {
            System.out.println("(No cards)");
            return;
        }

        int count = 0;

        for (Card card : cards) {
            System.out.printf("%-5s", card.toColoredString());
            System.out.print("  ");
            count++;

            if (count % 8 == 0) {
                System.out.println();
            }
        }

        if (count % 8 != 0) {
            System.out.println();
        }
    }

    public static void displayDeck(Player player, String label) {
        heading("PLAYER " + player.getPlayerNumber() + "'S " + label);
        System.out.println();
        System.out.println("Total Cards: " + player.getDeck().size());
        System.out.println();
        displayCards(player.getDeck().getCards());
    }

    public static void displayPlayerStatus(List<Player> players) {
        heading("PLAYER STATUS");
        System.out.println();

        for (Player player : players) {
            System.out.printf(
                "Player %-2d : %-12s (%d Cards)%n",
                player.getPlayerNumber(),
                player.isEliminated() ? "ELIMINATED" : "ACTIVE",
                player.getDeck().size()
            );
        }
    }

    public static void startRound(long round) {
        System.out.println();
        separator();
        System.out.println("                START ROUND " + round);
        separator();
    }

    public static void roundWinner(
        Player winner,
        long round,
        List<Card> collected
    ) {
        System.out.println();
        System.out.println(
            "ROUND " + round + " WINNER: PLAYER " +
            winner.getPlayerNumber()
        );

        String collectedCards = collected.stream()
            .map(Card::toColoredString)
            .collect(Collectors.joining(", "));

        System.out.println("COLLECTED CARDS: " + collectedCards);

        displayDeck(winner, "UPDATED DECK");
    }

    public static void finalResult(
        Player champion,
        long totalRounds,
        List<Player> players
    ) {
        displayPlayerStatus(players);
        heading("GAME RESULT");
        System.out.println();
        System.out.println(
            "              PLAYER " +
            champion.getPlayerNumber() + " WINS!"
        );
        System.out.println();
        System.out.println(
            "Reason: All other players have been eliminated."
        );
        System.out.println("Total Rounds Played: " + totalRounds);
        displayDeck(champion, "FINAL DECK");
    }

    public static void playAgainHeading() {
        System.out.println();
        System.out.println("             START A NEW GAME?");
        separator();
    }

    public static void goodbye() {
        System.out.println();
        System.out.println("             THANK YOU FOR PLAYING!");
        System.out.println("        SEE YOU IN THE NEXT CARD BATTLE!");
    }
}
