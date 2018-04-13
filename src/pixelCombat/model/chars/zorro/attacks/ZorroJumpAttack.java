package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroJumpAttack
  extends Attack
{
  private Zorro user;
  
  public ZorroJumpAttack(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        this.user.physics.VY = -5.0F;
        getUser().sound("/audio/Zorro_JumpAttack.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 3: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_JumpAttack_Slash.wav");
        getUser().setSwitcher(true);
      }
      break;
    case 8: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        getUser().setSwitcher(false);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().sound("/audio/Zorro_JumpAttack_Slash_Hit.wav");
    getUser().enemy.damage(getUser().getStrength() * 5.0F);
    getUser().sound(getUser().enemy.cry());
    
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    if (!getUser().enemy.statusLogic.isKnockback())
    {
      getUser().enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      getUser().enemy.physics.VX = (-getUser().enemy.getDir() * 18.0F);
      getUser().enemy.physics.VY = -20.0F;
      getUser().enemy.setKnockbackHeight(-7.0F);
      getUser().enemy.setKnockbackRange(30.0F);
      getUser().enemy.checkOnAir();
    }
    else
    {
      this.user.comboTouch(getUser().enemy, -15.0F, 35.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.user.setJumpAttacking(false);
    getUser().setSwitcher(true);
    this.user.viewLogic.update();
  }
  
  public boolean isAttacking()
  {
    return getUser().isJumpAttacking();
  }
  
  public void resetStats()
  {
    this.user.setJumpAttacking(false);
    getUser().setSwitcher(true);
    this.user.viewLogic.update();
  }
}
