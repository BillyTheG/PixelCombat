package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.artworks.NatsuSuper;
import pixelCombat.artworks.OnAir;
import pixelCombat.dusts.FireSpark;
import pixelCombat.dusts.FireSpearAura;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuGurenHooken
  extends Attack
{
  private Natsu natsu;
  private OnAir onAir = new OnAir();
  private FireSpearAura fireSpearAura;
  private FireSpark fireSpark;
  private NatsuSuper natsuSuper;

  
  public NatsuGurenHooken(Natsu natsu, int id)
  {
    super(natsu, id);
    this.natsu = natsu;
    this.fireSpark = new FireSpark(new Vector2d(), true);
    this.fireSpearAura = new FireSpearAura(new Vector2d(), true);
    this.natsuSuper = new NatsuSuper();
    setRequiredEnergy(80.0F);
  }
  
  public void process()
  {
    switch (this.natsu.picManager.getCurrFrameIndex())
    {
    case 0: 
      if (this.natsu.isSwitcher())
      {
    	  
        this.natsu.sound("/audio/Ruffy_SuperAttack.wav");
        natsu.getEngine().stopMP3();
        natsu.getEngine().musicMP3("/audio/Natsu_Guren_Hooken.mp3");
        this.natsu.superAttacking = true;
        this.natsu.getLightning().pos.x = (this.natsu.pos.x - 0.3F);
        this.natsu.getLightning().pos.y = (this.natsu.pos.y - 1.45F);
        this.natsu.getLightning().dustAnimator.start();
        this.natsu.getLightning().dead = false;
        this.natsu.releasedDusts.add(this.natsu.getLightning());
        if (!this.natsu.shaking) {
          this.natsu.shaking = true;
        }
        this.natsu.setSwitcher(false);
      }
      break;
    case 1: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/lightning.wav");
        this.natsu.setSwitcher(true);
        this.natsuSuper.reset();
        this.natsu.releasedArtWorks.add(this.natsuSuper);
      }
      if (!this.natsuSuper.isDead())
      {
        this.natsu.picManager.resetToIndex(1);
        return;
      }
      this.natsu.sound("/audio/natsu_super.wav");
      this.natsu.picManager.resetToIndex(2);
      return;
    case 3: 
      if (this.natsu.isSwitcher())
      {
        this.natsu.getLightning().pos.x = (this.natsu.pos.x - 0.3F);
        this.natsu.getLightning().pos.y = (this.natsu.pos.y - 1.45F);
        this.natsu.getLightning().dustAnimator.start();
        this.natsu.getLightning().dead = false;
        this.natsu.releasedDusts.add(this.natsu.getLightning());
        this.natsu.sound("/audio/lightning.wav");
        this.natsu.sound("/audio/natsu_guren.wav");
        this.natsu.setSwitcher(false);
      }
      break;
    case 4: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/natsu_edited_fire.wav");
        this.natsu.sound("/audio/natsu_explosion_middle.wav");
        this.natsu.getDust1().pos.x = this.natsu.pos.x;
        this.natsu.getDust1().pos.y = (this.natsu.pos.y - 0.35F);
        this.natsu.getDust1().dustAnimator.start();
        this.natsu.getDust1().dead = false;
        this.natsu.setDust2(this.natsu.getDust1());
        this.natsu.freeze = false;
        this.natsu.getAura().pos.x = this.natsu.pos.x;
        this.natsu.getAura().pos.y = (this.natsu.pos.y - 2.85F);
        this.natsu.getAura().dustAnimator.start();
        this.natsu.getAura().dead = false;
        this.natsu.releasedDusts.add(this.natsu.getAura());
        this.natsu.releasedDusts.add(this.natsu.getDust2());
        
        this.fireSpark.reset(new Vector2d(this.natsu.pos.x, 
          this.natsu.pos.y), this.natsu.statusLogic.isRight());
        this.natsu.releasedDusts.add(this.fireSpark);
        this.natsu.setSwitcher(true);
      }
      break;
    case 6: 
      if (this.natsu.isSwitcher())
      {
        if (!this.natsu.shaking) {
          this.natsu.shaking = true;
        }
        this.natsu.sound("/audio/natsu_hooken.wav");
        this.natsu.getBeam().pos.x = this.natsu.pos.x;
        this.natsu.getBeam().pos.y = (this.natsu.pos.y - 12.45F);
        this.natsu.getBeam().dustAnimator.start();
        this.natsu.getBeam().dead = false;
        this.natsu.releasedDusts.add(this.natsu.getBeam());
        this.natsu.setSwitcher(false);
      }
      break;
    case 13: 
      if (!this.natsu.isSwitcher())
      {
        this.natsu.sound("/audio/lightning.wav");
        this.natsu.setSwitcher(true);
      }
      this.natsu.freeze = true;
      this.natsu.freeze_loop = true;
      break;
    }
    if (this.natsu.picManager.getCurrFrameIndex() < 4) {
      this.natsu.freeze = true;
    }
  }
  
  public void checkContent()
  {
    this.natsu.enemy.damage(this.natsu.getStrength() * 16.0F);
    this.natsu.sound(this.natsu.enemy.cry());
    this.natsu.statusLogic.setAHitDelay(true);
    this.natsu.makeFireSpark(this.natsu.enemy);
    if (!this.natsu.shaking) {
      this.natsu.shaking = true;
    }
    if (!this.natsu.enemy.statusLogic.isKnockback())
    {
      this.natsu.enemy.statusLogic.setActionStates(ActionStates.STAND);
      this.natsu.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
      this.natsu.enemy.setKnockbackHeight(-40.0F);
      this.natsu.enemy.setKnockbackRange(0.0F);
    }
    else
    {
      this.natsu.comboTouch(this.natsu.enemy, -30.0F, 0.0F);
    }
  }
  
  public void checkFinished()
  {
    this.natsu.superAttacking = false;
    this.natsu.releasedDusts.remove(this.natsu.getDust2());
    this.natsu.releasedDusts.remove(this.fireSpearAura);
    this.natsu.releasedArtWorks.remove(this.natsuSuper);
    this.natsu.statusLogic.setFocused(false);
    this.natsu.enemy.statusLogic.setFocused(false);
    this.natsu.setBorderEffecting(false);
    this.natsu.statusLogic.setAHitDelay(false);
    this.natsu.freeze = false;
    this.natsu.freeze_loop = false;
    this.onAir.setDead(true);
    this.natsu.releasedArtWorks.remove(this.onAir);
    natsu.getEngine().stopMP3();
    natsu.getEngine().musicPreviousMP3();
  }
  
  public boolean isAttacking()
  {
    return this.natsu.attackLogic.isSpecialAttacking5();
  }
  
  public void resetStats()
  {
    this.natsu.freeze = false;
    this.natsu.freeze_loop = false;
    
    this.natsu.setSwitcher(true);
    this.natsu.superAttacking = false;
    this.natsu.releasedDusts.remove(this.natsu.getDust2());
    this.natsu.releasedDusts.remove(this.fireSpearAura);
    this.natsu.statusLogic.setAHitDelay(false);
    this.natsu.statusLogic.setFocused(false);
    this.natsu.enemy.statusLogic.setFocused(false);
    this.natsu.setBorderEffecting(false);
    this.onAir.setDead(true);
    this.natsu.releasedArtWorks.remove(this.onAir);
    this.natsu.releasedArtWorks.remove(this.natsuSuper);

  }
  

}
