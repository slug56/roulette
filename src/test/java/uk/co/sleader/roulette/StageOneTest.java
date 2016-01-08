package uk.co.sleader.roulette;

import org.junit.Before;
import org.junit.Test;
import uk.co.sleader.roulette.exceptions.RouletteGameException;

import static org.junit.Assert.*;

/**
 * Created by sleader on 05/01/2016.
 */
public class StageOneTest {

    private Game game;
    private Customer customer;
    private SelectionFactory selections;

    @Before
    public void setUp() {
        // Represents the notion of game on a roulette table
        game = new Game();

        // A new customer with chips to the value of £10 (1000p)
        customer = new Customer();
        customer.deposit(1000);

        // The game's table's possible selections (e.g. 12, red, 2nd half, voisins du zero)
        selections = game.getTable().getSelectionFactory();
    }

    @Test
    public void testLosingStraightBetReturnsNoWinnings() {
        try {
            // Given a customer places a bet of £10 on a pocket
            Selection selection = selections.straightBet("12");
            Bet losingBet = game.placeBet(customer, selection, 1000);

            // When I spin the roulette wheel and ball lands in a losing pocket
            Pocket winningPocket = selections.lookupPocket("11");
           game.spin(winningPocket);

            // Then the customer will receive £0 winnings
            assertFalse(losingBet.isWinner(winningPocket));
            assertEquals(losingBet.calculateActualProfit(winningPocket), 0);
            assertEquals(customer.getBalance(), 0);
        } catch (RouletteGameException e) {
            fail();
        }
    }

    @Test
    public void testWinningStraightBetReturnsWinnings() {
        try {
            // Given a customer places a bet of £10 on a pocket
            Selection selection = selections.straightBet("11");
            Bet winningBet = game.placeBet(customer, selection, 1000);
            // When I spin the roulette wheel and the ball lands in a winning pocket
            Pocket winningPocket = selections.lookupPocket("11");
            game.spin(winningPocket);

            // Then the customer will receive £360 winnings
            assertTrue(winningBet.isWinner(winningPocket));
            assertEquals(winningBet.calculateActualProfit(winningPocket), 36000);
            assertEquals(customer.getBalance(), 37000);
        } catch (RouletteGameException e) {
            fail();
        }
    }

}