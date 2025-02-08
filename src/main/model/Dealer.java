package main.model;

public class Dealer extends CardHolder {

    private Deck deck;
    private Card hiddenCard;

    public Dealer() {
        deck = new Deck();
        // Dealer has two cards, one hidden
        cards.add(deck.draw());
        hiddenCard = deck.draw();
    }

    // MODIFIES: this
    // EFFECTS: determines if the dealer should hit or stand
    @Override
    public boolean playTurn() {
        // Dealer must hit
        if (getHandValue() <= 16) {
            cards.add(deck.draw());
            return true;
        } else { // Dealer must stand
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: draws a card and deals it to player
    public void deal(Player player) {
        Card card = deck.draw();
        player.getCards().add(card);
    }

    // MODIFIES: this
    // EFFECTS: shows the dealer's hidden card
    public void showHiddenCard() {
        cards.add(hiddenCard);
    }
    
}