package uk.co.sleader.roulette;

import org.junit.Before;
import org.junit.Test;
import uk.co.sleader.roulette.exceptions.IllegalBetException;
import uk.co.sleader.roulette.exceptions.IllegalSelectionException;
import uk.co.sleader.roulette.exceptions.RouletteGameException;

import static org.junit.Assert.fail;

/**
 * Created by sleader on 05/01/2016.
 */
public class StageTwoTest {

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

    @Test(expected = IllegalBetException.class)
    public void testBetEqualToZeroThrowsRouletteGameException() throws RouletteGameException {
        // TODO Expecting exception. but only on placeBet and not on creating the selection
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
            } catch (IllegalBetException e1) {
                // Permitted and expected
            }
            // Test boundaries also
            customer.deposit(1000);
            try {
                game.placeBet(customer, selection, -1);
            } catch (IllegalBetException e1) {
                // Permitted and expected
            }

            try {
                game.placeBet(customer, selection, Integer.MIN_VALUE);
            } catch (IllegalBetException e1) {
                // Permitted and expected
            }
            // Then the application will throw a RouletteGameException with a suitable message
        } catch (IllegalSelectionException e2) {
            fail("Exception not expected");
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
            } catch (IllegalSelectionException e1) {
                // Permitted and expected
            }

            try {
                Selection selection = selections.straightBet("00");
                game.placeBet(customer, selection, 1000);
            } catch (IllegalSelectionException e1) {
                // Permitted and expected
            }

            try {
                Selection selection = selections.straightBet("");
                game.placeBet(customer, selection, 1000);
            } catch (IllegalSelectionException e1) {
                // Permitted and expected
            }

            // Then the application will throw a RouletteGameException with a suitable message
        } catch (IllegalBetException e2) {
            fail("Exception not expected");
        }
    }

}