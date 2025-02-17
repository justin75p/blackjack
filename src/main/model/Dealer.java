package main.model;

public class Dealer extends CardHolder {

    private Deck deck;
    private Card hiddenCard;

    public Dealer() {
        deck = new Deck();
    }

    public void drawCard() {
        cards.add(deck.draw());
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

    // MODIFIES: this
    // EFFECTS: gets a new deck of cards
    public void resetDeck() {
        deck = new Deck();
    }
    
}