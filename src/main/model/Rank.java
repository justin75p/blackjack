package main.model;

// Represents a card's rank
public enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, KING, QUEEN;

    public int getValue() {
        switch (this) {
            case ACE:
                // Logic determining whether ace is 1 or 11 will be handled in Player
                return 1;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            case TEN:
            case JACK:
            case KING:
            case QUEEN:
                return 10;
            default:
                return 0;
        }
    }
}
