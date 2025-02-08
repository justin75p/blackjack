package main.model;

// Represents a Card, having a rank and a suit
public class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // EFFECTS: returns rank of card
    public Rank getRank() {
        return rank;
    }

    // EFFECTS: returns suit of card
    public Suit getSuit() {
        return suit;
    }
}
