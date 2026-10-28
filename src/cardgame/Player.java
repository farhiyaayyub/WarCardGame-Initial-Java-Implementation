package cardgame;

public class Player {

    private final int playerNumber;
    private final Deck deck;
    private boolean eliminated;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
        this.deck = new Deck();
        this.eliminated = false;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isActive() {
        return !deck.isEmpty();
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public void eliminate() {
        eliminated = true;
    }
}
