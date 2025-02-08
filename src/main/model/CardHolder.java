package main.model;

import java.util.ArrayList;
import java.util.List;

public abstract class CardHolder {
    protected List<Card> cards;
    protected int aceCount;

    public CardHolder() {
        cards = new ArrayList<>();
        aceCount = 0;
    }

    // EFFECTS: returns the total value of cards in hand
    public int getHandValue() {
        int value = 0;
        for (int i = 0; i < cards.size(); i++) {
            value += cards.get(i).getRank().getValue();
            if (cards.get(i).getRank() == Rank.ACE) {
                aceCount++;
            }
        }

        for (int i = 0; i < aceCount; i++) {
            // Ensure that if a player has an ace it will count as its maximum value
            if (value + 10 <= 21) {
                value += 10;
            }
        }
        return value;
    }

    // EFFECTS: returns the holder's cards
    public List<Card> getCards() {
        return cards;
    }

    // MODIFIES: this
    // EFFECTS: clears the holder's cards
    public void resetHand() {
        cards.clear();
        aceCount = 0;
    }

    // EFFECTS: checks if holder has a blackjack
    public boolean hasBlackjack() {
        if (cards.size() == 2) {
            Card firstCard = cards.get(0);
            Card secondCard = cards.get(1);

            if (firstCard.getRank() == Rank.ACE && secondCard.getRank().getValue() == 10) {
                return true;
            } else if (secondCard.getRank() == Rank.ACE && firstCard.getRank().getValue() == 10) {
                return true;
            }
        }

        return false;
    }

    public abstract void playTurn(boolean hit);
}
