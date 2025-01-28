package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import main.model.Card;
import main.model.Deck;

public class DeckTest {
    private Deck deck;
    private List<Card> copyOfDeck;

    @BeforeEach
    void runBefore() {
        deck = new Deck();
        copyOfDeck = new ArrayList<>(deck.getCards());
    }

    @Test
    void testConstructor() {
        assertEquals(52 * 6, deck.getCards().size());
    }

    @Test
    void testShuffle() {
        deck.shuffle();
        assertFalse(deck.getCards().equals(copyOfDeck));
    }

    @Test
    void testDrawCard() {
        assertEquals(deck.draw(), copyOfDeck.get(copyOfDeck.size() - 1));
    }
}
