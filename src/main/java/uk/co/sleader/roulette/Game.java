package uk.co.sleader.roulette;

import uk.co.sleader.roulette.exceptions.IllegalBetException;
import uk.co.sleader.roulette.tables.FrenchTable;
import uk.co.sleader.roulette.tables.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the high level functionality of the roulette game, which will
 * then interact with the sub-components. For example, Game allows bets to be
 * placed, the ball to be spun...
 */
public class Game {

    private Table table;
    private Spin currentSpin;
    // Maintain a game history
    private List<Spin> history;

    /**
     * Create a new game of roulette using the default table
     */
    public Game() {
        // Default table
        this(new FrenchTable());
    }

    /**
     * Create a new game of roulette using the specified table
     *
     * @param table - the specific variation of roulette to play
     */
    public Game(Table table) {
        this.table = table;
        // Initialise the first round
        currentSpin = new Spin();
        history = new ArrayList<>();
    }

    /**
     * Get the table that this game of roulette is being played on
     *
     * @return - the game table
     */
    public Table getTable() {
        return table;
    }

    /**
     * Place a new bet on the table in the current spin/round
     *
     * @param customer  - the customer who is placing the bet
     * @param selection - the number(s) on the table that are being bet on
     * @param wager     - the number of chips to put against the bet
     * @return an object encapsulating the bet that has been placed
     * @throws IllegalBetException If an issue has been found with the
     *                             validity of the bet
     */
    public Bet placeBet(Customer customer, Selection selection, int wager)
            throws IllegalBetException {
        // TODO Don't return null at all. Throw exception of customer doesn't
        // have funds. e.g. NotEnoughFundsException extends
        // RouletteGameException
        Bet bet = null;
        // Verify customer has enough chips to cover the stake
        if (customer.earmark(wager)) {
            bet = new Bet(customer, selection, wager);
            // Add this bet to all the bets placed on this spin
            currentSpin.addBet(bet);
        }
        return bet;
    }

    /**
     * Spin the balls for this round using a randomly generated number from
     * the table
     *
     * @return
     */
    public Pocket spin() {
        return spin(table.throwBall());
    }

    /**
     * Make a spin and use the defined winning pocket at the outcome (used
     * for testing purposes)
     *
     * @param winningPocket - the pocket that will win the spin
     * @return the pocket which has won the spin
     */
    public Pocket spin(Pocket winningPocket) {
        // TODO Close bets
        currentSpin.setWinningPocket(winningPocket);
        // Make payouts, collect stakes etc...
        processBets(currentSpin);
        // Save the round and play and set up a new one
        history.add(currentSpin);
        currentSpin = new Spin();
        return winningPocket;
    }

    /**
     * When a round is coming to an end (the ball has been spun), collect
     * stakes and payout winnings.
     *
     * @param spin - the spin for which the bets should be settled
     */
    private void processBets(Spin spin) {
        processWinningBets(spin);
        processLosingBets(spin);
    }

    /**
     * Payout bets which have won
     *
     * @param spin- the spin for which the bets should be settled
     */
    private void processWinningBets(Spin spin) {
        // Winners
        for (Bet win : spin.getWinningBets()) {
            final Customer customer = win.getCustomer();
            // Release earmarked stake
            customer.releaseEarmark(win.getStake());
            // Pay profits
            customer.deposit(win.calculatePotentialProfit());
        }
    }


    /**
     * Collect stakes from bets which have lost
     *
     * @param spin- the spin for which the bets should be settled
     */
    private void processLosingBets(Spin spin) {
        // Losers
        for (Bet lose : spin.getLosingBets()) {
            // If house number, identify stakes where only half the bet
            // is returned
            if (spin.getWinningPocket().isHouse() && table
                    .isHalfStakeOnLosingOutsideBets()) {
                final int stake = lose.getStake();
                // Only collect half of the original stake if the table
                // defines this rule
                lose.getCustomer().collectPartialEarmark(stake, stake / 2);
            } else {
                // Retain original stake
                lose.getCustomer().collectEarmark(lose.getStake());
            }
        }
    }

}
