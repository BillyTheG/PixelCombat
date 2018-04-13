package pixelCombat.gamephase.gamephaseelement;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.utils.GamePhase;
import pixelCombat.view.Animation;
import pixelCombat.view.gamephases.GamePlayView;


public class GamePhasePointer extends GamePhaseElement{
	
	private GamePhaseButton 	pointedButton;
	private boolean				horizontal;
	private ArrayList<Image> 	images;
	private ArrayList<Float> 	times;  
	
	public GamePhasePointer(GamePhase gamePhase, Vector2d pos,GamePhaseButton pointedButton, boolean horizontal) 
	{
		super(gamePhase, pos);	
		this.horizontal 	= horizontal;
		this.pointedButton 	= pointedButton;
		init();
	}

	private void init() 
	{
		images 	= new ArrayList<Image>();
		times 	= new ArrayList<Float>();

		
		images.add(GamePlayView.loadImage("/gamePhase/elements/spiralsword_pointe_1.png"));
		images.add(GamePlayView.loadImage("/gamePhase/elements/spiralsword_pointe_2.png"));
		images.add(GamePlayView.loadImage("/gamePhase/elements/spiralsword_pointe_3.png"));
		images.add(GamePlayView.loadImage("/gamePhase/elements/spiralsword_pointe_4.png"));
		
		times.add(0.095f);
		times.add(0.095f);
		times.add(0.095f);
		times.add(0.095f);
		
		this.animator 		= new Animation(images,times,false);
		change(pointedButton);
	}

	public void change(GamePhaseButton pointedButton)
	{
		this.pointedButton	= pointedButton;
		this.horizontal		= pointedButton.isHorizontal();
		
		if(this.horizontal)
		{	
			this.pos.x		= 	(pointedButton.getPos().x - (float) images.get(0).getWidth()/100f +2f)*GamePlayView.SCALEFACTOR;
			this.pos.y 		= 	pointedButton.getPos().y;
		}
		else
		{	
			this.pos.x		= 	pointedButton.getPos().x;
			this.pos.y 		= 	(pointedButton.getPos().y + (float) images.get(0).getHeight()/100f +0.25f)*GamePlayView.SCALEFACTOR;
		}
		
	}

	@Override
	public void update(float delta) 
	{
		animator.update(delta);
	}

	@Override
	public Image draw() 
	{
		return this.animator.getImage();
	}

	@Override
	public void dynamic() 
	{
		
	}

	/**
	 * @return the pointedButton
	 */
	public GamePhaseButton getPointedButton() {
		return pointedButton;
	}

	/**
	 * @param pointedButton the pointedButton to set
	 */
	public void setPointedButton(GamePhaseButton pointedButton) {
		this.pointedButton = pointedButton;
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
