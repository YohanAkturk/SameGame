package g56514.samegame.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yohan
 */
public class Board {

    private Bille[][] billes;

    /**
     * Board's constructor.
     *
     * @param row the row.
     * @param column the column.
     */
    public Board(int row, int column) {
        billes = new Bille[row][column];
    }

    public Board(Board b) {
        this(b.getRowSize(), b.getColumnSize());
        for (int i = 0; i < b.getRowSize(); i++) {
            for (int j = 0; j < b.getColumnSize(); j++) {
                if (b.getBilles()[i][j] != null) {
                    put(new Bille(b.getBille(new Position(i, j)).getIndexBille()), new Position(i, j));
                } else {
                    billes[i][j] = null;
                }
            }
        }
    }

    public Board() {
        this(0, 0);
    }

    /**
     * Getter for the row size.
     *
     * @return the row size.
     */
    public int getRowSize() {
        return billes.length;
    }

    /**
     * Getter for the column size.
     *
     * @return the column size.
     */
    public int getColumnSize() {
        return billes[0].length;
    }

    /**
     * Getter of the billes board.
     *
     * @return the billes board.
     */
    public Bille[][] getBilles() {
        return billes;
    }

    /**
     * Method that checks if a given position is in the Board or not.
     *
     * @param pos the position.
     * @return true if the position is between 0 and 3, false else.
     */
    public boolean isInside(Position pos) {
        return pos.getRow() >= 0 && pos.getRow() < getRowSize()
                && pos.getColumn() >= 0 && pos.getColumn() < getColumnSize();
    }

    /**
     * Method that gives the bille in a given position.
     *
     * @param pos the position.
     * @return the tile in the position if this tile exists, null else.
     */
    public Bille getBille(Position pos) {
        return billes[pos.getRow()][pos.getColumn()];
    }

    /**
     * Method that places a bille in a given position.
     *
     * @param tile the tile.
     * @param pos the position.
     */
    public void put(Bille bille, Position pos) {
        billes[pos.getRow()][pos.getColumn()] = bille;
    }

    /**
     * Method that checks if a table is full.
     *
     * @return true if it is full, false else.
     */
    public boolean isEmpty() {
        boolean isEmpty = false;
        for (int i = 0; i < getRowSize() && !isEmpty; i++) {
            for (int j = 0; j < getColumnSize() && !isEmpty; j++) {
                if (billes[i][j] == null) {
                    isEmpty = true;
                }
            }
        }
        return isEmpty;
    }
}
