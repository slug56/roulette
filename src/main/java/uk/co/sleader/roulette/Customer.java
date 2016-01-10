package uk.co.sleader.roulette;

/**
 * Represents a gambler paying roulette!
 */
public class Customer {

    // Using pence, int supports >= £21,474,836.47
    private int availableBalance; // Available to place on new bets
    private int earmarkedBalance; // Amount 'on the table' in current bets

    /**
     * Increase the number of chips available to the customer to invest in
     * further bets
     *
     * @param amount - the number of chips to add to the existing balance
     */
    public synchronized void deposit(int amount) {
        // TODO Exception if amount is negative or zero
        availableBalance += amount;
    }

    /**
     * Withdraw the chip amount, taking the specified number away from the
     * available balance (i.e. customer is cashing in their winnings)
     *
     * @param amount - the amount to withdraw as chips
     */
    public synchronized void withdraw(int amount) {
        // TODO Exception if amount is negative
        // TODO Exception if withdrawal request exceeds availableBalance
        availableBalance -= amount;
    }

    /**
     * Check the customer's current available balance
     *
     * @return - the number of chips available to invest in new bets
     */
    public synchronized int getBalance() {
        return availableBalance;
    }

    /**
     * Represents the action of taking chips and placing them on the table in
     * a bet, making them unavailable to invest elsewhere. The chips are not
     * actually lost - that depends on the outcome of the spin.
     *
     * @param wager - the number of chips that are no longer available to use
     *              elsewhere
     * @return <code>true</code> if the action was successful (there was
     * enough money in the available balance), <code>false</code> otherwise.
     * In this instance the available balance will remain unaffected and the
     * chips will not be earmarked
     */
    public synchronized boolean earmark(int wager) {
        boolean earmarked = false;
        if (earmarked = availableBalance >= wager) {
            // User has enough funds, so mark out the chips
            availableBalance -= wager;
            earmarkedBalance += wager;
        }
        return earmarked;
    }

    /**
     * If a customer places a bet and wins, their original stake will be
     * returned to them. This method represents taking chips 'back off the
     * table' and returning them to the customer so they can either be
     * withdrawn or reinvested in further bets.
     *
     * @param wager - the amount of money to transfer from the earmarked
     *              balance to the available balance
     */
    public synchronized void releaseEarmark(int wager) {
        // TODO Better system would be to keep record of earmarked bets
        // TODO What if wager > earmarkedBalance?
        earmarkedBalance -= wager;
        availableBalance += wager;
    }

    /**
     * In French roulette, if the customer makes an outside bet and a house
     * number comes up, they will have half of their original bet returned to
     * them.
     *
     * @param wager    - the chip amount of the original bet
     * @param houseCut - the amount of this wager to be retained by the house
     */
    public synchronized void collectPartialEarmark(int wager, int houseCut) {
        // TODO houseCut must be <= wager (usually half)
        // TODO What if wager > earmarkedBalance...?
        earmarkedBalance -= wager;
        availableBalance += (wager - houseCut);
    }

    /**
     * If the customer places a bet and then loses, the house will take their
     * original stake. These chips will then no longer be available to the
     * customer for further reinvestment or withdrawal.
     *
     * @param wager - the amount to be taken by the house
     */
    public synchronized void collectEarmark(int wager) {
        // TODO Shouldn't happen, but what if wager > earmarkedBalance?
        earmarkedBalance -= wager;
    }

}
