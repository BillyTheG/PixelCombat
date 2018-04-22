package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyJumpAttack
  extends Attack
{
  private Ruffy user;
  
  public RuffyJumpAttack(Ruffy user, int id)
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
        getUser().sound("/audio/Ruffy_Jet_Whip.wav");
        getUser().setSwitcher(false);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().sound("/audio/Ruffy_JumpAttackHit.wav");
    getUser().enemy.damage(getUser().getStrength() * 5.0F);
    getUser().sound(getUser().enemy.cry());
    
    JetPistoleBlueHit currentBlueHit = (JetPistoleBlueHit)this.user.getJetPistole_Blues().get(this.user.getJetPistoleBlue_Id());
    this.user.setJetPistoleBlue_Id((this.user.getJetPistoleBlue_Id() + 1) % this.user.getJetPistole_Blues().size());
    currentBlueHit.dustAnimator.start();
    currentBlueHit.pos.x = this.user.enemy.pos.x;
    currentBlueHit.pos.y = (this.user.enemy.pos.y + 2.0F);
    currentBlueHit.dead = false;
    this.user.releasedDusts.add(currentBlueHit);
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-17.0F);
      this.user.enemy.setKnockbackRange(15.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -17.0F, 16.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    getUser().setSwitcher(true);
    getUser().sound("/audio/punch.wav");
    this.user.viewLogic.update();
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isJumpAttacking1();
  }
  
  public void resetStats()
  {
    this.user.attackLogic.setAttackStatus(AttackStates.notAttacking);
    getUser().setSwitcher(true);
  }
}
