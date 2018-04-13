package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.enums.MovementStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;
import pixelCombat.model.projectiles.Dragon_Breath;

public class NatsuKaryuNoHooken
  extends Attack
{
  private Natsu natsu;
  
  public NatsuKaryuNoHooken(Natsu natsu, int id)
  {
    super(natsu, id);
    this.natsu = natsu;
    setRequiredEnergy(30.0F);
  }
  
  public void process()
  {
    switch (getUser().picManager.getCurrFrameIndex())
    {
    case 0: 
      if (getUser().isSwitcher())
      {
        this.natsu.sound("/audio/natsu_karyuu.wav");
        getUser().setSwitcher(false);
      }
      break;
    case 2: 
      if (!getUser().isSwitcher())
      {
        this.natsu.sound("/audio/hoko.wav");
        getUser().setSwitcher(true);
      }
      break;
    case 3: 
      if (getUser().isSwitcher())
      {
        float x_pos = this.natsu.pos.x + this.natsu.getDir() * 6.5F;
        float y_pos = this.natsu.pos.y + 1.55F;
        makeDragonBreath(x_pos, y_pos);
        getUser().setSwitcher(false);
      }
      break;
    }
  }
  
  private void makeDragonBreath(float x_pos, float y_pos)
  {
    Dragon_Breath breath = new Dragon_Breath(this.natsu, new Vector2d(x_pos, y_pos), new Vector2d(x_pos + this.natsu.getDir(), y_pos), null, 15.0F, -16.0F, this.natsu.getDir());
    if (this.natsu.statusLogic.isRight()) {
      breath.statusLogic.setMovementStates(MovementStates.RIGHT);
    } else {
      breath.statusLogic.setMovementStates(MovementStates.LEFT);
    }
    this.natsu.releasedProjectiles.add(breath);
  }
  
  public void checkContent() {}
  
  public void checkFinished() {}
  
  public boolean isAttacking()
  {
    return this.natsu.attackLogic.isSpecialAttacking4();
  }
  
  public void resetStats() {}
}
