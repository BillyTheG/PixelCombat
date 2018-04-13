package pixelCombat.gamephase.gamephaseelement;

import pixelCombat.model.Character;
import pixelCombat.observer.IObserveComboNumbers;
import pixelCombat.observer.IObserveGameRounds;


public class GamePlayListener implements IObserveComboNumbers, IObserveGameRounds{
	
	private int 		player1ComboNumbers;
	private int 		player2ComboNumbers;
	private float 		player1ComboDuration 	= 0f;
	private float		player2ComboDuration 	= 0f;
	public  float 		comboNumberMaxDuration 	= 0.85f;
	public  int			rounds					= 0;
	
	
	

	
	@Override
	public void updateComboNumbers(Character player1, Character player2) {
		
		if(player1ComboNumbers < player1.getCombos())
			player1ComboDuration = 0f;
		
		if(player2ComboNumbers < player2.getCombos())
			player2ComboDuration = 0f;
		
		player1ComboNumbers = player1.getCombos();
		player2ComboNumbers = player2.getCombos();			
	}


	@Override
	public void update(float delta,Character player1, Character player2) {
		
		updateComboNumbers(player1,player2);
		
		if(player1ComboNumbers>0) this.player1ComboDuration+= delta;
		if(player2ComboNumbers>0) this.player2ComboDuration+= delta;
		
		if(player1ComboDuration>= comboNumberMaxDuration)
		{
			player1ComboDuration = 0f;
			player1ComboNumbers = 0;
			player1.setCombos(0);
		}
		if(player2ComboDuration>= comboNumberMaxDuration)
		{
			player2ComboDuration = 0f;
			player2ComboNumbers = 0;
			player2.setCombos(0);
		}
	}


	@Override
	public void update(float delta) {
		
	}


	@Override
	public void updateRounds(int rounds) {
		this.rounds = rounds;
	}

	
	
	
}
