import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {

    private Main main;

    @BeforeEach
    public void setup() {
        main = new Main();
    }

    @Test
    public void testCheckWinNoWin() {
        main.map.put(11, new GameButton());
        main.map.put(12, new GameButton());
        main.map.put(13, new GameButton());
        main.map.put(14, new GameButton());
        boolean result = main.checkWin(11);
        Assertions.assertFalse(result);
    }

    @Test
    public void testCheckWinHorizontalWin() {
        main.map.put(11, new GameButton());
        main.map.put(12, new GameButton());
        main.map.put(13, new GameButton());
        main.map.put(14, new GameButton());
        main.map.put(15, new GameButton());
        boolean result = main.checkWin(11);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckWinVerticalWin() {
        main.map.put(11, new GameButton());
        main.map.put(21, new GameButton());
        main.map.put(31, new GameButton());
        main.map.put(41, new GameButton());
        boolean result = main.checkWin(11);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckWinDiagonalWin() {
        main.map.put(11, new GameButton());
        main.map.put(22, new GameButton());
        main.map.put(33, new GameButton());
        main.map.put(44, new GameButton());
        boolean result = main.checkWin(11);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckWinNoNeighborWin() {
        boolean result = main.checkWin(11);
        Assertions.assertFalse(result);
    }
}
