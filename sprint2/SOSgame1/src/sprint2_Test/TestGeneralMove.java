package sprint2_Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint2.Board;
import sprint2.GUI;
import sprint2.Console;

public class TestGeneralMove {

    private Board board;

    @BeforeEach
    public void setup() {
        board = new Board(5, 5); // General game with 5x5 board
    }

    @Test
    public void testMakeMoveGeneralGame() {
        // Player 1 (RED) makes a move at (2, 2)
        board.makeMove(2, 2); // First move by Player RED
        assertEquals(Board.Cell.RED, board.getCell(2, 2)); // RED is placed
        assertEquals('B', board.getTurn()); // Turn switches to Player BLUE

        // Player 2 (BLUE) makes a move at (3, 3)
        board.makeMove(3, 3); // Second move by Player BLUE
        assertEquals(Board.Cell.BLUE, board.getCell(3, 3)); // BLUE is placed
        assertEquals('R', board.getTurn()); // Turn switches back to Player RED
    }

    @Test
    public void testInvalidMoveGeneralGame() {
        // Player 1 (RED) makes a valid move at (4, 4)
        board.makeMove(4, 4);
        assertEquals(Board.Cell.RED, board.getCell(4, 4));

        // Player 2 (BLUE) tries to make a move in the same spot (invalid move)
        board.makeMove(4, 4);
        assertEquals(Board.Cell.RED, board.getCell(4, 4)); // No change in state
        assertEquals('B', board.getTurn()); // Turn remains Player BLUE
    }
}