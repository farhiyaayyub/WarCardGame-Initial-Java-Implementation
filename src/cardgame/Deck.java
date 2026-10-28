package cardgame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Deck {

    private final Deque<Card> cards = new ArrayDeque<>();

    public Deck() {
    }

    public Deck(List<Card> initialCards) {
        cards.addAll(initialCards);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int size() {
        return cards.size();
    }

    public Card removeTop() {
        return cards.removeFirst();
    }

    public void addBottom(Card card) {
        cards.addLast(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void perfectShuffle() {
        List<Card> original = getCards();

        if (original.size() % 2 != 0) {
            throw new IllegalStateException(
                "Perfect shuffle requires an even number of cards."
            );
        }

        int half = original.size() / 2;
        cards.clear();

        for (int i = 0; i < half; i++) {
            addBottom(original.get(i));
            addBottom(original.get(i + half));
        }
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
