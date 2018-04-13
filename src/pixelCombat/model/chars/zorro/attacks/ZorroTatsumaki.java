package pixelCombat.model.chars.zorro.attacks;

import java.util.ArrayList;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.dusts.ZorroTatsumakiDragon;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;
import pixelCombat.model.projectiles.TasumakiWind;
import pixelCombat.projectiles.Projectile;

public class ZorroTatsumaki
  extends Attack
{
  private Zorro user;
  private ArrayList<Projectile> createdProjectiles;
  private ZorroTatsumakiDragon dragon1;
  private ZorroTatsumakiDragon dragon2;
  private ZorroTatsumakiDragon dragon3;
  
  public ZorroTatsumaki(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(40.0F);
    this.createdProjectiles = new ArrayList<Projectile>();
    this.dragon1 = new ZorroTatsumakiDragon(new Vector2d(), true);
    this.dragon2 = new ZorroTatsumakiDragon(new Vector2d(), true);
    this.dragon3 = new ZorroTatsumakiDragon(new Vector2d(), true);
    
    this.dragon1.dead = true;
    this.dragon2.dead = true;
    this.dragon3.dead = true;
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
        this.user.getTatsumakiWindAura().reset(new Vector2d(this.user.pos.x - 0.75F, this.user.pos.y + 4.25F), true);
        this.user.releasedDusts.add(this.user.getTatsumakiWindAura());
        
        this.user.getTatsumakiDragonArtWork().reset();
        this.user.releasedArtWorks.add(this.user.getTatsumakiDragonArtWork());
        this.user.superAttacking = true;
        this.user.specialBG.reset();
        this.user.freeze = true;
        this.user.freeze_loop = true;
        this.user.statusLogic.setFocused(true);
        getUser().sound("/audio/Zorro_Tatsumaki_1.wav");
        getUser().sound("/audio/Zorro_OniGiri_Intro.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 3: 
      if ((this.user.picManager.isAlmostFinished(3)) && (!this.user.isSwitcher()))
      {
        getUser().sound("/audio/Zorro_Tatsumaki_2.wav");
        this.user.setSwitcher(true);
        this.user.getTatsumakiWindExplosion().reset(new Vector2d(this.user.pos.x + 1.0F, this.user.pos.y - 3.45F), true);
        this.user.releasedDusts.add(this.user.getTatsumakiWindExplosion());
      }
      break;
    case 5: 
      if (this.user.isSwitcher())
      {
        this.user.getTatsumakiDragonArtWork().dead = true;
        this.user.releasedArtWorks.remove(this.user.getTatsumakiDragonArtWork());
        
        makeWinds(-7.55F, 0.0F, true);
        makeWinds(-5.0F, 0.0F, false);
        makeWinds(-2.55F, 0.0F, false);
        makeWinds(0.0F, 0.0F, false);
        makeWinds(2.55F, 0.0F, false);
        this.user.freeze_loop = false;
        this.user.superAttacking = false;
        
        this.user.setSwitcher(false);
      }
      break;
    case 7: 
      if ((!this.user.isSwitcher()) && (this.user.picManager.isAlmostFinished(7)))
      {
        boolean allDead = true;
        for (Projectile projectile : this.createdProjectiles) {
          allDead &= projectile.statusLogic.isDead();
        }
        if (!allDead) {
          this.user.picManager.resetToIndex(6);
        } else {
          this.user.setSwitcher(true);
        }
      }
      break;
    case 8: 
      if (this.user.isSwitcher())
      {
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        this.user.statusLogic.setFocused(false);
        this.user.setSwitcher(false);
      }
      break;
    }
    if ((this.user.picManager.getCurrFrameIndex() <= 7) && (this.user.picManager.getCurrFrameIndex() >= 6)) {
      makeDragons();
    }
  }
  
  private void makeDragons()
  {
    checkDragon(this.dragon1, -3.75F);
    checkDragon(this.dragon2, 0.0F);
    checkDragon(this.dragon3, 3.75F);
  }
  
  private void checkDragon(ZorroTatsumakiDragon dragon, float xPos)
  {
    if (dragon.isDead())
    {
      dragon.reset(new Vector2d(this.user.pos.x + xPos, this.user.pos.y + 5.0F), this.user.statusLogic.isRight());
      if (!this.user.releasedDusts.contains(dragon)) {
        this.user.releasedDusts.add(dragon);
      }
    }
    dragon.pos.y -= this.user.delta * 10.0F;
  }
  
  private void makeWinds(float y, float x, boolean sounding)
  {
    float x_pos = this.user.pos.x + x;
    float y_pos = this.user.pos.y + y;
    TasumakiWind breath = new TasumakiWind(this.user, 
      new Vector2d(x_pos, y_pos), new Vector2d(x_pos + this.user.getDir(), 
      y_pos), null, sounding, this.user.getDir());
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
    this.user.superAttacking = false;
    this.createdProjectiles.clear();
    this.user.statusLogic.setFocused(false);
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isSpecialAttacking3();
  }
  
  public void resetStats()
  {
    this.user.superAttacking = false;
    this.user.getTatsumakiDragonArtWork().dead = true;
    this.user.statusLogic.setFocused(false);
    this.createdProjectiles.clear();
    this.user.releasedArtWorks.remove(this.user.getTatsumakiDragonArtWork());
  }
}
