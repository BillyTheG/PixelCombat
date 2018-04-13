package pixelCombat.utils;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import pixelCombat.controller.Controller;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.view.Renderer;

public abstract class GamePhase 
{
	public Console							console;
	public Renderer 						render;
	public Controller 						controller;
	public GameEngine 						engine;
	public ArrayList<GamePhaseElement>		elements;
	
	public GamePhase(GameEngine engine,Console console)
	{
		this.console			= console;
		this.engine 			= engine;
		this.elements			= new ArrayList<GamePhaseElement>();
	}
	
	public void updatePrimaryStuff(long deltaTime)
	{
		controller.update(deltaTime);
		render.render(deltaTime);
		update(deltaTime);
	}
	
	abstract public void accept(GamePhaseVisitor visitor, boolean forward);
	abstract public void update(long deltaTime);
	abstract public void handleCommand(KeyCode kc, boolean hold);
	abstract public void reset();
	
	
}
