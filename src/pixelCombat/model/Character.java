package pixelCombat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.image.Image;
import pixelCombat.Math.BoundingRectangle;
import pixelCombat.Math.Vector2d;
import pixelCombat.artworks.ArtWork;
import pixelCombat.controller.GamePlayController;
import pixelCombat.dusts.DefendBubble;
import pixelCombat.dusts.JumpDust;
import pixelCombat.effects.UpAndDownBorder;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.enums.MovementStates;
import pixelCombat.enums.TimeState;
import pixelCombat.ki.AIManager;
import pixelCombat.physics.PlayerPhysics;
import pixelCombat.projectiles.Projectile;
import pixelCombat.projectiles.ProjectileManager;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.AnimatorThread;
import pixelCombat.view.PlayerPicManager;

public abstract class Character extends PXObject {
	private static final float MAXIMAL_VELOCITY_Y_FOR_INVINCIBLE = 6.0F;
	public static final String STAT_ATTACKSPEED = "attackSpeed";
	public static final String STAT_MAXLIFEPOINTS = "maxLifepoints";
	public static final String STAT_ACTUALLIFEPOINTS = "actualLifepoints";
	public static final String STAT_MAXMAGICPOINTS = "maxMagicpoints";
	public static final String STAT_ACTUALMAGICPOINTS = "actualMagicpoints";
	public static final String STAT_STRENGTH = "strength";
	public static final String STAT_MOVEMENTSPEED = "movementSpeed";
	public static final String STAT_REGENERATEMAGICTIME = "regenerateMagictime";
	public Clip clip;
	protected float attackSpeed;
	protected float maxLifepoints;
	protected float actualLifepoints;
	protected float maxMagicpoints;
	protected int strength;
	protected float actualMagicpoints;
	protected float regenerateMagicTime;
	protected String name;
	protected float groundLevel = 7.5F;
	public AttackLogic attackLogic;
	public StatusLogic statusLogic;
	public BoxLogic boxLogic;
	public ViewLogic viewLogic;
	public ParticlePackage ParticleManager = new ParticlePackage();
	public TimeManager timeManager = new TimeManager(this);
	public PlayerPicManager picManager;
	public Thread animator;
	public PlayerPhysics physics;
	public volatile float delta = 0.0F;
	public Character enemy;
	public ProjectileManager projectilManager;
	public List<Projectile> releasedProjectiles;
	public List<Particle> releasedParticles;
	public List<Dust> releasedDusts;
	public List<ArtWork> releasedArtWorks;
	public Map<String, ArrayList<ArrayList<BoundingRectangle>>> boxes;
	public List<ArrayList<BoundingRectangle>> currentBox;
	public Map<String, Attack> attacks = new HashMap<>();
	public List<Spirit> spirits = new ArrayList<>();
	private final String hitSound;
	private final String attackSound;
	private final String specialAttackSound;
	private final String deathSound;
	private JumpDust jumpDust;
	public float attackFactor;
	protected float bufferTime;
	protected float delayMagic = 5.0F;
	public float x;
	public float distanceToGo = 0.0F;
	private Vector2d target = new Vector2d();
	private boolean isJumpAttacking = false;
	protected float retreatRangeMax = 10.0F;
	protected float retreatRange = this.retreatRangeMax;
	private float knockBackRange = 1.0F;
	private float knockBackHeight = -15.0F;
	private float knockBackRange_df = 1.0F;
	private float knockBackHeight_df = -15.0F;
	private KnockBackManagement knockBackManagement = new KnockBackManagement(this);
	public boolean collidesX = false;
	public boolean collidesY = false;
	public boolean collidesBX = false;
	public boolean collidesBY = false;
	public boolean shaking = false;
	public Dust specialBG;
	public boolean borderEffecting = false;
	public boolean superAttacking = false;
	public boolean finishing = false;
	protected boolean switcher = true;
	private Map<String, Integer> airIndices;
	private Map<String, Boolean> airBools;
	protected boolean attackOnAir;
	public volatile AnimatorThread animationThread;
	protected UpAndDownBorder upAndDownBorder;
	public AIManager aiManager;
	public int wins = 0;
	private DefendBubble defendBubble1;
	private DefendBubble defendBubble2;
	private DefendBubble defendBubble3;
	private ArrayList<DefendBubble> defendBubbles = new ArrayList<>();
	private int defendBubble_Id = 0;
	public Console console;
	private int comboNumber;
	protected String landSound = "/audio/groundrecover.wav";
	protected String defendSound = "/audio/defend.wav";
	public String dashSound = "/audio/dash.wav";
	protected String groundHitSound = "/audio/groundhit2.wav";
	protected String jumpSound = "/audio/jump.wav";
	protected String dashSound1 = "/audio/teleport00.wav";
	protected String dashSound2 = "/audio/dash.wav";
	private GameEngine engine;

