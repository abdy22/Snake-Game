package application;
/************************************************************************
 * Author Name:        Abdirahman Hassan
 * Description: Point class 
 *             the class can be used to  sets x and y coordinates 
 *             and also getting the location of the point 
 *             
 *             
 ************************************************************************/


public class Point {
	private int x,y; //data fields 
	private final  int WIDTH=620;
	private final int HEIGHT=600;
	/**************************
	 * Constructs and initializes a point at the origin (0, 0) of the coordinate space.
	 *
	 *********************************************/
	Point(){
		this(0,0);
	}
	/***********************************
	 * 
	 * Constructs and initializes a point at the specified (x,y) location in the coordinate space.
	 * 
	 */
	Point(int x,int y){
		setX(x);
		setY(y);
		
	}
	/**
	 * @return the hEIGHT
	 */
	public int getHEIGHT() {
		return HEIGHT;
	}
	/**
	 * @return the wIDTH
	 */
	public int getWIDTH() {
		return WIDTH;
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
	/***********************************
	 * 
	 * sets the location for the X point 
	 */
	public void setX(int x) {
		this.x=x;
	}
	/**************************
	 * 
	 * sets the location for the Y point 
	 */
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y=y;
		
	}

}
