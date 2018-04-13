package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyGomuGomuNoStorm
  extends Attack
{
  private Ruffy user;
  
  public RuffyGomuGomuNoStorm(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(35.0F);
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
        this.user.gatling = 10;
        if (getUser().clip != null) {
          getUser().clip.stop();
        }
        this.user.freeze = true;
        this.user.statusLogic.setFocused(true);
        getUser().sound("/audio/luffy_storm.wav");
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
    case 4: 
      if ((this.user.isSwitcher()) && (this.user.gatling > 0))
      {
        getUser().sound("/audio/luffy_fist_sound2.wav");
        if (this.user.getGomu_Gomu_Storm().dead)
        {
          this.user.getGomu_Gomu_Storm().dustAnimator.start();
          this.user.getGomu_Gomu_Storm().dead = false;
          this.user.releasedDusts.add(this.user.getGomu_Gomu_Storm());
        }
        this.user.getGomu_Gomu_Storm().pos.y = (this.user.pos.y - 0.0F);
        this.user.getGomu_Gomu_Storm().pos.x = this.user.pos.x;
        this.user.statusLogic.setAHitDelay(false);
        this.user.gatling -= 1;
        this.user.freeze = false;
        this.user.statusLogic.setFocused(false);
        getUser().setSwitcher(false);
      }
      break;
    case 5: 
    case 7: 
      this.user.getGomu_Gomu_Storm().pos.y = (this.user.pos.y - 0.0F);
      this.user.getGomu_Gomu_Storm().pos.x = this.user.pos.x;
      break;
    case 6: 
      if ((!this.user.isSwitcher()) && (this.user.gatling > 0))
      {
        getUser().statusLogic.setAHitDelay(false);
        this.user.getGomu_Gomu_Storm().pos.y = (this.user.pos.y - 0.0F);
        this.user.getGomu_Gomu_Storm().pos.x = this.user.pos.x;
        getUser().setSwitcher(true);
      }
      break;
    case 8: 
      if ((this.user.isSwitcher()) && (this.user.gatling > 0))
      {
        this.user.picManager.setCurrFrameIndex(4);
        float endTime = this.user.picManager.getFrame(3).getEndTime();
        this.user.picManager.setAnimTime(endTime);
        getUser().sound("/audio/luffy_daradara.wav");
        getUser().setSwitcher(true);
      }
      break;
    }
    if (this.user.picManager.getCurrFrameIndex() > 7)
    {
      getUser().clip.stop();
      this.user.releasedDusts.remove(this.user.getGomu_Gomu_Storm());
      this.user.getGomu_Gomu_Storm().dead = true;
    }
  }
  
  public void checkContent()
  {
    this.user.enemy.damage(this.user.getStrength() * 2.0F);
    getUser().sound("/audio/Ruffy_Stamp.wav");
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
      this.user.enemy.setKnockbackHeight(-15.0F);
      this.user.enemy.physics.VX = 0.0F;
      this.user.enemy.setKnockbackRange(0.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      int factor = this.user.pos.x < this.user.enemy.pos.x ? 1 : -1;
      this.user.comboTouch(this.user.enemy, -5.75F, factor * 0.085F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.user.releasedDusts.remove(this.user.getGomu_Gomu_Storm());
    this.user.getGomu_Gomu_Storm().dead = true;
    this.user.gatling = this.user.getGatling_max();
    this.user.freeze = false;
    this.user.statusLogic.setFocused(false);
  }
  
  public boolean isAttacking()
  {
    return this.user.getAttackLogic().isAirSpecialAttacking1();
  }
  
  public void resetStats()
  {
    this.user.freeze = false;
    this.user.statusLogic.setFocused(false);
  }
  
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
