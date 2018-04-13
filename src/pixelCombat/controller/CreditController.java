package pixelCombat.controller;

import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.Credit;
import pixelCombat.view.Renderer;


public class CreditController extends Controller 
{
	public Credit 		credit;
	public Renderer		creditView;

	/**
	 * Constructor for Character Selection Controller
	 * 
	 * @param engine
	 *            GameEngine
	 */
	public CreditController(Credit credit, Renderer renderer) 
	{
		this.credit 			= credit;
		this.creditView			= renderer;
	}

	public boolean onKey(KeyCommand key, boolean hold) 
	{
		switch (key) 
		{

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
