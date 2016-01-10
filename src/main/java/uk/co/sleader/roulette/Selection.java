package uk.co.sleader.roulette;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A selection is a group of one or more pockets on the roulette table. For
 * example, it could represent a single number (straight bet) such as "12",
 * street bets, or it could represent an outside bet such as Odd, 2nd Column
 * or 1st Half. It will also support named bets, such as Voisins du Zero.
 */
public class Selection {

    // A name representing this particular selection of numbers. E.g. If
    // they're all odd, name it "odd"!
    private String name;
    // The pockets/numbers that are contained within this bet
    private Set<Pocket> pockets;
    // The odds of the selection should it contain a winning number. E.g. a
    // straight bet would be 36 and an Odd bet would be 2
    private int payoff;

    public Selection(String name, int payoff, Pocket... pockets) {
        this.name = name;
        this.payoff = payoff;
        this.pockets = new HashSet<>();
        this.pockets.addAll(Arrays.asList(pockets));
    }

    /**
     * Determine if this selection includes the specified pocket
     *
     * @param pocket - the pocket to check
     * @return <code>true</code> if this selection contains the specified
     * pocket, <code>false</code> otherwise
     */
    public boolean contains(Pocket pocket) {
        return pockets.contains(pocket);
    }

    /**
     * Get the payoff (odds) if this bet has been won.
     *
     * @return the odds that will be paid out if this selection wins
     */
    public int getPayoff() {
        return payoff;
    }
}
