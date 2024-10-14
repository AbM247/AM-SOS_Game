package sprint2_Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sprint2.Board;
import sprint2.GUI;
import sprint2.Console;


class ChooseSizeTest {
	private Board board;

	@Test
	void testBoardSize() {
		board = new Board(5,5);
		assertNotNull(board.getCell(0, 0));
	}

}
