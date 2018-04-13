package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuBasicAttack21
  extends Attack
{
  private Natsu natsu;
  
  public NatsuBasicAttack21(Natsu user, int id)
  {
    super(user, id);
    this.natsu = user;
  }
  
  public void process() {}
  
  public void checkContent()
  {
    this.natsu.enemy.damage(this.natsu.getStrength() * 5);
    this.natsu.sound(this.natsu.enemy.cry());
    this.natsu.sound("/audio/Natsu_Punch2_hit.wav");
    this.natsu.makeNatsuPunch();
    if (!this.natsu.enemy.statusLogic.isKnockback())
    {
      this.natsu.enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      this.natsu.enemy.setKnockbackHeight(-7.0F);
      this.natsu.enemy.setKnockbackRange(10.0F);
      this.natsu.enemy.checkOnAir();
    }
    else
    {
      this.natsu.comboTouch(this.natsu.enemy, -14.0F, 1.0F);
    }
    this.natsu.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    getUser().sound(getUser().attack());
    getUser().sound("/audio/punch.wav");
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking21();
  }
  
  public void resetStats() {}
}
