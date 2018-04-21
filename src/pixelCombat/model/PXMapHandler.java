package pixelCombat.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pixelCombat.Math.BoundingRectangle;
import pixelCombat.Math.Vector2d;
import pixelCombat.arenas.BackGroundEffect;
import pixelCombat.arenas.ColdWinter;
import pixelCombat.arenas.FairyTailGuildDestroyed;
import pixelCombat.arenas.PXMap;
import pixelCombat.artworks.ArtWork;
import pixelCombat.controller.GamePlayController;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.MovementStates;
import pixelCombat.gamephase.gamephaseelement.ContentListener;
import pixelCombat.model.chars.NatsuFactory;
import pixelCombat.model.chars.RuffyFactory;
import pixelCombat.model.chars.ZorroFactory;
import pixelCombat.model.factory.AbstractPCCharacterFactory;
import pixelCombat.npc.NPC;
import pixelCombat.projectiles.Projectile;
import pixelCombat.projectiles.ProjectileManager;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.gamephases.GamePlayView;
import pixelCombat.xml.StatsParser;
import pixelCombat.xml.XML_Box_Reader;

public class PXMapHandler
{

  public static final int X_FIELDS_STANDARD = 24;
  public static final int Y_FIELDS_STANDARD = 14;
  public static final int X_FIELDS = (int) (X_FIELDS_STANDARD * GamePlayView.SCALEFACTOR);
  public static final int Y_FIELDS = (int) (Y_FIELDS_STANDARD * GamePlayView.SCALEFACTOR);
  public static final float GROUNDLEVEL = 7.5F;
  private Object[][] grid;
  private List<Character> character = new ArrayList<>();
  private List<Projectile> projectiles = new ArrayList<>();
  private List<Particle> particles = new ArrayList<>();
  private List<NPC> npc = new ArrayList<>();
  private List<Dust> dusts = new ArrayList<>();
  private List<ArtWork> artWorks = new ArrayList<>();
  private List<Spirit> spirits = new ArrayList<>();
  private List<PXMap> maps = new ArrayList<>();
  private PXMap selectedMap;
  private Map<String, Map<String, ArrayList<ArrayList<BoundingRectangle>>>> allBoxes = new HashMap<String, Map<String, ArrayList<ArrayList<BoundingRectangle>>>>();
  private Character player1;
  private Character player2;
  public ProjectileManager projectileManager;
  AbstractPCCharacterFactory factory;
  private int width;
  private int height;
  private ContentListener contentListener;
  private Console console;
  private BackGroundEffect backgroundEffect = BackGroundEffect.NONE;
  public GamePlayController controller;
  private GameEngine engine;
  
  public PXMapHandler(ContentListener contentListener, Console console, GameEngine engine)
  {
    this.contentListener = contentListener;
    this.projectileManager = new ProjectileManager(console);
    this.factory = new AbstractPCCharacterFactory(this.projectileManager);
    setEngine(engine);
    this.factory.registerFactory("Ruffy", new RuffyFactory());
    this.factory.registerFactory("Natsu", new NatsuFactory());
    this.factory.registerFactory("Zorro", new ZorroFactory());
    
    this.console = console;
    
    this.width = 18;
    this.height = 10;
    
    initAllMaps();
  }
  
  private void initAllMaps()
  {
    this.maps.add(new ColdWinter());
    this.maps.add(new FairyTailGuildDestroyed());
  }
  
  public void setupCharacters()
  {
    String playerName1 = this.contentListener.player1.substring(0, 1).toUpperCase() + this.contentListener.player1.substring(1, this.contentListener.player1.length());
    String playerName2 = this.contentListener.player2.substring(0, 1).toUpperCase() + this.contentListener.player2.substring(1, this.contentListener.player2.length());
    int selecteMapId = this.contentListener.mapID;
    boolean vsAi = this.contentListener.isVsAi();
    setSelectedMap((PXMap)this.maps.get(selecteMapId));
    this.npc = this.selectedMap.getNPCs();
    GamePlayView.levelMax = getSelectedMap().getWidth();
    GamePlayView.levelMaxy = getSelectedMap().getHeight();
    
    this.player1 = loadCharacter(playerName1);
    this.player1.boxes = loadBox(this.player1.getClass().getSimpleName());
    this.player1.boxLogic.init();
    
    this.player1.statusLogic.setMovementStates(MovementStates.RIGHT);
    this.player1.setPos(new Vector2d(GamePlayView.levelMax / 100.0F - 18.0F + 6.0F, 7.5F));
    this.player1.setTarget(this.player1.getPos());
    
    this.character.add(this.player1);
    getEngine().loading.setLoading(15);
    
    this.player2 = loadCharacter(playerName2);
    this.player2.boxes = loadBox(this.player2.getClass().getSimpleName());
    this.player2.boxLogic.init();
    
    this.player2.statusLogic.setMovementStates(MovementStates.LEFT);
    this.player2.setPos(new Vector2d(GamePlayView.levelMax / 100.0F + 18.0F - 6.0F, 7.5F));
    this.player2.setTarget(this.player2.getPos());
    getEngine().loading.setLoading(30);
    if (vsAi) {
      this.player2.initializeAI(this.controller);
    }
    this.character.add(this.player2);
    
    this.player1.enemy = this.player2;
    this.player2.enemy = this.player1;
    this.player1.setEngine(engine);
    this.player2.setEngine(engine);
    
  }
  
