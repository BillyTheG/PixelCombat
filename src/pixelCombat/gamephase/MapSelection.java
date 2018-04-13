package pixelCombat.gamephase;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import pixelCombat.Math.Vector2d;
import pixelCombat.controller.MapSelectionController;
import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.gamephaseelement.ContentListener;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.gamephase.gamephaseelement.GamePhaseMapSelectionField;
import pixelCombat.observer.IObserveContentListener;
import pixelCombat.observer.Observable;
import pixelCombat.observer.Observer;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.utils.GamePhase;
import pixelCombat.utils.GamePhaseVisitor;
import pixelCombat.view.gamephases.MapSelectionView;

public class MapSelection extends GamePhase implements Observable{

	private MapSelectionView 						mapSelectionView;
	private MapSelectionController 					mapSelectionController;
	private ArrayList<IObserveContentListener> 		observers 					= new ArrayList<IObserveContentListener>(); 
	private int 									currentMapID 				= 0;
	private GamePhaseMapSelectionField 				selectionField;
	
	
	public MapSelection(GameEngine engine, Canvas canvas,  ContentListener contentListener, Console console) 
	{
		super(engine,console);
		this.mapSelectionView 									= new MapSelectionView(this,canvas);
		this.mapSelectionController 							= new MapSelectionController(engine,mapSelectionView,this);
		
		this.render 											= mapSelectionView;
		this.controller											= mapSelectionController;
		this.observers.add(contentListener);
		init(canvas);
	}

	private void init(Canvas canvas) {
		selectionField = new GamePhaseMapSelectionField(this, new Vector2d(2, 2),	canvas);		
		this.elements.add(selectionField);
	}

	@Override
	public void update(long deltaTime) 
	{
		float delta = ((float) deltaTime) / 1000000000.0f;
		for (GamePhaseElement el : this.elements)
			el.update(delta);
		
		if (mapSelectionController.enter_pressed) {
			mapSelectionController.enter_pressed = false;
			accept(engine.getVisitor(), true);
		}
		
		if (mapSelectionController.backSlash_pressed) {
			mapSelectionController.backSlash_pressed = false;
			accept(engine.getVisitor(), false);
		}
		
		
	}

	@Override
	public void handleCommand(KeyCode kc, boolean hold) 
	{
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
	public void accept(GamePhaseVisitor visitor, boolean forward) 
	{
		if(forward)
			engine.setGamePhase(visitor.visit(this));
		else
			engine.setGamePhase(visitor.exit(this));
	}

	
	public void move_cursor(boolean right) 
	{
		if(right)	currentMapID = MainMenue.mod(currentMapID+1,selectionField.getSelections_size());
		else		currentMapID = MainMenue.mod(currentMapID-1,selectionField.getSelections_size());			
		
	}
	public void select()
	{
		mapSelectionController.enter_pressed = true;
		notifyObservers();
	}
	
	
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
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
		
		
		for(IObserveContentListener observer :observers)
			observer.updateSelectedMap(currentMapID);
		
	}

	/**
	 * @return the currentMapID
	 */
	public int getCurrentMapID() {
		return currentMapID;
	}

	/**
	 * @param currentMapID the currentMapID to set
	 */
	public void setCurrentMapID(int currentMapID) {
		this.currentMapID = currentMapID;
	}

	/**
	 * @return the selectionField
	 */
	public GamePhaseMapSelectionField getSelectionField() {
		return selectionField;
	}

	/**
	 * @param selectionField the selectionField to set
	 */
	public void setSelectionField(GamePhaseMapSelectionField selectionField) {
		this.selectionField = selectionField;
	}

	
	
}
