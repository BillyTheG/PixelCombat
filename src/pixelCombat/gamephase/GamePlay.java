package pixelCombat.gamephase;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import pixelCombat.controller.GamePlayController;
import pixelCombat.effects.NextRoundBorder;
import pixelCombat.enums.GameEvent;
import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.gamephaseelement.ContentListener;
import pixelCombat.gamephase.gamephaseelement.GamePlayListener;
import pixelCombat.model.Character;
import pixelCombat.model.PXMapHandler;
import pixelCombat.observer.Observable;
import pixelCombat.observer.Observer;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.utils.GamePhase;
import pixelCombat.utils.GamePhaseVisitor;
import pixelCombat.utils.KomboHandler;
import pixelCombat.view.gamephases.GamePlayView;

public class GamePlay
  extends GamePhase
  implements Observable
{
  private GamePlayView gamePlayView;
  private GamePlayController gamePlayController;
  private PXMapHandler arena;
  private ContentListener contentListener;
  private GamePlayListener gamePlayListener;
  private boolean allLoaded;
  private ArrayList<Observer> observers = new ArrayList<Observer>();
  private float deltaTime;
  private boolean gameCanBePlayed;
  private int rounds = 1;
  public NextRoundBorder nextRoundBorder;
  public boolean nextRoundBorderReset = true;
  private String winnerClassName;
  private Character winner;
  private boolean player1wins;
  
  public GamePlay(GameEngine engine, Canvas canvas, PXMapHandler mapHandler, ContentListener contentListener, Console console)
  {
    super(engine, console);
    this.arena = mapHandler;
    setContentListener(contentListener);
    this.gamePlayView = new GamePlayView(mapHandler, canvas, this);
    setGamePlayController(new GamePlayController(engine, this.gamePlayView, mapHandler, this));
    
    this.render = this.gamePlayView;
    this.controller = getGamePlayController();
    this.gamePlayListener = new GamePlayListener();
    
    this.observers.add(contentListener);
    this.observers.add(this.gamePlayListener);
    this.nextRoundBorder = new NextRoundBorder();
  }
  
  public void resetForNextRound()
  {
    if ((getRounds() <= 3) && (!anyoneWinner()))
    {
      this.nextRoundBorder.makeOutro();
      this.arena.reset();
      getGamePlayController().reset();
      this.gamePlayView.reset();
    }
    else
    {
      this.winner = (this.arena.getPlayer1().wins > this.arena.getPlayer2().wins ? this.arena.getPlayer1() : this.arena.getPlayer2());
      this.winnerClassName = this.winner.getClass().getSimpleName();
      setPlayer1Won(this.arena.getPlayer1().wins > this.arena.getPlayer2().wins);
      
      getGamePlayController().eventHandler.nextRound = false;
      this.gameCanBePlayed = false;
      this.console.clear();
      this.allLoaded = false;
      this.rounds = 1;
      this.nextRoundBorder.reset();
      this.arena.killAll();
      this.gamePlayView.kill();
      this.gamePlayController.kill();
      accept(this.engine.getVisitor(), false);
    }
  }
  
  private boolean anyoneWinner()
  {
    return (this.rounds > 1) && ((this.arena.getPlayer1().wins > 1) || (this.arena.getPlayer2().wins > 1));
  }
  
  public void update(long deltaTime)
  {
    if (this.allLoaded)
    {
      this.engine.arena.getSelectedMap().playMusic(this.engine);
      this.gamePlayView.setGraphicsContext(this.engine.getGameCanvas().getGraphicsContext2D());
      this.allLoaded = false;
      this.gameCanBePlayed = true;
    }
    if (this.gameCanBePlayed)
    {
      this.deltaTime = ((float)deltaTime);
      this.engine.getKomboHandler().update(deltaTime);
      this.engine.arena.getSelectedMap().update((float)deltaTime / 1.0E9F);
      notifyObservers();
    }
    if ((roundIsEnd()) && (this.gameCanBePlayed))
    {
      Character alivePlayer = this.gamePlayController.player1.isAlive() ? this.gamePlayController.player1 : this.gamePlayController.player2;
      float distance = Math.abs(((GamePlayView)this.engine.getGamePlay().render).getTarget().x - alivePlayer.pos.x);
      if (distance > 22500.0F) {
        return;
      }
      if (this.nextRoundBorderReset) {
        resetNextRoundBorder();
      }
      if (this.nextRoundBorder.isIntroFinished())
      {
        this.gamePlayListener.updateRounds(getRounds());
        reset();
      }
      this.nextRoundBorder.update((float)deltaTime / 1.0E9F);
    }
  }
  
  public void resetNextRoundBorder()
  {
    this.nextRoundBorder.reset();
    this.nextRoundBorderReset = false;
    setRounds(getRounds() + 1);
  }
  
  public boolean roundIsEnd()
  {
    return getGamePlayController().event == GameEvent.END;
  }
  
  public void handleCommand(KeyCode kc, boolean hold)
  {
    if ((!this.gameCanBePlayed) || (getGamePlayController().uncontrollable())) {
      return;
    }
    switch (kc)
    {
    case V: 
      if (this.arena.getPlayer2().freeze) {
        return;
      }
      if (!hold)
      {
        if ((this.arena.getPlayer1().statusLogic.isJumping()) && (!this.arena.getPlayer1().statusLogic.canNotAirSpecial1())) {
          KomboHandler.pressKey("attack", this.arena.getPlayer1());
        }
        if (!this.arena.getPlayer1().statusLogic.canNotAttack()) {
          KomboHandler.pressKey("attack", this.arena.getPlayer1());
        }
        this.controller.onKey(KeyCommand.P1BASICATTACK1, hold);
      }
      break;
    case F: 
      if (this.arena.getPlayer2().freeze) {
        return;
      }
      if (!hold) {
        KomboHandler.pressKey("dash", this.arena.getPlayer1());
      }
      this.controller.onKey(KeyCommand.P1DASH, hold);
      break;
    case B: 
      if (this.arena.getPlayer2().freeze) {
        return;
      }
      if (!hold)
      {
        if (!this.arena.getPlayer1().statusLogic.canNotAttack()) {
          KomboHandler.pressKey("attack1", this.arena.getPlayer1());
        }
        this.controller.onKey(KeyCommand.P1BASICATTACK21, hold);
      }
      break;
    case G: 
      if (this.arena.getPlayer2().freeze) {
        return;
      }
      if (!hold) {
        this.controller.onKey(KeyCommand.P1SPECIALATTACK2, hold);
      }
      break;
    case N: 
      if (!this.arena.getPlayer2().freeze)
      {
        if (!hold) {
          KomboHandler.pressKey("defend", this.arena.getPlayer1());
        }
        this.controller.onKey(KeyCommand.P1DEFEND, hold);
      }
      break;
    case W: 
      if (this.arena.getPlayer2().freeze) {
        return;
      }
      if (!hold) {
        KomboHandler.pressKey("up", this.arena.getPlayer1());
      }
      this.controller.onKey(KeyCommand.P1JUMP, hold);
      break;
    case A: 
      if (this.arena.getPlayer2().freeze) {
        return;
      }
      if (!hold) {
        KomboHandler.pressKey("left", this.arena.getPlayer1());
      }
      this.controller.onKey(KeyCommand.P1LEFT, hold);
      break;
    case S: 
      if (this.arena.getPlayer2().freeze) {
        return;
      }
      if (!hold) {
        KomboHandler.pressKey("down", this.arena.getPlayer1());
      }
      break;
    case D: 
      if (this.arena.getPlayer2().freeze) {
        return;
      }
      if (!hold) {
        KomboHandler.pressKey("right", this.arena.getPlayer1());
      }
      this.controller.onKey(KeyCommand.P1RIGHT, hold);
      break;
    case NUMPAD1: 
      if (player1NotFreezeOrPlayerNotAI()) {
        if (!hold)
        {
          if (!this.arena.getPlayer2().statusLogic.canNotAttack()) {
            KomboHandler.pressKey("attack", this.arena.getPlayer2());
          }
          this.controller.onKey(KeyCommand.P2BASICATTACK1, hold);
        }
      }
      break;
    case NUMPAD2: 
      if (player1NotFreezeOrPlayerNotAI()) {
        if (!hold)
        {
          if (!this.arena.getPlayer2().statusLogic.canNotAttack()) {
            KomboHandler.pressKey("attack1", this.arena.getPlayer2());
          }
          this.controller.onKey(KeyCommand.P2BASICATTACK21, hold);
        }
      }
      break;
    case NUMPAD3: 
      if (player1NotFreezeOrPlayerNotAI())
      {
        if (!hold) {
          KomboHandler.pressKey("defend", this.arena.getPlayer2());
        }
        this.controller.onKey(KeyCommand.P2DEFEND, hold);
      }
      break;
    case NUMPAD4: 
      if (player1NotFreezeOrPlayerNotAI())
      {
        if (!hold) {
          KomboHandler.pressKey("dash", this.arena.getPlayer2());
        }
        this.controller.onKey(KeyCommand.P2DASH, hold);
      }
      break;
    case NUMPAD5: 
      if (player1NotFreezeOrPlayerNotAI()) {
        if (!hold) {
          this.controller.onKey(KeyCommand.P2SPECIALATTACK2, hold);
        }
      }
      break;
    case LEFT: 
      if (player1NotFreezeOrPlayerNotAI())
      {
        if (!hold) {
          KomboHandler.pressKey("left", this.arena.getPlayer2());
        }
        this.controller.onKey(KeyCommand.P2LEFT, hold);
      }
      break;
    case RIGHT: 
      if (player1NotFreezeOrPlayerNotAI())
      {
        if (!hold) {
          KomboHandler.pressKey("right", this.arena.getPlayer2());
        }
        this.controller.onKey(KeyCommand.P2RIGHT, hold);
      }
      break;
    case UP: 
      if (player1NotFreezeOrPlayerNotAI())
      {
        if (!hold) {
          KomboHandler.pressKey("up", this.arena.getPlayer2());
        }
        this.controller.onKey(KeyCommand.P2JUMP, hold);
      }
      break;
    case DOWN: 
      if (!player1NotFreezeOrPlayerNotAI()) {
        return;
      }
      if (!hold) {
        KomboHandler.pressKey("down", this.arena.getPlayer2());
      }
      break;
    case ENTER: 
      this.controller.onKey(KeyCommand.ENTER, hold);
      break;
    case F9: 
      this.engine.getGameStage().setFullScreen(false);
      break;
    case F10: 
      this.engine.getGameStage().setFullScreen(true);
      break;
    case J :
    	handleCommand(KeyCode.NUMPAD1,hold);
    	break;
    case K :
    	handleCommand(KeyCode.NUMPAD2,hold);
    	break;
    case L :
    	handleCommand(KeyCode.NUMPAD3,hold);
    	break;
    case I :
    	handleCommand(KeyCode.NUMPAD4,hold);
    	break;
    case O :
    	handleCommand(KeyCode.NUMPAD5,hold);
    	break;	
    default:
		break;
    }
  }
  
  private boolean player1NotFreezeOrPlayerNotAI()
  {
    return (!this.arena.getPlayer1().freeze) && (this.arena.getPlayer2().aiManager == null);
  }
  
  public void accept(GamePhaseVisitor visitor, boolean forward)
  {
    this.engine.setGamePhase(visitor.exit(this));
  }
  
  public void reset()
  {
    resetForNextRound();
  }
  
  public synchronized void init()
  {
    this.engine.stopMP3();
    
    this.arena.setupCharacters();
    
    getGamePlayController().init();
    this.engine.loading.setLoading(40);
    
    this.gamePlayView.init();
    this.allLoaded = true;
    this.engine.loading.setLoading(100);
  }
  
  public boolean isInitialized()
  {
    return (getGamePlayController().isInitialized()) && (this.gamePlayView.isInitialized());
  }
  
  public void addObserver(Observer o)
  {
    this.observers.add(o);
  }
  
  public void removeObserver(Observer o)
  {
    this.observers.remove(o);
  }
  
  public void notifyObservers()
  {
    for (Observer o : this.observers) {
      o.update(this.deltaTime / 1.0E9F);
    }
    this.gamePlayListener.update(this.deltaTime / 1.0E9F, this.arena.getPlayer1(), this.arena.getPlayer2());
    this.gamePlayListener.updateRounds(getRounds());
  }
  
  public ContentListener getContentListener()
  {
    return this.contentListener;
  }
  
  public void setContentListener(ContentListener contentListener)
  {
    this.contentListener = contentListener;
  }
  
  public GamePlayController getGamePlayController()
  {
    return this.gamePlayController;
  }
  
  public void setGamePlayController(GamePlayController gamePlayController)
  {
    this.gamePlayController = gamePlayController;
  }
  
  public int getRounds()
  {
    return this.rounds;
  }
  
  public void setRounds(int rounds)
  {
    this.rounds = rounds;
  }
  
  public String getWinnerClassName()
  {
    return this.winnerClassName;
  }
  
  public Character getWinner()
  {
    return this.winner;
  }
  
  public boolean hasPlayer1Won()
  {
    return this.player1wins;
  }
  
  public void setPlayer1Won(boolean player1wins)
  {
    this.player1wins = player1wins;
  }
}
