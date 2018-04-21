package pixelCombat.model;

import pixelCombat.arenas.BackGroundEffect;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.CCStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.enums.MovementStates;
import pixelCombat.enums.TimeState;


public abstract class StatusLogic {

	// Movement
	private boolean onAir = false;

	private boolean retreatAndDashButtonpressed; 
	private boolean back = false;
	private boolean forward = false;
	

	// Basic
	protected Character character;
	private GlobalStates globalStates = GlobalStates.ACTIVE;
	private CCStates ccStates = CCStates.EMPTY;
	private ActionStates actionStates = ActionStates.STAND;
	private MovementStates movementStates = MovementStates.RIGHT;
	private BackGroundEffect backGroundEffect = BackGroundEffect.NONE;
	private boolean hitDelay = false;
	public boolean resetAnimation = false;

	// Defending
	private boolean resetDefending = false;

	private boolean isFocused = false;

	public StatusLogic(Character character) {
		this.character = character;
	}

	public void checkUpdate(){
		
		if(actionStates == ActionStates.MOVE)
			character.physics.isMoving =true;
		else
			character.physics.isMoving =false;
		
		switch (globalStates) {

		case ACTIVE:
			character.active();
			return;
		case INVINCIBLE:
			character.invincible();
			return;
		case KNOCKBACK:
			character.timeManager.resetBufferTimes();
			character.cancelAction();
			character.knockBack();
			return;
		case BLINKING:
			character.active();
			character.blink();
			return;
		case DISABLED:
			character.timeManager.resetBufferTimes();
			character.cancelAction();
			character.disable();
			return;
		case DEAD:
			
			return;
		}
	}
	
	
	public void swapDirection(){
		if(movementStates == MovementStates.LEFT)
			movementStates = MovementStates.RIGHT;
		else
			movementStates = MovementStates.LEFT;
	}
	
	// Logic Questions 
	
	public boolean canNotJump() {
		return isKnockback() 
				|| character.attackLogic.isAttacking()
				|| isJumping() 
				|| isJumpRecovering()
				|| isDashing()
				|| isDead()
				|| isDefending()
				|| isInvincible()
				|| isDisabled()
				|| character.timeManager.getJumpDelay().getY() < character.timeManager.getDelayHolder().get(TimeState.JUMPDELAY).floatValue();

	}


	public boolean canNotAttack() {
		return 	  character.attackLogic.isAttacking()
				|| isKnockback() 
				|| isInvincible()
				|| isDead()
				|| isJumpRecovering()
				|| isDisabled()  
				|| isDashing()
				|| isDefending() ;

	}
	
	public boolean canNotDefend() {
		return  
				character.attackLogic.isAttacking()
				|| isKnockback()
				|| isMoving()
				|| isInvincible()
				|| character.enemy.freeze
				|| isDead()
				|| isJumpRecovering()
				|| isDisabled()
				|| isDashing() ;
	}

	public boolean canNotSpecial1() {
		return character.attackLogic.isAttacking()	
				|| isKnockback() 
				|| isMoving()
				|| isDead()
				|| isInvincible()
				|| isDisabled() 
				|| isDashing() 
				|| isDefending() 
				|| isJumpRecovering()
				|| isJumping();
	}

	public boolean canNotAirSpecial1() {
		return ( character.attackLogic.isAttacking()
			    || isKnockback() 			   
				|| isDead()
				|| isInvincible()
				|| isDisabled() 
				|| isDashing() 
				|| isDefending() 
				|| isJumpRecovering()
				|| !isJumping());
	}
	
	public boolean canNotMove() {
		return canNotAttack() 
				|| isJumping()
				|| character.getPos().y < PXMapHandler.GROUNDLEVEL;

	}

	public boolean canNotDash() {
		return canNotAttack() 
				|| isMoving()
				|| character.physics.VY < 0
				|| character.timeManager.getDashAndRetreatDelay().getY().floatValue() < character.timeManager.getDelayHolder().get(TimeState.DASHANDRETREATDELAY).floatValue();

	}

	public boolean canAirAttack() {
		return isJumping()  && 	Math.abs(character.getPos().y - PXMapHandler.GROUNDLEVEL) > 1f;
	}
	
	



	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public boolean isRetreatAndDashButtonPressed() {
		return retreatAndDashButtonpressed;
	}



	public void setRetreatAndDashButtonPressed(boolean retreatAndDashButtonpressed) {
		this.retreatAndDashButtonpressed = retreatAndDashButtonpressed;
	}



