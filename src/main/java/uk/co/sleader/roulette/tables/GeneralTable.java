package uk.co.sleader.roulette.tables;

import uk.co.sleader.roulette.Pocket;
import uk.co.sleader.roulette.SelectionFactory;

import java.util.*;

/**
 * A generic (abstract) roulette table implementing the standard
 * functionality as
 * required by the <code>Table</code> interface. Allows the various
 * implementations to specify their pockets and not much more.
 */
public abstract class GeneralTable implements Table {

    // A lookup of pockets by their identifier String
    protected Map<String, Pocket> pockets;
    // A list of pockets comprising the table
    private List<Pocket> pocketList;
    // Allows selections of pockets to be made which are valid for the table
    private SelectionFactory selectionFactory;
    // Used for spinning the ball and returning a winning pocket
    private Random random;

    public GeneralTable() {
        selectionFactory = new SelectionFactory(this);
        pockets = getPocketMap();
        pocketList = new ArrayList<>(pockets.values());
        random = new Random();
    }

    /**
     * Obtain the object which allows the selections of the Table's pocket to
     * be made.
     *
     * @return The <code>SelectionFactory</code> object
     */
    public SelectionFactory getSelectionFactory() {
        return selectionFactory;
    }

    /**
     * Lookup one of the table's Pockets by it's String identifier
     *
     * @param identifier - the String representation of the Pocket object to
     *                   retrieve
     * @return the Pocket matching the given identifier, <code>null</code>
     * otherwise
     */
    public Pocket lookupPocket(String identifier) {
        return pockets.get(identifier);
    }

    /**
     * Obtain the Pockets which make up this table
     *
     * @return the Table's Pockets
     */
    public Collection<Pocket> getPockets() {
        return pocketList;
    }

    /**
     * Allows a common implementation of randomly throwing the
     * roulette ball.
     *
     * @return The winning pocket
     */
    public Pocket throwBall() {
        return pocketList.get(random.nextInt(pocketList.size()));
    }

    /**
     * Allows concrete classes to specify the Pockets which are contains
     * within the table
     *
     * @return A lookup map of the Pockets contained in the table, with their
     * String identifier as the Key and the actual pocket as the Value
     */
    public abstract Map<String, Pocket> getPocketMap();

    /**
     * This general table assumes that none of a customer's outside bet is returned when a house pocket wins.
     * @return <code></code>
     */
    public boolean isHalfStakeOnLosingOutsideBets() {
        return false;
    }
}
