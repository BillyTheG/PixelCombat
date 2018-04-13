package pixelCombat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pixelCombat.enums.TimeState;
import pixelCombat.other.Tuple;


public class TimeManager {

	private Character character;
	private Tuple<TimeState,Float> down						;						//delay 80f
	private Tuple<TimeState,Float> disableTime 				;				//delay 40f
	private Tuple<TimeState,Float> blinkTime 				;					//delay 100f
	private Tuple<TimeState,Float> jumpDelay				;	 				//delay 19.5f
	private Tuple<TimeState,Float> dashAndRetreatDelay 		;		//delay 25f
	private Tuple<TimeState,Float> defendReleaseTime 		;			//delay 20f
//	private Tuple<TimeState,Float> basicAttack1_bufferTime	;	
//	private Tuple<TimeState,Float> basicAttack2_bufferTime	;	
//	private Tuple<TimeState,Float> specialAttack1_bufferTime;	
//	private Tuple<TimeState,Float> specialAttack2_bufferTime;	
//	private Tuple<TimeState,Float> specialAttack3_bufferTime;	
//	private Tuple<TimeState,Float> specialAttack4_bufferTime;	
	
	
	private ArrayList<Tuple<TimeState,Float>> timeHolder;
	private Map<TimeState, Float> delayHolder; 
	private Map<TimeState, Float> multiplierHolder; 
	//constant
	protected final float attackReleaseTime = 170f;
	
	//char specific
	public float attackFactor; //im char beim setter neu replacen ins Map
	private float multiplier = 1f; //in updateSpecial änderbar
	
