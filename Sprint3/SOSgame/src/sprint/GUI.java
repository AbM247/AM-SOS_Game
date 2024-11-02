package sprint;

import javafx.application.Application;
import javafx.stage.Stage;
import sprint.Board.GameState;
import javafx.scene.Scene;
import javafx.scene.control.Label; 
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
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
		
		String SR = "No";
	
		
		GridPane pane = new GridPane();
		
		TextField Bsize = new TextField();
		Bsize.setPromptText("Board Size");
		
		TextArea ScoreBoard = new TextArea();
		ScoreBoard.setText(SR);
		//ScoreBoard.setEditable(false); 
		
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
		
		//borderPane.setLeft(ScoreBoard);
		
		
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					int BoardSize = Integer.parseInt(Bsize.getText());
					
					if(BoardSize<3 || BoardSize > 20 ) {
						gameStatus.setText("Invalid board size. Board size must be greater than 2 or less than 20");
					} else {
						
						board = new Board(BoardSize,BoardSize);
						
						String mode = getMode();
						if(mode.equals("Simple")) {
							board.setGameMode(board.new SimpleM());
						}else {
							board.setGameMode(board.new GeneralM());
						}
						
						squares = new Square[board.getTotalRows()][board.getTotalColumns()];
						pane.getChildren().clear();
						
						for (int i = 0; i < board.getTotalRows(); i++)
							for (int j = 0; j < board.getTotalColumns(); j++)
								pane.add(squares[i][j] = new Square(i, j), j, i);
						
						borderPane.setCenter(pane);
						gameStatus.setText("Player"+board.getTurn()+"'s turn");
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
		for (int row = 0; row < board.getTotalRows(); row++) {
			for (int column = 0; column < board.getTotalColumns(); column++) {
				squares[row][column].getLetter().setText("");
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
		    String letter = " ";
		    Color color;
		    
		    if (board.getCell(row, column) == Board.Cell.EMPTY) {
		        if (board.getTurn() == 'R') {
		            letter = (Player1.getSelectedToggle() == Sbutton1) ? "S" : "O";
		            color = Color.RED;
		        } else {
		            letter = (Player2.getSelectedToggle() == Sbutton2) ? "S" : "O";
		            color = Color.BLUE;
		        }
		        
		   
		        Letter.setText(letter);
		        Letter.setTextFill(color);
		        board.getGameMode().makeMove(row, column, letter);
		        board.getGameMode().updateGameState(board.getTurn(), row, column); 

		        gameStatus.setText("Player " + board.getTurn() + "'s turn");
		        displayGameStatus();
		    }
		}
	
		
		public Label getLetter() {
			return Letter;
		}
		
		

		private void displayGameStatus() {
			
		 if (board.getGameState() == GameState.DRAW) {
			gameStatus.setText("It's a Draw! Click to play again.");
		} else if (board.getGameState() == GameState.RED_WON) {
			gameStatus.setText("'Red' Won! Click to play again.");
		} else if (board.getGameState() == GameState.BLUE_WON) {
			gameStatus.setText("'Blue' Won! Click to play again.");
		}
	}
		

	}

	public static void main(String[] args) {
		launch(args);
	}
}