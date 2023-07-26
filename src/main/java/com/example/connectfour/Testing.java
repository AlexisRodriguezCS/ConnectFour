/***************************************************************
 * File: Testing.java
 * Purpose: Contains JUnit test cases for the Main class, which represents a game with buttons.
 * Author: Alexis Rodriguez
 ***************************************************************/

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
        // Test case to check if there's no win (no horizontal, vertical, or diagonal sequence)
        main.map.put(11, new GameButton());
        main.map.put(12, new GameButton());
        main.map.put(13, new GameButton());
        main.map.put(14, new GameButton());
        boolean result = main.checkWin(11);
        Assertions.assertFalse(result);
    }

    @Test
    public void testCheckWinHorizontalWin() {
        // Test case to check if there's a horizontal win sequence
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
        // Test case to check if there's a vertical win sequence
        main.map.put(11, new GameButton());
        main.map.put(21, new GameButton());
        main.map.put(31, new GameButton());
        main.map.put(41, new GameButton());
        boolean result = main.checkWin(11);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckWinDiagonalWin() {
        // Test case to check if there's a diagonal win sequence
        main.map.put(11, new GameButton());
        main.map.put(22, new GameButton());
        main.map.put(33, new GameButton());
        main.map.put(44, new GameButton());
        boolean result = main.checkWin(11);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckWinNoNeighborWin() {
        // Test case to check if there's no win and no neighbors around the selected button
        boolean result = main.checkWin(11);
        Assertions.assertFalse(result);
    }
}
