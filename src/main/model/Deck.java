package main.model;

import java.util.ArrayList;
import java.util.List;

// Represents a deck of cards using 6 decks
public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    // MODIFIES: this
    // EFFECTS: initializes the deck using 6 decks of cards
    public void initializeDeck() {
        
    }

    // MODIFIES: this
    // EFFECTS: shuffles the deck
    public void shuffle() {

    }

    // MODIFIES: this
    // EFFECTS: draws a random card
    public Card draw() {
        return null;
    }
}
