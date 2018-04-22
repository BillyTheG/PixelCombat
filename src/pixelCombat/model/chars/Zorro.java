package pixelCombat.model.chars;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.artworks.TatsumakiDragon;
import pixelCombat.artworks.UlToraGari;
import pixelCombat.artworks.ZorroWaterDrop;
import pixelCombat.controller.GamePlayController;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.dusts.BloodSplash2;
import pixelCombat.dusts.OniGiriSlash;
import pixelCombat.dusts.OniGiriSmoke;
import pixelCombat.dusts.Pound360Charge;
import pixelCombat.dusts.ShiShinShinSonSmoke;
import pixelCombat.dusts.SpecialAttackSignalEffect;
import pixelCombat.dusts.TatsumakiWindAura;
import pixelCombat.dusts.TatsumakiWindExplosion;
import pixelCombat.dusts.UlToraGariWind;
import pixelCombat.dusts.ZorroBasicAttack21Dust;
import pixelCombat.dusts.ZorroShiShinShinSonSlash;
import pixelCombat.dusts.ZorroSpecialBG;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.ki.ZorroKI;
import pixelCombat.model.Attack;
import pixelCombat.model.Character;
import pixelCombat.model.chars.zorro.ZorroAttackLogic;
import pixelCombat.model.chars.zorro.attacks.Zorro360Pound;
import pixelCombat.model.chars.zorro.attacks.ZorroBasicAttack11;
import pixelCombat.model.chars.zorro.attacks.ZorroBasicAttack12;
import pixelCombat.model.chars.zorro.attacks.ZorroBasicAttack13;
import pixelCombat.model.chars.zorro.attacks.ZorroBasicAttack21;
import pixelCombat.model.chars.zorro.attacks.ZorroBasicAttack22;
import pixelCombat.model.chars.zorro.attacks.ZorroBasicAttack23;
import pixelCombat.model.chars.zorro.attacks.ZorroDash;
import pixelCombat.model.chars.zorro.attacks.ZorroJumpAttack;
import pixelCombat.model.chars.zorro.attacks.ZorroNigiri;
import pixelCombat.model.chars.zorro.attacks.ZorroOniGiri;
import pixelCombat.model.chars.zorro.attacks.ZorroRashomon;
import pixelCombat.model.chars.zorro.attacks.ZorroRunAttack1;
import pixelCombat.model.chars.zorro.attacks.ZorroRunAttack2;
import pixelCombat.model.chars.zorro.attacks.ZorroShiShinShinSon;
import pixelCombat.model.chars.zorro.attacks.ZorroTatsumaki;
import pixelCombat.model.chars.zorro.attacks.ZorroToraGari;
import pixelCombat.model.chars.zorro.attacks.ZorroYokkouDori;
import pixelCombat.projectiles.ProjectileManager;

