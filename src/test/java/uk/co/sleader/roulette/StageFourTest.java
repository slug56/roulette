package uk.co.sleader.roulette;

import org.junit.Before;
import org.junit.Test;
import uk.co.sleader.roulette.exceptions.RouletteGameException;
import uk.co.sleader.roulette.tables.AmericanTable;

import static org.junit.Assert.*;

/**
 * Created by sleader on 05/01/2016.
 */
public class StageFourTest {

    private Game game;
    private Customer customer;
    private SelectionFactory selections;

    @Before
    public void setUp() {
        // Represents the notion of game on an American roulette table
        game = new Game(new AmericanTable());

        // A new customer with chips to the value of $10 (1000c)
        customer = new Customer();
        customer.deposit(1000);

        // The game's table's possible selections (e.g. 12, red, 2nd half, voisins du zero)
        selections = game.getTable().getSelectionFactory();
    }

    @Test
    public void testDoubleZeroCanBeBetOnAmericanTable() {
        try {
            // TODO It seems all assertions are back to front. Expected then actual!
            // Given a customer places a bet of $10 on 00 pocket
            Selection selection = selections.straightBet("00");
            Bet bet = game.placeBet(customer, selection, 1000);

            // When I spin the roulette wheel and ball lands in this pocket
            Pocket doubleZero = selections.lookupPocket("00");
            game.spin(doubleZero);

            // Then the customer will receive $360 winnings
            assertTrue(bet.isWinner(doubleZero));
            assertEquals(bet.calculateActualProfit(doubleZero), 36000);
            assertEquals(customer.getBalance(), 37000);
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
        }
    }

}