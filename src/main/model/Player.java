package main.model;

import java.util.List;
import java.util.ArrayList;

// Represents a player having cards and balance
public class Player {
    private List<Card> cards;
    private int balance;

    public Player() {
        cards = new ArrayList<Card>();
        balance = 0;
    }

    // MODIFIES: this
    // EFFECTS: draw a Card
    public void hit() {

    }

    // EFFECTS: skip turn
    public void stand() {

    }

    // MODIFIES: this
    // EFFECTS: places bet of amount
    public void wager(int amount) {

    }

    // EFFECTS: returns the total value of the player's hand
    public int getHandValues() {
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: adds amount to player's balance
    public void addWinnings(int amount) {

    }

    // MODIFIES: this
    // EFFECTS: clears the player's cards
    public void resetHand() {
        cards.clear();
    }

    public int getBalance() {
        return balance;
    }
}
