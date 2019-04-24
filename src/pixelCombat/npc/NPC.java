package pixelCombat.npc;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.animation.Animation;

public class NPC {

	public Vector2d pos;
	public Animation animator;
	
	public NPC(Vector2d pos) 
	{
		this.pos = pos;
	}

	
	public void update(float delta)
	{
		this.animator.update(delta);
				
	}
	
	public Image draw(){
		return this.animator.getImage();		
		
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


	
    
}
