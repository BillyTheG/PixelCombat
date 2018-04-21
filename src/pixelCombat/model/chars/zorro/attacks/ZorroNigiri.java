package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.artworks.NigiriArtwork;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.dusts.DashWind;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.Spirit;
import pixelCombat.model.chars.Zorro;

public class ZorroNigiri
  extends Attack
{
  private Zorro user;
  private int standingsA;
  public boolean firstHit = false;
  public boolean secondHit = false;
  public boolean thirdHit = false;
  private float shadowBuffer = 0.0F;
  private float shadowMaxBuffer = 0.04F;
  private float posY = -1.0F;
  private DashWind dashWind;
  private NigiriArtwork nigiriArtWork;
  
  
  public ZorroNigiri(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(20.0F);
    this.dashWind = new DashWind(new Vector2d(), true);
    this.nigiriArtWork = new NigiriArtwork();
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
    	this.nigiriArtWork.reset();
    	user.releasedArtWorks.add(nigiriArtWork);
        this.standingsA = 6;
        this.user.superAttacking = true;
        this.user.specialBG.reset();
        this.user.freeze = true;
        this.user.freeze_loop = true;
        this.user.statusLogic.setFocused(true);
        this.user.setBorderEffecting(true);
        getUser().sound("/audio/Zorro_Nittoryou2.wav");
        this.user.setSwitcher(false);
        this.user.getUpAndDownBorder().reset();
      }
      break;
    case 3: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Nigiri.wav");
        this.user.setSwitcher(true);
      }
      break;
    case 5: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Nigiri_Attack1.wav");
        this.firstHit = true;
        
        this.user.setSwitcher(false);
      }
      updateShadows();
      break;
    case 6: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Nigiri_Slash.wav");
        this.user.setSwitcher(true);
      }
      updateShadows();
      break;
    case 7: 
      updateShadows();
      break;
    case 9: 
      if ((this.user.isSwitcher()) && (this.standingsA > 0) && (this.user.picManager.isAlmostFinished(9)))
      {
        this.standingsA -= 1;
        this.user.picManager.resetToIndex(8);
      }
      else if (this.standingsA <= 0)
      {
        this.user.setSwitcher(false);
      }
      break;
    case 13: 
      if (!this.user.isSwitcher())
      {
        getUser().statusLogic.setAHitDelay(false);
        getUser().sound("/audio/teleport.wav");
        this.user.releasedDusts.add(this.dashWind);
        this.user.setSwitcher(true);
      }
      updateShadows();
      
      this.user.physics.VX = (this.user.getDir() * 60.0F);
      break;
    case 14: 
      if (this.user.isSwitcher())
      {
        this.user.physics.VX = 0.0F;
        getUser().sound("/audio/Zorro_Nigiri_Attack2.wav");
        getUser().sound("/audio/Zorro_Nigiri_Slash.wav");
        this.secondHit = true;
        this.shadowMaxBuffer = 0.08F;
        this.user.setSwitcher(false);
      }
      break;
    case 20: 
      if (!this.user.isSwitcher())
      {
        getUser().statusLogic.setAHitDelay(false);
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        this.user.setSwitcher(true);
      }
      break;
    case 25: 
      if (this.user.isSwitcher())
      {
        getUser().statusLogic.setAHitDelay(false);
        getUser().sound("/audio/Zorro_Nigiri_Jump.wav");
        this.thirdHit = true;
        this.user.setSwitcher(false);
      }
      updateShadows();
      this.user.physics.VY = -23.0F;
      break;
    case 26: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Nigiri_Attack3.wav");
        this.user.setSwitcher(true);
      }
      updateShadows();
      break;
    case 27: 
      updateShadows();
      break;
    case 28: 
      if (this.posY == -1.0F) {
        this.posY = this.user.pos.y;
      }
      this.user.pos.y = this.posY;
      
      updateShadows();
      this.user.statusLogic.setFocused(false);
      break;
    case 29: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        this.user.setSwitcher(false);
        this.user.setBorderEffecting(false);
      }
      break;
    }
    this.user.getUpAndDownBorder().update(this.user.delta);
    checkDashWind();
  }
  
  private void checkDashWind()
  {
    if (this.user.picManager.getCurrFrameIndex() == 13) {
      this.dashWind.repositionate(new Vector2d(this.user.pos.x, this.user.pos.y + 1.75F), this.user.statusLogic.isRight());
    } else {
      this.user.releasedDusts.remove(this.dashWind);
    }
  }
  
  private void updateShadows()
  {
    this.shadowBuffer += this.user.delta;
    if (this.shadowBuffer > this.shadowMaxBuffer)
    {
      this.user.spirits.add(new Spirit(this.user.draw(), this.user.pos, this.user.statusLogic.isRight()));
      this.shadowBuffer = 0.0F;
    }
  }
  
  public void checkContent()
  {
    getUser().enemy.damage(getUser().getStrength() * 15.0F);
    getUser().sound(getUser().enemy.cry());
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_5.wav");
    
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (this.firstHit) {
      doFirst();
    }
    if (this.secondHit) {
      doSecond();
    }
    if (this.thirdHit) {
      doThird();
    }
    this.user.freeze = false;
    this.user.freeze_loop = false;
    
    this.user.enemy.checkOnAir();
    this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
    this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    
    getUser().statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.user.superAttacking = false;
    this.user.statusLogic.setFocused(false);
    this.standingsA = 6;
    this.shadowMaxBuffer = 0.04F;
    this.firstHit = false;
    this.secondHit = false;
    this.thirdHit = false;
    this.user.setBorderEffecting(false);
    this.posY = -1.0F;
    this.user.releasedDusts.remove(this.dashWind);
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isSpecialAttacking6();
  }
  
  public void resetStats()
  {
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.user.superAttacking = false;
    this.user.statusLogic.setFocused(false);
    this.standingsA = 6;
    this.shadowMaxBuffer = 0.04F;
    this.firstHit = false;
    this.secondHit = false;
    this.thirdHit = false;
    this.posY = -1.0F;
    this.user.setBorderEffecting(false);
    this.user.releasedDusts.remove(this.dashWind);
  }
  
  private void stableEnemy()
  {
    this.user.enemy.physics.VX = 0.0F;
    this.user.enemy.physics.VY = 0.0F;
    this.user.enemy.setKnockbackHeight(0.0F);
    this.user.enemy.setKnockbackRange(0.0F);
  }
  
  public void doFirst()
  {
    stableEnemy();
    this.user.enemy.setKnockbackHeight(-30.0F);
    this.user.enemy.setKnockbackRange(0.0F);
    this.firstHit = false;
  }
  
  public void doSecond()
  {
    stableEnemy();
    this.user.enemy.setKnockbackHeight(-20.0F);
    this.user.enemy.setKnockbackRange(0.0F);
    this.secondHit = false;
  }
  
  public void doThird()
  {
    stableEnemy();
    this.user.enemy.setKnockbackHeight(-15.0F);
    this.user.enemy.setKnockbackRange(0.0F);
    this.thirdHit = false;
  }
}
