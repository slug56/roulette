package uk.co.sleader.roulette;

import org.junit.Before;
import org.junit.Test;
import uk.co.sleader.roulette.exceptions.RouletteGameException;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by sleader on 05/01/2016.
 */
public class StageThreeTest {

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
    public void testTenPoundOddBetAndHousePocketWinsHasNoWinnings() {
        try {
            // Given a customer has placed a bet of £10 on odd or even
            Selection odd = selections.oddOrEvenBet(true);
            Bet bet = game.placeBet(customer, odd, 1000);

            // When I spin the roulette wheel and the ball lands in pocket 0
            Pocket zero = selections.lookupPocket("0");
            game.spin(zero);

            //Then the customer will receive £0 winnings
            assertFalse(bet.isWinner(zero));
            assertEquals(bet.calculateActualProfit(zero), 0);
            assertEquals(customer.getBalance(), 500);
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testTenPoundEvenBetAndHousePocketWinsHasNoWinnings() {
        try {
            // Given a customer has placed a bet of £10 on odd or even
            Selection even = selections.oddOrEvenBet(false);
            Bet bet = game.placeBet(customer, even, 1000);

            // When I spin the roulette wheel and the ball lands in pocket 0
            Pocket zero = selections.lookupPocket("0");
            game.spin(zero);

            //Then the customer will receive £0 winnings
            assertFalse(bet.isWinner(zero));
            assertEquals(bet.calculateActualProfit(zero), 0);
            assertEquals(customer.getBalance(), 500);
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testTenPoundEvenBetAndEvenWinsTwentyPounds() {
        try {
            // Given a customer has placed a bet of £10 on even
            Selection even = selections.oddOrEvenBet(false);
            Bet bet = game.placeBet(customer, even, 1000);

            // When I spin the roulette wheel and the ball lands on an even pocket
            Pocket two = selections.lookupPocket("2");
            game.spin(two);

            // Then the customer will receive £20 winnings
            assertTrue(bet.isWinner(two));
            assertEquals(bet.calculateActualProfit(two), 2000);
            assertEquals(customer.getBalance(), 3000);
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testTenPoundEvenBetAndOddLoses() {
        try {
            // Given a customer has placed a bet of £10 on even
            Selection even = selections.oddOrEvenBet(false);
            Bet bet = game.placeBet(customer, even, 1000);

            // When I spin the roulette wheel and the ball lands on an odd pocket
            Pocket one = selections.lookupPocket("1");
            game.spin(one);

            // Then the customer will receive £0 winnings
            assertFalse(bet.isWinner(one));
            assertEquals(bet.calculateActualProfit(one), 0);
            assertEquals(customer.getBalance(), 0);
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testTenPoundOddBetAndEvenLoses() {
        try {
            // Given a customer has placed a bet of £10 on odd
            Selection odd = selections.oddOrEvenBet(true);
            Bet bet = game.placeBet(customer, odd, 1000);

            // When I spin the roulette wheel and the ball lands on an even pocket
            Pocket two = selections.lookupPocket("2");
            game.spin(two);

            // Then the customer will receive £0 winnings
            assertFalse(bet.isWinner(two));
            assertEquals(bet.calculateActualProfit(two), 0);
            assertEquals(customer.getBalance(), 0);
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void testTenPoundOddBetAndOddWinsTwentyPounds() {
        try {
            // Given a customer has placed a bet of £10 on odd
            Selection odd = selections.oddOrEvenBet(true);
            Bet bet = game.placeBet(customer, odd, 1000);

            // When I spin the roulette wheel and the ball lands on an odd pocket
            Pocket one = selections.lookupPocket("1");
            game.spin(one);

            // Then the customer will receive £20 winnings
            assertTrue(bet.isWinner(one));
            assertEquals(bet.calculateActualProfit(one), 2000);
            assertEquals(customer.getBalance(), 3000);
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
        }
    }

}