package pixelCombat.gamephase.gamephaseelement;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.utils.GamePhase;
import pixelCombat.view.Animation;

public abstract class GamePhaseElement 
{
	protected GamePhase gamePhase;
	protected Vector2d pos;
	protected Animation animator;
	private Clip clip;

	/**
	 * 
	 *	@author BillyG
	 * 	- 	Zugehörig zu einer GamePhase
	 *  - 	Füllt eine GamePhase mit funktionalen Elementen
	 *  -	Eigenschaften:
	 *  	-	Positionen	
	 *  	-	Zustand
	 * 		-	draw():		Bild (statisch oder dynamisch (anim.)
	 * 		-	update():	Logik/Bild
	 * 		-	dynamic():	Bewegung/Zustandsübergänge
	 * 		
	 * 	-	Unterklassen
	 * 		-	Button
	 * 		-	Specials
	 * 		-	Pointer
	 * 		-	Selectionfield
	 * 		
	 * 
	 */
	
	public GamePhaseElement(GamePhase gamePhase, Vector2d pos)
	{
		this.gamePhase 			= gamePhase;
		this.pos				= pos;
		
	}
	
	public abstract void update(float delta);
	public abstract Image draw();
	public abstract void dynamic();

	/**
	 * @return the gamePhase
	 */
	public GamePhase getGamePhase() {
		return gamePhase;
	}

	/**
	 * @param gamePhase the gamePhase to set
	 */
	public void setGamePhase(GamePhase gamePhase) {
		this.gamePhase = gamePhase;
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
	 * @return the animator
	 */
	public Animation getAnimator() {
		return animator;
	}

	/**
	 * @param animator the animator to set
	 */
	public void setAnimator(Animation animator) {
		this.animator = animator;
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
	
	
}
