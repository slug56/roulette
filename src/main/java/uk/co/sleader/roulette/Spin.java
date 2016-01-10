package uk.co.sleader.roulette;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a round of a game of roulette - that is: the bets placed on the
 * table, spinning the ball and the resulting winning pocket.
 */
public class Spin {

    private Pocket winningPocket; // The number the ball lands on
    private Set<Bet> bets; // The bets placed in this round of play

    public Spin() {
        bets = new HashSet<>();
    }

    /**
     * Add a bet to the bets that have currently being placed in this round.
     * I.e. if looking at the physical table, this would represent all chips
     * that have currently been placed.
     *
     * @param bet - the bet to add to this round of play
     */
    public void addBet(Bet bet) {
        // TODO If customer already has existing bet, add to it, otherwise it
        // will get overwritten
        bets.add(bet);
    }

    /**
     * Once the ball has been spun, make a note of the 'winning' pocket
     *
     * @param pocket - the pocket in which the ball has landed
     */
    public void setWinningPocket(Pocket pocket) {
        winningPocket = pocket;
    }

    /**
     * Get the pocket in which the ball has landed for this round of play
     *
     * @return the pocket in which the ball has landed
     */
    public Pocket getWinningPocket() {
        return winningPocket;
    }

    /**
     * Using the winning pocket, determine which of the bets placed in the
     * round of play are to be paid out
     *
     * @return the bets which the customers are considered to have won
     */
    public Set<Bet> getWinningBets() {
        // TODO What if the winning pocket hasn't been set yet?
        return bets.stream().filter(b -> b.isWinner(winningPocket)).collect
                (Collectors.toSet());
    }

    /**
     * Using the winning pocket, determine which of the bets placed in the
     * round of play are to have their stakes collected (i.e. have lost)
     *
     * @return the bets which the customers are considered to have lost
     */
    public Set<Bet> getLosingBets() {
        return bets.stream().filter(b -> !b.isWinner(winningPocket)).collect
                (Collectors.toSet());
    }

}
