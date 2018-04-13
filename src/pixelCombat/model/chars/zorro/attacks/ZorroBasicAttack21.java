package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroBasicAttack21
  extends Attack
{
  private Zorro user;
  
  public ZorroBasicAttack21(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 2: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_BasicAttack12.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 4: 
      if (!this.user.isSwitcher())
      {
        this.user.getBasicAttack21Slash().reset(new Vector2d(this.user.pos.x + this.user.getDir() * 1.25F, this.user.pos.y + 1.0F), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.user.getBasicAttack21Slash());
        getUser().sound("/audio/Zorro_Sword_Slash_2.wav");
        this.user.setSwitcher(true);
      }
      break;
    case 9: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        this.user.setSwitcher(false);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_4.wav");
    getUser().enemy.damage(getUser().getStrength() * 2.0F);
    getUser().sound(getUser().enemy.cry());
    getUser().statusLogic.setAHitDelay(true);
    
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
      getUser().comboTouch(getUser().enemy, -4.0F, 1.0F);
    }
  }
  
  public void checkFinished()
  {
    getUser().sound(getUser().attack());
    getUser().sound("/audio/punch.wav");
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking21();
  }
  
  public void resetStats() {}
}
