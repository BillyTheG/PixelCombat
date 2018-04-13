package pixelCombat.model.projectiles;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.FireSpark;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.enums.ProjectileActionStates;
import pixelCombat.enums.ProjectileGlobalStates;
import pixelCombat.model.Character;
import pixelCombat.model.chars.Natsu;
import pixelCombat.projectiles.ICanExplode;
import pixelCombat.projectiles.Projectile;

public class Dragon_Breath extends Projectile implements ICanExplode{

	private float damage = 5;
	private Clip clip;
	private float sound_buffer_time = 0f;
	private float sound_duration = 2f;
	private Natsu own_char;
	private boolean canSound = true;
	public Dragon_Breath(Natsu character,
			Vector2d pos, Vector2d target,
			GlobalStates effect,
			float KnockRange, float KnockHeight,
			float dir) {
		super(character.projectilManager,character, 0,	0, pos, target, effect, 100, KnockRange, KnockHeight, dir, 1);
		own_char = character;
	}
	
	@Override
	public void load_own_boxes() 
	{
	 this.boxes = manager.boxes.get("Dragon_Breath");
	}

	@Override
	public void load_own_images() 
	{
		 this.Images 		= manager.pictures.get("dragon_breath");
		 this.loopBools 	= manager.loopBools.get("dragon_breath");
		 this.loopIndizes 	= manager.loopVariabels.get("dragon_breath");
		 this.times 		= manager.times.get("dragon_breath");
	}

	@Override
	public void Dead()
	{
		clip.stop();
		
	}

	public void sound(String url) {
		try {
			
			clip = AudioSystem.getClip();
			AudioInputStream inputStream1 = AudioSystem
					.getAudioInputStream(Character.class.getResource(url));
			clip.open(inputStream1);
			clip.start();
			;

		} catch (Exception e) {
		}
	}
	
	@Override
	public void Creation() 
	{
		
		if(this.canSound)
		{	
			sound("/audio/hoken_creation.wav");
			this.canSound = false;
		}
		
		if(this.picManager.getCurrFrameIndex() == 2 &&				
				this.picManager.getAnimTime() == this.picManager.getTotalDuration())
		{
			this.statusLogic.setActionStates(ProjectileActionStates.MOVE);
		}
	}

	@Override
	public void Active() {
		switch (this.statusLogic.getActionStates()) {

		case CREATION:
			Creation();
			break;
		case MOVE:
			updateMovement();
			break;
		case EXPLOSION:
			explode();
			break;
		default:
			break;
		}
		
	}

	public void checkSoundBuffer(float delta)
	{
		this.sound_buffer_time+=delta;
		if(this.sound_buffer_time >= this.sound_duration)
		{
			this.canSound = true;
			this.sound_buffer_time = 0f;
		}
	}

	
	
	@Override
	public void updateMovement() 
	{
		if(this.canSound)
		{	
			sound("/audio/hoken_move.wav");
			this.canSound = false;
		}
		
		if(!this.creator.attackLogic.isAttacking())
			this.statusLogic.setActionStates(ProjectileActionStates.EXPLOSION);
	}

	@Override
	public void explode() {
		if(this.picManager.getCurrFrameIndex() == 1 &&
				this.picManager.getAnimTime() == this.picManager.getTotalDuration())
			this.statusLogic.setGlobalStatus(ProjectileGlobalStates.DEAD);
		
	}

	@Override
	public void update(float delta) 
	{
		statusLogic.checkUpdate();
		physics.update(delta);
		checkHitDelay(delta);
		if(!this.canSound)
			this.checkSoundBuffer(delta);
		
		if (statusLogic.isAlive()) 		
				Active();
		
		if(statusLogic.isDead())
			Dead();
		
		picManager.update(delta);
		boxLogic.update();		
		
	}
	
	
	@Override
	public void checkDefender(Character defender) {
		if(this.hits(defender))
		{
			defender.damage(damage );
			sound("/audio/natsu_fire.wav");
			sound(defender.cry());
			
			FireSpark currentFireSpark = own_char.firesparks.get(own_char.firesparks_id);
			own_char.firesparks_id = (own_char.firesparks_id + 1)% own_char.firesparks.size();
			currentFireSpark.dustAnimator.start();
			currentFireSpark.pos.x = defender.pos.x;
			currentFireSpark.pos.y = defender.pos.y;
			currentFireSpark.dead = false;
			own_char.releasedDusts.add(currentFireSpark);	
			

			if (!defender.statusLogic.isKnockback()) {

				defender.setKnockbackHeight(this.KnockHeight);
				defender.setKnockbackRange(this.KnockRange);
				defender.checkOnAir();
				defender.statusLogic.setActionStates(ActionStates.STAND);
				defender.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);


			} else {
				own_char.comboTouch(defender, -2f, 1.5f);
			}
			this.canHit = false;
		}
	}

}
