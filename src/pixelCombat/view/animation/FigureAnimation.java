package pixelCombat.view.animation;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.gamephase.gamephaseelement.GamePhaseFigure;

public class FigureAnimation extends Animation{

	private GamePhaseFigure figure;

	public FigureAnimation(ArrayList<Image> images, ArrayList<Float> times,	boolean once, GamePhaseFigure figure) 
	{
		super(images, times, once);
		this.figure = figure;
	}
	
	public void setup(ArrayList<Image> animation, ArrayList<Float> time, boolean once) {
		this.once = once;
		reset();
		loadFrames(animation,time);
	}
	
	public void setup(ArrayList<Image> animation, ArrayList<Float> time, boolean once, int loopPoint) {
		this.loopPoint1 = loopPoint;
		setup(animation, time, once);
		
	}

	public void reset() {
		totalDuration = 0;
		currFrameIndex = 0;
		animTime = 0;
		this.frames.clear();
	}

	public void kill() {
		this.frames.clear();
		
	}
	
	@Override
	protected void loadFrames(ArrayList<Image> images, ArrayList<Float> times) {
			for (int i = 0; i < images.size(); i++) {
			addFrame(images.get(i), times.get(i) / 2000f);
		}
	}
	
	@Override
	public void update(float delta)
	{
		if(frames.size() > 1)
			animTime += delta;
		
		
		if(animTime >= totalDuration && !once)
		{
			if(loopPoint1 == 0)
				animTime = animTime % totalDuration;
			else
			{
				animTime = getFrame(loopPoint1-1).endTime;
			}
			
			currFrameIndex = loopPoint1;
		}
		
		if(animTime >= totalDuration && once)
		{
			animTime = totalDuration;	
			changeFigureState();
		}
		
		while(animTime > getFrame(currFrameIndex).endTime)
		{
			currFrameIndex++;
		}
		
	}

	private void changeFigureState() {
		switch(figure.getState())
		{
			case SELECTED:
				figure.setReady(true);
				break;
			case DESELECTED:
				figure.changeToStandAnim();
				break;
			default:
				break;		
		}
		
	}
	
	
}
