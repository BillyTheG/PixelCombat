package pixelCombat.Math;



/**
 * 
 *  A simple circular bounding box
 *
 * 
 * @author BillyG
  * @version 0.1
 */
public class BoundingCircle implements BoundingBoxInterface {

	private Vector2d center;
	private float radius;

	/**
	 * Constructor of BoundingCircle
	 * 
	 * @param center center
	 * @param radius radius
	 */
	public BoundingCircle(Vector2d center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	/**
	 * Returns whether or not the bounding box collides with the vector
	 * 
	 * @param v vector
	 * @return does collide
	 */

	public boolean isCollision(Vector2d v) {
		return center.sub(v).length() <= radius;
	}

	/**
	 * Returns the radius of the circle
	 * 
	 * @return radius
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * Returns the center position of the circle
	 * 
	 * @return center position
	 */
	public Vector2d getCenter() {
		return center;
	}
}
