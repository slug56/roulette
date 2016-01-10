package uk.co.sleader.roulette;

import org.junit.Before;
import org.junit.Test;
import uk.co.sleader.roulette.exceptions.IllegalBetException;
import uk.co.sleader.roulette.exceptions.IllegalSelectionException;
import uk.co.sleader.roulette.exceptions.RouletteGameException;
import uk.co.sleader.roulette.tables.Table;

import static org.junit.Assert.fail;

/**
 * Tests representing the acceptance criteria for Stage Two of the CACI
 * Roulette game.
 */
public class StageTwoTest {

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

        // The game's table's possible selections (e.g. 12, red, 2nd half, voisins du zero)
        selections = table.getSelectionFactory();
    }

    @Test(expected = IllegalBetException.class)
    public void testBetEqualToZeroThrowsRouletteGameException() throws RouletteGameException {
        // Given a customer has placed a bet
        // When that bet is less than or equal to £0
        Selection selection = selections.straightBet("12");
        game.placeBet(customer, selection, 0);
        // Then the application will throw a RouletteGameException with a suitable message
    }

    @Test
    public void testBetOfLessThanZeroThrowsRouletteGameException() {
        // TODO Expecting exception. but only on placeBet and not on creating the selection
        try {
            // Given a customer has placed a bet
            Selection selection = selections.straightBet("12");

            // When that bet is less than or equal to £0
            try {
                game.placeBet(customer, selection, -1000);
                fail("Exception should have been thrown.");
            } catch (IllegalBetException e1) {
                // Permitted and expected
            }
            // Test boundaries also
            customer.deposit(1000);
            try {
                game.placeBet(customer, selection, -1);
                fail("Exception should have been thrown.");
            } catch (IllegalBetException e1) {
                // Permitted and expected
            }

            try {
                game.placeBet(customer, selection, Integer.MIN_VALUE);
                fail("Exception should have been thrown.");
            } catch (IllegalBetException e1) {
                // Permitted and expected
            }
            // Then the application will throw a RouletteGameException with a suitable message
        } catch (IllegalSelectionException e2) {
            fail("Exception not expected");
            e2.printStackTrace();
        }
    }

    @Test
    public void testBetOnInvalidPocketThrowsRouletteGameException() {
        try {
            // Given a customer has placed a bet
            // When a customer has selected an invalid pocket
            try {
                Selection selection = selections.straightBet("invalidPocket");
                game.placeBet(customer, selection, 1000);
                fail("Exception should have been thrown.");
            } catch (IllegalSelectionException e1) {
                // Permitted and expected
            }

            try {
                Selection selection = selections.straightBet("00");
                game.placeBet(customer, selection, 1000);
                fail("Exception should have been thrown.");
            } catch (IllegalSelectionException e1) {
                // Permitted and expected
            }

            try {
                Selection selection = selections.straightBet("");
                game.placeBet(customer, selection, 1000);
                fail("Exception should have been thrown.");
            } catch (IllegalSelectionException e1) {
                // Permitted and expected
            }

            // Then the application will throw a RouletteGameException with a suitable message
        } catch (IllegalBetException e2) {
            fail("Exception not expected");
            e2.printStackTrace();
        }
    }

}