package uk.co.sleader.roulette;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sleader on 05/01/2016.
 */
public class Selection {

    private String name;
    private Set<Pocket> pockets;
    private int payoff;

    public Selection(String name, int payoff, Pocket... pockets) {
        this.name = name;
        this.payoff = payoff;
        this.pockets = new HashSet<>();
        this.pockets.addAll(Arrays.asList(pockets));
    }

    public boolean contains(Pocket winningPocket) {
        return pockets.contains(winningPocket);
    }

    public int getPayoff() {
        return payoff;
    }
}
