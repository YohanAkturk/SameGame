package g56514.samegame.model;

import g56514.samegame.view.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author yohan
 */
public class Game implements Model, Observable, Cloneable {

    private State state;
    List<Observer> observers = new ArrayList<>();
    private Board board;
    private List<Position> neighbours = new ArrayList<>();
    private int score = 0;

    private int index = -1;
    List<Board> history = new ArrayList<>();

    /**
     * Game's constructor.
     *
     * @param row the board's row.
     * @param column the board's row.
     * @param min the minimum number of colors.
     * @param max the maximum number of colors.
     */
    public Game(int row, int column, int min, int max) {
        initializeBoard(row, column, min, max);
        register();
        state = State.SHOW_RESULT;
    }

    /**
     * Game's constructor for test unit.
     *
     * @param row the board's row.
     * @param column the board's row.
     */
    public Game(int row, int column) {
        board = new Board(row, column);
        state = State.GIVE_POSITION_OR_ABANDON;
    }

    @Override
    public void initializeBoard(int row, int column, int min, int max) {
        board = new Board(row, column);
        for (int i = 0; i < board.getRowSize(); i++) {
            for (int j = 0; j < board.getColumnSize(); j++) {
                Bille indexBille = new Bille(getRandomIndex(min, max));
                board.put(indexBille, new Position(i, j));
            }
        }
    }

    /**
     * Getter of board.
     *
     * @return board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Setter of board.
     *
     * @param board a board.
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Getter of neighbours.
     *
     * @return neighbours.
     */
    public List<Position> getNeighbours() {
        return neighbours;
    }

    /**
     * Getter of state.
     *
     * @return the state.
     */
    public State getState() {
        return state;
    }

    /**
     * Setter of state.
     *
     * @param state a state.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Getter of score.
     *
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter of score.
     *
     * @param score a score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter of index.
     *
     * @return the index.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter of index.
     *
     * @param index a index.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Getter of history.
     *
     * @return the history.
     */
    public List<Board> getHistory() {
        return history;
    }

    /**
     * Getter of observers.
     *
     * @return the observers.
     */
    public List<Observer> getObservers() {
        return observers;
    }

    /**
     * Adds a observer.
     *
     * @param observer the observer.
     */
    void subscribe(Observer observer) {
        observers.add(observer);
    }

