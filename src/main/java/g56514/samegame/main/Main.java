package g56514.samegame.main;

import g56514.samegame.controller.Controller;
import g56514.samegame.model.Game;
import g56514.samegame.view.View;

/**
 *
 * @author yohan
 */
public class Main {

    /**
     * main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        String parameters[] = View.initializationGame();
        Game game = new Game(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), 1, Integer.parseInt(parameters[2]));
        Controller controller = new Controller(game);
        controller.start();
    }
}
