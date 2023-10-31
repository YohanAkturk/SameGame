package g56514.samegame.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

/**
 *
 * @author yohan
 */
public class MainLayout extends BorderPane {

    private final Button startBtn = new Button("Start");
    private final Slider slider = new Slider(3, 5, 3);
    private ToggleGroup toggleGroup = new ToggleGroup();
    private RadioMenuItem menu1;
    private RadioMenuItem menu2;
    private RadioMenuItem menu3;

    /**
     * Creates the center window that allows the user choose his options.
     */
    public MainLayout() {
        this.setStyle("-fx-background-color: #A0A4F7");
        this.setPrefSize(300, 300);
        Text text = new Text("SameGame");
        text.setFont(new Font(70));
        Text underSlider = new Text("Level");

        this.setMargin(slider, new Insets(10, 120, 0, 120));
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setOnMouseReleased(event -> {
            if (slider.getValue() % 1 != 0) {
                slider.setValue(Math.round(slider.getValue()));
                System.out.println(slider.getValue());
            }
        });
        slider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double nb) {
                String result = "";
                if (nb == 3) {
                    result = "facile";
                }
                if (nb == 4) {
                    result = "normal";
                }
                if (nb == 5) {
                    result = "difficile";
                }
                return result;
            }

            @Override
            public Double fromString(String s) {
                double d = 3;
                switch (s) {
                    case "facile":
                        d = 3;
                        break;
                    case "normal":
                        d = 4;
                        break;
                    case "difficile":
                        d = 5;
                        break;
                }
                return d;
            }
        });

        HBox hBox = new HBox();
        Menu configuration = new Menu("Configuratoin");
        configuration.setGraphic(new ImageView("file:config.png"));
        MenuBar configBar = new MenuBar(configuration);

        //menu1
        menu1 = new RadioMenuItem("4 x 3");
        //menu2
        menu2 = new RadioMenuItem("12 x 16");
        //menu3
        menu3 = new RadioMenuItem("16 x 20");
        configuration.getItems().addAll(menu1, menu2, menu3);
        toggleGroup.getToggles().addAll(menu1, menu2, menu3);

        VBox vBoxMenu = new VBox(configBar);

        startBtn.setStyle("-fx-background-radius: 100px; -fx-border-radius: 50px;\n"
                + "    -fx-border-width: 3px;\n"
                + "    -fx-border-color: black;");
        hBox.setSpacing(20);

        VBox vBoxCenter = new VBox();
        vBoxCenter.getChildren().addAll(text, slider, underSlider, startBtn);

        vBoxCenter.setAlignment(Pos.CENTER);
        vBoxCenter.setPadding(new Insets(200)); //la largeur du slider!
        vBoxCenter.setMargin(text, new Insets(20));
        vBoxCenter.setMargin(slider, new Insets(20));
        vBoxCenter.setMargin(startBtn, new Insets(20));
        this.setTop(vBoxMenu);
        this.setCenter(vBoxCenter);
        this.setBottom(hBox);
    }

    /**
     * Getter of the start button.
     *
     * @return the start button.
     */
    public Button getStartBtn() {
        return startBtn;
    }

    /**
     * Getter of the slider.
     *
     * @return its value.
     */
    public Slider getSlider() {
        return slider;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }
    
    /**
     * Gives the string selected menu.
     * 
     * @return the string selected menu.
     */
    public String theSelectedMenu(){
        if(menu1.isSelected()) return menu1.getText();
        if(menu2.isSelected()) return menu2.getText();
        if(menu3.isSelected()) return menu3.getText();
        return menu1.getText();
    }
}
