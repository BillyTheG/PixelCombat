package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.RuffyPunch;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyBasicAttack11
  extends Attack
{
  public RuffyBasicAttack11(Ruffy user, int id)
  {
    super(user, id);
  }
  
  public void process() {}
  
  public void checkContent()
  {
    getUser().sound("/audio/punches.wav");
    getUser().enemy.damage(getUser().getStrength() * 2);
    getUser().sound(getUser().enemy.cry());
    getUser().statusLogic.setAHitDelay(true);
    
    RuffyPunch ruffyPunch = new RuffyPunch(new Vector2d(getUser().enemy.pos.x - 
      getUser().getDir() * 0.55F, getUser().getGroundLevel() - 1.0F), 
      getUser().enemy.statusLogic.isRight());
    getUser().enemy.releasedDusts.add(ruffyPunch);
    if (!getUser().enemy.statusLogic.isKnockback())
    {
      getUser().enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      getUser().enemy.setKnockbackHeight(-7.0F);
      getUser().enemy.setKnockbackRange(10.0F);
      getUser().enemy.checkOnAir();
    }
    else
    {
      getUser().comboTouch(getUser().enemy, -4.0F, 1.0F);
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
