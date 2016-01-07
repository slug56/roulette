package uk.co.sleader.roulette;

/**
 * Created by sleader on 06/01/2016.
 */
public class Pocket {

    private enum Colour {
        RED, BLACK, GREEN;
    }

    private String identifier;
    private int numeric;
    private Colour colour;
    private int row; // 0 = House
    private int column; // 1 = First, 2 = Second

}
