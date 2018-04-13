package pixelCombat.model.chars.natsu.attacks;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import pixelCombat.Math.Vector2d;
import pixelCombat.artworks.OnAir;
import pixelCombat.dusts.FireSpearAura;
import pixelCombat.dusts.NatsuPumpUp;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuGurenBakurenji
  extends Attack
{
  private Natsu natsu;
  private boolean enemyWasHit;
  private OnAir onAir = new OnAir();
  private FireSpearAura fireSpearAura;
  private pixelCombat.artworks.NatsuGurenBakurenji bakurenji;
  private NatsuPumpUp pumpAura;
  private int punches = 10;
  private boolean spearAttackEnabled;
  private boolean activateAnimation = false;
  private Clip clip;
  
  public NatsuGurenBakurenji(Natsu natsu, int id)
  {
    super(natsu, id);
    this.natsu = natsu;
    this.fireSpearAura = new FireSpearAura(new Vector2d(), true);
    this.bakurenji = new pixelCombat.artworks.NatsuGurenBakurenji();
    this.pumpAura = new NatsuPumpUp(new Vector2d(), true);
    setRequiredEnergy(90.0F);
  }
  
  public void process()
  {
    switch (this.natsu.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.natsu.isSwitcher())
      {
        music("/audio/Natsu_Bakurenji_Snd_2.wav");
        this.natsu.sound("/audio/natsu_dragon.wav");
        if (!this.natsu.shaking) {
          this.natsu.shaking = true;
        }
        this.natsu.freeze = true;
        this.natsu.freeze_loop = true;
        this.natsu.borderEffecting = true;
        this.natsu.superAttacking = true;
        this.natsu.getDust3().reset(new Vector2d(this.natsu.pos.x, this.natsu.pos.y - 2.25F), this.natsu.statusLogic.isRight());
        this.pumpAura.reset(new Vector2d(this.natsu.pos.x, this.natsu.pos.y + 0.25F), this.natsu.statusLogic.isRight());
        this.natsu.releasedDusts.add(this.natsu.getDust3());
        this.natsu.releasedDusts.add(this.pumpAura);
        this.natsu.sound("/audio/Natsu_Guren2.wav");
        this.natsu.sound("/audio/Natsu_Pump.wav");
        this.natsu.setSwitcher(false);
      }
      break;
    case 1: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/Natsu_Bakurenji.wav");
        this.natsu.setSwitcher(true);
      }
      break;
    case 3: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.freeze = false;
        this.natsu.freeze_loop = false;
        this.natsu.setSwitcher(false);
      }
      break;
    case 4: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.setSwitcher(true);
      }
      break;
    case 5: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/natsu_23_hit.wav");
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.setSwitcher(false);
      }
      break;
    case 6: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.setSwitcher(true);
      }
      break;
    case 7: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/natsu_23_hit.wav");
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.setSwitcher(false);
      }
      break;
    case 8: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.setSwitcher(true);
      }
      break;
    case 9: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/natsu_23_hit.wav");
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.setSwitcher(false);
      }
      break;
    case 10: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.setSwitcher(true);
      }
      break;
    case 11: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/natsu_23_hit.wav");
        this.natsu.statusLogic.setAHitDelay(false);
        this.natsu.setSwitcher(false);
      }
      if ((this.natsu.picManager.isAlmostFinished(11)) && (this.punches > 0))
      {
        this.punches -= 1;
        this.natsu.enemy.physics.VX += this.natsu.getDir() * 0.25F;
        this.natsu.picManager.resetToIndex(4);
      }
      break;
    case 12: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/Natsu_Bakurenji_Yell_1.wav");
        this.natsu.setSwitcher(true);
      }
      this.natsu.statusLogic.setAHitDelay(false);
      break;
    case 13: 
      if ((this.natsu.isSwitcher()) && (this.natsu.picManager.isAlmostFinished(13)))
      {
        this.natsu.freeze_loop = false;
        this.natsu.freeze = false;
        this.natsu.setSwitcher(false);
      }
      break;
    case 15: 
      if (!this.spearAttackEnabled)
      {
        this.clip.stop();
        this.natsu.attackLogic.setAttackStatus(AttackStates.notAttacking);
        resetStats();
        return;
      }
      if (this.enemyWasHit)
      {
        this.natsu.sound("/audio/Natsu_Bakurenji_Yell_2.wav");
        this.onAir.reset();
        this.natsu.releasedArtWorks.add(this.onAir);
        this.natsu.statusLogic.setFocused(false);
        this.natsu.enemy.statusLogic.setFocused(true);
        if (!this.natsu.shaking) {
          this.natsu.shaking = true;
        }
        this.fireSpearAura.pos.x = this.natsu.pos.x;
        this.natsu.sound("/audio/natsu_edited_fire.wav");
        this.natsu.sound("/audio/natsu_explosion_middle.wav");
        this.natsu.physics.GROUNDLEVELACTIVATED = false;
        this.natsu.pos.y = 18.0F;
        this.natsu.physics.VY = 0.0F;
        this.fireSpearAura.pos.y = (this.natsu.pos.y + 4.35F);
        this.fireSpearAura.dustAnimator.start();
        this.fireSpearAura.dead = false;
        this.natsu.releasedDusts.add(this.fireSpearAura);
        this.enemyWasHit = false;
      }
      break;
    case 16: 
      this.natsu.statusLogic.setAHitDelay(false);
      this.natsu.physics.VY -= 0.95F;
      if (this.natsu.physics.VY < -80.0F) {
        this.natsu.physics.VY = -80.0F;
      }
      break;
    case 17: 
      this.natsu.statusLogic.setAHitDelay(false);
      
      this.natsu.physics.VY -= 0.95F;
      if (this.natsu.physics.VY < -80.0F)
      {
        this.natsu.physics.VY = -80.0F;
      }
      else if (this.natsu.picManager.isAlmostFinished(17))
      {
        this.natsu.sound("/audio/natsu_edited_fire.wav");
        this.natsu.picManager.resetToIndex(16);
      }
      if (!this.natsu.isSwitcher())
      {
        if (!this.natsu.shaking) {
          this.natsu.shaking = true;
        }
        this.fireSpearAura.pos.x = this.natsu.pos.x;
        this.fireSpearAura.pos.y = (this.natsu.pos.y + 4.35F);
        this.natsu.setSwitcher(true);
      }
      else if (this.natsu.physics.VY > 0.0F)
      {
        this.natsu.releasedDusts.remove(this.natsu.getDust3());
        this.natsu.statusLogic.setFocused(false);
      }
      else
      {
        this.fireSpearAura.pos.x = this.natsu.pos.x;
        this.fireSpearAura.pos.y = (this.natsu.pos.y + 4.35F);
      }
      break;
    }
    activateAnimation();
    waitAndLoopUntilAnimationFinished();
    if ((this.spearAttackEnabled) && (this.natsu.picManager.getCurrFrameIndex() >= 15))
    {
      prepareSpearAttackFromBottom();
      return;
    }
    checkForLoops();
  }
  
  private void checkForLoops()
  {
    if ((this.natsu.picManager.getCurrFrameIndex() < 13) && (this.natsu.picManager.getCurrFrameIndex() >= 12))
    {
      this.natsu.freeze = true;
      this.natsu.freeze_loop = true;
    }
  }
  
  private void prepareSpearAttackFromBottom()
  {
    this.natsu.enemy.statusLogic.setFocused(true);
    this.natsu.enemy.pos.x = this.natsu.pos.x;
    this.natsu.enemy.pos.y = -3.5F;
    this.natsu.enemy.physics.VY = 0.0F;
    this.natsu.enemy.physics.VX = 0.0F;
    if (this.natsu.picManager.getCurrFrameIndex() == 15)
    {
      this.natsu.physics.GROUNDLEVELACTIVATED = false;
      this.natsu.pos.y = 15.0F;
      this.natsu.physics.VY = 0.0F;
    }
  }
  
  private void waitAndLoopUntilAnimationFinished()
  {
    if ((!this.bakurenji.dead) && (this.activateAnimation) && (this.natsu.picManager.getCurrFrameIndex() == 17))
    {
      this.natsu.physics.VY = 0.0F;
      this.natsu.enemy.physics.VY = 0.0F;
      
      this.natsu.pos.y = 0.0F;
      this.natsu.enemy.pos.y = 0.0F;
      
      this.natsu.picManager.resetToIndex(17);
    }
  }
  
  private void activateAnimation()
  {
    if ((!this.activateAnimation) && (this.natsu.pos.y < -3.5F))
    {
      this.bakurenji.reset();
      this.natsu.releasedArtWorks.add(this.bakurenji);
      this.activateAnimation = true;
      this.natsu.sound("/audio/natsu_super.wav");
    }
  }
  
  public void checkContent()
  {
    if (this.natsu.picManager.getCurrFrameIndex() < 14)
    {
      if (!this.natsu.shaking) {
        this.natsu.shaking = true;
      }
      this.natsu.enemy.damage(this.natsu.getStrength() * 0.25F);
      this.natsu.sound(this.natsu.enemy.cry());
      this.natsu.statusLogic.setAHitDelay(true);
      this.natsu.makeFireSpark(this.natsu.enemy);
      
      makeKnockback(0.0F, 1.75F);
      return;
    }
    if (this.natsu.picManager.getCurrFrameIndex() == 14)
    {
      this.enemyWasHit = true;
      this.spearAttackEnabled = true;
      this.natsu.sound(this.natsu.enemy.cry());
      this.natsu.sound("/audio/Natsu_JumpAttack_Hit.wav");
      this.natsu.makeFireSpark(this.natsu.enemy);
      this.natsu.enemy.damage(this.natsu.getStrength() * 3.0F);
      this.natsu.statusLogic.setAHitDelay(true);
      makeKnockback(0.0F, 85.0F);
    }
    else if ((this.natsu.picManager.getCurrFrameIndex() == 16) || (this.natsu.picManager.getCurrFrameIndex() == 17))
    {
      this.spearAttackEnabled = false;
      this.natsu.enemy.physics.VX = 0.0F;
      this.natsu.enemy.physics.VY = 0.0F;
      if (this.natsu.enemy.pos.y > this.natsu.pos.y)
      {
        float deltaY = this.natsu.enemy.pos.y - this.natsu.pos.y;
        this.natsu.enemy.pos.y = (this.natsu.pos.y - deltaY - 4.0F);
      }
      else
      {
        this.natsu.enemy.pos.y = (this.natsu.pos.y - 4.0F);
      }
      return;
    }
  }
  
  private void makeKnockback(float VX, float VY)
  {
    if (!this.natsu.enemy.statusLogic.isKnockback())
    {
      this.natsu.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.natsu.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
      this.natsu.enemy.setKnockbackHeight(-VY);
      this.natsu.enemy.setKnockbackRange(VX);
    }
    else
    {
      this.natsu.comboTouch(this.natsu.enemy, -VY, VX);
    }
  }
  
  public void checkFinished()
  {
    this.natsu.physics.GROUNDLEVELACTIVATED = true;
    this.natsu.pos.y = 0.0F;
    this.natsu.physics.VY = 0.0F;
    this.natsu.enemy.pos.y = 0.0F;
    this.natsu.enemy.physics.VY = 0.0F;
    resetStats();
    if (this.clip != null) {
      this.clip.stop();
    }
  }
  
  public boolean isAttacking()
  {
    return this.natsu.attackLogic.isSpecialAttacking7();
  }
  
  public void resetStats()
  {
    this.natsu.superAttacking = false;
    this.natsu.freeze = false;
    this.natsu.freeze_loop = false;
    this.natsu.setBorderEffecting(false);
    
    this.onAir.setDead(true);
    this.natsu.releasedDusts.remove(this.natsu.getDust2());
    this.natsu.releasedDusts.remove(this.fireSpearAura);
    this.natsu.releasedDusts.remove(this.natsu.getDust3());
    this.natsu.releasedArtWorks.remove(this.onAir);
    
    this.natsu.statusLogic.setFocused(false);
    this.natsu.enemy.statusLogic.setFocused(false);
    
    this.enemyWasHit = false;
    this.spearAttackEnabled = false;
    this.activateAnimation = false;
    
    this.punches = 10;
    
    this.natsu.setSwitcher(true);
    this.natsu.statusLogic.setAHitDelay(false);
    if (this.clip != null) {
      this.clip.stop();
    }
  }
  
  public void music(String url)
  {
    try
    {
      if (this.clip != null)
      {
        this.clip.stop();
        this.clip.close();
      }
      this.clip = AudioSystem.getClip();
      AudioInputStream inputStream1 = AudioSystem.getAudioInputStream(getClass().getResource(url));
      this.clip.open(inputStream1);
      this.clip.loop(-1);
    }
    catch (Exception localException) {}
  }
}
