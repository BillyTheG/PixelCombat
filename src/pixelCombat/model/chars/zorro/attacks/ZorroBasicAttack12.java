package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroBasicAttack12
  extends Attack
{
  private Zorro user;
  
  public ZorroBasicAttack12(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_BasicAttack12.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 3: 
      this.user.physics.VX = (this.user.getDir() * 50.0F);
      if (this.user.behindEnemy()) {
        this.user.physics.VX = 0.0F;
      }
      break;
    case 4: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_Slash_Hit_2.wav");
        this.user.setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().enemy.damage(getUser().getStrength() * 5);
    getUser().sound(getUser().enemy.cry());
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_2.wav");
    
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!getUser().enemy.statusLogic.isKnockback())
    {
      getUser().enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      getUser().enemy.setKnockbackHeight(-7.0F);
      getUser().enemy.setKnockbackRange(10.0F);
      getUser().enemy.checkOnAir();
    }
    else
    {
      getUser().comboTouch(getUser().enemy, -14.0F, 1.0F);
    }
    getUser().statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.user.statusLogic.swapDirection();
    getUser().sound(getUser().attack());
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking2();
  }
  
  public void resetStats() {}
}
