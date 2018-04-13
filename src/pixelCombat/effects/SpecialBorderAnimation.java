package pixelCombat.effects;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXObject;
import pixelCombat.view.Animation;

public class SpecialBorderAnimation extends PXObject {

	public Vector2d pos;
	public Animation introAnimator;
	public Animation outroAnimator;
	public boolean dead = false;
	public boolean faceRight;
	public boolean intro = true;
	public boolean outro = false;
	
	public SpecialBorderAnimation() 
	{
		super(0f, new Vector2d(0,0));
	}

	
	public void update(float delta)
	{
		if(intro)
		{
			this.introAnimator.update(delta);
		}
		if(outro)
		{
			this.outroAnimator.update(delta);
		}	

	}
	
	public void reset()
	{
		intro = true;
		outro = false;
		introAnimator.start();
		outroAnimator.start();
		dead = false;
	}
	
	public void makeOutro()
	{
		dead = false;
		intro = false;
		outro = true;
		outroAnimator.start();
	}
	
	public Image draw(){
		if(intro)return this.introAnimator.getImage();		
		if(outro)return this.outroAnimator.getImage();	
		return null;
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

    public boolean isIntroFinished(){
    	return intro && introAnimator.animationFinished();
    }
    
    public boolean isOutroFinished(){
    	return outro && outroAnimator.animationFinished();
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


	public Animation getIntroAnimator() {
		return introAnimator;
	}


	public void setIntroAnimator(Animation introAnimator) {
		this.introAnimator = introAnimator;
	}


	public Animation getOutroAnimator() {
		return outroAnimator;
	}


	public void setOutroAnimator(Animation outroAnimator) {
		this.outroAnimator = outroAnimator;
	}


	public boolean isFaceRight() {
		return faceRight;
	}


	public void setFaceRight(boolean faceRight) {
		this.faceRight = faceRight;
	}


	public boolean isIntro() {
		return intro;
	}


	public void setIntro(boolean intro) {
		this.intro = intro;
	}


	public boolean isOutro() {
		return outro;
	}


	public void setOutro(boolean outro) {
		this.outro = outro;
	}


	public void setDead(boolean dead) {
		this.dead = dead;
	}

     
    
    
}
