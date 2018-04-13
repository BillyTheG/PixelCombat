package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.StampDust;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyBasicAttack22
  extends Attack
{
  private Ruffy user;
  private StampDust stampDust;
  
  public RuffyBasicAttack22(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    this.stampDust = new StampDust(new Vector2d(user.pos.x, user.pos.y), false);
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Stamp_Voice.wav");
        getUser().sound("/audio/Ruffy_Stretch.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 4: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Stamp.wav");
        if (!this.user.shaking) {
          this.user.shaking = true;
        }
        this.stampDust.dustAnimator.start();
        this.stampDust.pos.x = (this.user.pos.x + this.user.getDir() * 5.0F);
        this.stampDust.pos.y = (this.user.getGroundLevel() - 3.0F);
        this.stampDust.dead = false;
        this.user.releasedDusts.add(this.stampDust);
        this.user.setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().sound("/audio/punchHit.wav");
    this.user.enemy.damage(this.user.getStrength());
    getUser().sound(this.user.enemy.cry());
    this.user.statusLogic.setAHitDelay(true);
    
    this.user.makeBlueHit();
    
    this.user.freeze = true;
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.timeManager.getDisableTime().setY(Float.valueOf(0.0F));
      this.user.enemy.physics.VX = (-this.user.enemy.getDir() * 5.0F);
      this.user.enemy.physics.VY = -14.0F;
      this.user.enemy.setKnockbackHeight(-7.0F);
      this.user.enemy.setKnockbackRange(10.0F);
      this.user.enemy.checkOnAir();
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -4.0F, 1.0F);
    }
  }
  
  public void checkFinished() {}
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking22();
  }
  
  public void resetStats() {}
}
