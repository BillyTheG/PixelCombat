package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BazookaBackDust;
import pixelCombat.dusts.BazookaDust;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyBasicAttack23
  extends Attack
{
  private Ruffy user;
  private BazookaDust bazookaDust;
  private BazookaBackDust bazookaBackDust;
  private float distance = 0.0F;
  private float maxDistance = 2.5F;
  private float oldYPos;
  
  public RuffyBasicAttack23(Ruffy user, int id)
  {
    super(user, id);
    this.user = user;
    this.bazookaDust = new BazookaDust(new Vector2d(user.pos.x, user.pos.y), false);
    this.bazookaBackDust = new BazookaBackDust(new Vector2d(user.pos.x, user.pos.y), false);
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Stretch.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 1: 
      if (!this.user.isSwitcher())
      {
        this.user.freeze = true;
        this.user.freeze_loop = true;
        this.user.statusLogic.setFocused(true);
        this.user.borderEffecting = true;
        this.user.getUpAndDownBorder().reset();
        if (!this.user.isGear2On()) {
          getUser().sound("/audio/Gomu_hard.wav");
        } else {
          getUser().sound("/audio/Ruffy_Jet_Cry.wav");
        }
        this.user.setSwitcher(true);
      }
      break;
    case 2: 
      if (this.user.isSwitcher())
      {
        this.user.freeze = false;
        this.user.freeze_loop = false;
        this.user.statusLogic.setFocused(false);
        this.user.borderEffecting = false;
        getUser().sound("/audio/teleport.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 3: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Bazooka_Voice.wav");
        this.bazookaBackDust.reset(new Vector2d(this.user.pos.x - this.user.getDir() * 4.0F, this.user.pos.y - 0.5F), 
          this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.bazookaBackDust);
        this.user.setSwitcher(true);
      }
      float oldX = this.user.pos.x;
      float oldY = this.user.pos.y;
      if (this.distance < this.maxDistance)
      {
        this.user.physics.VX = (this.user.getDir() * 45.0F);
        this.user.physics.VY = -10.0F;
      }
      else
      {
        this.user.physics.VX = 0.0F;
      }
      this.user.physics.update(this.user.delta);
      float newX = this.user.pos.x;
      float newY = this.user.pos.y;
      
      this.distance = ((float)(this.distance + Math.sqrt((newX - oldX) * (newX - oldX) + (newY - oldY) * (newY - oldY))));
      
      break;
    case 4: 
    case 5: 
      if (this.user.isSwitcher())
      {
        this.oldYPos = this.user.pos.y;
        
        this.user.physics.VX = (-this.user.getDir() * 15.0F);
        getUser().sound("/audio/jet_pistole_fly.wav");
        this.bazookaDust.reset(new Vector2d(this.user.pos.x + this.user.getDir() * 5.0F, this.user.pos.y + 0.5F), 
          this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.bazookaDust);
        this.user.setSwitcher(false);
      }
      this.user.pos.y = this.oldYPos;
      break;
    case 7: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Ruffy_Stretch_Back.wav");
        this.user.setSwitcher(true);
      }
      this.user.pos.y = this.oldYPos;
      break;
    case 8: 
      this.user.freeze = false;
      this.user.freeze_loop = false;
      this.user.pos.y = this.oldYPos;
      this.user.physics.VY = 0.0F;
      break;
    case 6: 
    default: 
      this.user.pos.y = this.oldYPos;
    }
  }
  
  public void checkContent()
  {
    getUser().sound("/audio/elephanto_gun_hit.wav");
    int factor = this.user.isGear2On() ? 2 : 1;
    this.user.enemy.damage(this.user.getStrength() * 5.0F * factor);
    getUser().sound(this.user.enemy.cry());
    this.user.statusLogic.setAHitDelay(true);
    
    this.user.makeBlueHit();
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-15.0F);
      this.user.enemy.setKnockbackRange(30.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -17.0F, 30.0F);
    }
  }
  
  public void checkFinished()
  {
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.distance = 0.0F;
    this.user.statusLogic.setFocused(false);
    this.user.borderEffecting = false;
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking23();
  }
  
  public void resetStats()
  {
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.distance = 0.0F;
    this.user.statusLogic.setFocused(false);
    this.user.borderEffecting = false;
  }
}