	public TimeManager(Character character){
		this.character = character;
		this.down 						=	 	new Tuple<TimeState, Float>(TimeState.DOWN,80f); 					//delay 80f
		this.disableTime 				= 		new Tuple<TimeState, Float>(TimeState.DISABLETIME,40f);				//delay 40f
		this.blinkTime 					= 		new Tuple<TimeState, Float>(TimeState.BLINKTIME,10f);				//delay 100f
		this.jumpDelay					=	 	new Tuple<TimeState, Float>(TimeState.JUMPDELAY,19.5f); 				//delay 19.5f
		this.dashAndRetreatDelay 		=		new Tuple<TimeState, Float>(TimeState.DASHANDRETREATDELAY,40f);		//delay 25f
		this.defendReleaseTime 			= 		new Tuple<TimeState, Float>(TimeState.DEFENDRELEASETIME,20f);		//delay 20f
//		this.basicAttack1_bufferTime	=		new Tuple<TimeState, Float>(TimeState.BASICATTACK1_BUFFERTIME,1f * character.attackReleaseTime);
//		this.basicAttack2_bufferTime	=		new Tuple<TimeState, Float>(TimeState.BASICATTACK2_BUFFERTIME,1f * character.attackReleaseTime);
//		this.specialAttack1_bufferTime	=		new Tuple<TimeState, Float>(TimeState.SPECIALATTACK1_BUFFERTIME,1f * character.attackReleaseTime);
//		this.specialAttack2_bufferTime	=		new Tuple<TimeState, Float>(TimeState.SPECIALATTACK2_BUFFERTIME,1f * character.attackReleaseTime);
//		this.specialAttack3_bufferTime	=		new Tuple<TimeState, Float>(TimeState.SPECIALATTACK3_BUFFERTIME,1f * character.attackReleaseTime);
//		this.specialAttack4_bufferTime	=		new Tuple<TimeState, Float>(TimeState.SPECIALATTACK4_BUFFERTIME,1f * character.attackReleaseTime);
//		
		
		
		
		this.delayHolder = new HashMap<TimeState, Float>();
		delayHolder.put(TimeState.DOWN, 80f);
		delayHolder.put(TimeState.DISABLETIME, 40f);
		delayHolder.put(TimeState.BLINKTIME, 10f);
		delayHolder.put(TimeState.JUMPDELAY, 19.5f);
		delayHolder.put(TimeState.DASHANDRETREATDELAY, 40f);
		delayHolder.put(TimeState.DEFENDRELEASETIME, 20f);
		
		//standard timer
//		delayHolder.put(TimeState.BASICATTACK1_BUFFERTIME, 1f * character.attackReleaseTime);
//		delayHolder.put(TimeState.BASICATTACK2_BUFFERTIME, 1f * character.attackReleaseTime);
//		delayHolder.put(TimeState.SPECIALATTACK1_BUFFERTIME, 1f * character.attackReleaseTime);
//		delayHolder.put(TimeState.SPECIALATTACK2_BUFFERTIME, 1f * character.attackReleaseTime);
//		delayHolder.put(TimeState.SPECIALATTACK3_BUFFERTIME, 1f * character.attackReleaseTime);
//		delayHolder.put(TimeState.SPECIALATTACK4_BUFFERTIME, 1f * character.attackReleaseTime);
		
		//delayHolder.put(TimeState.BUFFERTIME, attackFactor*attackReleaseTime);
		
		this.multiplierHolder = new HashMap<TimeState, Float>();
		multiplierHolder.put(TimeState.DOWN, 0.5f);
		multiplierHolder.put(TimeState.DISABLETIME, 0.65f);
		multiplierHolder.put(TimeState.BLINKTIME, 0.65f);
		multiplierHolder.put(TimeState.JUMPDELAY, 0.75f);
		multiplierHolder.put(TimeState.DASHANDRETREATDELAY, 1f);
		multiplierHolder.put(TimeState.DEFENDRELEASETIME, 4f);
		
//		multiplierHolder.put(TimeState.BASICATTACK1_BUFFERTIME, 20f);
//		multiplierHolder.put(TimeState.BASICATTACK2_BUFFERTIME, 20f);
//		multiplierHolder.put(TimeState.SPECIALATTACK1_BUFFERTIME, 20f);
//		multiplierHolder.put(TimeState.SPECIALATTACK2_BUFFERTIME, 20f);
//		multiplierHolder.put(TimeState.SPECIALATTACK3_BUFFERTIME, 20f);
//		multiplierHolder.put(TimeState.SPECIALATTACK4_BUFFERTIME, 20f);
		
		
		this.timeHolder = new ArrayList<Tuple<TimeState,Float>>();
		timeHolder.add(down);
		timeHolder.add(disableTime);
		timeHolder.add(blinkTime);
		timeHolder.add(jumpDelay);
		timeHolder.add(dashAndRetreatDelay);
		timeHolder.add(defendReleaseTime);
//		timeHolder.add(basicAttack1_bufferTime);
//		timeHolder.add(basicAttack2_bufferTime);
//		timeHolder.add(specialAttack1_bufferTime);
//		timeHolder.add(specialAttack2_bufferTime);
//		timeHolder.add(specialAttack3_bufferTime);
//		timeHolder.add(specialAttack4_bufferTime);
		
		
		
		
		
	}
	
	
	public void update(float delta){
		
		//time updaten
		for(int i = 0; i< timeHolder.size(); i++)
		{
			TimeState 	timeName 	= 	timeHolder.get(i).x;
			
			float boundary = 0f;
			float multiplier = 0f;
			switch (timeName)
			{
			case DOWN:
					boundary 		= 	delayHolder.get(TimeState.DOWN);
					multiplier 		= 	multiplierHolder.get(TimeState.DOWN);
					break;
			case DISABLETIME:
					boundary 		= 	delayHolder.get(TimeState.DISABLETIME);
					multiplier 		= 	multiplierHolder.get(TimeState.DISABLETIME);
					break;
			case BLINKTIME:
				    boundary 		= 	delayHolder.get(TimeState.BLINKTIME);
				    multiplier 		= 	multiplierHolder.get(TimeState.BLINKTIME);
				    break;
			case JUMPDELAY:
				    boundary 		= 	delayHolder.get(TimeState.JUMPDELAY);
				    multiplier 		= 	multiplierHolder.get(TimeState.JUMPDELAY);
				    break;
			case DASHANDRETREATDELAY:
				    boundary 		= 	delayHolder.get(TimeState.DASHANDRETREATDELAY);
				    multiplier 		= 	multiplierHolder.get(TimeState.DASHANDRETREATDELAY);
				    break;
			case DEFENDRELEASETIME:
				    boundary 		= 	delayHolder.get(TimeState.DEFENDRELEASETIME);
				    multiplier 		= 	multiplierHolder.get(TimeState.DEFENDRELEASETIME);
				    break;
//			case BASICATTACK1_BUFFERTIME:
//					boundary 		= 	delayHolder.get(TimeState.BASICATTACK1_BUFFERTIME);
//					multiplier 		= 	multiplierHolder.get(TimeState.BASICATTACK1_BUFFERTIME);
//					break;
//			case BASICATTACK2_BUFFERTIME:
//					boundary 		= 	delayHolder.get(TimeState.BASICATTACK2_BUFFERTIME);
//					multiplier 		= 	multiplierHolder.get(TimeState.BASICATTACK2_BUFFERTIME);
//					break;
//			case SPECIALATTACK1_BUFFERTIME:
//					boundary 		= 	delayHolder.get(TimeState.SPECIALATTACK1_BUFFERTIME);
//					multiplier 		= 	multiplierHolder.get(TimeState.SPECIALATTACK1_BUFFERTIME);
//					break;
//			case SPECIALATTACK2_BUFFERTIME:
//					boundary 		= 	delayHolder.get(TimeState.SPECIALATTACK2_BUFFERTIME);
//					multiplier 		= 	multiplierHolder.get(TimeState.SPECIALATTACK2_BUFFERTIME);
//					break;
//			case SPECIALATTACK3_BUFFERTIME:
//					boundary 		= 	delayHolder.get(TimeState.SPECIALATTACK3_BUFFERTIME);
//					multiplier 		= 	multiplierHolder.get(TimeState.SPECIALATTACK3_BUFFERTIME);
//					break;
//			case SPECIALATTACK4_BUFFERTIME:
//					boundary 		= 	delayHolder.get(TimeState.SPECIALATTACK4_BUFFERTIME);
//					multiplier 		= 	multiplierHolder.get(TimeState.SPECIALATTACK4_BUFFERTIME);
//					break;
			default:
				break;
			}
			
			
			float time = timeHolder.get(i).y;
			time += ((delta) * 100f * multiplier);
			
			
			if(time >= boundary) //falls überschritten, dann auf Grenze setzen
				timeHolder.get(i).setY(boundary);
			else
				timeHolder.get(i).setY(time);
			

		}
		
		
		
	}
	
