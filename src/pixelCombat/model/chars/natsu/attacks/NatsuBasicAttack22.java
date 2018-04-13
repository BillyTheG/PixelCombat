package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuBasicAttack22
  extends Attack
{
  private Natsu natsu;
  private int yokugeki_max = 1;
  private int yokugeki = this.yokugeki_max;
  
  public NatsuBasicAttack22(Natsu user, int id)
  {
    super(user, id);
    this.natsu = user;
  }
  
  public void process()
  {
    switch (this.natsu.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/natsu_yokugeki.wav");
        this.natsu.setSwitcher(false);
      }
      break;
    case 2: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.sound("/audio/natsu_22.wav");
        this.natsu.setSwitcher(true);
      }
      break;
    case 3: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.sound("/audio/natsu_22.wav");
        this.natsu.setSwitcher(false);
      }
      break;
    case 5: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.sound("/audio/natsu_22.wav");
        this.natsu.setSwitcher(true);
      }
      break;
    case 7: 
      if ((this.natsu.isSwitcher()) && (this.yokugeki > 0))
      {
        this.yokugeki -= 1;
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.sound("/audio/natsu_22.wav");
        this.natsu.picManager.resetToIndex(2);
        this.natsu.setSwitcher(false);
      }
      break;
    }
  }
  
  public void checkContent()
  {
    this.natsu.enemy.damage(this.natsu.getStrength() * 0.2F);
    this.natsu.sound(this.natsu.enemy.cry());
    this.natsu.sound("/audio/natsu_22_hit.wav");
    
    this.natsu.makeNatsuPunch();
    if (!this.natsu.enemy.statusLogic.isKnockback())
    {
      this.natsu.enemy.timeManager.getDisableTime().setY(Float.valueOf(0.1F));
      this.natsu.enemy.physics.VY -= 0.1F;
      this.natsu.enemy.physics.VX += this.natsu.getDir() * 1.0F;
      this.natsu.enemy.checkOnAir();
    }
    else
    {
      this.natsu.comboTouch(this.natsu.enemy, -1.0F, 1.0F);
    }
    this.natsu.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    getUser().sound(getUser().attack());
    getUser().sound("/audio/punch.wav");
    this.yokugeki = this.yokugeki_max;
  }
  
  public boolean isAttacking()
  {
    return getUser().attackLogic.isBasicAttacking22();
  }
  
  public void resetStats()
  {
    this.yokugeki = this.yokugeki_max;
  }
}
