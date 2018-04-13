package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.dusts.ZorroBasicAttack23Dust;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroBasicAttack23
  extends Attack
{
  private Zorro user;
  private ZorroBasicAttack23Dust zorroBasicAttack23Dust;
  
  public ZorroBasicAttack23(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    this.zorroBasicAttack23Dust = new ZorroBasicAttack23Dust(new Vector2d(0.0F, 0.0F), true);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_BasicAttack23.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 2: 
      if (!getUser().isSwitcher())
      {
        this.zorroBasicAttack23Dust.reset(new Vector2d(this.user.pos.x + this.user.getDir() * 0.5F, this.user.pos.y), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.zorroBasicAttack23Dust);
        getUser().sound("/audio/Zorro_Sword_Slash_Hit_2.wav");
        getUser().setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().enemy.damage(getUser().getStrength() * 3);
    getUser().sound(getUser().enemy.cry());
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_4.wav");
    
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!getUser().enemy.statusLogic.isKnockback())
    {
      getUser().enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      getUser().enemy.setKnockbackHeight(-17.0F);
      getUser().enemy.setKnockbackRange(10.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      getUser().comboTouch(getUser().enemy, -24.0F, 5.0F);
    }
    getUser().statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished() {}
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking23();
  }
  
  public void resetStats() {}
}
