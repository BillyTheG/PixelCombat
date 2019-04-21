package pixelCombat.model.chars;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.ai.NatsuKi;
import pixelCombat.auras.Natsu_Aura;
import pixelCombat.auras.Natsu_Aurabeam;
import pixelCombat.controller.GamePlayController;
import pixelCombat.dusts.FireSpark;
import pixelCombat.dusts.Fire_Dragon;
import pixelCombat.dusts.Karyuu_No_teki;
import pixelCombat.dusts.Lightning;
import pixelCombat.dusts.NatsuPunch;
import pixelCombat.dusts.ZorroSpecialBG;
import pixelCombat.enums.ActionStates;
import pixelCombat.model.Attack;
import pixelCombat.model.Character;
import pixelCombat.model.Dust;
import pixelCombat.model.chars.natsu.attacks.NatsuBasicAttack11;
import pixelCombat.model.chars.natsu.attacks.NatsuBasicAttack12;
import pixelCombat.model.chars.natsu.attacks.NatsuBasicAttack13;
import pixelCombat.model.chars.natsu.attacks.NatsuBasicAttack21;
import pixelCombat.model.chars.natsu.attacks.NatsuBasicAttack22;
import pixelCombat.model.chars.natsu.attacks.NatsuBasicAttack23;
import pixelCombat.model.chars.natsu.attacks.NatsuDash;
import pixelCombat.model.chars.natsu.attacks.NatsuGurenBakurenji;
import pixelCombat.model.chars.natsu.attacks.NatsuGurenHooken;
import pixelCombat.model.chars.natsu.attacks.NatsuJumpAttack;
import pixelCombat.model.chars.natsu.attacks.NatsuKaryuNoHooken;
import pixelCombat.model.chars.natsu.attacks.NatsuKaryuNoKagisume;
import pixelCombat.model.chars.natsu.attacks.NatsuKaryuNoTekken;
import pixelCombat.model.chars.natsu.attacks.NatsuRainenNoMahoNoPika;
import pixelCombat.model.chars.natsu.attacks.NatsuRunAttack1;
import pixelCombat.model.chars.natsu.attacks.NatsuRunAttack2;
import pixelCombat.projectiles.ProjectileManager;

