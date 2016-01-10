package uk.co.sleader.roulette.exceptions;

/**
 * Indicates an unexpected state in Roulette
 */
public class RouletteGameException extends Exception {

    public RouletteGameException() {
        super();
    }

    public RouletteGameException(String message) {
        super(message);
    }
}
