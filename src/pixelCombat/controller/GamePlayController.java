package pixelCombat.controller;

/**
 * Character Selection Controller (Menu)
 * 
 * @author: BillyG
 * @version 1.0
 */


import java.util.ArrayList;
import java.util.List;

import pixelCombat.artworks.ArtWork;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GameEvent;
import pixelCombat.enums.KeyCommand;
import pixelCombat.enums.MovementStates;
import pixelCombat.eventhandling.EventHandler;
import pixelCombat.eventhandling.EventListener;
import pixelCombat.gamephase.GamePlay;
import pixelCombat.model.BoxLogic;
import pixelCombat.model.Character;
import pixelCombat.model.Dust;
import pixelCombat.model.PXMapHandler;
import pixelCombat.model.PXObject;
import pixelCombat.model.Particle;
import pixelCombat.model.Spirit;
import pixelCombat.npc.NPC;
import pixelCombat.projectiles.Projectile;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.gamephases.GamePlayView;


public class GamePlayController extends Controller  implements EventListener{

	public	GameEngine engine;
	public 	PXMapHandler arena;
	public 	Character player1;
	public 	Character player2;
	public	EventHandler eventHandler;
	public	GameEvent event;
	private GamePlayView gamePlayView;
	private boolean initialized;
	private GamePlay gamePlay;
	/**
	 * Constructor for Character Selection Controller
	 * 
	 * @param engine
	 *            GameEngine
	 */

	public GamePlayController(GameEngine engine,GamePlayView gamePlayView, PXMapHandler arena, GamePlay gamePlay) 
	{
		this.setGamePlayView(gamePlayView);
		this.engine = engine;
		this.gamePlay = gamePlay;
		this.arena = arena;
		arena.controller = this;
	}

	public boolean onKey(KeyCommand key, boolean hold) {
		Character player1 = arena.getPlayer1();
		Character player2 = arena.getPlayer2();
	
		
		if(uncontrollable())
			return true;
		
		switch (key) {
//-----------------------------------------------------------------------------------------------------------------------------------			
//---------------------------------------Player 1  Keys -----------------------------------------------------------------------------			
//-----------------------------------------------------------------------------------------------------------------------------------			
		case P1RIGHT:
			return move(player1,hold,true);			
		case P1LEFT:
			return move(player1,hold,false);	
		case P1BASICATTACK1:
			return basicAttack(player1,AttackStates.isBasicAttacking1);			
		case P1BASICATTACK2:
			return basicAttack(player1,AttackStates.isBasicAttacking2);	
		case P1BASICATTACK21:
			return basicAttack(player1,AttackStates.isBasicAttacking21);	
		case P1BASICATTACK22:
			return basicAttack(player1,AttackStates.isBasicAttacking22);	
		case P1BASICATTACK23:
			return basicAttack(player1,AttackStates.isBasicAttacking23);	
		case P1COMBOATTACK:
			return combo(player1);			
		case P1DASH:
			return dash(player1);
		case P1SPECIALATTACK1:
			return specialAttack(player1,AttackStates.isSpecialAttacking1,"specialAttack2");		
		case P1SPECIALATTACK2:
			return specialAttack(player1,AttackStates.isSpecialAttacking2,"specialAttack3");
		case P1SPECIALATTACK3:
			return specialAttack(player1,AttackStates.isSpecialAttacking3,"specialAttack4");
		case P1SPECIALATTACK4:
			return specialAttack(player1,AttackStates.isSpecialAttacking4,"specialAttack5");
		case P1SPECIALATTACK5:
			return specialAttack(player1,AttackStates.isSpecialAttacking5,"specialAttack6");
		case P1SPECIALATTACK6:
			return specialAttack(player1,AttackStates.isSpecialAttacking6,"specialAttack7");
		case P1JUMP:
			return jump(player1);
		case P1DEFEND:
			return defend(hold, player1);
		case P1AIRSPECIALATTACK1:
			return airSpecial(AttackStates.isAirSpecialAttacking1, player1,"airSpecialAttack1");
//-----------------------------------------------------------------------------------------------------------------------------------			
//---------------------------------------Player 2  Keys -----------------------------------------------------------------------------			
//-----------------------------------------------------------------------------------------------------------------------------------			
		case P2RIGHT:			
			return move(player2,hold,true);			
		case P2LEFT:
			return move(player2,hold,false);	
		case P2BASICATTACK1:
			return basicAttack(player2,AttackStates.isBasicAttacking1);			
		case P2BASICATTACK2:
			return basicAttack(player2,AttackStates.isBasicAttacking2);	
		case P2BASICATTACK21:
			return basicAttack(player2,AttackStates.isBasicAttacking21);	
		case P2BASICATTACK22:
			return basicAttack(player2,AttackStates.isBasicAttacking22);	
		case P2BASICATTACK23:
			return basicAttack(player2,AttackStates.isBasicAttacking23);	
		case P2COMBOATTACK:
			return combo(player2);			
		case P2DASH:
			return dash(player2);
		case P2SPECIALATTACK1:
			return specialAttack(player2,AttackStates.isSpecialAttacking1,"specialAttack2");		
		case P2SPECIALATTACK2:
			return specialAttack(player2,AttackStates.isSpecialAttacking2,"specialAttack3");
		case P2SPECIALATTACK3:
			return specialAttack(player2,AttackStates.isSpecialAttacking3,"specialAttack4");
		case P2SPECIALATTACK4:
			return specialAttack(player2,AttackStates.isSpecialAttacking4,"specialAttack5");
		case P2SPECIALATTACK5:
			return specialAttack(player2,AttackStates.isSpecialAttacking5,"specialAttack6");
		case P2SPECIALATTACK6:
			return specialAttack(player2,AttackStates.isSpecialAttacking6,"specialAttack7");
		case P2JUMP:
			return jump(player2);
		case P2DEFEND:
			return defend(hold, player2);
		case P2AIRSPECIALATTACK1:
			return airSpecial(AttackStates.isAirSpecialAttacking1, player2,"airSpecialAttack1");
		default:
			break;
		}	
		return true;
	}



	

