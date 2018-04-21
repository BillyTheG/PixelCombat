package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.GroundSweep;
import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.dusts.JetPistoleFromAirDust;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyJetPistoleFromAir
  extends Attack
{
  private Ruffy user;
  public JetPistoleFromAirDust jetPistoleDust;
  public GroundSweep earthQuake;
  
  public RuffyJetPistoleFromAir(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(20.0F);
    
    this.jetPistoleDust = new JetPistoleFromAirDust(new Vector2d(0.0F, 0.0F), true);
    this.earthQuake = new GroundSweep(new Vector2d(0.0F, 0.0F), true);
  }
  
  public void process()
  {
    this.user.physics.VY = 0.0F;
    checkTeleport();
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        getUser().setSwitcher(false);
        checkIfIsOnAir();
      }
      break;
    case 4: 
      getUser().setSwitcher(false);
      break;
    case 5: 
      if (!getUser().isSwitcher())
      {
    	  this.user.freeze = false;
          this.user.freeze_loop = false;
          this.user.statusLogic.setFocused(false);
        getUser().setSwitcher(true);
        getUser().sound("/audio/Jet Pistol 01.wav");
      }
      break;
    case 6: 
      if (getUser().isSwitcher())
      {
      
        this.jetPistoleDust.reset(new Vector2d(this.user.pos.x + this.user.getDir() * 0.2F, this.user.pos.y + 0.15F), this.user.statusLogic.isRight());
        this.earthQuake.reset(new Vector2d(this.user.pos.x, 8.0F), this.user.statusLogic.isRight());
        if (!this.user.shaking) {
          this.user.shaking = true;
        }
        this.user.releasedDusts.add(this.earthQuake);
        this.user.releasedDusts.add(this.jetPistoleDust);
        getUser().sound("/audio/jet_pistole_sound.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 8: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Stamp.wav");
        getUser().setSwitcher(true);
      }
      break;
    }
  }
  
  private void checkTeleport()
  {
    if ((this.user.picManager.getCurrFrameIndex() >= 2) && (!this.user.upperJetPistole_Air))
    {
      this.user.physics.VX = 0.0F;
      getUser().sound("/audio/teleport.wav");
      this.user.pos = new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y - 4.5F);
      this.user.upperJetPistole_Air = true;
      this.user.getJetDust().reset(new Vector2d(this.user.pos.x, this.user.pos.y), this.user.statusLogic.isRight());
      this.user.releasedDusts.add(this.user.getJetDust());
      this.user.upperJetPistole_Punch = true;
    }
  }
  
  private void checkIfIsOnAir()
  {
    if (this.user.isAttackOnAir())
    {
      int airIndex = ((Integer)this.user.getAirIndices().get("specialAttack3")).intValue();
      this.user.picManager.setCurrFrameIndex(airIndex);
      this.user.picManager.setAnimTime(this.user.picManager.getFrame(airIndex - 1)
        .getEndTime());
      this.user.setAttackOnAir(false);
    }
    else
    {
    	 this.user.freeze = true;
         this.user.freeze_loop = true;
         this.user.statusLogic.setFocused(true);
         getUser().sound("/audio/gum_gum.wav");
    }
  }
  
  public void checkContent()
  {
    this.user.enemy.damage(this.user.getStrength() * 4.5F);
    getUser().sound("/audio/meleehit2.wav");
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
      this.user.enemy.setKnockbackHeight(30.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -10.0F, 30.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.user.upperJetPistole_Air = false;
    this.user.setAttackOnAir(false);
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.user.statusLogic.setFocused(false);
  }
  
  public boolean isAttacking()
  {
    return this.user.getAttackLogic().isSpecialAttacking3();
  }
  
  public void resetStats()
  {
    this.user.freeze = false;
    this.user.setAttackOnAir(false);
    this.user.freeze_loop = false;
    this.user.statusLogic.setFocused(false);
    this.user.upperJetPistole_Air = false;
  }
}
