package pixelCombat.physics;

public abstract class Physics<T> {

	protected T pxObject;
	public float VX,currentSpeed = 0;
	public float maximumSpeed;
	public float VY;
	public static float GRAVITATION = 45f;
	public float ACCELERATION = 1.35f;
	public static float FRICTION = 0.90f;
	
	
	public Physics(T pxObject)
	{
		this.pxObject = pxObject;
		init();
	}
	
	public abstract void init();

	
	public void update(float delta)
	{
		gravitation(delta);
		friction(delta);
		movement(delta);
	}
	
	
	public abstract void movement(float delta);
	
	
	
	
	public void friction(float delta)
	{
		VX*=FRICTION;
	}
	
	
	
	public void gravitation(float delta) {
		VY = VY + GRAVITATION*delta;
	
	}
	
	
	
}
