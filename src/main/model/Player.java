package main.model;

// Represents a player having cards and balance
public class Player extends CardHolder {
    private int balance;

    public Player() {
        balance = 0;
    }

    // MODIFIES: this
    // EFFECTS: player has the choice to hit, or stand.
    @Override
    public void playTurn() {

    }

    // MODIFIES: this
    // EFFECTS: places bet of amount
    public void wager(int amount) {

    }

    // MODIFIES: this
    // EFFECTS: adds amount to player's balance
    public void addWinnings(int amount) {

    }

    // EFFECTS: returns player's balance
    public int getBalance() {
        return balance;
    }
}
