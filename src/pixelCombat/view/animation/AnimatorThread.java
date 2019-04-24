package pixelCombat.view.animation;

import pixelCombat.model.Character;
import pixelCombat.view.picManager.PicManager;

public class AnimatorThread implements Runnable 
{
	private volatile PicManager<Character> picManager;
	private volatile Character			   character;

	public AnimatorThread(Character character,PicManager<Character> picManager)
	{
		this.character  = character;
		this.picManager = picManager;
	}
		
	@Override
	public void run() 
	{	
		picManager.update(character.getDelta());
	}

}
