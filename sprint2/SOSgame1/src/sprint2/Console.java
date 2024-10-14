package sprint2;

import sprint2.Board.Cell;


public class Console {
	private Board board;

	public Console(Board board) {
		this.board = board;
	}

	public void displayBoard() {
		for (int row = 0; row<board.getTotalRows(); row++) {
			System.out.println("-------");
			System.out.print("|"+ symbol(board.getCell(row, 0)));
			System.out.print("|"+ symbol(board.getCell(row, 1)));
			System.out.print("|"+ symbol(board.getCell(row, 2)));
			System.out.println("|");
		}
		System.out.println("-------");
	}

	private char symbol(Cell cell) {
		if (cell==Cell.RED)
			return 'R';
		else
			if (cell==Cell.BLUE)
				return 'B';
			else return ' ';
	}
	
	public static void main(String[] args) {
		new Console(new Board(3,3)).displayBoard();; 

	}
}
