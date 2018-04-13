package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyBasicAttack13
  extends Attack
{
  private Ruffy user;
  
  public RuffyBasicAttack13(Ruffy user, int id)
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
        getUser().sound("/audio/Ruffy_Stretch.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 8: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Ruffy_BasicAttack_Finish.wav");
        getUser().setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().sound("/audio/riffle_end.wav");
    getUser().enemy.damage(getUser().getStrength() * 5.0F);
    getUser().sound("/audio/meleehit2.wav");
    getUser().sound(getUser().enemy.cry());
    
    this.user.makeBlueHit();
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    if (!getUser().enemy.statusLogic.isKnockback())
    {
      getUser().enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      getUser().enemy.physics.VX = (-getUser().enemy.getDir() * 18.0F);
      getUser().enemy.physics.VY = -20.0F;
      getUser().enemy.setKnockbackHeight(-7.0F);
      getUser().enemy.setKnockbackRange(10.0F);
      getUser().enemy.checkOnAir();
    }
    else
    {
      this.user.comboTouch(getUser().enemy, -15.0F, 15.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished() {}
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isSpecialAttacking1();
  }
  
  public void resetStats() {}
}
