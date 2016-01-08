package uk.co.sleader.roulette;

/**
 * Created by sleader on 05/01/2016.
 */
public interface Table {

    public SelectionFactory getSelectionFactory();

    public String throwBall();

    public Pocket lookupPocket(String identifier);
}
