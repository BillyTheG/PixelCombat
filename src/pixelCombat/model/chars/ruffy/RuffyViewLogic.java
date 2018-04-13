package pixelCombat.model.chars.ruffy;

import pixelCombat.enums.AttackStates;
import pixelCombat.model.Character;
import pixelCombat.model.ViewLogic;

public class RuffyViewLogic extends ViewLogic
{

	private int GigantoGatling = 32;

	public RuffyViewLogic(Character character) {
		super(character);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void changeFurtherImages(AttackStates attackStatus) {
		switch (character.attackLogic.getAttackStatus()) {
		case Gear2Transform:
			character.picManager.change(GEAR2TRANSFORM);
			break;
		case GigantoGatling:
			character.picManager.change(GigantoGatling );
			break;
		default:
			break;
		}
		
	}

}
