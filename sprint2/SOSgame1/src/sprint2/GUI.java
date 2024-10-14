package sprint2;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.text.*;
import javafx.scene.layout.VBox;


public class GUI extends Application {

	private Square[][] squares;

	private Label gameStatus = new Label("Choose the game settings");

	private Board board;
	
	public static ToggleGroup Mode; 
	private ToggleGroup Player1; 
	private ToggleGroup Player2; 
	public static RadioButton Smode = new RadioButton("Simple");
    public static RadioButton Gmode = new RadioButton("General");
	
	
	RadioButton Sbutton1 = new RadioButton("S");
	RadioButton Obutton1 = new RadioButton("O");
	
	RadioButton Sbutton2 = new RadioButton("S");
	RadioButton Obutton2 = new RadioButton("O");
	
	
	
	

	@Override
	public void start(Stage primaryStage) {
		
		
		
		GridPane pane = new GridPane();
		
		//New code
		TextField Bsize = new TextField();
		Bsize.setPromptText("Board Size");
		
		
		Mode = new ToggleGroup();
		Player1 = new ToggleGroup();
		Player2 = new ToggleGroup();
		
		
		
		
		Smode.setToggleGroup(Mode);
		Gmode.setToggleGroup(Mode);
		Mode.selectToggle(Smode);
		
		Sbutton1.setToggleGroup(Player1);
		Obutton1.setToggleGroup(Player1);
		Player1.selectToggle(Sbutton1);
		
		Sbutton2.setToggleGroup(Player2);
		Obutton2.setToggleGroup(Player2);
		Player2.selectToggle(Sbutton2);
		
		
		
		Button submit = new Button("Sumbit");
		
		VBox input = new VBox(5,Bsize,submit,Smode,Gmode);
		input.setSpacing(5);
		
		VBox Player1Side = new VBox(5,Sbutton1,Obutton1);
		VBox Player2Side = new VBox(5,Sbutton2,Obutton2);


		BorderPane borderPane = new BorderPane();
		borderPane.setBottom(input);
		borderPane.setTop(gameStatus);
		borderPane.setLeft(Player1Side);
		borderPane.setRight(Player2Side);
		
		
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					int BoardSize = Integer.parseInt(Bsize.getText());
					
					if(BoardSize<3 || BoardSize > 20 ) {
						gameStatus.setText("Invalid board size. Board size must be greater than 2 or less than 20");
					} else {
						
						
						
						getMode();
						board = new Board(BoardSize,BoardSize);
						squares = new Square[board.getTotalRows()][board.getTotalColumns()];
						pane.getChildren().clear();
						
						for (int i = 0; i < board.getTotalRows(); i++)
							for (int j = 0; j < board.getTotalColumns(); j++)
								pane.add(squares[i][j] = new Square(i, j), j, i);
						
						borderPane.setCenter(pane);
						gameStatus.setText("Player 1's Turn");
						drawBoard();
					}
										
				} 

				catch(NumberFormatException d) {
					gameStatus.setText("Invalid board size. Enter a valid integer");
				}
			}
		};
		
		submit.setOnAction(event);
		
	
		Scene scene = new Scene(borderPane, 900, 600);
		primaryStage.setTitle("SOS Game");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
	}
	
	public static  String getMode() {
		RadioButton ModeSelected = (RadioButton) Mode.getSelectedToggle();
		return ModeSelected.getText();
	}
	
	
	
	
	public void drawBoard() {
		RadioButton selectedMove1 = (RadioButton) Player1.getSelectedToggle();
		RadioButton selectedMove2 = (RadioButton) Player2.getSelectedToggle();
		for (int row = 0; row < board.getTotalRows(); row++) {
			for (int column = 0; column < board.getTotalColumns(); column++) {
				squares[row][column].getLabel().setText("");
				
				if (board.getCell(row, column) == Board.Cell.RED) {
					
					if(board.getPlayerCell(row,column)=='R'&& selectedMove1.getText().equals("S")) {
						squares[row][column].getLabel().setText( "S"); 
	                    squares[row][column].getLabel().setTextFill(Color.RED); 
						
					}if(board.getPlayerCell(row,column)=='R'&& selectedMove1.getText().equals("O")) {
						squares[row][column].getLabel().setText("O"); 
	                    squares[row][column].getLabel().setTextFill(Color.RED); 
					}
					
				}else if (board.getCell(row, column) == Board.Cell.BLUE )
					
					if (board.getPlayerCell(row, column) != 'R' &&selectedMove2.getText().equals("S")) {
	                    squares[row][column].getLabel().setText("S"); 
	                    squares[row][column].getLabel().setTextFill(Color.BLUE); 
	                } if (board.getPlayerCell(row, column) != 'R' && selectedMove2.getText().equals("O")){
	                    squares[row][column].getLabel().setText("O"); 
	                    squares[row][column].getLabel().setTextFill(Color.BLUE); 
	                }
	}
	}
	}
	
	

	public class Square extends Pane {

		private int row, column;
		private Label Letter;
		 
		
		public Square(int row, int column) {
			this.row = row;
			this.column = column;
			Letter = new Label();
			Letter.setStyle("-fx-font-size: 60; -fx-alignment: center;");
			setStyle("-fx-border-color: black");
			this.setPrefSize(200, 200);
			this.getChildren().add(Letter);
			this.setOnMouseClicked(e -> handleMouseClick());
		}

		private void handleMouseClick() {
			
			if (board.getCell(row,column)==Board.Cell.EMPTY) {
				board.makeMove(row, column);
				RadioButton selectedMove1 = (RadioButton) Player1.getSelectedToggle();
				RadioButton selectedMove2 = (RadioButton) Player2.getSelectedToggle();
				
				
				if(board.getTurn()=='R') {
					
					if(selectedMove1.getText().equals("S")) {
						Letter.setText("S");
						Letter.setTextFill(Color.RED);
						
					}else if(selectedMove1.getText().equals("O")) {
						Letter.setText("O");
						Letter.setTextFill(Color.RED);
					}
				}else {
					if(selectedMove2.getText().equals("S")) {
						Letter.setText("S");
						Letter.setTextFill(Color.BLUE);
					}else if(selectedMove2.getText().equals("O")) {
						Letter.setText("O");
						Letter.setTextFill(Color.BLUE);
					}
				
				}
			
				
					
					
			}//
			
			drawBoard();
			displayGameStatus();
		}
		
		public Label getLabel() {
			return Letter;
		}
		
		
		
		
	
		
		

		private void displayGameStatus() {
			if (board.getTurn() == 'R') {
				gameStatus.setText("Player 1's Turn");
			} else {
				gameStatus.setText("Player 2's  Turn");
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}