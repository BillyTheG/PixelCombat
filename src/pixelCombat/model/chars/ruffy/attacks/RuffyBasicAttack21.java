package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyBasicAttack21
  extends Attack
{
  private Ruffy user;
  
  public RuffyBasicAttack21(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
  }
  
  public void process() {}
  
  public void checkContent()
  {
    getUser().sound("/audio/punchHit.wav");
    this.user.enemy.damage(this.user.getStrength());
    getUser().sound(this.user.enemy.cry());
    this.user.statusLogic.setAHitDelay(true);
    
    this.user.makeBlueHit();
    
    this.user.enemy.physics.VX += -this.user.enemy.getDir() * 5.0F;
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      this.user.enemy.setKnockbackHeight(-7.0F);
      this.user.enemy.setKnockbackRange(10.0F);
      this.user.enemy.checkOnAir();
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -4.0F, 1.0F);
    }
  }
  
  public void checkFinished()
  {
    getUser().sound("/audio/Ruffy_BasicAttack2.wav");
    getUser().sound("/audio/punch.wav");
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking21();
  }
  
  public void resetStats() {}
}
