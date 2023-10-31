package g56514.samegame.main;

import g56514.samegame.view.StartViewFx;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author yohan
 */
public class MainFx extends Application {

    /**
     * main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Method that allows to start the game.
     *
     * @param stage the stage.
     * @throws Exception exception in case of problem to start the game.
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("SameGame");
        StartViewFx startViewFx = new StartViewFx();
        startViewFx.start(stage);
    }
}