  public void reset()
  {
    this.player1.reset();
    this.player1.statusLogic.setMovementStates(MovementStates.RIGHT);
    this.player1.setPos(new Vector2d(GamePlayView.levelMax / 100.0F - 18.0F + 6.0F, 7.5F));
    
    this.player2.reset();
    this.player2.statusLogic.setMovementStates(MovementStates.LEFT);
    this.player2.setPos(new Vector2d(GamePlayView.levelMax / 100.0F + 18.0F - 6.0F, 7.5F));
  }
  
//  private void initNPCs()
//  {
//    Zorro zorro = new Zorro(new Vector2d(10.0F, 6.25F), false);
//    Sanji sanji = new Sanji(new Vector2d(13.0F, 6.25F), false);
//    Franky franky = new Franky(new Vector2d(26.0F, 6.25F), false);
//    Chopper chopper = new Chopper(new Vector2d(28.25F, 6.25F), false);
//    Nami nami = new Nami(new Vector2d(46.0F, 6.25F), false);
//    Robin robin = new Robin(new Vector2d(44.0F, 6.25F), false);
//    Goku goku = new Goku(new Vector2d(50.0F, 6.25F), false);
//    Vegeta vegeta = new Vegeta(new Vector2d(52.0F, 6.25F), false);
//    Brook brook = new Brook(new Vector2d(16.0F, 6.25F), false);
//    Usopp usopp = new Usopp(new Vector2d(19.5F, 6.25F), false);
//    Law law = new Law(new Vector2d(8.0F, 6.25F), false);
//    Natsu natsu = new Natsu(new Vector2d(33.5F, 6.25F), false);
//    Lucy lucy = new Lucy(new Vector2d(35.0F, 6.25F), false);
//    Gray gray = new Gray(new Vector2d(31.0F, 6.25F), false);
//    Erza erza = new Erza(new Vector2d(30.0F, 6.5F), false);
//    Happy happy = new Happy(new Vector2d(32.5F, 5.25F), false);
//    RanmaM ranmaM = new RanmaM(new Vector2d(38.0F, 6.25F), false);
//    RanmaW ranmaW = new RanmaW(new Vector2d(41.0F, 6.25F), false);
//    Kirito kirito = new Kirito(new Vector2d(3.0F, 6.25F), false);
//    Asuna asuna = new Asuna(new Vector2d(5.0F, 6.25F), false);
//    
//    this.npc.add(zorro);
//    this.npc.add(sanji);
//    this.npc.add(franky);
//    this.npc.add(chopper);
//    this.npc.add(nami);
//    this.npc.add(robin);
//    this.npc.add(goku);
//    this.npc.add(vegeta);
//    this.npc.add(brook);
//    this.npc.add(usopp);
//    this.npc.add(law);
//    this.npc.add(natsu);
//    this.npc.add(lucy);
//    this.npc.add(gray);
//    this.npc.add(erza);
//    this.npc.add(happy);
//    this.npc.add(ranmaM);
//    this.npc.add(ranmaW);
//    this.npc.add(kirito);
//    this.npc.add(asuna);
//  }
  
  public Character loadCharacter(String name)
  {
    Map<String, Object> stats = loadStats(name);
    if (this.character.isEmpty())
    {
      stats.put("name", "p1");
      stats.put("dir", new Vector2d(1.0F, 0.0F));
      stats.put("pos", new Vector2d(21.0F, 7.5F));
      stats.put("hp", Integer.valueOf(500));
      stats.put("mHP", Integer.valueOf(500));
    }
    else
    {
      stats.put("name", "p2");
      stats.put("dir", new Vector2d(-1.0F, 0.0F));
      stats.put("pos", new Vector2d(21.0F, 7.5F));
      stats.put("hp", Integer.valueOf(500));
      stats.put("mHP", Integer.valueOf(500));
    }
    return this.factory.createCharacter(stats);
  }
  