	public Character(String name, int lifepoints, float movementspeed, float attackspeed, Vector2d pos, int strength,
			int MaxLifePoints, int magicpoints, int MaxMagicPoints, float AttackFactor, String HitSound,
			String AttackSound, String SpecialAttackSound, String DeathSound, ProjectileManager projectileManager) {
		super(movementspeed, pos);
		this.physics = new PlayerPhysics(this);
		this.name = name;
		this.actualLifepoints = lifepoints;
		this.strength = strength;
		this.maxLifepoints = MaxLifePoints;
		this.attackSpeed = attackspeed;
		this.attackFactor = AttackFactor;
		this.hitSound = HitSound;
		this.attackSound = AttackSound;
		this.specialAttackSound = SpecialAttackSound;
		this.deathSound = DeathSound;
		this.actualMagicpoints = 100f;
		this.maxMagicpoints = MaxMagicPoints;
		this.releasedProjectiles = new ArrayList<>();
		this.releasedParticles = new ArrayList<>();
		this.releasedDusts = new ArrayList<>();
		this.releasedArtWorks = new ArrayList<>();
		this.projectilManager = projectileManager;
		this.rank = 0;
		this.upAndDownBorder = new UpAndDownBorder();
		this.console = projectileManager.getConsole();
		setJumpDust(new JumpDust(new Vector2d(0.0F, 0.0F), false));
		init();

		this.defendBubble1 = new DefendBubble(new Vector2d(), true);
		this.defendBubble2 = new DefendBubble(new Vector2d(), true);
		this.defendBubble3 = new DefendBubble(new Vector2d(), true);
		this.defendBubbles.add(this.defendBubble1);
		this.defendBubbles.add(this.defendBubble2);
		this.defendBubbles.add(this.defendBubble3);
	}

	public void sound(String url) {
		try {
			this.clip = AudioSystem.getClip();
			AudioInputStream inputStream1 = AudioSystem.getAudioInputStream(Character.class.getResource(url));
			this.clip.open(inputStream1);
			this.clip.start();
		} catch (Exception localException) {
		}
	}

	public void sound(String url, boolean once) {
		if ((once) && (!this.clip.isRunning())) {
			sound(url);
		}
		if ((!once) && (this.clip.isRunning())) {
			return;
		}
		sound(url);
	}

	public String cry() {
		return this.hitSound;
	}

	public String attack() {
		return this.attackSound;
	}

	public String spAttack() {
		return this.specialAttackSound;
	}

	public String die() {
		return this.deathSound;
	}

	protected abstract void init();

	protected abstract String dash();

	protected abstract String retreat();

	public void updateMovement(){
			if(Math.abs(physics.VX)<= 1f && !physics.isMoving){
				statusLogic.setActionStates(ActionStates.STAND);
			}		
		
	}
	
	public void updateJump() {
		
		if (getPos().y == this.groundLevel) {
			this.jumpDust.reset(new Vector2d(this.pos.x, this.groundLevel + 0.25F), this.statusLogic.isRight());
			this.releasedDusts.add(this.jumpDust);
			this.statusLogic.setOnAir(false);
			this.timeManager.getJumpDelay().setY(Float.valueOf(0.0F));
			setJumpAttacking(false);
			this.attackLogic.setAttackStatus(AttackStates.notAttacking);
			this.freeze = false;
			this.freeze_loop = false;
			setSwitcher(true);

			sound(this.landSound);
			this.statusLogic.setActionStates(ActionStates.JUMP_RECOVER);
		}
		
		if (this.physics.VY >0) {
			this.statusLogic.setActionStates(ActionStates.JUMPFALL);
		}
	}
	
