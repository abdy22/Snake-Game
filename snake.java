package application;

import java.util.ArrayList;

import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/************************************************************************
 * Author Name:        Abdirahman Hassan
 * Description: Snake class 
 *              The class sets up the size of the snake to 20. The size grows when the snake eats food 
 *              The class can move the snake and sets the direction the user wants the snake to go
 *              The snake stops moving when it hits the wall or it hits its body 
 *              The uses array list of type rectangle   
 *             
 *             
 ************************************************************************/
public class snake extends Point {
	Pane pane;
	Label Score;
	private ArrayList<Rectangle> snakeBody;
	private Direction direction;
	private int starting_Size;
	private boolean isMoving, elongate, isGameOver;
	private Rectangle head;
	private Rectangle tail;
	private static final double speed=4;
	/***********************************************
	 * Constructor 
	 * initializes the size of the snake to 20 
	 * sets the Direction it should be moving when it start moving 
	 * the snake initially starts at (200,200) on the playing area ;
	 * 
	 ******************/

	public snake() {
		snakeBody = new ArrayList<Rectangle>();
		starting_Size=20;
		setDir(Direction.RIGHT);
		setX(200);
		setY(200);

		direction = Direction.RIGHT;
		isMoving = false;
		elongate = false;

	}
	/*************************************************
	 * private method to check if the snake hit the walls of the playing area 
	 * if hits the walls the game is over 
	 */

	private void collideWithWall() {
		Bounds bound = pane.getLayoutBounds();
		if (head.getX() >= bound.getMaxX() || head.getY() >= bound.getMaxY() || head.getX() <= bound.getMinX()
				|| head.getY() <= bound.getMinY()) {
			isGameOver = true;
			
		}

	}
	/**********************************
	 * Draws the snake on the playing area with  starting body size of 20 rectangles 
	 * snake body is rectangles that 6 by 6 pixels 
	 * snake is initially drawn on (200,200) point in the playing and the rest of the body is 6 pixels minus the next body 
	 * snake head is set to the zero element of the array list
	 * the head is focus traversable and listens to key event passed   
	 * the tail is the last element in the array list(snake body). 
	 ***************************************************************************/

	public void draw(Pane pane) {
		this.pane = pane;

		for (int i = 0; i < starting_Size; i++) {
			snakeBody.add(new Rectangle(getX() - 6 * i, getY(), 6, 6));
			snakeBody.get(i).setStroke(Color.BLUE);
			snakeBody.get(i).setFill(Color.BLUE);
		}
		this.pane.getChildren().addAll(snakeBody);

		head = snakeBody.get(0);
		head.setStroke(Color.CORAL);
		head.setFill(Color.CORAL);
		head.setFocusTraversable(true);
		head.setOnKeyPressed(e -> {
			update(e);
		});
		this.tail = snakeBody.get(snakeBody.size() - 1);

	}

	/**
	 * @return the Direction of the snake
	 */
	public Direction getDir() {
		return direction;
	}

	/**
	 * @return the head of the snake this is useful when when locating the snake
	 *         head location
	 */
	public Rectangle getHead() {
		return head;
	}
    /****************************************
     * 
     * @return the pane in which the snake is playing 
     */
	public Pane getPane() {
		return pane;

	}

	/**
	 * @return the tail of the snake 
	 */
	public Rectangle getTail() {
		return tail;
	}
   /****************************************************
    * if the snake eats food, the snake body is elongated with one rectangle 
    * the new rectangle is added to tail's minus the size of the rectangle=6 pixels by 6 pixels 
    * the body is added into the playing are so that it should follow the the rest of the body  
    * size of the body is updated with + 1. 
    * elongation is set to false so that it should not keep elongating the snake 
    * The tail of the snake is made the new body added 
    *  
    */
	public void ifElongate() {
		if (isElongate() == true) {
			Rectangle temp = new Rectangle(tail.getX(), tail.getY(), 6, 6);
			temp.setStroke(Color.BLUE);
			temp.setFill(Color.BLUE);
			snakeBody.add(temp);
			pane.getChildren().add(temp);
			starting_Size++;
		}
		setElongate(false);
		this.tail = snakeBody.get(snakeBody.size() - 1);
	}
    /*******************************************
     * private method that checks if the snake head hits any part of the body   
     * if the head has the same coordinates in any part of its body counted as hitting it self and the game ends 
     *   
     */
	private void isCollideSelf() {
		double x = head.getX();
		double Y = head.getY();
		for (int i = 1; i < snakeBody.size() - 1; i++) {
			if (snakeBody.get(i).getX() == x && snakeBody.get(i).getY() == Y) {
				isGameOver = true;
				
			}

		}
	}

