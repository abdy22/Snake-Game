package application;

import java.util.Random;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/************************************************************************
* Author Name:        Abdirahman Hassan
* Description: Food class 
*             the class extends the Point class to set x and y location of the food 
*             the food's location is randomly chosen within the specified pane when the snake 
*             eats the food. The class also keeps track of the number of hits by the snake or the 
*             number of food eaten during the game period. 
*           
*             
*             
************************************************************************/

public class Food extends Point{
	Pane pane;// this is used for contrainig the food withing the pane it is on. 
	private int score;
	snake snake;
	private Rectangle rect;
	private Color    foodColor;
	/*******************************************************
	 * Constructor that takes as an argument snake object and uses to initialize snake object 
	 * the constructor also picks random location 
	 * sets the color of the food to lawgreen
	 * Initializes the score to zero 
	 * 
	 */
	public Food(snake snake) {
		this.snake=snake;
		pane=snake.getPane();
		this.snake=snake;
		score=0;
		foodColor=Color.LAWNGREEN;
		setX(300);
		setY(100);
		
	}
	/**************************************
	 * the method of the keeps track if the snake has eaten the food 
	 * if the snake head is within the food bounds it counts it as food eaten 
	 * food location is updated with new x and y location 
	 * the score is added to one 
	 * snake is elongated 
	 * 
	 */
	public void isFoodEaten() {
		if(snake.getHead().intersects(rect.getLayoutBounds())){
			pickLocation();
			snake.setElongate(true);
			score++;
			
		}
		
		
	}
	/***************************************************
	 * creates the food using the pane passed
	 * The food is a rectangle that is 8 by 8
	 * the old food that is eaten is removed from the pane 
	 * the new food with different location 
	 * 
	 *************************************************/
	public void createFood(Pane pane) {
		this.pane=pane;
		
		pane.getChildren().remove(rect);
		rect=new Rectangle(getX(),getY(),8,8);
		rect.setFill(foodColor);
		pane.getChildren().add(rect);
	}
	/******************
	 * 
	 * @return score which is the number of times the food is eaten 
	 ****************/
	public int getScore() {
		return score;
	}
	/*******************************
	 * picks a new location using random generator and it's restricted within the playing area 
	 **************************/
	public void pickLocation() {
		Random rand=new Random();
		int height=(int) (snake.getPane().getHeight())/10;
		int width=(int) snake.getPane().getWidth()/10;
		int Y=rand.nextInt(height)*10;
		int X=rand.nextInt(width)*10;
		setX(X);
        setY(Y);
	}

}
