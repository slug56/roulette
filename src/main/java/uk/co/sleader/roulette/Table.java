package uk.co.sleader.roulette;

import java.util.Collection;

/**
 * Created by sleader on 05/01/2016.
 */
public interface Table {

    public SelectionFactory getSelectionFactory();

    public Pocket throwBall();

    public Pocket lookupPocket(String identifier);

    public Collection<Pocket> getPockets();

    public boolean isHalfStakeOnLosingOutsideBets();
}
