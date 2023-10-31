package g56514.samegame.model;

/**
 *
 * @author yohan
 */
public class CommandUndoRedo implements Command {

    /**
     * Cancels the command.
     *
     */
    @Override
    public void unexecute(Game game) {
        if (game.getIndex() > 0) {
            int index = game.getIndex();
            game.setIndex(--index);
            game.setBoard(new Board(game.getHistory().get(index)));
        } else {
            System.out.println("UNDO PAS POSSIBLE");
        }
        game.setState(State.SHOW_RESULT);
        game.notifyObservers();
    }

    @Override
    /**
     * Reexecutes the latest entered command.
     *
     */
    public void reexecute(Game game) {
        if (game.getIndex() < game.getHistory().size() - 1) {
            int index = game.getIndex();
            game.setIndex(++index);
            game.setBoard(new Board(game.getHistory().get(index)));
        } else {
            System.out.println("REDO PAS POSSIBLE");
        }
        game.setState(State.SHOW_RESULT);
        game.notifyObservers();
    }
}
