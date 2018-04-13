package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.dusts.RashomonDust;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroRashomon
  extends Attack
{
  private Zorro user;
  private int talkings = 4;
  private float max_distance = 6.0F;
  private float distance;
  private RashomonDust rashomonDust;
  
  public ZorroRashomon(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(25.0F);
    this.rashomonDust = new RashomonDust(new Vector2d(0.0F, 0.0F), true);
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        this.user.freeze = true;
        this.user.freeze_loop = true;
        this.user.statusLogic.setFocused(true);
        this.user.setSwitcher(false);
      }
      break;
    case 1: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Nittoryou.wav");
        this.user.setSwitcher(true);
      }
      break;
    case 3: 
      if ((this.user.isSwitcher()) && (this.talkings > 0) && (this.user.picManager.isAlmostFinished(3)))
      {
        this.talkings -= 1;
        this.user.picManager.resetToIndex(2);
      }
      else if (this.talkings <= 0)
      {
        this.user.setSwitcher(false);
        this.user.freeze = false;
        this.user.freeze_loop = false;
        this.user.statusLogic.setFocused(false);
      }
      break;
    case 5: 
      if (this.distance < this.max_distance)
      {
        this.user.physics.VX = (this.user.getDir() * 120.0F);
        this.user.picManager.resetToIndex(5);
      }
      float step_size = this.user.getDir() * this.user.physics.tickDistanceX(this.user.delta);
      if (step_size >= this.max_distance / 2.0F) {
        step_size = 0.0F;
      }
      this.distance += step_size;
      if (!this.user.isSwitcher())
      {
        this.rashomonDust.reset(new Vector2d(this.user.pos.x + this.user.getDir() * 3.0F, this.user.pos.y - 4.5F), this.user.statusLogic.isRight());
        this.user.releasedDusts.add(this.rashomonDust);
        getUser().sound("/audio/Zorro_Rashomon_Dust_Sound.wav");
        
        this.user.setSwitcher(true);
      }
      break;
    case 6: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Rashomon.wav");
        this.user.physics.VX = 0.0F;
        this.user.setSwitcher(false);
      }
      break;
    case 9: 
      if (!this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        this.user.setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().enemy.damage(getUser().getStrength() * 15.0F);
    getUser().sound(getUser().enemy.cry());
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_5.wav");
    
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-25.0F);
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
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.user.superAttacking = false;
    this.user.statusLogic.setFocused(false);
    this.distance = 0.0F;
    this.talkings = 4;
    this.distance = 0.0F;
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isSpecialAttacking4();
  }
  
  public void resetStats()
  {
    this.user.superAttacking = false;
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.talkings = 4;
    this.distance = 0.0F;
    this.distance = 0.0F;
  }
}
