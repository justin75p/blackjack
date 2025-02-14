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

    // EFFECTS: returns the file image path of this card
    public String getImageFileName() {
        return "data/cards/" + getRankString() + "-" + getSuitString() + ".png";
    }

    private String getRankString() {
        switch (rank) {
            case ACE: return "A";
            case TWO: return "2";
            case THREE: return "3";
            case FOUR: return "4";
            case FIVE: return "5";
            case SIX: return "6";
            case SEVEN: return "7";
            case EIGHT: return "8";
            case NINE: return "9";
            case TEN: return "10";
            case JACK: return "J";
            case KING: return "K";
            case QUEEN: return "Q";
            default: return "";
        }
    }

    private String getSuitString() {
        switch (suit) {
            case CLUBS: return "C";
            case DIAMONDS: return "D";
            case HEARTS: return "H";
            case SPADES: return "S";
            default: return "";
        }
    }
}