	public void resetBufferTimes(){
		down.setY(80f); 
		//disableTime.setY(40f); 	// disableTime bleibt sonst immer beim max			
		character.setBufferTime(0f); // noch zu tun, wegen view und bufferTimes
		blinkTime.setY(10f);  					
		jumpDelay.setY(19.5f); 					
		dashAndRetreatDelay.setY(40f);  		
		defendReleaseTime.setY(20f);  				
//		basicAttack1_bufferTime.setY(20f); 		;
//		basicAttack2_bufferTime.setY(1f * character.attackReleaseTime); 		
//		specialAttack1_bufferTime.setY(1f * character.attackReleaseTime); 	
//		specialAttack2_bufferTime.setY(1f * character.attackReleaseTime); 	
//		specialAttack3_bufferTime.setY(1f * character.attackReleaseTime); 	
//		specialAttack4_bufferTime.setY(1f * character.attackReleaseTime); 			
	}
	

	public Character getCharacter() {
		return character;
	}


	public void setCharacter(Character character) {
		this.character = character;
	}


	public Tuple<TimeState, Float> getDown() {
		return down;
	}


	public void setDown(Tuple<TimeState, Float> down) {
		this.down = down;
	}


	public Tuple<TimeState, Float> getDisableTime() {
		return disableTime;
	}


	public void setDisableTime(Tuple<TimeState, Float> disableTime) {
		this.disableTime = disableTime;
	}


	public Tuple<TimeState, Float> getBlinkTime() {
		return blinkTime;
	}


	public void setBlinkTime(Tuple<TimeState, Float> blinkTime) {
		this.blinkTime = blinkTime;
	}


	public Tuple<TimeState, Float> getJumpDelay() {
		return jumpDelay;
	}


	public void setJumpDelay(Tuple<TimeState, Float> jumpDelay) {
		this.jumpDelay = jumpDelay;
	}


	public Tuple<TimeState, Float> getDashAndRetreatDelay() {
		return dashAndRetreatDelay;
	}


	public void setDashAndRetreatDelay(Tuple<TimeState, Float> dashAndRetreatDelay) {
		this.dashAndRetreatDelay = dashAndRetreatDelay;
	}


	public Tuple<TimeState, Float> getDefendReleaseTime() {
		return defendReleaseTime;
	}


	public void setDefendReleaseTime(Tuple<TimeState, Float> defendReleaseTime) {
		this.defendReleaseTime = defendReleaseTime;
	}


	public ArrayList<Tuple<TimeState, Float>> getTimeHolder() {
		return timeHolder;
	}


	public void setTimeHolder(ArrayList<Tuple<TimeState, Float>> timeHolder) {
		this.timeHolder = timeHolder;
	}


	public Map<TimeState, Float> getDelayHolder() {
		return delayHolder;
	}


	public void setDelayHolder(Map<TimeState, Float> delayHolder) {
		this.delayHolder = delayHolder;
	}


	public Map<TimeState, Float> getMultiplierHolder() {
		return multiplierHolder;
	}


	public void setMultiplierHolder(Map<TimeState, Float> multiplierHolder) {
		this.multiplierHolder = multiplierHolder;
	}


	public float getAttackFactor() {
		return attackFactor;
	}


	public void setAttackFactor(float attackFactor) {
		this.attackFactor = attackFactor;
	}


	public float getMultiplier() {
		return multiplier;
	}


	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}


	public float getAttackReleaseTime() {
		return attackReleaseTime;
	}

	
	
}
	