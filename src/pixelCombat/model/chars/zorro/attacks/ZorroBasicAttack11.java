package pixelCombat.model.chars.zorro.attacks;

import java.util.ArrayList;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;
import pixelCombat.projectiles.Projectile;

public class ZorroBasicAttack11
  extends Attack
{
  private Zorro user;
  private ArrayList<Projectile> createdProjectiles = new ArrayList<Projectile>();
  
  public ZorroBasicAttack11(Zorro user, int id)
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
        getUser().sound("/audio/Zorro_BasicAttack11.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 4: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_Slash_Hit_1.wav");
        this.user.setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_1.wav");
    getUser().enemy.damage(getUser().getStrength() * 2.5F);
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
    this.user.releasedProjectiles.clear();
    this.createdProjectiles.clear();
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking1();
  }
  
  public void resetStats()
  {
    this.user.releasedProjectiles.clear();
    this.createdProjectiles.clear();
  }
}
