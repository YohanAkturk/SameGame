package g56514.samegame.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yohan
 */
public interface Model {

    /**
     * Allows to initialize the board.
     *
     * @param row the board's row.
     * @param column the board's row.
     * @param min the minimum number of colors.
     * @param max the maximum number of colors.
     */
    public void initializeBoard(int row, int column, int min, int max);

    /**
     * Allows to find the neighbours of a bille in a given position.
     *
     * @param pos the position.
     * @param indexBille the bille.
     * @return the list of neighbours's position.
     */
    public List<Position> findNeighbours(Position pos, int indexBille);

    /**
     * Gives the score. It is calculated from the following formula : n * (n-1).
     *
     * @return the score.
     */
    public int scoreAprèsCoup();

    /**
     * The score after a click.
     *
     * @return the score.
     */
    public int scoreLiveForCoup();

    /**
     * Gives the remaining billes in the game.
     *
     * @return the remaining billes.
     */
    public int remainingBilles();

    /**
     * Allows to delete all the billes that are impacted with the click.
     */
    public void deleteBilles();

    /**
     * Allows to update the board by moving down the billes above the impact of
     * the click.
     */
    public void decalerVertical();

    /**
     * Allows to update the board by moving the billes to the left when there is
     * an empty space between two columns of billes.
     */
    public void decalerHorizontal();

    /**
     * Checks if a hit is still possible.
     *
     * @return true if there is a possibility, false else.
     */
    public boolean canStillMove();

    /**
     * Checks if the board is empty.
     *
     * @return true if it is empty, false else.
     */
    public boolean isEmpty();

}
