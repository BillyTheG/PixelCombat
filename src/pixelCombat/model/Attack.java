package pixelCombat.model;

import pixelCombat.enums.AttackStates;
import pixelCombat.enums.MovementStates;


/**
 * The class, that processes the attacks of all Characters
 * Each Character will have a certain number of Attacks. 
 * 
 * 
 * @author BillyG
 *
 */
public abstract class Attack {

	private Character user;
	private int id;
	private float requiredEnergy;
	
	
	public Attack(Character user, int id)
	{
		this.user 	= 	user;
		this.id 	= 	id;
	}
	
	/**
	 * This method proceeds the events during the attack phase.
	 * For each Frame in an Attack there is a different function 
	 * involved depending on the attacks specialty.
	 * 
	 */
	public abstract void process();
	
	/**
	 *  
	 * This method is meant for validating the hit on the enemy. If the enemy
	 * was able to block this attack, the procedure will not do anything. If the
	 * hit was successful, the enemy will be charged with lifepoint reduction, status change
	 * and etc.
	 * 
	 */
	public abstract void checkContent();

	/**
	 *  
	 * This method does the final check, whether the attack reached the final
	 * frame and the animation time has been lapsed.
	 * 
	 */
	public abstract void checkFinished();
	
	/**
	 * 	 
	 * @return the attacking state variable
	 */
	public abstract boolean isAttacking();
	
	
	/**
	 * Completes the procedure for checking the result of the attack  
	 * 
	 */
	public void check()
	{
		if(user.checkDefender(user.enemy) && isAttacking())
		{
			if (user.statusLogic.isRight()) {
				user.enemy.statusLogic.setMovementStates(MovementStates.LEFT);
			} else {
				user.enemy.statusLogic.setMovementStates(MovementStates.RIGHT);
			}
			checkContent();
		}
		if(user.animationTimeRunnedOut() && isAttacking())
		{
			checkFinished();
			user.attackLogic.setAttackStatus(AttackStates.notAttacking);
			user.statusLogic.setAHitDelay(false);
			user.setSwitcher(true);
			user.attackOnAir = false;
		}
	}
	
		
	
	/**
	 * @return the user
	 */
	public Character getUser() {
		return user;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public abstract void resetStats();

	public float getRequiredEnergy() {
		return requiredEnergy;
	}

	public void setRequiredEnergy(float requiredEnergy) {
		this.requiredEnergy = requiredEnergy;
	}
	
	

}
