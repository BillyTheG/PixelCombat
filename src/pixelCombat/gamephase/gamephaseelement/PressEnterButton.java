package pixelCombat.gamephase.gamephaseelement;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.utils.GamePhase;
import pixelCombat.view.animation.Animation;
import pixelCombat.view.gamephases.GamePlayView;

public class PressEnterButton extends GamePhaseButton {

	public PressEnterButton(GamePhase gamePhase, Vector2d pos,boolean horizontal) 
	{
		super(gamePhase, pos, horizontal);
		ArrayList<Image> images 	= new ArrayList<Image>();
		ArrayList<Float> times		= new ArrayList<Float>();
		
		images.add(GamePlayView.loadImage("/gamePhase/elements/press_enter_1.png"));
		images.add(GamePlayView.loadImage("/gamePhase/elements/press_enter_2.png"));
		
		times.add(0.125f);
		times.add(0.125f);
	
		this.animator = new Animation(images,times,false);
	}
	
	@Override
	public void update(float delta)
	{
		this.animator.update(delta);
	}

}
