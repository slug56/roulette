package uk.co.sleader.roulette;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sleader on 05/01/2016.
 */
public class Spin {

    private String winningPocket;
    private Set<Bet> bets;

    public Spin() {
        bets = new HashSet<>();
    }

    public void addBet(Bet bet) {
        // TODO If customer already has existing bet, add to it
        bets.add(bet);
    }

    public void setWinningPocket(String pocket) {
        winningPocket = pocket;
    }

    public Set<Bet> getBets() {
        return bets;
    }

    public Set<Bet> getWinningBets() {
        return bets.stream().filter(b -> b.isWinner(winningPocket)).collect(Collectors.toSet());
    }

    public Set<Bet> getLosingBets() {
        return bets.stream().filter(b -> !b.isWinner(winningPocket)).collect(Collectors.toSet());
    }

}
