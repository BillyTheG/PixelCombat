package pixelCombat.view;

import java.util.ArrayList;

import javafx.scene.image.Image;

public abstract class PicManager<T> {

	// gesamte Bildreihe + Zeiten + LoopIndizes + LoopBoolean
	protected ArrayList<ArrayList<Image>> Images;
	protected ArrayList<ArrayList<Float>> times;
	protected ArrayList<Boolean> loopBools;
	protected ArrayList<Integer> loopIndizes;

	// temporäre Bildreihe und dessen Zeiten
	protected ArrayList<Image> animation;
	protected ArrayList<Float> time;

	// Animationsvariablen
	protected ArrayList<AnimFrame> frames;
	protected int currFrameIndex = 0;
	protected float animTime = 0f;
	protected float totalDuration = 0f;
	public boolean loops = false;
	protected int loopPoint = 0;
	protected int currentAnimation = 0;
	protected int lastAnimation = 0;
	
	protected T pxObject;

	public PicManager(T pxObject, ArrayList<ArrayList<Image>> Images,
			ArrayList<ArrayList<Float>> times, ArrayList<Integer> loopIndizes,
			ArrayList<Boolean> loopBools) 
	{
		this.pxObject = pxObject;
		this.Images = Images;
		this.times = times;
		this.loopBools = loopBools;
		this.loopIndizes = loopIndizes;
		init();
	}

	

	public void setup() {
		animation = Images.get(currentAnimation);
		time = times.get(currentAnimation);
		loops = loopBools.get(currentAnimation);
		loopPoint = this.loopIndizes.get(currentAnimation);
		reset(this.animation, this.time);
		loadFrames();
	}

	public void reset(ArrayList<Image> animation, ArrayList<Float> time) {
		totalDuration = 0;
		currFrameIndex = 0;
		animTime = 0;
		this.animation = animation;
		this.time = time;
		this.frames = new ArrayList<AnimFrame>();
	}

	public void loadFrames() {
		for (int i = 0; i < animation.size(); i++) {
			addFrame(animation.get(i), time.get(i) / Animation.ANIMATION_DIVISOR);
		}
	}
	
	public abstract void init();
	public abstract void update(float delta);
	public abstract void change(int nextAnim);
	
	
	public AnimFrame getFrame(int currFrameIndex) {
		return (AnimFrame) frames.get(currFrameIndex);
	}

	public void addFrame(Image image, float duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}

	public class AnimFrame {
		Image image;
		public float endTime;

		public AnimFrame(Image image, float endTime) {
			this.image = image;
			this.endTime = endTime;
		}

		public float getEndTime() {
			return endTime;
		}
	}

	public Image getImage() {
		if (frames.size() == 0) {

			return null;

		} else {

			return this.getFrame(currFrameIndex).image;
		}
	}

	// getter,setter
	public ArrayList<ArrayList<Image>> getImages() {
		return Images;
	}

	public void setImages(ArrayList<ArrayList<Image>> images) {
		Images = images;
	}

	public ArrayList<Image> getAnimation() {
		return animation;
	}

	public void setAnimation(ArrayList<Image> animation) {
		this.animation = animation;
	}

	public ArrayList<Float> getTimes() {
		return time;
	}

	public void setTimes(ArrayList<Float> time) {
		this.time = time;
	}

	public ArrayList<AnimFrame> getFrames() {
		return frames;
	}

	public void setFrames(ArrayList<AnimFrame> frames) {
		this.frames = frames;
	}

	public int getCurrFrameIndex() {
		return currFrameIndex;
	}

	public void setCurrFrameIndex(int currFrameIndex) {
		this.currFrameIndex = currFrameIndex;
	}

	public float getAnimTime() {
		return animTime;
	}

	public void setAnimTime(float animTime) {
		this.animTime = animTime;
	}

	public float getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(float totalDuration) {
		this.totalDuration = totalDuration;
	}

	public boolean isOnce() {
		return loops;
	}

	public void setOnce(boolean once) {
		this.loops = once;
	}

	public int getLoopPoint() {
		return loopPoint;
	}

	public void setLoopPoint(int loopPoint) {
		this.loopPoint = loopPoint;
	}

}
