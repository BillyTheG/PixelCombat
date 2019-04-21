package pixelCombat.ai;

import java.util.ArrayList;
import java.util.Random;

import pixelCombat.controller.GamePlayController;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.MovementStates;
import pixelCombat.model.Character;

public abstract class AIManager {

	private static final float 	maxPossiibleDelayTime = 0.1f;
	private Random 				random = new Random();
	protected GamePlayController 	controller;
	protected Character 			character;
	protected float 				actionDelay = 0.05f;
	protected float				actionBufferTime = 0f;
	
	private boolean 			previousDirection = false;
	protected int 				actionId;
	protected ArrayList<Integer>	priorities;
	public static final float RECOMMENDENERGY	= 50	;
	public static final float RECOMMENDMAXDISTANCE =0.75f;
	
	public static final int STAND 			= 0;
	public static final int MOVE 			= 1;
	public static final int JUMPING 		= 2;
	public static final int DASHING 		= 3;
	public static final int DEFENDING 		= 4;
	public static final int BASICATTACK 	= 5;
	public static final int BASICATTACK1 	= 6;
	public static final int BASICATTACK21 	= 7;
	public static final int BASICATTACK22 	= 8;
	public static final int BASICATTACK23 	= 9;
	public static final int JUMPATTACK 		= 10;
	public static final int SPECIALATTACK1 	= 11;
	public static final int SPECIALATTACK2 	= 12;
	public static final int SPECIALATTACK3 	= 13;
	public static final int SPECIALATTACK4 	= 14;
	public static final int SPECIALATTACK5 	= 15;
	public static final int SPECIALATTACK6 	= 16;
	public static final int SPECIALATTACK7 	= 17;

	public static final int MAXACTIONS 	   	= 18;

	
	
	public AIManager(Character character, GamePlayController controller)
	{
		this.character 	= character;
		this.controller = controller;
		
		initPrio();
	}
	
	private void initPrio() {
		this.priorities = new ArrayList<>();
		for(int i = 0; i<MAXACTIONS;i++)
			priorities.add(1);
		
	}

	public void update(float delta){
		
		if(character.enemy.statusLogic.isDead()|| controller.uncontrollable())
			return;
		
		
		actionBufferTime+= delta;
		
		if(actionBufferTime>= actionDelay)
		{
			
			if(character.statusLogic.isDefending())
				controller.defend(false, character);
			
			//character.calculateNewProbabilities();
			actionBufferTime = 0f;
			updatePriorities();
			actionId = ActionProbabilityHandler.computeActionSquareId(priorities);
			float randomOffset 	= random.nextFloat()*0.5f;
			
			actionDelay = (actionDelay+randomOffset) % maxPossiibleDelayTime;
			
		}
		
		doAction(delta);
		
		
	}

	private void doAction(float delta) {
		
		
		if(!character.statusLogic.isActive())
			return;
		
		
		switch(actionId)
		{
		case STAND:
			//stand idle
			stand();
			break;
		case MOVE:			
			moveToEnemy();
			break;
		case JUMPING:
			jump(delta);
			break;
		case DASHING:
			dash(delta);
			break;
		case DEFENDING:
			defend();
			break;
		case BASICATTACK:
			//stand idle
			basicAttack1();
			break;	
		case BASICATTACK1:
			//stand idle
			basicAttack2();
			break;		
		case BASICATTACK21:
			//stand idle
			basicAttack21();
			break;	
		case BASICATTACK22:
			//stand idle
			basicAttack22();
			break;		
		case BASICATTACK23:
			//stand idle
			basicAttack23();
			break;		
		case SPECIALATTACK1:
			//stand idle
			basicAttack3();
			break;	
		case SPECIALATTACK2:
			specialAttack(AttackStates.isSpecialAttacking1,"specialAttack2");
			break;
		case SPECIALATTACK3:
			specialAttack(AttackStates.isSpecialAttacking2,"specialAttack3");
			break;
		case SPECIALATTACK4:			
			specialAttack(AttackStates.isSpecialAttacking3,"specialAttack4");
			break;
		case SPECIALATTACK5:
			specialAttack(AttackStates.isSpecialAttacking4,"specialAttack5");
			break;
		case SPECIALATTACK6:
			specialAttack(AttackStates.isSpecialAttacking5,"specialAttack6");
			break;	
		case SPECIALATTACK7:
			specialAttack(AttackStates.isSpecialAttacking6,"specialAttack7");
			break;	
		default:
			checkMoreCases();
		break;		
		}
		
	}

