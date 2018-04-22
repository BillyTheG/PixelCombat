package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuJumpAttack
  extends Attack
{
  private Natsu natsu;
  
  public NatsuJumpAttack(Natsu user, int id)
  {
    super(user, id);
    this.natsu = user;
  }
  
  public void process() {}
  
  public void checkContent()
  {
    this.natsu.enemy.damage(this.natsu.getStrength() * 5.0F);
    this.natsu.sound("/audio/Natsu_JumpAttack_Hit.wav");
    this.natsu.sound(this.natsu.enemy.cry());
    
    this.natsu.makeNatsuPunch();
    
    this.natsu.enemy.setKnockbackHeight(25.0F);
    this.natsu.enemy.setKnockbackRange(15.0F);
    this.natsu.enemy.checkOnAir();
    this.natsu.enemy.statusLogic.setActionStates(ActionStates.STAND);
    this.natsu.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    
    this.natsu.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.natsu.viewLogic.update();
  }
  
  public boolean isAttacking()
  {
	  return natsu.attackLogic.isJumpAttacking1();
  }
  
  public void resetStats()
  {
    this.natsu.viewLogic.update();

  }
}
