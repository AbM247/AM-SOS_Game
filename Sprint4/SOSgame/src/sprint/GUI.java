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
import javafx.application.Platform;



public class GUI extends Application {
	
	private CPU cpu1;
	private CPU cpu2;

	private Square[][] squares;

	private Label gameStatus = new Label("Choose the game settings");

	static private Board game;
	
	public static ToggleGroup Mode; 
	public static ToggleGroup Player1Type;
	public static ToggleGroup Player2Type;
	private ToggleGroup Player1; 
	private ToggleGroup Player2; 
	
	public static RadioButton Smode = new RadioButton("Simple");
    public static RadioButton Gmode = new RadioButton("General");
    
    public static RadioButton PlayerP1 = new RadioButton("P1");
    public static RadioButton PlayerCPU1 = new RadioButton("CPU1");
    public static RadioButton PlayerP2 = new RadioButton("P2");
    public static RadioButton PlayerCPU2 = new RadioButton("CPU2");
	
	
	RadioButton Sbutton1 = new RadioButton("S");
	RadioButton Obutton1 = new RadioButton("O");
	
	RadioButton Sbutton2 = new RadioButton("S");
	RadioButton Obutton2 = new RadioButton("O");
	

	@Override
	public void start(Stage primaryStage) {
		
	
		
		GridPane pane = new GridPane();
		
		TextField Bsize = new TextField();
		Bsize.setPromptText("Board Size");
		
		
		Mode = new ToggleGroup();
		Player1 = new ToggleGroup();
		Player2 = new ToggleGroup();
		Player1Type = new ToggleGroup();
		Player2Type = new ToggleGroup();
		
		Smode.setToggleGroup(Mode);
		Gmode.setToggleGroup(Mode);
		Mode.selectToggle(Smode);
		
		Sbutton1.setToggleGroup(Player1);
		Obutton1.setToggleGroup(Player1);
		PlayerP1.setToggleGroup(Player1Type);
		PlayerCPU1.setToggleGroup(Player1Type);
		Player1.selectToggle(Sbutton1);
		Player1Type.selectToggle(PlayerP1);
		
		
		Sbutton2.setToggleGroup(Player2);
		Obutton2.setToggleGroup(Player2);
		PlayerP2.setToggleGroup(Player2Type);
		PlayerCPU2.setToggleGroup(Player2Type);
		Player2.selectToggle(Sbutton2);
		Player2Type.selectToggle(PlayerP2);

		
		
		
		Button submit = new Button("Sumbit");
		
		VBox input = new VBox(5,Bsize,submit,Smode,Gmode);
		input.setSpacing(5);
		
		VBox Player1Side = new VBox(5,Sbutton1,Obutton1,PlayerP1,PlayerCPU1);
		VBox Player2Side = new VBox(5,Sbutton2,Obutton2,PlayerP2,PlayerCPU2);


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
						gameStatus.setText("Invalid game size. Board size must be greater than 2 or less than 20");
					} else {
						
						game = new Board(BoardSize,BoardSize);

						String mode = getMode();
						if(mode.equals("Simple")) {
							game.setGameMode(game.new SimpleM());
						}else {
							game.setGameMode(game.new GeneralM());
						}
						
						if (GUI.getPlayer1() == "CPU1") {
	                        cpu1 = new CPU(BoardSize, BoardSize, 'R');
	                    }
	                    if (GUI.getPlayer2() == "CPU2") {
	                        cpu2 = new CPU(BoardSize, BoardSize, 'B');
	                    }
						
						
						squares = new Square[game.getTotalRows()][game.getTotalColumns()];
						pane.getChildren().clear();
						
						for (int i = 0; i < game.getTotalRows(); i++)
							for (int j = 0; j < game.getTotalColumns(); j++)
								pane.add(squares[i][j] = new Square(i, j), j, i);
						
						borderPane.setCenter(pane);
						
						
						
						gameStatus.setText("Player "+ game.getTurn() +"'s turn");
						
						drawBoard();
						}
										
				} 

				catch(NumberFormatException d) {
					gameStatus.setText("Invalid game size. Enter a valid integer");
				}
			}
		};
		
		submit.setOnAction(event);
		
	
		Scene scene = new Scene(borderPane, 900, 600);
		primaryStage.setTitle("SOS Game");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
		
	}
	
	 
	
	public static String getMode() {
		RadioButton ModeSelected = (RadioButton) Mode.getSelectedToggle();
		return ModeSelected.getText();
	}
	
	public static String getPlayer1() {
		RadioButton Player1Selected = (RadioButton) Player1Type.getSelectedToggle();
		return Player1Selected.getText();
	}
	
	public static String getPlayer2() {
		RadioButton Player2Selected = (RadioButton) Player2Type.getSelectedToggle();
		return Player2Selected.getText();
	}
	
	
	
	public void drawBoard() {
		for (int row = 0; row < game.getTotalRows(); row++) {
			for (int column = 0; column < game.getTotalColumns(); column++) {
				squares[row][column].getLetter().setText("");
				
				
				if(getPlayer1()=="CPU1") {
				game.gameMode.makeMove(0, 0, "S");
				squares[0][0].getLetter().setText("S");
				squares[0][0].getLetter().setTextFill(Color.RED);
				game.setTurn('B');
				}
				
				if(getPlayer1()=="CPU1"&&getPlayer2()=="CPU2") {
					game.gameMode.makeMove(0, 0, "S");
					squares[0][0].getLetter().setText("S");
					squares[0][0].getLetter().setTextFill(Color.RED);
					game.setTurn('B');
					squares[row][column].makeCPUMove();
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
			if(game.getGameState() == GameState.PLAYING) {
			if(getPlayer2() =="CPU2"&&getPlayer1()=="P1") {
			game.setTurn('R');
			}
			if(getPlayer1()=="CPU1"&&getPlayer2()=="P2") {
				game.setTurn('B');
			}
		    String letter = " ";
		    Color color = Color.RED;		    
		    if (game.getCell(row, column) == Board.Cell.EMPTY) {
		        if (game.getTurn() == 'R'&& getPlayer1() == "P1") {
		            letter = (Player1.getSelectedToggle() == Sbutton1) ? "S" : "O";
		            color = Color.RED;
		        } else if(game.getTurn() == 'B'&& getPlayer2() == "P2")  {
		            letter = (Player2.getSelectedToggle() == Sbutton2) ? "S" : "O";
		            color = Color.BLUE;
		        }
		        
		       
		   
		        Letter.setText(letter);
		        Letter.setTextFill(color);
		        game.getGameMode().makeMove(row, column, letter);
		        game.getGameMode().updateGameState(game.getTurn(), row, column); 
		       
		      

		        gameStatus.setText("Player " + game.getTurn() + "'s turn");
		        makeCPUMove();
		        displayGameStatus();
		        
		  
		    }
			}
		}
		
		private void makeCPUMove() {
			 if (game.getTurn() == 'R' && getPlayer1().equals("CPU1")) {
			        Platform.runLater(() -> CPUMove(cpu1, Color.RED));
			    } else if (game.getTurn() == 'B' && getPlayer2().equals("CPU2")) {
			        Platform.runLater(() -> CPUMove(cpu2, Color.BLUE));
			    }
		}
		
		private void CPUMove(CPU cpuPlayer, Color color) {
		
			int[] move = cpuPlayer.makeAutoMove();
		    
		    int row = move[0];
		    int column = move[1];
		    String letter = (move[2]==0)?"S":"O";
		    
		    

		    squares[row][column].getLetter().setText(letter);
		    squares[row][column].getLetter().setTextFill(color);
		    
		    game.gameMode.makeMove(row, column, letter);

		    game.getGameMode().updateGameState(game.getTurn(), row, column);
		    displayGameStatus();
		    
		    game.setTurn((game.getTurn()=='R')?'B':'R');
		    
		    gameStatus.setText("Player " + game.getTurn() + "'s turn");

		}
		
		
	
		
		public Label getLetter() {
			return Letter;
		}
		

		private void displayGameStatus() {
			
		 if (game.getGameState() == GameState.DRAW) {
			gameStatus.setText("It's a Draw! Click to play again.");
		 } else if (game.getGameState() == GameState.RED_WON) {
			gameStatus.setText("'Red' Won! Click to play again.");
		 } else if (game.getGameState() == GameState.BLUE_WON) {
			gameStatus.setText("'Blue' Won! Click to play again.");
		   }
		}
		

	}

	public static void main(String[] args) {
		launch(args);
	}
}