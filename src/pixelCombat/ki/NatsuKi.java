package pixelCombat.ki;

import pixelCombat.controller.GamePlayController;
import pixelCombat.model.Character;

public class NatsuKi extends AIManager{

	public NatsuKi(Character character, GamePlayController controller) {
		super(character, controller);
	}

	@Override
	public void checkMoreCases() {
	}

	@Override
	public void updateFurtherPriorities() {
			
	}

	@Override
	public void updatePatternsForSpecialAttackPriorities() {
		
		if(character.pos.distance(character.enemy.pos)> RECOMMENDMAXDISTANCE)
		{
			priorities.set(SPECIALATTACK2, priorities.get(SPECIALATTACK2)+5);
			priorities.set(SPECIALATTACK5, priorities.get(SPECIALATTACK5)+10);
			priorities.set(SPECIALATTACK7, priorities.get(SPECIALATTACK7)+5);
		}
		else
		{
			priorities.set(SPECIALATTACK4, priorities.get(SPECIALATTACK4)+10);
			priorities.set(SPECIALATTACK6, priorities.get(SPECIALATTACK6)+5);
		}
		
		if(character.enemy.statusLogic.isJumping())
		{
			priorities.set(SPECIALATTACK6, priorities.get(SPECIALATTACK6)+10);
			priorities.set(SPECIALATTACK4, priorities.get(SPECIALATTACK4)+5);
			priorities.set(SPECIALATTACK3, priorities.get(SPECIALATTACK3)+5);
		}
		
		
	}

}
