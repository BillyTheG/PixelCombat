package pixelCombat.view;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Animation {

	protected ArrayList<AnimFrame> frames;
	protected int currFrameIndex;
	protected int loopPoint1 = 0;
	protected float animTime;
	protected float totalDuration;
	public boolean once;
	public int loopCounts = 0;
	public int loopTypedCounts = 0;
	
	public static float ANIMATION_DIVISOR = 2000f;
	
	public Animation(ArrayList<Image> images, ArrayList<Float> times, boolean once, int loopPoint1)
	{
		frames = new ArrayList<AnimFrame>();
		totalDuration = 0f;
		this.loopPoint1 = loopPoint1;
		this.once = once;
		start();
		loadFrames(images,times);
	}
	
	/**
	 *  Constructor with loopPoint and loopCount for Special ArtWorks 
	 * 
	 * 
	 * @param images , all images
	 * @param times , all time vlaues for each img
	 * @param loopPoint1 , looping start index
	 * @param loopCounts , amount of loopings
	 */
	public Animation(ArrayList<Image> images, ArrayList<Float> times, int loopPoint1, int loopCounts)
	{
		frames = new ArrayList<AnimFrame>();
		totalDuration = 0f;
		this.loopPoint1 = loopPoint1;
		this.once = true;
		loopTypedCounts= loopCounts;
		start();
		loadFrames(images,times);
	}
	
	
	
	public Animation(ArrayList<Image> images, ArrayList<Float> times, boolean once)
	{
		frames = new ArrayList<AnimFrame>();
		totalDuration = 0f;
		this.once = once;
		start();
		loadFrames(images,times);
		
	}
	
	
	protected void loadFrames(ArrayList<Image> images, ArrayList<Float> times) {
		
		for(int i = 0; i< images.size();i++){
			addFrame(images.get(i),times.get(i));			
		}
		
	}


	public void start()
	{
		animTime = 0f;
		currFrameIndex = 0;
		loopCounts = loopTypedCounts;
	}
	
	public void addFrame(Image image, float duration)
	{
		
		totalDuration += duration;
		frames.add(new AnimFrame(image,totalDuration));
			
	}
	

	protected class AnimFrame
	{
		Image image;	
		float endTime;
	
		public AnimFrame(Image image, float endTime){
			this.image = image;
			this.endTime = endTime;
		}

		public float getEndTime() {
			return endTime;
		}
	}
	
	public Image getImage(){
		if(frames.size() == 0)
			{
			
				return null;
				
			}
		else
		{
			return this.getFrame(currFrameIndex).image;
		}
	}
	
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
			if(loopCounts == 0)
				animTime = totalDuration;		
			else
			{
				animTime = animTime % totalDuration;
				loopCounts-=1;
				currFrameIndex = 0;
			}
		}
		
		while(animTime > getFrame(currFrameIndex).endTime)
		{
			currFrameIndex++;
		}
		
	}


	protected AnimFrame getFrame(int currFrameIndex) 
	{
		return (AnimFrame) frames.get(currFrameIndex);
	}
	
	
	
	
	/**
	 * @return the totalDuration
	 */
	public float getTotalDuration() {
		return totalDuration;
	}


	/**
	 * @param totalDuration the totalDuration to set
	 */
	public void setTotalDuration(float totalDuration) {
		this.totalDuration = totalDuration;
	}


	/**
	 * @return the animTime
	 */
	public float getAnimTime() {
		return animTime;
	}



	/**
	 * @return the currFrameIndex
	 */
	public int getCurrFrameIndex() {
		return currFrameIndex;
	}



	/**
	 * @param currFrameIndex the currFrameIndex to set
	 */
	public void setCurrFrameIndex(int currFrameIndex) {
		this.currFrameIndex = currFrameIndex;
	}


	public void resetToIndex(int returnIndex) {	
		setCurrFrameIndex(returnIndex);
		setAnimTime(getFrame(returnIndex-1).getEndTime());							
	}



	private void setAnimTime(float animTime) {
		this.animTime = animTime;
		
	}
	
	public boolean isAlmostFinished(int currentFrameIndex) {	
		int previousIndex 		= currentFrameIndex-1;
		float middleTime		= animTime -getFrame(previousIndex).endTime;				
		float diff  			= getFrame(currentFrameIndex).endTime - getFrame(previousIndex).endTime;
		float percent			= middleTime/diff;	
			
		return(percent >= 0.85f);							
	}



	public boolean animationFinished() {
		return this.animTime >= this.totalDuration;
	}

	
}
