package uk.co.sleader.roulette;

/**
 * Ties together a specific customer, the number(s) they've chosen and the monetary value they're staking.
 */
public class Bet {

    private Customer customer;
    private Selection selection;
    private int stake;

    public Bet(Customer customer, Selection selection , int stake) {
        this.customer = customer;
        this.selection = selection;
        this.stake = stake;
    }

    public int calculatePotentialProfit() {
       return selection.getPayoff() * stake;
    }

    public int calculateActualProfit(String winningPocket) {
        // Calculate payoff if a winner, otherwise a losing bet reaps no rewards
        return isWinner(winningPocket) ? (selection.getPayoff() * stake) : 0;
    }

    public boolean isWinner(String winningPocket) {
        return selection.contains(winningPocket);
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getStake() {
        return stake;
    }

}
