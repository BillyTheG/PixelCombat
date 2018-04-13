package pixelCombat.utils;

import pixelCombat.gamephase.CharacterSelection;
import pixelCombat.gamephase.Credit;
import pixelCombat.gamephase.GamePlay;
import pixelCombat.gamephase.Help;
import pixelCombat.gamephase.MainMenue;
import pixelCombat.gamephase.MapSelection;
import pixelCombat.gamephase.Summary;
import pixelCombat.gamephase.Title;
import pixelCombat.gamephase.gamephaseelement.ContentListener;
import pixelCombat.model.PXMapHandler;

public class GamePhaseLoader implements Runnable {

	private GameEngine gameEngine;
	
	
	public GamePhaseLoader(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		
	}
	
	
	@Override
	public void run() {

		float progress						= 0;
		float delta							= 1f/11f * 100;
		
		gameEngine.contentListener			= new ContentListener();
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);
		//System.out.println(progress);
		gameEngine.arena 					= new PXMapHandler(gameEngine.contentListener,gameEngine.console,gameEngine);
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);
		//System.out.println(progress);
		gameEngine.title 				= new Title(gameEngine,gameEngine.getGameCanvas(),gameEngine.console);
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);
		//System.out.println(progress);		
		gameEngine.mainMenue 				= new MainMenue(gameEngine,gameEngine.getGameCanvas(),gameEngine.console);
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);
		//System.out.println(progress);
		gameEngine.characterSelection 		= new CharacterSelection(gameEngine,gameEngine.getGameCanvas(),gameEngine.contentListener,gameEngine.console);
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);
		//System.out.println(progress);
		gameEngine.mapSelection 			= new MapSelection(gameEngine,gameEngine.getGameCanvas(),gameEngine.contentListener,gameEngine.console);
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);
		//System.out.println(progress);
		gameEngine.gamePlay 				= new GamePlay(gameEngine,gameEngine.getGameCanvas(),gameEngine.arena,gameEngine.contentListener,gameEngine.console);
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);
		gameEngine.setKomboHandler(new KomboHandler(gameEngine));
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);	
		//System.out.println(progress);
		gameEngine.credit 					= new Credit(gameEngine,gameEngine.getGameCanvas(),gameEngine.console);
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);
		//System.out.println(progress);
		gameEngine.help 					= new Help(gameEngine,gameEngine.getGameCanvas(),gameEngine.console);
		progress+= delta;
		gameEngine.loading.setLoading((int)progress);
		//System.out.println(progress);
		gameEngine.summary 					= new Summary(gameEngine, gameEngine.getGameCanvas(), gameEngine.console);
		progress= 100;
		//System.out.println(progress);
		
		gameEngine.loading.setLoading((int)progress);
		
	}

}
