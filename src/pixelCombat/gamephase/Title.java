package pixelCombat.gamephase;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import pixelCombat.Math.Vector2d;
import pixelCombat.controller.TitleController;
import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.gamephase.gamephaseelement.PressEnterButton;
import pixelCombat.model.PXMapHandler;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.utils.GamePhase;
import pixelCombat.utils.GamePhaseVisitor;
import pixelCombat.view.gamephases.TitleView;

public class Title extends GamePhase{

	private TitleView 			titleView ;
	private TitleController 	titleController; 
	private PressEnterButton	enter_button;
	
	public Title(GameEngine engine, Canvas canvas, Console console) 
	{
		super(engine,console);
		this.enter_button				= new PressEnterButton(this,new Vector2d(),true);
		
		this.enter_button.getPos().x	= PXMapHandler.X_FIELDS*	1/2;				
		this.enter_button.getPos().y	= PXMapHandler.Y_FIELDS* 	4/5;
		this.elements.add(enter_button);
		
		this.titleView 					= new TitleView(this,canvas);
		this.titleController 			= new TitleController(this,titleView);
		this.render 					= titleView;
		this.controller					= titleController;		
		
	}

	@Override
	public void update(long deltaTime) 
	{
		float delta = (float) deltaTime / 1000000000.0f;
		
		for(GamePhaseElement el : this.elements)
			el.update(delta);
		
		if(titleController.enter_pressed)
		{
			titleController.enter_pressed = false;
			accept(engine.getVisitor(),true);
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
		engine.setGamePhase(visitor.visit(this));	
	}

	@Override
	public void reset() {
			
	}

}
