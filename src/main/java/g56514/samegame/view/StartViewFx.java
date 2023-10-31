package g56514.samegame.view;

import g56514.samegame.controller.Controller;
import g56514.samegame.model.Game;
import g56514.samegame.view.GameViewFx;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author yohan
 */
public class StartViewFx extends Application {

    /**
     * main method.
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
        MainLayout root = new MainLayout();
        root.getStartBtn().setOnAction(event -> {
            String[] tab = root.theSelectedMenu().split(" ");
            Game game = new Game(Integer.parseInt(tab[0]), Integer.parseInt(tab[2]), 1, (int) root.getSlider().getValue());
            Controller controller = new Controller(game);
            GameViewFx gameViewFx = new GameViewFx(controller);
            controller.start(stage, gameViewFx);
        });
        Scene scene = new Scene(root, 1000, 750);
        stage.setScene(scene);
        stage.show();
    }

}
