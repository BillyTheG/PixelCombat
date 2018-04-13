package pixelCombat.model.chars.zorro.attacks;

import java.util.ArrayList;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.enums.MovementStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;
import pixelCombat.model.projectiles.Yakkoudori;
import pixelCombat.projectiles.Projectile;

public class ZorroYokkouDori
  extends Attack
{
  private Zorro user;
  private ArrayList<Projectile> createdProjectiles;
  private float oldY;
  private float max_distance = 1.0F;
  private float distance = 0.0F;
  
  public ZorroYokkouDori(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(45.0F);
    this.createdProjectiles = new ArrayList<Projectile>();
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        this.user.statusLogic.setFocused(true);
        this.user.setSwitcher(false);
      }
      if (this.distance < this.max_distance)
      {
        this.user.physics.VY = -25.0F;
        this.distance += this.user.physics.tickDistanceY(this.user.delta);
      }
      else
      {
        this.user.physics.VY = 0.0F;
      }
      break;
    case 2: 
      if (!this.user.isSwitcher())
      {
        this.user.superAttacking = true;
        this.user.specialBG.reset();
        this.user.freeze = true;
        this.user.freeze_loop = true;
        
        getUser().sound("/audio/Zorro_Santoryou_Ho_Gi.wav");
        getUser().sound("/audio/Zorro_OniGiri_Intro.wav");
        
        this.oldY = this.user.pos.y;
        this.user.setSwitcher(true);
      }
      break;
    case 4: 
      if ((this.user.isSwitcher()) && (this.user.picManager.isAlmostFinished(4)))
      {
        getUser().sound("/audio/Zorro_Yokkoudori.wav");
        this.user.superAttacking = false;
        this.user.freeze = false;
        makeYakkoudori(1.0F, 1.0F, true);
        makeYakkoudori(1.0F, -1.0F, false);
        makeYakkoudori(-1.0F, 1.0F, false);
        makeYakkoudori(-1.0F, -1.0F, false);
      }
      break;
    case 5: 
      if (!this.user.isSwitcher())
      {
        if (getUser().clip != null) {
          getUser().clip.stop();
        }
        this.user.setSwitcher(true);
      }
      break;
    case 9: 
      if (this.user.isSwitcher())
      {
        this.user.freeze_loop = false;
        this.user.statusLogic.setFocused(false);
        this.user.setSwitcher(false);
      }
      break;
    case 11: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        this.user.setSwitcher(true);
      }
      break;
    }
    if (this.oldY > 0.0F) {
      this.user.pos.y = this.oldY;
    }
  }
  
  private void makeYakkoudori(float y, float x, boolean sounding)
  {
    float x_pos = this.user.pos.x;
    float y_pos = this.user.pos.y;
    Yakkoudori breath = new Yakkoudori(this.user, 
      new Vector2d(x_pos, y_pos), new Vector2d(x_pos + x, 
      y_pos + y), null, sounding, this.user.getDir());
    if (this.user.statusLogic.isRight()) {
      breath.statusLogic.setMovementStates(MovementStates.RIGHT);
    } else {
      breath.statusLogic.setMovementStates(MovementStates.LEFT);
    }
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
    this.oldY = 0.0F;
    this.max_distance = 4.0F;
    this.distance = 0.0F;
    this.user.statusLogic.setFocused(false);
    this.user.releasedDusts.remove(this.user.getPound360Charge());
  }
  
  public boolean isAttacking()
  {
    return this.user.getAttackLogic().isYokkouDoring();
  }
  
  public void resetStats()
  {
    this.user.superAttacking = false;
    this.createdProjectiles.clear();
    this.user.releasedDusts.remove(this.user.getPound360Charge());
    this.oldY = 0.0F;
    this.max_distance = 4.0F;
    this.distance = 0.0F;
  }
}
