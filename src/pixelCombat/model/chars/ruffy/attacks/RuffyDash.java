package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.JetDust;
import pixelCombat.enums.ActionStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyDash
  extends Attack
{
  private Ruffy user;
  private JetDust jetDust;
  
  public RuffyDash(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    setJetDust(new JetDust(new Vector2d(user.pos.x, user.pos.y), false));
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 2: 
    case 3: 
    case 4: 
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
    case 5: 
      if (!this.user.isSwitcher())
      {
        this.user.statusLogic.swapDirection();
        this.user.physics.VY = 0.0F;
        this.user.physics.VX = (this.user.getDir() * 0.1F);
        getUser().setSwitcher(true);
      }
      break;
    }
    checkDashEnd();
  }
  
  private void checkDashEnd()
  {
    if (this.user.picManager.animationIsFinished())
    {
      this.user.physics.VX = 0.0F;
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
    this.user.timeManager.getDashAndRetreatDelay().setY(Float.valueOf(-20.0F));
    this.user.statusLogic.setActionStates(ActionStates.STAND);
    this.user.setSwitcher(true);
  }
  
  public boolean isAttacking()
  {
    return this.user.statusLogic.isDashing();
  }
  
  public void resetStats()
  {
    this.user.setSwitcher(true);
  }
  
  public JetDust getJetDust()
  {
    return this.jetDust;
  }
  
  public void setJetDust(JetDust jetDustBegin)
  {
    this.jetDust = jetDustBegin;
  }
}
