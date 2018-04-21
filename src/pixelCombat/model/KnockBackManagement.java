package pixelCombat.model;

import pixelCombat.enums.GlobalStates;

public class KnockBackManagement {

	private Character 	player;
	private float		requiredMaxVX 			= 15f;
	private float		requiredLeastVY 		= 10f;
	private float		requiredMaxVY 			= 20f;
	private float 		leastDistanceToGround 	= 1.5f;

	public KnockBackManagement(Character player)
	{
		this.player = player;
	}
	
	
	public void update()
	{
		if(!player.isAlive() || player.enemy.superAttacking) 
			return;
		
		float phyiscxVX = player.physics.VX;
		float physicsVY = player.physics.VY;
		
		
		//Requirement for KnockBack Recover
		if(Math.abs(phyiscxVX) <= requiredMaxVX && physicsVY > requiredLeastVY && physicsVY <= requiredMaxVY
				&& Math.abs(player.pos.y-player.groundLevel) > leastDistanceToGround)
		{
			
			player.physics.VX = 0;
			player.physics.VY = -6.5f;
			player.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACKRECOVER);
			
			
		}
		
		
		
		
		
	}
	
	
	
	
	
	
}
