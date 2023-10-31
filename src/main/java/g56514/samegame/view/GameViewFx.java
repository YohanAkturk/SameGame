package g56514.samegame.view;

import g56514.samegame.controller.Controller;
import g56514.samegame.model.Position;
import g56514.samegame.model.State;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author yohan
 */
public class GameViewFx implements Observer {

    private Controller controller;
    private Stage gameStage;
    private BorderPane back = new BorderPane();
    private GridPane grid = new GridPane();
    Button undoBtn = new Button("UNDO");
    Button redoBtn = new Button("REDO");
    Button abandonBtn = new Button("ABANDON");
    HBox scoreBox = new HBox();
    Text totalScore = new Text("Total Score : 0");
    Text liveScore = new Text("This Click Score : 0");
    Text remainingBilles = new Text("Remaining billes : 0");

    /**
     * ViewFx's constructor.
     *
     * @param controller the game controller.
     */
    public GameViewFx(Controller controller) {
        this.controller = controller;
    }

    /**
     * Allows to start the game by initialising it.
     *
     * @param stage the stage.
     */
    public void start(Stage stage) {
        var image = new Image("file:photofunky.gif", true);
        var bgImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        grid.setBackground(new Background(bgImage));
        grid.setStyle("-fx-border-width: 3px; -fx-border-color: black;\n");
        back.setStyle("-fx-background-color: #A0A4F7");
        gameStage = stage;
        VBox buttons = new VBox();
        buttons.getChildren().addAll(undoBtn, redoBtn);
        undoBtn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 50px;\n"
                + "    -fx-border-width: 3px;\n"
                + "    -fx-border-color: black;");
        redoBtn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 50px;\n"
                + "    -fx-border-width: 3px;\n"
                + "    -fx-border-color: black;");
        abandonBtn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 50px;\n"
                + "    -fx-border-width: 3px;\n"
                + "    -fx-border-color: black;");

        abandonBtn.setOnAction(e -> {
            abandon();
        });

        Text top = new Text("SAME GAME");
        HBox topBox = new HBox(top);
        HBox leftBox = new HBox(abandonBtn);
        topBox.setAlignment(Pos.CENTER);
        back.setTop(topBox);
        back.setMargin(topBox, new Insets(25, 0, 25, 0));
        leftBox.setAlignment(Pos.CENTER);
        back.setLeft(leftBox);
        back.setMargin(leftBox, new Insets(0, 25, 0, 25));

        initializeGrid(grid);
        grid.setAlignment(Pos.CENTER);
        back.setCenter(grid);

        buttons.setAlignment(Pos.CENTER);
        back.setRight(buttons);
        back.setMargin(buttons, new Insets(0, 25, 0, 25));
        buttons.setSpacing(100);

        scoreBox.getChildren().addAll(totalScore, liveScore, remainingBilles);
        back.setBottom(scoreBox);
        scoreBox.setSpacing(200);
        back.setMargin(scoreBox, new Insets(25, 0, 50, 175));

        Scene scene = new Scene(back, 1000, 750);
        stage.setScene(scene);

        stage.show();
    }

    /**
     * Method that initializes the grid.
     *
     * @param root the grid.
     */
    public void initializeGrid(GridPane root) {
        root.getChildren().clear();
        for (int i = 0; i < controller.getBoard().getRowSize(); i++) {
            for (int j = 0; j < controller.getBoard().getColumnSize(); j++) {
                if (controller.getBoard().getBille(new Position(i, j)) != null) {
                    Position pos = new Position(i, j);
                    Circle c = new Circle();
                    c.setRadius(25);
                    c.setFill(codeColor(controller.getBoard().getBille(pos).getIndexBille()));
                    c.setId(Integer.toString(i) + Integer.toString(j));
                    c.setOnMousePressed(e -> {
                        System.out.println(c.getId());
                        play(pos);
                    });
                    undo();
                    redo();
                    root.add(c, j, i);
                }
            }
        }
    }

    /**
     * Gives the color for the bille's index.
     *
     * @param index the bille's index.
     * @return the color.
     */
    public Color codeColor(int index) {
        switch (index) {
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.RED;
            case 5:
                return Color.VIOLET;
        }
        return Color.BLACK;
    }

    /**
     * Allows to play for a given position.
     *
     * @param pos the position.
     */
    private void play(Position pos) {
        controller.findNeighbours(pos, controller.getBoard().getBille(pos).getIndexBille());
        controller.deleteBilles();
        controller.isEmpty();
    }

    /**
     * Cancels the hit.
     */
    private void undo() {
        undoBtn.setOnAction(e -> {
            controller.undo(controller.getGame());
        });
    }

    /**
     * Redoes the cancelled hit.
     */
    private void redo() {
        redoBtn.setOnAction(e -> {
            controller.redo(controller.getGame());
        });
    }

    /**
     * Allows to abandon the game.
     */
    private void abandon() {
        controller.setState(State.GAME_OVER);
        update();
    }

    @Override
    public void update() {
        switch (controller.getState()) {
            case SHOW_RESULT:
                initializeGrid(grid);
                controller.canStillMove();
                break;
            case UPDATE_BOARD:
                totalScore.setText("Total Score : " + Integer.toString(controller.scoreAprèsCoup()));
                liveScore.setText("This Click Score : " + Integer.toString(controller.scoreLiveForCoup()));
                remainingBilles.setText("Remaining billes : " + Integer.toString(controller.remainingBilles()));
                controller.decalerVertical();
                controller.decalerHorizontal();
                break;
            case GAME_OVER:
                showGameOver();
                break;
            case GAME_WIN:
                showGameWin();
                break;
        }
    }

    /**
     * Show a game over stage.
     */
    public void showGameOver() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gameStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setStyle("-fx-background-color: #45498A");
        Text text = new Text("GAME OVER");
        text.setFont(new Font(70));
        dialogVbox.getChildren().add(text);
        dialogVbox.setAlignment(Pos.CENTER);
        Scene dialogScene = new Scene(dialogVbox, 500, 700);
        dialog.setScene(dialogScene);
        Button homeBtn = new Button("HOME", new ImageView("file:home.png"));
        homeBtn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 50px;\n"
                + "    -fx-border-width: 3px;\n"
                + "    -fx-border-color: black;");
        dialogVbox.getChildren().add(homeBtn);

        homeBtn.setOnAction(e -> {
            StartViewFx startViewFx = new StartViewFx();
            try {
                startViewFx.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(GameViewFx.class.getName()).log(Level.SEVERE, null, ex);
            }
            gameStage.close();
        });

        dialog.show();
    }

    /**
     * Show a game over stage.
     */
    public void showGameWin() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(gameStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setStyle("-fx-background-image: url('file:win.png')");
        Text text = new Text("WIN");
        text.setFont(new Font(70));
        dialogVbox.getChildren().add(text);
        dialogVbox.setAlignment(Pos.CENTER);
        Scene dialogScene = new Scene(dialogVbox, 500, 700);
        dialog.setScene(dialogScene);
        Button homeBtn = new Button("HOME", new ImageView("file:home.png"));
        homeBtn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 50px;\n"
                + "    -fx-border-width: 3px;\n"
                + "    -fx-border-color: black;");
        dialogVbox.getChildren().add(homeBtn);

        homeBtn.setOnAction(e -> {
            StartViewFx startViewFx = new StartViewFx();
            try {
                startViewFx.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(GameViewFx.class.getName()).log(Level.SEVERE, null, ex);
            }
            gameStage.close();
        });

        dialog.show();
    }

}
