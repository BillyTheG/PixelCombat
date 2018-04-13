package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.dusts.OniGiriSmoke;
import pixelCombat.dusts.ZorroDemonAura;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroOniGiri
  extends Attack
{
  private Zorro user;
  private int shadows = 5;
  private float max_distance = 20.0F;
  private float distance;
  private ZorroDemonAura demonAura;
  
  public ZorroOniGiri(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(70.0F);
    this.demonAura = new ZorroDemonAura(new Vector2d(), true);
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        this.user.superAttacking = true;
        this.user.specialBG.reset();
        this.user.freeze = true;
        this.user.freeze_loop = true;
        this.user.borderEffecting = true;
        this.user.statusLogic.setFocused(true);
        
        this.demonAura.reset(new Vector2d(this.user.pos.x, this.user.pos.y + 1.0F), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.demonAura);
        
        getUser().sound("/audio/Zorro_Demon_Aura.wav");
        
        getUser().sound("/audio/Zorro_Santoryou_Ho_Gi.wav");
        getUser().sound("/audio/Zorro_OniGiri_Intro.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 6: 
      if ((!this.user.isSwitcher()) && (this.shadows > 0) && (this.user.picManager.isAlmostFinished(6)))
      {
        this.shadows -= 1;
        this.user.picManager.resetToIndex(4);
      }
      else if (this.shadows <= 0)
      {
        this.user.setSwitcher(true);
      }
      break;
    case 8: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_OniGiri.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 9: 
      this.user.physics.VX = (this.user.getDir() * 100.0F);
      this.distance += this.user.getDir() * this.user.physics.tickDistanceX(this.user.delta);
      OniGiriSmoke currentOniGiriSmoke = (OniGiriSmoke)this.user.getOniGiriSmokes().get(this.user.getOniGiriSmokeId());
      this.user.setOniGiriSmokeId((this.user.getOniGiriSmokeId() + 1) % this.user.getOniGiriSmokes().size());
      currentOniGiriSmoke.reset(new Vector2d(this.user.pos.x, this.user.pos.y + 1.25F), this.user.statusLogic.isRight());
      this.user.releasedDusts.add(currentOniGiriSmoke);
      break;
    case 10: 
      this.user.physics.VX = (this.user.getDir() * 100.0F);
      this.distance += this.user.getDir() * this.user.physics.tickDistanceX(this.user.delta);
      if ((this.user.picManager.isAlmostFinished(10)) && (Math.abs(this.distance) < this.max_distance))
      {
        this.user.picManager.resetToIndex(9);
      }
      else
      {
        this.user.getOniGiriSlash().reset(new Vector2d(this.user.pos.x - this.user.getDir() * 7.0F, this.user.pos.y), true);
        this.user.releasedDusts.add(this.user.getOniGiriSlash());
      }
      this.user.setSwitcher(true);
      break;
    case 11: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_OniGiri_Slash.wav");
        this.user.physics.VX = 0.0F;
        this.user.setSwitcher(false);
      }
      break;
    case 16: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        this.user.setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().enemy.damage(getUser().getStrength() * 35.0F);
    getUser().sound(getUser().enemy.cry());
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_5.wav");
    
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-25.0F);
      this.user.enemy.setKnockbackRange(5.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -7.0F, 30.0F);
    }
    getUser().statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.user.freeze_loop = false;
    this.user.superAttacking = false;
    this.user.statusLogic.setFocused(false);
    this.shadows = 5;
    this.distance = 0.0F;
    this.user.borderEffecting = false;
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isSpecialAttacking2();
  }
  
  public void resetStats()
  {
    this.user.borderEffecting = false;
    this.user.superAttacking = false;
    this.shadows = 5;
    this.distance = 0.0F;
  }
}
