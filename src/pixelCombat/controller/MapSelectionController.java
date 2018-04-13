package pixelCombat.controller;

import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.MapSelection;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.Renderer;



public class MapSelectionController extends Controller 
{
	public GameEngine 	engine;
	public Renderer		titleView;
	public boolean 		enter_pressed = false;
	private MapSelection mapSelection;
	/**
	 * Constructor for Character Selection Controller
	 * 
	 * @param engine
	 *            GameEngine
	 */
	public MapSelectionController(GameEngine engine, Renderer renderer,MapSelection mapSelection) 
	{
		this.engine 			= engine;
		this.titleView			= renderer;
		this.mapSelection		= mapSelection;
	}

	public boolean onKey(KeyCommand key, boolean hold) 
	{
		switch (key) 
		{
			case P1LEFT:
				if (!hold)
					mapSelection.move_cursor(false);					
				return true;
			case P1RIGHT:
				if (!hold)
					mapSelection.move_cursor(true);
				return true;
			case P2LEFT:
				if (!hold)
					mapSelection.move_cursor(false);
				return true;
			case P2RIGHT:
				if (!hold)
					mapSelection.move_cursor(true);
				return true;		
			case ENTER:
				if (!hold)
					mapSelection.select();
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
