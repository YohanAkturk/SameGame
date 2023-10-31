package g56514.samegame.view;

import javafx.scene.layout.GridPane;

/**
 *
 * @author yohan
 */
public class BackgroundLayout extends GridPane {

    /**
     * Creates the background display.
     */
    public BackgroundLayout(int row, int column, int min, int max) {
        this.setStyle("-fx-border-color: black");
        
        GridPane root = new GridPane();
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                javafx.scene.control.Button btn = new javafx.scene.control.Button();
                btn.setStyle("-fx-background-color: #2E8B57");
                root.add(btn, i, j);
            }
        }
    }
}
