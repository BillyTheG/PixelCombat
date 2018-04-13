package pixelCombat.controller;

import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.MainMenue;
import pixelCombat.view.Renderer;


public class MainMenueController extends Controller 
{
	public MainMenue 	mainMenue;
	public Renderer		titleView;

	/**
	 * Constructor for Character Selection Controller
	 * 
	 * @param engine
	 *            GameEngine
	 */
	public MainMenueController(MainMenue mainMenue, Renderer renderer) 
	{
		this.mainMenue 			= mainMenue;
		this.titleView			= renderer;
	}

	public boolean onKey(KeyCommand key, boolean hold) 
	{
		switch (key) 
		{
			case ENTER:
				if (!hold)
					mainMenue.enterNextScene();
				return true;
			case P1DOWN:
				if (!hold)
					mainMenue.move_cursor(true);
				return true;
			case P2DOWN:
				if (!hold)
					mainMenue.move_cursor(true);
				return true;
			case P1JUMP:
				if (!hold)
					mainMenue.move_cursor(false);
				return true;
			case P2JUMP:
				if (!hold)
					mainMenue.move_cursor(false);
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
