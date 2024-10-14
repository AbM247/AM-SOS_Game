package sprint2_Test;




import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint2.Board;
import sprint2.GUI;
import sprint2.Console;


public class TestSimpleMove {

    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board(3, 3); // Simple game with 3x3 board
    }

    @Test
    public void testMakeMoveSimpleGame() {
        // Player 1 (RED) makes a move at (0, 0)
        board.makeMove(0, 0); // First move by Player RED
        assertEquals(Board.Cell.RED, board.getCell(0, 0)); // RED is placed
        assertEquals('B', board.getTurn()); // Turn switches to Player BLUE

        // Player 2 (BLUE) makes a move at (0, 1)
        board.makeMove(0, 1); // Second move by Player BLUE
        assertEquals(Board.Cell.BLUE, board.getCell(0, 1)); // BLUE is placed
        assertEquals('R', board.getTurn()); // Turn switches back to Player RED
    }

    @Test
    public void testInvalidMoveSimpleGame() {
        // Player 1 (RED) makes a valid move at (0, 0)
        board.makeMove(0, 0);
        assertEquals(Board.Cell.RED, board.getCell(0, 0));

        // Player 2 (BLUE) tries to make a move in the same spot (invalid move)
        board.makeMove(0, 0);
        assertEquals(Board.Cell.RED, board.getCell(0, 0)); // No change in state
        assertEquals('B', board.getTurn()); // Turn remains Player 2 (BLUE)
    }
}