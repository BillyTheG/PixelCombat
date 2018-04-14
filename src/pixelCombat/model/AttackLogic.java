package pixelCombat.model;

import pixelCombat.enums.AttackStates;

public abstract class AttackLogic {

	private AttackStates attackStatus = AttackStates.notAttacking;
	protected Character character;

	public AttackLogic(Character character) {
		this.character = character;
	}

	// General Attacks

	public boolean isAttacking() {

		if (	   attackStatus == AttackStates.isComboAttacking 
				|| attackStatus == AttackStates.isSpecialAttacking1
				|| attackStatus == AttackStates.isSpecialAttacking2 
				|| attackStatus == AttackStates.isSpecialAttacking3
				|| attackStatus == AttackStates.isSpecialAttacking4 
				|| attackStatus == AttackStates.isSpecialAttacking5
				|| attackStatus == AttackStates.isSpecialAttacking6
				|| attackStatus == AttackStates.isBasicAttacking21
				|| attackStatus == AttackStates.isBasicAttacking22 
				|| attackStatus == AttackStates.isBasicAttacking23
				|| attackStatus == AttackStates.isBasicAttacking1 
				|| attackStatus == AttackStates.isBasicAttacking2
				|| attackStatus == AttackStates.isRunAttacking1 
				|| attackStatus == AttackStates.isRunAttacking2
				|| attackStatus == AttackStates.isAirSpecialAttacking1 
				|| character.isJumpAttacking()
				|| furtherAttacks()
				)
			return true;

		return false;
	}

	public abstract boolean furtherAttacks() ;
	
	// Basic Attacks
	public boolean isBasicAttacking() {

		if (attackStatus == AttackStates.isBasicAttacking1 || attackStatus == AttackStates.isBasicAttacking2
				|| attackStatus == AttackStates.isBasicAttacking21 || attackStatus == AttackStates.isBasicAttacking22
				|| attackStatus == AttackStates.isBasicAttacking23)
			return true;

		return false;
	}
	
	

	public boolean isBasicAttacking1() {

		if (attackStatus == AttackStates.isBasicAttacking1)
			return true;

		return false;
	}

	public boolean isBasicAttacking2() {

		if (attackStatus == AttackStates.isBasicAttacking2)
			return true;

		return false;
	}
	public boolean isBasicAttacking21() {

		if (attackStatus == AttackStates.isBasicAttacking21)
			return true;

		return false;
	}
	public boolean isBasicAttacking22() {

		if (attackStatus == AttackStates.isBasicAttacking22)
			return true;

		return false;
	}
	public boolean isBasicAttacking23() {

		if (attackStatus == AttackStates.isBasicAttacking23)
			return true;

		return false;
	}

	// SpecialAttacks

	public boolean isSpecialAttacking() {

		if (attackStatus == AttackStates.isComboAttacking || attackStatus == AttackStates.isSpecialAttacking1
				|| attackStatus == AttackStates.isSpecialAttacking2 || attackStatus == AttackStates.isSpecialAttacking3
				|| attackStatus == AttackStates.isSpecialAttacking4 || attackStatus == AttackStates.isSpecialAttacking5
				|| attackStatus == AttackStates.isSpecialAttacking6
				|| attackStatus == AttackStates.Gear2Transform
				|| attackStatus == AttackStates.GigantoGatling
				|| attackStatus == AttackStates.isYokkouDoring)
			return true;

		return false;
	}

	public boolean isSpecialAttacking1() {

		if (attackStatus == AttackStates.isComboAttacking)
			return true;

		return false;
	}

	public boolean isSpecialAttacking2() {

		if (attackStatus == AttackStates.isSpecialAttacking1)
			return true;

		return false;
	}

	public boolean isSpecialAttacking3() {

		if (attackStatus == AttackStates.isSpecialAttacking2)
			return true;

		return false;
	}

	public boolean isSpecialAttacking4() {

		if (attackStatus == AttackStates.isSpecialAttacking3)
			return true;

		return false;
	}

	public boolean isSpecialAttacking5() {

		if (attackStatus == AttackStates.isSpecialAttacking4)
			return true;

		return false;
	}

	public boolean isSpecialAttacking6() {

		if (attackStatus == AttackStates.isSpecialAttacking5)
			return true;

		return false;
	}

	public boolean isSpecialAttacking7() {

		if (attackStatus == AttackStates.isSpecialAttacking6)
			return true;

		return false;
	}

	public boolean isRunAttacking() {

		if (attackStatus == AttackStates.isRunAttacking1 || attackStatus == AttackStates.isRunAttacking2
				)
			return true;

		return false;
	}
	
	public boolean isRunAttacking1() {

		if (attackStatus == AttackStates.isRunAttacking1)
			return true;

		return false;
	}
	
	public boolean isRunAttacking2() {

		if (attackStatus == AttackStates.isRunAttacking2)
			return true;

		return false;
	}
	
	
	public boolean isAirSpecialAttacking() {

		if (attackStatus == AttackStates.isAirSpecialAttacking1 || attackStatus == AttackStates.isYokkouDoring )
			return true;

		return false;
	}
	
	public boolean isAirSpecialAttacking1() {

		if (attackStatus == AttackStates.isAirSpecialAttacking1)
			return true;

		return false;
	}
	
	public AttackStates getAttackStatus() {
		return attackStatus;
	}

	public void setAttackStatus(AttackStates attackStatus) {
		this.attackStatus = attackStatus;
		character.viewLogic.update();
	};

}
