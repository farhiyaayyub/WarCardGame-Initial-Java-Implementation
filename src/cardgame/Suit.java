package cardgame;

public enum Suit {
    DIAMOND("D"),
    HEART("H"),
    SPADE("S"),
    CLUB("C");

    private final String code;

    Suit(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Suit fromCode(String code) {
        for (Suit suit : values()) {
            if (suit.code.equalsIgnoreCase(code)) {
                return suit;
            }
        }
        throw new IllegalArgumentException("Invalid card suit: " + code);
    }
}
