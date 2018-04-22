package pixelCombat.model;

import pixelCombat.Math.Vector2d;
/**
 * PCObject
 * 
 * @author BillyG
  * @version 0.1
 */
public abstract class PXObject {

	protected float movementSpeed;
	protected float actualMovementSpeed;
	public Vector2d pos;
	private int id = 0;
	public float dir = 1;
	public int rank; 
	public float delta = 0;
	protected float jumpSpeed = 0f;
	public static float FREEZE_TIME = 0.25f;
	public float freezeBuffer = 0f;
	public boolean freeze = false;
	public boolean freeze_loop = false;
	
	public PXObject(float movementSpeed, Vector2d pos) 
	{
		this.movementSpeed = movementSpeed;
		this.actualMovementSpeed = movementSpeed;
		this.pos = pos;		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	
	/**
	 * Updates the Movement of any Object.
	 * 
	 * 
	 * @param delta, Delta
	 */
	public void updateMovement(){};

	public Vector2d getPos() {
		return pos;

	}



	public float getMovementspeed() {
		return movementSpeed;
	}

	public void setmovementspeed(float movementspeed) {
		this.movementSpeed = movementspeed;
	}

	public void setPos(Vector2d pos) {
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}

	/**
	 * Updates the Object
	 * 
	 * @param delta
	 *            delta
	*/
	public abstract void update(float delta);

	public float getActualmovementspeed() {
		return actualMovementSpeed;
	}

	public void setActualmovementspeed(float actualmovementspeed) {
		this.actualMovementSpeed = actualmovementspeed;
	}

	
	public float getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(float jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}


	public boolean isMoving()
	{
		return false;
	}
	
	
	
	public float getDir() {
		return this.dir;
	}

	public void setDir(float dir) {
		this.dir = dir;
	}

	public void freezeTime(float delta)
	{
		freezeBuffer+= delta;
		if(freezeBuffer >= FREEZE_TIME)
			{
			freezeBuffer = FREEZE_TIME;
			}
		
	}
	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}

}
