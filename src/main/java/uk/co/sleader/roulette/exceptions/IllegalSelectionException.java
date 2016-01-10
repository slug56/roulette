package uk.co.sleader.roulette.exceptions;

/**
 * Indicates an invalid selection of pockets, such as trying to select a pocket which does not exist on the table.
 */
public class IllegalSelectionException extends RouletteGameException {

    public IllegalSelectionException() {
        super();
    }

    public IllegalSelectionException(String message) {
        super(message);
    }

}
