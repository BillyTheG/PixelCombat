package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroToraGari
  extends Attack
{
  private Zorro user;
  private float oldY = -1.0F;
  
  public ZorroToraGari(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(35.0F);
  }
  
  public void process()
  {
    this.user.physics.VY = 0.0F;
    if ((this.oldY > 0.0F) && (this.user.picManager.getCurrFrameIndex() != 3)) {
      this.user.pos.y = this.oldY;
    }
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        this.user.getSignalEffect().dustAnimator.start();
        this.user.getSignalEffect().pos.x = (this.user.pos.x - 0.25F);
        this.user.getSignalEffect().pos.y = (this.user.pos.y + 1.5F);
        this.user.getSignalEffect().dead = false;
        this.user.releasedDusts.add(this.user.getSignalEffect());
        
        this.oldY = this.user.pos.y;
        this.user.freeze = true;
        this.user.freeze_loop = true;
        this.user.statusLogic.setFocused(true);
        getUser().sound("/audio/Zorro_Santoryou_Ho_Gi.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 3: 
      if (!getUser().isSwitcher())
      {
        this.user.getUlToraGariArtWork().reset();
        this.user.releasedArtWorks.add(this.user.getUlToraGariArtWork());
        getUser().sound("/audio/Zorro_UL_To_Guira_Lion.wav");
        
        this.user.pos.y += 3.0F;
        if (this.user.pos.y >= this.user.getGroundLevel()) {
          this.user.pos.y -= 3.0F;
        }
        this.oldY = this.user.pos.y;
        
        getUser().setSwitcher(true);
      }
      this.user.pos.y = this.oldY;
      break;
    case 4: 
      if (getUser().isSwitcher())
      {
        this.user.getUlToraGariArtWork().dead = true;
        this.user.releasedArtWorks.remove(this.user.getUlToraGariArtWork());
        getUser().sound("/audio/Zorro_UL_To_Guira_Explosion.wav");
        
        this.user.getUlToraGariWind().dustAnimator.start();
        this.user.getUlToraGariWind().pos.x = this.user.pos.x;
        this.user.getUlToraGariWind().pos.y = (this.user.getGroundLevel() - 6.0F);
        this.user.getUlToraGariWind().dead = false;
        this.user.releasedDusts.add(this.user.getUlToraGariWind());
        getUser().setSwitcher(false);
      }
      break;
    case 5: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_UL_To_Guira_Bottom_Explosion.wav");
        getUser().setSwitcher(true);
      }
      break;
    case 6: 
      if (getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_UL_To_Guira.wav");
        getUser().setSwitcher(false);
        this.user.freeze_loop = false;
        this.user.statusLogic.setFocused(false);
      }
      break;
    case 8: 
      if (!getUser().isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        getUser().setSwitcher(true);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    getUser().sound("/audio/gwhip_hit.wav");
    getUser().enemy.damage(getUser().getStrength() * 10.0F);
    getUser().sound(getUser().enemy.cry());
    if (!this.user.shaking) {
      this.user.shaking = true;
    }
    BloodSplash1 currentBloodSplash = (BloodSplash1)this.user.getBloodSplashs1().get(this.user.getBloodSpashId());
    this.user.setBloodSpashId((this.user.getBloodSpashId() + 1) % this.user.getBloodSplashs1().size());
    currentBloodSplash.reset(new Vector2d(this.user.enemy.pos.x, this.user.enemy.pos.y), this.user.statusLogic.isRight());
    this.user.releasedDusts.add(currentBloodSplash);
    if (!this.user.enemy.statusLogic.isKnockback())
    {
      this.user.enemy.setKnockbackHeight(-37.0F);
      this.user.enemy.setKnockbackRange(1.0F);
      this.user.enemy.checkOnAir();
      this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
    }
    else
    {
      this.user.comboTouch(this.user.enemy, -27.0F, 1.0F);
    }
    this.user.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.oldY = -1.0F;
    getUser().setSwitcher(true);
    this.user.statusLogic.setFocused(false);
    this.user.getUlToraGariArtWork().dead = true;
    this.user.releasedArtWorks.remove(this.user.getUlToraGariArtWork());
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isAirSpecialAttacking1();
  }
  
  public void resetStats()
  {
    this.oldY = -1.0F;
    getUser().setSwitcher(true);
    this.user.statusLogic.setFocused(false);
    this.user.getUlToraGariArtWork().dead = true;
    this.user.releasedArtWorks.remove(this.user.getUlToraGariArtWork());
  }
}
