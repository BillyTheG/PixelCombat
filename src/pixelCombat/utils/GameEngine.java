package pixelCombat.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pixelCombat.controller.Controller;
import pixelCombat.gamephase.CharacterSelection;
import pixelCombat.gamephase.Credit;
import pixelCombat.gamephase.GamePlay;
import pixelCombat.gamephase.Help;
import pixelCombat.gamephase.Loading;
import pixelCombat.gamephase.MainMenue;
import pixelCombat.gamephase.MapSelection;
import pixelCombat.gamephase.Summary;
import pixelCombat.gamephase.Title;
import pixelCombat.gamephase.gamephaseelement.ContentListener;
import pixelCombat.model.PXMapHandler;
import pixelCombat.stage.ConsoleStage;
import pixelCombat.stage.GameStage;

public class GameEngine
{
  protected static Controller controller;
  private volatile Canvas gameCanvas;
  private volatile GameStage gameStage;
  public static Clip clip;
  protected volatile EvalGamePhase visitor;
  protected volatile GamePhase gamePhase;
  protected volatile Title title;
  protected volatile MainMenue mainMenue;
  protected volatile CharacterSelection characterSelection;
  protected volatile MapSelection mapSelection;
  public volatile Loading loading;
  protected volatile GamePlay gamePlay;
  protected volatile Credit credit;
  protected volatile Help help;
  protected volatile Summary summary;
  public volatile ContentListener contentListener;
  public volatile PXMapHandler arena;
  private volatile KomboHandler komboHandler;
  public volatile Console console;
  private ConsoleStage consoleStage;
  private MediaPlayer mediaPlayer;
  public static boolean TOGGLE_MUSIC = true;
  public static final boolean TOGGLE_SOUND = true;
  
  public GameEngine(Pane mainViewGroup, GameStage gameStage, Console console, ConsoleStage consoleStage)
  {
    this.consoleStage = consoleStage;
    this.gameStage = gameStage;
    this.console = console;
    setGameCanvas(new Canvas());
    mainViewGroup.getChildren().add(getGameCanvas());
    init();
  }
  
  public Clip getClip()
  {
    return clip;
  }
  
  public void setClip(Clip clip)
  {
    GameEngine.clip = clip;
  }
  
  public void init()
  {
    this.loading = new Loading(this, this.gameCanvas, this.console);
    this.loading.init(true);
    this.gamePhase = this.loading;
    
    Thread thread = new Thread(new GamePhaseLoader(this));
    thread.start();
    
    this.visitor = new EvalGamePhase(this, this.gameStage, this.consoleStage);
    
    startMenuMusic();
  }
  
  public void startMenuMusic()
  {
    musicMP3("/audio/RyusTheme.mp3");
  }
  
  public void close()
  {
    this.gameStage.close();
  }
  
  public void music(String url)
  {
    if (!TOGGLE_MUSIC) {
      return;
    }
    try
    {
      if (clip != null)
      {
        clip.stop();
        clip.close();
      }
      clip = AudioSystem.getClip();
      AudioInputStream inputStream1 = AudioSystem.getAudioInputStream(getClass().getResource(url));
      clip.open(inputStream1);
      clip.loop(-1);
    }
    catch (Exception e)
    {
      this.console.println("Nothing");
    }
  }
  
  public void stopMP3()
  {
    if (this.mediaPlayer == null) {
      return;
    }
    this.mediaPlayer.stop();
    this.mediaPlayer.dispose();
  }
  
  public void musicMP3(String url)
  {
    Media hit = new Media(getClass().getResource(url).toString());
    this.mediaPlayer = new MediaPlayer(hit);
    this.mediaPlayer.setCycleCount(-1);
    this.mediaPlayer.play();
  }
  
  public void update(long deltaTime)
  {
    this.gamePhase.updatePrimaryStuff(deltaTime);
  }
  
  public void handleCommand(KeyCode kc, boolean hold)
  {
    this.gamePhase.handleCommand(kc, hold);
  }
  
  public GamePhase getGamePhase()
  {
    return this.gamePhase;
  }
  
  public void setGamePhase(GamePhase gamePhase)
  {
    this.gamePhase = gamePhase;
  }
  
  public Title getTitle()
  {
    return this.title;
  }
  
  public void setTitle(Title title)
  {
    this.title = title;
  }
  
  public MainMenue getMainMenue()
  {
    return this.mainMenue;
  }
  
  public void setMainMenue(MainMenue mainMenue)
  {
    this.mainMenue = mainMenue;
  }
  
  public CharacterSelection getCharacterSelection()
  {
    return this.characterSelection;
  }
  
  public void setCharacterSelection(CharacterSelection characterSelection)
  {
    this.characterSelection = characterSelection;
  }
  
  public MapSelection getMapSelection()
  {
    return this.mapSelection;
  }
  
  public void setMapSelection(MapSelection mapSelection)
  {
    this.mapSelection = mapSelection;
  }
  
  public GamePlay getGamePlay()
  {
    return this.gamePlay;
  }
  
  public void setGamePlay(GamePlay gamePlay)
  {
    this.gamePlay = gamePlay;
  }
  
  public EvalGamePhase getVisitor()
  {
    return this.visitor;
  }
  
  public KomboHandler getKomboHandler()
  {
    return this.komboHandler;
  }
  
  public void setKomboHandler(KomboHandler komboHandler)
  {
    this.komboHandler = komboHandler;
  }
  
  public Controller getController()
  {
    return this.gamePhase.controller;
  }
  
  public GameStage getGameStage()
  {
    return this.gameStage;
  }
  
  public Canvas getGameCanvas()
  {
    return this.gameCanvas;
  }
  
  public void setGameCanvas(Canvas gameCanvas)
  {
    this.gameCanvas = gameCanvas;
  }
}