	public boolean defend(boolean hold, Character player) {
		if (!player.statusLogic.canNotDefend()) {
			
			if(player.statusLogic.isJumping())	player.statusLogic.setActionStates(ActionStates.AIR_DEFENDING);
			else								player.statusLogic.setActionStates(ActionStates.DEFENDING);
		if(!hold)
				player.statusLogic.setActionStates(ActionStates.STAND);
		}
		
		return true;
	}



	public boolean jump(Character player) {
		if (!player.statusLogic.canNotJump()) {
			player.physics.VY =(-20f);
			player.statusLogic.setOnAir(true);
			player.statusLogic.setActionStates(ActionStates.JUMP);
			sound(player.getJumpSound());
		}
		return true;
	}



	public boolean dash(Character player) {
		if (player.statusLogic.canNotDash())
			return true;
		player.statusLogic.setActionStates(ActionStates.DASHING);
		return true;
	}



	public static boolean specialAttack(Character player, AttackStates specialAttack, String input_seq)
	  {
	    if (!player.enoughMagic(input_seq, false)) {
	      return true;
	    }
	    if (player.checkAirCombo(input_seq))
	    {
	      if (!player.enoughMagicMinus(input_seq)) {
	        return true;
	      }
	      player.setAttackOnAir(true);
	      player.attackLogic.setAttackStatus(specialAttack);
	      return true;
	    }
	    if (!player.statusLogic.canNotSpecial1())
	    {
	      if (!player.enoughMagicMinus(input_seq)) {
	        return true;
	      }
	      player.statusLogic.setActionStates(ActionStates.STAND);
	      player.attackLogic.setAttackStatus(specialAttack);
	      return true;
	    }
	    return true;
	  }
	  
	  public static boolean airSpecial(AttackStates isairspecialattacking, Character player, String input_seq)
	  {
	    if (!player.enoughMagic(input_seq, true)) {
	      return true;
	    }
	    if ((!player.statusLogic.canNotAirSpecial1()) && 
	      (Math.abs(player.getPos().y - 7.5F) > 2.0F))
	    {
	      if (!player.enoughMagicMinus(input_seq)) {
	        return true;
	      }
	      player.attackLogic.setAttackStatus(isairspecialattacking);
	      return true;
	    }
	    return true;
	  }
	
	

