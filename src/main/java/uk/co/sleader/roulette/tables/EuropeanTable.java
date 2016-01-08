package uk.co.sleader.roulette.tables;

import uk.co.sleader.roulette.Pocket;
import uk.co.sleader.roulette.Pocket.Colour;
import uk.co.sleader.roulette.SelectionFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sleader on 05/01/2016.
 */
public class EuropeanTable implements Table {

    private Map<String, Pocket> pockets;

    private SelectionFactory selectionFactory;

    public SelectionFactory getSelectionFactory() {
        return selectionFactory;
    }

    public EuropeanTable() {
        selectionFactory = new SelectionFactory(this);
        initialisePockets();
    }

    public Pocket throwBall() {
        // TODO Throw an actual pocket number (random)
        return lookupPocket("11");
    }

    public Pocket lookupPocket(String identifier) {
        return pockets.get(identifier);
    }

    @Override
    public Collection<Pocket> getPockets() {
        return pockets.values();
    }

    @Override
    public boolean isHalfStakeOnLosingOutsideBets() {
        return false;
    }

    private void initialisePockets() {
        pockets = new HashMap<>();
        pockets.put("0", new Pocket("0", 0, Colour.GREEN, 0, 1));
        pockets.put("1", new Pocket("1", 1, Colour.RED, 1, 1));
        pockets.put("2", new Pocket("2", 2, Colour.BLACK, 1, 2));
        pockets.put("3", new Pocket("3", 3, Colour.RED, 1, 3));
        pockets.put("4", new Pocket("4", 4, Colour.BLACK, 2, 1));
        pockets.put("5", new Pocket("5", 5, Colour.RED, 2, 2));
        pockets.put("6", new Pocket("6", 6, Colour.BLACK, 2, 3));
        pockets.put("7", new Pocket("7", 7, Colour.RED, 3, 1));
        pockets.put("8", new Pocket("8", 8, Colour.BLACK, 3, 2));
        pockets.put("9", new Pocket("9", 9, Colour.RED, 3, 3));
        pockets.put("10", new Pocket("10", 10, Colour.BLACK, 4, 1));
        pockets.put("11", new Pocket("11", 11, Colour.BLACK, 4, 2));
        pockets.put("12", new Pocket("12", 12, Colour.RED, 4, 3));
        pockets.put("13", new Pocket("13", 13, Colour.BLACK, 5, 1));
        pockets.put("14", new Pocket("14", 14, Colour.RED, 5, 2));
        pockets.put("15", new Pocket("15", 15, Colour.BLACK, 5, 3));
        pockets.put("16", new Pocket("16", 16, Colour.RED, 6, 1));
        pockets.put("17", new Pocket("17", 17, Colour.BLACK, 6, 2));
        pockets.put("18", new Pocket("18", 18, Colour.RED, 6, 3));
        pockets.put("19", new Pocket("19", 19, Colour.RED, 7, 1));
        pockets.put("20", new Pocket("20", 20, Colour.BLACK, 7, 2));
        pockets.put("21", new Pocket("21", 21, Colour.RED, 7, 3));
        pockets.put("22", new Pocket("22", 22, Colour.BLACK,8,  1));
        pockets.put("23", new Pocket("23", 23, Colour.RED, 8, 2));
        pockets.put("24", new Pocket("24", 24, Colour.BLACK, 8, 3));
        pockets.put("25", new Pocket("25", 25, Colour.RED, 9, 1));
        pockets.put("26", new Pocket("26", 26, Colour.BLACK, 9, 2));
        pockets.put("27", new Pocket("27", 27, Colour.RED, 9, 3));
        pockets.put("28", new Pocket("28", 28, Colour.BLACK, 10, 1));
        pockets.put("29", new Pocket("29", 29, Colour.BLACK, 10, 2));
        pockets.put("30", new Pocket("30", 30, Colour.RED, 10, 3));
        pockets.put("31", new Pocket("31", 31, Colour.BLACK, 11, 1));
        pockets.put("32", new Pocket("32", 32, Colour.RED, 11, 2));
        pockets.put("33", new Pocket("33", 33, Colour.BLACK, 11, 3));
        pockets.put("34", new Pocket("34", 34, Colour.RED, 12, 1));
        pockets.put("35", new Pocket("35", 35, Colour.BLACK, 12, 2));
        pockets.put("36", new Pocket("36", 36, Colour.RED, 12, 3));
    }
}
