package sprint_Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sprint.Board;
import sprint.Console;
import sprint.GUI;


class ChooseSizeTest {
	private Board board;

	@Test
	void testBoardSize() {
		board = new Board(5,5);
		assertNotNull(board.getCell(0, 0));
	}

}
