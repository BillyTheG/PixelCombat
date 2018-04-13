package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuBasicAttack12
  extends Attack
{
  private Natsu natsu;
  
  public NatsuBasicAttack12(Natsu user, int id)
  {
    super(user, id);
    this.natsu = user;
  }
  
  public void process()
  {
    switch (this.natsu.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/natsu_basicAttack3.wav");
        this.natsu.setSwitcher(false);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    this.natsu.enemy.damage(this.natsu.getStrength() * 5);
    this.natsu.sound(this.natsu.enemy.cry());
    this.natsu.sound("/audio/punchHit.wav");
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
    return getUser().attackLogic.isBasicAttacking2();
  }
  
  public void resetStats() {}
}
