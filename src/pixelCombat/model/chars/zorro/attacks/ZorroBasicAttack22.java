package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.dusts.ZorroBasicAttack22Dust;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroBasicAttack22
  extends Attack
{
  private Zorro user;
  private ZorroBasicAttack22Dust zorroBasicAttack22Dust;
  
  public ZorroBasicAttack22(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    this.zorroBasicAttack22Dust = new ZorroBasicAttack22Dust(new Vector2d(0.0F, 0.0F), true);
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_BasicAttack22.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 2: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_Slash_Hit_2.wav");
        this.zorroBasicAttack22Dust.reset(new Vector2d(this.user.pos.x + this.user.getDir() * 1.5F, this.user.pos.y + 1.0F), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.zorroBasicAttack22Dust);
        this.user.setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().enemy.damage(getUser().getStrength() * 2);
    getUser().sound(getUser().enemy.cry());
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_2.wav");
    
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!getUser().enemy.statusLogic.isKnockback())
    {
      getUser().enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      getUser().enemy.setKnockbackHeight(-12.0F);
      getUser().enemy.setKnockbackRange(5.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      getUser().comboTouch(getUser().enemy, -14.0F, 1.0F);
    }
    getUser().statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    getUser().sound(getUser().attack());
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking22();
  }
  
  public void resetStats() {}
}
