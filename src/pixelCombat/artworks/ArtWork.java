package pixelCombat.artworks;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.animation.Animation;

public class ArtWork {

	public 				Vector2d 	pos;
	public 				Vector2d 	startPos;
	public 				Vector2d 	firstTarget;
	public 				Animation 	dustAnimator;
	public 				float 		VX;
	public 				float 		VY;
	public 				boolean 	dead 				= 	false;
	private 			Clip 		clip;
	protected 			float 		scaleX 				=	1f;
	protected 			float 		scaleY 				=	1f;
	private 			boolean 	isSpecialArtWork 	= 	false;
	private				double		OPACITY				=	1d;
	private				boolean 	drawBehind			= 	false;
	
	
	public ArtWork(Vector2d pos, float VX, float VY) 
	{
		this.pos = pos;
		this.startPos = new Vector2d(pos.x,pos.y);
		this.VX = VX;
		this.VY = VY;
	}

	public ArtWork(Vector2d pos,Vector2d firstTarget, float VX, float VY) 
	{
		this.pos = pos;
		this.firstTarget = firstTarget;
		this.startPos = new Vector2d(pos.x,pos.y);
		this.VX = VX;
		this.VY = VY;
	}
	
	
	
	public void update(float delta)
	{
		this.dustAnimator.update(delta);
	
		if(dustAnimator.getAnimTime() == dustAnimator.getTotalDuration())
		{
			this.dead = true;
		}
		move(delta);
	}
	
	public void move(float delta)
	{
		this.pos.x += VX*delta;
		this.pos.y += VY*delta;
		
		if(firstTarget!=null)
			checkIfTargetReached(firstTarget);

		
	}
	protected void checkIfTargetReached(Vector2d target) {
		
		if(pos.x > target.x && VX >0)
			pos.x = target.x ;
		else
		if(pos.x < target.x && VX <0)
			pos.x = target.x ;
				
		if(pos.y > target.y && VY > 0)	
			pos.y = target.y;
		else
		if(pos.y < target.y && VY < 0)	
			pos.y = target.y;
		
	}

	public void repositionate()
	{
		this.pos.x = startPos.x;
		this.pos.y = startPos.y;
	}
	
	public void reset()
	{
		repositionate();
		setDead(false);
		dustAnimator.start();
	}
	

	public void setDead(boolean dead) 
	{
		this.dead = dead;
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


	  public void sound(String url)
	  {
	    try
	    {
	      this.clip = AudioSystem.getClip();
	      AudioInputStream inputStream1 = 
	        AudioSystem.getAudioInputStream(Character.class.getResource(url));
	      this.clip.open(inputStream1);
	      this.clip.start();
	    }
	    catch (Exception localException) {}
	  }

	public float getScaleX() {
		return scaleX;
	}
    
	public float getScaleY() {
		return scaleY;
	}

	public boolean isSpecialArtWork() {
		return isSpecialArtWork;
	}

	public void setSpecialArtWork(boolean isSpecialArtWork) {
		this.isSpecialArtWork = isSpecialArtWork;
	}

	public double getOPACITY() {
		return OPACITY;
	}

	public void setOPACITY(double oPACITY) {
		OPACITY = oPACITY;
	}

	public boolean shouldBeDrawBehind() {
		return drawBehind;
	}

	public void setDrawBehind(boolean drawBehind) {
		this.drawBehind = drawBehind;
	}
}
