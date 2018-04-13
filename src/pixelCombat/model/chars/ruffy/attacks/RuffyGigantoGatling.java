package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyGigantoGatling
  extends Attack
{
  private Ruffy user;
  
  public RuffyGigantoGatling(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(95.0F);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/Ruffy_SuperAttack.wav");
        this.user.gatling = this.user.getGatling_max();
        this.user.getJetGatling().reset();
        this.user.releasedArtWorks.add(this.user.getJetGatling());
        this.user.superAttacking = true;
        getUser().setSwitcher(false);
      }
      break;
    case 1: 
      if ((this.user.picManager.getAnimTime() >= 0.85F * this.user.picManager.getFrame(1).getEndTime()) && (this.user.SuperAttackIntro > 0))
      {
        this.user.picManager.setCurrFrameIndex(0);
        this.user.picManager.setAnimTime(0.0F);
        this.user.SuperAttackIntro -= 1;
        if (this.user.SuperAttackIntro == 6) {
          getUser().sound("/audio/Gomu_hard.wav");
        }
        if (this.user.SuperAttackIntro == 2)
        {
          this.user.getRuffy_aura().reset(new Vector2d(this.user.pos.x + 0.35F, this.user.pos.y + 0.8F), true);
          this.user.releasedDusts.add(this.user.getRuffy_aura());
        }
      }
      break;
    case 2: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Giganto.wav");
        getUser().sound("/audio/bonefusen_sound.wav");
        getUser().setSwitcher(true);
      }
      break;
    case 5: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/armament_Haki.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 10: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Giganto_Gatling_Haaa.wav");
        getUser().setSwitcher(true);
      }
      break;
    case 11: 
      if (this.user.isSwitcher())
      {
        this.user.getDust1().reset(new Vector2d(this.user.pos.x + this.user.getDir() * 7.2F, this.user.getGroundLevel() - 0.25F + 0.25F), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.user.getDust1());
        getUser().setSwitcher(false);
      }
      break;
    case 13: 
      if ((!this.user.isSwitcher()) && (this.user.gatling > 0))
      {
        getUser().sound("/audio/Giganto_Noise.wav");
        this.user.picManager.setCurrFrameIndex(11);
        this.user.picManager.setAnimTime(this.user.picManager.getFrame(10).getEndTime());
        this.user.statusLogic.setAHitDelay(false);
        this.user.gatling -= 1;
      }
      break;
    case 14: 
      if ((!this.user.isSwitcher()) && (this.user.getDust1() != null))
      {
        getUser().sound("/audio/Rufy_SuperAttack_Cry.wav");
        this.user.releasedDusts.remove(this.user.getDust1());
        this.user.superAttacking = false;
        getUser().setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    this.user.enemy.damage(this.user.getStrength() * 1.0F);
    getUser().sound("/audio/meleehit2.wav");
    getUser().sound(this.user.enemy.cry());
    
    float randY = this.user.getRandom().nextFloat();
    float randX = this.user.getRandom().nextFloat();
    JetPistoleBlueHit currentBlueHit = (JetPistoleBlueHit)this.user.getJetPistole_Blues().get(this.user.getJetPistoleBlue_Id());
    this.user.setJetPistoleBlue_Id((this.user.getJetPistoleBlue_Id() + 1) % this.user.getJetPistole_Blues().size());
    currentBlueHit.dustAnimator.start();
    currentBlueHit.pos.x = (this.user.enemy.pos.x + randX);
    currentBlueHit.pos.y = (this.user.enemy.pos.y + 2.0F + randY);
    currentBlueHit.dead = false;
    this.user.releasedDusts.add(currentBlueHit);
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-4.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
      if ((this.user.gatling == this.user.getGatling_max()) || (this.user.gatling == this.user.getGatling_max() - 1)) {
        this.user.freeze = true;
      }
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -2.0F, 1.5F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    if (this.user.enemy.statusLogic.isDefending()) {
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
    }
    this.user.gatling = this.user.getGatling_max();
    this.user.SuperAttackIntro = 10;
    this.user.superAttacking = false;
    this.user.gatling = this.user.getGatling_max();
  }
  
  public boolean isAttacking()
  {
    return this.user.getAttackLogic().isGigantoGatling();
  }
  
  public void resetStats()
  {
    this.user.superAttacking = false;
  }
}
