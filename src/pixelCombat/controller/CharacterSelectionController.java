package pixelCombat.controller;

import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.CharacterSelection;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.Renderer;



public class CharacterSelectionController extends Controller 
{
	public GameEngine 			engine;
	public Renderer				titleView;
	public CharacterSelection	characterSelection;
	
	
	/**
	 * Constructor for Character Selection Controller
	 * 
	 * @param engine
	 *            GameEngine
	 */
	public CharacterSelectionController(GameEngine engine, Renderer renderer, CharacterSelection characterSelection) 
	{
		this.engine 			= engine;
		this.titleView			= renderer;
		this.characterSelection	= characterSelection;
	}

	public boolean onKey(KeyCommand key, boolean hold) 
	{
		
		switch (key) 
		{
			case P1LEFT:
				if (!hold)
					characterSelection.move_cursor(true,false);					
				return true;
			case P1RIGHT:
				if (!hold)
					characterSelection.move_cursor(true,true);
				return true;
			case P1BASICATTACK1:
				if (!hold)
					characterSelection.select(true);
				return true;
			case P1BASICATTACK2:
				if (!hold)
					characterSelection.deselect(true);
				return true;
			case P2LEFT:
				if (!hold)
					characterSelection.move_cursor(false,false);
				return true;
			case P2RIGHT:
				if (!hold)
					characterSelection.move_cursor(false,true);
				return true;
			case P2BASICATTACK1:
				if (!hold)
					characterSelection.select(false);
				return true;
			case P2BASICATTACK2:
				if (!hold)
					characterSelection.deselect(false);
				return true;
			case ENTER:		
				enter_pressed = true;
				changeScene();
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
