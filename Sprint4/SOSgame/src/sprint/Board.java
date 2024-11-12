package sprint;


public class Board {
	
	public enum Cell {EMPTY, RED_S, RED_O,BLUE_S ,BLUE_O}
	public enum GameState {PLAYING, DRAW, RED_WON, BLUE_WON}
	
	
	protected  int TotalRows; 
	protected  int TotalColumns; 

	protected Cell[][] grid;
	protected char turn;
	protected GameState currentGameState;
	protected GameMode gameMode;
	
	protected boolean isPlayer1CPU;
	protected boolean isPlayer2CPU;
	
	public abstract class GameMode{
		

		public boolean isSOS(char turn, int row, int column) {
			
		    for (int col = (column - 2 < 0 ? 0 :column -2 ); col <= (column >= TotalColumns ? TotalColumns - 1: column); col++) {
		        if (col + 2 < TotalColumns &&
		            isS(grid[row][col]) &&
		            isO(grid[row][col + 1]) &&
		            isS(grid[row][col + 2]) ) {
		            return true;
		        }
		    }

		    for (int r = (row - 2 < 0 ? 0: row-2); r <= (row +2 >= TotalRows - 1?TotalRows: row+2); r++) {
		        if (r + 2 < TotalRows &&
		            isS(grid[r][column] )&&
		            isO(grid[r + 1][column])  &&
		            isS(grid[r + 2][column])) {
		            return true;
		        }
		    }

		    for (int i = -2; i <= 0; i++) {
		        int r = row + i, c = column + i;
		        if (r >= 0 && c >= 0 && r + 2 < TotalRows && c + 2 < TotalColumns &&
		            isS(grid[r][c]) &&
		            isO(grid[r + 1][c + 1]) &&
		            isS(grid[r + 2][c + 2]) ) {
		            return true;
		        }
		    }
		    for (int i = -2; i <= 0; i++) {
		        int r = row + i, c = column - i;
		        if (r >= 0 && c < TotalColumns && r + 2 < TotalRows && c - 2 >= 0 &&
		            isS(grid[r][c])  &&
		            isO(grid[r + 1][c - 1] )&&
		            isS(grid[r + 2][c - 2]) ) {
		            return true;
		        }
		    }

		    return false;
		}

		public boolean isDraw() {
			for (int row = 0; row < TotalRows; ++row) {
				for (int col = 0; col < TotalColumns; ++col) {
					if (grid[row][col] == Cell.EMPTY) {
						return false; 
					}
				}
			}
			return true;
		}
		
		public void makeMove(int row, int column,String letter) {
			if (grid[row][column] == Cell.EMPTY) {
	            if (turn == 'R') {
	                if (letter.equals("S")) {
	                    grid[row][column] = Cell.RED_S; 
	                } else {
	                    grid[row][column] = Cell.RED_O; 
	                }
	            } else {
	                if (letter.equals("S")) {
	                    grid[row][column] = Cell.BLUE_S; 
	                } else {
	                    grid[row][column] = Cell.BLUE_O; 
	                }
	            }
	            gameMode.updateGameState(turn, row, column);
	            
	            turn = (turn == 'R') ? 'B' : 'R'; 
	            
	            
	            
	        }
	    }
		
		public abstract void updateGameState(char turn, int row, int column);
	}
	
	
	
	public class SimpleM extends GameMode{ 
		
		
		@Override
		public void updateGameState(char turn, int row, int column) { 
			if (isSOS(turn, row, column)) { 
				currentGameState = (turn == 'R') ? GameState.BLUE_WON : GameState.RED_WON;
			} else if (isDraw()) {
				currentGameState = GameState.DRAW;
			}
		}
		
	}
	
	
	public class GeneralM extends GameMode {
		private int ScoreR = 0;
		private int ScoreB = 0;
		
		public int getScoreR() {
	        return ScoreR;
	    }

	    public int getScoreB() {
	        return ScoreB;
	    }
	
		
	
		public void updateScoreR(int point){
			ScoreR += point;
		}
		
		public void updateScoreB(int point) {
			ScoreB += point;
		}
		
		
		public void updateScores(char turn, int row, int column) {
			if(isSOS(turn,row,column)) {
				if(turn == 'B') {
				 updateScoreR(1);
			
				}else if(turn == 'R') {
				 updateScoreB(1);
				 
				}
					
				 }
		}
	
		
		@Override
		public void updateGameState(char turn, int row, int column) {
						updateScores(turn,row,column);
						if(isDraw()) {
							if(ScoreR > ScoreB) {
								currentGameState = GameState.RED_WON;
							}else if(ScoreB > ScoreR) {
								currentGameState = GameState.BLUE_WON;
							}else if (ScoreR == ScoreB) {
								currentGameState = GameState.DRAW;
							}
						}
			
		}
	
	
	}
	
	public void setTurn(char newTurn) {
		this.turn = newTurn;
	}
	
	
	
	public void setGameMode(GameMode mode) {
	    this.gameMode = mode;
	}
	
	public GameMode getGameMode() { 
        return gameMode;
    }
	
	public Board(int TotalRows,int TotalColumns) {
		this.TotalRows = TotalRows;
		this.TotalColumns = TotalColumns;
		grid = new Cell[TotalRows][TotalColumns];
		initBoard();
	}

	public void initBoard() {
		for (int row = 0; row < TotalRows; row++) {
			for (int column = 0; column < TotalColumns; column++) {
				grid[row][column] = Cell.EMPTY;
			}
		}
		currentGameState = GameState.PLAYING;
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
	


	public char getTurn() {
		return turn;
	}

	
	private boolean isS(Cell cell) {
	    return cell == Cell.RED_S || cell == Cell.BLUE_S;
	}

	private boolean isO(Cell cell) {
	    return cell == Cell.RED_O || cell == Cell.BLUE_O;
	}
	
	public void setPlayer1CPU(boolean isCPU) {
		this.isPlayer1CPU = isCPU;
	}
	
	public void setPlayer2CPU(boolean isCPU) {
		this.isPlayer2CPU = isCPU;
	}
		
	public GameState getGameState() {
		return currentGameState;
	}

	}
