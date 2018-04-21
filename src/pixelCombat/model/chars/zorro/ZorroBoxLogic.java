package pixelCombat.model.chars.zorro;

import pixelCombat.model.BoxLogic;
import pixelCombat.model.Character;
import pixelCombat.utils.Console;

public class ZorroBoxLogic extends BoxLogic{

	
	private final int YOKKOUDORI = MAX_STANDARD_SPRITES +1;

	public ZorroBoxLogic(Character character, Console console) {
		super(character,console);
		
	}

	@Override
	public void loadFurtherBoxes(int currentAnimation2) {
		switch (currentAnimation2) {
		case YOKKOUDORI :
			updateBoxSeq(YOKKOUDORI,"yokkoudori");
			break;
		default:
			break;
		}
	}

	
	
}
