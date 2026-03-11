package com.example.csc311_cardgame;

public class Card {

    public static final String HEARTS   = "Hearts";
    public static final String DIAMONDS = "Diamonds";
    public static final String CLUBS    = "Clubs";
    public static final String SPADES   = "Spades";

    private final String suit;
    private final int value; // 1=Ace, 2-10=face value, 11=Jack, 12=Queen, 13=King

    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() { return suit; }
    public int getValue() { return value; }

    public String getRankLabel() {
        return switch (value) {
            case 1  -> "A";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(value);
        };
    }

    public boolean isRed() {
        return suit.equals(HEARTS) || suit.equals(DIAMONDS);
    }

    /**
     * Returns the image filename for this card.
     * e.g. "ace_of_hearts.png", "6_of_diamonds.png", "king_of_spades.png"
     */
    public String getImageFilename() {
        String rank = switch (value) {
            case 1  -> "ace";
            case 11 -> "jack";
            case 12 -> "queen";
            case 13 -> "king";
            default -> String.valueOf(value);
        };
        return rank + "_of_" + suit.toLowerCase() + ".png";
    }

    @Override
    public String toString() {
        return getRankLabel() + " of " + suit;
    }
}