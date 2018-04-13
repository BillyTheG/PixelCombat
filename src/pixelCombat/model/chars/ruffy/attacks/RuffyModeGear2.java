package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.enums.AttackStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyModeGear2
  extends Attack
{
  private Ruffy user;
  
  public RuffyModeGear2(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
  }
  
  public void process()
  {
    checkFreeze();
    checkUpAndDownBorderEffect();
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        checkGearAura();
        getUser().setSwitcher(false);
      }
      break;
    case 5: 
      if (!getUser().isSwitcher())
      {
        startAura();
        getUser().setSwitcher(true);
      }
      break;
    case 11: 
      if (getUser().isSwitcher())
      {
        this.user.getUpAndDownBorder().makeOutro();
        getUser().setSwitcher(false);
      }
      break;
    }
    this.user.getUpAndDownBorder().update(this.user.delta);
  }
  
  private void startAura()
  {
    if (this.user.isStartAura())
    {
      this.user.getGear2aura().dustAnimator.start();
      this.user.getGear2aura().pos.x = this.user.pos.x;
      this.user.getGear2aura().pos.y = this.user.pos.y;
      getUser().sound("/audio/gear_2_pump.wav");
      this.user.releasedDusts.add(this.user.getGear2aura());
      this.user.setStartAura(false);
    }
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    this.user.setGear2On(true);
    this.user.getPump_it_aura().reset(new Vector2d(this.user.pos.x, this.user.pos.y - 2.5F), true);
    this.user.releasedDusts.add(this.user.getPump_it_aura());
  }
  
  private void checkGearAura()
  {
    this.user.getUpAndDownBorder().reset();
    this.user.borderEffecting = true;
    this.user.statusLogic.setFocused(true);
    if (this.user.isGear2On())
    {
      cancelGear2();
      return;
    }
    this.user.setStartAura(true);
    getUser().sound("/audio/Ruffy_Gear2_Cry.wav");
  }
  
  private void cancelGear2()
  {
    getUser().sound("/audio/gear_2_pump.wav");
    this.user.getPump_it_aura().reset(new Vector2d(this.user.pos.x, this.user.pos.y - 2.5F), true);
    this.user.releasedDusts.add(this.user.getPump_it_aura());
    
    this.user.setGear2On(false);
    this.user.getAttackLogic().setAttackStatus(AttackStates.notAttacking);
    this.user.releasedDusts.remove(this.user.getGear2aura());
    this.user.freeze_loop = false;
    getUser().setSwitcher(true);
    this.user.statusLogic.setFocused(false);
    this.user.borderEffecting = false;
    this.user.physics.setMaximumSpeed(this.user.getTempSpeed());
    this.user.physics.setACCELERATION(this.user.getTempAcc());
    this.user.setPushNotDoneSofar(true);
  }
  
  private void checkUpAndDownBorderEffect()
  {
    this.user.getUpAndDownBorder().update(this.user.delta);
    if ((!this.user.getUpAndDownBorder().intro) && (!this.user.getUpAndDownBorder().outro)) {
      this.user.borderEffecting = false;
    }
  }
  
  private void checkFreeze()
  {
    this.user.freeze = true;
    this.user.freeze_loop = true;
  }
  
  public void checkContent() {}
  
  public void checkFinished()
  {
    this.user.freeze_loop = false;
    this.user.borderEffecting = false;
    this.user.statusLogic.setFocused(false);
  }
  
  public boolean isAttacking()
  {
    return this.user.getAttackLogic().isGear2Transforming();
  }
  
  public void resetStats() {}
}
