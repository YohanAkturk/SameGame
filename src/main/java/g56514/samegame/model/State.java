package g56514.samegame.model;

/**
 *
 * @author yohan
 */
public enum State {

    /**
     * this value represents the state when the user has to give a position.
     */
    GIVE_POSITION_OR_ABANDON,
    /**
     * this value represents the state when the game will update the board.
     */
    UPDATE_BOARD,
    /**
     * this value represents the state when the board has to be shown.
     */
    SHOW_RESULT,
    /**
     * this value represents the state when the game is over.
     */
    GAME_OVER,
        /**
     * this value represents the state when the game is won.
     */
    GAME_WIN,
}
