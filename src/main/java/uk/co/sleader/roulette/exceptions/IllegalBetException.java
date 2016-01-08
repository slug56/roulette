package uk.co.sleader.roulette.exceptions;

/**
 * Created by sleader on 08/01/2016.
 */
public class IllegalBetException extends RouletteGameException {

    public IllegalBetException() {
        super();
    }

    public IllegalBetException(String message) {
        super(message);
    }
    
}
