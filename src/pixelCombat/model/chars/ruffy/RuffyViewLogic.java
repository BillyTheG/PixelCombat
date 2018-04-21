package pixelCombat.model.chars.ruffy;

import pixelCombat.enums.AttackStates;
import pixelCombat.model.Character;
import pixelCombat.model.ViewLogic;

public class RuffyViewLogic extends ViewLogic
{
	public 	final int GEAR2TRANSFORM = MAX_STANDARD_SPRITES+1;
	public  final int GigantoGatling = MAX_STANDARD_SPRITES+2;

	public RuffyViewLogic(Character character) {
		super(character);
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
