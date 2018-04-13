package pixelCombat.Math;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pixelCombat.view.gamephases.GamePlayView;



/**
 * 
 *  A simple rectangular bounding box
 *
 * 
 * @author BillyG
  * @version 0.1
 */
public class BoundingRectangle implements BoundingBoxInterface {

	private Vector2d upperLeft;
	private Vector2d lowerRight;
	
	private float width;
	private float height;
	private Vector2d pos;
	private boolean hurts = false;
	
	/**
	 * Constructor of BoundingRectangle
	 * 
	 * @param upperLeft position of upper left corner
	 * @param lowerRight position of lower right corner
	 */
	public BoundingRectangle(Vector2d upperLeft, Vector2d lowerRight) {
		this.upperLeft = upperLeft;
		this.lowerRight = lowerRight;
		this.pos = new Vector2d((upperLeft.x - lowerRight.x)/2f,(upperLeft.y - lowerRight.y)/2f);
		
		this.height	= height * GamePlayView.FIELD_SIZE;
		this.width	= width * GamePlayView.FIELD_SIZE; 
	}
	/**
	 * Constructor of BoundingRectangle
	 * 
	 * @param pos, the middle point of the bottom line
	 * @param height, height of the Rect
	 * @param width, width of the Rect
	 */
	public BoundingRectangle(Vector2d pos, float height, float width) {
		this.upperLeft = new Vector2d(pos.x-width/2f,pos.y-height);
		this.lowerRight = new Vector2d(pos.x+width/2f,pos.y);
		this.pos = new Vector2d((upperLeft.x - lowerRight.x)/2f,(upperLeft.y - lowerRight.y)/2f);
		
		this.height	= height * GamePlayView.FIELD_SIZE;
		this.width	= width * GamePlayView.FIELD_SIZE; 
	}
	
	/**
	 * Constructor of BoundingRectangle
	 * 
	 * @param height, height of the Rect
	 * @param pos, the middle point of the whole Rectangle Volume
	 * @param width, width of the Rect
	 */
	public BoundingRectangle( float height,Vector2d pos, float width) {
		this.upperLeft = new Vector2d(pos.x-width/2f,pos.y-height/2f);
		this.lowerRight = new Vector2d(pos.x+width/2f,pos.y+height/2f);
		this.pos = pos;
	
		this.height	= height * GamePlayView.FIELD_SIZE;
		this.width	= width * GamePlayView.FIELD_SIZE; 
	}
	
	
	
	public float getHeight(){
		return (lowerRight.y - upperLeft.y);
		
	}
	
	
	public Vector2d getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Vector2d upperLeft) {
		this.upperLeft = upperLeft;
	}

	public float getWidth(){
		return (lowerRight.x - upperLeft.x);
		
	}
	
	
	public Vector2d getLowerRight() {
		return lowerRight;
	}

	public void setLowerRight(Vector2d lowerRight) {
		this.lowerRight = lowerRight;
	}

	/**
	 * Returns whether or not the bounding box collides with the vector
	 * 
	 * @param v vector
	 * @return does collide
	 */

	public boolean isCollision(Vector2d v) {
		if (upperLeft.x <= v.x && upperLeft.y <= v.y && lowerRight.x >= v.x && lowerRight.y >= v.y) {
			return true;
		}
		return false;
	}

	public void draw(GraphicsContext g, float CX, float screenX) {
		float x1 = upperLeft.x 	* GamePlayView.FIELD_SIZE;
		float y1 = upperLeft.y 	* GamePlayView.FIELD_SIZE;
		float x2 = lowerRight.x * GamePlayView.FIELD_SIZE;
		float y2 = lowerRight.y * GamePlayView.FIELD_SIZE;
		
		g.setLineWidth(5);
		
		if(hurts)
			g.setStroke(Color.RED);
		else
			g.setStroke(Color.YELLOWGREEN);
		

		//horizontal
		g.strokeLine(x1 - screenX +CX,  y1, x1 + width - screenX +CX, y1);
		g.strokeLine(x1 - screenX +CX,  y2, x1 + width - screenX +CX, y2);
		//vertical
		g.strokeLine(x1 - screenX +CX,  y1, x1 - screenX +CX, y2);
		g.strokeLine(x2 - screenX +CX,  y1, x2 - screenX +CX, y2);

		
	}
	
	
	public Vector2d getPos() {
		return pos;
	}
	
	public void setPos(Vector2d pos) {
		this.pos = pos;
	}
	
	public void edit(float height, Vector2d point, float width) {
		this.pos.x = point.x;
		this.pos.y = point.y;
		
		this.upperLeft = new Vector2d(pos.x-width/2f,pos.y-height/2f);
		this.lowerRight = new Vector2d(pos.x+width/2f,pos.y+height/2f);

		
		this.height	= height * GamePlayView.FIELD_SIZE;
		this.width	= width * GamePlayView.FIELD_SIZE; 
		
	}
	public boolean getHurts() {
		return hurts;
	}
	public void setHurts(boolean hurts) {
		this.hurts = hurts;
	}
	
}
