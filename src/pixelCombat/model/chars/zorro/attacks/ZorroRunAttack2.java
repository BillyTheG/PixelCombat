package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroRunAttack2
  extends Attack
{
  private Zorro user;
  
  public ZorroRunAttack2(Zorro user, int id)
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
        getUser().sound("/audio/Zorro_RunAttack2.wav");
        getUser().setSwitcher(false);
        this.user.physics.VX += this.user.getDir() * 10.0F;
      }
      break;
    case 1: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_Slash_4.wav");
        getUser().setSwitcher(true);
      }
      break;
    case 5: 
      if (getUser().isSwitcher())
      {
        this.user.physics.VX = 0.0F;
        getUser().setSwitcher(false);
      }
      break;
    case 7: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        getUser().setSwitcher(true);
      }
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
    getUser().sound("/audio/Zorro_RunAttack2_Slash.wav");
    getUser().enemy.damage(getUser().getStrength() * 5.0F);
    getUser().sound(getUser().enemy.cry());
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-15.0F);
      this.user.enemy.setKnockbackRange(15.0F);
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
    return getUser().attackLogic.isRunAttacking2();
  }
  
  public void resetStats() {}
}
