package uk.co.sleader.roulette;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sleader on 05/01/2016.
 */
public class Game {

    private Table table;
    private Spin currentSpin;
    private List<Spin> history;

    public Game() {
        table = new Table();
        // Initialise the first round
        currentSpin = new Spin();
        history = new ArrayList<>();
    }

    public Table getTable() {
        return table;
    }

    public Bet placeBet(Customer customer, Selection selection, int wager) {
        Bet bet = null;
        // Verify customer has enough chips to cover the stake
        if (customer.earmark(wager)) {
            bet = new Bet(customer, selection, wager);
            currentSpin.addBet(bet);
        }
        return bet;
    }

    public void spin() {
        spin(table.throwBall());
    }

    /**
     * Make a spin and use the defined winning pocket at the outcome
     * @param winningPocket
     * @return
     */
    public String spin(String winningPocket) {
        // TODO Close bets
        currentSpin.setWinningPocket(winningPocket);
        processBets(currentSpin);
        history.add(currentSpin);
        currentSpin = new Spin();
        return winningPocket;
    }

    private void processBets(Spin spin) {
        // Winners
        for (Bet win : spin.getWinningBets()) {
            final Customer customer = win.getCustomer();
            // Release earmarked stake
            customer.releaseEarmark(win.getStake());
            // Pay profits
            customer.deposit(win.calculatePotentialProfit());
        }
        // Losers
        for (Bet lose : spin.getLosingBets()) {
            // Retain original stake
           lose.getCustomer().collectEarmark(lose.getStake());
        }
    }
}
