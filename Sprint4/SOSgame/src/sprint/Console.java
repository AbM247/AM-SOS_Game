package sprint;


import sprint.Board.Cell;



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
	    switch (cell) {
	        case RED_S: return 'S';
	        case RED_O: return 'O';
	        case BLUE_S: return 'S';
	        case BLUE_O: return 'O';
	        default: return ' ';
	    }
	}

	
	public static void main(String[] args) {
		new Console(new Board(3,3)).displayBoard();; 

	}
}
