package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.dusts.ZorroBasicAttack13Dust;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroBasicAttack13
  extends Attack
{
  private Zorro user;
  private ZorroBasicAttack13Dust zorroBasicAttack13Dust;
  
  public ZorroBasicAttack13(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    this.zorroBasicAttack13Dust = new ZorroBasicAttack13Dust(new Vector2d(0.0F, 0.0F), true);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_BasicAttack13.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 5: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_Slash_Hit_2.wav");
        this.zorroBasicAttack13Dust.reset(new Vector2d(this.user.pos.x + this.user.getDir() * 0.5F, this.user.pos.y + 0.5F), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.zorroBasicAttack13Dust);
        getUser().setSwitcher(true);
      }
      break;
    case 10: 
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
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_3.wav");
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
      getUser().enemy.setKnockbackRange(20.0F);
      getUser().enemy.checkOnAir();
    }
    else
    {
      this.user.comboTouch(getUser().enemy, -15.0F, 25.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished() {}
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isSpecialAttacking1();
  }
  
  public void resetStats() {}
}
