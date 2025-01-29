package main.model;

public class Dealer extends CardHolder {

    private Deck deck;

    public Dealer() {
        deck = new Deck();
        // Dealer has two cards, one hidden
        cards.add(deck.draw());
        cards.add(deck.draw());
    }

    // MODIFIES: this
    // EFFECTS: determines if the dealer should hit or stand
    @Override
    public void playTurn() {
        
    }

    // MODIFIES: this
    // EFFECTS: draws a card and deals it to player
    public void deal(Player player) {

    }
    
}