	public boolean move(Character player, boolean hold, boolean right)
	{
		int factor = 1;
		MovementStates movementState = MovementStates.RIGHT;
		if(!right) 
		{
			factor *=-1;
			movementState =  MovementStates.LEFT;
		}
		
		if (!player.statusLogic.canNotMove() ) {
			if(!player.statusLogic.isMoving() || Math.abs(player.physics.VX) <= 1f)
			{
				player.physics.VX = factor*1f;
				player.statusLogic.setActionStates(ActionStates.MOVE);
				player.statusLogic.setMovementStates(movementState);		
			}
		
			if(!hold && !player.statusLogic.isOnAir())
				player.statusLogic.setActionStates(ActionStates.STAND);
		}
		
		if (	player.statusLogic.isActive() 
			&& 	player.statusLogic.isOnAir() 
			&& !player.isJumpAttacking() 
			&& !player.attackLogic.isAttacking() 
			&& !player.statusLogic.isDashing() ) {
			player.statusLogic.setMovementStates(movementState);
			if(!player.statusLogic.isMoving())
				{
					if(!player.collidesBY)
						player.physics.VX = factor*player.getMovementspeed()*1.75f;
				}
			
			if(!hold)
				player.statusLogic.setActionStates(ActionStates.JUMP);
		}

		return true;	
	}
	
	
	public boolean basicAttack(Character player,AttackStates attackStates)
	{
		if(	player.statusLogic.isJumping()  
				&& 	Math.abs(player.getPos().y - PXMapHandler.GROUNDLEVEL) > 2f) 
			{
				if (player.statusLogic.canNotAirSpecial1())
					return true;
				if (player.statusLogic.canNotAttack() && !player.statusLogic.isJumping()) 
					return true;

				player.setJumpAttacking(true); 
				return true;
			}
			if (player.statusLogic.canNotAttack())
				return true;
			if(player.statusLogic.isMoving() && Math.abs(player.physics.VX) == player.physics.maximumSpeed)
			{
				if(attackStates == AttackStates.isBasicAttacking1) player.attackLogic.setAttackStatus(AttackStates.isRunAttacking1);
				else player.attackLogic.setAttackStatus(AttackStates.isRunAttacking2);
					
				player.statusLogic.setActionStates(ActionStates.STAND);
				return true;
			}
			if(!player.statusLogic.isJumping())
			{
				player.statusLogic.setActionStates(ActionStates.STAND);
				player.attackLogic.setAttackStatus(attackStates);
			}
			return true;
	}
	
