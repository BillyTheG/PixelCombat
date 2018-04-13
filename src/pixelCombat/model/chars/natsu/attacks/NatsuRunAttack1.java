package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuRunAttack1
  extends Attack
{
  private Natsu natsu;
  
  public NatsuRunAttack1(Natsu natsu, int id)
  {
    super(natsu, id);
    this.natsu = natsu;
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        this.natsu.sound("/audio/natsu_yokugeki.wav");
        getUser().setSwitcher(false);
      }
      this.natsu.physics.VX = (this.natsu.getDir() * 45.0F);
      break;
    case 3: 
      if (!getUser().isSwitcher())
      {
        this.natsu.sound("/audio/natsu_explosion_middle.wav");
        this.natsu.getDust1().reset(new Vector2d(this.natsu.pos.x, this.natsu.pos.y - 0.35F), this.natsu.statusLogic.isRight());
        this.natsu.releasedDusts.add(this.natsu.getDust1());
        getUser().setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    this.natsu.enemy.damage(this.natsu.getStrength() * 2.0F);
    this.natsu.sound("/audio/natsu_explosion_hit.wav");
    this.natsu.sound(this.natsu.enemy.cry());
    this.natsu.makeFireSpark(this.natsu.enemy);
    if (!this.natsu.enemy.statusLogic.isKnockback())
    {
      this.natsu.enemy.setKnockbackHeight(-25.0F);
      this.natsu.enemy.setKnockbackRange(7.0F);
      this.natsu.enemy.checkOnAir();
      this.natsu.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.natsu.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.natsu.comboTouch(this.natsu.enemy, -20.0F, 7.0F);
    }
    this.natsu.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished() {}
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isRunAttacking1();
  }
  
  public void resetStats() {}
}
