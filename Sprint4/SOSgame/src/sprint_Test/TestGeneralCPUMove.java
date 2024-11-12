package sprint_Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint.Board;


class TestGeneralCPUMove {
    private Board board;
    private Board.GeneralM generalMode;

    @BeforeEach
    void setUp() {
      
        board = new Board(4, 4);
        generalMode = board.new GeneralM();
        board.setGameMode(generalMode);
    }
    @Test
    void testGeneralMove() {
        
        generalMode.makeMove(0, 0, "S");
        assertEquals(Board.Cell.RED_S, board.getCell(0, 0), "Cell (0,0) should contain RED_S");

    
        assertEquals('B', board.getTurn(), "Turn should switch to 'B' after Red's move");

       
        generalMode.makeMove(0, 1, "O");
        assertEquals(Board.Cell.BLUE_O, board.getCell(0, 1), "Cell (0,1) should contain BLUE_O");


    }
}