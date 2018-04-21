package pixelCombat.model.chars.ruffy;

import pixelCombat.model.BoxLogic;
import pixelCombat.model.Character;
import pixelCombat.utils.Console;

public class RuffyBoxLogic extends BoxLogic{

	public 	final int GEAR2TRANSFORM = MAX_STANDARD_SPRITES+1;
	private final int GigantoGatling = MAX_STANDARD_SPRITES+2;

	public RuffyBoxLogic(Character character, Console console) {
		super(character,console);
	}

	@Override
	public void loadFurtherBoxes(int currentAnimation2) {
		switch (currentAnimation2) {
		case GEAR2TRANSFORM:
			updateBoxSeq(GEAR2TRANSFORM,"gear2transform");
			break;
		case GigantoGatling :
			updateBoxSeq(GigantoGatling,"gigantoGatling");
			break;
		default:
			break;
		}
	}

	
	
}
