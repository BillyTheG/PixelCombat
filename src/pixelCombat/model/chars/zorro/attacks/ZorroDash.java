package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.JetDust;
import pixelCombat.enums.ActionStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroDash
  extends Attack
{
  private JetDust jetDustBegin;
  private JetDust jetDustEnd;
  private Zorro user;
  
  public ZorroDash(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    setJetDustBegin(new JetDust(new Vector2d(user.pos.x, user.pos.y), false));
    setJetDustEnd(new JetDust(new Vector2d(user.pos.x, user.pos.y), false));
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      checkMiscs();
      if (getUser().isSwitcher())
      {
        getJetDust().reset(
          new Vector2d(this.user.pos.x - this.user.getDir() * 1.25F, 
          this.user.getGroundLevel()), 
          this.user.statusLogic.isRight());
        this.user.releasedDusts.add(getJetDust());
        getUser().sound(this.user.dashSound);
        getUser().setSwitcher(false);
      }
      break;
    case 1: 
      if ((!this.user.isSwitcher()) && (this.user.picManager.isAlmostFinished(1))) {
        getUser().setSwitcher(true);
      }
      checkMiscs();
      break;
    }
    checkDashEnd();
  }
  
  private void checkDashEnd()
  {
    if (this.user.picManager.animationIsFinished())
    {
      this.user.statusLogic.setActionStates(ActionStates.STAND);
      return;
    }
  }
  
  private void checkMiscs()
  {
    this.user.physics.VY = 0.0F;
    this.user.physics.VX = (this.user.getDir() * 52.0F);
  }
  
  public void checkContent() {}
  
  public void checkFinished()
  {
    this.jetDustEnd.reset(new Vector2d(this.user.pos.x, this.user.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(this.jetDustEnd);
    this.user.statusLogic.swapDirection();
    this.user.timeManager.getDashAndRetreatDelay().setY(Float.valueOf(-20.0F));
    this.user.physics.VY = 0.0F;
    this.user.physics.VX = (this.user.getDir() * 0.1F);
    this.user.setSwitcher(true);
    this.user.releasedDusts.remove(this.jetDustBegin);
  }
  
  public boolean isAttacking()
  {
    return this.user.statusLogic.isDashing();
  }
  
  public JetDust getJetDust()
  {
    return this.jetDustBegin;
  }
  
  public void setJetDustBegin(JetDust jetDust)
  {
    this.jetDustBegin = jetDust;
  }
  
  public void resetStats()
  {
    this.user.setSwitcher(true);
    this.user.releasedDusts.remove(this.jetDustBegin);
  }
  
  private void setJetDustEnd(JetDust jetDustEnd)
  {
    this.jetDustEnd = jetDustEnd;
  }
}
