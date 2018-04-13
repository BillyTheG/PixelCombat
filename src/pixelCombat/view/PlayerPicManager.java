package pixelCombat.view;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.model.Character;
import pixelCombat.model.PXMapHandler;

public class PlayerPicManager extends PicManager<Character>{

	
	// Bildsequenzen
	public final int STAND = 0;
	public final int MOVE = 1;
	public final int JUMPING = 2;
	public final int BASICATTACK = 3;
	public final int SPECIALATTACK1 = 4;
	public final int SPECIALATTACK2 = 5;
	public final int SPECIALATTACK3 = 6;
	public final int ISHIT = 7;
	public final int KNOCKBACK = 8;
	public final int KNOCKEDOUT = 9;
	public final int AVATAR = 10;
	public final int BASICATTACK1 = 11;
	public final int JUMPATTACK = 12;
	public final int RETREATING = 13;
	public final int DASHING = 14;
	public final int DEFENDING = 15;
	public final int SPECIALATTACK4 = 16;
	public final int SPECIALATTACK5 = 17;
	public final int BASICATTACK21 = 18;
	public final int BASICATTACK22 = 19;
	public final int BASICATTACK23 = 20;
	public final int SPECIALATTACK6 = 21;
	public final int SPECIALATTACK7 = 22;
	public final int INTRO = 23;
	public final int WIN = 24;
	public final int DEAD = 25;


	public PlayerPicManager(Character character, ArrayList<ArrayList<Image>> Images,
			ArrayList<ArrayList<Float>> times, ArrayList<Integer> loopIndizes,
			ArrayList<Boolean> loopBools) {
		super(character,Images,times,loopIndizes,loopBools);
	}

	public void init() {
		animation = Images.get(STAND);
		time = times.get(STAND);
		once = loopBools.get(STAND);
		reset(this.animation, this.time);
		loadFrames();
	}
	

	@Override
	public void update(float delta) {
		if (frames.size() > 1)
			animTime += delta;

		if (animTime >= totalDuration && once) {
			// Bei Loop muss Zeit auf den LoopPunkt gesetzt werden.
			float beginTime = 0f;
			for (int i = 0; i < loopPoint; i++) {
				beginTime += time.get(i);
			}

			animTime = (animTime + beginTime) % totalDuration;
			currFrameIndex = loopPoint;
		}

		if (animTime >= totalDuration && !once) {
			animTime = totalDuration;
		}

		while (animTime > getFrame(currFrameIndex).endTime) {
			currFrameIndex++;
		}

		checkOnGround();

	}

	private void checkOnGround() {

		if (Math.abs(pxObject.getPos().y - PXMapHandler.GROUNDLEVEL) <= 0.25f && pxObject.statusLogic.getActionStates() == ActionStates.STAND
				&& this.currentAnimation != STAND && pxObject.statusLogic.isActive() && !pxObject.isJumpAttacking()
				&& pxObject.attackLogic.getAttackStatus() == AttackStates.notAttacking) {
			this.lastAnimation = this.currentAnimation;
			this.currentAnimation = STAND;
			pxObject.boxLogic.update();
			setup();
			return;
		}

	}
	@Override
	public void change(int nextAnim) 
	{
		
		// Spieler ist in Luft, Bilder werden ab LoopIndex gezeichnet
		if ((nextAnim == JUMPING && currentAnimation != STAND && currentAnimation != MOVE)
				|| (nextAnim == STAND && Math.abs(pxObject.getPos().y - PXMapHandler.GROUNDLEVEL) > 0.15f)) {
			// Bilder, Zeiten, LoopIndex holen
			this.pxObject.statusLogic.setActionStates_manuell(ActionStates.JUMP);
			this.lastAnimation = this.currentAnimation;
			this.currentAnimation = JUMPING;
			once = this.loopBools.get(JUMPING);
			loopPoint = this.loopIndizes.get(JUMPING);
			this.animation = Images.get(JUMPING);
			this.time = times.get(JUMPING);
			
			// Frames erneuern
			totalDuration = 0;
			currFrameIndex = loopIndizes.get(JUMPING);
			this.pxObject.boxLogic.update();
			this.frames = new ArrayList<AnimFrame>();
			loadFrames();

			// animTime auf LoopIndex setzen
			float beginTime = 0f;
			for (int i = 0; i < loopPoint; i++) 
			{
				beginTime += time.get(i);
			}
			animTime = (animTime + beginTime) % totalDuration;
			return;
		}

		if (currentAnimation == nextAnim)
			return;

		if (Images.get(nextAnim).size() == 0)
			return;

		this.lastAnimation = this.currentAnimation;
		this.currentAnimation = nextAnim;
		setup();

	}
	
	public boolean animationIsFinished()
	{
		return animTime >= totalDuration;
	}
	

	public boolean isAlmostFinished(int currentFrameIndex) {	
		int previousIndex 		= currentFrameIndex-1;
		float middleTime		= animTime -getFrame(previousIndex).endTime;				
		float diff  			= getFrame(currentFrameIndex).endTime - getFrame(previousIndex).endTime;
		float percent			= middleTime/diff;	
			
		return(percent >= 0.30f);							
	}
	
	public void resetToIndex(int returnIndex) {	
		setCurrFrameIndex(returnIndex);
		if(returnIndex == 0)
			setAnimTime(getFrame(returnIndex).getEndTime());	
		else
			setAnimTime(getFrame(returnIndex-1).getEndTime());							
	}
	
	public int getLastAnimation() {
		return lastAnimation;
	}

	public void setLastAnimation(int lastAnimation) {
		this.lastAnimation = lastAnimation;
	}

	public int getSTAND() {
		return STAND;
	}

	public int getMOVE() {
		return MOVE;
	}

	public int getJUMPING() {
		return JUMPING;
	}

	public int getBASICATTACK() {
		return BASICATTACK;
	}

	public int getSPECIALATTACK1() {
		return SPECIALATTACK1;
	}

	public int getSPECIALATTACK2() {
		return SPECIALATTACK2;
	}

	public int getSPECIALATTACK3() {
		return SPECIALATTACK3;
	}

	public int getISHIT() {
		return ISHIT;
	}

	public int getKNOCKBACK() {
		return KNOCKBACK;
	}

	public int getKNOCKEDOUT() {
		return KNOCKEDOUT;
	}

	public int getAVATAR() {
		return AVATAR;
	}

	public int getBASICATTACK1() {
		return BASICATTACK1;
	}

	public int getJUMPATTACK() {
		return JUMPATTACK;
	}

	public int getRETREATING() {
		return RETREATING;
	}

	public int getDASHING() {
		return DASHING;
	}

	public int getDEFENDING() {
		return DEFENDING;
	}

	public int getCurrentAnimation() {
		return this.currentAnimation;
	}

	public void kill() {

	}





}
