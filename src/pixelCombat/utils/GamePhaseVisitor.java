package pixelCombat.utils;

import pixelCombat.gamephase.CharacterSelection;
import pixelCombat.gamephase.Credit;
import pixelCombat.gamephase.GamePlay;
import pixelCombat.gamephase.Help;
import pixelCombat.gamephase.Loading;
import pixelCombat.gamephase.MainMenue;
import pixelCombat.gamephase.MapSelection;
import pixelCombat.gamephase.Summary;
import pixelCombat.gamephase.Title;

public interface GamePhaseVisitor 
{
	public GamePhase visit(Title element);
	public GamePhase visit(MainMenue element);
	public GamePhase visit(CharacterSelection element);
	public GamePhase visit(MapSelection element);
	public GamePhase visit(GamePlay element);
	public GamePhase visit(Summary element);
	public GamePhase visit(Loading loading);
	
	public GamePhase exit(Loading loading);
	public GamePhase exit(MainMenue element);
	public GamePhase exit(CharacterSelection element);
	public GamePhase exit(MapSelection element);
	public GamePhase exit(GamePlay element);
	public GamePhase exit(Credit element);
	public GamePhase exit(Help element);
	
}
