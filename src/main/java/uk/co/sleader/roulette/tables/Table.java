package uk.co.sleader.roulette.tables;

import uk.co.sleader.roulette.Pocket;
import uk.co.sleader.roulette.SelectionFactory;

import java.util.Collection;

/**
 * Represents the Roulette table and the actions available with this
 */
public interface Table {

    /**
     * Obtain the object associated with the Table which allows selections to
     * be made (e.g. a straight bet on 12 or an odd bet)
     *
     * @return the table's <code>SelectionFactory</code>
     */
    SelectionFactory getSelectionFactory();

    /**
     * Simulate throwing the ball on the wheel and it landing in a winning
     * pocket
     *
     * @return The winning pocket
     */
    Pocket throwBall();

    /**
     * Search the table's pockets (numbers) for the one matching the
     * identifier specified. For example, "6" will return the Pocket object
     * representing the pocket numbered 6.
     *
     * @param identifier - the String representation of the Pocket object to
     *                   retrieve
     * @return The Pocket object represented by the given String identifier,
     * <code>null</code> otherwise.
     */
    Pocket lookupPocket(String identifier);

    /**
     * All of the pockets associated with this table
     *
     * @return A collection of all the pockets which are affiliated with this
     * table
     */
    Collection<Pocket> getPockets();

    /**
     * Some variations of roulette return half of the customer's stake if
     * they place an outside bet (e.g. odd or even) and a house (green)
     * numbers wins.
     *
     * @return <code>true</code> if half of the stake is returned on outside
     * bets when a house pocket wins, <code>false</code> otherwise.
     */
    boolean isHalfStakeOnLosingOutsideBets();
}
