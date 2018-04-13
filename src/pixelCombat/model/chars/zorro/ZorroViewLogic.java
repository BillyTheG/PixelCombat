package pixelCombat.model.chars.zorro;

import pixelCombat.enums.AttackStates;
import pixelCombat.model.Character;
import pixelCombat.model.ViewLogic;

public class ZorroViewLogic extends ViewLogic
{

	private static final int YOKKOUDORI = 31;

	public ZorroViewLogic(Character character) {
		super(character);
	}

	@Override
	public void changeFurtherImages(AttackStates attackStatus) {
		switch (character.attackLogic.getAttackStatus()) {
		case isYokkouDoring:
			character.picManager.change(YOKKOUDORI );
			break;
		default:
			break;
		}	
	}

}
