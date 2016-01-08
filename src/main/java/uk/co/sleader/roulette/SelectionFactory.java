package uk.co.sleader.roulette;

import uk.co.sleader.roulette.exceptions.RouletteGameException;

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

    public Selection straightBet(String pocket) throws RouletteGameException {
        // Verify the pocket
        if (!isValidSelection(pocket)) {
            throw new RouletteGameException(String.format("The specified pocket '%s' does not exist on the table", pocket));
        }
        return new Selection("Straight", 36, pocket);
    }

    private boolean isValidSelection(String... pockets) {
        return !Arrays.stream(pockets).anyMatch(p -> table.lookupPocket(p) == null);
    }

}
