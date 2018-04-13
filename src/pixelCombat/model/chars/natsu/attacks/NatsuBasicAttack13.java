package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuBasicAttack13
  extends Attack
{
  private Natsu natsu;
  
  public NatsuBasicAttack13(Natsu user, int id)
  {
    super(user, id);
    this.natsu = user;
  }
  
  public void process()
  {
    switch (this.natsu.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/natsu_basicAttack1.wav");
        this.natsu.setSwitcher(false);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    this.natsu.enemy.damage(this.natsu.getStrength() * 3.0F);
    this.natsu.sound("/audio/meleehit2.wav");
    this.natsu.sound(this.natsu.enemy.cry());
    this.natsu.makeNatsuPunch();
    if (!this.natsu.shaking) {
      this.natsu.shaking = true;
    }
    if (!this.natsu.enemy.statusLogic.isKnockback())
    {
      this.natsu.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.natsu.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
      this.natsu.enemy.setKnockbackHeight(-17.0F);
      this.natsu.enemy.setKnockbackRange(10.0F);
    }
    else
    {
      this.natsu.comboTouch(this.natsu.enemy, -15.0F, 15.0F);
    }
    this.natsu.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    getUser().sound(getUser().attack());
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isSpecialAttacking1();
  }
  
  public void resetStats() {}
}
