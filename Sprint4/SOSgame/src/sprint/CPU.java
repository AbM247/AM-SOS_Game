package sprint;

import java.util.Random;




public class CPU extends Board {
	private char autoPlayer1;
	private char autoPlayer2;
	
	public CPU(int row, int column, char player) {
		super(row,column);
		if(GUI.getPlayer1()=="CPU1") {
			autoPlayer1 = 'R';
			if (autoPlayer1=='R') {
				makeFirstMove();
			}
		}else if(GUI.getPlayer2()=="CPU2") {
			autoPlayer2 = 'B';
		}
		
		
	}

	private void makeFirstMove() {
		makeAutoMove();
	}
	
	
	protected int[] makeAutoMove() {
		Random random = new Random();
	    String letter = random.nextBoolean() ? "S" : "O"; // Random choice between S and O
	    int numberOfEmptyCells = getNumberOfEmptyCells();
		int targetMove = random.nextInt(numberOfEmptyCells);
		int index=0;
		
	    for (int row = 0; row < TotalRows; ++row) {
			for (int col = 0; col < TotalColumns; ++col) {  
				if (grid[row][col] == Cell.EMPTY) {
					if (targetMove == index) {
						return new int[] {row,col,letter.equals("S")?0:1};
					}else {
						index++;
					}
				}
					
			}
	    }  
			
	  
	
	    
	  return new int[] {-1,-1,-1};
	}
	

	private int getNumberOfEmptyCells() {
		int numberOfEmptyCells = 0;
		for (int row = 0; row < TotalRows; ++row) {
			for (int col = 0; col < TotalColumns; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					numberOfEmptyCells++;
				}
			}
		}
		return numberOfEmptyCells;
	}

}
