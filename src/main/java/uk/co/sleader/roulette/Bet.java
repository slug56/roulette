package uk.co.sleader.roulette;

import uk.co.sleader.roulette.exceptions.IllegalBetException;

/**
 * Ties together a specific customer, the number(s) they've chosen and the
 * monetary value they're staking on these numbers. E.g. Fred Bloggs is
 * putting a £10.00 straight-bet on the number 12.
 */
public class Bet {

    private Customer customer;
    private Selection selection;
    private int stake;

    /**
     * Create the bet characterised by the specified parameters.
     *
     * @param customer  - the customer making the bet
     * @param selection - the numbers on the table they're betting on
     * @param stake     - the amount of money they're staking
     * @throws IllegalBetException If the stake is not a positive value, it
     *                             is invalid
     */
    public Bet(Customer customer, Selection selection, int stake) throws
            IllegalBetException {
        if (!validateBet(stake)) {
            throw new IllegalBetException(String.format("Stake of %s is not " +
                    "valid. Must be >=0", stake));
        }
        this.customer = customer;
        this.selection = selection;
        this.stake = stake;
    }

    /**
     * Calcualte the potential returns from this bet. For example, if £10.00
     * has been placed on a single number (straight-bet) then the protential
     * profit should be £360.00.
     *
     * @return the profit made on this bet if it were to win
     */
    public int calculatePotentialProfit() {
        return selection.getPayoff() * stake;
    }

    /**
     * Taking the result from the corresponding spin, calculate how much
     * money this bet has actually made, which could also be 0 if it is not a
     * winning bet.
     *
     * @param winningPocket - the pocket which has come up on this spin
     * @return the value this bet has actually made, calculated using the
     * original stake. If the bet is not a winning one then the profit will be 0
     */
    public int calculateActualProfit(Pocket winningPocket) {
        // Calculate payoff if a winner, otherwise a losing bet reaps no rewards
        return isWinner(winningPocket) ? (selection.getPayoff() * stake) : 0;
    }

    /**
     * Determine if this bet is a winning bet. An outside bet
     * where the table returns half of the original
     * stake when a house number comes up is not a
     * winning bet.
     *
     * @param winningPocket The Pocket object representing the pocket which
     *                      has come up in the spin
     * @return <code>true</code> if this bet has won,
     * <code>false</code> otherwise
     */
    public boolean isWinner(Pocket winningPocket) {
        return selection.contains(winningPocket);
    }

    /**
     * Get the customer who made this bet
     *
     * @return the customer who made this bet
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Get the amount of money put on this bet
     *
     * @return the amount of money put on this bet
     */
    public int getStake() {
        return stake;
    }

    /**
     * Ensure the amount of money put on this bet is valid, i.e. > 0
     *
     * @param stake - the specified amount
     * @return <code>true</code> if the amount if valid, <code>false</code>
     * otherwise
     */
    public static boolean validateBet(int stake) {
        // constraints of any min/max bets
        return stake > 0;
    }

}
