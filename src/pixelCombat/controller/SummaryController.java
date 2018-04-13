package pixelCombat.controller;

import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.Summary;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.Renderer;



public class SummaryController extends Controller 
{
	public GameEngine 			engine;
	public Renderer				titleView;
	public Summary				summary;
	
	
	/**
	 * Constructor for Character Selection Controller
	 * 
	 * @param engine
	 *            GameEngine
	 */
	public SummaryController(GameEngine engine, Renderer renderer, Summary summary) 
	{
		this.engine 			= engine;
		this.titleView			= renderer;
		this.summary			= summary;
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
			case BACK_SLASH:
				if (!hold)
					backSlash_pressed = true;
				return true;	
			default:
				break;
		}
				
		return true;
	}
	
	private void changeScene() 
	{
		
		
	}

	@Override
	public void update(long delta)  
	{
		
	}

}
