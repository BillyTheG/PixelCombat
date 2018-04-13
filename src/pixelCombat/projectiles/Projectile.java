package pixelCombat.projectiles;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import pixelCombat.Math.BoundingRectangle;
import pixelCombat.Math.Vector2d;
import pixelCombat.enums.GlobalStates;
import pixelCombat.enums.ProjectileGlobalStates;
import pixelCombat.model.Character;
import pixelCombat.model.PXObject;
import pixelCombat.physics.ProjectilePhysics;
import pixelCombat.utils.Console;

/**
 * PCProjectile
 * 
 * @author BillyG
  * @version 0.1
 */
public abstract class Projectile extends PXObject {
	
	//Basic
	public GlobalStates effect;
	public ProjectileGlobalStates state = ProjectileGlobalStates.ALIVE;
	public ProjectilePhysics physics = new ProjectilePhysics(this);
	
	//Logical Stuff
	public ProjectileStatusLogic statusLogic = new ProjectileStatusLogic(this);
	public ProjectileViewLogic viewLogic;
	public ProjectileBoxLogic boxLogic;
	//Animation + Pic's & Co
	public ProjectilePicManager picManager;
	public ProjectileManager manager;
	
	
	
	public float bufferTime = 0.0f;
	public float endExplosion = 200;
	protected Vector2d target;
	protected Vector2d direction;
	public Character creator;
	private int dmg = 0;
	private int id;
	protected int lifepoints;
	private float movementSpeed_Y;
	
	public static float MAX_RANGE = 40f;
	
	protected final float KnockRange;
	protected final float KnockHeight;
	protected static float hitdelay = 0.16f; 
	protected float hitdelay_buffer = 0f; 
	protected boolean canHit = true;
	//boxes
	public ArrayList<ArrayList<BoundingRectangle>> currentBox;
	public ArrayList<ArrayList<ArrayList<BoundingRectangle>>> boxes;
	//images
	public ArrayList<ArrayList<Image>> Images;
	public ArrayList<ArrayList<Float>> times;
	public ArrayList<Integer> loopIndizes;
	public ArrayList<Boolean> loopBools;
	private Console console;
	
	private boolean isRotable = false;
	private Clip clip;
	
	
	

	/**
	 * Constructor of a Projectile
	 * 
	 * @param id, Identification Number 
	 * @param lifepoints, Lifepoints
	 * @param KnockRange, max. Range of the distance, when knockbacked a char
	 * @param KnockHeight, max. Height of the distance, when knockbacked a char
	 * @param movementspeed, Movementspeed
	 * @param pos, Position
	 * @param dir, Direction
	 * @param effect, Effect
	 * @param dmg, Damage
	 */
	public Projectile(ProjectileManager manager,Character character,float movementspeed_X,float movementSpeed_Y, Vector2d pos,
			Vector2d target, GlobalStates effect, int lifepoints,float KnockRange, float KnockHeight,float dir, int rank ) 
	{
		super(movementspeed_X, pos);
		this.creator = character;
		this.effect = effect;
		this.target = target;
		this.dir = dir;
		this.movementSpeed_Y =movementSpeed_Y;
		this.lifepoints = lifepoints; 
		this.KnockRange = KnockRange;
		this.KnockHeight= KnockHeight;
		this.rank = rank;
		this.manager = manager;
		this.console = manager.getConsole();
		init();
	}

	
	
	private void init() 
	{
		//create dummies
		
		//vectors
		this.direction = new Vector2d();
		//boxes
		this.currentBox = new ArrayList<ArrayList<BoundingRectangle>>();
		this.boxes = new  ArrayList<ArrayList<ArrayList<BoundingRectangle>>>();
		
		//images
		this.Images = new ArrayList<ArrayList<Image>>();
		this.times = new ArrayList<ArrayList<Float>>();
		this.loopIndizes = new ArrayList<Integer>();
		this.loopBools = new ArrayList<Boolean>();	
		
		//Images have to be loaded first
		load_own_images();
		//Boxes have to be loaded
		load_own_boxes();
		
		//initiate the box and view logics now (after images and boxes have been loaded)
		
		//rest stuff
		calculateDirection();
		this.picManager = new ProjectilePicManager(this,Images,times,loopIndizes,loopBools);
		this.viewLogic  = new ProjectileViewLogic(this);
		this.boxLogic = new ProjectileBoxLogic(this,console);
		
	}


	abstract public void load_own_boxes();
	abstract public void load_own_images();



	public float getKnockRange() {
	    return KnockRange;
	}



	public float getKnockHeight() {
	    return KnockHeight;
	}



	public int getLifepoints() {
		return lifepoints; 
	}
	
	public void setLifepoints(int lifepoints){
		
		this.lifepoints = lifepoints; 
	}
	
	
	public int getId() {
		return id;
	}

	@Override
	public float getDir() {
		if (statusLogic.isRight())
			return 1.0f;
		else
			return -1.0f;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getDmg() {
		return dmg;
	}





	public void setDmg(int dmg) {
		this.dmg = dmg;
	}





	public GlobalStates getEffect() {
		return effect;
	}


	public Vector2d getDirection() {
		return direction;
	}





	public void setDirection(Vector2d dir) {
		this.direction = dir;
	}





	public Character getCreator() {
		return creator;
	}

	public void setCreator(Character creator) {
		this.creator = creator;
	}

	public int getID(){
		return id;
	}

	public boolean canTouch(Character defender)
	{
		return boxLogic.isCollision(defender);			
	}

	@Override
	public void update(float delta) 
	{
		statusLogic.checkUpdate();
		physics.update(delta);
		checkHitDelay(delta);
		
		if (statusLogic.isAlive()) 
		{			
				Active();
				if(statusLogic.isInCreation())
					Creation();
					
				if (statusLogic.isMoving())
					updateMovement();
		}
		
		if(statusLogic.isDead())
			Dead();
		
		picManager.update(delta);
		boxLogic.update();		
	}

	public Image draw()
	{				
		return this.picManager.getImage();		
	}

	public abstract void Dead();
	public abstract void Creation();
	public abstract void Active(); 
	public abstract void updateMovement();

	public boolean hits(Character defender)
	{
		return this.canTouch(defender) && this.canHit && !defender.canDefend(this);
	}
	
	public abstract void checkDefender(Character defender);

	public double calculateDirection()
	{
		
				
		double r = Math.sqrt(direction.x*direction.x + direction.y*direction.y );
		double angle = 0;
		if(direction.y >=0)
		{
			angle = Math.acos(direction.x/r)/Math.PI * 180;
		}
		else
		{
			angle = 0- Math.acos(direction.x/r)/Math.PI * 180;
		}
		return angle;	
	}

	public void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	public void checkHitDelay(float delta)
	{
		this.hitdelay_buffer+=delta;
		if(this.hitdelay_buffer >= Projectile.hitdelay)
		{
			this.canHit = true;
			this.hitdelay_buffer = 0f;
		}
	}



	public float getMovementSpeed_Y() {
		return movementSpeed_Y;
	}



	public void setMovementSpeed_Y(float movementSpeed_Y) {
		this.movementSpeed_Y = movementSpeed_Y;
	}



	public boolean isRotable() {
		return isRotable;
	}



	public void setRotable(boolean isRotable) {
		this.isRotable = isRotable;
	}
	 public void sound(String url)
	  {
	    try
	    {
	      this.clip = AudioSystem.getClip();
	      AudioInputStream inputStream1 = 
	        AudioSystem.getAudioInputStream(Character.class.getResource(url));
	      this.clip.open(inputStream1);
	      this.clip.start();
	    }
	    catch (Exception localException) {}
	  }
}
