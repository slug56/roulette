package uk.co.sleader.roulette.exceptions;

/**
 * Indicated an invalid bet, such as a wager which has been defined as <= 0.
 */
public class IllegalBetException extends RouletteGameException {

    public IllegalBetException() {
        super();
    }

    public IllegalBetException(String message) {
        super(message);
    }

}