public class Natsu
  extends Character
{
  public Random random = new Random();
  public float karyuuTekkenRange = 6.0F;
  private Karyuu_No_teki dust1 = null;
  private Dust dust2 = null;
  private Fire_Dragon dust3 = null;
  private Natsu_Aura aura = null;
  private Lightning lightning = null;
  private Natsu_Aurabeam beam = null;
  private FireSpark firespark1 = null;
  private FireSpark firespark2 = null;
  private FireSpark firespark3 = null;
  public ArrayList<FireSpark> firesparks;
  public int firesparks_id = 0;
  private NatsuPunch natsuPunch1 = null;
  private NatsuPunch natsuPunch2 = null;
  private List<NatsuPunch> natsuPunches;
  private int natsuPunch_id = 0;
  
  public Natsu(String name, int lifePoints, Vector2d pos, Vector2d dir, int maxLifePoints, ProjectileManager projectileManager)
  {
    super(name, lifePoints, 8.5F, 5.0F, pos, 5, maxLifePoints, 5, 5, 2.0F, "/audio/natsu_is_Hit.wav", "/audio/natsu_basicAttack.wav", "/audio/natsu_basicAttack3.wav", "/audio/Ruffy_Death.wav", projectileManager);
    setDust1(new Karyuu_No_teki(new Vector2d(this.pos.x, this.pos.y), true));
    setDust2(getDust1());
    setDust3(new Fire_Dragon(new Vector2d(this.pos.x, this.pos.y), true));
    setAura(new Natsu_Aura(new Vector2d(this.pos.x, this.pos.y), false));
    
    setBeam(new Natsu_Aurabeam(new Vector2d(this.pos.x, this.pos.y), false));
    setLightning(new Lightning(new Vector2d(this.pos.x, this.pos.y), false));
    
    this.specialBG = new ZorroSpecialBG(null, true);
    
    this.firespark1 = new FireSpark(new Vector2d(this.pos.x, this.pos.y), false);
    this.firespark2 = new FireSpark(new Vector2d(this.pos.x, this.pos.y), false);
    this.firespark3 = new FireSpark(new Vector2d(this.pos.x, this.pos.y), false);
    
    this.firesparks = new ArrayList<>();
    this.firesparks.add(this.firespark1);
    this.firesparks.add(this.firespark2);
    this.firesparks.add(this.firespark3);
    
    this.natsuPunch1 = new NatsuPunch(new Vector2d(this.pos.x, this.pos.y), true);
    this.natsuPunch2 = new NatsuPunch(new Vector2d(this.pos.x, this.pos.y), true);
    setJetPistoles(new ArrayList<>());
    getNatsuPunch().add(this.natsuPunch1);
    getNatsuPunch().add(this.natsuPunch2);
    
    this.attacks.put("basicAttack11", new NatsuBasicAttack11(this, 0));
    this.attacks.put("basicAttack12", new NatsuBasicAttack12(this, 1));
    this.attacks.put("basicAttack13", new NatsuBasicAttack13(this, 2));
    this.attacks.put("basicAttack21", new NatsuBasicAttack21(this, 3));
    this.attacks.put("basicAttack22", new NatsuBasicAttack22(this, 4));
    this.attacks.put("basicAttack23", new NatsuBasicAttack23(this, 5));
    
    this.attacks.put("specialAttack2", new NatsuKaryuNoTekken(this, 10));
    this.attacks.put("specialAttack3", new NatsuKaryuNoKagisume(this, 11));
    this.attacks.put("specialAttack4", new NatsuKaryuNoHooken(this, 13));
    this.attacks.put("specialAttack5", new NatsuGurenHooken(this, 9));
    this.attacks.put("specialAttack6", new NatsuRainenNoMahoNoPika(this, 7));
    this.attacks.put("specialAttack7", new NatsuGurenBakurenji(this, 9));
    
    this.attacks.put("runAttack1", new NatsuRunAttack1(this, 15));
    this.attacks.put("runAttack2", new NatsuRunAttack2(this, 16));
    this.attacks.put("jumpAttack", new NatsuJumpAttack(this, 17));
    
    this.attacks.put("dash", new NatsuDash(this, 17));
    
    this.jumpSound = "/audio/Natsu_Jump.wav";
    this.dashSound = "/audio/natsu_dash.wav";
    this.dashSound1 = "/audio/natsu_dash.wav";
  }
  
  public void updateDash()
  {
    ((Attack)this.attacks.get("dash")).process();
  }
  
  public void updateAttack()
  {
    if (this.attackLogic.isBasicAttacking1()) {
      ((Attack)this.attacks.get("basicAttack11")).process();
    } else if (this.attackLogic.isBasicAttacking2()) {
      ((Attack)this.attacks.get("basicAttack12")).process();
    } else if (this.attackLogic.isBasicAttacking2()) {
      ((Attack)this.attacks.get("basicAttack21")).process();
    } else if (this.attackLogic.isBasicAttacking22()) {
      ((Attack)this.attacks.get("basicAttack22")).process();
    } else if (this.attackLogic.isBasicAttacking23()) {
      ((Attack)this.attacks.get("basicAttack23")).process();
    } else if (this.attackLogic.isJumpAttacking1()) {
      ((Attack)this.attacks.get("jumpAttack")).process();
    }
  }
  
  public void updateSpecial()
  {
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
  
  public void resetCharStats()
  {
    this.switcher = true;
    this.superAttacking = false;
    this.releasedDusts.remove(getDust2());
    this.releasedDusts.remove(getDust3());
    this.statusLogic.setAHitDelay(false);
    this.freeze_loop = false;
    this.statusLogic.setFocused(false);
    for (String a : this.attacks.keySet()) {
      ((Attack)this.attacks.get(a)).resetStats();
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
  
  public void checkBasicAttack(Character defender)
  {
    ((Attack)this.attacks.get("basicAttack11")).check();
  }
  
  public void checkBasicAttack1(Character defender)
  {
    ((Attack)this.attacks.get("basicAttack12")).check();
  }
  
  public void checkJumpAttack(Character defender)
  {
    ((Attack)this.attacks.get("jumpAttack")).check();
  }
  
  public void checkDash()
  {
    ((Attack)this.attacks.get("dash")).check();
  }
  
  public void dying()
  {
    switch (this.picManager.getCurrFrameIndex())
    {
    case 0: 
      isSwitcher();
      
      break;
    case 1: 
      break;
    case 2: 
      break;
    }
  }
  
  public void finishing()
  {
    if (!this.statusLogic.isWinning()) {
      return;
    }
    switch (this.picManager.getCurrFrameIndex())
    {
    case 0: 
      break;
    case 1: 
      if (isSwitcher())
      {
        sound("/audio/natsu_muwata.wav");
        setSwitcher(false);
      }
      break;
    case 5: 
      if (!isSwitcher())
      {
        sound("/audio/Natsu_Happy_Finish.wav");
        setSwitcher(true);
      }
      break;
    }
  }
  
  public void introduct()
  {
    if (this.picManager.getAnimTime() == 0.0F) {
      sound("/audio/natsu_intro.wav");
    }
    if (this.picManager.getAnimTime() == this.picManager.getTotalDuration()) {
      this.statusLogic.setActionStates(ActionStates.STAND);
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
  
  public void makeFireSpark(Character defender)
  {
    float deltaX = this.random.nextFloat() * 1.5F;
    float deltaY = this.random.nextFloat() * 1.5F;
    FireSpark currentFireSpark = (FireSpark)this.firesparks.get(this.firesparks_id);
    this.firesparks_id = ((this.firesparks_id + 1) % this.firesparks.size());
    currentFireSpark.dustAnimator.start();
    currentFireSpark.pos.x = (defender.pos.x + deltaX);
    currentFireSpark.pos.y = (defender.pos.y + deltaY);
    currentFireSpark.dead = false;
    this.releasedDusts.add(currentFireSpark);
  }
  
  public void makeNatsuPunch()
  {
    NatsuPunch currentNatsuPunch = (NatsuPunch)getNatsuPunch().get(getNatsuPunch_id());
    setNatsuPunch_id((getNatsuPunch_id() + 1) % getNatsuPunch().size());
    
    currentNatsuPunch.dustAnimator.start();
    currentNatsuPunch.pos.x = this.enemy.pos.x;
    currentNatsuPunch.pos.y = this.enemy.pos.y;
    currentNatsuPunch.dead = false;
    this.releasedDusts.add(currentNatsuPunch);
  }
  
  public void checkAirSpecialAttack1(Character defender) {}
  
  public void updateAirSpecialAttack() {}
  
  public void updateMiscs() {}
  
  public void loadFurtherImages(List<ArrayList<Image>> player_all, Map<String, ArrayList<Image>> player1) {}
  
  public void checkFurtherCombos(List<ArrayList<String>> combos, List<String> result) {}
  
  public void checkFurtherAttacks(Character defender) {}
  
  protected void init() {}
  
  public void initializeAI(GamePlayController controller)
  {
    this.aiManager = new NatsuKi(this, controller);
  }
  
  public Lightning getLightning()
  {
    return this.lightning;
  }
  
  public void setLightning(Lightning lightning)
  {
    this.lightning = lightning;
  }
  
  public Karyuu_No_teki getDust1()
  {
    return this.dust1;
  }
  
  public void setDust1(Karyuu_No_teki dust1)
  {
    this.dust1 = dust1;
  }
  
  public Dust getDust2()
  {
    return this.dust2;
  }
  
  public void setDust2(Dust dust2)
  {
    this.dust2 = dust2;
  }
  
  public Natsu_Aura getAura()
  {
    return this.aura;
  }
  
  public void setAura(Natsu_Aura aura)
  {
    this.aura = aura;
  }
  
  public Natsu_Aurabeam getBeam()
  {
    return this.beam;
  }
  
  public void setBeam(Natsu_Aurabeam beam)
  {
    this.beam = beam;
  }
  
  public Fire_Dragon getDust3()
  {
    return this.dust3;
  }
  
  public void setDust3(Fire_Dragon dust3)
  {
    this.dust3 = dust3;
  }
  
  public int getNatsuPunch_id()
  {
    return this.natsuPunch_id;
  }
  
  public void setNatsuPunch_id(int jetPistoles_id)
  {
    this.natsuPunch_id = jetPistoles_id;
  }
  
  public List<NatsuPunch> getNatsuPunch()
  {
    return this.natsuPunches;
  }
  
  public void setJetPistoles(List<NatsuPunch> jetPistoles)
  {
    this.natsuPunches = jetPistoles;
  }
}
