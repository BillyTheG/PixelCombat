package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.JetDust;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuKaryuNoKagisume
  extends Attack
{
  private Natsu natsu;
  private JetDust jetDust;
  private int gakisume_max = 3;
  private int gakisume = this.gakisume_max;
  
  public NatsuKaryuNoKagisume(Natsu natsu, int id)
  {
    super(natsu, id);
    this.natsu = natsu;
    this.jetDust = new JetDust(new Vector2d(), true);
    
    setRequiredEnergy(20.0F);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        this.natsu.sound("/audio/natsu_karyuu.wav");
        this.jetDust.reset(new Vector2d(this.natsu.pos.x, this.natsu.pos.y), this.natsu.statusLogic.isRight());
        this.natsu.releasedDusts.add(this.jetDust);
        this.natsu.physics.VY = -20.0F;
        this.natsu.physics.update(this.natsu.delta);
        getUser().setSwitcher(false);
      }
      break;
    case 2: 
      if (!getUser().isSwitcher())
      {
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.sound("/audio/natsu_fire.wav");
        this.natsu.sound("/audio/natsu_kagisume.wav");
        getUser().setSwitcher(true);
      }
      this.natsu.physics.VX = (this.natsu.getDir() * 50.0F);
      break;
    case 8: 
      if ((getUser().isSwitcher()) && (this.natsu.picManager.isAlmostFinished(8)) && (this.gakisume > 0))
      {
        this.natsu.picManager.resetToIndex(3);
        this.natsu.statusLogic.setAHitDelay(false);
        this.gakisume -= 1;
      }
      if (this.gakisume == 0) {
        getUser().setSwitcher(false);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    this.natsu.sound("/audio/meleehit2.wav");
    this.natsu.makeFireSpark(this.natsu.enemy);
    if (!this.natsu.shaking) {
      this.natsu.shaking = true;
    }
    this.natsu.enemy.damage(this.natsu.getStrength() * 4.0F);
    this.natsu.sound(this.natsu.enemy.cry());
    if (!this.natsu.enemy.statusLogic.isKnockback())
    {
      this.natsu.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.natsu.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
      this.natsu.enemy.setKnockbackHeight(-20.0F);
      this.natsu.enemy.setKnockbackRange(30.0F);
    }
    else
    {
      this.natsu.comboTouch(this.natsu.enemy, -5.0F, 10.0F);
    }
    this.natsu.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.gakisume = this.gakisume_max;
  }
  
  public boolean isAttacking()
  {
    return this.natsu.attackLogic.isSpecialAttacking3();
  }
  
  public void resetStats()
  {
    this.gakisume = this.gakisume_max;
  }
}
