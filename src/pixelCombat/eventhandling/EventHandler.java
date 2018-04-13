package pixelCombat.eventhandling;

import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import pixelCombat.artworks.ArtWork;
import pixelCombat.artworks.Fight;
import pixelCombat.artworks.Round1;
import pixelCombat.artworks.Round2;
import pixelCombat.artworks.Round3;
import pixelCombat.controller.Controller;
import pixelCombat.controller.GamePlayController;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GameEvent;
import pixelCombat.model.Character;
import pixelCombat.model.PXMapHandler;
import pixelCombat.model.PXObject;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.gamephases.GamePlayView;
/**
 * 
 * @author bilal_000
 *
 */
public class EventHandler 
{
	public static final float IDLETIME = 1.f;
	private float timeBuffer = 0;
	private GameEvent event;
	private GameEngine engine;
	private PXMapHandler arena;
	private GamePlayController controller;
	private Character player1;
	private Character player2;
	private boolean start = false;
	private boolean player1ready = false;
	private boolean player2ready = false;
	private boolean freezeready = false;
	public  boolean nextRound = false;
	
	//Round 1, 2, 3  + Fight!
	public Round1 round1 = new Round1();
	public Round2 round2 = new Round2();
	public Round3 round3 = new Round3();
	public Fight  fight  = new Fight();
	private Map<Integer,ArtWork> rounds = new HashMap<>();
	private boolean beginFight;
	
	
	
	/**
	 * 
	 * @param engine
	 */
	public EventHandler(GameEngine engine, PXMapHandler arena)
	{
		this.engine = engine;
		this.arena = arena;
		init();
	}

	/**
	 * 
	 * @param controller
	 */
	public EventHandler(Controller controller, PXMapHandler arena)
	{
		this.controller = (GamePlayController) controller;
		this.arena = arena;
		init();
	}
	
	public EventHandler(GamePlayController gamePlayController, PXMapHandler arena) {
		this.arena = arena;
		this.event = GameEvent.BATTLE;
		this.controller = gamePlayController;
		this.player1 = arena.getPlayer1();
		this.player2 = arena.getPlayer2();
		rounds.put(1, round1);
		rounds.put(2, round2);
		rounds.put(3, round3);
	}

	public void init() 
	{
		this.event = GameEvent.BATTLE;
		this.player1 = arena.getPlayer1();
		this.player2 = arena.getPlayer2();
		player1.wins = 0;
		player2.wins = 0;
		rounds.put(1, round1);
		rounds.put(2, round2);
		rounds.put(3, round3);
	    start = false;
		player1ready = false;
		player2ready = false;
		freezeready = false;
		nextRound = false;
	}
	
	
	public void update() throws InterruptedException
	{
		switch(event)
		{
		case INTRODUCTION:
			performIntro();			
			break;
		case NEXTROUND:
			performNextRound();
			break;
		case BATTLE:
			performBattle();	
			break;
		case KO:
			performKO();
			break;
		case END:
			performEnd();
			break;	
		default:
			break;
		}
	}
	
	

	private void performNextRound() {		
		ArtWork round = rounds.get(engine.getGamePlay().getRounds());
		if(timeBuffer == 0f)
		{
			round.reset();
			player1.releasedArtWorks.add(round);
		}
		if(timeBuffer < IDLETIME*1.5f)
		{
			this.timeBuffer += player1.delta;	
			return;
		}
		else
		if(round.dead)
		{	
			if(!beginFight)
			{
				fight.reset();
				player1.releasedArtWorks.add(fight);
				beginFight = true;
			}
			else
			if(fight.dead){
				beginFight = false;
				this.event = GameEvent.BATTLE;
				this.player1.freeze = false;
				this.player1.freeze_loop = false;
				this.player2.freeze = false;
				this.player2.freeze_loop = false;
				this.player1.finishing = false;
				this.player2.finishing = false;
				timeBuffer = 0;
				freezeready = false;
			}
		}		
	
	}

	public void performIntro() throws InterruptedException 
	{
		// Introduction Ende
		if(!start)
		{
			if(timeBuffer < IDLETIME)
				{
					this.timeBuffer += player1.delta;	
					return;
				}
			else	
			{
				this.player1.statusLogic.setActionStates(ActionStates.INTRO);
				start = true;
				this.timeBuffer = 0f;
			}
		}
		
		if(!this.player1.statusLogic.isIntroducting() && !player1ready)
		{
			player1ready = true;
		}
		
		if(player1ready && !player2ready && timeBuffer < IDLETIME)
		{
			this.timeBuffer += player1.delta;	
			return;
		}
		
		
		if(timeBuffer >= IDLETIME && !player2ready && player1ready)	
		{
			this.player2ready = true;
			this.player2.statusLogic.setActionStates(ActionStates.INTRO);
			this.timeBuffer = 0f;
		}
		
		
		if(!player1.statusLogic.isIntroducting() && !player2.statusLogic.isIntroducting())
		{
			ArtWork round = rounds.get(engine.getGamePlay().getRounds());;
			if(timeBuffer == 0f)
			{
				round.reset();
				player1.releasedArtWorks.add(round);
			}
			if(timeBuffer < IDLETIME)
			{
				this.timeBuffer += player1.delta;	
				return;
			}
			else
			if(round.dead)
			{			
				if(!beginFight)
				{
					fight.reset();
					player1.releasedArtWorks.add(fight);
					beginFight = true;
				}
				else
				if(fight.dead){
					beginFight = false;
					this.event = GameEvent.BATTLE;
					this.timeBuffer = 0f;
					player1ready =false;
					player2ready = false;
					  this.freezeready = false;
					start = false;	
				}
			}
		}
		
	}
	
