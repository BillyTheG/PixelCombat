package pixelCombat.Math;

/**
 * 
 * Creates matrices for standard transformation calculations (scale, rotate and translate) and performs collision
 * detection
 * 
 * @author BillyG
  * @version 0.1
 */
public class GeometryUtils {
    	
    /**
     * 
     * @param point, an arbitrary position vector for the support of linear graph
     * @param linestart, the start vector for the direction vector
     * @param lineend, the end vector for the direction vector
     * @return a linear graph vector, which has the given parameter as points
     */
	public static Vector2d PointLineDist(Vector2d point, Vector2d linestart, Vector2d lineend)
	{
	      Vector2d a = lineend.sub(linestart);
	      Vector2d b = point.sub(linestart);
	      float t = (a.x *b.x + a.y*b.y)/((a.length()) * (a.length()) );
	      if (t < 0) t = 0;
	      if (t > 1) t = 1;
	      return linestart.add(a.mult(t));
	}
	
	
	
	/**
	 * Calculates the angle between two Vector2d
	 * 
	 * @param v1 Vector2d v1
	 * @param v2 Vector2d v2
	 * @return angle between v1 and v2
	 */
	public static float angle(Vector2d v1, Vector2d v2) {
		float pp = v1.pointproduct(v2);
		return (float) Math.toDegrees(Math.acos(pp));
	}

	/**
	 * Checks if two bounding boxes overlap
	 * 
	 * @param b1 bounding box b1
	 * @param b2 bounding box b2
	 * @return do boxes collide
	 */
	public static boolean isCollision(BoundingBoxInterface b1, BoundingBoxInterface b2) {
		if (b1 instanceof BoundingCircle) {
			if (b2 instanceof BoundingCircle) {
				return isCollision((BoundingCircle) b1, (BoundingCircle) b2);
			}
		}
		if (b1 instanceof BoundingRectangle) {
			if (b2 instanceof BoundingRectangle) {
				return isCollision((BoundingRectangle) b1, (BoundingRectangle) b2);
			}
		}
		if (b1 instanceof BoundingCircle) {
			if (b2 instanceof BoundingRectangle) {
				return isCollision((BoundingCircle) b1, (BoundingRectangle) b2);
			}
		}
		if (b1 instanceof BoundingRectangle) {
			if (b2 instanceof BoundingCircle) {
				return isCollision((BoundingCircle) b2, (BoundingRectangle) b1);
			}
		}
		throw new RuntimeException("BoundingGeometry type cannot be recognized!");
	}

	/**
	 * Checks if two rectangles overlap
	 * 
	 * @param r1 rectangle r1
	 * @param r2 rectangle r2
	 * @return do rectangles collide
	 */
	public static boolean isCollision(BoundingRectangle r1, BoundingRectangle r2) {
		 return r1.getUpperLeft().x < r2.getUpperLeft().x + r2.getWidth()  &&
				 r2.getUpperLeft().x < r1.getUpperLeft().x + r1.getWidth()  &&
				 r1.getUpperLeft().y < r2.getUpperLeft().y  + r2.getHeight() &&
				 r2.getUpperLeft().y < r1.getUpperLeft().y + r1.getHeight();
	}
	
	
	
	/**
	 * 
	 * @param c1 , Circle1
	 * @param c2, Circle2
	 * @return collsion result
	 */
	public static boolean isCollision(BoundingCircle c1, BoundingCircle c2) {
		return c1.getCenter().distance(c2.getCenter()) <= c1.getRadius() + c2.getRadius();
	}
	/**
	 * 
	 * @param c1 Circle
	 * @param c2 Rectangle
	 * @return collision result
	 */
	public static boolean isCollision(BoundingCircle c1, BoundingRectangle c2) {
		
	     float minDistSq = 100000000000000000000000000000000000000f;
	      // Seiten durchgehen, Schleife kann (bzw muss, je nachdem wie Rect aussieht) entrollt werden
	      for(int i=0; i<4 ;i++)
	      {
	    	    Vector2d[] rect = {	c2.getUpperLeft(),
	    	    					new Vector2d(c2.getUpperLeft().x +c2.getWidth(),c2.getUpperLeft().y),
	    	    					c2.getLowerRight(),
	    	    					new Vector2d(c2.getLowerRight().x -c2.getWidth(),c2.getLowerRight().y)};
	    	   
	            Vector2d base = PointLineDist(c1.getCenter(), rect[i], rect[(i+1) % 4]);
	            if((c1.getCenter().sub(base).length())<minDistSq)
	            {
	                  // Kuerzerer Abstand, neu zuweisen.
	                  minDistSq = (c1.getCenter().sub(base).length());	             
	            }
	      }
	      return (minDistSq < c1.getRadius()* c1.getRadius());
	              
		
	}
	
	
	
}