package uk.co.sleader.roulette;

import uk.co.sleader.roulette.exceptions.IllegalBetException;
import uk.co.sleader.roulette.exceptions.RouletteGameException;

/**
 * Ties together a specific customer, the number(s) they've chosen and the monetary value they're staking.
 */
public class Bet {

    private Customer customer;
    private Selection selection;
    private int stake;

    public Bet(Customer customer, Selection selection , int stake) throws IllegalBetException {
        if (!validateBet(stake)) {
            throw new IllegalBetException(String.format("Stake of %s is not valid. Must be >=0", stake));
        }
        this.customer = customer;
        this.selection = selection;
        this.stake = stake;
    }

    public int calculatePotentialProfit() {
       return selection.getPayoff() * stake;
    }

    public int calculateActualProfit(Pocket winningPocket) {
        // Calculate payoff if a winner, otherwise a losing bet reaps no rewards
        return isWinner(winningPocket) ? (selection.getPayoff() * stake) : 0;
    }

    public boolean isWinner(Pocket winningPocket) {
        return selection.contains(winningPocket);
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getStake() {
        return stake;
    }

    public static boolean validateBet(int stake) {
        //TODO Given the Table object, could also verify bet is within the constraints of any min/max bets
        return stake > 0;
    }

}