public class Zorro
  extends Character
{
  private SpecialAttackSignalEffect signalEffect;
  private UlToraGariWind ulToraGariWind;
  private UlToraGari ulToraGariArtWork;
  private TatsumakiDragon tatsumakiDragonArtWork;
  private OniGiriSlash oniGiriSlash;
  private TatsumakiWindExplosion tatsumakiWindExplosion;
  private TatsumakiWindAura tatsumakiWindAura;
  private ZorroWaterDrop zorroWaterDrop;
  private ZorroBasicAttack21Dust basicAttack21Slash;
  private Pound360Charge pound360Charge;
  private BloodSplash1 bloodSplash_1;
  private BloodSplash1 bloodSplash_2;
  private BloodSplash1 bloodSplash_3;
  private ArrayList<BloodSplash1> bloodSplashs1 = new ArrayList<>();
  private int bloodSpashId = 0;
  private BloodSplash2 bloodSplash2;
  private ZorroShiShinShinSonSlash shiShinShinSonSlash;
  private ShiShinShinSonSmoke shiShinShinSonSmoke;
  private OniGiriSmoke oniGiriSmoke1;
  private OniGiriSmoke oniGiriSmoke2;
  private OniGiriSmoke oniGiriSmoke3;
  private OniGiriSmoke oniGiriSmoke4;
  private OniGiriSmoke oniGiriSmoke5;
  private ArrayList<OniGiriSmoke> oniGiriSmokes = new ArrayList<OniGiriSmoke>();
  private int oniGiriSmokeId = 0;
  private int introBlabberings = 5;
  
  public Zorro(String name, int lifePoints, Vector2d pos, Vector2d dir, int maxLifePoints, ProjectileManager projectileManager)
  {
    super(name, lifePoints, 11.0F, 8.0F, pos, 5, maxLifePoints, 5, 5, 2.0F, "", "", "", "", projectileManager);
    
    this.specialBG = new ZorroSpecialBG(null, true);
    setZorroWaterDrop(new ZorroWaterDrop());
    
    this.bloodSplash_1 = new BloodSplash1(new Vector2d(this.pos.x, this.pos.y), false);
    this.bloodSplash_2 = new BloodSplash1(new Vector2d(this.pos.x, this.pos.y), false);
    this.bloodSplash_3 = new BloodSplash1(new Vector2d(this.pos.x, this.pos.y), false);
    
    this.bloodSplash2 = new BloodSplash2(new Vector2d(this.pos.x, this.pos.y), false);
    this.shiShinShinSonSlash = new ZorroShiShinShinSonSlash(new Vector2d(),true);
    this.shiShinShinSonSmoke = new ShiShinShinSonSmoke(new Vector2d(this.pos.x, this.pos.y), false);
    
    this.bloodSplashs1.add(this.bloodSplash_1);
    this.bloodSplashs1.add(this.bloodSplash_2);
    this.bloodSplashs1.add(this.bloodSplash_3);
    
    this.oniGiriSmoke1 = new OniGiriSmoke(new Vector2d(this.pos.x, this.pos.y), false);
    this.oniGiriSmoke2 = new OniGiriSmoke(new Vector2d(this.pos.x, this.pos.y), false);
    this.oniGiriSmoke3 = new OniGiriSmoke(new Vector2d(this.pos.x, this.pos.y), false);
    this.oniGiriSmoke4 = new OniGiriSmoke(new Vector2d(this.pos.x, this.pos.y), false);
    this.oniGiriSmoke5 = new OniGiriSmoke(new Vector2d(this.pos.x, this.pos.y), false);
    
    this.oniGiriSmokes.add(this.oniGiriSmoke1);
    this.oniGiriSmokes.add(this.oniGiriSmoke2);
    this.oniGiriSmokes.add(this.oniGiriSmoke3);
    this.oniGiriSmokes.add(this.oniGiriSmoke4);
    this.oniGiriSmokes.add(this.oniGiriSmoke5);
    
    this.signalEffect = new SpecialAttackSignalEffect(new Vector2d(this.pos.x, this.pos.y), false);
    this.ulToraGariWind = new UlToraGariWind(new Vector2d(this.pos.x, this.pos.y), false);
    this.ulToraGariArtWork = new UlToraGari();
    this.tatsumakiDragonArtWork = new TatsumakiDragon();
    this.tatsumakiWindExplosion = new TatsumakiWindExplosion(new Vector2d(this.pos.x, this.pos.y), false);
    this.tatsumakiWindAura = new TatsumakiWindAura(new Vector2d(this.pos.x, this.pos.y), false);
    this.basicAttack21Slash = new ZorroBasicAttack21Dust(new Vector2d(this.pos.x, this.pos.y), false);
    this.pound360Charge = new Pound360Charge(new Vector2d(this.pos.x, this.pos.y), false);
    
    setOniGiriSlash(new OniGiriSlash(new Vector2d(this.pos.x, this.pos.y), false));
    
    this.landSound = "/audio/Zorro_Land.wav";
  }
  
  protected void init()
  {
    this.attacks.put("basicAttack11", new ZorroBasicAttack11(this, 0));
    this.attacks.put("basicAttack12", new ZorroBasicAttack12(this, 1));
    this.attacks.put("basicAttack13", new ZorroBasicAttack13(this, 2));
    this.attacks.put("basicAttack21", new ZorroBasicAttack21(this, 3));
    this.attacks.put("basicAttack22", new ZorroBasicAttack22(this, 4));
    this.attacks.put("basicAttack23", new ZorroBasicAttack23(this, 5));
    this.attacks.put("specialAttack2", new ZorroOniGiri(this, 6));
    this.attacks.put("specialAttack3", new ZorroTatsumaki(this, 7));
    this.attacks.put("specialAttack4", new ZorroRashomon(this, 7));
    this.attacks.put("specialAttack6", new ZorroNigiri(this, 8));
    this.attacks.put("specialAttack7", new Zorro360Pound(this, 8));
    this.attacks.put("specialAttack5", new ZorroShiShinShinSon(this, 8));
    this.attacks.put("runAttack1", new ZorroRunAttack1(this, 15));
    this.attacks.put("runAttack2", new ZorroRunAttack2(this, 16));
    this.attacks.put("airSpecialAttack1", new ZorroToraGari(this, 17));
    this.attacks.put("jumpAttack", new ZorroJumpAttack(this, 17));
    this.attacks.put("yokkoudori", new ZorroYokkouDori(this, 17));
    this.attacks.put("dash", new ZorroDash(this, 18));
  }
  
  public void updateAttack()
  {
    if (this.attackLogic.isBasicAttacking1()) {
      ((Attack)this.attacks.get("basicAttack11")).process();
    } else if (this.attackLogic.isBasicAttacking2()) {
      ((Attack)this.attacks.get("basicAttack12")).process();
    } else if (this.attackLogic.isBasicAttacking21()) {
      ((Attack)this.attacks.get("basicAttack21")).process();
    } else if (this.attackLogic.isBasicAttacking22()) {
      ((Attack)this.attacks.get("basicAttack22")).process();
    } else if (this.attackLogic.isBasicAttacking23()) {
      ((Attack)this.attacks.get("basicAttack23")).process();
    } else if (this.attackLogic.isJumpAttacking1()) {
      ((Attack)this.attacks.get("jumpAttack")).process();
    }
  }
  
  public void checkBasicAttack(Character defender)
  {
    ((Attack)this.attacks.get("basicAttack11")).check();
  }
  
  public void checkBasicAttack1(Character defender)
  {
    ((Attack)this.attacks.get("basicAttack12")).check();
  }
  
  public void checkBasicAttack21(Character defender)
  {
    ((Attack)this.attacks.get("basicAttack21")).check();
  }
  
  public void checkBasicAttack22(Character defender)
  {
    ((Attack)this.attacks.get("basicAttack22")).check();
  }
  
  public void checkBasicAttack23(Character defender)
  {
    ((Attack)this.attacks.get("basicAttack23")).check();
  }
  
  public void checkJumpAttack(Character defender)
  {
    ((Attack)this.attacks.get("jumpAttack")).check();
  }
  
  public void checkDash()
  {
    ((Attack)this.attacks.get("dash")).check();
  }
  
  public void updateSpecial()
  {
    if (this.attackOnAir) {
      this.physics.VY = 0.0F;
    }
    if (this.attackLogic.isSpecialAttacking1()) {
      ((Attack)this.attacks.get("basicAttack13")).process();
    } else if (this.attackLogic.isSpecialAttacking2()) {
      ((Attack)this.attacks.get("specialAttack2")).process();
    } else if (this.attackLogic.isSpecialAttacking3()) {
      ((Attack)this.attacks.get("specialAttack3")).process();
    } else if (this.attackLogic.isSpecialAttacking4()) {
      ((Attack)this.attacks.get("specialAttack4")).process();
    } else if (this.attackLogic.isSpecialAttacking5()) {
      ((Attack)this.attacks.get("specialAttack5")).process();
    } else if (this.attackLogic.isSpecialAttacking6()) {
      ((Attack)this.attacks.get("specialAttack6")).process();
    } else if (this.attackLogic.isSpecialAttacking7()) {
      ((Attack)this.attacks.get("specialAttack7")).process();
    }
  }
  
  public void checkSpecialAttack1(Character defender)
  {
    ((Attack)this.attacks.get("basicAttack13")).check();
  }
  
  public void checkSpecialAttack2(Character defender)
  {
    ((Attack)this.attacks.get("specialAttack2")).check();
  }
  
  public void checkSpecialAttack3(Character defender)
  {
    ((Attack)this.attacks.get("specialAttack3")).check();
  }
  
  public void checkSpecialAttack4(Character defender)
  {
    ((Attack)this.attacks.get("specialAttack4")).check();
  }
  
  public void checkSpecialAttack5(Character defender)
  {
    ((Attack)this.attacks.get("specialAttack5")).check();
  }
  
  public void checkSpecialAttack6(Character defender)
  {
    ((Attack)this.attacks.get("specialAttack6")).check();
  }
  
  public void checkSpecialAttack7(Character defender)
  {
    ((Attack)this.attacks.get("specialAttack7")).check();
  }
  
  public void updateDash()
  {
    ((Attack)this.attacks.get("dash")).process();
  }
  
  public void resetCharStats()
  {
    this.switcher = true;
    this.statusLogic.setAHitDelay(false);
    this.freeze_loop = false;
    setAttackOnAir(false);
    for (String attack : this.attacks.keySet()) {
      ((Attack)this.attacks.get(attack)).resetStats();
    }
  }
  
  protected String dash()
  {
    return "/audio/Ruffy_Dash.wav";
  }
  
  protected String retreat()
  {
    return "/audio/Ruffy_Retreat.wav";
  }
  
  public void dying() {}
  
  public void finishing()
  {
	  
	  
	  
    getUlToraGariArtWork().dead = true;
    this.releasedArtWorks.clear();
    if (!this.statusLogic.isWinning()) {
        return;
      }
    
    
    switch (this.picManager.getCurrFrameIndex())
    {
    case 0: 
      break;
    case 1: 
      break;
    }
  }
  
  public void introduct()
  {
    if ((this.picManager.getCurrFrameIndex() == 3) && (this.switcher))
    {
      sound("/audio/Zorro_Sword_OutOfSheath.wav");
      this.switcher = false;
    }
    if ((this.picManager.getCurrFrameIndex() == 6) && (!this.switcher))
    {
      sound("/audio/Zorro_Intro.wav");
      this.switcher = true;
    }
    if ((this.picManager.isAlmostFinished(8)) && (this.introBlabberings > 0))
    {
      this.picManager.resetToIndex(7);
      this.introBlabberings -= 1;
    }
    if (this.picManager.getAnimTime() == this.picManager.getTotalDuration())
    {
      this.statusLogic.setActionStates(ActionStates.STAND);
      this.switcher = true;
      this.introBlabberings = 5;
    }
  }
  
  public void updateRunAttack()
  {
    if (this.attackLogic.isRunAttacking1()) {
      ((Attack)this.attacks.get("runAttack1")).process();
    } else if (this.attackLogic.isRunAttacking2()) {
      ((Attack)this.attacks.get("runAttack2")).process();
    }
  }
  
  public void checkRunAttack1(Character defender)
  {
    ((Attack)this.attacks.get("runAttack1")).check();
  }
  
  public void checkRunAttack2(Character defender)
  {
    ((Attack)this.attacks.get("runAttack2")).check();
  }
  
  public void updateAirSpecialAttack()
  {
    this.physics.VY = 0.0F;
    if (this.attackLogic.isAirSpecialAttacking1()) {
      ((Attack)this.attacks.get("airSpecialAttack1")).process();
    }
    if (getAttackLogic().isYokkouDoring()) {
      ((Attack)this.attacks.get("yokkoudori")).process();
    }
  }
  
  public void checkAirSpecialAttack1(Character defender)
  {
    ((Attack)this.attacks.get("airSpecialAttack1")).check();
  }
  
  public void checkAirSpecialAttack2(Character defender)
  {
    ((Attack)this.attacks.get("yokkoudori")).check();
  }
  
  public Image draw()
  {
    return this.picManager.getImage();
  }
  
  public void updateMiscs() {}
  
  public void loadFurtherImages(List<ArrayList<Image>> player_all, Map<String, ArrayList<Image>> player)
  {
    player_all.add(this.viewLogic.MAX_STANDARD_SPRITES+1, (ArrayList<Image>)player.get("yokkoudori"));
  }
  
  public void checkFurtherCombos(List<ArrayList<String>> combos, List<String> result)
  {
    if (combos.get(18) == result)
    {
      stopActing();
      GamePlayController.airSpecial(AttackStates.isYokkouDoring, this, "yokkoudori");
    }
  }
  
  public void checkFurtherAttacks(Character defender)
  {
    checkAirSpecialAttack2(defender);
  }
  
  public ZorroAttackLogic getAttackLogic()
  {
    return (ZorroAttackLogic)this.attackLogic;
  }
  
  public SpecialAttackSignalEffect getSignalEffect()
  {
    return this.signalEffect;
  }
  
  public void setSignalEffect(SpecialAttackSignalEffect signalEffect)
  {
    this.signalEffect = signalEffect;
  }
  
  public UlToraGariWind getUlToraGariWind()
  {
    return this.ulToraGariWind;
  }
  
  public void setUlToraGariWind(UlToraGariWind ulToraGariWind)
  {
    this.ulToraGariWind = ulToraGariWind;
  }
  
  public ArrayList<BloodSplash1> getBloodSplashs1()
  {
    return this.bloodSplashs1;
  }
  
  public void setBloodSplashs1(ArrayList<BloodSplash1> bloodSplashs1)
  {
    this.bloodSplashs1 = bloodSplashs1;
  }
  
  public int getBloodSpashId()
  {
    return this.bloodSpashId;
  }
  
  public void setBloodSpashId(int bloodSpashId)
  {
    this.bloodSpashId = bloodSpashId;
  }
  
  public UlToraGari getUlToraGariArtWork()
  {
    return this.ulToraGariArtWork;
  }
  
  public void setUlToraGariArtWork(UlToraGari ulToraGariArtWork)
  {
    this.ulToraGariArtWork = ulToraGariArtWork;
  }
  
  public OniGiriSlash getOniGiriSlash()
  {
    return this.oniGiriSlash;
  }
  
  public void setOniGiriSlash(OniGiriSlash oniGiriSlash)
  {
    this.oniGiriSlash = oniGiriSlash;
  }
  
  public TatsumakiDragon getTatsumakiDragonArtWork()
  {
    return this.tatsumakiDragonArtWork;
  }
  
  public void setTatsumakiDragonArtWork(TatsumakiDragon tatsumakiDragonArtWork)
  {
    this.tatsumakiDragonArtWork = tatsumakiDragonArtWork;
  }
  
  public TatsumakiWindExplosion getTatsumakiWindExplosion()
  {
    return this.tatsumakiWindExplosion;
  }
  
  public TatsumakiWindAura getTatsumakiWindAura()
  {
    return this.tatsumakiWindAura;
  }
  
  public ZorroBasicAttack21Dust getBasicAttack21Slash()
  {
    return this.basicAttack21Slash;
  }
  
  public Pound360Charge getPound360Charge()
  {
    return this.pound360Charge;
  }
  
  public ZorroWaterDrop getZorroWaterDrop()
  {
    return this.zorroWaterDrop;
  }
  
  public void setZorroWaterDrop(ZorroWaterDrop zorroWaterDrop)
  {
    this.zorroWaterDrop = zorroWaterDrop;
  }
  
  public ArrayList<OniGiriSmoke> getOniGiriSmokes()
  {
    return this.oniGiriSmokes;
  }
  
  public void setOniGiriSmokes(ArrayList<OniGiriSmoke> oniGiriSmokes)
  {
    this.oniGiriSmokes = oniGiriSmokes;
  }
  
  public int getOniGiriSmokeId()
  {
    return this.oniGiriSmokeId;
  }
  
  public void setOniGiriSmokeId(int oniGiriSmokeId)
  {
    this.oniGiriSmokeId = oniGiriSmokeId;
  }
  
  public BloodSplash2 getBloodSplash2()
  {
    return this.bloodSplash2;
  }
  
  public ZorroShiShinShinSonSlash getShiShinShinSonSlash()
  {
    return this.shiShinShinSonSlash;
  }
  
  public ShiShinShinSonSmoke getShiShinShinSonSmoke()
  {
    return this.shiShinShinSonSmoke;
  }
  
  public void initializeAI(GamePlayController controller)
  {
    this.aiManager = new ZorroKI(this, controller);
  }
}
