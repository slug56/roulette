package uk.co.sleader.roulette;

import org.junit.Before;
import org.junit.Test;
import uk.co.sleader.roulette.exceptions.RouletteGameException;
import uk.co.sleader.roulette.tables.AmericanTable;
import uk.co.sleader.roulette.tables.Table;

import static org.junit.Assert.*;

/**
 * Tests representing the acceptance criteria for Stage Four of the CACI
 * Roulette game.
 */
public class StageFourTest {

    private Game game;
    private Table table;
    private Customer customer;
    private SelectionFactory selections;

    @Before
    public void setUp() {
        // Represents the notion of game on an American roulette table
        game = new Game(new AmericanTable());
        table = game.getTable();

        // A new customer with chips to the value of $10 (1000c)
        customer = new Customer();
        customer.deposit(1000);

        // The game's table's possible selections (e.g. 12, red, 2nd half,
        // voisins du zero)
        selections = table.getSelectionFactory();
    }

    @Test
    public void testDoubleZeroCanBeBetOnAmericanTable() {
        try {
            // Given a customer places a bet of $10 on 00 pocket
            Selection selection = selections.straightBet("00");
            Bet bet = game.placeBet(customer, selection, 1000);

            // When I spin the roulette wheel and ball lands in this pocket
            Pocket doubleZero = table.lookupPocket("00");
            game.spin(doubleZero);

            // Then the customer will receive $360 winnings
            assertTrue(bet.isWinner(doubleZero));
            assertEquals(36000, bet.calculateActualProfit(doubleZero));
            assertEquals(37000, customer.getBalance());
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
            e.printStackTrace();
        }
    }

}