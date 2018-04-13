package pixelCombat.physics;

import pixelCombat.projectiles.Projectile;

public class ProjectilePhysics  extends Physics<Projectile>{

	public float VX,currentSpeed = 0;
	public float maximumSpeed_X;
	public float VY;
	public float maximumSpeed_Y;
	public static float GRAVITATION = 45f;
	public static float ACCELERATION = 3.35f;
	public static float EPSILON = 0.005f;
	public static float FRICTION = 0.90f;
	
	//control in statusLogic
	public boolean isMoving = false;
	
	public ProjectilePhysics(Projectile projectile)
	{
		super(projectile);
		init();
	}
	
	public void init()
	{
		this.maximumSpeed_X = pxObject.getMovementspeed();
		this.maximumSpeed_Y = pxObject.getMovementSpeed_Y();
		this.VX = 1f;
		this.VY = 1f;
		
	}
	
	@Override
	public void update(float delta)
	{
		if(isMoving)
		{
			this.maximumSpeed_X = pxObject.getMovementspeed();
			this.maximumSpeed_Y = pxObject.getMovementSpeed_Y();
			acceleration(delta);	
			movement(delta);
		}
		
		
	}
	
	public void movement(float delta)
	{
		if(!pxObject.isRotable())
		{
			pxObject.pos.x += pxObject.getDir()*VX*delta;  
			pxObject.pos.y += VY*delta;  	
		}
		else
		{
			pxObject.pos.x += pxObject.getDirection().x*VX*delta;  
			pxObject.pos.y += pxObject.getDirection().y*VY*delta;  	
		}
			
	}
	
	public void acceleration(float delta)
	{
		VX*=ACCELERATION;
		VY*=ACCELERATION;
		if(Math.abs(VX) >= maximumSpeed_X)
			VX = Math.signum(VX)*maximumSpeed_X;
		if(Math.abs(VY) >= maximumSpeed_Y)
			VY = Math.signum(VY)*maximumSpeed_Y;
	}
	
	public void friction(float delta)
	{
		VX*=FRICTION;
	}	
	
	

	public float getVX() {
		return VX;
	}

	public void setVX(float vX) {
		VX = vX;
	}

	public float getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(float currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public float getMaximumSpeed() {
		return maximumSpeed_X;
	}

	public void setMaximumSpeed(float maximumSpeed) {
		this.maximumSpeed_X = maximumSpeed;
	}

	public float getVY() {
		return VY;
	}

	public void setVY(float vY) {
		VY = vY;
	}

	public static float getGRAVITATION() {
		return GRAVITATION;
	}

	public static void setGRAVITATION(float gRAVITATION) {
		GRAVITATION = gRAVITATION;
	}

	public static float getACCELERATION() {
		return ACCELERATION;
	}

	public static void setACCELERATION(float aCCELERATION) {
		ACCELERATION = aCCELERATION;
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
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	
	
	
}
