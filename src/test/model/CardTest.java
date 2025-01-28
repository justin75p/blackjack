package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import main.model.Card;
import main.model.Rank;
import main.model.Suit;

public class CardTest {
    private Card card;

    @BeforeEach
    void runBefore() {
        card = new Card(Rank.KING, Suit.CLUBS);
    }

    @Test
    void testCardRank() {
        assertEquals(Rank.KING, card.getRank());
    }

    @Test
    void testCardSuit() {
        assertEquals(Suit.CLUBS, card.getSuit());
    }

    @Test
    void testCardValue() {
        assertEquals(10, card.getRank().getValue());
    }
}
