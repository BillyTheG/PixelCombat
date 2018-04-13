package pixelCombat.gamephase;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import pixelCombat.Math.Vector2d;
import pixelCombat.controller.CharacterSelectionController;
import pixelCombat.enums.FigureState;
import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.gamephaseelement.ContentListener;
import pixelCombat.gamephase.gamephaseelement.GamePhaseCharacterSelectionField;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.gamephase.gamephaseelement.GamePhaseFigure;
import pixelCombat.gamephase.gamephaseelement.figures.NatsuFigure;
import pixelCombat.gamephase.gamephaseelement.figures.RuffyFigure;
import pixelCombat.gamephase.gamephaseelement.figures.ZorroFigure;
import pixelCombat.observer.IObserveContentListener;
import pixelCombat.observer.Observable;
import pixelCombat.observer.Observer;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.utils.GamePhase;
import pixelCombat.utils.GamePhaseVisitor;
import pixelCombat.view.gamephases.CharacterSelectionView;

public class CharacterSelection extends GamePhase implements Observable{

	private ArrayList<IObserveContentListener> 				observers = new ArrayList<IObserveContentListener>();
	private CharacterSelectionView 				characterSelectionView;
	private CharacterSelectionController 		characterSelectionController;
	private GamePhaseCharacterSelectionField 	selectionField;		
	private GamePhaseFigure 					player1_selection;
	private GamePhaseFigure 					player2_selection;	
	private int 								player1_pointer = 0;
	private int									player2_pointer = 0;
	private Canvas 								canvas;
	private boolean 							player1HasSelected;
	private boolean 							player2HasSelected;
	private boolean vsAi;

	// all figures
	

	public CharacterSelection(GameEngine engine, Canvas canvas, ContentListener contentListener, Console console) {
		super(engine,console);
		this.characterSelectionView = new CharacterSelectionView(this, canvas);
		this.characterSelectionController = new CharacterSelectionController(engine, characterSelectionView,this);
		this.canvas = canvas;
		this.render = characterSelectionView;
		this.controller = characterSelectionController;
		this.observers.add(contentListener);
		init(canvas);
	}

	public void init(Canvas canvas) {
		selectionField = new GamePhaseCharacterSelectionField(this, new Vector2d(2, 2),	canvas);
		
		player1_selection = new RuffyFigure(this, new Vector2d(0, 0), canvas);
		player2_selection = new RuffyFigure(this, new Vector2d(0, 0), canvas);
		
				
		elements.add(selectionField);
	}

	@Override
	public void update(long deltaTime) {
		float delta = ((float) deltaTime) / 1000000000.0f;

		player1_selection.update(delta);
		player2_selection.update(delta);
		
		
		for (GamePhaseElement el : this.elements)
			el.update(delta);

		checkCondiitonsForNextPhase();

		if (characterSelectionController.backSlash_pressed) {
			characterSelectionController.backSlash_pressed = false;
			accept(engine.getVisitor(), false);
		}
	}

	private void checkCondiitonsForNextPhase() {
		
			
		if (this.player1HasSelected 
				&&	this.player2HasSelected
				&&	this.player1_selection.isReady()
				&&	this.player2_selection.isReady()) {
			player1HasSelected = false;							
			player1_selection.changeToStandAnim();
			player1_selection.setReady(false);
			player2HasSelected = false;							
			player2_selection.changeToStandAnim();
			player2_selection.setReady(false);
			characterSelectionController.enter_pressed = false;
			accept(engine.getVisitor(),true);
			
		}
	}

