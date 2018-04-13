package pixelCombat.projectiles;

import pixelCombat.enums.MovementStates;
import pixelCombat.enums.ProjectileActionStates;
import pixelCombat.enums.ProjectileGlobalStates;


public class ProjectileStatusLogic {

	// Movement
	protected boolean back = false;
	protected boolean forward = false;
	
	// Basic
	protected Projectile projectile;
	protected MovementStates movementStates = MovementStates.RIGHT;
	protected ProjectileGlobalStates globalStates = ProjectileGlobalStates.ALIVE;
	private ProjectileActionStates actionStates = ProjectileActionStates.CREATION;

	public ProjectileStatusLogic(Projectile projectile) {
		this.projectile = projectile;
	}

	public void checkUpdate()
	{	
		switch (globalStates) {
		
		case ALIVE:
			
			projectile.Active();
			break;
		case DEAD:
			break;
		default:
			break;
		}
	}
	
	public void swapDirection()
	{
		if(movementStates == MovementStates.LEFT)
			movementStates = MovementStates.RIGHT;
		else
			movementStates = MovementStates.LEFT;
	}
	
	// Logic Questions

	public boolean canNotMove() 
	{
		return  isDead()|| isExploding() || 
				isInCreation() || isSpecialEffecting();	
	}

	public boolean canNotSpecialEffect() {
		return  isDead()|| isExploding() || 
				isInCreation() || isSpecialEffecting();	
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	


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



	public void setActionStatus(ProjectileActionStates actionStatus) 
	{
		this.setActionStates(actionStatus);
		projectile.viewLogic.update();
	}
	

	public void setGlobalStatus(ProjectileGlobalStates globalStatus) 
	{
		this.globalStates = globalStatus;
		projectile.viewLogic.update();
	}



	public MovementStates getMovementStates() {
		return movementStates;
	}



	public void setMovementStates(MovementStates movementStates) {
		this.movementStates = movementStates;
	}

	
	
	
	
//-------------------------------------------------------------------------------------


	public boolean isRight() {
		return this.movementStates == MovementStates.RIGHT;
	}

	public boolean isLeft() {
		return this.movementStates == MovementStates.LEFT;
	}

	public boolean isAlive() {
		return this.globalStates == ProjectileGlobalStates.ALIVE ;
	}
	public boolean isDead() {
		return this.globalStates == ProjectileGlobalStates.DEAD;
	}
	
	
	public boolean isExploding() {
		return this.getActionStates() == ProjectileActionStates.EXPLOSION;
	}
	public boolean isInCreation() {
		return this.getActionStates() == ProjectileActionStates.CREATION;
	}
	public boolean isMoving() {
		return this.getActionStates() == ProjectileActionStates.MOVE;
	}
	
	public boolean isSpecialEffecting1() {
		return this.getActionStates() == ProjectileActionStates.SPECIALEFFECT1;
	}
	
	public boolean isSpecialEffecting2() {
		return this.getActionStates() == ProjectileActionStates.SPECIALEFFECT2;
	}
	
	public boolean isSpecialEffecting() {
		return this.getActionStates() == ProjectileActionStates.SPECIALEFFECT2 ||
			   this.getActionStates() == ProjectileActionStates.SPECIALEFFECT1	;		
	}

	public ProjectileActionStates getActionStates() {
		return actionStates;
	}

	public void setActionStates(ProjectileActionStates actionStates) {
		this.actionStates = actionStates;
		projectile.viewLogic.update();
	}
	
}
