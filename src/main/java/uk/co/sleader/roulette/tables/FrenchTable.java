package uk.co.sleader.roulette.tables;

/**
 * Created by sleader on 05/01/2016.
 */
public class FrenchTable extends EuropeanTable {

    @Override
    public boolean isHalfStakeOnLosingOutsideBets() {
        return true;
    }

}
