package g56514.samegame.view;

import g56514.samegame.controller.Controller;
import g56514.samegame.model.Game;
import g56514.samegame.model.Position;
import g56514.samegame.model.State;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author yohan
 */
public class View implements Observer {

    private Controller controller;
    private Game game;
    Scanner clavier = new Scanner(System.in);

    /**
     * View's constructor.
     *
     * @param controller the controller.
     * @param game the model.
     */
    public View(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;
    }

    /**
     * Gives the parameters to initialize the game.
     *
     * @return the parameters.
     */
    public static String[] initializationGame() {
        String tab[] = new String[3];
        tab[0] = readStringNbRow("Entrez le nombre de lignes : ");
        tab[1] = readStringNbCol("Entrez le nombre de colonnes : ");
        String niveau = readNiveau("Choisisez le niveau : facile - normal - difficile");
        switch (niveau) {
            case "facile":
                tab[2] = "3";
                break;
            case "normal":
                tab[2] = "4";
                break;
            case "difficile":
                tab[2] = "5";
                break;
        }
        return tab;
    }

    /**
     * Allows to play.
     */
    public void start() {
        switch (controller.getState()) {
            case SHOW_RESULT:
                afficher();
                controller.canStillMove();
                break;
            case GIVE_POSITION_OR_ABANDON:
                System.out.println("abandon - continue - undo - redo? ");
                String enter = clavier.nextLine();
                if (enter.contains("a")) {
                    System.out.println("Do you want abandon ? yes - no");
                    String confirm = clavier.nextLine();
                    if (confirm.contains("y")) {
                        controller.setState(State.GAME_OVER);
                        System.out.println("GAME OVER");
                    } else {
                        Position pos = askPosition();
                        if (!controller.getBoard().isInside(pos)) {
                            System.out.println("position is not in the board");
                        } else {
                            controller.findNeighbours(pos, controller.getBoard().getBilles()[pos.getRow()][pos.getColumn()].getIndexBille());
                            controller.deleteBilles();
                            controller.isEmpty();
                        }
                    }
                } else if (enter.contains("c")) {
                    Position pos = askPosition();
                    while (!controller.getBoard().isInside(pos) || controller.getBoard().getBille(pos) == null) {
                        System.out.println("position is not in the board");
                        pos = askPosition();
                    }
                    controller.findNeighbours(pos, controller.getBoard().getBilles()[pos.getRow()][pos.getColumn()].getIndexBille());
                    controller.deleteBilles();
                    controller.isEmpty();
                } else if (enter.equals("undo")) {
                    controller.undo(game);
                } else if (enter.equals("redo")) {
                    controller.redo(game);
                }
                break;
            case UPDATE_BOARD:
                controller.decalerVertical();
                controller.decalerHorizontal();
                break;
            case GAME_OVER:
                System.out.println("GAME OVER");
                break;
            case GAME_WIN:
                System.out.println("CONGRATULATION!!!");
                break;
        }

    }

    /**
     * Shows the game board.
     */
    public void afficher() {
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                if (game.getBoard().getBilles()[i][j] != null) {
                    System.out.print(game.getBoard().getBille(new Position(i, j)).getIndexBille() + " ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Allows to read a string number. If there is only one number, it must be
     * between 2 and 9.
     *
     * @param message the messasge to be displayed.
     * @return the string entered.
     */
    private static String readNiveau(String message) {
        Scanner scanner = new Scanner(System.in);
        String niveau = "";
        System.out.println(message);
        niveau = scanner.nextLine();
        while (!niveau.contains("fa") && !niveau.contains("no") && !niveau.contains("di")) {
            System.out.println("entered not valid");
            System.out.println(message);
            niveau = "";
            niveau = scanner.nextLine();
        }
        if(niveau.contains("fa")) niveau = "facile";
        if(niveau.contains("no")) niveau = "normal";
        if(niveau.contains("di")) niveau = "difficile";
        return niveau;
    }

    /**
     * Allows to read a string in a robust way.
     *
     * @param message the messasge to be displayed.
     * @return the string entered.
     */
    private static String readStringNbRow(String message) {
        Scanner scanner = new Scanner(System.in);
        String nb = "";
        System.out.println(message);
        nb = scanner.nextLine();
        if (nb.length() < 2) {
            while (!String.valueOf(nb.charAt(0)).matches("[2-9]") && nb.length() < 2) {
                System.out.println("The number must be an integer between 2 and 9");
                System.out.println(message);
                nb = "";
                nb = scanner.nextLine();
            }
        } else if (nb.length() >= 2) {
            while (!nb.matches("\\d\\d")) {
                System.out.println("The number is not an integer...");
                System.out.println(message);
                nb = "";
                nb = scanner.nextLine();
            }
        }
        return nb;

    }

    /**
     * Allows to read a string number. If there is only one number, it must be
     * between 1 and 9.
     *
     * @param message the messasge to be displayed.
     * @return the string entered.
     */
    private static String readStringNbCol(String message) {
        Scanner scanner = new Scanner(System.in);
        String nb = "";
        System.out.println(message);
        nb = scanner.nextLine();
        if (nb.length() < 2) {
            while (!String.valueOf(nb.charAt(0)).matches("[1-9]") && nb.length() < 2) {
                System.out.println("The number muste be a non-null integer...");
                System.out.println(message);
                nb = "";
                nb = scanner.nextLine();
            }
        } else if (nb.length() >= 2) {
            while (!nb.matches("\\d\\d")) {
                System.out.println("The number is not an integer...");
                System.out.println(message);
                nb = "";
                nb = scanner.nextLine();
            }
        }
        return nb;
    }

    /**
     * Read and returns an integer in a robust way.
     *
     * @param message the messasge to be displayed.
     * @return the integer entered.
     */
    private int readInteger(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("The number is not an integer...");
            System.out.println(message);
        }
        return scanner.nextInt();

    }

    /**
     * Gives a position by asking it..
     *
     * @return the position.
     */
    public Position askPosition() {
        Position pos = askRowAndColumn();
        return pos;
    }

    /**
     * Gives a position by asking the row and the column.
     *
     * @return the position.
     */
    private Position askRowAndColumn() {
        int row = readInteger("Enter a row number");
        int column = readInteger("Enter a column number");
        Position pos = new Position(row, column);
        return pos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.clavier);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final View other = (View) obj;
        if (!Objects.equals(this.clavier, other.clavier)) {
            return false;
        }
        return true;
    }

    @Override
    public void update() {
        start();
    }
}
