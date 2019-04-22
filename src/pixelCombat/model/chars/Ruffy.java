package pixelCombat.model.chars;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.ai.RuffyKI;
import pixelCombat.artworks.Gear2;
import pixelCombat.artworks.Gear3;
import pixelCombat.artworks.JetGatling;
import pixelCombat.artworks.JetPistol;
import pixelCombat.artworks.RuffySuper;
import pixelCombat.auras.PumpItAura;
import pixelCombat.auras.RuffyGear2Aura;
import pixelCombat.auras.Ruffy_Pump_Aura;
import pixelCombat.controller.GamePlayController;
import pixelCombat.dusts.DashDust;
import pixelCombat.dusts.DashDustAttack;
import pixelCombat.dusts.GigantoGatlingFist;
import pixelCombat.dusts.GomuGomuStorm;
import pixelCombat.dusts.JetBigDustBackDust;
import pixelCombat.dusts.JetBigDustCircle;
import pixelCombat.dusts.JetDust;
import pixelCombat.dusts.JetPistole;
import pixelCombat.dusts.JetPistoleBigDust;
import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.dusts.RuffySpecialBG;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.model.Attack;
import pixelCombat.model.Character;
import pixelCombat.model.chars.ruffy.RuffyAttackLogic;
import pixelCombat.model.chars.ruffy.attacks.RuffyBasicAttack11;
import pixelCombat.model.chars.ruffy.attacks.RuffyBasicAttack12;
import pixelCombat.model.chars.ruffy.attacks.RuffyBasicAttack13;
import pixelCombat.model.chars.ruffy.attacks.RuffyBasicAttack21;
import pixelCombat.model.chars.ruffy.attacks.RuffyBasicAttack22;
import pixelCombat.model.chars.ruffy.attacks.RuffyBasicAttack23;
import pixelCombat.model.chars.ruffy.attacks.RuffyDash;
import pixelCombat.model.chars.ruffy.attacks.RuffyGigantoGatling;
import pixelCombat.model.chars.ruffy.attacks.RuffyGigantoWhip;
import pixelCombat.model.chars.ruffy.attacks.RuffyGomuGomuNoStorm;
import pixelCombat.model.chars.ruffy.attacks.RuffyJetGatling;
import pixelCombat.model.chars.ruffy.attacks.RuffyJetPistole;
import pixelCombat.model.chars.ruffy.attacks.RuffyJetPistoleFromAir;
import pixelCombat.model.chars.ruffy.attacks.RuffyJumpAttack;
import pixelCombat.model.chars.ruffy.attacks.RuffyKomboGear2;
import pixelCombat.model.chars.ruffy.attacks.RuffyKomboGear3;
import pixelCombat.model.chars.ruffy.attacks.RuffyModeGear2;
import pixelCombat.model.chars.ruffy.attacks.RuffyRunAttack1;
import pixelCombat.model.chars.ruffy.attacks.RuffyRunAttack2;
import pixelCombat.projectiles.ProjectileManager;
import pixelCombat.view.EffectManager;

