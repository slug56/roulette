package uk.co.sleader.roulette.tables;

import uk.co.sleader.roulette.Pocket;
import uk.co.sleader.roulette.SelectionFactory;

import java.util.Collection;

/**
 * Created by sleader on 05/01/2016.
 */
public interface Table {

    SelectionFactory getSelectionFactory();

    Pocket throwBall();

    Pocket lookupPocket(String identifier);

    Collection<Pocket> getPockets();

    boolean isHalfStakeOnLosingOutsideBets();
}
