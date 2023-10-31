package g56514.samegame.controller;

import g56514.samegame.model.Board;
import g56514.samegame.model.Command;
import g56514.samegame.model.CommandUndoRedo;
import g56514.samegame.model.Game;
import g56514.samegame.model.Position;
import g56514.samegame.model.State;
import g56514.samegame.view.View;
import g56514.samegame.view.GameViewFx;
import javafx.stage.Stage;

/**
 *
 * @author yohan
 */
public class Controller {

    private View view;
    private Game game;
    private Command command;

    /**
     * Controller's constructor.
     *
     * @param game the game.
     */
    public Controller(Game game) {
        this.game = game;
        this.view = new View(this, game);
        this.command = new CommandUndoRedo();
    }

    public void start() {
        game.addObserver(view);
        view.start();
    }

    /**
     * starts the game with a added observer.
     *
     * @param stage the stage
     */
    public void start(Stage stage, GameViewFx gameViewFx) {
        game.addObserver(gameViewFx);
        gameViewFx.start(stage);
    }

    /**
     * Getter of board.
     *
     * @return the board.
     */
    public Board getBoard() {
        return game.getBoard();
    }

    /**
     * Getter of game.
     *
     * @return the game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Allows to initialize the board.
     *
     * @param row the board's row.
     * @param column the board's row.
     * @param min the minimum number of colors.
     * @param max the maximum number of colors.
     */
    public void initializeBoard(int row, int column, int min, int max) {
        game.initializeBoard(row, column, min, max);
    }

    /**
     * Method that checks the row of a given bille from its position to look for
     * the billes with the same index.
     *
     * @param pos the position.
     * @param indexBille the bille's index.
     */
    public void findNeighbours(Position pos, int indexBille) {
        game.findNeighbours(pos, indexBille);
    }

    /**
     * Method that gives the score after a hit.
     *
     * @return the score.
     */
    public int scoreAprèsCoup() {
        return game.scoreAprèsCoup();
    }

    /**
     * Method that gives the score after a hit.
     *
     * @return the score.
     */
    public int scoreLiveForCoup() {
        return game.scoreLiveForCoup();
    }

    /**
     * Method that gives the remaining billes.
     *
     * @return the remaining billes.
     */
    public int remainingBilles() {
        return game.remainingBilles();
    }

    /**
     * Method that delete the billes.
     */
    public void deleteBilles() {
        game.deleteBilles();
    }

    /**
     * Method that vertically shifts the billes.
     */
    public void decalerVertical() {
        game.decalerVertical();
    }

    /**
     * Method that horizontally shifts the billes.
     */
    public void decalerHorizontal() {
        game.decalerHorizontal();
    }

    /**
     * Checks if a hit is still possible.
     *
     * @return true if there is a possibility, false else.
     */
    public boolean canStillMove() {
        return game.canStillMove();
    }

    /**
     * Checks if the board is empty.
     *
     * @return true if it is empty, false else.
     */
    public boolean isEmpty() {
        return game.isEmpty();
    }

    /**
     * Getter of state.
     *
     * @return the state.
     */
    public State getState() {
        return game.getState();
    }

    /**
     * Setter of state.
     *
     * @param state the state.
     */
    public void setState(State state) {
        game.setState(state);
    }

    /**
     * Cancels the hit.
     */
    public void undo(Game game) {
        command.unexecute(game);
    }

    /**
     * Redoes the cancelled hit.
     */
    public void redo(Game game) {
        command.reexecute(game);
    }
}
