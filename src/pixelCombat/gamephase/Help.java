package pixelCombat.gamephase;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import pixelCombat.Math.Vector2d;
import pixelCombat.controller.HelpController;
import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.gamephase.gamephaseelement.PressEnterButton;
import pixelCombat.model.PXMapHandler;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.utils.GamePhase;
import pixelCombat.utils.GamePhaseVisitor;
import pixelCombat.view.gamephases.HelpView;

public class Help extends GamePhase{

	private HelpView 			creditView ;
	private HelpController 		creditController; 
	private PressEnterButton	enter_button;
	private int					currentPage = 0;
	
	public Help(GameEngine engine, Canvas canvas, Console console) 
	{
		super(engine,console);
		this.enter_button				= new PressEnterButton(this,new Vector2d(),true);
		
		this.enter_button.getPos().x	= PXMapHandler.X_FIELDS*	1/2;				
		this.enter_button.getPos().y	= PXMapHandler.Y_FIELDS* 	4/5;
		this.elements.add(enter_button);
		
		this.creditView 				= new HelpView(this,canvas);
		this.creditController 			= new HelpController(this,creditView);
		this.render 					= creditView;
		this.controller					= creditController;		
		
	}

	@Override
	public void update(long deltaTime) 
	{
		float delta = (float) deltaTime / 1000000000.0f;
		
		for(GamePhaseElement el : this.elements)
			el.update(delta);
		
		if(creditController.enter_pressed)
		{
			creditController.enter_pressed = false;
			currentPage = ((currentPage+1) % 3);
		}
		
		if(creditController.backSlash_pressed)
		{
			creditController.backSlash_pressed = false;
			accept(engine.getVisitor(),false);
		}
	}

	@Override
	public void handleCommand(KeyCode kc, boolean hold) 
	{
		switch (kc)	
		{
			case ENTER:
				controller.onKey(KeyCommand.ENTER, hold);
				break;
			case BACK_SPACE:
				controller.onKey(KeyCommand.BACK_SLASH, hold);
				break;
			case F9:
				this.engine.getGameStage().setFullScreen(false);
				break;
			case F10:
				this.engine.getGameStage().setFullScreen(true);
				break;
			default:
				break;
		}
	}

	@Override
	public void accept(GamePhaseVisitor visitor,boolean forward) 
	{
		engine.setGamePhase(visitor.exit(this));	
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	public int getCurrentPage() {
		return currentPage;
	}

}
