package pixelCombat.observer;

public interface IObserveContentListener extends Observer{
	public void updateSelectedCharacter(String player1,String player2,boolean vsKi);
	public void updateSelectedMap(int id);
}
