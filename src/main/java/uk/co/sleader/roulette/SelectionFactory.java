package uk.co.sleader.roulette;

/**
 * Created by sleader on 05/01/2016.
 */
public class SelectionFactory {


    public Selection straightBet(String pocket) {
        return new Selection("Straight", 36, pocket);
    }

}
