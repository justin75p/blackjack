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
}
