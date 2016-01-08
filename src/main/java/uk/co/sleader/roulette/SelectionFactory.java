package uk.co.sleader.roulette;

import uk.co.sleader.roulette.exceptions.IllegalSelectionException;

import java.util.Arrays;

/**
 * Created by sleader on 05/01/2016.
 */
public class SelectionFactory {

    // TODO Implement a cache so selections aren't being generated over and over again

    private final Table table;

    public SelectionFactory(Table table) {
        this.table = table;
    }

    public Selection straightBet(String pocket) throws IllegalSelectionException {
        // Verify the pocket
        if (!isValidSelection(pocket)) {
            throw new IllegalSelectionException(String.format("The specified pocket '%s' does not exist on the table", pocket));
        }
        return new Selection("Straight", 36, table.lookupPocket(pocket));
    }

    private boolean isValidSelection(String... pockets) {
        return !Arrays.stream(pockets).anyMatch(p -> table.lookupPocket(p) == null);
    }

    public Pocket lookupPocket(String identifier) throws IllegalSelectionException {
        if (!isValidSelection(identifier)) {
            throw new IllegalSelectionException(String.format("The specified pocket '%s' does not exist on the table", identifier));
        }
        return table.lookupPocket(identifier);
    }

    public Selection oddOrEvenBet(boolean odd) {
        int remainder = odd ? 1 : 0;
        String name = odd ? "Odd" : "Even";
        Pocket[] pockets = table.getPockets().stream().filter(p -> !p.isHouse() && p.getNumeric() % 2 == remainder).toArray(Pocket[]::new);
        return new Selection(name, 2, pockets);
    }
}
