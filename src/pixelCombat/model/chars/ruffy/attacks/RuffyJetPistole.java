package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.JetPistoleBigDust;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyJetPistole
  extends Attack
{
  private Ruffy user;
  
  public RuffyJetPistole(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(15.0F);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      this.user.freeze = true;
      this.user.freeze_loop = true;
      if (getUser().isSwitcher())
      {
        if (!this.user.isGear2On()) {
          getUser().sound("/audio/Gomu_hard.wav");
        } else {
          getUser().sound("/audio/Ruffy_Jet_Cry.wav");
        }
        this.user.statusLogic.setFocused(true);
        user.getGear2().reset();
        user.releasedArtWorks.add(user.getGear2());
        getUser().setSwitcher(false);
      }
      break;
    case 1: 
      this.user.freeze_loop = false;
      this.user.borderEffecting = false;
      this.user.statusLogic.setFocused(false);
      break;
    case 3: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/jet_pistole_sound.wav");
        JetPistoleBigDust currentDust = (JetPistoleBigDust)this.user.getJetPistole_BigDusts().get(this.user.getJetPistoleBigDust_Id());
        this.user.setJetPistoleBigDust_Id((this.user.getJetPistoleBigDust_Id() + 1) % this.user.getJetPistole_BigDusts().size());
        currentDust.reset(new Vector2d(this.user.pos.x + this.user.getDir() * 6.75F, this.user.pos.y + 2.5F), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(currentDust);
        this.user.getJetDust().reset(new Vector2d(this.user.pos.x, this.user.pos.y), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.user.getJetDust());
        getUser().setSwitcher(true);
      }
      break;
    case 4: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Pistole.wav");
        getUser().setSwitcher(false);
      }
      break;
    }
    this.user.getUpAndDownBorder().update(this.user.delta);
  }
  
  public void checkContent()
  {
    int factor = this.user.isGear2On() ? 2 : 1;
    
    this.user.enemy.damage(this.user.getStrength() * 15.0F * factor);
    getUser().sound("/audio/jet_pistole_fly.wav");
    getUser().sound(this.user.enemy.cry());
    
    this.user.makeBlueHit();
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-15.0F);
      this.user.enemy.setKnockbackRange(35.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -3.5F, 35.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.user.borderEffecting = false;
    this.user.statusLogic.setFocused(false);
  }
  
  public boolean isAttacking()
  {
    return this.user.getAttackLogic().isSpecialAttacking2();
  }
  
  public void resetStats()
  {
    this.user.borderEffecting = false;
    this.user.statusLogic.setFocused(false);
  }
}
