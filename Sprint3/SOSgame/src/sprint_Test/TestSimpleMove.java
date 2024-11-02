package sprint_Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sprint.Board;

class TestSimpleMove{
	 private Board board;
	 private Board.SimpleM simpleMode;
	  
	@BeforeEach
    void setUp() {
     
        board = new Board(3, 3);
        simpleMode = board.new SimpleM();
        board.setGameMode(simpleMode);
    }
	@Test
    void testSimpleModeMove() {
       
        Board board = new Board(3, 3);
        Board.GameMode simpleMode = board.new SimpleM();
        board.setGameMode(simpleMode);

       
        simpleMode.makeMove(0, 0, "S");
        assertEquals(Board.Cell.RED_S, board.getCell(0, 0), "Cell (0,0) should contain RED_S");
        
        simpleMode.makeMove(0, 1, "O");
        assertEquals(Board.Cell.BLUE_O, board.getCell(0, 1), "Cell (0,1) should contain BLUE_O");

}
}