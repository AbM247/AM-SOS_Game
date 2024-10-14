package sprint2;



public class Board {
	public enum Cell {EMPTY, RED, BLUE}

	private  int TotalRows; 
	private  int TotalColumns; 

	private Cell[][] grid;
	private char turn;
	private char [][] playerGrid;
	

	public Board(int TotalRows,int TotalColumns) {
		this.TotalRows = TotalRows;
		this.TotalColumns = TotalColumns;
		grid = new Cell[TotalRows][TotalColumns];
		playerGrid = new char[TotalRows][TotalColumns];
		initBoard();
	}

	public void initBoard() {
		for (int row = 0; row < TotalRows; row++) {
			for (int column = 0; column < TotalColumns; column++) {
				grid[row][column] = Cell.EMPTY;
				playerGrid[row][column]=' ';
			}
		}
		turn = 'R';
	}

	public int getTotalRows() {
		return TotalRows;
	}

	public int getTotalColumns() {
		return TotalColumns;
	}

	public Cell getCell(int row, int column) {
		if (row >= 0 && row < TotalRows && column >= 0 && column < TotalColumns) {
			return grid[row][column];
		} else {
			return null;
		}
	}
	
	public char getPlayerCell(int row, int column) {
		return playerGrid[row][column];
	}

	public char getTurn() {
		return turn;
	}

	public void makeMove(int row, int column) {
		if (row >= 0 && row < TotalRows && column >= 0 && column <TotalColumns && grid[row][column] == Cell.EMPTY) {
			grid[row][column] = (turn == 'R') ? Cell.RED : Cell.BLUE;
			playerGrid[row][column] = turn;
			turn = (turn == 'R') ? 'B' : 'R';
		}
	}

}
