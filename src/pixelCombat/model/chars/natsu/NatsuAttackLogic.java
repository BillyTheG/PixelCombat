package pixelCombat.model.chars.natsu;

import pixelCombat.model.AttackLogic;
import pixelCombat.model.Character;

public class NatsuAttackLogic extends AttackLogic {

	public NatsuAttackLogic(Character character) {
		super(character);
	}

	@Override
	public boolean furtherAttacks() {
		return false;
	}

	

}
