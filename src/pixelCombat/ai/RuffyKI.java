package pixelCombat.ai;

import pixelCombat.controller.GamePlayController;
import pixelCombat.enums.AttackStates;
import pixelCombat.model.Character;

public class RuffyKI extends AIManager{

	public static final int AIRSPECIALATTACK 	= 18;
	
	public RuffyKI(Character character, GamePlayController controller) {
		super(character, controller);
		this.priorities.add(1);
	}

	@Override
	public void checkMoreCases() {
		switch(actionId)
		{
		case AIRSPECIALATTACK:
				airSpecialAttack(AttackStates.isAirSpecialAttacking1);
				break;	
		default:
			break;		
		}
		
	}

	private void airSpecialAttack(AttackStates isairspecialattacking1) {
		if(!character.attackLogic.isAttacking()&& !character.statusLogic.canNotAttack())
		{
			faceToEnemy();
		}else
		{
			actionBufferTime = actionDelay;
			return;
		}
		GamePlayController.airSpecial(AttackStates.isAirSpecialAttacking1, character,"airSpecialAttack1");
		
	}

	@Override
	public void updateFurtherPriorities() {
		priorities.set(BASICATTACK23, 1);
		
	}

	@Override
	public void updatePatternsForSpecialAttackPriorities() {
		
		
		if(character.pos.distance(character.enemy.pos)> RECOMMENDMAXDISTANCE)
		{
			resetSpecs();
			priorities.set(SPECIALATTACK2, priorities.get(SPECIALATTACK2)+20);
			priorities.set(SPECIALATTACK5, priorities.get(SPECIALATTACK5)+10);
			priorities.set(SPECIALATTACK7, priorities.get(SPECIALATTACK7)+5);
		}
		else
		{
			resetSpecs();
			priorities.set(SPECIALATTACK4, priorities.get(SPECIALATTACK4)+10);
			priorities.set(SPECIALATTACK6, priorities.get(SPECIALATTACK6)+5);
		}
		
		if(character.enemy.statusLogic.isJumping() || character.enemy.statusLogic.isOnAir())
		{
			resetSpecs();
			priorities.set(SPECIALATTACK4, priorities.get(SPECIALATTACK4)+5);
			priorities.set(SPECIALATTACK3, priorities.get(SPECIALATTACK3)+10);
		}
		
		if(character.statusLogic.isJumping() && enemyIsNear())
		{
			resetSpecs();
			if(character.pos.y >= character.enemy.pos.y || character.enemy.statusLogic.isOnAir())
				priorities.set(AIRSPECIALATTACK, priorities.get(AIRSPECIALATTACK)+45);
			priorities.set(SPECIALATTACK4, priorities.get(SPECIALATTACK4)+25);
			priorities.set(SPECIALATTACK6, priorities.get(SPECIALATTACK6)+25);
			
			
		}
		
	}



	private boolean enemyIsNear() {
		return (character.pos.distance(character.enemy.pos) <= 3f);
	}

}