	public void updateJumpFall() {
		if (getPos().y == this.groundLevel) {
			this.jumpDust.reset(new Vector2d(this.pos.x, this.groundLevel + 0.25F), this.statusLogic.isRight());
			this.releasedDusts.add(this.jumpDust);
			this.statusLogic.setOnAir(false);
			this.timeManager.getJumpDelay().setY(Float.valueOf(0.0F));
			setJumpAttacking(false);
			this.attackLogic.setAttackStatus(AttackStates.notAttacking);
			this.freeze = false;
			this.freeze_loop = false;
			setSwitcher(true);

			sound(this.landSound);
			this.statusLogic.setActionStates(ActionStates.JUMP_RECOVER);
		}
	}

	private void updateJumpRecover() {
		this.physics.VX = 0;
		
		if (this.picManager.getAnimTime() == this.picManager.getTotalDuration()) {
			this.statusLogic.setActionStates(ActionStates.STAND);
		}
	}

	public abstract void updateDash();

	private void defend() {
		
		
		if (this.statusLogic.isResetDefending()) {
			this.timeManager.getDefendReleaseTime().setY(Float.valueOf(0.0F));
			if (this.statusLogic.isJumping()) {
				this.statusLogic.setActionStates(ActionStates.AIR_DEFENDING);
			} else {
				this.statusLogic.setActionStates(ActionStates.DEFENDING);
			}
			this.statusLogic.setResetDefending(false);
		}
		
		if(this.timeManager.getDefendReleaseTime().y == this.timeManager.getDelayHolder().get(TimeState.DEFENDRELEASETIME))
		{
			this.statusLogic.setResetDefending(false);
			this.statusLogic.setActionStates(ActionStates.STAND);
		}
		
		
	}

	public void knockBack() {
		if (!this.statusLogic.isOnAir()) {
			sound(cry());
			this.physics.VY = this.knockBackHeight;
			this.physics.VX = (-getDir() * this.knockBackRange);
			this.physics.update(this.delta);
			this.statusLogic.setOnAir(true);
		}
		
		knockBackManagement.update();
		
		
		if ((getPos().y == PXMapHandler.GROUNDLEVEL) && (Math.abs(this.physics.VY) < MAXIMAL_VELOCITY_Y_FOR_INVINCIBLE)) {
			this.timeManager.getDown().setY(Float.valueOf(0.0F));
			sound(this.groundHitSound);
			getJumpDust().reset(new Vector2d(this.pos.x, this.groundLevel + 0.25F), this.statusLogic.isRight());
			this.releasedDusts.add(getJumpDust());
			this.statusLogic.setGlobalStatus(GlobalStates.INVINCIBLE);
			this.knockBackHeight = this.knockBackHeight_df;
			this.knockBackRange = this.knockBackRange_df;
			this.statusLogic.setOnAir(false);
		}
	}
	
	public void knockBackRecover() {

		
		if (this.picManager.getAnimTime() == this.picManager.getTotalDuration()) {
			this.statusLogic.setGlobalStatus(GlobalStates.ACTIVE);
			this.statusLogic.setActionStates(ActionStates.JUMP);
		}
		
	}

	public void invincible() {
		if ((this.picManager.animationIsFinished()) && (isAlive())) {
			this.timeManager.getBlinkTime().setY(Float.valueOf(0.0F));
			this.physics.VX = 0.0F;
			this.statusLogic.setGlobalStatus(GlobalStates.BLINKING);
		}
		if (!isAlive()) {
			this.statusLogic.setGlobalStatus(GlobalStates.DEAD);
			this.physics.VX = 0.0F;
		}
	}

