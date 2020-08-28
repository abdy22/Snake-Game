package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/************************************************************************
* Author Name:        Abdirahman Hassan
* Description: Game window 
*              the class creates stage and scene the controller object is placed. 
*              The class initialize controller object contains a border pane that has at center snake game board and within it
*              the snake is moved and food is drawn and at bottom it has grid pane that contains game play descriptions, score card and options
*              board. 
*             
*************************************************************************/
public class GameWindow extends Application {
	private Point point;

	@Override
	public void start(Stage primary) throws Exception {
		point=new Point();
		Controller pane=new Controller(primary);
		
		pane.setStyle("-fx-background-color: black;");
		Scene scene=new Scene(pane,point.getWIDTH(),point.getHEIGHT(),Color.BLACK);
		primary.setTitle("SNAKE GAME");
		primary.setScene(scene);
		//primary.setResizable(false);
		primary.show();
		
		
	}
	public static void main(String[] args) {
		launch(args);
		
	}
	

}
