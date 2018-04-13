package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyGigantoWhip
  extends Attack
{
  private Ruffy user;
  
  public RuffyGigantoWhip(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(10.0F);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (checkGear()) {
        return;
      }
      if (getUser().isSwitcher())
      {
        if (this.user.isAttackOnAir()) {
          this.user.changeToAirPoint("specialAttack6");
        } else {
          getUser().sound("/audio/Gear Third 00.wav");
        }
        getUser().setSwitcher(false);
      }
      break;
    case 1: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/bonefusen_sound.wav");
        getUser().setSwitcher(true);
      }
      break;
    case 8: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/giganto_stretch.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 12: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/giganto_whip_cry.wav");
        getUser().setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    this.user.enemy.damage(this.user.getStrength() * 11.5F);
    getUser().sound("/audio/gwhip_hit.wav");
    getUser().sound(this.user.enemy.cry());
    
    JetPistoleBlueHit currentBlueHit = (JetPistoleBlueHit)this.user.getJetPistole_Blues().get(this.user.getJetPistoleBlue_Id());
    this.user.setJetPistoleBlue_Id((this.user.getJetPistoleBlue_Id() + 1) % this.user.getJetPistole_Blues().size());
    currentBlueHit.dustAnimator.start();
    currentBlueHit.pos.x = this.user.enemy.pos.x;
    currentBlueHit.pos.y = (this.user.enemy.pos.y + 2.0F);
    currentBlueHit.dead = false;
    this.user.releasedDusts.add(currentBlueHit);
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-12.0F);
      this.user.enemy.setKnockbackRange(50.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -7.0F, 50.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished() {}
  
  public boolean isAttacking()
  {
    return this.user.getAttackLogic().isSpecialAttacking6();
  }
  
  public void resetStats() {}
  
  private boolean checkGear()
  {
    if (!this.user.isGear2On()) {
      return false;
    }
    this.user.giveEnergyBack(this);
    this.user.attackLogic.setAttackStatus(AttackStates.notAttacking);
    
    return true;
  }
}
