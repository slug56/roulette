package uk.co.sleader.roulette;

/**
 * Created by sleader on 05/01/2016.
 */
public class Table {

    private SelectionFactory selectionFactory;

    public Table() {
       selectionFactory = new SelectionFactory();
    }

    public SelectionFactory getSelectionFactory() {
        return selectionFactory;
    }

    public String throwBall() {
        // TODO Throw an actual pocket number (random)
        return "11";
    }
}
