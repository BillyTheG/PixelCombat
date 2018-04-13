package pixelCombat.gamephase;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import pixelCombat.Math.Vector2d;
import pixelCombat.controller.MainMenueController;
import pixelCombat.enums.KeyCommand;
import pixelCombat.enums.MenuPoint;
import pixelCombat.gamephase.gamephaseelement.GamePhaseButton;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.gamephase.gamephaseelement.GamePhasePointer;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.utils.GamePhase;
import pixelCombat.utils.GamePhaseVisitor;
import pixelCombat.view.gamephases.MainMenueView;

public class MainMenue extends GamePhase{

	private MainMenueView 					mainMenueView;
	private MainMenueController 			mainMenueController; 
	private GamePhaseButton 				arcade;
	private	GamePhaseButton					versus_player;
	private	GamePhaseButton 				versus_ki;
	private GamePhaseButton 				help;
	private GamePhaseButton 				credits;
	private GamePhaseButton 				end_game;
	private GamePhasePointer 				pointer;
	private ArrayList<GamePhaseButton>		buttons;
	private int								current_menu_point 	= 0;
	private float 							y_offset			= 2f;
	private float 							delta				= 1f;
	private float 							y_start				= 4.1f;
	private float 							x_offset			= 8f;
	private MenuPoint						menuPoint 			= MenuPoint.NONE;
	
	
	public MainMenue(GameEngine engine, Canvas canvas, Console console) 
	{
		super(engine,console);
		this.mainMenueView 				= new MainMenueView(this,canvas);
		this.mainMenueController 		= new MainMenueController(this,mainMenueView);
		this.render 					= mainMenueView;
		this.controller					= mainMenueController;
		this.buttons					= new ArrayList<GamePhaseButton>();
		createButtons();		
	}

	private void createButtons() 
	{
		Vector2d	pos					= new Vector2d();
		
		pos.x							= x_offset;
		pos.y							= y_start-y_offset;
		arcade							= new GamePhaseButton(this, new Vector2d(pos.x,pos.y), true);
		pos.x							= x_offset;
		pos.y							= (y_start+1*delta)-y_offset;
		versus_player					= new GamePhaseButton(this, new Vector2d(pos.x,pos.y), true);
		pos.x							= x_offset;
		pos.y							= (y_start+2*delta)-y_offset;
		versus_ki						= new GamePhaseButton(this, new Vector2d(pos.x,pos.y), true);
		pos.x							= x_offset;
		pos.y							= (y_start+3*delta)-y_offset;
		help							= new GamePhaseButton(this, new Vector2d(pos.x,pos.y), true);
		pos.x							= x_offset;
		pos.y							= (y_start+4*delta)-y_offset;
		credits							= new GamePhaseButton(this, new Vector2d(pos.x,pos.y), true);
		pos.x							= x_offset;
		pos.y							= (y_start+5*delta)-y_offset;
		end_game						= new GamePhaseButton(this, new Vector2d(pos.x,pos.y), true);
				
		
		this.buttons.add(arcade);
		this.buttons.add(versus_player);
		this.buttons.add(versus_ki);
		this.buttons.add(help);
		this.buttons.add(credits);
		this.buttons.add(end_game);
		
		pointer							= new GamePhasePointer(this, pos, arcade,true);
	}

	@Override
	public void update(long deltaTime) 
	{
		float delta = (float) deltaTime / 1000000000.0f;

		for(GamePhaseElement el : this.elements)
			el.update(delta);
		
		this.pointer.update(delta);
		
		if(mainMenueController.enter_pressed)
		{
			enterNextScene();
			mainMenueController.enter_pressed = false;
			
		}
		if(mainMenueController.backSlash_pressed)
		{
			mainMenueController.backSlash_pressed = false;
			accept(engine.getVisitor(),false);
		}
	}
	
	public void move_cursor(boolean up)
	{
		if(up)
			current_menu_point 			= mod(current_menu_point+1,buttons.size());
		else
			current_menu_point 			= mod(current_menu_point-1,buttons.size());
			
		pointer.change(buttons.get(current_menu_point));
	}
	
	public static int mod(int x, int y)
	{
	    int result = x % y;
	    if (result < 0)
	    {
	        result += y;
	    }
	    return result;
	}
	
	public void enterNextScene()
	{
		switch(current_menu_point)
		{
			case 0:
				setMenuPoint(MenuPoint.ARCADE);
				accept(engine.getVisitor(),true);
				break;
			case 1:
				setMenuPoint(MenuPoint.PLAYER);
				accept(engine.getVisitor(),true);
				break;
			case 2:
				setMenuPoint(MenuPoint.KI);
				accept(engine.getVisitor(),true);
				break;
			case 3:
				setMenuPoint(MenuPoint.HELP);
				accept(engine.getVisitor(),true);
				break;
			case 4:
				setMenuPoint(MenuPoint.CREDIT);
				accept(engine.getVisitor(),true);
				break;
			case 5:
				setMenuPoint(MenuPoint.NONE);
				engine.close();
				break;
			default:
				setMenuPoint(MenuPoint.NONE);
				break;
		}
	}
	
	@Override
	public void handleCommand(KeyCode kc, boolean hold) 
	{
		switch (kc)	
		{
			case UP:
				controller.onKey(KeyCommand.P2JUMP, hold);
				break;
			case DOWN:
				controller.onKey(KeyCommand.P2DOWN, hold);
				break;
			case W:
				controller.onKey(KeyCommand.P1JUMP, hold);
				break;
			case S:
				controller.onKey(KeyCommand.P1DOWN, hold);
				break;
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
		if(forward)
			engine.setGamePhase(visitor.visit(this));
		else
			engine.setGamePhase(visitor.exit(this));
	}

	/**
	 * @return the pointer
	 */
	public GamePhasePointer getPointer() {
		return pointer;
	}

	/**
	 * @param pointer the pointer to set
	 */
	public void setPointer(GamePhasePointer pointer) {
		this.pointer = pointer;
	}

	@Override
	public void reset() {
		
	}

	/**
	 * @return the menuPoint
	 */
	public MenuPoint getMenuPoint() {
		return menuPoint;
	}

	/**
	 * @param menuPoint the menuPoint to set
	 */
	public void setMenuPoint(MenuPoint menuPoint) {
		this.menuPoint = menuPoint;
	}
	
	

}