	private void basicAttack23() {
		if(!character.attackLogic.isAttacking() && !character.statusLogic.isDashing())
		{
			faceToEnemy();
		}
		
		if(character.pos.distance(character.enemy.pos)> 5f)
		{
			actionBufferTime= actionDelay;
			return;
		}
		
		controller.basicAttack(character, AttackStates.isBasicAttacking23);
		
	}

	private void basicAttack22() {
		if(!character.attackLogic.isAttacking() && !character.statusLogic.isDashing())
		{
			faceToEnemy();
		}
		
		if(character.pos.distance(character.enemy.pos)> 5f)
		{
			actionBufferTime= actionDelay;
			return;
		}
		
		controller.basicAttack(character, AttackStates.isBasicAttacking22);
		
	}

	private void basicAttack21() {
		if(!character.attackLogic.isAttacking() && !character.statusLogic.isDashing())
		{
			faceToEnemy();
		}
		
		if(character.pos.distance(character.enemy.pos)> 5f)
		{
			actionBufferTime= actionDelay;
			return;
		}
		
		controller.basicAttack(character, AttackStates.isBasicAttacking21);
		
	}

	private void basicAttack3() {
		if(!character.attackLogic.isAttacking() && !character.statusLogic.isDashing())
		{
			faceToEnemy();
		}
		
		if(character.pos.distance(character.enemy.pos)> 5f)
		{
			actionBufferTime= actionDelay;
			return;
		}
		
		controller.basicAttack(character, AttackStates.isComboAttacking);
		
	}

	public abstract void checkMoreCases();

	private void defend() {
		if(!character.attackLogic.isAttacking() && !character.statusLogic.isDashing())
		{
			faceToEnemy();
		}
		
		
		controller.defend(true, character);
		
	}

	protected void specialAttack(AttackStates attackState, String attackStateString) {
		if(!character.attackLogic.isAttacking() && !character.statusLogic.isDashing())
		{
			faceToEnemy();
		}
		GamePlayController.specialAttack(character, attackState, attackStateString);
	}

	

	private void dash(float delta) {
		if(!character.attackLogic.isAttacking() && !character.statusLogic.isDashing())
			faceToEnemy();
		else
		{
			actionBufferTime = actionDelay;
			return;
		}
		controller.dash(character);
		character.physics.update(delta);
		
	}


	private void basicAttack1() {
		
		if(!character.attackLogic.isAttacking() && !character.statusLogic.isDashing())
		{
			faceToEnemy();
		}
		
		if(character.pos.distance(character.enemy.pos)> 5f)
		{
			actionBufferTime= actionDelay;
			return;
		}
		
		controller.basicAttack(character, AttackStates.isBasicAttacking1);
		
	}

	protected void faceToEnemy() {
		if(character.pos.x <= character.enemy.pos.x)
		{
			if(behindEnemy()) 
			{
				character.statusLogic.setMovementStates(MovementStates.RIGHT);	
				character.statusLogic.setActionStates(ActionStates.STAND);
				return;
			}
			
		}
		else
		if(behindEnemy())
		{
			character.statusLogic.setMovementStates(MovementStates.LEFT);	
			character.statusLogic.setActionStates(ActionStates.STAND);
		}
			
	}

	public boolean behindEnemy() {
		
		if(		character.pos.x <= character.enemy.pos.x && character.getDir() == -1.0f || 
				character.pos.x >= character.enemy.pos.x && character.getDir() == 1.0f)
				return true;
		return false;
	}
	
	private void basicAttack2() {
	
		if(!character.attackLogic.isAttacking() && !character.statusLogic.isDashing())
		{
			faceToEnemy();
		}
		
		if(character.pos.distance(character.enemy.pos)> 5f)
		{
			actionBufferTime= actionDelay;
			return;
		}
		
		controller.basicAttack(character, AttackStates.isBasicAttacking2);
		
	}
	
	
	private void stand() {
		//character.statusLogic.setActionStates(ActionStates.STAND);		
	}
	
	private void jump(float delta) {
		if(character.attackLogic.isAttacking() || character.statusLogic.isDashing())
		{
			actionBufferTime = actionDelay;
			return;
		}
		controller.jump(character);
		character.physics.update(delta);
		actionBufferTime= actionDelay;
	}

	private void moveToEnemy() {
		
		if(!character.attackLogic.isAttacking() && !character.statusLogic.canNotMove())
		{
			faceToEnemy();
		}
		else
		{
			actionBufferTime = actionDelay;
			return;
		}
			
		if(character.attackLogic.isAttacking())
			return;
		
		boolean hold = false;
		boolean enemyIsRight = (character.pos.x <= character.enemy.pos.x); 
		
		if(enemyIsRight)
			character.statusLogic.setMovementStates(MovementStates.RIGHT);
		else
			character.statusLogic.setMovementStates(MovementStates.LEFT);
		
		if(character.pos.distance(character.enemy.pos)<= RECOMMENDMAXDISTANCE)
		{
			actionBufferTime = actionDelay;
			return;
		}
		
		if(previousDirection != enemyIsRight)
			hold = false;
		else
			hold = true;
		
		controller.move(character, hold, character.statusLogic.isRight());
		previousDirection = enemyIsRight;
		
	}
	
