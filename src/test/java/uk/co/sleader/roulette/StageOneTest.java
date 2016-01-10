package uk.co.sleader.roulette;

import org.junit.Before;
import org.junit.Test;
import uk.co.sleader.roulette.exceptions.RouletteGameException;
import uk.co.sleader.roulette.tables.Table;

import static org.junit.Assert.*;

/**
 * Tests representing the acceptance criteria for Stage One of the CACI
 * Roulette game.
 */
public class StageOneTest {

    private Game game;
    private Table table;
    private Customer customer;
    private SelectionFactory selections;

    @Before
    public void setUp() {
        // Represents the notion of game on a roulette table
        game = new Game();
        table = game.getTable();

        // A new customer with chips to the value of £10 (1000p)
        customer = new Customer();
        customer.deposit(1000);

        // The game's table's possible selections (e.g. 12, red, 2nd half,
        // voisins du zero)
        selections = table.getSelectionFactory();
    }

    @Test
    public void testLosingStraightBetReturnsNoWinnings() {
        try {
            // Given a customer places a bet of £10 on a pocket
            Selection selection = selections.straightBet("12");
            Bet losingBet = game.placeBet(customer, selection, 1000);

            // When I spin the roulette wheel and ball lands in a losing pocket
            Pocket winningPocket = table.lookupPocket("11");
            game.spin(winningPocket);

            // Then the customer will receive £0 winnings
            assertFalse(losingBet.isWinner(winningPocket));
            assertEquals(0, losingBet.calculateActualProfit(winningPocket));
            assertEquals(0, customer.getBalance());
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
            e.printStackTrace();
        }
    }

    @Test
    public void testWinningStraightBetReturnsWinnings() {
        try {
            // Given a customer places a bet of £10 on a pocket
            Selection selection = selections.straightBet("11");
            Bet winningBet = game.placeBet(customer, selection, 1000);

            // When I spin the roulette wheel and the ball lands in a winning
            // pocket
            Pocket winningPocket = table.lookupPocket("11");
            game.spin(winningPocket);

            // Then the customer will receive £360 winnings
            assertTrue(winningBet.isWinner(winningPocket));
            assertEquals(36000, winningBet.calculateActualProfit(winningPocket));
            // i.e. Return of original stake along with £360 winnings
            assertEquals(37000, customer.getBalance());
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
            e.printStackTrace();
        }
    }

}