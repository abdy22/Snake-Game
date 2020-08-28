package application;
/************************************************************************
* Author Name:        Abdirahman Hassan
* Description: Enum class 
*              The class sets up the direction in the X and Y plane 
*              If the user chooses right the y is 0 and x is 1 
*              if the user chooses left the y is 0 and x is -1 
*              if the user chooses down the y is 1(  goes to the positive direction in the screen) and x is 0 
*              if the user chooses up( going to the negative direction in the screen) the y is -1 and x is 0 
*                
*           
*             
*             
************************************************************************/
public enum Direction {
	
	UP(0, -1), //x=0, y=-1 
	DOWN(0, 1), 
	LEFT(-1, 0),
	RIGHT(1, 0);
	private int x, y; //data fields 
    /**************************************
     * private constructor that is used to initialize the constant fields 
     * 
     */
	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