	public void disable() {
		this.physics.VX += -getDir() * 0.2F;
		if (!isAlive()) {
			this.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
			this.physics.VX = 0.0F;
		}
		if (((Float) this.timeManager.getDisableTime().getY())
				.floatValue() == ((Float) this.timeManager.getDelayHolder().get(TimeState.DISABLETIME)).floatValue()) {
			this.statusLogic.setGlobalStatus(GlobalStates.ACTIVE);
			return;
		}
	}

	public void blink() {
		if (((Float) this.timeManager.getBlinkTime().getY())
				.floatValue() == ((Float) this.timeManager.getDelayHolder().get(TimeState.BLINKTIME)).floatValue()) {
			this.statusLogic.setGlobalStatus(GlobalStates.ACTIVE);
			this.statusLogic.setActionStates(ActionStates.STAND);
			this.physics.VX = 0.0F;
			return;
		}
	}

	public void active() {
		if (this.statusLogic.getActionStates() != ActionStates.DASHING) {
			this.retreatRange = this.retreatRangeMax;
		}
		if (!isAlive()) {
			this.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
			this.physics.VX = 0.0F;
		}
	}

	public void reset() {
		resetCharStats();
		this.statusLogic.setGlobalStatus(GlobalStates.ACTIVE);
		this.statusLogic.setActionStates(ActionStates.STAND);
		this.attackLogic.setAttackStatus(AttackStates.notAttacking);
		this.releasedArtWorks.clear();
		this.releasedDusts.clear();
		this.releasedParticles.clear();
		this.releasedProjectiles.clear();
		this.actualLifepoints = this.maxLifepoints;
		this.actualMagicpoints = 0.0F;
		this.physics.reset();
		this.viewLogic.update();
		this.boxLogic.update();
		this.finishing = false;
		this.superAttacking = false;
	}

	public void update(float delta) {
		this.delta = delta;
		this.timeManager.update(this.delta);
		this.statusLogic.checkUpdate();
		this.physics.update(this.delta);
		regenerateMagic();
		if (this.aiManager != null) {
			this.aiManager.update(delta);
		}
		if ((this.statusLogic.isActive()) || (this.statusLogic.isBlinking())) {
			
			if(this.statusLogic.isMoving())
				updateMovement();
			
			if (this.statusLogic.isIntroducting()) {
				introduct();
			}
			if (this.statusLogic.isWinning()) {
				finishing();
			}
			if ((this.attackLogic.isBasicAttacking()) || (isJumpAttacking())) {
				updateAttack();
			}
			if (this.statusLogic.isJumping()) {
				updateJump();
			}
			
			if (this.statusLogic.isJumpFalling()) {
				updateJumpFall();			}
			
			if (this.statusLogic.isJumpRecovering()) {
				updateJumpRecover();
			}
			if (this.statusLogic.isDashing()) {
				updateDash();
			}
			if ((this.statusLogic.isDefending()) || (this.statusLogic.isResetDefending())) {
				defend();
			}
			if (this.attackLogic.isSpecialAttacking()) {
				updateSpecial();
			}
			if (this.attackLogic.isRunAttacking()) {
				updateRunAttack();
			}
			if (this.attackLogic.isAirSpecialAttacking()) {
				updateAirSpecialAttack();
			}
			updateMiscs();
		}
		if ((this.statusLogic.isDead()) || (!isAlive())) {
			dying();
		}
		this.picManager.update(this.delta);
		this.boxLogic.update();
	}

	public abstract void updateMiscs();

	public abstract void dying();

	public abstract void finishing();

	public abstract void introduct();

	public abstract void updateAttack();

	public abstract void updateSpecial();

	public abstract void updateRunAttack();

	public abstract void updateAirSpecialAttack();

	public abstract void checkBasicAttack(Character paramCharacter);

	public abstract void checkBasicAttack1(Character paramCharacter);

	public abstract void checkBasicAttack21(Character paramCharacter);

	public abstract void checkBasicAttack22(Character paramCharacter);

	public abstract void checkBasicAttack23(Character paramCharacter);

	public abstract void checkSpecialAttack1(Character paramCharacter);

	public abstract void checkSpecialAttack2(Character paramCharacter);

