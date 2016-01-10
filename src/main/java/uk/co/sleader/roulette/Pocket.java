package uk.co.sleader.roulette;

/**
 * Represents a number on a roulette table (or pocket in the wheel)
 * TODO Getters for the other Pocket attributes
 */
public class Pocket {

    public enum Colour {
        RED, BLACK, GREEN;
    }

    private String identifier; // e.g. "6" or "00"
    private int numeric; // 6 or 0
    private Colour colour; // Red, black or green
    // Row and column used for calculating selections
    private int row; // 0 = House
    private int column; // 1 = First, 2 = Second, 3 = Third

    public Pocket(String identifier, int numeric, Colour colour, int row, int
            column) {
        this.identifier = identifier;
        this.numeric = numeric;
        this.colour = colour;
        this.row = row;
        this.column = column;
    }

    /**
     * Get the number value of the pocket. E.g. for "6" return 6.
     *
     * @return the numerical value of the pocket
     */
    public int getNumeric() {
        return numeric;
    }

    /**
     * Determine if the pocket is a house pocket
     *
     * @return <code>true</code> if this is a house pocked (i.e. green
     * colour), <code>false</code> otherwise
     */
    public boolean isHouse() {
        return colour == Colour.GREEN;
    }

}
