package g56514.samegame.model;

/**
 *
 * @author yohan
 */
public class Bille {

    private int indexBille;
    private boolean isExplosif;

    /**
     * Bille's constructor.
     *
     * @param cat the bille's color.
     */
    public Bille(int cat) {
        this.indexBille = cat;
        this.isExplosif = false;
    }

    /**
     * Getter of color.
     *
     * @return the color.
     */
    public int getIndexBille() {
        return indexBille;
    }

    /**
     * Getter of isExplosif.
     *
     * @return isExplosif.
     */
    public boolean isIsExplosif() {
        return isExplosif;
    }

    /**
     * Makes the bille explosive.
     */
    public void makesExplosif() {
        this.isExplosif = true;
    }

    /**
     * Makes the bille non-explosive.
     */
    public void makesNotExplosif() {
        this.isExplosif = false;
    }

    @Override
    public String toString() {
        return "color = " + indexBille;
    }

}
