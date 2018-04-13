package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyRunAttack1
  extends Attack
{
  private Ruffy user;
  
  public RuffyRunAttack1(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
  }
  
  public void process()
  {
    createParticleWinds();
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/riffle_begin.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 1: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Riffle.wav");
        getUser().setSwitcher(true);
      }
      break;
    case 3: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/dash.wav");
        
        this.user.getDashDust().pos.x = (this.user.pos.x - 1.0F);
        this.user.getDashDust().pos.y = (this.user.pos.y - 1.0F);
        this.user.getDashDust().dustAnimator.start();
        this.user.getDashDust().faceRight = this.user.statusLogic.isRight();
        this.user.getDashDust().dead = false;
        this.user.releasedDusts.add(this.user.getDashDust());
        getUser().setSwitcher(false);
      }
      this.user.physics.VX = (this.user.getDir() * 65.0F);
      break;
    }
  }
  
  private void createParticleWinds()
  {
    if ((this.user.picManager.getCurrFrameIndex() >= 4) && (this.user.picManager.getCurrFrameIndex() <= 6))
    {
      this.user.ParticleManager.createParticleWinds(this.user.pos.add(new Vector2d(0.0F, 1.0F)), 180, 
        0, this.user);
      this.user.ParticleManager.createParticleWinds(this.user.pos, 180, 0, this.user);
      this.user.ParticleManager.createParticleWinds(this.user.pos.add(new Vector2d(0.0F, -1.0F)), 
        180, 0, this.user);
    }
  }
  
  public void checkContent()
  {
    getUser().sound("/audio/riffle_end.wav");
    getUser().enemy.damage(getUser().getStrength() * 2.0F);
    getUser().sound(getUser().enemy.cry());
    
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
      this.user.enemy.setKnockbackHeight(-7.0F);
      this.user.enemy.setKnockbackRange(35.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -7.0F, 30.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished() {}
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isRunAttacking1();
  }
  
  public void resetStats() {}
}
