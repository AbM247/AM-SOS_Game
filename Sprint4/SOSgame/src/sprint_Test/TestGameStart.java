package sprint_Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import sprint.Board;
import sprint.GUI;

class TestGameStart {
    private Board board;

    @BeforeAll
    static void initJFX() {
        // Initialize JavaFX toolkit
        Platform.startup(() -> {});
    }

    @Test
    void testStartSimple() {
        // Initialize the board in the test method
        board = new Board(5, 5);
        
        // Run the GUI related code in the JavaFX Application Thread
        Platform.runLater(() -> {
            // Simulate the selection of "Simple" mode
            GUI.Smode.setSelected(true);  // Select the Simple mode

            // Verify the mode selected
            String mode = GUI.getMode();
            assertEquals("Simple", mode, "The selected mode should be Simple");
            
            // Verify the initial state of the board
            assertNotNull(board.getCell(0, 0), "The cell should not be null");
            assertEquals(Board.Cell.EMPTY, board.getCell(0, 0), "The initial cell should be EMPTY");

            // You may want to add more assertions based on your game's logic
        });
    }

    @Test
    void testStartGeneral() {
        // Initialize the board in the test method
        board = new Board(5, 5);
        
        // Run the GUI related code in the JavaFX Application Thread
        Platform.runLater(() -> {
            // Simulate the selection of "General" mode
            GUI.Gmode.setSelected(true);  // Select the General mode

            // Verify the mode selected
            String mode = GUI.getMode();
            assertEquals("General", mode, "The selected mode should be General");

            // Verify the initial state of the board
            assertNotNull(board.getCell(0, 0), "The cell should not be null");
            assertEquals(Board.Cell.EMPTY, board.getCell(0, 0), "The initial cell should be EMPTY");

            // You may want to add more assertions based on your game's logic
        });
    }
}
