package pixelCombat.physics;

import java.util.ArrayList;
import java.util.List;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.HardLand;
import pixelCombat.model.Character;

public class PlayerPhysics extends Physics<Character> {
	private static final float HARD_LAND_VELOCITY_THRESHOLD_VY = 12.0F;
	private static final float KNOCKBACK_GRAV_SCALER_VY = -0.55F;
	public boolean GROUNDLEVELACTIVATED = true;
	public float VX;
	public float currentSpeed = 0.0F;
	public float maximumSpeed;
	public float VY;
	public static float GRAVITATION = 45.0F;
	public float ACCELERATION = 1.35F;
	public static float FRICTION = 0.85F;
	public static float EPSILON = 0.005F;
	private HardLand hardland1 = null;
	private HardLand hardland2 = null;
	private HardLand hardland3 = null;
	private List<HardLand> hardlands = new ArrayList<HardLand>();
	private int hardland_id = 0;
	public boolean isMoving = false;

	public PlayerPhysics(Character character) {
		super(character);
		init();
	}

	public void init() {
		this.maximumSpeed = ((Character) this.pxObject).getMovementspeed();
		this.VX = 0.0F;
		this.VY = 0.0F;

		this.hardlands = new ArrayList<HardLand>();

		this.hardland1 = new HardLand(new Vector2d(), true);
		this.hardland2 = new HardLand(new Vector2d(), true);
		this.hardland3 = new HardLand(new Vector2d(), true);

		this.hardlands.add(this.hardland1);
		this.hardlands.add(this.hardland2);
		this.hardlands.add(this.hardland3);
	}

	public void reset() {
		init();
	}

	public void update(float delta) {
		gravitation(delta);
		if ((((Character) this.pxObject).statusLogic.isActive())
				|| (((Character) this.pxObject).statusLogic.isBlinking())
				|| (((Character) this.pxObject).statusLogic.isInvincible())
				|| (((Character) this.pxObject).statusLogic.isDisabled())) {
			friction(delta);
		}
		if (this.isMoving || (pxObject.aiManager != null) && pxObject.statusLogic.isMoving()) {
			acceleration(delta);
		}
		this.isMoving = false;
		movement(delta);
	}

	public void movement(float delta) {
		((Character) this.pxObject).pos.x += tickDistanceX(delta);
		((Character) this.pxObject).pos.y += this.VY * delta;
		if ((((Character) this.pxObject).statusLogic.isKnockback()) && (((Character) this.pxObject).getPos().y >= 7.5F)
				&& (Math.abs(this.VY) >= 10.0F)) {
			this.VY *= KNOCKBACK_GRAV_SCALER_VY;
			makeGroundDust();

			this.VX = (-((Character) this.pxObject).getDir() * 5.0F);
			((Character) this.pxObject).pos.y += this.VY * delta;
		} else if ((((Character) this.pxObject).getPos().y > 7.5F) && (this.GROUNDLEVELACTIVATED)) {
			((Character) this.pxObject).getPos().y = 7.5F;
			this.VY = 0.0F;
		}
	}

	private void makeGroundDust() {
		if (Math.abs(this.VY) > HARD_LAND_VELOCITY_THRESHOLD_VY) {
			pxObject.getEngine().console.println("That was a hard landing.");
			makeHardLandDust();
			((Character) this.pxObject).sound("/audio/Ruffy_Stamp.wav");
			if (!((Character) this.pxObject).shaking) {
				((Character) this.pxObject).shaking = true;
			}
			return;
		}
		((Character) this.pxObject).getJumpDust()
				.reset(new Vector2d(((Character) this.pxObject).pos.x,
						((Character) this.pxObject).getGroundLevel() + 0.25F),
						((Character) this.pxObject).statusLogic.isRight());
		((Character) this.pxObject).sound("/audio/groundhit2.wav");
		((Character) this.pxObject).releasedDusts.add(((Character) this.pxObject).getJumpDust());
	}

	public void makeHardLandDust() {
		HardLand currentHardLand = (HardLand) this.hardlands.get(this.hardland_id);
		this.hardland_id = ((this.hardland_id + 1) % this.hardlands.size());

		currentHardLand.dustAnimator.start();
		currentHardLand.pos.x = ((Character) this.pxObject).pos.x;
		currentHardLand.pos.y = (((Character) this.pxObject).getGroundLevel() + 0.25F);
		currentHardLand.dead = false;
		((Character) this.pxObject).releasedDusts.add(currentHardLand);
	}

	public float tickDistanceX(float delta) {
		return this.VX * delta;
	}

	public float tickDistanceY(float delta) {
		return Math.abs(this.VY * delta);
	}

	public void acceleration(float delta) {
		this.VX *= this.ACCELERATION;
		if (Math.abs(this.VX) >= this.maximumSpeed) {
			this.VX = (Math.signum(this.VX) * this.maximumSpeed);
		}
	}

	public void friction(float delta) {
		this.VX *= FRICTION;
	}

	public void gravitation(float delta) {
		this.VY += GRAVITATION * delta;
	}

	public float getVX() {
		return this.VX;
	}

	public void setVX(float vX) {
		this.VX = vX;
	}

	public float getCurrentSpeed() {
		return this.currentSpeed;
	}

	public void setCurrentSpeed(float currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public float getMaximumSpeed() {
		return this.maximumSpeed;
	}

	public void setMaximumSpeed(float maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public float getVY() {
		return this.VY;
	}

	public void setVY(float vY) {
		this.VY = vY;
	}

	public static float getGRAVITATION() {
		return GRAVITATION;
	}

	public static void setGRAVITATION(float gRAVITATION) {
		GRAVITATION = gRAVITATION;
	}

	public float getACCELERATION() {
		return this.ACCELERATION;
	}

	public void setACCELERATION(float aCCELERATION) {
		this.ACCELERATION = aCCELERATION;
	}

	public static float getFRICTION() {
		return FRICTION;
	}

	public static void setFRICTION(float fRICTION) {
		FRICTION = fRICTION;
	}

	public static float getEPSILON() {
		return EPSILON;
	}

	public static void setEPSILON(float ePSILON) {
		EPSILON = ePSILON;
	}

	public boolean isMoving() {
		return this.isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
}
