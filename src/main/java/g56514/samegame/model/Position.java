package g56514.samegame.model;

/**
 * This class represents a position with a row and a column.
 *
 * @author yohan
 */
public class Position {

    private int row;
    private int column;

    /**
     * Position class's constructor.
     *
     * @param row the row number.
     * @param column the column number.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Getter of row.
     *
     * @return row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter of column.
     *
     * @return column.
     */
    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.row;
        hash = 47 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        return true;
    }

}