public class Ruffy
  extends Character
{
  private static final int GatlingMax = 50;
public float range = 8.0F;
  public boolean jetHammer = false;
  public int gatling = 40;
  public int SuperAttackIntro = 10;
  public boolean secondTeleport = false;
  public boolean upperJetPistole_Air = false;
  public boolean upperJetPistole_Punch = false;
  private Random random = new Random();
  private GigantoGatlingFist dust1 = null;
  private RuffySuper jetGatling = null;
  private Ruffy_Pump_Aura ruffy_aura = null;
  private JetPistole ruffy_superhitspark = null;
  private RuffyGear2Aura gear2aura = null;
  private DashDustAttack dashDust = null;
  private int kicks = 10;
  private PumpItAura pump_it_aura;
  private JetPistoleBlueHit jetPistole_Blue1;
  private JetPistoleBlueHit jetPistole_Blue2;
  private JetPistoleBlueHit jetPistole_Blue3;
  private ArrayList<JetPistoleBlueHit> jetPistole_Blues;
  private JetBigDustCircle jetBigDustCircle;
  private JetBigDustBackDust jetBigDustBackDust;
  private JetPistoleBigDust jetPistoleBigDust_1;
  private JetPistoleBigDust jetPistoleBigDust_2;
  private JetPistoleBigDust jetPistoleBigDust_3;
  private JetPistoleBigDust jetPistoleBigDust_4;
  private JetPistoleBigDust jetPistoleBigDust_5;
  private JetPistoleBigDust jetPistoleBigDust_6;
  private JetPistoleBigDust jetPistoleBigDust_7;
  private ArrayList<JetPistoleBigDust> jetPistole_BigDusts;
  private int jetPistoleBlue_Id = 0;
  private int jetPistoleBigDust_Id = 0;
  private GomuGomuStorm gomu_Gomu_Storm;
  private EffectManager effectManager;
  private boolean startAura = true;
  private JetGatling jetGatlingArtWork;
  private JetPistol jetPistoleArtWork;
  private JetDust jetDust;
  private DashDust dashGatlingDust;
  private Gear2 gear2 = new Gear2();
  private Gear3 gear3 = new Gear3();
  private boolean isGear2On = false;
  private float tempSpeed = 0.0F;
  private float tempAcc = 0.0F;
  private boolean pushNotDoneSofar = true;
  
  public Ruffy(String name, int lifePoints, Vector2d pos, Vector2d dir, int maxLifePoints, ProjectileManager projectileManager)
  {
    super(name, lifePoints, 11.0F, 8.0F, pos, 10, maxLifePoints, 5, 5, 2.0F, "/audio/RuffyIsHit.wav", "/audio/Ruffy_basicAttack.wav", "/audio/Ruffy_Riffle.wav", "/audio/Ruffy_Death.wav", projectileManager);
    
    this.specialBG = new RuffySpecialBG(null, true);
    this.jetGatling = new RuffySuper();
    this.ruffy_aura = new Ruffy_Pump_Aura(new Vector2d(this.pos.x - 0.2F, this.pos.y + 0.1F), true);
    this.ruffy_superhitspark = new JetPistole(new Vector2d(this.pos.x - 0.2F, this.pos.y + 0.1F), true);
    this.gomu_Gomu_Storm = new GomuGomuStorm(new Vector2d(), true);
    this.gomu_Gomu_Storm.dead = true;
    this.gear2aura = new RuffyGear2Aura(new Vector2d(), true);
    this.gear2aura.dead = false;
    this.dashDust = new DashDustAttack(new Vector2d(), true);
    this.pump_it_aura = new PumpItAura(new Vector2d(this.pos.x, this.pos.y), false);
    
    this.jetPistole_Blue1 = new JetPistoleBlueHit(new Vector2d(this.pos.x, this.pos.y), false);
    this.jetPistole_Blue2 = new JetPistoleBlueHit(new Vector2d(this.pos.x, this.pos.y), false);
    this.jetPistole_Blue3 = new JetPistoleBlueHit(new Vector2d(this.pos.x, this.pos.y), false);
    
    this.jetPistole_Blues = new ArrayList<>();
    this.jetPistole_Blues.add(this.jetPistole_Blue1);
    this.jetPistole_Blues.add(this.jetPistole_Blue2);
    this.jetPistole_Blues.add(this.jetPistole_Blue3);
    
    this.jetPistole_BigDusts = new ArrayList<>();
    this.jetPistoleBigDust_1 = new JetPistoleBigDust(new Vector2d(this.pos.x, this.pos.y), false);
    this.jetPistoleBigDust_2 = new JetPistoleBigDust(new Vector2d(this.pos.x, this.pos.y), false);
    this.jetPistoleBigDust_3 = new JetPistoleBigDust(new Vector2d(this.pos.x, this.pos.y), false);
    this.jetPistoleBigDust_4 = new JetPistoleBigDust(new Vector2d(this.pos.x, this.pos.y), false);
    this.jetPistoleBigDust_5 = new JetPistoleBigDust(new Vector2d(this.pos.x, this.pos.y), false);
    this.jetPistoleBigDust_6 = new JetPistoleBigDust(new Vector2d(this.pos.x, this.pos.y), false);
    this.jetPistoleBigDust_7 = new JetPistoleBigDust(new Vector2d(this.pos.x, this.pos.y), false);
    
    this.jetBigDustCircle = new JetBigDustCircle(new Vector2d(this.pos.x, this.pos.y), false);
    this.jetBigDustBackDust = new JetBigDustBackDust(new Vector2d(this.pos.x, this.pos.y), false);
    
    this.jetPistole_BigDusts.add(this.jetPistoleBigDust_1);
    this.jetPistole_BigDusts.add(this.jetPistoleBigDust_2);
    this.jetPistole_BigDusts.add(this.jetPistoleBigDust_3);
    this.jetPistole_BigDusts.add(this.jetPistoleBigDust_4);
    this.jetPistole_BigDusts.add(this.jetPistoleBigDust_5);
    this.jetPistole_BigDusts.add(this.jetPistoleBigDust_6);
    this.jetPistole_BigDusts.add(this.jetPistoleBigDust_7);
    
    this.jetGatlingArtWork = new JetGatling();
    this.jetPistoleArtWork = new JetPistol();
    setJetDust(new JetDust(new Vector2d(this.pos.x, this.pos.y), false));
    this.dashGatlingDust = new DashDust(new Vector2d(this.pos.x, this.pos.y), false);
    this.dust1 = new GigantoGatlingFist(new Vector2d(0.0F, 0.0F), true);
    this.effectManager = new EffectManager();
    
    this.jumpSound = "/audio/Ruffy_Jump.wav";
  }
  
  protected void init()
  {
    this.attacks.put("basicAttack11", new RuffyBasicAttack11(this, 0));
    this.attacks.put("basicAttack12", new RuffyBasicAttack12(this, 1));
    this.attacks.put("basicAttack13", new RuffyBasicAttack13(this, 2));
    this.attacks.put("basicAttack21", new RuffyBasicAttack21(this, 3));
    this.attacks.put("basicAttack22", new RuffyBasicAttack22(this, 4));
    this.attacks.put("basicAttack23", new RuffyBasicAttack23(this, 5));
    
    this.attacks.put("specialAttack2", new RuffyJetPistole(this, 10));
    this.attacks.put("specialAttack3", new RuffyJetPistoleFromAir(this, 11));
    this.attacks.put("specialAttack4", new RuffyKomboGear3(this, 13));
    this.attacks.put("specialAttack5", new RuffyJetGatling(this, 9));
    this.attacks.put("specialAttack6", new RuffyGigantoWhip(this, 7));
    this.attacks.put("specialAttack7", new RuffyKomboGear2(this, 12));
    
    this.attacks.put("airSpecialAttack1", new RuffyGomuGomuNoStorm(this, 8));
    this.attacks.put("gigantoGatling", new RuffyGigantoGatling(this, 6));
    this.attacks.put("modeGear2", new RuffyModeGear2(this, 14));
    
    this.attacks.put("runAttack1", new RuffyRunAttack1(this, 15));
    this.attacks.put("runAttack2", new RuffyRunAttack2(this, 16));
    this.attacks.put("jumpAttack", new RuffyJumpAttack(this, 17));
    
    this.attacks.put("dash", new RuffyDash(this, 18));
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
  
  public void checkDash()
  {
    ((Attack)this.attacks.get("dash")).check();
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
    this.upperJetPistole_Air = false;
    this.upperJetPistole_Punch = false;
    this.jetHammer = false;
    this.gatling = 40;
    this.SuperAttackIntro = 10;
    this.secondTeleport = false;
    this.gomu_Gomu_Storm.dead = true;
    this.releasedDusts.remove(this.gomu_Gomu_Storm);
    this.releasedDusts.remove(this.jetBigDustCircle);
    setKicks(10);
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
	  
	  this.releasedDusts.remove(this.gear2aura);
	  if (!this.statusLogic.isWinning()) {
	      return;
	    }  
	  
    setGear2On(false);
   
    switch (this.picManager.getCurrFrameIndex())
    {
    case 0: 
    	if(this.switcher){
    		sound("/audio/Ruffy_Won_Round.wav");
    		this.switcher = false;
    	}
      break;
    case 1: 
      break;
    case 2: 
      break;
    }
  }
  
  public void introduct()
  {
    if ((this.picManager.getCurrFrameIndex() == 3) && (this.switcher))
    {
      sound("/audio/Ruffy_Intro_Punch.wav");
      this.switcher = false;
    }
    if ((this.picManager.getCurrFrameIndex() == 4) && (!this.switcher))
    {
      sound("/audio/Ruffy_Dash.wav");
      this.switcher = true;
    }
    if (this.picManager.getAnimTime() == this.picManager.getTotalDuration())
    {
      this.statusLogic.setActionStates(ActionStates.STAND);
      this.switcher = true;
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
    this.physics.VY -= 0.5F;
    if (this.attackLogic.isAirSpecialAttacking1()) {
      ((Attack)this.attacks.get("airSpecialAttack1")).process();
    }
  }
  
  public void checkAirSpecialAttack1(Character defender)
  {
    ((Attack)this.attacks.get("airSpecialAttack1")).check();
  }
  
  public Image draw()
  {
    if (isGear2On()) {
      try
      {
        return this.effectManager.drawImage(this.picManager.getImage(), this.picManager.getCurrFrameIndex(), 
          this.picManager.getCurrentAnimation());
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    return this.picManager.getImage();
  }
  
  public void updateMiscs()
  {
    if (getAttackLogic().isGigantoGatling()) {
      ((Attack)this.attacks.get("gigantoGatling")).process();
    } else if (getAttackLogic().isGear2Transforming()) {
      ((Attack)this.attacks.get("modeGear2")).process();
    }
  }
  
  private void checkJetPistoling(Character defender)
  {
    ((Attack)this.attacks.get("gigantoGatling")).check();
  }
  
  private void checkGear2Storm(Character defender)
  {
    ((Attack)this.attacks.get("modeGear2")).check();
  }
  
  private void updateModeGear2()
  {
	if(!releasedDusts.contains(gear2aura))
	  this.releasedDusts.add(gear2aura);  
    
	this.gear2aura.pos.x = this.pos.x;
    this.gear2aura.pos.y = (this.pos.y + 0.25F);
    if (isPushNotDoneSofar())
    {
      this.tempSpeed = (0.0F + this.physics.getMaximumSpeed());
      this.tempAcc = (0.0F + this.physics.getACCELERATION());
      this.physics.setMaximumSpeed(this.tempSpeed * 1.5F);
      this.physics.setACCELERATION(this.tempAcc + 0.5F);
      setPushNotDoneSofar(false);
    }
  }
  
  public void loadFurtherImages(List<ArrayList<Image>> player_all, Map<String, ArrayList<Image>> player)
  {
    player_all.add(this.viewLogic.MAX_STANDARD_SPRITES+1, (ArrayList<Image>)player.get("gear2transform"));
    player_all.add(this.viewLogic.MAX_STANDARD_SPRITES+2, (ArrayList<Image>)player.get("gigantoGatling"));
  }
  
  public void checkFurtherCombos(List<ArrayList<String>> combos, List<String> result)
  {
    if (combos.get(18) == result) {
      GamePlayController.specialAttack(this, AttackStates.Gear2Transform, "gear2transform");
    }
  }
  
  public void checkFurtherAttacks(Character defender)
  {
    checkGear2Storm(defender);
    checkJetPistoling(defender);
  }
  
  public RuffyAttackLogic getAttackLogic()
  {
    return (RuffyAttackLogic)this.attackLogic;
  }
  
  public void makeBlueHit()
  {
    JetPistoleBlueHit currentBlueHit = (JetPistoleBlueHit)getJetPistole_Blues().get(getJetPistoleBlue_Id());
    setJetPistoleBlue_Id((getJetPistoleBlue_Id() + 1) % getJetPistole_Blues().size());
    currentBlueHit.dustAnimator.start();
    currentBlueHit.pos.x = this.enemy.pos.x;
    currentBlueHit.pos.y = (this.enemy.pos.y + 2.0F);
    currentBlueHit.dead = false;
    this.releasedDusts.add(currentBlueHit);
  }
  
  public void update(float delta)
  {
    super.update(delta);
    if (isGear2On()) {
      updateModeGear2();
    }
  }
  
  public float getRange()
  {
    return this.range;
  }
  
  public boolean isJetHammer()
  {
    return this.jetHammer;
  }
  
  public int getGatling_max()
  {
    return GatlingMax;
  }
  
  public int getGatling()
  {
    return this.gatling;
  }
  
  public int getSuperAttackIntro()
  {
    return this.SuperAttackIntro;
  }
  
  public boolean isSecondTeleport()
  {
    return this.secondTeleport;
  }
  
  public boolean isUpperJetPistole_Air()
  {
    return this.upperJetPistole_Air;
  }
  
  public boolean isUpperJetPistole_Punch()
  {
    return this.upperJetPistole_Punch;
  }
  
  public Random getRandom()
  {
    return this.random;
  }
  
  public GigantoGatlingFist getDust1()
  {
    return this.dust1;
  }
  
  public RuffySuper getJetGatling()
  {
    return this.jetGatling;
  }
  
  public Ruffy_Pump_Aura getRuffy_aura()
  {
    return this.ruffy_aura;
  }
  
  public JetPistole getRuffy_superhitspark()
  {
    return this.ruffy_superhitspark;
  }
  
  public RuffyGear2Aura getGear2aura()
  {
    return this.gear2aura;
  }
  
  public DashDustAttack getDashDust()
  {
    return this.dashDust;
  }
  
  public int getKicks()
  {
    return this.kicks;
  }
  
  public PumpItAura getPump_it_aura()
  {
    return this.pump_it_aura;
  }
  
  public JetPistoleBlueHit getJetPistole_Blue1()
  {
    return this.jetPistole_Blue1;
  }
  
  public JetPistoleBlueHit getJetPistole_Blue2()
  {
    return this.jetPistole_Blue2;
  }
  
  public JetPistoleBlueHit getJetPistole_Blue3()
  {
    return this.jetPistole_Blue3;
  }
  
  public ArrayList<JetPistoleBlueHit> getJetPistole_Blues()
  {
    return this.jetPistole_Blues;
  }
  
  public JetBigDustCircle getJetBigDustCircle()
  {
    return this.jetBigDustCircle;
  }
  
  public JetBigDustBackDust getJetBigDustBackDust()
  {
    return this.jetBigDustBackDust;
  }
  
  public JetPistoleBigDust getJetPistoleBigDust_1()
  {
    return this.jetPistoleBigDust_1;
  }
  
  public JetPistoleBigDust getJetPistoleBigDust_2()
  {
    return this.jetPistoleBigDust_2;
  }
  
  public JetPistoleBigDust getJetPistoleBigDust_3()
  {
    return this.jetPistoleBigDust_3;
  }
  
  public JetPistoleBigDust getJetPistoleBigDust_4()
  {
    return this.jetPistoleBigDust_4;
  }
  
  public JetPistoleBigDust getJetPistoleBigDust_5()
  {
    return this.jetPistoleBigDust_5;
  }
  
  public JetPistoleBigDust getJetPistoleBigDust_6()
  {
    return this.jetPistoleBigDust_6;
  }
  
  public JetPistoleBigDust getJetPistoleBigDust_7()
  {
    return this.jetPistoleBigDust_7;
  }
  
  public ArrayList<JetPistoleBigDust> getJetPistole_BigDusts()
  {
    return this.jetPistole_BigDusts;
  }
  
  public int getJetPistoleBlue_Id()
  {
    return this.jetPistoleBlue_Id;
  }
  
  public int getJetPistoleBigDust_Id()
  {
    return this.jetPistoleBigDust_Id;
  }
  
  public GomuGomuStorm getGomu_Gomu_Storm()
  {
    return this.gomu_Gomu_Storm;
  }
  
  public EffectManager getEffectManager()
  {
    return this.effectManager;
  }
  
  public boolean isStartAura()
  {
    return this.startAura;
  }
  
  public JetGatling getJetGatlingArtWork()
  {
    return this.jetGatlingArtWork;
  }
  
  public JetPistol getJetPistoleArtWork()
  {
    return this.jetPistoleArtWork;
  }
  
  public JetDust getJetDust()
  {
    return this.jetDust;
  }
  
  public DashDust getDashGatlingDust()
  {
    return this.dashGatlingDust;
  }
  
  public Gear2 getGear2()
  {
    return this.gear2;
  }
  
  public Gear3 getGear3()
  {
    return this.gear3;
  }
  
  public boolean isGear2On()
  {
    return this.isGear2On;
  }
  
  public float getTempSpeed()
  {
    return this.tempSpeed;
  }
  
  public float getTempAcc()
  {
    return this.tempAcc;
  }
  
  public boolean isPushNotDoneSofar()
  {
    return this.pushNotDoneSofar;
  }
  
  public void setJetPistoleBlue_Id(int jetPistoleBlue_Id)
  {
    this.jetPistoleBlue_Id = jetPistoleBlue_Id;
  }
  
  public void setJetPistoleBigDust_Id(int jetPistoleBigDust_Id)
  {
    this.jetPistoleBigDust_Id = jetPistoleBigDust_Id;
  }
  
  public void setKicks(int kicks)
  {
    this.kicks = kicks;
  }
  
  public void setGear2On(boolean isGear2On)
  {
    this.isGear2On = isGear2On;
  }
  
  public void setPushNotDoneSofar(boolean pushNotDoneSofar)
  {
    this.pushNotDoneSofar = pushNotDoneSofar;
  }
  
  public void setStartAura(boolean startAura)
  {
    this.startAura = startAura;
  }
  
  public void setJetDust(JetDust jetDust)
  {
    this.jetDust = jetDust;
  }
  
  public void initializeAI(GamePlayController controller)
  {
    this.aiManager = new RuffyKI(this, controller);
  }
  
  public void giveEnergyBack(Attack attack)
  {
    this.actualMagicpoints += attack.getRequiredEnergy();
  }
}
