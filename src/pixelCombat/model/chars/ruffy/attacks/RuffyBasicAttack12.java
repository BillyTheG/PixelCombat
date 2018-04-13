package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyBasicAttack12
  extends Attack
{
  private Ruffy user;
  
  public RuffyBasicAttack12(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Jet_Hammer.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 3: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Jet_Hammer_End.wav");
        this.user.setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().enemy.damage(getUser().getStrength() * 5);
    getUser().sound(getUser().enemy.cry());
    getUser().sound("/audio/Ruffy_BasicAttack12Hit.wav");
    
    this.user.makeBlueHit();
    if (!getUser().enemy.statusLogic.isKnockback())
    {
      getUser().enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      getUser().enemy.setKnockbackHeight(-7.0F);
      getUser().enemy.setKnockbackRange(10.0F);
      getUser().enemy.checkOnAir();
    }
    else
    {
      getUser().comboTouch(getUser().enemy, -14.0F, 1.0F);
    }
    getUser().statusLogic.setAHitDelay(true);
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
