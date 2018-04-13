package pixelCombat.model.projectiles;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.BloodSplash1;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.enums.ProjectileActionStates;
import pixelCombat.enums.ProjectileGlobalStates;
import pixelCombat.model.Character;
import pixelCombat.model.chars.Zorro;
import pixelCombat.projectiles.ICanExplode;
import pixelCombat.projectiles.Projectile;

public class TasumakiWind extends Projectile implements ICanExplode{

	private float damage = 1;
	private Clip clip;
	private float sound_buffer_time = 0f;
	private float sound_duration = 2.5f;
	private Zorro own_char;
	
	private boolean sounding;
	private boolean canSound = true;
	private int anims;
	private boolean switcher;
	
	
	
	public TasumakiWind(Zorro character,
			Vector2d pos, Vector2d target,
			GlobalStates effect, boolean sounding,
			float dir) {
		super(character.projectilManager,character, 0,	-2f, pos, target, effect, 100, 0, -25f, dir, 1);
		own_char = character;
		this.sounding = sounding;
	}
	
	@Override
	public void load_own_boxes() 
	{
	 this.boxes = manager.boxes.get("TatsumakiWind");
	}

	@Override
	public void load_own_images() 
	{
		 this.Images 		= manager.pictures.get("tatsumakiwind");
		 this.loopBools 	= manager.loopBools.get("tatsumakiwind");
		 this.loopIndizes 	= manager.loopVariabels.get("tatsumakiwind");
		 this.times 		= manager.times.get("tatsumakiwind");
	}

	@Override
	public void Dead()
	{
				
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
		
		if(this.canSound && sounding)
		{	
			sound("/audio/Zorro_Tatsumaki_3.wav");
			this.canSound = false;
		}
		
		if(this.picManager.getAnimTime() == this.picManager.getTotalDuration()){
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
			if(!statusLogic.isDead() && !statusLogic.isExploding())
			this.canSound = true;

			this.sound_buffer_time = 0f;
		}
	}

	
	
	@Override
	public void updateMovement() 
	{
		if(this.canSound && sounding)
		{	
			sound("/audio/Zorro_Tatsumaki_Wind1.wav");
			this.canSound = false;
		}
		
		if(this.picManager.getCurrFrameIndex() == 1 && switcher)
		{
			this.anims++;
			this.switcher = false;
		}
		if(picManager.getCurrFrameIndex()>1)
			this.switcher = true;
			
		if(this.anims > 5)	
			this.statusLogic.setActionStates(ProjectileActionStates.EXPLOSION);
	}

	@Override
	public void explode() {
		
		if(this.canSound && sounding)
		{	
			sound("/audio/Zorro_Tatsumaki_Wind1.wav");
			this.canSound = false;
		}
		
		if(	this.picManager.getAnimTime() == this.picManager.getTotalDuration())
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
			
			if(Math.random() < 0.5) sound("/audio/Zorro_Sword_Slash_Hit_4.wav");
			else	sound("/audio/Zorro_Sword_Slash_Hit_3.wav");
			
			sound(defender.cry());
			
			BloodSplash1 currentBloodSplash = own_char.getBloodSplashs1().get(own_char.getBloodSpashId());
			own_char.setBloodSpashId((own_char.getBloodSpashId() + 1)% own_char.getBloodSplashs1().size());
			currentBloodSplash.reset(new Vector2d(own_char.enemy.pos.x,own_char.enemy.pos.y), own_char.statusLogic.isRight());
			own_char.releasedDusts.add(currentBloodSplash);
			

			if (!defender.statusLogic.isKnockback()) {

				defender.setKnockbackHeight(this.KnockHeight);
				defender.setKnockbackRange(this.KnockRange);
				defender.checkOnAir();
				defender.statusLogic.setActionStates(ActionStates.STAND);
				defender.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);


			} else {
				own_char.comboTouch(defender, -1.8f, 0f);
			}
			this.canHit = false;
		}
	}

}
