package pixelCombat.controller;

import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.Loading;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.Renderer;



public class LoadingController extends Controller 
{
	public GameEngine 			engine;
	public Renderer				titleView;
	public Loading				loading;
	
	
	/**
	 * Constructor for Character Selection Controller
	 * 
	 * @param engine
	 *            GameEngine
	 */
	public LoadingController(GameEngine engine, Renderer renderer, Loading loading) 
	{
		this.engine 			= engine;
		this.titleView			= renderer;
		this.loading			= loading;
		
		
		
	}

	public boolean onKey(KeyCommand key, boolean hold) 
	{				
		return true;
	}
	

	@Override
	public void update(long delta)  
	{
		
	}

}
