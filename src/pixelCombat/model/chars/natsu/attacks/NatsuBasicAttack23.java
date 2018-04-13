package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.Natsu_Fire_Wind;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuBasicAttack23
  extends Attack
{
  private Natsu natsu;
  private Natsu_Fire_Wind fireWheel = null;
  
  public NatsuBasicAttack23(Natsu user, int id)
  {
    super(user, id);
    this.natsu = user;
    this.fireWheel = new Natsu_Fire_Wind(new Vector2d(), true);
  }
  
  public void process()
  {
    switch (this.natsu.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/natsu_basic23.wav");
        this.natsu.setSwitcher(false);
      }
      break;
    case 1: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.sound("/audio/natsu_23.wav");
        float deltaX = this.natsu.statusLogic.isRight() ? 0.8F : -1.15F;
        this.fireWheel.reset(new Vector2d(this.natsu.pos.x + deltaX, this.natsu.pos.y), this.natsu.statusLogic.isRight());
        this.natsu.releasedDusts.add(this.fireWheel);
        this.natsu.setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    this.natsu.enemy.damage(this.natsu.getStrength() * 3);
    this.natsu.sound(this.natsu.enemy.cry());
    this.natsu.sound("/audio/natsu_23_hit.wav");
    
    this.natsu.makeFireSpark(this.natsu.enemy);
    if (!this.natsu.enemy.statusLogic.isKnockback())
    {
      this.natsu.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.natsu.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
      this.natsu.enemy.setKnockbackHeight(-10.0F);
      this.natsu.enemy.setKnockbackRange(40.0F);
    }
    else
    {
      this.natsu.comboTouch(this.natsu.enemy, -10.0F, 40.0F);
    }
    this.natsu.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    getUser().sound(getUser().attack());
    this.natsu.releasedDusts.remove(this.fireWheel);
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking23();
  }
  
  public void resetStats()
  {
    this.natsu.releasedDusts.remove(this.fireWheel);
  }
}
