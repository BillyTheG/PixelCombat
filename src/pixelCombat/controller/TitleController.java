package pixelCombat.controller;

import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.Title;
import pixelCombat.view.gamephases.TitleView;


public class TitleController extends Controller 
{
	public Title 				title;
	public TitleView			titleView;
	public boolean 				enter_pressed = false;
	/**
	 * Constructor for Character Selection Controller
	 * 
	 * @param engine
	 *            GameEngine
	 */
	public TitleController(Title title, TitleView renderer) 
	{
		this.title 					= title;
		this.titleView				= renderer;
	}

	public boolean onKey(KeyCommand key, boolean hold) 
	{
		switch (key) 
		{
			case ENTER:	
				if (!hold)
					{
						enter_pressed = true;
						changeScene();
					}
				return true;
			default:
				break;
		}
		return true;
	}
	
	private void changeScene() 
	{
		sound("/audio/select_sound.wav");
	}

	@Override
	public void update(long delta)  
	{
		
	}

}
