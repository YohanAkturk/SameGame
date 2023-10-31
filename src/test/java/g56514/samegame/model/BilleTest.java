package g56514.samegame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author yohan
 */
public class BilleTest {

    @Test
    public void test_Constructor() {
        Bille bille = new Bille(1);
        assertEquals(1, bille.getIndexBille());
    }

    @Test
    public void isExplosif_When_Bille_Is_Not_Explosif() {
        Bille bille = new Bille(1);
        assertEquals(false, bille.isIsExplosif());
    }

    @Test
    public void isExplosif_When_Bille_Is_Explosif() {
        Bille bille = new Bille(1);
        bille.makesExplosif();
        assertEquals(true, bille.isIsExplosif());
    }

    @Test
    public void isExplosif_When_Bille_Is_Explosif_After_Makes_It_Explosif() {
        Bille bille = new Bille(1);
        bille.makesExplosif();
        bille.makesNotExplosif();
        assertEquals(false, bille.isIsExplosif());
    }

}