	public boolean combo(Character player) {
		if (player.statusLogic.canNotAttack())
			return true;
		
		player.statusLogic.setActionStates(ActionStates.STAND);
		player.attackLogic.setAttackStatus(AttackStates.isComboAttacking);
		return true;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void update(long delta)  {
		
		if(!this.gamePlay.isInitialized()) return;
		
		//EventHandler
		try {
			listen(eventHandler);
			eventHandler.update();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		checkFreeze(delta);
		
		if(player1.superAttacking)
			player1.specialBG.update((float) delta / 1000000000.0f);
		if(player2.superAttacking)
			player2.specialBG.update((float) delta / 1000000000.0f);
		
	
		for(NPC o : arena.getNpc())
			o.update((float) delta / 1000000000.0f);
		for(Particle o : arena.getParticles())
			o.update((float) delta / 1000000000.0f);
		
		for(Dust o : player1.getReleasedDusts())
			{
				if(!player2.freeze)
					o.update((float) delta / 1000000000.0f);
			}
		for(Dust o : player2.getReleasedDusts())
			{
			if(!player1.freeze)
				o.update((float) delta / 1000000000.0f);
			}
		
		for(ArtWork o : arena.getArtWorks())
			o.update((float) delta / 1000000000.0f);
		
		for(Spirit spirit : arena.getSpirits())
			spirit.update((float) delta / 1000000000.0f);

		List<Particle> par =  new ArrayList<Particle>(arena.getParticles());
		for (Particle o : par) {
			if(o.hasDied())
				arena.deleteParticle(o);
		}
		

		
		for(Projectile o : arena.getProjectiles())
		{
			if(!o.creator.freeze && !o.creator.enemy.freeze)
				o.update((float) delta / 1000000000.0f);
		}
	
		checkFinals();

	}

	private void checkFinals() 
	{
		BoxLogic boxlogic_p1 = player1.boxLogic;
		BoxLogic boxlogic_p2 = player2.boxLogic;
		
		//ViewLogic viewLogic_p1 = player1.viewLogic;
		//ViewLogic viewLogic_p2 = player2.viewLogic;
		
		boxlogic_p1.update();
		boxlogic_p2.update();
		//viewLogic_p1.update();
		//viewLogic_p2.update();
		
		
	}

	private void checkFreeze(long delta) {
		
			float delta_n = (float) delta / 1000000000.0f;
		
			if(!player2.freeze)
			{
				player1.update(delta_n);
			}
			if(!player1.freeze)
			{
				player2.update(delta_n);
			}
			
		
			if(player1.freeze)
			{
				player2.freezeTime(delta_n);
				if(player2.freezeBuffer >= PXObject.FREEZE_TIME)
				{
					if(!player1.freeze_loop)
						player1.freeze = false;
					player2.freezeBuffer = 0f;
				}
			}
				
			if(player2.freeze)
			{
				player1.freezeTime(delta_n);
				if(player1.freezeBuffer >= PXObject.FREEZE_TIME)
				{
					if(!player2.freeze_loop)
						player2.freeze = false;
					player1.freezeBuffer = 0f;
				}
			}
			updateChecks();
		
	}

	private void updateChecks() {
		
	
		//checkings -hier gekapselt
				for (Character attacker : arena.getCharacter()) {
					
					for (Character defender : arena.getCharacter()) {
						if (attacker != defender) {
							attacker.checkBasicAttack(defender);
							attacker.checkBasicAttack1(defender);
							attacker.checkSpecialAttack1(defender);
							attacker.checkSpecialAttack2(defender);
							attacker.checkSpecialAttack3(defender);
							attacker.checkSpecialAttack4(defender);
							attacker.checkSpecialAttack5(defender);
							attacker.checkSpecialAttack6(defender);
							attacker.checkSpecialAttack7(defender);
							attacker.checkJumpAttack(defender);
							attacker.checkBasicAttack21(defender);
							attacker.checkBasicAttack22(defender);
							attacker.checkBasicAttack23(defender);
							attacker.checkRunAttack1(defender);
							attacker.checkRunAttack2(defender);
							attacker.checkAirSpecialAttack1(defender);
							attacker.checkFurtherAttacks(defender);
							attacker.checkDash();
							
							
							arena.checkProjectiles();
							arena.checkParticles();
							arena.checkDusts();
							arena.checkArtWorks();
							arena.checkSpirits();
						}
						
					}
					
				}
				
			  for (Projectile projectile : arena.getProjectiles()) {
					
					for (Character defender : arena.getCharacter()) {
						if (projectile.creator != defender) {
							projectile.checkDefender(defender);
						}
					}
				}
				
				//arena update
				arena.checkCollision(player1,player2);
				arena.checkCollision(player2,player1);
				arena.checkBackGroundEffect(player1, player2);
	}

	@Override
	public void listen(EventHandler eventHandler) 
	{
		event = this.eventHandler.getEvent();
	}

	@Override
	public void setEvent(EventHandler eventHandler, GameEvent event) 
	{
		eventHandler.setEvent(event);
	}


	public boolean uncontrollable()
	{
		
		if(!gamePlay.isInitialized())
			return false;
		
		return (
				event == GameEvent.INTRODUCTION ||
				event == GameEvent.KO || 
				event == GameEvent.NEXTROUND ||
				event == GameEvent.END ||
				player1.superAttacking || 
				player2.superAttacking)||
				player1.freeze ||
				player2.freeze;
	}
	
	public void init() {
	
		this.player1 = arena.getPlayer1();
		this.player2 = arena.getPlayer2();
		if(this.eventHandler == null)
		{
			this.eventHandler = new EventHandler(this,arena);	
			this.eventHandler.setEngine(engine);
		}
		else
			eventHandler.init();
		setInitialized(true);
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
	public boolean fightIsFinished(){		
		if(eventHandler != null)
			return eventHandler.nextRound;
		return false;
	}

	/**
	 * @return the gamePlayView
	 */
	public GamePlayView getGamePlayView() {
		return gamePlayView;
	}

	/**
	 * @param gamePlayView the gamePlayView to set
	 */
	public void setGamePlayView(GamePlayView gamePlayView) {
		this.gamePlayView = gamePlayView;
	}

	public void kill() {
		this.player1 = null;
		this.player2 = null;
		this.eventHandler.setEvent(GameEvent.INTRODUCTION);
		initialized = false;
		
	}


	
	
}