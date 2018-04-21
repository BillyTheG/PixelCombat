package pixelCombat.ki;

import pixelCombat.controller.GamePlayController;
import pixelCombat.enums.AttackStates;
import pixelCombat.model.Character;

public class ZorroKI extends AIManager{

	public static final int AIRSPECIALATTACK 	= 18;
	public static final int YOKKOUDORI		 	= 19;
	
	public ZorroKI(Character character, GamePlayController controller) {
		super(character, controller);
		this.priorities.add(1);
		this.priorities.add(1);
	}

	@Override
	public void checkMoreCases() {
		switch(actionId)
		{
		case AIRSPECIALATTACK:
				airSpecialAttack(AttackStates.isAirSpecialAttacking1,"airSpecialAttack1");
				break;	
		case YOKKOUDORI:
			airSpecialAttack(AttackStates.isYokkouDoring,"yokkoudori");
			break;				
		default:
			break;		
		}
	}

	private void airSpecialAttack(AttackStates isairspecialattacking1,String input_seq) {
		if(!character.attackLogic.isAttacking() && !character.statusLogic.canNotAttack())
		{
			faceToEnemy();
		}
		else
		{
			actionBufferTime = actionDelay;
			return;
		}
		GamePlayController.airSpecial(isairspecialattacking1, character,input_seq);
		
	}
	
	@Override
	public void updateFurtherPriorities() {
			
	}

	@Override
	public void updatePatternsForSpecialAttackPriorities() {
		
		if(character.pos.distance(character.enemy.pos)> RECOMMENDMAXDISTANCE)
		{
			resetSpecs();
			if(character.getActualMagicpoints() >= character.attacks.get("specialAttack2").getRequiredEnergy())
			{
				priorities.set(SPECIALATTACK2, priorities.get(SPECIALATTACK2)+40);
			}
			if(character.getActualMagicpoints()>= character.attacks.get("specialAttack5").getRequiredEnergy())
				priorities.set(SPECIALATTACK5, priorities.get(SPECIALATTACK5)+30);
			priorities.set(SPECIALATTACK4, priorities.get(SPECIALATTACK4)+15);
			priorities.set(SPECIALATTACK7, priorities.get(SPECIALATTACK7)+20);
		}
		else
		{
			resetSpecs();
			priorities.set(SPECIALATTACK3, priorities.get(SPECIALATTACK3)+40);
			priorities.set(SPECIALATTACK6, priorities.get(SPECIALATTACK6)+65);
		}
		
		if(character.enemy.statusLogic.isJumping() || character.enemy.statusLogic.isOnAir())
		{
			resetSpecs();
			if(character.statusLogic.isJumping() && enemyIsNear())
				priorities.set(AIRSPECIALATTACK, priorities.get(AIRSPECIALATTACK)+40);
			
			priorities.set(SPECIALATTACK4, priorities.get(SPECIALATTACK4)+25);
			priorities.set(SPECIALATTACK3, priorities.get(SPECIALATTACK3)+25);
		}
		
		if(character.statusLogic.isJumping() && character.pos.distance(character.enemy.pos)< 8f)
		{
			resetSpecs();
			if(character.getActualMagicpoints()>= character.attacks.get("yokkoudori").getRequiredEnergy())
				priorities.set(YOKKOUDORI, priorities.get(YOKKOUDORI)+100);	
			
		}
	}
	
	private boolean enemyIsNear() {
		return (character.pos.distance(character.enemy.pos) <= 4f);
	}
}
