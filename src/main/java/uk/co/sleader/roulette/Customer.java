package uk.co.sleader.roulette;

/**
 * Created by sleader on 05/01/2016.
 */
public class Customer {

    // Using pence, int supports >= £21,474,836.47
    private int availableBalance;
    private int earmarkedBalance;

    public synchronized void deposit(int amount) {
        // TODO Exception if amount is negative
        availableBalance += amount;
    }

    public synchronized void withdraw(int amount) {
        // TODO Exception if amount is negative
        // TODO Exception if withdrawal request exceeds availableBalance
        availableBalance -= amount;
    }

    public synchronized int getBalance() {
        return availableBalance;
    }

    public synchronized boolean earmark(int wager) {
        boolean earmarked = false;
        // TODO Synchronized?
        if (earmarked = availableBalance >= wager) {
            availableBalance -= wager;
            earmarkedBalance += wager;
        }
        return earmarked;
    }

    public synchronized void releaseEarmark(int wager) {
        earmarkedBalance -= wager;
        availableBalance += wager;
    }

    public synchronized void collectPartialEarmark(int wager, int houseCut) {
        // TODO Not really representing partial earmarks in this game...
        // In the case of a bet on RED when a house number comes up
        // TODO houseCut must be <= wager (usually half)
        earmarkedBalance -= wager;
        availableBalance += (wager - houseCut);
    }

    public synchronized void collectEarmark(int wager) {
        earmarkedBalance -= wager;
    }

}
