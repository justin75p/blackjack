package main.model;

public class Dealer extends CardHolder {

    private Deck deck;

    public Dealer() {
        deck = new Deck();
    }

    // MODIFIES: this
    // EFFECTS: determines if the dealer should hit or stand
    @Override
    public void playTurn() {
        
    }
    
}