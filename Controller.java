package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
/************************************************************************
* Author Name:        Abdirahman Hassan
* Description: Controller class 
*             The class can is used to control the snake game events. from keeping track if the game has ended or if the snake is moving 
*             or stationary. The class also animates the playing area with text, buttons. when the game ends a new stage is created if the 
*             user chooses to. 
*             
*************************************************************************/
public class Controller extends BorderPane {
	private Pane gameBoard;
	private FlowPane optionBoard, aboutGame;
	private VBox setUp;
	private Button newGame, howToGame;

	private Stage stage;// this is used to set up the stage for the new game 
	private Food food; // food object 
	private snake snake; // snake object 
	private Timeline animation; 
	private KeyFrame frame; // animation frame. plays like video 
	private Text score, info, header;
	private Point point;
	private GridPane grid;
	private Duration durate;
	/********************************************************************
	 * Constructor that takes as a stage. this is used to initialize the stage object. this is used to create new game when the game ends.
	 * Constructor initializes all data fields like game playing area using Pane, and option board that uses flow pane.
	 * creates a new snake object that is used to get snake properties like movements, collision and the elongation status. 
	 * The constructor also controls the game playing time  using the key frame and animation to display game as video that continuously plays 
	 * The duration is set to indefinite.  
	 * 
	 * 
	 * 
	 *********************************************/

	public Controller(Stage stage) {
		durate=new Duration(20);
		point = new Point();
		this.stage = stage;
		this.setPrefHeight(point.getHEIGHT());
		this.setPrefWidth(point.getWIDTH());
		gameDescribtion();// private method line 103
		gameButtons();// private method line 124
		setUpOptionBoard();// private method line 117
		gameBoardSetUp();
		score = new Text();
		score.setTextAlignment(TextAlignment.RIGHT);
		grid = new GridPane();
		GridPane.setConstraints(score, 0, 0);
		GridPane.setConstraints(optionBoard, 4, 0);
		GridPane.setConstraints(aboutGame, 1, 0);
		grid.setStyle("-fx-background-color:white");
		grid.setHgap(60);
		grid.setPrefHeight(this.getPrefHeight() / 5.5);
		grid.getChildren().addAll(score, optionBoard, aboutGame);
		this.setCenter(gameBoard);
		this.setBottom(grid);
		snake = new snake();
		snake.draw(gameBoard);
		food = new Food(snake);
		newGame.setOnAction(A -> {
			detector(A);
		});
		this.frame = new KeyFrame(Duration.millis(durate.toMillis()), e -> {
			animate();

		    });

		animation = new Timeline(frame);
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}
	/*****************************************
	 * The method is used set up what happens during the animation event in the key frame process
	 * it moves the snake using the snake movement 
	 * creates food and checks every time if the snake has eaten the snake to pick a new location for the food to drawn. 
	 *  
	 * it checks if the game has ended which prompts the user the score 
	 */

	public void animate() {
		snake.move(gameBoard);
		food.createFood(gameBoard);
		food.isFoodEaten();
		scoreCard();
		if(food.getScore()>=5) {
			durate=new Duration(durate.toMillis()-40);
		}
		gameOverAlert();

	}
	/********************************************
	 * The method creates the about game field to describe the game and how to play the game. 
	 * The description is used by using text object to write in the pane. 
	 * 
	 */

	private void gameDescribtion() {
		aboutGame = new FlowPane();
		aboutGame.setPrefWidth(20);
		aboutGame.setAlignment(Pos.TOP_LEFT);
		aboutGame.setStyle("-fx-background-color:white");
		aboutGame.setOrientation(Orientation.VERTICAL);
		header = new Text();
		info = new Text();
		header.setText("About Snake game");
		header.setFont(Font.font("charcoal", FontWeight.BOLD, 20));
		info.setWrappingWidth(aboutGame.getBaselineOffset());
		info.setText("To play the game, use Arrows Keys" + "\n" + " to control the  snake movement.Game" + "\n"
				+ " ends if it hits the Wall or collides with" + "\n" + " itself. ");
		header.setFill(Color.BLACK);
		info.setTextAlignment(TextAlignment.LEFT);
		;
		info.setTextOrigin(VPos.BASELINE);
		info.setFill(Color.BLACK);
		aboutGame.getChildren().addAll(header, info);

	}
	/***********************************************************
	 * the method sets up the snake playing area using the Pane 
	 * 
	 */
	private void gameBoardSetUp() {
		gameBoard = new Pane();// playing area for the snake
		gameBoard.setPrefWidth(this.getPrefWidth());
		;
		gameBoard.setPrefHeight(this.getPrefHeight() / 1.5);// limits the height to 400 
		gameBoard.requestFocus();
		
	}
	/*******************************
	 * the method sets up the vertical box that contains buttons lets the user choose a new game
	 * it adds the the set up into a option board of type flow pane. 
	 * 
	 */

	private void setUpOptionBoard() {
		setUp = new VBox();
		setUp.getChildren().addAll(newGame,howToGame);
		optionBoard = new FlowPane();
		optionBoard.setVgap(20);
		;
		optionBoard.setAlignment(setUp.getAlignment());
		optionBoard.setStyle("-fx-background-color:white");
		optionBoard.getChildren().addAll(setUp);
		optionBoard.setAlignment(Pos.BOTTOM_RIGHT);
	}
	/************************************************
	 * the method sets up the game buttons such as new game button and how to play the game button
	 */

	private void gameButtons() {
		newGame = new Button("New Game");
		newGame.setPrefWidth(100);
		howToGame = new Button("how to play");
		howToGame.setStyle("-fx-background-color:red");
		newGame.setStyle("-fx-background-color:green");
		howToGame.setFocusTraversable(false);
		newGame.setFocusTraversable(false);

	}
	/*****************************************
	 * the method detects to the user actions of which button they chose.  
	 * the action are set up to new game button 
	 * if the user presses the new game button a the old stage is closed and a new scene is created. 
	 * a new constructor is called to sets a new game and the stage that has the new scene
	 *   
	 * 
	 * 
	 */

	public void detector(ActionEvent event) {
		if (event.getSource() == newGame) {
			Point point = new Point();
			stage.close();
			Controller pane = new Controller(stage);

			pane.setStyle("-fx-background-color: black;");
			Scene scene = new Scene(pane, point.getWIDTH(), point.getHEIGHT(), Color.BLACK);
			stage.setTitle("SNAKE GAME");
			stage.setScene(scene);
			stage.show();
		}
	}
	/************************************8
	 * the method alerts the user if the game ends 
	 * the alerts creates dialog that tells the user the game ended and tells the user to start a new game. 
	 */

	public void gameOverAlert() {
		if (snake.isGameOver() == true) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.DECORATED);
			alert.setTitle("Game Over!");
			alert.setHeaderText("Score: " + food.getScore());
			alert.setContentText("Press New game to start new Game");
			animation.pause();
			alert.show();
		}
	}
	/****************************
	 * The method displays the score of the user in the game to the screen 
	 */

	public void scoreCard() {
		score.setText("Score: " + food.getScore());
		score.setFont(Font.font("charcoal", FontWeight.BOLD, 25));
		score.setFill(Color.RED);
	}
}
