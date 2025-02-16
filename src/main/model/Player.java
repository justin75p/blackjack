package main.model;

// Represents a player having cards and balance
public class Player extends CardHolder {
    private final int INITIAL_BALANCE = 100;
    private int balance;
    private int amountWagered;

    public Player() {
        balance = INITIAL_BALANCE;
        amountWagered = 0;
    }

    // MODIFIES: this
    // EFFECTS: places bet of amount, balance is unchanged if amount > balance
    public void wager(int amount) {
        if (amount <= balance) {
            balance -= amount;
            amountWagered = amount;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds amount to player's balance
    public void addWinnings(int amount) {
        balance += amount;
        amountWagered = 0;
    }

    // EFFECTS: returns player's balance
    public int getBalance() {
        return balance;
    }
}
