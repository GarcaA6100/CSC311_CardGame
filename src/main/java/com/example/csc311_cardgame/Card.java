package com.example.csc311_cardgame;

public class Card {

    // The four suits in a deck
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

    // Returns the letter or number shown on the card (A, 2-10, J, Q, K)
    public String getRankLabel() {
        return switch (value) {
            case 1  -> "A";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(value);
        };
    }

    /** Returns true if the suit is Hearts or Diamonds (red cards) */
    public boolean isRed() {
        return suit.equals(HEARTS) || suit.equals(DIAMONDS);
    }


    // Returns the image file name for this card, e.g. "ace_of_hearts.png"
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