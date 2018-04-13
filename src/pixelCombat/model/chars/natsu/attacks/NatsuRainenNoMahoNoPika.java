package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.auras.Natsu_LightningI;
import pixelCombat.auras.Natsu_LightningII;
import pixelCombat.dusts.JetDust;
import pixelCombat.dusts.Lightning;
import pixelCombat.dusts.NatsuKenkaku;
import pixelCombat.dusts.NatsuLightningFist;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.enums.MovementStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuRainenNoMahoNoPika
  extends Attack
{
  private Natsu natsu;
  private Lightning lightning = null;
  private Natsu_LightningI lightning_aura_1 = null;
  private Natsu_LightningII lightning_aura_2 = null;
  private NatsuLightningFist lightning_fist = null;
  private boolean lightningFistTouch = false;
  private JetDust jumpDust = null;
  private NatsuKenkaku natsuKenkaku = null;
  private float bufferTime = 0.0F;
  private float hitDelayTime = 0.06F;
  
  public NatsuRainenNoMahoNoPika(Natsu natsu, int id)
  {
    super(natsu, id);
    this.natsu = natsu;
    this.lightning = new Lightning(new Vector2d(), true);
    this.lightning_aura_1 = new Natsu_LightningI(new Vector2d(), true);
    this.lightning_aura_2 = new Natsu_LightningII(new Vector2d(), true);
    this.lightning_fist = new NatsuLightningFist(new Vector2d(), true);
    this.jumpDust = new JetDust(new Vector2d(), true);
    this.natsuKenkaku = new NatsuKenkaku(new Vector2d(), true);
    setRequiredEnergy(45.0F);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        this.natsu.sound("/audio/natsu_rainen_no.wav");
        this.lightning.reset(new Vector2d(this.natsu.pos.x - 0.3F, this.natsu.pos.y - 1.45F), this.natsu.statusLogic.isRight());
        this.natsu.releasedDusts.add(this.lightning);
        this.natsu.sound("/audio/lightning.wav");
        if (!this.natsu.shaking) {
          this.natsu.shaking = true;
        }
        getUser().setSwitcher(false);
      }
      break;
    case 2: 
      if (!getUser().isSwitcher())
      {
        makeLightningAura2();
        getUser().setSwitcher(true);
      }
      break;
    case 3: 
      if (getUser().isSwitcher())
      {
        if (!this.natsu.shaking) {
          this.natsu.shaking = true;
        }
        makeLightningAura1();
        getUser().setSwitcher(false);
      }
      break;
    case 5: 
      if (!getUser().isSwitcher())
      {
        makeLightningFist();
        getUser().setSwitcher(true);
      }
      break;
    case 7: 
      if (!this.lightningFistTouch)
      {
        getUser().setSwitcher(false);
        this.natsu.picManager.resetToIndex(21);
      }
      break;
    case 8: 
      this.natsu.statusLogic.setAHitDelay(false);
      break;
    case 9: 
      if (getUser().isSwitcher())
      {
        makeJumpDust();
        getUser().setSwitcher(false);
      }
      break;
    case 12: 
      this.natsu.statusLogic.setAHitDelay(false);
      swapDirection();
      break;
    case 15: 
      this.natsu.statusLogic.setAHitDelay(false);
      break;
    case 17: 
      if (!getUser().isSwitcher())
      {
        makeTeleport();
        getUser().setSwitcher(true);
      }
      break;
    case 18: 
      if (getUser().isSwitcher())
      {
        swapDirection();
        getUser().setSwitcher(false);
      }
      break;
    case 19: 
      if (!getUser().isSwitcher())
      {
        swapDirection();
        getUser().setSwitcher(true);
      }
      break;
    case 20: 
      if (getUser().isSwitcher())
      {
        this.natsuKenkaku.dustAnimator.start();
        this.natsu.releasedDusts.add(this.natsuKenkaku);
        this.natsu.sound("/audio/natsu_kenkaku.wav");
        this.natsuKenkaku.faceRight = this.natsu.statusLogic.isRight();
        getUser().setSwitcher(false);
      }
      checkKekakuAura();
      break;
    case 21: 
      if (!getUser().isSwitcher())
      {
        this.natsu.releasedDusts.remove(this.natsuKenkaku);
        this.natsu.sound("/audio/natsu_kenkaku_cry.wav");
        this.natsu.physics.VY = -20.0F;
        this.natsu.physics.VX = 1.0F;
        getUser().setSwitcher(true);
      }
      break;
    case 24: 
      if ((getUser().isSwitcher()) && (Math.abs(7.5F - this.natsu.pos.y) > 1.5F) && 
        (this.natsu.picManager.isAlmostFinished(24))) {
        this.natsu.picManager.resetToIndex(21);
      }
      break;
    }
    checkLoopAndMiscs();
    checkLightningFist();
  }
  
  private void checkKekakuAura()
  {
    this.natsuKenkaku.pos.x = this.natsu.pos.x;
    this.natsuKenkaku.pos.y = (this.natsu.pos.y + 3.0F);
    this.natsu.physics.VX = (this.natsu.getDir() * 50.0F);
    checkFinishSpecial();
  }
  
  private void checkFinishSpecial()
  {
    this.bufferTime += this.natsu.delta;
    if (this.bufferTime > this.hitDelayTime)
    {
      this.bufferTime = 0.0F;
      this.natsu.statusLogic.setAHitDelay(false);
    }
  }
  
  private void swapDirection()
  {
    if (this.natsu.pos.x <= this.natsu.enemy.pos.x) {
      this.natsu.statusLogic.setMovementStates(MovementStates.RIGHT);
    } else {
      this.natsu.statusLogic.setMovementStates(MovementStates.LEFT);
    }
  }
  
  private void makeTeleport()
  {
    this.natsu.sound("/audio/teleport00.wav");
    this.natsu.pos.y = (this.natsu.enemy.pos.y + 2.5F);
    if (this.natsu.pos.x <= this.natsu.enemy.pos.x)
    {
      this.natsu.statusLogic.setMovementStates(MovementStates.RIGHT);
      this.natsu.pos.x = (this.natsu.enemy.pos.x - 15.0F);
    }
    else
    {
      this.natsu.statusLogic.setMovementStates(MovementStates.LEFT);
      this.natsu.pos.x = (this.natsu.enemy.pos.x + 15.0F);
    }
  }
  
  private void makeJumpDust()
  {
    this.jumpDust.reset(new Vector2d(this.natsu.pos.x, this.natsu.pos.y), this.natsu.statusLogic.isRight());
    this.natsu.releasedDusts.add(this.jumpDust);
    this.natsu.sound("/audio/jump_natsu.wav");
    this.natsu.physics.VX += this.natsu.getDir() * 28.0F;
    this.natsu.physics.VY = -15.0F;
  }
  
  private void checkLightningFist()
  {
    if (!this.lightning_fist.dead)
    {
      this.lightning_fist.pos.x = (this.natsu.pos.x + this.natsu.getDir() * 4.0F);
      this.lightning_fist.pos.y = (this.natsu.pos.y + 2.05F);
    }
  }
  
  private void makeLightningAura2()
  {
    this.natsu.sound("/audio/lightning_fist.wav");
    this.lightning_aura_2.reset(new Vector2d(this.natsu.pos.x - this.natsu.getDir() * 1.5F, this.natsu.pos.y - 1.35F), this.natsu.statusLogic.isRight());
    this.natsu.releasedDusts.add(this.lightning_aura_2);
  }
  
  private void makeLightningAura1()
  {
    this.lightning_aura_1.reset(new Vector2d(this.natsu.pos.x - this.natsu.getDir() * 1.3F, this.natsu.pos.y - 0.5F), this.natsu.statusLogic.isRight());
    this.natsu.releasedDusts.add(this.lightning_aura_1);
    this.natsu.sound("/audio/lightning_fist.wav");
    this.natsu.sound("/audio/natsu_guren.wav");
  }
  
  private void makeLightningFist()
  {
    this.natsu.sound("/audio/natsu_23.wav");
    this.natsu.sound("/audio/lightning_fist.wav");
    this.natsu.sound("/audio/natsu_rainen_no.wav");
    this.natsu.physics.VX += this.natsu.getDir() * 50.0F;
    this.lightning_fist.reset(new Vector2d(this.natsu.pos.x + this.natsu.getDir() * 4.0F, this.natsu.pos.y + 2.05F), this.natsu.statusLogic.isRight());
    this.natsu.releasedDusts.add(this.lightning_fist);
  }
  
  private void checkLoopAndMiscs()
  {
    if (this.natsu.picManager.getCurrFrameIndex() < 4)
    {
      this.natsu.freeze = true;
      this.natsu.freeze_loop = true;
    }
    else
    {
      this.natsu.freeze_loop = false;
    }
    if ((this.natsu.picManager.getCurrFrameIndex() >= 10) && (this.natsu.picManager.getCurrFrameIndex() <= 20)) {
      this.natsu.physics.VY = 0.0F;
    }
    if ((this.natsu.picManager.getCurrFrameIndex() >= 21) && (this.natsu.picManager.getCurrFrameIndex() <= 24)) {
      this.natsu.physics.VX += -this.natsu.getDir() * 1.3F;
    }
  }
  
  public void checkContent()
  {
    if (!this.natsu.shaking) {
      this.natsu.shaking = true;
    }
    if ((this.natsu.picManager.getCurrFrameIndex() == 5) || (this.natsu.picManager.getCurrFrameIndex() == 6))
    {
      this.lightningFistTouch = true;
      this.natsu.sound("/audio/natsu_lightning_hit.wav");
    }
    if ((this.natsu.picManager.getCurrFrameIndex() == 11) || (this.natsu.picManager.getCurrFrameIndex() == 13))
    {
      this.natsu.sound("/audio/meleehit2.wav");
      this.natsu.makeNatsuPunch();
    }
    if (this.natsu.picManager.getCurrFrameIndex() == 20)
    {
      this.natsu.sound("/audio/natsu_explosion_hit.wav");
      this.natsu.makeFireSpark(this.natsu.enemy);
    }
    this.natsu.enemy.damage(this.natsu.getStrength() * 3.0F);
    this.natsu.sound(this.natsu.enemy.cry());
    if (!this.natsu.enemy.statusLogic.isKnockback())
    {
      if ((this.lightningFistTouch))
      {
        this.natsu.enemy.physics.VY = 0.0F;
        this.natsu.enemy.setKnockbackHeight(-25.0F);
        this.natsu.enemy.setKnockbackRange(10.0F);
        this.natsu.enemy.checkOnAir();
        this.natsu.enemy.statusLogic.setActionStates(ActionStates.STAND);
        this.natsu.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
      }
    }
    else
    {
      if ((this.lightningFistTouch))
      {
        this.natsu.enemy.physics.VY = 0.0F;
        this.natsu.enemy.physics.VX = 0.0F;
        this.natsu.comboTouch(this.natsu.enemy, -25.0F, 10.0F);
      }
      if (this.natsu.picManager.getCurrFrameIndex() == 11)
      {
        this.natsu.enemy.physics.VY = 0.0F;
        this.natsu.comboTouch(this.natsu.enemy, -10.0F, 5.0F);
      }
      if (this.natsu.picManager.getCurrFrameIndex() == 13)
      {
        this.natsu.enemy.physics.VY = 0.0F;
        this.natsu.comboTouch(this.natsu.enemy, -30.0F, 25.0F);
      }
      if (this.natsu.picManager.getCurrFrameIndex() == 20)
      {
        this.natsu.enemy.physics.VY = 0.0F;
        this.natsu.enemy.physics.VX = 0.0F;
        this.natsu.comboTouch(this.natsu.enemy, -9.0F, 25.0F);
      }
    }
    this.natsu.statusLogic.setAHitDelay(true);
  }
  
  public void checkFinished()
  {
    this.natsu.statusLogic.setAHitDelay(false);
    this.lightningFistTouch = false;
    this.bufferTime = 0.0F;
    this.natsu.freeze_loop = false;
    this.natsu.freeze = false;
  }
  
  public boolean isAttacking()
  {
    return this.natsu.attackLogic.isSpecialAttacking6();
  }
  
  public void resetStats()
  {
    this.natsu.statusLogic.setAHitDelay(false);
    this.lightningFistTouch = false;
    this.bufferTime = 0.0F;
    this.natsu.freeze = false;
    this.natsu.freeze_loop = false;
  }
}
