package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.enums.MovementStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyKomboGear2
  extends Attack
{
  private Ruffy user;
  
  public RuffyKomboGear2(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(20.0F);
  }
  
  public void process()
  {
    checkFreeze();
    checkAura();
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        if (checkGear()) {
          return;
        }
        getUser().sound("/audio/gear_2.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 9: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/gear_2_pump.wav");
        getUser().sound("/audio/Ruffy_SuperAttack.wav");
        if (!this.user.shaking) {
          this.user.shaking = true;
        }
        this.user.getPump_it_aura().reset(new Vector2d(this.user.pos.x, this.user.pos.y - 3.0F), true);
        this.user.releasedDusts.add(this.user.getPump_it_aura());
        getUser().setSwitcher(true);
      }
      break;
    case 10: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/ruffy_dash_2.wav");
        this.user.pos.x += this.user.getDir() * 12.0F;
        this.user.getJetDust().reset(new Vector2d(this.user.pos.x, this.user.getGroundLevel() + 0.25F), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.user.getJetDust());
        this.user.statusLogic.swapDirection();
        getUser().setSwitcher(false);
      }
      break;
    case 14: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Jet Pistol 01.wav");
        this.user.getJetPistoleBigDust_1().reset(new Vector2d(this.user.pos.x + this.user.getDir() * 5.0F, this.user.pos.y + 2.0F), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.user.getJetPistoleBigDust_1());
        getUser().setSwitcher(true);
      }
      break;
    case 15: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/jet_pistole_sound.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 17: 
      if (!getUser().isSwitcher())
      {
        checkTeleport();
        getUser().setSwitcher(true);
      }
      break;
    case 18: 
      if (getUser().isSwitcher())
      {
        this.user.physics.VX = (this.user.getDir() * 70.0F);
        this.user.physics.VY += -25.0F;
        getUser().setSwitcher(false);
      }
      break;
    case 19: 
      if ((!getUser().isSwitcher()) && (this.user.getKicks() > 0) && (this.user.picManager.isAlmostFinished(19)))
      {
        this.user.setKicks(this.user.getKicks() - 1);
        this.user.picManager.setCurrFrameIndex(18);
        this.user.statusLogic.setAHitDelay(false);
        this.user.picManager.setAnimTime(this.user.picManager.getFrame(17).getEndTime());
      }
      break;
    case 20: 
      if (!getUser().isSwitcher())
      {
        this.user.physics.VX = 0.0F;
        this.user.physics.VY = 0.0F;
        this.user.getPump_it_aura().pos.x = this.user.pos.x;
        this.user.getPump_it_aura().pos.y = (this.user.pos.y - 3.0F);
        this.user.getPump_it_aura().dustAnimator.start();
        this.user.getPump_it_aura().dead = false;
        this.user.releasedDusts.add(this.user.getPump_it_aura());
        this.user.releasedDusts.remove(this.user.getGear2aura());
        getUser().setSwitcher(false);
      }
      break;
    }
  }
  
  private void checkTeleport()
  {
    getUser().sound("/audio/ruffy_dash_2.wav");
    this.user.statusLogic.setAHitDelay(false);
    if (this.user.pos.x <= this.user.enemy.pos.x)
    {
      this.user.statusLogic.setMovementStates(MovementStates.LEFT);
      this.user.pos.x = (this.user.enemy.pos.x + 4.0F);
    }
    else
    {
      this.user.statusLogic.setMovementStates(MovementStates.RIGHT);
      this.user.pos.x = (this.user.enemy.pos.x - 4.0F);
    }
  }
  
  private void checkAura()
  {
    if ((this.user.picManager.getCurrFrameIndex() <= 20) && (this.user.picManager.getCurrFrameIndex() >= 9))
    {
      this.user.getGear2aura().repositionate(new Vector2d(this.user.pos.x, this.user.pos.y), true);
      if (this.user.releasedDusts.contains(this.user.getGear2aura())) {
        return;
      }
      this.user.releasedDusts.add(this.user.getGear2aura());
    }
  }
  
  private void checkFreeze()
  {
    if (this.user.picManager.getCurrFrameIndex() < 10.0F)
    {
      this.user.freeze = true;
      this.user.freeze_loop = true;
    }
    else
    {
      this.user.freeze_loop = false;
    }
  }
  
  public void checkContent()
  {
    this.user.enemy.damage(this.user.getStrength() * 11.5F);
    getUser().sound("/audio/elephanto_gun_hit.wav");
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
      this.user.enemy.setKnockbackRange(30.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -7.0F, 7.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
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
  
  public void checkFinished()
  {
    this.user.setKicks(10);
  }
  
  public boolean isAttacking()
  {
    return this.user.getAttackLogic().isSpecialAttacking7();
  }
  
  public void resetStats() {}
}
