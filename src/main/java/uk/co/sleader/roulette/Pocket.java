package uk.co.sleader.roulette;

/**
 * Created by sleader on 06/01/2016.
 */
public class Pocket {

    public enum Colour {
        RED, BLACK, GREEN;
    }

    private String identifier;
    private int numeric;
    private Colour colour;
    private int row; // 0 = House
    private int column; // 1 = First, 2 = Second

    public Pocket(String identifier, int numeric, Colour colour, int row, int column) {
        this.identifier = identifier;
        this.numeric = numeric;
        this.colour = colour;
        this.row = row;
        this.column = column;
    }

    public int getNumeric() {
        return numeric;
    }

    public boolean isHouse() {
        return colour == Colour.GREEN;
    }

}
