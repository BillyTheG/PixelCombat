package pixelCombat.ai;

import java.util.ArrayList;
import java.util.Random;

public class ActionProbabilityHandler {

	private static Random 			random 			= new Random();
	
	
	public static int computeActionSquareId(ArrayList<Integer> action_priorities){
		
		float[] 			probabilites    = new float[action_priorities.size()];		
		int sum = 0;
		for(Integer actionProb : action_priorities)
			sum+= actionProb*actionProb;
		
		//sum = Arrays.stream(action_priorities).sum();
		
		for(int i = 0; i<probabilites.length;i++)
			probabilites[i] = (float)action_priorities.get(i)*(float)action_priorities.get(i)/(float)sum;
		
		float randomNumber = random.nextFloat();
		float offset	   = 0;	
		for(int i = 0; i<probabilites.length;i++)
		{
			if(offset <= randomNumber && randomNumber<= offset+probabilites[i])
				return i;
			
			offset+= probabilites[i];
		}
		return 0;
	}
	
	public static int computeActionLinearId(ArrayList<Integer> action_priorities){
		
		float[] 			probabilites    = new float[AIManager.MAXACTIONS];		
		
		int sum = 0;
		for(Integer actionProb : action_priorities)
			sum+= actionProb;
		
		//sum = Arrays.stream(action_priorities).sum();
		
		for(int i = 0; i<probabilites.length;i++)
			probabilites[i] = action_priorities.get(i)*action_priorities.get(i)/sum;
		
		float randomNumber = random.nextFloat();
		float offset	   = 0;	
		for(int i = 0; i<probabilites.length;i++)
		{
			if(offset <= randomNumber && randomNumber<= offset+probabilites[i])
				return i;
			
			offset+= probabilites[i];
		}
		return 0;
	}
	
	
	
	
}
