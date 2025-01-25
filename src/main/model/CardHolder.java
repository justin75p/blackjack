package main.model;

import java.util.ArrayList;
import java.util.List;

public abstract class CardHolder {
    private List<Card> cards;

    public CardHolder() {
        cards = new ArrayList<>();
    }

    // EFFECTS: returns the total value of cards in hand
    public int getHandValue() {
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: clears the holder's cards
    public void resetHand() {
        cards.clear();
    }

    // EFFECTS: checks if holder has a blackjack
    public boolean hasBlackjack() {
        return false;
    }

    public abstract void playTurn();
}