	@Override
	public void handleCommand(KeyCode kc, boolean hold) {
		switch (kc) {
		case LEFT:
			controller.onKey(KeyCommand.P2LEFT, hold);
			break;
		case RIGHT:
			controller.onKey(KeyCommand.P2RIGHT, hold);
			break;
		case A:
			controller.onKey(KeyCommand.P1LEFT, hold);
			break;
		case D:
			controller.onKey(KeyCommand.P1RIGHT, hold);
			break;
		case V:
			controller.onKey(KeyCommand.P1BASICATTACK1, hold);
			break;	
		case B:
			controller.onKey(KeyCommand.P1BASICATTACK2, hold);
			break;	
		case J:
			controller.onKey(KeyCommand.P2BASICATTACK1, hold);
			break;	
		case K:
			controller.onKey(KeyCommand.P2BASICATTACK2, hold);
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

	
	public void move_cursor(boolean player1, boolean right) 
	{
		if(player1 && !player1HasSelected)
		{
			if(this.player1_selection.getState() != FigureState.STAND)
				return;
			
			if(right)
				player1_pointer = MainMenue.mod(player1_pointer+1,selectionField.getSelections_size());
			else
				player1_pointer = MainMenue.mod(player1_pointer-1,selectionField.getSelections_size());			
			
			String selected_character_name = selectionField.getSelections()[player1_pointer];
			matchCharacter(true,selected_character_name);
		}
		else
		if(!player1 && !player2HasSelected)
		{
			if(this.player2_selection.getState() != FigureState.STAND)
				return;
			
			if(right)
				player2_pointer = MainMenue.mod(player2_pointer+1,selectionField.getSelections_size());
			else
				player2_pointer = MainMenue.mod(player2_pointer-1,selectionField.getSelections_size());
			
			String selected_character_name = selectionField.getSelections()[player2_pointer];
			matchCharacter(false,selected_character_name);
			
		}
		
	}
	
	
	public void select(boolean player1)
	{
		if(player1)	{
			if(this.player1_selection.getState() != FigureState.STAND)
				return;
			player1HasSelected = true;							
			player1_selection.changeToSelectAnim();
		}
		else {
			if(this.player2_selection.getState() != FigureState.STAND)
				return;
			player2HasSelected = true;			
			player2_selection.changeToSelectAnim();
		}
		notifyObservers();
	}
	
	public void deselect(boolean player1)
	{
		if(player1)	{
			if(!this.player1_selection.isReady())
				return;
			player1HasSelected = false;							
			player1_selection.changeToDeSelectAnim();
			player1_selection.setReady(false);
		}
		else {
			if(!this.player2_selection.isReady())
				return;
			player2HasSelected = false;	
			player2_selection.changeToDeSelectAnim();
			player2_selection.setReady(false);
		}
		notifyObservers();
	}
	
	public void matchCharacter(boolean player1,String selected_char) 
	{
		switch(selected_char)
		{
		case "ruffy": 	RuffyFigure ruffy = new RuffyFigure(this, new Vector2d(0, 0), canvas);
						if(player1)	this.player1_selection = ruffy;
						else this.player2_selection = ruffy;						
						break;
						
		case "natsu":	NatsuFigure natsu = new NatsuFigure(this, new Vector2d(0, 0), canvas);
						if(player1)	this.player1_selection = natsu;
						else this.player2_selection = natsu;						
						break;
		case "zorro":	ZorroFigure zorro = new ZorroFigure(this, new Vector2d(0, 0), canvas);
						if(player1)	this.player1_selection = zorro;
						else this.player2_selection = zorro;						
						break;
		default:
						break;		
		
		}
		
		
		
	}
	
	
	@Override
	public void accept(GamePhaseVisitor visitor, boolean forward) {
		if (forward)
			engine.setGamePhase(visitor.visit(this));
		else
			engine.setGamePhase(visitor.exit(this));
	}

	/**
	 * @return the selectionField
	 */
	public GamePhaseCharacterSelectionField getSelectionField() {
		return selectionField;
	}

	/**
	 * @param selectionField the selectionField to set
	 */
	public void setSelectionField(GamePhaseCharacterSelectionField selectionField) {
		this.selectionField = selectionField;
	}

	/**
	 * @return the player1_selection
	 */
	public GamePhaseFigure getPlayer1_selection() {
		return player1_selection;
	}

	/**
	 * @param player1_selection the player1_selection to set
	 */
	public void setPlayer1_selection(GamePhaseFigure player1_selection) {
		this.player1_selection = player1_selection;
	}

	/**
	 * @return the player2_selection
	 */
	public GamePhaseFigure getPlayer2_selection() {
		return player2_selection;
	}

	/**
	 * @param player2_selection the player2_selection to set
	 */
	public void setPlayer2_selection(GamePhaseFigure player2_selection) {
		this.player2_selection = player2_selection;
	}

	/**
	 * @return the player1_pointer
	 */
	public int getPlayer1_pointer() {
		return player1_pointer;
	}

	/**
	 * @param player1_pointer the player1_pointer to set
	 */
	public void setPlayer1_pointer(int player1_pointer) {
		this.player1_pointer = player1_pointer;
	}

	/**
	 * @return the player2_pointer
	 */
	public int getPlayer2_pointer() {
		return player2_pointer;
	}

	/**
	 * @param player2_pointer the player2_pointer to set
	 */
	public void setPlayer2_pointer(int player2_pointer) {
		this.player2_pointer = player2_pointer;
	}

	@Override
	public void addObserver(Observer o) 
	{
		this.observers.add((IObserveContentListener) o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.observers.remove(o);
		
	}

	@Override
	public void notifyObservers() {
		String player1 = selectionField.getSelections()[player1_pointer];
		String player2 = selectionField.getSelections()[player2_pointer];
		
		for(IObserveContentListener observer :observers)
			observer.updateSelectedCharacter(player1, player2,vsAi);
		
	}

	@Override
	public void reset() {
		this.player1HasSelected = false;
		this.player2HasSelected = false;
		this.player1_selection.setReady(false);
		this.player2_selection.setReady(false);
		this.player1_selection.changeToStandAnim();
		this.player2_selection.changeToStandAnim();
		this.player1_selection.setState(FigureState.STAND);
		this.player2_selection.setState(FigureState.STAND);
	}

	public void setVsAi(boolean vsAi) {
		this.vsAi = vsAi;
		
	}
	


}
