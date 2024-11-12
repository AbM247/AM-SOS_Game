package sprint_Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint.Board;


class TestGeneralOver {
    private Board board;
    private Board.GeneralM generalMode;


    @BeforeEach
    void setUp() {
        // Initialize a 3x3 board and set the game mode to General Mode
        board = new Board(4, 4);
        generalMode = board.new GeneralM();
        board.setGameMode(generalMode);
    }
    @Test
    void testGeneralRedWin() {
        // Make the first move with 'R' player setting an 'S' at (0,0)
        generalMode.makeMove(0, 0, "S");
        assertEquals(Board.Cell.RED_S, board.getCell(0, 0), "Cell (0,0) should contain RED_S");

        // Verify turn switches to 'B'
        assertEquals('B', board.getTurn(), "Turn should switch to 'B' after Red's move");

        // Make the second move with 'B' player setting an 'O' at (0,1)
        generalMode.makeMove(0, 1, "O");
        assertEquals(Board.Cell.BLUE_O, board.getCell(0, 1), "Cell (0,1) should contain BLUE_O");

        // Next move with 'R' player sets an 'S' at (0,2), completing an SOS
        generalMode.makeMove(0, 2, "S");
        assertEquals(Board.Cell.RED_S, board.getCell(0, 2), "Cell (0,2) should contain RED_S");

        // Check for SOS and update game state
        generalMode.updateGameState('R', 0, 2);

        // Ensure Red's score increased due to the SOS sequence
        //assertEquals(1, generalMode.getScoreR(), "Red should score 1 point after completing SOS");

        // Verify turn switches back to 'B'
        assertEquals('B', board.getTurn(), "Turn should switch back to 'B' after Red's scoring move");
        
        generalMode.makeMove(1, 0, "S");
        generalMode.makeMove(1, 1, "S");
        generalMode.makeMove(1, 2, "S");
        generalMode.makeMove(2, 0, "S");
        generalMode.makeMove(2, 1, "S");
        generalMode.makeMove(2, 2, "S");
        
        assertEquals(Board.GameState.RED_WON, board.getGameState(), "The game should be won by Red player");

    }
    @Test
    void testGeneralBlueWin() {
        // Make the first move with 'R' player setting an 'S' at (0,0)
        generalMode.makeMove(0, 0, "S");
        assertEquals(Board.Cell.RED_S, board.getCell(0, 0), "Cell (0,0) should contain RED_S");

        // Verify turn switches to 'B'
        assertEquals('B', board.getTurn(), "Turn should switch to 'B' after Red's move");

        // Make the second move with 'B' player setting an 'O' at (0,1)
        generalMode.makeMove(0, 1, "O");
        assertEquals(Board.Cell.BLUE_O, board.getCell(0, 1), "Cell (0,1) should contain BLUE_O");

        // Add a move for 'R' player at (2,2)
        generalMode.makeMove(2, 2, "S"); // Red player sets 'O'
        assertEquals(Board.Cell.RED_S, board.getCell(2, 2));

        // Next move with 'B' player sets an 'S' at (0,2), completing an SOS
        generalMode.makeMove(0, 2, "S");
        assertEquals(Board.Cell.BLUE_S, board.getCell(0, 2), "Cell (0,2) should contain BLUE_S");

        // Check for SOS and update game state
        generalMode.updateGameState('B', 0, 2); // Pass 'B' for Blue player

        // Ensure Blue's score increased due to the SOS sequence
       // assertEquals(1, generalMode.getScoreB(), "Blue should score 1 point after completing SOS");

        // Verify turn switches back to 'R'
        assertEquals('R', board.getTurn(), "Turn should switch back to 'R' after Blue's scoring move");

        // Complete additional moves to ensure Blue wins
        generalMode.makeMove(1, 0, "S");
        generalMode.makeMove(1, 1, "S");
        generalMode.makeMove(1, 2, "S");
        
        generalMode.makeMove(2, 0, "S");
        generalMode.makeMove(2, 1, "S");


        // Check for game state
        assertEquals(Board.GameState.BLUE_WON, board.getGameState(), "The game should be won by Blue player");
    }
    
}