	public void updatePriorities(){
	
		for(int i=0; i< priorities.size();i++)
			priorities.set(i,1);
		
		//	checkDistance
		// -Move if Character is without range	
		if(character.pos.distance(character.enemy.pos)> RECOMMENDMAXDISTANCE)
		{
			priorities.set(MOVE, priorities.get(MOVE)+10);
			priorities.set(DASHING, priorities.get(DASHING)+2);
		}
		else // Character is within range
		{
			int factor 	= (character.getActualMagicpoints() <= RECOMMENDENERGY) ? 30 :0;
			priorities.set(MOVE, 1);
			priorities.set(BASICATTACK, priorities.get(BASICATTACK)+(10+factor));
			priorities.set(BASICATTACK1, priorities.get(BASICATTACK1)+(10+factor));
			priorities.set(BASICATTACK21, priorities.get(BASICATTACK21)+(10+factor));
			priorities.set(BASICATTACK22, priorities.get(BASICATTACK22)+(10+factor));
			priorities.set(BASICATTACK23, priorities.get(BASICATTACK23)+(10+factor));
		}
		
		
		
		if(character.enemy.statusLogic.isOnAir() )
			priorities.set(JUMPING, priorities.get(JUMPING)+10);
		
		//  checkEnergy
		// -Use SpecialAttack if sufficient Energy
		
		if(character.getActualMagicpoints()>= RECOMMENDENERGY)
		{
			priorities.set(SPECIALATTACK2, priorities.get(SPECIALATTACK2)+2);
			priorities.set(SPECIALATTACK3, priorities.get(SPECIALATTACK3)+2);
			priorities.set(SPECIALATTACK4, priorities.get(SPECIALATTACK4)+2);
			priorities.set(SPECIALATTACK5, priorities.get(SPECIALATTACK5)+2);
			priorities.set(SPECIALATTACK6, priorities.get(SPECIALATTACK6)+2);
			priorities.set(SPECIALATTACK7, priorities.get(SPECIALATTACK7)+2);
			updatePatternsForSpecialAttackPriorities();
		}
		else
		{
			int factor 	= (character.getActualMagicpoints() <= RECOMMENDENERGY) ? 3 :0;
			priorities.set(BASICATTACK, priorities.get(BASICATTACK)+(5+factor));
			priorities.set(BASICATTACK1, priorities.get(BASICATTACK1)+(5+factor));
			priorities.set(BASICATTACK21, priorities.get(BASICATTACK21)+(5+factor));
			priorities.set(BASICATTACK22, priorities.get(BASICATTACK22)+(5+factor));
			priorities.set(BASICATTACK23, priorities.get(BASICATTACK23)+(3+factor));
			priorities.set(SPECIALATTACK1, priorities.get(SPECIALATTACK1)+3 + factor);
			
			
		}
		// check Defend
		if(character.enemy.attackLogic.isAttacking())
		{
			priorities.set(JUMPING, priorities.get(JUMPING)+5);
			priorities.set(DASHING, priorities.get(DASHING)+10);
			priorities.set(DEFENDING, priorities.get(DEFENDING)+15);
		}
		
		if(character.enemy.statusLogic.isBlinking() || character.enemy.statusLogic.isInvincible())
		{
			priorities.set(STAND, 40);
			priorities.set(BASICATTACK, 1);
			priorities.set(BASICATTACK1,1);
			priorities.set(BASICATTACK21,1);
			priorities.set(BASICATTACK22, 1);
			priorities.set(BASICATTACK23, 1);
			resetSpecs();
		}
		updateFurtherPriorities();
		
	}

	protected void resetSpecs() {
		priorities.set(SPECIALATTACK1,1);
		priorities.set(SPECIALATTACK2,1);
		priorities.set(SPECIALATTACK3, 1);
		priorities.set(SPECIALATTACK4, 1);
		priorities.set(SPECIALATTACK5, 1);
		priorities.set(SPECIALATTACK6, 1);
		priorities.set(SPECIALATTACK7, 1);
	}

	public abstract void updatePatternsForSpecialAttackPriorities() ;

	public abstract void updateFurtherPriorities();
	
	
	
	
}
