package pixelCombat.model.chars.zorro;

import pixelCombat.enums.AttackStates;
import pixelCombat.model.AttackLogic;
import pixelCombat.model.chars.Zorro;

public class ZorroAttackLogic extends AttackLogic {

	
	//Specials's	
	
	
	public ZorroAttackLogic(Zorro character) {
		super(character);
		this.character = character;
		
	}

	@Override
	public boolean furtherAttacks() {
		return isYokkouDoring();
	}

	public boolean isYokkouDoring() {
		return this.getAttackStatus() == AttackStates.isYokkouDoring;
	}

}
