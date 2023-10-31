package g56514.samegame.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author yohan
 */
public class PositionTest {

    @Test
    public void test_Constructor() {
        Position pos = new Position(1, 0);
        assertEquals(1, pos.getRow());
        assertEquals(0, pos.getColumn());
    }

}