  public Map<String, Object> loadStats(String name)
  {
    Map<String, Object> stats = new HashMap<String, Object>();
    try
    {
      InputStream stream = StatsParser.class.getResourceAsStream("/characters_stats/" + name + ".xml");
      InputSource source = new InputSource(stream);
      
      XMLReader xmlreader = XMLReaderFactory.createXMLReader();
      StatsParser ch = new StatsParser(this.console);
      xmlreader.setContentHandler(ch);
      xmlreader.parse(source);
      
      stats = ch.getStats();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return stats;
  }
  
  public Character getPlayer1()
  {
    return this.player1;
  }
  
  public Object[][] getGrid()
  {
    return this.grid;
  }
  
  public void setGrid(Object[][] grid)
  {
    this.grid = grid;
  }
  
  public List<Character> getCharacter()
  {
    return this.character;
  }
  
  public void setCharacter(List<Character> character)
  {
    this.character = character;
  }
  
  public List<Projectile> getProjectiles()
  {
    return this.projectiles;
  }
  
  public void setProjectiles(List<Projectile> projectiles)
  {
    this.projectiles = projectiles;
  }
  
  public void setPlayer1(Character player1)
  {
    this.player1 = player1;
  }
  
  public Character getPlayer2()
  {
    return this.player2;
  }
  
  public void setPlayer2(Character player2)
  {
    this.player2 = player2;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public void setWidth(int width)
  {
    this.width = width;
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public void setHeight(int height)
  {
    this.height = height;
  }
  
  public static int getxFields()
  {
    return 18;
  }
  
  public static int getyFields()
  {
    return 10;
  }
  
  public static float getGroundlevel()
  {
    return 7.5F;
  }
  
  public void checkCollision(Character player1, Character player2)
  {
    BoundingRectangle iRect = player1.boxLogic.intersection(player2);
    if (playersCanCollide(player1, player2)) {
      return;
    }
    if (checkFurtherCollisions(player1, player2)) {
      return;
    }
    if (iRect == null)
    {
      if ((player1.collidesX) && (!player1.collidesY))
      {
        player1.statusLogic.setActionStates(ActionStates.STAND);
        player1.boxLogic.update();
        player1.collidesX = false;
      }
      if (player1.collidesY)
      {
        player1.statusLogic.setActionStates(ActionStates.STAND);
        player1.boxLogic.update();
        player1.collidesY = false;
      }
      return;
    }
    // the intersection box
    float x1 = iRect.getUpperLeft().x;
    float y1 = iRect.getUpperLeft().y;
    float w1 = iRect.getWidth();
    float h1 = iRect.getHeight();
    // rectangle box of one arbitrary player
    float x2 = player1.boxLogic.currentColBox.getUpperLeft().x;
    float y2 = player1.boxLogic.currentColBox.getUpperLeft().y;
    float w2 = player1.boxLogic.currentColBox.getWidth();
    float h2 = player1.boxLogic.currentColBox.getHeight();
    
    // we hit from left
    if ((x1 + w1 / 2.0F > x2 + w2 / 2.0F) && (!player1.collidesX))
    {
      player1.physics.VX = (-1.0F * Math.abs(player1.physics.VX));
      player1.collidesX = true;
    }
    // we hit from right
    if ((x1 + w1 / 2.0F < x2 + w2 / 2.0F) && (!player1.collidesX))
    {
      player1.physics.VX = Math.abs(player1.physics.VX);
      player1.collidesX = true;
    }
    
    
    // we hit from above
    if ((y1 + h1 / 2.0F > y2 + h2 / 2.0F) && (!player1.collidesY))
    {
      player1.physics.VY = - Math.abs(player1.physics.VY);
      player1.collidesY = true;
      //both players have to repel each other
      if (player1.getPos().x < player2.getPos().x)
      {
        player1.physics.VX -= 5.0F;
        player2.physics.VX += 5.0F;
      }
      else
      {
        player1.physics.VX += 5.0F;
        player2.physics.VX -= 5.0F;
      }
    }
    // we hit from botton
    if ((y1 + h1 / 2.0F < y2 + h2 / 2.0F) && (!player1.collidesY))
    {
      player1.physics.VY = Math.abs(player1.physics.VY);
      player1.collidesY = true;
      //both players have to repel each other
      if (player1.getPos().x < player2.getPos().x)
      {
        player1.physics.VX -= 5.0F;
        player2.physics.VX += 5.0F;
      }
      else
      {
        player1.physics.VX += 5.0F;
        player2.physics.VX -= 5.0F;
      }
    }
  }
  
  private boolean playersCanCollide(Character player1, Character player2)
  {
    return (player1.statusLogic.isJumping()) || (player2.statusLogic.isJumping()) || 
      ((!player1.statusLogic.isActive()) && (!player2.statusLogic.isActive())) || 
      (player1.statusLogic.isDashing()) || (player2.statusLogic.isDashing());
  }
  
  private boolean checkFurtherCollisions(Character player12, Character player22)
  {
    if (((this.player1.statusLogic.isActive()) && (this.player2.statusLogic.isKnockback())) || 
      ((this.player2.statusLogic.isActive()) && (this.player1.statusLogic.isKnockback())) || 
      ((this.player1.statusLogic.isActive()) && (this.player2.statusLogic.isDisabled())) || 
      ((this.player2.statusLogic.isActive()) && (this.player1.statusLogic.isDisabled())) || 
      ((this.player1.statusLogic.isActive()) && (this.player2.statusLogic.isInvincible())) || (
      (this.player2.statusLogic.isActive()) && (this.player1.statusLogic.isInvincible()))) {
      return true;
    }
    return false;
  }
  
  public void checkBackGroundEffect(Character player1, Character player2)
  {
    boolean player1BGEffect = player1.statusLogic.isBackGroundEffecting();
    boolean player2BGEffect = player2.statusLogic.isBackGroundEffecting();
    
    boolean xOr = player1BGEffect ^ player2BGEffect;
    if (!xOr)
    {
      this.backgroundEffect = BackGroundEffect.NONE;
      return;
    }
    if (player1BGEffect) {
      this.backgroundEffect = player1.statusLogic.getBackGroundEffect();
    } else {
      this.backgroundEffect = player2.statusLogic.getBackGroundEffect();
    }
  }
  
  public void checkProjectiles()
  {
    ArrayList<Projectile> p1 = new ArrayList<Projectile>(this.player1.getReleasedProjectiles());
    for (int i = 0; i < p1.size(); i++)
    {
      this.projectiles.add((Projectile)p1.get(i));
      this.player1.getReleasedProjectiles().remove(p1.get(i));
    }
    ArrayList<Projectile> p2 = new ArrayList<Projectile>(this.player2.getReleasedProjectiles());
    for (int i = 0; i < p2.size(); i++)
    {
      this.projectiles.add((Projectile)p2.get(i));
      this.player2.getReleasedProjectiles().remove(p2.get(i));
    }
    ArrayList<Projectile> p3 = new ArrayList<Projectile>(this.projectiles);
    for (int i = 0; i < p3.size(); i++) {
      if (((Projectile)p3.get(i)).statusLogic.isDead()) {
        this.projectiles.remove(p3.get(i));
      }
    }
  }
  
  public void checkParticles()
  {
    ArrayList<Particle> p1 = new ArrayList<Particle>(this.player1.getReleasedParticles());
    for (int i = 0; i < p1.size(); i++)
    {
      this.particles.add((Particle)p1.get(i));
      this.player1.getReleasedParticles().remove(0);
    }
    ArrayList<Particle> p2 = new ArrayList<Particle>(this.player2.getReleasedParticles());
    for (int i = 0; i < p2.size(); i++)
    {
      this.particles.add((Particle)p2.get(i));
      this.player2.getReleasedParticles().remove(0);
    }
  }
  
  public void checkDusts()
  {
    ArrayList<Dust> p1 = new ArrayList<Dust>(this.player1.getReleasedDusts());
    for (Dust p : p1) {
      if (p.isDead()) {
        this.player1.getReleasedDusts().remove(p);
      }
    }
    ArrayList<Dust> p2 = new ArrayList<Dust>(this.player2.getReleasedDusts());
    for (Dust p : p2) {
      if (p.isDead()) {
        this.player2.getReleasedDusts().remove(p);
      }
    }
  }
  
  public void checkArtWorks()
  {
    ArrayList<ArtWork> p1 = new ArrayList<ArtWork>(this.player1.getReleasedArtWorks());
    for (int i = 0; i < p1.size(); i++)
    {
      this.artWorks.add((ArtWork)p1.get(i));
      this.player1.getReleasedArtWorks().remove(0);
    }
    ArrayList<ArtWork> p2 = new ArrayList<ArtWork>(this.player2.getReleasedArtWorks());
    for (int i = 0; i < p2.size(); i++)
    {
      this.artWorks.add((ArtWork)p2.get(i));
      this.player2.getReleasedArtWorks().remove(0);
    }
    ArrayList<ArtWork> p3 = new ArrayList<ArtWork>(this.artWorks);
    for (ArtWork p : p3) {
      if (p.isDead()) {
        this.artWorks.remove(p);
      }
    }
  }
  
  public void checkSpirits()
  {
    ArrayList<Spirit> p1 = new ArrayList<Spirit>(this.player1.getSpirits());
    for (int i = 0; i < p1.size(); i++)
    {
      this.spirits.add((Spirit)p1.get(i));
      this.player1.getSpirits().remove(0);
    }
    ArrayList<Spirit> p2 = new ArrayList<Spirit>(this.player2.getSpirits());
    for (int i = 0; i < p2.size(); i++)
    {
      this.spirits.add((Spirit)p2.get(i));
      this.player2.getSpirits().remove(0);
    }
    ArrayList<Spirit> p3 = new ArrayList<Spirit>(this.spirits);
    for (Spirit p : p3) {
      if (p.isDead()) {
        this.spirits.remove(p);
      }
    }
  }
  
  public void addProjectile(Projectile p)
  {
    this.projectiles.add(p);
  }
  
  public List<Particle> getParticles()
  {
    return this.particles;
  }
  
  public void setParticles(List<Particle> particles)
  {
    this.particles = particles;
  }
  
  public void deleteProjectile(Projectile p)
  {
    this.projectiles.remove(p);
  }
  
  public void deleteParticle(Particle p)
  {
    this.particles.remove(p);
  }
  
  public List<Dust> getDusts()
  {
    return this.dusts;
  }
  
  public void setDusts(List<Dust> dusts)
  {
    this.dusts = dusts;
  }
  
  public void addDust(Dust p)
  {
    this.dusts.add(p);
  }
  
  public void deleteDust(Dust p)
  {
    this.dusts.remove(p);
  }
  
  public void deleteCharacter(Character p)
  {
    this.character.remove(p);
  }
  
  public void addCharacter(Character p)
  {
    this.character.add(p);
  }
  
  public List<NPC> getNpc()
  {
    return this.npc;
  }
  
  public void setNpc(List<NPC> npc)
  {
    this.npc = npc;
  }
  
  public List<ArtWork> getArtWorks()
  {
    return this.artWorks;
  }
  
  public BackGroundEffect getBackGroundEffect()
  {
    return this.backgroundEffect;
  }
  
  public PXMap getMap()
  {
    return getSelectedMap();
  }
  
  public PXMap getSelectedMap()
  {
    return this.selectedMap;
  }
  
  public void setSelectedMap(PXMap selectedMap)
  {
    this.selectedMap = selectedMap;
  }
  
  public List<Spirit> getSpirits()
  {
    return this.spirits;
  }
  
  public void killAll()
  {
    this.character.clear();
    this.player1.kill();
    this.player1 = null;
    this.player2.kill();
    this.player2 = null;
    this.particles.clear();
    this.projectiles.clear();
    this.artWorks.clear();
  }
  
  public Map<String, ArrayList<ArrayList<BoundingRectangle>>> loadBox(String name)
  {
    if (this.allBoxes.get(name) != null) {
      return (Map<String, ArrayList<ArrayList<BoundingRectangle>>>)this.allBoxes.get(name);
    }
    Map<String, ArrayList<ArrayList<BoundingRectangle>>> boxes = new HashMap<>();
    try
    {
      InputStream stream = StatsParser.class.getResourceAsStream("/chars_boxes/" + name + ".xml");
      InputSource source = new InputSource(stream);
      
      XMLReader xmlreader = XMLReaderFactory.createXMLReader();
      XML_Box_Reader ch = new XML_Box_Reader(this.console);
      xmlreader.setContentHandler(ch);
      xmlreader.parse(source);
      
      boxes = ch.getBoxes();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    this.allBoxes.put(name, boxes);
    return boxes;
  }
  
  public GameEngine getEngine()
  {
    return this.engine;
  }
  
  public void setEngine(GameEngine engine)
  {
    this.engine = engine;
  }
}
