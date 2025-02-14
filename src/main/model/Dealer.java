package main.model;

public class Dealer extends CardHolder {

    private Deck deck;
    private Card hiddenCard;

    public Dealer() {
        deck = new Deck();
        // Dealer has two cards, one hidden
        hiddenCard = deck.draw();
        cards.add(hiddenCard);
        cards.add(deck.draw());
        
    }

    // MODIFIES: this
    // EFFECTS: determines if the dealer should hit or stand
    @Override
    public void playTurn(boolean hit) {
        // Dealer must hit
        if (hit) {
            if (getHandValue() <= 16) {
                cards.add(deck.draw());
            }   
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