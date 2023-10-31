package g56514.samegame.model;

/**
 *
 * @author yohan
 */
public interface Command {

    /**
     * Cancels the command.
     *
     * @param game the game.
     */
    public void unexecute(Game game);

    /**
     * Reexecutes the previously command.
     *
     * @param game the game.
     */
    public void reexecute(Game game);
}
