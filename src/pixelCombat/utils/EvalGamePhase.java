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
import pixelCombat.stage.ConsoleStage;
import pixelCombat.stage.GameStage;

/**
 * <b>Anleitung !!!!:</b> 
 * <br>
 * - Zuerst in GameEngine alle Concrets of Gamephase definieren<br>
 * - Danach ‹berg‰nge zwischen Phasen sicherstellen <br>
 * - Anschlieﬂend die visits und exists definieren<br>
 * - accept Methode in GamePhase Concrets mit boolean Parameter (forward:= true, back:= false)<br>
 * - [Title und Gameplay sind speziell (Kein Back/Forward)]<br>
 * - GamePlayView s‰ubern<br>
 * - PXMap in Handler umwandeln und neue Klasse Map mit Attributen definieren<br>
 * 
 * 
 * Info:<br>
 * accept Methode wird durch Enter oder BackSlash aufgerufen<br>
 * 
 * @author BillyG
 *
 */
public class EvalGamePhase implements GamePhaseVisitor {

	private GameEngine 		engine;
	
	public EvalGamePhase(GameEngine engine, GameStage gameStage, ConsoleStage consoleStage)	{
		this.engine = engine;
	}
	
	
	@Override
	public GamePhase visit(Title element) 
	{		
		return engine.mainMenue;
	}

	@Override
	public GamePhase visit(MainMenue element){
		switch(element.getMenuPoint())
		{
		case ARCADE:
			return engine.mainMenue;
		case CREDIT:
			return engine.credit;
		case HELP:
			return engine.help;
		case KI:
			engine.characterSelection.setVsAi(true);
			return engine.characterSelection;
		case PLAYER:
			engine.characterSelection.setVsAi(false);
			return engine.characterSelection;
		default:
			return engine.mainMenue;
		}		
		
	}

	@Override
	public GamePhase visit(CharacterSelection element){
		
		return engine.mapSelection;
	}

	@Override
	public GamePhase visit(MapSelection element) 
	{
		engine.loading.init(false);		
		Thread thread = new Thread(new Runnable(){

			@Override
			public void run() {
				engine.gamePlay.init();				
			}
			
		});
		thread.start();
		return engine.loading;
	}

	@Override
	public GamePhase visit(GamePlay element){
		element.reset();	
		engine.title.reset();
		return engine.title;
	}
	
	@Override
	public GamePhase visit(Summary element) {
		return engine.mainMenue;
	}

	
	
	@Override
	public GamePhase exit(MainMenue element){
		element.reset();	
		engine.title.reset();
		return engine.title;
	}


	@Override
	public GamePhase exit(CharacterSelection element){
		element.reset();	
		engine.mainMenue.reset();
		return engine.mainMenue;
	}


	@Override
	public GamePhase exit(MapSelection element) 
	{		
		element.reset();		
		engine.characterSelection.reset();
		return engine.characterSelection;
	}


	@Override
	public GamePhase exit(GamePlay element) 
	{		
		engine.summary.init(engine.getGameCanvas(), element.getWinnerClassName(), element.hasPlayer1Won());
		return engine.summary;
	}

	@Override
	public GamePhase exit(Credit element) {
		element.reset();
		return engine.mainMenue;
	}

	@Override
	public GamePhase exit(Help element) {
		element.reset();	
		return engine.mainMenue;
	}

	@Override
	public GamePhase visit(Loading loading) {
		
		return engine.gamePlay;
	}

	@Override
	public GamePhase exit(Loading loading) {
		return engine.title;
	}

	
}