	public boolean isBack() {
		return back;
	}



	public void setBack(boolean back) {
		this.back = back;
	}



	public boolean isForward() {
		return forward;
	}



	public void setForward(boolean forward) {
		this.forward = forward;
	}



	public GlobalStates getGlobalStatus() {
		return globalStates;
	}



	public void setGlobalStatus(GlobalStates globalStatus) {
		
			
		this.globalStates = globalStatus;
		character.viewLogic.update();
	}



	public CCStates getCcStates() {
		return ccStates;
	}



	public void setCcStates(CCStates ccStates) {
		
			
		this.ccStates = ccStates;
		character.viewLogic.update();
	}



	public ActionStates getActionStates() {
		return actionStates;
	}



	public void setActionStates(ActionStates actionStates) {
		if(this.actionStates == ActionStates.DASHING && actionStates == ActionStates.STAND)
			character.setSwitcher(true);
		this.actionStates = actionStates;
		character.viewLogic.update();
	}

	public void setActionStates_manuell(ActionStates actionStates) {
		
		this.actionStates = actionStates;
	}

	public boolean isHitDelayFinished() {
		return hitDelay;
	}



	public void setAHitDelay(boolean hitDelay) {
		this.hitDelay = hitDelay;
	}



	public boolean isResetAnimation() {
		return resetAnimation;
	}



	public void setResetAnimation(boolean resetAnimation) {
		this.resetAnimation = resetAnimation;
	}


	public MovementStates getMovementStates() {
		return movementStates;
	}



	public void setMovementStates(MovementStates movementStates) {
		this.movementStates = movementStates;
	}


	public boolean isOnAir() {
		return onAir || character.getPos().y < PXMapHandler.GROUNDLEVEL-1.2f;
	}



	public void setOnAir(boolean onAir) {
		this.onAir = onAir;
	}



	public boolean isResetDefending() {
		return resetDefending;
	}



	public void setResetDefending(boolean resetDefending) {
		this.resetDefending = resetDefending;
	}

	
	
	
	
//-------------------------------------------------------------------------------------

	public boolean isMoving() {
		return this.actionStates == ActionStates.MOVE;
	}

	public boolean isStanding() {
		return this.actionStates == ActionStates.STAND;
	}
	
	
	
	public boolean isIntroducting() {
		return this.actionStates == ActionStates.INTRO;
	}
	
	public boolean isWinning() {
		return this.actionStates == ActionStates.WIN;
	}



	public boolean isDead() {
		return this.globalStates == GlobalStates.DEAD;
	}

	public boolean isActive() {
		return getGlobalStatus() == GlobalStates.ACTIVE;
	}

	public boolean isJumpRecovering() {
		return this.actionStates == ActionStates.JUMP_RECOVER;
	}





	public boolean isBlinking() {
		return getGlobalStatus() == GlobalStates.BLINKING;
	}







	public boolean isJumping() {
		return getActionStates() == ActionStates.JUMP || isOnAir();
	}




	public boolean isDashing() {
		return this.actionStates == ActionStates.DASHING;
	}







	public boolean isDefending() {
		return getActionStates() == ActionStates.DEFENDING ||getActionStates() == ActionStates.AIR_DEFENDING;
	}


	public boolean isKnockback(){
		
		return getGlobalStatus() == GlobalStates.KNOCKBACK;
	}

	public boolean isImportant() {
		
		return getGlobalStatus() == GlobalStates.KNOCKBACK || 
		isFocused();
	}

	public boolean isFocused() {
		return isFocused;
	}
	
	public void setFocused(boolean isFocused) {
		this.isFocused = isFocused;
	}

	public boolean isInvincible() {
		
		return getGlobalStatus() == GlobalStates.INVINCIBLE;
	}
	
	public boolean isDisabled(){
		
		return getGlobalStatus() == GlobalStates.DISABLED;
	}

	public boolean isRight() {
		return this.movementStates == MovementStates.RIGHT;
	}

	public boolean isLeft() {
		return this.movementStates == MovementStates.LEFT;
	}

	public boolean isBackGroundEffecting()
	{
		return this.backGroundEffect != BackGroundEffect.NONE;
	}
	
	public BackGroundEffect getBackGroundEffect()
	{
		return this.backGroundEffect;
	}

	public void setBackGroundEffect(BackGroundEffect effect) {
		this.backGroundEffect = effect;
		
	}
	
	
	
}
