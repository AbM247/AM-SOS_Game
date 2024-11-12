package sprint_Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint.Board;

class TestSimpleOver{
	 private Board board;
	 private Board.SimpleM simpleMode;
	  
	@BeforeEach
    void setUp() {
        // Initialize a 3x3 board and set the game mode to General Mode
        board = new Board(3, 3);
        simpleMode = board.new SimpleM();
        board.setGameMode(simpleMode);
    }
	@Test
    void testSimpleRedWin() {
 
        // Make the first move with 'R' player setting an 'S' at (0,0)
        simpleMode.makeMove(0, 0, "S");
        assertEquals(Board.Cell.RED_S, board.getCell(0, 0), "Cell (0,0) should contain RED_S");

        // Make the second move with 'B' player setting an 'O' at (0,1)
        simpleMode.makeMove(0, 1, "O");
        assertEquals(Board.Cell.BLUE_O, board.getCell(0, 1), "Cell (0,1) should contain BLUE_O");

        // Make the third move with 'R' player setting an 'S' at (0,2), completing an SOS
        simpleMode.makeMove(0, 2, "S");
        assertEquals(Board.Cell.RED_S, board.getCell(0, 2), "Cell (0,2) should contain RED_S");

        // Update game state to check for a winner
        simpleMode.updateGameState('R', 0, 2);

        // Check if the game state reflects the correct winner
        assertEquals(Board.GameState.RED_WON, board.getGameState(), "The game should be won by Red player");
    }
    @Test
    void testSimpleBlueWin() {
        
        // Make the first move with 'R' player setting an 'S' at (0,0)
        simpleMode.makeMove(0, 0, "S");
        assertEquals(Board.Cell.RED_S, board.getCell(0, 0), "Cell (0,0) should contain RED_S");

        // Make the second move with 'B' player setting an 'O' at (0,1)
        simpleMode.makeMove(0, 1, "O");
        assertEquals(Board.Cell.BLUE_O, board.getCell(0, 1), "Cell (0,1) should contain BLUE_O");
        
        simpleMode.makeMove(2, 2, "O"); //Red move

        // Make the third move with 'R' player setting an 'S' at (0,2), completing an SOS
        simpleMode.makeMove(0, 2, "S");
        assertEquals(Board.Cell.BLUE_S, board.getCell(0, 2), "Cell (0,2) should contain Blue_S");

        // Update game state to check for a winner
        simpleMode.updateGameState('B', 0, 2);

        // Check if the game state reflects the correct winner
        assertEquals(Board.GameState.BLUE_WON, board.getGameState(), "The game should be won by Blue player");
    }

}