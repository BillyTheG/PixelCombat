package pixelCombat.observer;

import pixelCombat.model.Character;

public interface IObserveComboNumbers extends Observer{

	public void updateComboNumbers(Character player1, Character player2);

	public void update(float delta, Character player1, Character player2);



}
