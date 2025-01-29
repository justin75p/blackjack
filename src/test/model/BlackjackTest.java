package test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import main.model.Card;
import main.model.Dealer;
import main.model.Player;
import main.model.Rank;
import main.model.Suit;

public class BlackjackTest {

    private Dealer dealer;
    private Player player;
    private Card ace;
    private Card jack;
    private Card queen;
    private Card king;

    
    @BeforeEach
    void runBefore() {
        dealer = new Dealer();
        player = new Player();
        ace = new Card(Rank.ACE, Suit.HEARTS);
        jack = new Card(Rank.JACK, Suit.SPADES);
        queen = new Card(Rank.QUEEN, Suit.DIAMONDS);
        king = new Card(Rank.KING, Suit.CLUBS);
    }

    @Test
    void testPlayerWagerWin() {
        player.wager(100);
        List<Card> playerCards = player.getCards();
        playerCards.add(ace);
        playerCards.add(jack);
        assertTrue(player.hasBlackjack());

        // pays out even
        assertEquals(200, player.getBalance());

    }
    
    @Test
    void testPlayerInsufficientBalance() {
        player.wager(1000);
        assertEquals(player.getBalance(), 100);
    }

    @Test
    void testPlayerBust() {
        player.resetHand();
        List<Card> playerCards = player.getCards();
        playerCards.add(king);
        playerCards.add(queen);
        playerCards.add(jack);
        assertTrue(player.getHandValue() > 21);
        assertFalse(player.hasBlackjack());
    }

    @Test
    void testMultipleAces() {
        List<Card> playerCards = player.getCards();
        playerCards.add(new Card(Rank.ACE, Suit.CLUBS));
        playerCards.add(new Card(Rank.ACE, Suit.HEARTS));
        playerCards.add(new Card(Rank.NINE, Suit.DIAMONDS));
        assertEquals(player.getHandValue(), 21);
    }

    @Test
    void testDealerBust() {
        dealer.resetHand();
        List<Card> dealerHand = dealer.getCards();
        dealerHand.add(king);
        dealerHand.add(queen);
        dealerHand.add(jack);
        assertTrue(dealer.getHandValue() > 21);
        assertFalse(dealer.hasBlackjack());
    }

    @Test
    void testDealerStand() {
        dealer.resetHand();
        List<Card> dealerHand = dealer.getCards();
        dealerHand.add(king);
        dealerHand.add(new Card(Rank.SEVEN, Suit.HEARTS));
        assertEquals(dealer.getHandValue(), 17);
        dealer.playTurn();
        assertEquals(dealer.getHandValue(), 17);
    }

    @Test
    void testDealerDealInitialHand() {
        // Dealer has two cards
        assertEquals(2, dealer.getCards().size());
        dealer.deal(player);
        dealer.deal(player);
        assertEquals(2, player.getCards().size());
    }

    
}
