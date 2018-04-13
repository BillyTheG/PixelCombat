package pixelCombat.gamephase.gamephaseelement;

import pixelCombat.observer.IObserveContentListener;



public class ContentListener implements IObserveContentListener {

	public String 	player1;
	public String 	player2;
	public int		mapID;
	private boolean vsAi = false;
	
	@Override
	public void updateSelectedCharacter(String player1, String player2,boolean vsAi) {
		this.player1 = player1;
		this.player2 = player2;
		this.setVsAi(vsAi);
	}

	@Override
	public void updateSelectedMap(int id) {
		this.mapID = id;		
	}

	@Override
	public void update(float delta) {
				
	}

	/**
	 * @return the vsAi
	 */
	public boolean isVsAi() {
		return vsAi;
	}

	/**
	 * @param vsAi the vsAi to set
	 */
	public void setVsAi(boolean vsAi) {
		this.vsAi = vsAi;
	}


}
