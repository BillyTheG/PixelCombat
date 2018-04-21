package pixelCombat.model.chars.zorro.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.arenas.BackGroundEffect;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Zorro;

public class ZorroShiShinShinSon
  extends Attack
{
  private Zorro user;
  private int talk1 = 10;
  private int talk2 = 8;
  
  private int outroMax = 20;
  private int outro    = outroMax;
  
  public ZorroShiShinShinSon(Zorro user, int id)
  {
    super(user, id);
    this.user = user;
    setRequiredEnergy(90.0F);
  }
  
  public void process()
  {
    switch (this.user.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.user.isSwitcher())
      {
    	user.getEngine().pauseMP3();
        this.user.statusLogic.setBackGroundEffect(BackGroundEffect.MONOCHROME);
        
        this.user.freeze = true;
        this.user.freeze_loop = true;
        this.user.borderEffecting = true;
        this.user.statusLogic.setFocused(true);
        getUser().sound("/audio/Zorro_Shi_Shin_SonSon_0.wav");
        this.user.setSwitcher(false);
      }
      break;
    case 1: 
      if (!this.user.isSwitcher())
      {
        this.user.statusLogic.setBackGroundEffect(BackGroundEffect.MONOCHROME);
        getUser().sound("/audio/Zorro_Shi_Shin_SonSon_1.wav");
        this.user.setSwitcher(true);
      }
      break;
    case 2: 
      if ((this.user.isSwitcher()) && (this.talk1 > 0) && (this.user.picManager.isAlmostFinished(2)))
      {
        this.talk1 -= 1;
        if (this.talk1 <= 0)
        {
          this.user.setSwitcher(false);
          getUser().sound("/audio/Zorro_Shi_Shin_SonSon_2.wav");
        }
        else
        {
          this.user.picManager.resetToIndex(1);
        }
      }
      break;
    case 5: 
      if ((!this.user.isSwitcher()) && (this.talk2 > 0) && (this.user.picManager.isAlmostFinished(5)))
      {
        this.talk2 -= 1;
        if (this.talk2 <= 0)
        {
          this.user.setSwitcher(true);
          getUser().sound("/audio/Zorro_Shi_Shin_SonSon_3.wav");
          this.user.superAttacking = true;
        }
        else
        {
          this.user.picManager.resetToIndex(4);
        }
      }
      break;
    case 6: 
      if (this.user.isSwitcher()) {
        this.user.setSwitcher(false);
      }
      break;
    case 7: 
      if (!this.user.isSwitcher())
      {
        this.user.getZorroWaterDrop().reset();
        this.user.releasedArtWorks.add(this.user.getZorroWaterDrop());
        this.user.setSwitcher(true);
        getUser().sound("/audio/Zorro_Shi_Shin_SonSon_WaterDrop.wav");
      }
      if (!this.user.getZorroWaterDrop().dead)
      {
        this.user.picManager.resetToIndex(7);
      }
      else if (this.user.picManager.isAlmostFinished(7))
      {
        getUser().sound("/audio/Zorro_Shi_Shin_SonSon_Dash.wav");
        makeTelePort();
        
        this.user.picManager.resetToIndex(8);
      }
      break;      
    case 12: 
      if (this.user.isSwitcher()&& (this.outro > 0) && (this.user.picManager.isAlmostFinished(12)))
      {
    	if(outro == outroMax)
    		getUser().sound("/audio/Zorro_Ittoryou.wav");
    	if(outro == outroMax/2)
    	{
    		 getUser().sound("/audio/Zorro_Shi_Shin_SonSon_Talk.wav");
             getUser().sound("/audio/Zorro_Shi_Shin_SonSon_3.wav");
    	}
    	outro-=1;
    	this.user.picManager.resetToIndex(11);  
      }
      if(outro == 0)
    	  this.user.setSwitcher(false);
      
      break;
    case 13: 
      if (!this.user.isSwitcher())
      {
        this.user.getShiShinShinSonSlash().reset(new Vector2d(user.enemy.pos.x,user.enemy.pos.y),user.statusLogic.isRight());
        this.user.releasedDusts.add(this.user.getShiShinShinSonSlash());
        getUser().sound("/audio/Zorro_Sword_ReturnToSheath.wav");
        this.user.setSwitcher(true);
      }
      break;
    }
  }
  
  private void makeTelePort()
  {
    float x2 = this.user.enemy.pos.x;
    if (this.user.behindEnemy()) {
      this.user.statusLogic.swapDirection();
    }
    if (this.user.statusLogic.isRight()) {
      this.user.pos.x = (x2 + 5.5F);
    } else {
      this.user.pos.x = (x2 - 5.5F);
    }
  }
  
  public void checkContent()
  {
    getUser().enemy.damage(getUser().getStrength() * 50.0F);
    getUser().sound(getUser().enemy.cry());
    getUser().sound("/audio/Zorro_Sword_Slash_Hit_5.wav");
    
    this.user.getBloodSplash2().reset(new Vector2d(this.user.enemy.pos.x - 0.5F, this.user.enemy.pos.y), 
      this.user.enemy.statusLogic.isRight());
    this.user.releasedDusts.add(this.user.getBloodSplash2());
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
    this.user.freeze = false;
    this.user.freeze_loop = false;
    this.user.borderEffecting = false;
    this.user.superAttacking = false;
    this.user.statusLogic.setFocused(false);
    this.talk1 = 10;
    this.talk2 = 8;
    outro = outroMax;
    this.user.statusLogic.setBackGroundEffect(BackGroundEffect.NONE);
    user.getEngine().playMP3();
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isSpecialAttacking5();
  }
  
  public void resetStats()
  {
    this.user.statusLogic.setBackGroundEffect(BackGroundEffect.NONE);
    this.user.superAttacking = false;
    this.talk1 = 10;
    this.talk2 = 8;
    this.user.borderEffecting = false;
    this.user.freeze = false;
    this.user.freeze_loop = false;
    outro = outroMax;
  }
}