    /**
     * Adds observer.
     *
     * @param observer the observer.
     */
    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes the observer.
     *
     * @param observer the observer.
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies the observers to update.
     */
    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    /**
     * Gives a random index that represents a bille's index.
     *
     * @return the index.
     */
    private int getRandomIndex(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    @Override
    public List<Position> findNeighbours(Position pos, int indexBille) {
        checkPositionRecursive(pos, indexBille, -1, 0);
        checkPositionRecursive(pos, indexBille, 0, 1);
        checkPositionRecursive(pos, indexBille, 0, -1);
        checkPositionRecursive(pos, indexBille, 1, 0);
        List<Position> foundNeighbours = new ArrayList<>();
        foundNeighbours.addAll(neighbours);
        state = State.UPDATE_BOARD;
        return foundNeighbours;
    }

    /**
     * Checks recursively for each position's direction.
     *
     * @param pos the position.
     * @param indexBille the bille's index.
     * @param directionV the vertical direction.
     * @param directionH the horizontal direction.
     */
    private void checkPositionRecursive(Position pos, int indexBille, int directionV, int directionH) {
        Position potentialPos = new Position(pos.getRow() + directionV, pos.getColumn() + directionH);
        if (board.isInside(potentialPos) && board.getBille(potentialPos) != null && board.getBille(potentialPos).getIndexBille() == indexBille) {
            if (!neighbours.contains(potentialPos)) {
                neighbours.add(potentialPos);
                findNeighbours(potentialPos, indexBille);
            }
        }
    }

    @Override
    public int scoreAprèsCoup() {
        setScore(score += neighbours.size() * (neighbours.size() - 1));
        return getScore();
    }

    @Override
    public int scoreLiveForCoup() {
        int score = neighbours.size() * (neighbours.size() - 1);
        neighbours.clear();
        return score;
    }

    @Override
    public int remainingBilles() {
        int remainngBilles = 0;
        for (int i = 0; i < getBoard().getRowSize(); i++) {
            for (int j = 0; j < getBoard().getColumnSize(); j++) {
                if (getBoard().getBilles()[i][j] != null) {
                    remainngBilles++;
                }
            }
        }
        return remainngBilles;
    }

    /**
     * Method that delete the billes.
     */
    @Override
    public void deleteBilles() {
        neighbours.forEach(e -> {
            board.getBilles()[e.getRow()][e.getColumn()] = null;
        });
    }

    @Override
    public void decalerVertical() {
        for (int i = 0; i < board.getColumnSize(); i++) {
            for (int j = board.getRowSize() - 1; j >= 0; j--) {
                if (board.getBilles()[j][i] == null) {
                    swapVertical(j, i);
                }
            }
        }
    }

    /**
     * Shifts the billes vertically.
     *
     * @param j the vertical position.
     * @param i the horizontal position.
     */
    private void swapVertical(int j, int i) {
        int begin = j;
        int current = begin;
        while (current >= 0) {
            current--;
            if (board.isInside(new Position(current, i)) && board.getBilles()[current][i] != null) {
                board.getBilles()[begin][i] = board.getBilles()[current][i];
                board.getBilles()[current][i] = null;
                begin = current;
            }
        }
    }

    @Override
    public void decalerHorizontal() {
        boolean allNull = true;
        for (int i = 0; i < board.getColumnSize(); i++) {
            allNull = true;
            for (int j = 0; j < board.getRowSize() && allNull; j++) {
                if (board.getBilles()[j][i] != null) {
                    allNull = false;
                }
                if ((j == board.getRowSize() - 1) && board.getBilles()[j][i] == null) {
                    if (board.isInside(new Position(j, i + 1))) {
                        swapHorizontal(i);
                    }
                }
            }
        }
        if (board.getBilles()[board.getRowSize() - 1][0] == null) {
            decalerHorizontal();
        }
        setState(State.SHOW_RESULT);
        register();
        this.notifyObservers();
    }

    /**
     * Shifts the billes horizontally.
     *
     * @param i the vertical position.
     */
    private void swapHorizontal(int i) {
        for (int k = (i + 1); k < board.getColumnSize(); k++, i++) {
            for (int l = 0; l < board.getRowSize(); l++) {
                if (board.getBilles()[l][k] != null) {
                    board.getBilles()[l][i] = board.getBilles()[l][k];
                    board.getBilles()[l][k] = null;
                } else {
                    board.getBilles()[l][k] = null;
                }
            }
        }
    }

    @Override
    public boolean canStillMove() {
        boolean canMove = false;
        neighbours.clear();
        for (int i = board.getRowSize() - 1; i >= 0 && !canMove; i--) {
            for (int j = 0; j < board.getColumnSize() && !canMove; j++) {
                if (getBoard().getBilles()[i][j] != null) {
                    findNeighbours(new Position(i, j), getBoard().getBilles()[i][j].getIndexBille());
                    if (neighbours.size() >= 1) {
                        System.out.println("moves possibles : " + neighbours);
                        canMove = true;
                    }
                    neighbours.clear();
                }
            }
        }
        if (!canMove) {
            setState(State.GAME_OVER);
        } else {
            setState(State.GIVE_POSITION_OR_ABANDON);
        }
        this.notifyObservers();
        return canMove;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true;
        for (int i = 0; i < getBoard().getRowSize() && isEmpty; i++) {
            for (int j = 0; j < getBoard().getColumnSize() && isEmpty; j++) {
                if (getBoard().getBilles()[i][j] != null) {
                    isEmpty = false;
                }
            }
        }
        if (isEmpty) {
            setState(State.GAME_WIN);
        } else{
            setState(State.UPDATE_BOARD);
        }
        this.notifyObservers();
        return isEmpty;
    }

    /**
     * Allows to register the board in the history.
     */
    public void register() {
        history.add(new Board(getBoard()));
        setIndex(++index);
    }
}
