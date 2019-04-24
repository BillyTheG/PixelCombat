package pixelCombat.model;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.animation.Animation;

public class Dust extends PXObject {

	public Vector2d pos;
	public Animation dustAnimator;
	public boolean dead = false;
	public boolean faceRight;
	
	public Dust(Vector2d pos, boolean faceRight) 
	{
		super(0f, pos);
		this.faceRight = faceRight;
		this.pos = pos;
	}

	
	public void update(float delta)
	{
		this.dustAnimator.update(delta);
		if(dustAnimator.getAnimTime() == dustAnimator.getTotalDuration() && dustAnimator.once)
			this.dead = true;
		
	}
	
	public void reset(Vector2d newPos, boolean newDirection)
	{
		dustAnimator.start();
		dead = false;
		repositionate(newPos, newDirection);
	}

	public void reset()
	{
		dustAnimator.start();
		dead = false;
	}

	public void repositionate(Vector2d newPos, boolean newDirection) {
		pos.x = newPos.x;
		pos.y = newPos.y;
	    faceRight = newDirection;
	}
	
	public Image draw(){
		return this.dustAnimator.getImage();		
		
	}

	
	 /**
     * Safe way for loading images from resource folder without <br>
     * crashing on invalid url
     * 
     * @param url
     *            String
     * @return Image
     */
    public Image loadImage(String url) {
	try {
	    Image img = new Image(url);
	    
	    return img;
	} catch (Exception e) {
	    System.out.println("The image: "+url + " was not found");
	    return null;
	}
    }


	/**
	 * @return the pos
	 */
	public Vector2d getPos() {
		return pos;
	}


	/**
	 * @param pos the pos to set
	 */
	public void setPos(Vector2d pos) {
		this.pos = pos;
	}


	/**
	 * @return the dead
	 */
	public boolean isDead() {
		return dead;
	}


	@Override
	public void updateMovement() {

		
	}

     
    
    
}
