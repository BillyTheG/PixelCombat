package pixelCombat.controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import pixelCombat.enums.KeyCommand;

/**
 * 
 * Controller
 * 
 * 
 * @author BillyG
 * @version 0.1
 */
public abstract class Controller 
{
	public boolean 		enter_pressed = false;
	public boolean 		backSlash_pressed	= false;
	private Clip clip;
	
	
	public abstract  void update(long delta);
	public abstract boolean onKey(KeyCommand keyCommand,boolean hold);
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