	/**
	 * @return the elongation status 
	 */
	public boolean isElongate() {
		return elongate;
	}

	/**
	 * @return the isGameOver to be true or false 
	 */
	public boolean isGameOver() {
		return isGameOver;
	}

	/**
	 * @return the isMoving 
	 * false or true
	 */
	public boolean isMoving() {
		return isMoving;
	}
    /********************************************************************
     * snake movement 
     * it only moves if the movement is true( cases when the user may pause the game movement is stopped) 
     * and if the game is not over 
     * Before moving collision with body and walls are checked this is to check if the game has not ended due to the rules 
     * if game is not over, the next checking is if the snake if elongated and another body is added.This is to get the right size of the body snake 
     * so that within the move method every part of the snake body is moved. 
     * movement is achieved by the setting the X and Y of the last element index=size-1 to the X and Y of the one before it index=size-2 
     * the movement is index=n == index=n-1. The process is continued all the to n>0 
     * then Zero element which is the head is moved the direction its facing  
     * 
     */
	public void move(Pane pane) {
		if (isMoving == true && isGameOver() == false) {
			isCollideSelf();
			collideWithWall();

			ifElongate();
			for (int i = starting_Size - 1; i > 0; i--) {
				snakeBody.get(i).setX(snakeBody.get(i - 1).getX());

				snakeBody.get(i).setY(snakeBody.get(i - 1).getY());
			}

			head.setX(snakeBody.get(0).getX() + speed*getDir().getX());

			head.setY(snakeBody.get(0).getY() + speed*getDir().getY());
		}
	}

	/**
	 * @param set direction of the snake 
	 * Direction UP, Down ,Right, Left 
	 */
	public void setDir(Direction xyPlane) {
		this.direction = xyPlane;
	}

	/**************
	 * set elongate state 
	 */
	public void setElongate(boolean value) {
		this.elongate = value;
	}

	/**
	 * @param sets movement to false if not moving or true is moving 
	 */
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
    /****************************
     *  starts the movement of the snake 
     */
	private  void start() {
		setMoving(true);

	}
	/*********************************************************************************
	 * Key pressed  event
	 * The method listens to the user key pressed event. 
	 * The keys that moved the snake are the arrows and the space key is used to start or pause the snake game 
	 * the snake can move up/ down if the snake is either going left or right direction. and the snake can go right or left when it either going 
	 * up or down.this is to prevent the snake going the opposite direction and hitting it self. 
	 *      
	 * 
	 */

	public void update(KeyEvent event) {
		if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.RIGHT
				|| event.getCode() == KeyCode.LEFT) {
			start();
		}
		if (event.getCode() == KeyCode.UP) {
			if (getDir() == Direction.RIGHT || getDir() == Direction.LEFT) {
				setDir(Direction.UP);
			}

		} else if (event.getCode() == KeyCode.DOWN) {
			if (getDir() == Direction.RIGHT || getDir() == Direction.LEFT) {
				setDir(Direction.DOWN);
			}

		} else if (event.getCode() == KeyCode.RIGHT) {
			if (getDir() == Direction.DOWN || getDir() == Direction.UP) {
				setDir(Direction.RIGHT);
			}

		} else if (event.getCode() == KeyCode.LEFT) {

			if (getDir() == Direction.DOWN || getDir() == Direction.UP) {
				setDir(Direction.LEFT);

			}
		} else if (event.getCode() == KeyCode.SPACE) {
			if (isMoving == true) {
				setMoving(false);
			} else {
				start();
			}
		}
	}

}
