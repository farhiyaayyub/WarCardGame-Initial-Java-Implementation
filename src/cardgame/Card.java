
package cardgame;

public class Card implements Comparable<Card> {

    private static final String RED = "\u001B[91m";
    private static final String WHITE = "\u001B[97m";
    private static final String RESET = "\u001B[0m";

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public int compareTo(Card other) {
        int rankComparison = Integer.compare(
            other.rank.ordinal(),
            this.rank.ordinal()
        );

        if (rankComparison != 0) {
            return rankComparison;
        }

        return Integer.compare(
            other.suit.ordinal(),
            this.suit.ordinal()
        );
    }

    public String toColoredString() {
    String symbol;
    String color;

    switch (suit) {
        case DIAMOND:
            symbol = "\u2666";
            color = "\u001B[91m";
            break;
        case HEART:
            symbol = "\u2665";
            color = "\u001B[91m";
            break;
        case SPADE:
            symbol = "\u2660";
            color = "\u001B[97m";
            break;
        case CLUB:
            symbol = "\u2663";
            color = "\u001B[97m";
            break;
        default:
            throw new IllegalStateException("Unknown card suit.");
    }

    return color + symbol + "-" + rank.getCode() + "\u001B[0m";
}
    @Override
    public String toString() {
        return suit.getCode() + "-" + rank.getCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Card)) {
            return false;
        }

        Card other = (Card) object;
        return suit == other.suit && rank == other.rank;
    }

    @Override
    public int hashCode() {
        return 31 * suit.hashCode() + rank.hashCode();
    }
}
