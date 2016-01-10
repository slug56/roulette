package uk.co.sleader.roulette;

import org.junit.Before;
import org.junit.Test;
import uk.co.sleader.roulette.exceptions.RouletteGameException;
import uk.co.sleader.roulette.tables.Table;

import static junit.framework.Assert.fail;
import static org.junit.Assert.*;

/**
 * Tests representing the acceptance criteria for Stage Three of the CACI
 * Roulette game.
 */
public class StageThreeTest {

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
    public void testTenPoundOddBetAndHousePocketWinsHasNoWinnings() {
        try {
            // Given a customer has placed a bet of £10 on odd or even
            Selection odd = selections.oddOrEvenBet(true);
            Bet bet = game.placeBet(customer, odd, 1000);

            // When I spin the roulette wheel and the ball lands in pocket 0
            Pocket zero = table.lookupPocket("0");
            game.spin(zero);

            //Then the customer will receive £0 winnings
            assertFalse(bet.isWinner(zero));
            assertEquals(0, bet.calculateActualProfit(zero));
            // Testing on French roulette so half of stake returned
            assertEquals(500, customer.getBalance());
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
            e.printStackTrace();
        }
    }

    @Test
    public void testTenPoundEvenBetAndHousePocketWinsHasNoWinnings() {
        try {
            // Given a customer has placed a bet of £10 on odd or even
            Selection even = selections.oddOrEvenBet(false);
            Bet bet = game.placeBet(customer, even, 1000);

            // When I spin the roulette wheel and the ball lands in pocket 0
            Pocket zero = table.lookupPocket("0");
            game.spin(zero);

            //Then the customer will receive £0 winnings
            assertFalse(bet.isWinner(zero));
            assertEquals(0, bet.calculateActualProfit(zero));
            // Testing on French roulette so half of stake returned
            assertEquals(500, customer.getBalance());
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
            e.printStackTrace();
        }
    }

    @Test
    public void testTenPoundEvenBetAndEvenWinsTwentyPounds() {
        try {
            // Given a customer has placed a bet of £10 on even
            Selection even = selections.oddOrEvenBet(false);
            Bet bet = game.placeBet(customer, even, 1000);

            // When I spin the roulette wheel and the ball lands on an even
            // pocket
            Pocket two = table.lookupPocket("2");
            game.spin(two);

            // Then the customer will receive £20 winnings
            assertTrue(bet.isWinner(two));
            assertEquals(2000, bet.calculateActualProfit(two));
            assertEquals(3000, customer.getBalance());
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
            e.printStackTrace();
        }
    }

    @Test
    public void testTenPoundEvenBetAndOddLoses() {
        try {
            // Given a customer has placed a bet of £10 on even
            Selection even = selections.oddOrEvenBet(false);
            Bet bet = game.placeBet(customer, even, 1000);

            // When I spin the roulette wheel and the ball lands on an odd
            // pocket
            Pocket one = table.lookupPocket("1");
            game.spin(one);

            // Then the customer will receive £0 winnings
            assertFalse(bet.isWinner(one));
            assertEquals(0, bet.calculateActualProfit(one));
            assertEquals(0, customer.getBalance());
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
            e.printStackTrace();
        }
    }

    @Test
    public void testTenPoundOddBetAndEvenLoses() {
        try {
            // Given a customer has placed a bet of £10 on odd
            Selection odd = selections.oddOrEvenBet(true);
            Bet bet = game.placeBet(customer, odd, 1000);

            // When I spin the roulette wheel and the ball lands on an even
            // pocket
            Pocket two = table.lookupPocket("2");
            game.spin(two);

            // Then the customer will receive £0 winnings
            assertFalse(bet.isWinner(two));
            assertEquals(0, bet.calculateActualProfit(two));
            assertEquals(0, customer.getBalance());
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
            e.printStackTrace();
        }
    }

    @Test
    public void testTenPoundOddBetAndOddWinsTwentyPounds() {
        try {
            // Given a customer has placed a bet of £10 on odd
            Selection odd = selections.oddOrEvenBet(true);
            Bet bet = game.placeBet(customer, odd, 1000);

            // When I spin the roulette wheel and the ball lands on an odd
            // pocket
            Pocket one = table.lookupPocket("1");
            game.spin(one);

            // Then the customer will receive £20 winnings
            assertTrue(bet.isWinner(one));
            assertEquals(2000, bet.calculateActualProfit(one));
            assertEquals(3000, customer.getBalance());
        } catch (RouletteGameException e) {
            fail("Unexpected exception");
            e.printStackTrace();
        }
    }

}