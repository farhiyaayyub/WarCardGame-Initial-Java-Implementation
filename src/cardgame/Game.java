
package cardgame;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Player> players;
    private final Deck mainDeck;

    public Game(
        List<Card> originalCards,
        int numberOfPlayers,
        int numberOfShuffles
    ) {
        players = new ArrayList<>();
        mainDeck = new Deck(originalCards);

        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player(i));
        }

        prepareDeck(numberOfShuffles);
        distributeCards();
    }

    private void prepareDeck(int numberOfShuffles) {
        ConsoleUI.heading("PREPARING THE 52-CARD DECK");

        System.out.println();
        System.out.println("ORIGINAL DECK:");
        ConsoleUI.displayCards(mainDeck.getCards());

        System.out.println();
        System.out.println(
            "Performing " + numberOfShuffles +
            " perfect shuffle(s)..."
        );

        for (int i = 0; i < numberOfShuffles; i++) {
            mainDeck.perfectShuffle();
        }

        System.out.println();
        System.out.println("SHUFFLED DECK:");
        ConsoleUI.displayCards(mainDeck.getCards());
    }

    private void distributeCards() {
        ConsoleUI.heading("DEALING CARDS TO PLAYERS");

        int currentPlayer = 0;

        while (!mainDeck.isEmpty()) {
            Player player = players.get(currentPlayer);
            Card card = mainDeck.removeTop();
            player.getDeck().addBottom(card);

            currentPlayer =
                (currentPlayer + 1) % players.size();
        }

        System.out.println();
        System.out.println("All 52 cards have been distributed.");
        System.out.println();

        for (Player player : players) {
            System.out.println(
                "PLAYER " + player.getPlayerNumber() +
                " - " + player.getDeck().size() +
                " CARDS"
            );

            ConsoleUI.displayCards(player.getDeck().getCards());
            System.out.println();
        }
    }

    private Player findChampion() {
        for (Player player : players) {
            if (player.getDeck().size() == 52) {
                return player;
            }
        }

        return null;
    }

    private void checkEliminations() {
        for (Player player : players) {
            if (!player.isActive() &&
                !player.isEliminated()) {

                player.eliminate();

                System.out.println(
                    "Player " +
                    player.getPlayerNumber() +
                    " has been eliminated."
                );
            }
        }
    }

    private List<Card> collectCards(
        Player winner,
        List<Play> playedCards
    ) {
        List<Card> collected = new ArrayList<>();
        int totalPlayers = players.size();

        for (int offset = 0;
             offset < totalPlayers;
             offset++) {

            int playerNumber =
                ((winner.getPlayerNumber() - 1 + offset)
                % totalPlayers) + 1;

            for (Play play : playedCards) {
                if (play.getPlayer().getPlayerNumber()
                    == playerNumber) {

                    collected.add(play.getCard());
                    break;
                }
            }
        }

        for (Card card : collected) {
            winner.getDeck().addBottom(card);
        }

        return collected;
    }

    public void start() {
        ConsoleUI.heading("THE CARD BATTLE BEGINS!");

        long roundNumber = 1;

        while (true) {
            Player champion = findChampion();

            if (champion != null) {
                ConsoleUI.finalResult(
                    champion,
                    roundNumber - 1,
                    players
                );
                return;
            }

            ConsoleUI.startRound(roundNumber);

            List<Play> playedCards = new ArrayList<>();
            Play highestPlay = null;

            for (Player player : players) {
                if (!player.isActive()) {
                    continue;
                }

                Card card = player.getDeck().removeTop();
                Play play = new Play(player, card);
                playedCards.add(play);

                System.out.println(
                    "Player " +
                    player.getPlayerNumber() +
                    " plays: " + card.toColoredString()
                );

                if (highestPlay == null ||
                    card.compareTo(
                        highestPlay.getCard()
                    ) > 0) {

                    highestPlay = play;
                }
            }

            Player roundWinner =
                highestPlay.getPlayer();

            List<Card> collected = collectCards(
                roundWinner,
                playedCards
            );

            ConsoleUI.roundWinner(
                roundWinner,
                roundNumber,
                collected
            );

            checkEliminations();
            roundNumber++;
        }
    }
}
