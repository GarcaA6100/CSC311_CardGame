package com.example.csc311_cardgame;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        String[] suits = {Card.HEARTS, Card.DIAMONDS, Card.CLUBS, Card.SPADES};
        for (String suit : suits) {
            for (int value = 1; value <= 13; value++) {
                cards.add(new Card(suit, value));
            }
        }
        Collections.shuffle(cards);
    }

    public List<Card> dealFour() {
        if (cards.size() < 4) {
            cards.clear();
            String[] suits = {Card.HEARTS, Card.DIAMONDS, Card.CLUBS, Card.SPADES};
            for (String suit : suits) {
                for (int value = 1; value <= 13; value++) {
                    cards.add(new Card(suit, value));
                }
            }
            Collections.shuffle(cards);
        }
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            hand.add(cards.remove(0));
        }
        return hand;
    }
}