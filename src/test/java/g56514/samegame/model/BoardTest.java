package g56514.samegame.model;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author yohan
 */
public class BoardTest {

    private Board board;

    @BeforeEach     // Exécutée avant chaque test
    public void setUp() {
        board = new Board(12, 16);
    }

    /* === Tests for isInside === */
    @org.junit.jupiter.api.Test
    public void isInside_True_When_Really_Inside() {
        Position pos = new Position(1, 2);
        assertTrue(board.isInside(pos));
    }

    @org.junit.jupiter.api.Test
    public void isInside_True_When_First_Row() {
        Position pos = new Position(0, 2);
        assertTrue(board.isInside(pos));
    }

    @org.junit.jupiter.api.Test
    public void isInside_True_When_Last_Row() {
        Position pos = new Position(1, board.getRowSize() - 1);
        assertTrue(board.isInside(pos));
    }

    @org.junit.jupiter.api.Test
    public void isInside_Position_When_Row_Too_Small() {
        Position pos = new Position(-1, 1);
        assertFalse(board.isInside(pos));
    }

    @org.junit.jupiter.api.Test
    public void isInside_Position_When_Row_Too_Big() {
        Position pos = new Position(board.getRowSize(), 1);
        assertFalse(board.isInside(pos));
    }

    @org.junit.jupiter.api.Test
    public void isInside_True_When_First_Col() {
        Position pos = new Position(2, 0);
        assertTrue(board.isInside(pos));
    }

    @org.junit.jupiter.api.Test
    public void isInside_True_When_Last_Col() {
        Position pos = new Position(board.getRowSize() - 1, 1);
        assertTrue(board.isInside(pos));
    }

    @org.junit.jupiter.api.Test
    public void isInside_Position_When_Col_Too_Small() {
        Position pos = new Position(1, -1);
        assertFalse(board.isInside(pos));
    }

    @org.junit.jupiter.api.Test
    public void isInside_Position_When_Col_Too_Big() {
        Position pos = new Position(2, board.getColumnSize());
        assertFalse(board.isInside(pos));
    }

    /* === Tests for isFull === */
    @Test
    public void isEmpty_False_When_Empty() {
        board = new Board(12, 16);
        assertTrue(board.isEmpty());
    }

    @Test
    public void makesExplosif() {
        board = new Board(12, 16);
        assertTrue(board.isEmpty());
    }

    /* === Tests for put === */
    @org.junit.jupiter.api.Test
    public void put() {
        Bille bille = new Bille(1);
        Position pos = new Position(1, 2);
        board.put(bille, pos);
        assertTrue(board.getBille(pos) == bille);
    }

}
