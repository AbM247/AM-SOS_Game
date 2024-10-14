package sprint2_Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import sprint2.Board;
import sprint2.GUI;

class TestGameStart {
    private Board board;

    @BeforeAll
    static void initJFX() {
        // Initialize JavaFX toolkit (this is required for running tests involving JavaFX components)
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

            // Assertions to verify the selected mode and the board state
            assertEquals("Simple", GUI.getMode());
            assertNotNull(board.getCell(0, 0));  // Verify that the cell is not null
            
           
        });
        
        
    }
    void testStartGeneral() {
        // Initialize the board in the test method
        board = new Board(5, 5);
        
        // Run the GUI related code in the JavaFX Application Thread
        Platform.runLater(() -> {
            
            GUI.Gmode.setSelected(true);  

            // Assertions to verify the selected mode and the board state
            assertEquals("General", GUI.getMode());
            assertNotNull(board.getCell(0, 0));  // Verify that the cell is not null
            
           
        });
        
        
}
}
