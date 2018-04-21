package pixelCombat.model;

import pixelCombat.enums.AttackStates;

public abstract class ViewLogic {

	// Bildsequenzen als Stütze
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
    
	protected Character character;
	private int RUNATTACK1 = 26;
	private int RUNATTACK2 = 27;
	public final int JUMPRECOVER = 28;
	public final int AIRDEFENSE = 29;

	public final int AIR_SPECIALATTACK1 = 30;
	
	public final int KNOCKBACKRECOVER = 31;
	public final int MAX_STANDARD_SPRITES = KNOCKBACKRECOVER;
	
	
	public ViewLogic(Character character) {
		this.character = character;
	}

	public void update() {

		// Character ist aktiv
		if (character.statusLogic.isActive() || character.statusLogic.isBlinking()) {

			// muss noch geändert werden
			if (character.isJumpAttacking()) {
				character.picManager.change(JUMPATTACK);
				return;
			} else {
				if (character.attackLogic.isAttacking()) {

					switch (character.attackLogic.getAttackStatus()) {
					case isBasicAttacking1:
						character.picManager.change(BASICATTACK);
						break;
					case isBasicAttacking2:
						character.picManager.change(BASICATTACK1);
						break;
					case isBasicAttacking21:
						character.picManager.change(BASICATTACK21);
						break;
					case isBasicAttacking22:
						character.picManager.change(BASICATTACK22);
						break;
					case isBasicAttacking23:
						character.picManager.change(BASICATTACK23);
						break;
					case isComboAttacking:
						character.picManager.change(SPECIALATTACK1);
						break;
					case isSpecialAttacking1:
						character.picManager.change(SPECIALATTACK2);
						break;
					case isSpecialAttacking2:
						character.picManager.change(SPECIALATTACK3);
						break;
					case isSpecialAttacking3:
						character.picManager.change(SPECIALATTACK4);
						break;
					case isSpecialAttacking4:
						character.picManager.change(SPECIALATTACK5);
						break;
					case isSpecialAttacking5:
						character.picManager.change(SPECIALATTACK6);
						break;
					case isSpecialAttacking6:
						character.picManager.change(SPECIALATTACK7);
						break;
					case isRunAttacking1:
						character.picManager.change(RUNATTACK1);
						break;
					case isRunAttacking2:
						character.picManager.change(RUNATTACK2);
						break;
					case isAirSpecialAttacking1:
						character.picManager.change(AIR_SPECIALATTACK1);
						break;
					default:
						changeFurtherImages(character.attackLogic.getAttackStatus());
						break;
					}
					// Bildreihe gewechselt
					return;
				} else {

					switch (character.statusLogic.getActionStates()) {
					case MOVE:
						character.picManager.change(MOVE);
						break;
					case STAND:
						character.picManager.change(STAND);
						break;
					case WIN:
						character.picManager.change(WIN);
						break;
					case INTRO:
						character.picManager.change(INTRO);
						break;
					case JUMP:
						character.picManager.change(JUMPING);
						break;
					case JUMP_RECOVER:
						character.picManager.change(JUMPRECOVER);
						break;
					case DEFENDING:
						character.picManager.change(DEFENDING);
						break;
					case AIR_DEFENDING:
						character.picManager.change(AIRDEFENSE);
						break;
					case DASHING:
						character.picManager.change(DASHING);
						break;				
					default:
						break;
					}
					return;
				}

			}

		} else {

			switch (character.statusLogic.getGlobalStatus()) {
			case INVINCIBLE:
				character.picManager.change(KNOCKEDOUT);
				break;
			case KNOCKBACK:
				character.picManager.change(KNOCKBACK);
				break;
			case KNOCKBACKRECOVER:
				character.picManager.change(KNOCKBACKRECOVER);
				break;	
			case DISABLED:
				character.picManager.change(ISHIT);
				break;
			case DEAD:
				character.picManager.change(DEAD);
				break;
			default:
				break;

			}
			return;
		}
	}

	public abstract void changeFurtherImages(AttackStates attackStatus);

}
