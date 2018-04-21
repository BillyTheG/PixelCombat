package pixelCombat.gamephase.gamephaseelement;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.enums.FigureState;
import pixelCombat.utils.GamePhase;
import pixelCombat.view.FigureAnimation;

public abstract class GamePhaseFigure extends GamePhaseElement 
{
	
	protected 	HashMap<String,ArrayList<Image>> 	anims;
	protected 	ArrayList<Image> 					stand;
	protected 	ArrayList<Image> 					selected;
	protected 	ArrayList<Image> 					deselected;
	protected 	ArrayList<Image> 					victory;
	
	
	protected 	HashMap<String,ArrayList<Float>> 	times;
	protected 	ArrayList<Float> 					stand_times;
	protected 	ArrayList<Float> 					selected_times;
	protected 	ArrayList<Float> 					deselected_times;
	protected 	ArrayList<Float> 					victory_times;
	
	private 	boolean 							kill 				= false;	
	private 	FigureState 						state 				= FigureState.STAND;
	protected 	boolean switcher = true;
	private 	boolean ready;
	protected 	String selectedSound;
	protected 	String deselectedSound;
	protected 	String victorySound;
	
	
	public GamePhaseFigure(GamePhase gamePhase, Vector2d pos, Canvas canvas) 
	{
		super(gamePhase, pos);
		init();
	}

	private void init() 
	{
		this.anims 				= new HashMap<String,ArrayList<Image>>();
		this.stand 				= new ArrayList<Image>();
		this.selected 			= new ArrayList<Image>();
		this.deselected 		= new ArrayList<Image>();
		this.victory			= new ArrayList<>();
		
		this.times 				= new HashMap<String,ArrayList<Float>>();
		this.stand_times 		= new ArrayList<Float>();
		this.selected_times 	= new ArrayList<Float>();
		this.deselected_times 	= new ArrayList<Float>();
		this.victory_times		= new ArrayList<>();
		
		
		anims.put("stand", stand);
		anims.put("selected", selected);
		anims.put("deselected", deselected);
		anims.put("victory", victory);
		
		times.put("stand", stand_times);
		times.put("selected", selected_times);
		times.put("deselected", deselected_times);
		times.put("victory", victory_times);
	}

	
	@Override
	public void update(float delta)
	{
		if(!kill)
			animator.update(delta);
		updateMore();
	}
	
	public void updateMore()
	{
		switch(state){
		
		case SELECTED:
			updateSelected();
			break;
		case  DESELECTED: 
			updateDeselected();
		break;
		case  VICTORY: 
			updateVictory();
		break;
		default:
				break;
		}
	}
	public abstract void updateDeselected();
	public abstract void updateSelected();
	public abstract void updateVictory();
	public abstract void resetStats();

	public void changeToSelectAnim(){
	//	Character.sound(selectedSound);
		resetStats();
		state = FigureState.SELECTED;
		getAnimator().setup(selected, selected_times, true);
	}
	
	

	public void changeToDeSelectAnim(){
	//	Character.sound(deselectedSound);
		resetStats();
		state = FigureState.DESELECTED;	
		getAnimator().setup(deselected, deselected_times, true);
	}
	
	public abstract void changeToVictoryAnim();


	public void changeToStandAnim(){
		resetStats();
		state = FigureState.STAND;	
		getAnimator().setup(stand, stand_times, false);
	}
	

	@Override
	public Image draw() 
	{
		return animator.getImage();
	}

	
	public void kill()
	{
		
		this.anims.clear();
		this.stand.clear(); 				
		this.selected.clear(); 			
		this.deselected.clear(); 		
		this.victory.clear();
		
		this.times.clear(); 				
		this.stand_times.clear(); 		
		this.selected_times.clear(); 	
		this.deselected_times.clear(); 	
		this.victory_times.clear();
		
		kill = true;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof GamePhaseFigure)
		{
			return  ((GamePhaseFigure) other).times.equals(this.times) &&
					((GamePhaseFigure) other).anims.equals(this.anims) &&
							((GamePhaseFigure) other).getClass().getName().equals(this.getClass().getName());
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return stand.size() + selected.size() + deselected.size();
	}
	
	public FigureAnimation getAnimator()
	{
		return (FigureAnimation) this.animator;
	}

	/**
	 * @return the state
	 */
	public FigureState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(FigureState state) {
		this.state = state;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
		
	}

	public boolean isReady() {
		return ready;
	}
	
	
	
}
