package main.model;

// Represents a player having cards and balance
public class Player extends CardHolder {
    private final int INITIAL_BALANCE = 100;
    private double balance;
    private double amountWagered;

    public Player() {
        balance = INITIAL_BALANCE;
        amountWagered = 0;
    }

    // MODIFIES: this
    // EFFECTS: places bet of amount if amount <= balance and returns true, false otherwise
    public boolean wager(double amount) {
        if (amount <= balance) {
            balance -= amount;
            amountWagered = amount;
            return true;
        }
        return false;
    }

    public double getAmountWagered() {
        return amountWagered;
    }

    // MODIFIES: this
    // EFFECTS: adds amount to player's balance
    public void addWinnings(double amount) {
        balance += amount;
    }

    // EFFECTS: returns player's balance
    public double getBalance() {
        return balance;
    }
}
