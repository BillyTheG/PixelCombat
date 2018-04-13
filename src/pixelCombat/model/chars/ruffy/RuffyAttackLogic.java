package pixelCombat.model.chars.ruffy;

import pixelCombat.enums.AttackStates;
import pixelCombat.model.AttackLogic;
import pixelCombat.model.chars.Ruffy;

public class RuffyAttackLogic
  extends AttackLogic
{
  public RuffyAttackLogic(Ruffy character)
  {
    super(character);
    this.character = character;
  }
  
  public boolean furtherAttacks()
  {
    return (isGear2Transforming()) || (isGigantoGatling());
  }
  
  public boolean isGear2Transforming()
  {
    return getAttackStatus() == AttackStates.Gear2Transform;
  }
  
  public boolean isGigantoGatling()
  {
    return getAttackStatus() == AttackStates.GigantoGatling;
  }
}
