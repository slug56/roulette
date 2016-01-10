package uk.co.sleader.roulette;

import uk.co.sleader.roulette.exceptions.IllegalSelectionException;
import uk.co.sleader.roulette.tables.Table;

import java.util.Arrays;

/**
 * Generates the selections (of numbers) that can be be taken forward to bet
 * on, whether these are single number selections (straight bets),
 * multi-number bets such as street bets, or outside and named bets such as
 * Odd, Red, 2nd Column, 1st Half or Voisins du Zero.
 * TODO Implement a cache so selections aren't being generated over and over
 */
public class SelectionFactory {

    // The table (and corresponding Pockets) this will be generating
    // selections for
    private final Table table;

    /**
     * Create a new factory object for the specified table
     *
     * @param table - the table for which selections will be generated for
     */
    public SelectionFactory(Table table) {
        this.table = table;
    }

    /**
     * Create a new selection for a straight bet - i.e. a single number bet
     *
     * @param pocket - the pocket representing the single number
     * @return a selection representing this single number
     * @throws IllegalSelectionException if the specified pocket does not
     *                                   exist on the game's table
     */
    public Selection straightBet(String pocket) throws
            IllegalSelectionException {
        // Verify the pocket
        if (!isValidSelection(pocket)) {
            throw new IllegalSelectionException(String.format("The specified " +
                    "pocket '%s' does not exist on the table", pocket));
        }
        return new Selection("Straight", 36, table.lookupPocket(pocket));
    }

    /**
     * Create the selection for an odd or even outside bet
     *
     * @param odd - <code>true</code> to retrieve a selection of all odd
     *            numbers or <code>false</code> to return a selection of all
     *            even numbers, excluding the house numbers
     * @return a selection of either all odd or even numbers, excluding the
     * house numbers
     */
    public Selection oddOrEvenBet(boolean odd) {
        int remainder = odd ? 1 : 0;
        String name = odd ? "Odd" : "Even";
        Pocket[] pockets = table.getPockets().stream().filter(p -> !p.isHouse
                () && p.getNumeric() % 2 == remainder).toArray(Pocket[]::new);
        return new Selection(name, 2, pockets);
    }

    /**
     * Check whether the specified pocket(s) are valid - i.e. present on the
     * game's table
     *
     * @param pockets - the pockets to check whether they are present on the
     *                game's table
     * @return <code>true</code> if the pockets are all valid,
     * <code>false</code> otherwise
     */
    private boolean isValidSelection(String... pockets) {
        return !Arrays.stream(pockets).anyMatch(p -> table.lookupPocket(p) ==
                null);
    }

}
