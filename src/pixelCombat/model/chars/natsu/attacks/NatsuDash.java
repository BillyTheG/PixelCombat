package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.NatsuDashDust;
import pixelCombat.enums.ActionStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuDash
  extends Attack
{
  private Natsu natsu;
  private NatsuDashDust natsuDash;
  
  public NatsuDash(Natsu natsu, int id)
  {
    super(natsu, id);
    this.natsu = natsu;
    this.natsuDash = new NatsuDashDust(new Vector2d(), true);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      checkMiscs();
      if (getUser().isSwitcher())
      {
        this.natsuDash.reset(new Vector2d(this.natsu.pos.x, this.natsu.pos.y + 1.5F), this.natsu.statusLogic.isRight());
        this.natsu.releasedDusts.add(this.natsuDash);
        getUser().sound(this.natsu.dashSound);
        getUser().setSwitcher(false);
      }
      break;
    case 1: 
      if (!this.natsu.isSwitcher()) {
        getUser().setSwitcher(true);
      }
      checkMiscs();
      break;
    case 2: 
      if (getUser().isSwitcher())
      {
        this.natsu.statusLogic.swapDirection();
        this.natsu.physics.VY = 0.0F;
        this.natsu.physics.VX = (this.natsu.getDir() * 0.1F);
        getUser().setSwitcher(false);
      }
      break;
    }
    checkDashEnd();
  }
  
  private void checkDashEnd()
  {
    if (this.natsu.picManager.animationIsFinished())
    {
      this.natsu.physics.VX = 0.0F;
      return;
    }
  }
  
  private void checkMiscs()
  {
    this.natsu.physics.VY = 0.0F;
    this.natsu.physics.VX = (this.natsu.getDir() * 52.0F);
  }
  
  public void checkContent() {}
  
  public void checkFinished()
  {
    this.natsu.timeManager.getDashAndRetreatDelay().setY(Float.valueOf(-20.0F));
    this.natsu.setSwitcher(true);
    this.natsu.statusLogic.setActionStates(ActionStates.STAND);
  }
  
  public boolean isAttacking()
  {
    return this.natsu.statusLogic.isDashing();
  }
  
  public void resetStats()
  {
    this.natsu.setSwitcher(true);
  }
}
