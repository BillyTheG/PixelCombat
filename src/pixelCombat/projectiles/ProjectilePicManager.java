package pixelCombat.projectiles;

import java.util.ArrayList;

import pixelCombat.view.animation.PositionedImage;
import pixelCombat.view.picManager.PicManager;

public class ProjectilePicManager extends PicManager<Projectile>{


	// Bildsequenzen
	public final int CREATION = 0;
	public final int MOVE = 1;
	public final int EXPLOSION = 2;
	public final int DEAD = 3;
	public final int SPECIALEFFECT1 = 4;
	public final int SPECIALEFFECT2 = 5;


	public ProjectilePicManager(Projectile projectile, ArrayList<ArrayList<PositionedImage>> Images,
			ArrayList<ArrayList<Float>> times, ArrayList<Integer> loopIndizes,
			ArrayList<Boolean> loopBools) {
		super(projectile,Images,times,loopIndizes,loopBools);
	}

	public void init() {
		animation = Images.get(CREATION);
		time = times.get(CREATION);
		loops = loopBools.get(CREATION);
		reset(this.animation, this.time);
		loadFrames();
	}

	@Override
	public void update(float delta) {
		if (!frames.isEmpty())
			animTime += delta;

		if (animTime >= totalDuration && loops) 
		{
			// Bei Loop muss Zeit auf den LoopPunkt gesetzt werden.
			float beginTime = 0f;
			for (int i = 0; i < loopPoint; i++) {
				beginTime += time.get(i);
			}

			animTime = (animTime + beginTime) % totalDuration;
			currFrameIndex = loopPoint;
		}

		if (animTime >= totalDuration && !loops) {
			animTime = totalDuration;
		}

		while (animTime > getFrame(currFrameIndex).endTime) {
			currFrameIndex++;
		}

	}

	
	@Override
	public void change(int nextAnim) 
	{

		if (currentAnimation == nextAnim)
			return;
		
		if (Images.get(nextAnim).size() == 0)
			return;
		
		this.lastAnimation = this.currentAnimation;
		this.currentAnimation = nextAnim;
		setup();
	}


	public int getLastAnimation() {
		return lastAnimation;
	}

	public void setLastAnimation(int lastAnimation) {
		this.lastAnimation = lastAnimation;
	}

	public int getSTAND() {
		return CREATION;
	}

	public int getMOVE() {
		return MOVE;
	}


	public int getCurrentAnimation() {
		return this.currentAnimation;
	}

}
