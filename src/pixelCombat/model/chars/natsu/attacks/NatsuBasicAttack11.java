package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuBasicAttack11
  extends Attack
{
  private Natsu natsu;
  
  public NatsuBasicAttack11(Natsu user, int id)
  {
    super(user, id);
    this.natsu = user;
  }
  
  public void process() {}
  
  public void checkContent()
  {
    this.natsu.sound("/audio/Natsu_Punch1_hit.wav");
    this.natsu.enemy.damage(this.natsu.getStrength());
    this.natsu.sound(this.natsu.enemy.cry());
    this.natsu.statusLogic.setAHitDelay(true);
    
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
      this.natsu.comboTouch(this.natsu.enemy, -4.0F, 1.0F);
    }
  }
  
  public void checkFinished()
  {
    getUser().sound(getUser().attack());
    getUser().sound("/audio/punch.wav");
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking1();
  }
  
  public void resetStats() {}
}
