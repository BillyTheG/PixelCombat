package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.JetPistoleBigDust;
import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyJetGatling
  extends Attack
{
  private Ruffy user;
  private boolean canSound = true;
  
  public RuffyJetGatling(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(65.0F);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        if (checkGear()) {
          return;
        }
        getUser().sound("/audio/Gomu_hard.wav");
        this.user.freeze = true;
        this.user.freeze_loop = true;
        this.user.borderEffecting = true;
        this.user.getUpAndDownBorder().reset();
        
        this.user.getJetGatlingArtWork().reset();
        this.user.getJetDust().reset(new Vector2d(this.user.pos.x, this.user.getGroundLevel() + 0.25F), this.user.statusLogic.isRight());
        this.user.releasedArtWorks.add(this.user.getJetGatlingArtWork());
        this.user.releasedDusts.add(this.user.getJetDust());
        getUser().setSwitcher(false);
      }
      break;
    case 2: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Jet_Cry.wav");
        activateAllDusts();
        this.user.setSwitcher(true);
      }
      break;
    case 3: 
      if (this.user.isSwitcher())
      {
        this.user.freeze = false;
        this.user.freeze_loop = false;
        this.user.borderEffecting = false;
        makeJetGatlingDust();
        makeJetGatlingDust();
        makeJetGatlingDust();
        if (this.canSound)
        {
          getUser().sound("/audio/Ruffy_Jet_Gatling_Cry.wav");
          this.canSound = false;
        }
        this.user.setSwitcher(false);
      }
      break;
    case 5: 
      if ((!this.user.isSwitcher()) && (this.user.picManager.isAlmostFinished(5)) && (this.user.gatling > 0))
      {
        getUser().sound("/audio/JetGatlingBigDustSound.wav");
        this.user.picManager.setCurrFrameIndex(3);
        this.user.picManager.setAnimTime(this.user.picManager.getFrame(2).getEndTime());
        this.user.statusLogic.setAHitDelay(false);
        this.user.gatling -= 1;
        this.user.setSwitcher(true);
      }
      break;
    case 7: 
      if (!this.user.isSwitcher())
      {
        this.user.releasedDusts.remove(this.user.getJetBigDustCircle());
        this.user.releasedDusts.remove(this.user.getJetBigDustBackDust());
        getUser().setSwitcher(true);
      }
      break;
    }
  }
  
  private void makeJetGatlingDust()
  {
    JetPistoleBigDust currentDust = (JetPistoleBigDust)this.user.getJetPistole_BigDusts().get(this.user.getJetPistoleBigDust_Id());
    this.user.setJetPistoleBigDust_Id((this.user.getJetPistoleBigDust_Id() + 1) % this.user.getJetPistole_BigDusts().size());
    int randX = this.user.getRandom().nextInt(8);
    int randY = this.user.getRandom().nextInt(4);
    currentDust.reset(new Vector2d(this.user.pos.x + this.user.getDir() * (6.5F + randX), this.user.pos.y + 1.5F + randY), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentDust);
  }
  
  private boolean checkGear()
  {
    if (!this.user.isGear2On())
    {
      this.user.attackLogic.setAttackStatus(AttackStates.GigantoGatling);
      return true;
    }
    return false;
  }
  
  private void activateAllDusts()
  {
    this.user.getJetBigDustCircle().reset(new Vector2d(this.user.pos.x + this.user.getDir() * 6.0F, this.user.pos.y - 5.0F), this.user.statusLogic.isRight());
    this.user.getJetBigDustBackDust().reset(new Vector2d(this.user.pos.x + -this.user.getDir() * 5.75F, this.user.getGroundLevel() + 0.5F), this.user.statusLogic.isRight());
    
    this.user.releasedDusts.add(this.user.getJetBigDustCircle());
    this.user.releasedDusts.add(this.user.getJetBigDustBackDust());
  }
  
  public void checkContent()
  {
    this.user.enemy.damage(this.user.getStrength() * 1.5F);
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
    if (this.user.gatling == this.user.getGatling_max() / 2)
    {
      this.user.enemy.freeze = true;
      this.user.freeze = true;
    }
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-7.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
      if ((this.user.gatling == this.user.getGatling_max()) || (this.user.gatling == this.user.getGatling_max() - 1)) {
        this.user.freeze = true;
      }
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -3.52F, 4.5F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.user.borderEffecting = false;
    this.user.releasedDusts.remove(this.user.getJetBigDustCircle());
    this.user.releasedDusts.remove(this.user.getJetBigDustBackDust());
    this.user.releasedDusts.remove(this.user.getDashGatlingDust());
    this.user.gatling = this.user.getGatling_max();
    this.user.SuperAttackIntro = 10;
    this.user.superAttacking = false;
    this.canSound = true;
  }
  
  public boolean isAttacking()
  {
    return this.user.getAttackLogic().isSpecialAttacking5();
  }
  
  public void resetStats()
  {
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.user.borderEffecting = false;
    this.canSound = true;
  }
}
