package pixelCombat.model;

import java.util.Random;

import javafx.scene.paint.Color;
import pixelCombat.Math.Vector2d;

public class ParticlePackage {

	private Random random = new Random();
	
	
	public void createParticleRings(Vector2d pos, Character player){
		
		if(player.releasedParticles.size()> 50)
			return;
		
		for(int ring = 0; ring<10; ring++)
		{
			float velocity = 0.07f + (float) random.nextInt(10) /100f ;
			float lifeSpan = 1.2f + random.nextInt(10) / 100f;
			float radius = 2f ;
			
			
			for(int i = 0; i< 100; i++){
				float angle = (float) Math.toRadians(random.nextInt(360));
				int number = random.nextInt(2);
				Particle p = new Particle(velocity, pos, angle,radius, lifeSpan,Color.WHITE);
				p.setMaxRange(1.2f);
				switch (number){
				case 0: p.setColor(Color.WHITE);break;
				case 1: p.setColor(Color.LIGHTBLUE);break;
				
				}
				player.releasedParticles.add(p);	
				if(player.releasedParticles.size()> 50)
					return;
			}
			
			
		}		
	}
	
	public void createParticleWinds(Vector2d pos, int a, int b, Character player){
		for(int wind = 0; wind<5; wind++)
		{
			float velocity = 0.09f + (float) random.nextInt(10) /100f ;
			float lifeSpan = 0.2f + random.nextInt(10) / 100f;
			float radius = 1f;
			
			
			for(int i = 0; i< 30; i++){
				float angle = 0;
				int number = random.nextInt(3);
				if(player.statusLogic.isRight())
					angle = (float) Math.toRadians(a);
				else
					angle = (float) Math.toRadians(b);
				number = random.nextInt(4);
				
				Particle p = new Particle(velocity, new Vector2d(pos.x - player.getDir()*(float)i/100f,pos.y), angle,radius, lifeSpan,Color.WHITE);
				p.setMaxRange(3f);
				switch (number){
				case 0: p.setColor(Color.WHITE);break;
				case 1: p.setColor(Color.GREY);break;
				case 3: p.setColor(Color.LIGHTGRAY);break;
				
				}
				
				player.releasedParticles.add(p);
			}
			
			
		}		
	}
	
	public void createDefRings(Vector2d pos, Character player){
				
		if(player.releasedParticles.size()> 50)
			return;
		for(int ring = 0; ring<10; ring++)
				{
					float velocity = 0.15f + (float) random.nextInt(10) /100f ;
					float lifeSpan = 4.5f + random.nextInt(10) / 100f;
					float radius = 1f ;
					
					
					for(int i = 0; i< 70; i++){
						float angle = (float) Math.toRadians(random.nextInt(360));
						int number = random.nextInt(3);
						Particle p = new Particle(velocity, pos, angle,radius, lifeSpan,Color.WHITE);
						p.setMaxRange(1.2f);
						switch (number){
						case 0: p.setColor(Color.LIGHTGRAY);break;
						case 1: p.setColor(Color.LIGHTGRAY);break;
						case 2: p.setColor(Color.LIGHTGRAY);break;
						
						
						}
						player.releasedParticles.add(p);	
						if(player.releasedParticles.size()> 50)
							return;
					}
					
					
				}		
			}
	
	public void createJumpLines(Vector2d pos, Character player){
		for(int wind = 0; wind<3; wind++)
		{
			float velocity = 0.09f + (float) random.nextInt(10) /100f ;
			float lifeSpan = 0.1f + random.nextInt(10) / 100f;
			float radius =1f;
				for(int i = 0; i< 300; i++){
				float angle = 0;
				int number = random.nextInt(2);
				if(player.jumpSpeed >= 0)
					angle = (float) Math.toRadians(70);
				else
					angle = (float) Math.toRadians(260);
				number = random.nextInt(4);
				
				Particle p = new Particle(velocity, new Vector2d(pos.x,pos.y +1f- (player.physics.VY)*(float)i/500f), angle,radius, lifeSpan,Color.WHITE);
				p.setMaxRange(0.3f);
				switch (number){
				case 0: p.setColor(Color.GRAY);break;
				case 1: p.setColor(Color.WHITE);break;
				case 2: p.setColor(Color.LIGHTBLUE);break;
				case 3: p.setColor(Color.LIGHTGRAY);break;
				
				}
				
				player.releasedParticles.add(p);
			}
			
			
		}		
	}
	
}
