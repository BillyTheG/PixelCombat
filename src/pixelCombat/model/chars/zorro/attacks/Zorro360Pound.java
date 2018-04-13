package pixelCombat.model.chars.zorro.attacks;

import java.util.ArrayList;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.enums.MovementStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;
import pixelCombat.model.projectiles.Pound360;
import pixelCombat.projectiles.Projectile;

public class Zorro360Pound
  extends Attack
{
  private Zorro user;
  private ArrayList<Projectile> createdProjectiles;
  private int charges = 5;
  
  public Zorro360Pound(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(40.0F);
    this.createdProjectiles = new ArrayList<Projectile>();
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        this.user.superAttacking = true;
        this.user.freeze = true;
        this.user.freeze_loop = true;
        this.user.statusLogic.setFocused(true);
        getUser().sound("/audio/Zorro_Santoryou_Ho_Gi.wav");
        getUser().sound("/audio/Zorro_OniGiri_Intro.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 3: 
      if (!this.user.isSwitcher())
      {
        this.user.getPound360Charge().reset(new Vector2d(this.user.pos.x, this.user.pos.y), true);
        this.user.releasedDusts.add(this.user.getPound360Charge());
        this.user.setSwitcher(true);
      }
      break;
    case 4: 
      if ((this.user.isSwitcher()) && (this.user.picManager.isAlmostFinished(4)) && (this.charges > 0))
      {
        if (this.charges % 2 == 0) {
          getUser().sound("/audio/Zorro_360Pound_Charge.wav");
        }
        this.charges -= 1;
        if (this.charges == 0) {
          this.user.setSwitcher(false);
        } else {
          this.user.picManager.resetToIndex(3);
        }
      }
      break;
    case 5: 
      if (!this.user.isSwitcher())
      {
        if (getUser().clip != null) {
          getUser().clip.stop();
        }
        getUser().sound("/audio/Zorro_360Pound_360.wav");
        this.user.releasedDusts.remove(this.user.getPound360Charge());
        this.user.setSwitcher(true);
      }
      break;
    case 7: 
      if (this.user.isSwitcher())
      {
        makeBeam(2.0F, 0.0F, true);
        this.user.freeze_loop = false;
        this.user.superAttacking = false;
        this.user.statusLogic.setFocused(false);
        this.user.setSwitcher(false);
        this.charges = 10;
      }
      break;
    case 9: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_360Pound_Pound.wav");
        this.user.setSwitcher(true);
      }
      break;
    case 10: 
      if ((this.user.isSwitcher()) && (this.user.picManager.isAlmostFinished(10)) && (this.charges > 0))
      {
        this.charges -= 1;
        if (this.charges == 0) {
          this.user.setSwitcher(false);
        } else {
          this.user.picManager.resetToIndex(9);
        }
      }
      break;
    case 13: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        this.user.setSwitcher(true);
      }
      break;
    }
  }
  
  private void makeBeam(float y, float x, boolean sounding)
  {
    float x_pos = this.user.pos.x + x;
    float y_pos = this.user.pos.y + y;
    Pound360 breath = new Pound360(this.user, 
      new Vector2d(x_pos, y_pos), new Vector2d(x_pos, 
      y_pos), null, sounding, this.user.getDir());
    if (this.user.statusLogic.isRight()) {
      breath.statusLogic.setMovementStates(MovementStates.RIGHT);
    } else {
      breath.statusLogic.setMovementStates(MovementStates.LEFT);
    }
    breath.physics.VX *= 10.0F;
    
    this.user.releasedProjectiles.add(breath);
    this.createdProjectiles.add(breath);
  }
  
  public void checkContent()
  {
    getUser().enemy.damage(getUser().getStrength() * 15.0F);
    getUser().sound(getUser().enemy.cry());
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_5.wav");
    
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(
      this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % 
      this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, 
      this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-5.0F);
      this.user.enemy.setKnockbackRange(5.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -7.0F, 30.0F);
    }
    getUser().statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.user.freeze_loop = false;
    this.user.superAttacking = false;
    this.createdProjectiles.clear();
    this.charges = 5;
    this.user.statusLogic.setFocused(false);
    this.user.releasedDusts.remove(this.user.getPound360Charge());
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isSpecialAttacking7();
  }
  
  public void resetStats()
  {
    this.user.superAttacking = false;
    this.createdProjectiles.clear();
    this.user.releasedDusts.remove(this.user.getPound360Charge());
    this.charges = 5;
  }
}
