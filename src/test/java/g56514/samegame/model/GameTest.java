package g56514.samegame.model;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author yohan
 */
public class GameTest {

    private Game game;

    @Test
    public void testInitializeBoard() {
        int min = 0;
        int max = 2;
        game = new Game(12, 16, min, max);
        assertFalse(game.getBoard().isEmpty());
    }

    @Test
    public void getNeighbours() {
        Game game = new Game(8, 8, 0, 0);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(3, 3));
        game.getBoard().put(new Bille(0), new Position(4, 4));
        game.getBoard().put(new Bille(0), new Position(3, 4));
        game.getBoard().put(new Bille(0), new Position(4, 3));
        game.findNeighbours(new Position(4, 4), 0);
        assertTrue(game.getNeighbours().size() == 4);
    }

    @Test
    public void getNeighbours_When_1_Bille_Only() {
        Game game = new Game(8, 8, 0, 0);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(4, 4));
        game.findNeighbours(new Position(4, 4), 0);
        assertTrue(game.getNeighbours().isEmpty());
    }

    @Test
    public void deleteBilles() {
        Game game = new Game(8, 8, 0, 0);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(3, 3));
        game.getBoard().put(new Bille(0), new Position(4, 4));
        game.getBoard().put(new Bille(0), new Position(3, 4));
        game.getBoard().put(new Bille(0), new Position(4, 3));
        game.findNeighbours(new Position(4, 4), 0);
        game.deleteBilles();
        assertTrue(game.getBoard().getBille(new Position(3, 3)) == null
                && game.getBoard().getBille(new Position(4, 4)) == null
                && game.getBoard().getBille(new Position(3, 4)) == null
                && game.getBoard().getBille(new Position(4, 3)) == null);
    }

    /* === Tests for decalerHorizontal === */
    @Test
    public void décalerHorizontal_With_First_Empty_Column() {
        Game game = new Game(3, 4);
        for (int i = 1; i < game.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < game.getBoard().getRowSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(j, i));
            }
        }
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
        game.decalerHorizontal();
        System.out.println("AFTER");
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
        System.out.println(game.getBoard().getBille(new Position(0, 0)).getIndexBille());
        assertTrue(game.getBoard().getBilles()[0][(game.getBoard().getColumnSize() - 1)] == (null)
                && game.getBoard().getBille(new Position(0, 0)).getIndexBille() == 1);
    }

    @Test
    public void décalerHorizontal_With_Two_Empty_Columns() {
        Game game = new Game(3, 4);
        for (int i = 2; i < game.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < game.getBoard().getRowSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(j, i));
            }
        }
        game.decalerHorizontal();
        assertTrue(game.getBoard().getBille(new Position(0, 0)).getIndexBille() == 1
                && game.getBoard().getBille(new Position(0, 1)).getIndexBille() == 1);
    }

    @Test
    public void décalerHorizontal_With_No_Empty_Column() {
        Game game = new Game(3, 4);
        System.out.println(game.getBoard().getRowSize());
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.decalerHorizontal();
        assertTrue(game.getBoard().getBille(new Position(0, game.getBoard().getColumnSize() - 1)).getIndexBille() == 1);
    }

    /* === Tests for decalerVertical === */
    @Test
    public void décalerVertical_With_1_Column_With_An_Empty_Case() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < game.getBoard().getRowSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(j, i));
            }
        }
        game.getBoard().put(null, new Position(2, 0));
        game.decalerVertical();
        assertTrue(game.getBoard().getBille(new Position(0, 0)) == null);
    }

    public void décalerVertical_With_No_Completely_Empty_Row() {
        Game game = new Game(3, 4);
        System.out.println(game.getBoard().getRowSize());
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            game.getBoard().put(null, new Position(2, i));
        }
        game.decalerVertical();
        assertTrue(game.getBoard().getBille(new Position(0, 0)) == null
                && game.getBoard().getBille(new Position(0, 1)) == null
                && game.getBoard().getBille(new Position(0, 2)) == null
                && game.getBoard().getBille(new Position(0, 3)) == null);
    }

    public void décalerVertical_With_No_Empty_Row() {
        Game game = new Game(3, 4);
        System.out.println(game.getBoard().getRowSize());
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.decalerVertical();
        assertTrue(game.getBoard().getBille(new Position(0, game.getBoard().getColumnSize() - 1)).getIndexBille() == 1);
    }

    /* === Tests for scoreAprèsCoup === */
    @Test
    public void scoreAprèsCoup_0_Exploded_Bille() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(1, 1));
        game.findNeighbours(new Position(1, 1), game.getBoard().getBilles()[1][1].getIndexBille());
        assertTrue(game.scoreAprèsCoup() == 0);
    }

    @Test
    public void scoreAprèsCoup_2_Exploded_Billes() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(1, 1));
        game.getBoard().put(new Bille(0), new Position(1, 2));
        game.findNeighbours(new Position(1, 2), game.getBoard().getBilles()[1][2].getIndexBille());
        assertTrue(game.scoreAprèsCoup() == 2);
    }

    @Test
    public void scoreAprèsCoup_4_Exploded_Billes() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(1, 0));
        game.getBoard().put(new Bille(0), new Position(1, 1));
        game.getBoard().put(new Bille(0), new Position(1, 2));
        game.getBoard().put(new Bille(0), new Position(1, 3));
        game.findNeighbours(new Position(1, 2), game.getBoard().getBilles()[1][2].getIndexBille());
        assertTrue(game.scoreAprèsCoup() == 12);
    }

    /* === Tests for scoreLiveForCoup === */
    @Test
    public void scoreLiveForCoup_0_Exploded_Bille() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(1, 1));
        game.findNeighbours(new Position(1, 1), game.getBoard().getBilles()[1][1].getIndexBille());
        assertTrue(game.scoreLiveForCoup() == 0);
    }

    @Test
    public void scoreAprèsCoup_2_Exploded_Billes_Followed_By_10_Others() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(1, 1));
        game.getBoard().put(new Bille(0), new Position(1, 2));
        game.findNeighbours(new Position(1, 2), game.getBoard().getBilles()[1][2].getIndexBille());
        assertTrue(game.scoreLiveForCoup() == 2);
        game.decalerHorizontal();
        game.decalerVertical();
        game.deleteBilles();
        game.findNeighbours(new Position(2, 0), game.getBoard().getBilles()[2][0].getIndexBille());
        assertTrue(game.scoreLiveForCoup() == 90);
    }

    /* === Tests for remainingBilles === */
    @Test
    public void remainingBilles_At_The_Beginning() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        assertTrue(game.remainingBilles() == game.getBoard().getRowSize() * game.getBoard().getColumnSize());
    }

    @Test
    public void remainingBilles_After_2_Billes_exploded() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(1, 1));
        game.getBoard().put(new Bille(0), new Position(1, 2));
        game.findNeighbours(new Position(1, 2), game.getBoard().getBilles()[1][2].getIndexBille());
        game.deleteBilles();
        assertTrue(game.remainingBilles() == game.getBoard().getRowSize() * game.getBoard().getColumnSize() - 2);
    }

    /* === Tests for canStillMove === */
    @Test
    public void canStillMove_When_1_Bille_Rest() {
        Game game = new Game(3, 4);
        game.getBoard().put(new Bille(0), new Position(game.getBoard().getRowSize() - 1, 0));
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
        assertFalse(game.canStillMove());
    }

    @Test
    public void canStillMove_When_First_Bille_Without_Combination_Possible() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(game.getBoard().getRowSize() - 1, 0));
        assertTrue(game.canStillMove());
    }

    /* === Tests for isEmpty === */
    @Test
    public void isEmpty_True_When_Empty() {
        game = new Game(12, 16);
        assertTrue(game.isEmpty());
    }

    @Test
    public void isEmpty_False_When_Not_Empty() {
        Game game = new Game(3, 4, 1, 3);
        assertFalse(game.isEmpty());
    }

    @Test
    public void isEmpty_False_After_Playing() {
        Game game = new Game(3, 4);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.findNeighbours(new Position(0, 0), 1);
        game.deleteBilles();
        assertTrue(game.isEmpty());
    }

    @Test
    public void isEmpty_False_When_1_Bille_Left() {
        Game game = new Game(2, 2);
        for (int i = 0; i < game.getBoard().getRowSize(); i++) {
            for (int j = 0; j < game.getBoard().getColumnSize(); j++) {
                game.getBoard().put(new Bille(1), new Position(i, j));
            }
        }
        game.getBoard().put(new Bille(0), new Position(game.getBoard().getRowSize() - 1, 0));
        game.findNeighbours(new Position(0, 0), 1);
        game.deleteBilles();
        assertFalse(game.isEmpty());
    }

}
