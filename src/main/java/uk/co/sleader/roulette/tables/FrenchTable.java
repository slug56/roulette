package uk.co.sleader.roulette.tables;

/**
 * French roulette is a specific va riation of European roulette, where half
 * of the customer's stake is returned to the user in the case that they have
 * placed an outside bet (e.g. odd or even) and a house number (i.e. 0) wins
 * the spin.
 */
public class FrenchTable extends EuropeanTable {

    @Override
    public boolean isHalfStakeOnLosingOutsideBets() {
        return true;
    }

}
