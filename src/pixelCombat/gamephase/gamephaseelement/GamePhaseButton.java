package pixelCombat.gamephase.gamephaseelement;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.utils.GamePhase;


public class GamePhaseButton extends GamePhaseElement{
	
	private boolean selected	= false;
	private boolean	horizontal	= false;
	
	public GamePhaseButton(GamePhase gamePhase, Vector2d pos, boolean horizontal) 
	{
		super(gamePhase, pos);	
		this.horizontal 	= horizontal;
	}

	@Override
	public void update(float delta) 
	{
		
	}

	@Override
	public Image draw() 
	{
		return animator.getImage();
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * @param horizontal the horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	
	
	
}
