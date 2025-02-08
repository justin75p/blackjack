package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a deck of cards using 6 decks
public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    // MODIFIES: this
    // EFFECTS: initializes the deck using 6 decks of cards
    public void initializeDeck() {
        for (int i = 0; i < 6; i++) {
            for (Suit suit : Suit.values()) {
                for (Rank rank : Rank.values()) {
                    cards.add(new Card(rank, suit));
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: shuffles the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // MODIFIES: this
    // EFFECTS: draws a card from deck
    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public List<Card> getCards() {
        return cards;
    }
}
