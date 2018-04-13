package pixelCombat.controller;

import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.Help;
import pixelCombat.view.Renderer;


public class HelpController extends Controller 
{
	public Help 		help;
	public Renderer		creditView;

	/**
	 * Constructor for Character Selection Controller
	 * 
	 * @param engine
	 *            GameEngine
	 */
	public HelpController(Help help, Renderer renderer) 
	{
		this.help 			= help;
		this.creditView		= renderer;
	}

	public boolean onKey(KeyCommand key, boolean hold) 
	{
		switch (key) 
		{
			case ENTER:
				if (!hold)
					enter_pressed = true;
				return true;	
			case BACK_SLASH:
				if (!hold)
					backSlash_pressed = true;
				return true;	
			default:
				break;
		}
		return true;
	}
	

	@Override
	public void update(long delta)  
	{
		
	}

}
