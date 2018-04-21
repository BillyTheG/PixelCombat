package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroRunAttack1
  extends Attack
{
  private Zorro user;
  private boolean firstHit;
  private boolean secondHit;
  
  public ZorroRunAttack1(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
  }
  
  public void process()
  {
  
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_RunAttack1.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 4: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_Slash_4.wav");
        getUser().setSwitcher(true);
        this.firstHit = true;
      }
      break;
    case 5: 
      if (getUser().isSwitcher())
      {
        this.user.physics.VX = 0.0F;
        getUser().setSwitcher(false);
      }
      break;
    case 8: 
      if (!getUser().isSwitcher())
      {
        this.user.statusLogic.setAHitDelay(false);
        this.user.physics.VX += this.user.getDir() * 20.0F;
        getUser().sound("/audio/Zorro_Sword_Slash_5.wav");
        getUser().setSwitcher(true);
        this.firstHit = false;
        this.secondHit = true;
      }
      break;
    case 9: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_Slash_5.wav");
        
        this.user.physics.VX += this.user.getDir() * 25.0F;
        
        getUser().setSwitcher(false);
      }
      break;
    }
  }
  

  public void checkContent()
  {
    if (this.firstHit) {
      getUser().sound("/audio/Zorro_Sword_Slash_Hit_3.wav");
    }
    if (this.secondHit) {
      getUser().sound("/audio/Zorro_Sword_Slash_Hit_4.wav");
    }
    getUser().enemy.damage(getUser().getStrength() * 2.0F);
    getUser().sound(getUser().enemy.cry());
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-15.0F);
      this.user.enemy.setKnockbackRange(5.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -7.0F, 30.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.secondHit = false;
    this.firstHit = false;
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isRunAttacking1();
  }
  
  public void resetStats()
  {
    this.secondHit = false;
    this.firstHit = false;
  }
}