	public abstract void checkSpecialAttack3(Character paramCharacter);

	public abstract void checkSpecialAttack4(Character paramCharacter);

	public abstract void checkSpecialAttack5(Character paramCharacter);

	public abstract void checkSpecialAttack6(Character paramCharacter);

	public abstract void checkSpecialAttack7(Character paramCharacter);

	public abstract void checkJumpAttack(Character paramCharacter);

	public abstract void checkRunAttack1(Character paramCharacter);

	public abstract void checkRunAttack2(Character paramCharacter);

	public abstract void checkAirSpecialAttack1(Character paramCharacter);

	public void checkOnAir() {
		if ((this.statusLogic.isOnAir()) || (getPos().y < 6.3F)) {
			sound(cry());
			this.physics.VY = this.knockBackHeight;
			this.physics.VX = (-getDir() * this.knockBackRange);
			this.physics.update(this.delta);
			this.statusLogic.setActionStates(ActionStates.STAND);
			this.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
			return;
		}
		this.statusLogic.setActionStates(ActionStates.STAND);
		this.statusLogic.setGlobalStatus(GlobalStates.DISABLED);
	}

	public boolean canDefend(Character attacker) {
		if ((!isNotHittable()) && (attacker.statusLogic.isRight())) {
			if ((this.statusLogic.isLeft()) && (this.statusLogic.isDefending())) {
				if (!attacker.statusLogic.isHitDelayFinished()) {
					try {
						Thread.sleep(50L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					makeDefendBubbles();
					sound(this.defendSound);

					getPos().x += attacker.getDir() * 0.25F;
					attacker.statusLogic.setAHitDelay(true);
				}
				return true;
			}
		}
		if ((!isNotHittable()) && (attacker.statusLogic.isLeft())) {
			if ((this.statusLogic.isRight()) && (this.statusLogic.isDefending())) {
				if (!attacker.statusLogic.isHitDelayFinished()) {
					try {
						Thread.sleep(50L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					makeDefendBubbles();
					sound(this.defendSound);

					getPos().x += attacker.getDir() * 0.25F;
					attacker.statusLogic.setAHitDelay(true);
				}
				return true;
			}
		}
		if (isNotHittable()) {
			return true;
		}
		
		return false;
	}

	private void makeDefendBubbles() {
		DefendBubble currentDefendBubble = (DefendBubble) this.defendBubbles.get(this.defendBubble_Id);
		this.defendBubble_Id = ((this.defendBubble_Id + 1) % this.defendBubbles.size());
		currentDefendBubble.reset(new Vector2d(getPos().x, getPos().y - 0.5F), this.statusLogic.isRight());
		this.releasedDusts.add(currentDefendBubble);
	}

	public boolean canDefend(Projectile projectile) {
		if ((!isNotHittable()) && (projectile.statusLogic.isRight()) && (this.statusLogic.isLeft())
				&& (this.statusLogic.isDefending()) && (!projectileMighty(projectile))) {
			this.ParticleManager.createDefRings(new Vector2d(getPos().x - 0.5F, getPos().y - 0.75F), this);
			sound(this.defendSound, true);
			getPos().x += projectile.getDir() * 0.1F;
			return true;
		}
		if ((!isNotHittable()) && (projectile.statusLogic.isLeft())) {
			if ((this.statusLogic.isRight()) && (this.statusLogic.isDefending()) && (!projectileMighty(projectile))) {
				this.ParticleManager.createDefRings(new Vector2d(getPos().x + 0.5F, getPos().y - 0.75F), this);
				sound(this.defendSound, true);
				getPos().x += projectile.getDir() * 0.1F;
				return true;
			}
		}
		if (isNotHittable()) {
			return true;
		}
		if (projectile.statusLogic.isRight()) {
			this.statusLogic.setMovementStates(MovementStates.LEFT);
		} else {
			this.statusLogic.setMovementStates(MovementStates.RIGHT);
		}
		return false;
	}

	private boolean projectileMighty(Projectile projectile) {
		return this.rank < projectile.rank;
	}

	public void comboTouch(Character defender, float jumpSpeed, float movementSpeed) {
		if (defender.physics.VY >= 0.0F) {
			defender.physics.VY = jumpSpeed;
		} else {
			defender.physics.VY += jumpSpeed;
		}
		if (Math.signum(defender.physics.VX) == Math.signum(-defender.getDir() * movementSpeed)) {
			defender.physics.VX += -defender.getDir() * movementSpeed;
		} else {
			defender.physics.VX = (-defender.getDir() * movementSpeed);
		}
		defender.statusLogic.setActionStates(ActionStates.STAND);
		defender.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
		defender.picManager.resetToIndex(0);
	}

	public boolean canTouch(Character defender) {
		return this.boxLogic.isCollision(defender);
	}

	public abstract void resetCharStats();

	public boolean isNotHittable() {
		return (this.statusLogic.isInvincible()) || (this.statusLogic.isBlinking());
	}

	public void stopActing() {
		this.switcher = true;
		if ((!this.attackLogic.isSpecialAttacking()) && (!this.attackLogic.isRunAttacking())
				&& (!this.attackLogic.isAirSpecialAttacking())) {
			this.attackLogic.setAttackStatus(AttackStates.notAttacking);
		}
		this.statusLogic.setAHitDelay(false);
		if (this.isJumpAttacking) {
			setJumpAttacking(false);
		}
		this.statusLogic.setActionStates(ActionStates.STAND);
	}

	public Image draw() {
		return this.picManager.getImage();
	}

	public void cancelAction() {
		
		this.statusLogic.setActionStates(ActionStates.STAND);

		this.attackLogic.setAttackStatus(AttackStates.notAttacking);

		setJumpAttacking(false);

		this.switcher = true;

		this.retreatRange = 5.0F;

		this.superAttacking = false;
		resetCharStats();
	}

	public Dust getSpecialBG() {
		return this.specialBG;
	}

	public boolean isAlive() {
		return this.actualLifepoints > 0.0F;
	}

	public boolean enoughMagicMinus(String input_seq) {
		float energy = 0.0F;
		try {
			energy = ((Attack) this.attacks.get(input_seq)).getRequiredEnergy();
		} catch (Exception localException) {
		}
		return enoughMagicMinus(energy);
	}

	public boolean enoughMagic(String input_seq, boolean airAttack) {
		float energy = 0.0F;
		try {
			energy = ((Attack) this.attacks.get(input_seq)).getRequiredEnergy();
		} catch (Exception localException) {
		}
		boolean permittedAttackBeforeRelease = (!this.attackLogic.isBasicAttacking1())
				&& (!this.attackLogic.isBasicAttacking21()) && (!isJumpAttacking()) ? false
						: airAttack ? isJumpAttacking() : true;
		if ((energy <= this.actualMagicpoints)
				&& ((!this.statusLogic.canNotAttack()) || (permittedAttackBeforeRelease))) {
			stopActing();
			return true;
		}
		return false;
	}

	public boolean enoughMagicMinus(float energy) {
		if (energy <= this.actualMagicpoints) {
			this.actualMagicpoints -= energy;
			if (this.actualMagicpoints <= 0.0F) {
				this.actualMagicpoints = 0.0F;
			}
			return true;
		}
		return false;
	}

	public void damage(float dmg) {
		this.actualLifepoints -= dmg;
		this.enemy.increaseCombo();
	}

	private void increaseCombo() {
		this.comboNumber += 1;
	}

	public boolean animationTimeRunnedOut() {
		return this.picManager.animationIsFinished();
	}

	private void regenerateMagic() {
		if ((isAlive()) && (!this.attackLogic.isSpecialAttacking())) {
			this.regenerateMagicTime += this.delta * 40.5F;
			if (this.regenerateMagicTime >= this.delayMagic) {
				if (this.actualMagicpoints < this.maxMagicpoints) {
					this.actualMagicpoints = ((float) (this.actualMagicpoints + 0.5D));
				} else {
					this.actualMagicpoints = 100.0F;
				}
				this.regenerateMagicTime = 0.0F;
			}
		}
	}

	public float getDir() {
		if (this.statusLogic.isRight()) {
			return 1.0F;
		}
		return -1.0F;
	}

	public void kill() {
		this.picManager.kill();
		this.picManager = null;
		this.statusLogic = null;
		this.attackLogic = null;
		this.viewLogic = null;
	}

	public List<Projectile> getReleasedProjectiles() {
		return this.releasedProjectiles;
	}

	public void setReleasedProjectiles(ArrayList<Projectile> releasedProjectiles) {
		this.releasedProjectiles = releasedProjectiles;
	}

	public List<Particle> getReleasedParticles() {
		return this.releasedParticles;
	}

	public boolean checkDefender(Character defender) {
		return (canTouch(defender)) && (!defender.canDefend(this)) && (!this.statusLogic.isHitDelayFinished());
	}

	public void setReleasedParticles(ArrayList<Particle> releasedParticles) {
		this.releasedParticles = releasedParticles;
	}

	public boolean equals(Character obj) {
		return obj.getName().equals(this.name);
	}

	public void setMaxMagicpoints(int maxMagicpoints) {
		this.maxMagicpoints = maxMagicpoints;
	}

	public void setMaxLifepoints(int maxLifepoints) {
		this.maxLifepoints = maxLifepoints;
	}

	public float getActualLifepoints() {
		return this.actualLifepoints;
	}

	public void setActualLifepoints(int actualLifepoints) {
		this.actualLifepoints = actualLifepoints;
	}

	public void setMaxLifepoints(Integer MaxLifePoints) {
		this.maxLifepoints = MaxLifePoints.intValue();
	}

	public float getAttackfactor() {
		return this.attackFactor;
	}

	public void setAttackfactor(float attackfactor) {
		this.attackFactor = attackfactor;
	}

	public float getActualMagicpoints() {
		return this.actualMagicpoints;
	}

	public void setActualMagicpoints(float actualMagicpoints) {
		this.actualMagicpoints = actualMagicpoints;
	}

	public float getRegenerateMagicTime() {
		return this.regenerateMagicTime;
	}

	public void setRegenerateMagicTime(float regenerateMagicTime) {
		this.regenerateMagicTime = regenerateMagicTime;
	}

	public float getDelayMagic() {
		return this.delayMagic;
	}

	public void setDelayMagic(float delayMagic) {
		this.delayMagic = delayMagic;
	}

	public float getAttackSpeed() {
		return this.attackSpeed;
	}

	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public float getBufferTime() {
		return this.bufferTime;
	}

	public void setBufferTime(float bufferTime) {
		this.bufferTime = bufferTime;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public float getDistanceToGo() {
		return this.distanceToGo;
	}

	public float getKnockbackRange() {
		return this.knockBackRange;
	}

	public void setKnockbackRange(float knockbackRange) {
		this.knockBackRange = knockbackRange;
	}

	public float getKnockbackHeight() {
		return this.knockBackHeight;
	}

	public void setKnockbackHeight(float knockbackHeight) {
		this.knockBackHeight = knockbackHeight;
	}

	public void setDistanceToGo(float distanceToGo) {
		this.distanceToGo = distanceToGo;
	}

	public float getGroundLevel() {
		return this.groundLevel;
	}

	public void setGroundLevel(float groundLevel) {
		this.groundLevel = groundLevel;
	}

	public Vector2d getTarget() {
		return this.target;
	}

	public void setTarget(Vector2d target) {
		this.target = target;
	}

	public String getName() {
		return this.name;
	}

	public float getMaxMagicpoints() {
		return this.maxMagicpoints;
	}

	public float getMaxLifepoints() {
		return this.maxLifepoints;
	}

	public boolean isJumpAttacking() {
		return this.isJumpAttacking;
	}

	public void setJumpAttacking(boolean b) {
		this.isJumpAttacking = b;
		this.viewLogic.update();
	}

	public String toString() {
		return "Player " + getName();
	}

	public List<Dust> getReleasedDusts() {
		return this.releasedDusts;
	}

	public void setReleasedDusts(List<Dust> releasedDusts) {
		this.releasedDusts = releasedDusts;
	}

	public void setPicManager(PlayerPicManager picManager) {
		this.picManager = picManager;
	}

	public List<ArtWork> getReleasedArtWorks() {
		return this.releasedArtWorks;
	}

	public void setReleasedArtWorks(List<ArtWork> releasedArtworks) {
		this.releasedArtWorks = releasedArtworks;
	}

	public void setAirAttackConfig(Map<String, Integer> player1_airIndices, Map<String, Boolean> player1_airBools) {
		this.airIndices = player1_airIndices;
		this.airBools = player1_airBools;
	}

	public void setAttackOnAir(boolean attackOnAir) {
		this.attackOnAir = attackOnAir;
	}

	public boolean checkAirCombo(String spriteSeq) {
		return (((Boolean) this.airBools.get(spriteSeq)).booleanValue()) && (!this.statusLogic.canNotAirSpecial1())
				&& (Math.abs(getPos().y - 7.5F) > 2.0F);
	}

	public Map<String, Integer> getAirIndices() {
		return this.airIndices;
	}

	public void setAirIndices(Map<String, Integer> airIndices) {
		this.airIndices = airIndices;
	}

	public Map<String, Boolean> getAirBools() {
		return this.airBools;
	}

	public void setAirBools(Map<String, Boolean> airBools) {
		this.airBools = airBools;
	}

	public boolean isAttackOnAir() {
		return this.attackOnAir;
	}

	public synchronized float getDelta() {
		return this.delta;
	}

	public synchronized void setDelta(float delta) {
		this.delta = delta;
	}

	public boolean isBorderEffecting() {
		return this.borderEffecting;
	}

	public void setBorderEffecting(boolean borderEffecting) {
		this.borderEffecting = borderEffecting;
	}

	public UpAndDownBorder getUpAndDownBorder() {
		return this.upAndDownBorder;
	}

	public void setUpAndDownBorder(UpAndDownBorder upAndDownBorder) {
		this.upAndDownBorder = upAndDownBorder;
	}

	public boolean isSwitcher() {
		return this.switcher;
	}

	public void setSwitcher(boolean switcher) {
		this.switcher = switcher;
	}

	public abstract void loadFurtherImages(List<ArrayList<Image>> paramList, Map<String, ArrayList<Image>> paramMap);

	public abstract void checkFurtherCombos(List<ArrayList<String>> paramList, List<String> paramList1);

	public abstract void checkFurtherAttacks(Character paramCharacter);

	public void changeToAirPoint(String animFrame) {
		int newStartAirIndex = ((Integer) getAirIndices().get(animFrame)).intValue();
		this.picManager.setCurrFrameIndex(newStartAirIndex);
		this.picManager.setAnimTime(this.picManager.getFrame(newStartAirIndex - 1).getEndTime());
	}

	public int getCombos() {
		return this.comboNumber;
	}

	public void setCombos(int newComboNumber) {
		this.comboNumber = newComboNumber;
	}

	public abstract void initializeAI(GamePlayController paramGamePlayController);

	public JumpDust getJumpDust() {
		return this.jumpDust;
	}

	public void setJumpDust(JumpDust jumpDust) {
		this.jumpDust = jumpDust;
	}

	public List<Spirit> getSpirits() {
		return this.spirits;
	}

	public void setSpirits(List<Spirit> spirits) {
		this.spirits = spirits;
	}

	public abstract void checkDash();

	public String getJumpSound() {
		return this.jumpSound;
	}

	public boolean behindEnemy() {
		if (((this.pos.x <= this.enemy.pos.x) && (getDir() == -1.0F))
				|| ((this.pos.x >= this.enemy.pos.x) && (getDir() == 1.0F))) {
			return true;
		}
		return false;
	}

	public boolean deadAndFinished() {
		this.picManager.getClass();
		return this.picManager.getCurrentAnimation() == 25;
	}

	public void setEngine(GameEngine engine) {
		this.engine = engine;

	}

	public GameEngine getEngine() {
		return engine;
	}


}