	public void performBattle()
	{
		if(!player1.isAlive() || !player2.isAlive())
		{
			if(!freezeready)
			{//
			//1) screen super effekt (sound bild)
				player1.finishing();	
				player2.finishing();
				
				sound("/audio/ko_sound.wav");
				player1.finishing = true;
				GamePlayView.CAMERA_X_SPEED *= 5f;
				player1.shaking = true;
				//2) freeze _ bestimmte_dauer 
				//Erhöhe um bestimmten Faktor
				PXObject.FREEZE_TIME *= 10f;
				player1.freeze = true;
				player2.freeze = true;
				freezeready = true;

			}
			
			//3) kamera auf dead zeigen
			//   siehe View
			
			
			
			//4) rest play time bestimmte dauer
			//
			
			
			//5) change
			//
			if(!player1.freeze && !player2.freeze)
				{
					player1.finishing = false;
					freezeready = false;
					timeBuffer = 0f;
					GamePlayView.CAMERA_X_SPEED /= 5f;
					PXObject.FREEZE_TIME /= 10f;
					this.event = GameEvent.KO;
				}
		}
		
	}
	
	public void performKO() throws InterruptedException
	{
		//unentschieden
		//if(!player1.isAlive() && !player2.isAlive())
		player1.finishing();	
		player2.finishing();
		
		
		
		if(timeBuffer < IDLETIME*3f )	
		{
			this.timeBuffer += player1.delta;	
			return;
		}
				
		player1.finishing();	
		player2.finishing();
		
		//player2 wins
		if(!player1.isAlive() && both_idle())
		{
			player2.wins++;
			player2.statusLogic.setActionStates(ActionStates.WIN);
			timeBuffer = 0f;
			engine.getGamePlay().resetNextRoundBorder();
			this.event = GameEvent.END;
		}
		//player1 wins	
		if(!player2.isAlive() && both_idle())	
		{
			player1.wins++;
			player1.statusLogic.setActionStates(ActionStates.WIN);
			timeBuffer = 0f;
			engine.getGamePlay().resetNextRoundBorder();
			this.event = GameEvent.END;
		}
	}

	
	
	public void  performEnd() {
		
		Character alivePlayer = (player1.isAlive()) ? player1 : player2;		
		float distance = Math.abs(((GamePlayView)engine.getGamePlay().render).getTarget().x - alivePlayer.pos.x);
		if(distance > GamePlayView.SCREEN_WIDTH/2*GamePlayView.FIELD_SIZE)
			{
			alivePlayer.picManager.resetToIndex(0);
			return;
		}
		
		
		if(player1.statusLogic.isWinning() && player1.picManager.animationIsFinished() || 
				player2.statusLogic.isWinning() && player2.picManager.animationIsFinished())
			{
				nextRound = true;	
				timeBuffer = 0;
			}
		
		timeBuffer = 0f;
		if(engine.getGamePlay().nextRoundBorder.isOutroFinished())
			this.event = GameEvent.NEXTROUND;
		
	}
	
	
	
	public boolean both_idle() 
	{	
		return (this.player1.isAlive() && player1.statusLogic.isStanding() && 
					!player1.attackLogic.isAttacking() && !player2.statusLogic.isOnAir()) &&
				     player1.releasedDusts.isEmpty() && player2.releasedDusts.isEmpty()||
			   (this.player2.isAlive() && player2.statusLogic.isStanding() && 
					!player2.attackLogic.isAttacking() && !player1.statusLogic.isOnAir() &&
				     player1.releasedDusts.isEmpty() && player2.releasedDusts.isEmpty());			
	}

	//--------------------------------------------------
	
	public GameEvent getEvent() {
		return event;
	}

	public void setEvent(GameEvent event) {
		this.event = event;
	}

	public GameEngine getEngine() {
		return engine;
	}

	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}

	public PXMapHandler getArena() {
		return arena;
	}

	public void setArena(PXMapHandler arena) {
		this.arena = arena;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(GamePlayController controller) {
		this.controller = controller;
	}

	public Character getPlayer1() {
		return player1;
	}

	public void setPlayer1(Character player1) {
		this.player1 = player1;
	}

	public Character getPlayer2() {
		return player2;
	}

	public void setPlayer2(Character player2) {
		this.player2 = player2;
	}
	
	/**
	 * Method for the sounding effects.
	 * 
	 * 
	 * @param url
	 *            , Url
	 */
	public void sound(String url) {
		try {
			Clip clip;
			clip = AudioSystem.getClip();
			AudioInputStream inputStream1 = AudioSystem
					.getAudioInputStream(this.getClass().getResource(url));
			clip.open(inputStream1);
			clip.start();
			;

		} catch (Exception e) {
		}
	}
	
}
