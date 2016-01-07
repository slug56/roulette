package uk.co.sleader.roulette;

import org.junit.Before;
import org.junit.Test;
import uk.co.sleader.roulette.exceptions.RouletteGameException;

import static org.junit.Assert.*;

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

    @Test(expected = RouletteGameException.class)
    public void betEqualToZeroThrowsRouletteGameException() throws RouletteGameException {

        // Given a customer has placed a bet
        // When that bet is less than or equal to £0
        Selection selection = selections.straightBet("12");
        game.placeBet(customer, selection, 0);
        // Then the application will throw a RouletteGameException with a suitable message
    }

    @Test(expected = RouletteGameException.class)
    public void betOfLessThanZeroThrowsRouletteGameException() throws RouletteGameException {
        // Given a customer has placed a bet
        // When that bet is less than or equal to £0
        Selection selection = selections.straightBet("12");
        game.placeBet(customer, selection, -1000);
        // Then the application will throw a RouletteGameException with a suitable message
    }

    @Test(expected = RouletteGameException.class)
    public void betOnInvalidPocketThrowsRouletteGameException() throws RouletteGameException {
        // Given a customer has placed a bet
        // When a customer has selected an invalid pocket
        Selection selection = selections.straightBet("invalidPocket");
        game.placeBet(customer, selection, 1000);
        // Then the application will throw a RouletteGameException with a suitable message
    }

}