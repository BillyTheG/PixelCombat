package pixelCombat.model;

import javafx.scene.paint.Color;
import pixelCombat.Math.Vector2d;

public class Particle extends PXObject {

	private float lifeSpan;
	private float time;
	private float radius;
	private Color color;
	private Vector2d vel;
	private float range;
	private float MaxRange;

	
	
	/**Constructor of a particle
	 * 
	 * @param movementSpeed
	 * @param po
	 * @param angle
	 * @param radius
	 * @param lifeSpan
	 * @param color
	 */
	public Particle(float movementSpeed, 
					Vector2d pos, 
					float angle, 
					float radius, 
					float lifeSpan,
					Color color) 
	{
		super(movementSpeed, pos);
		this.vel = Vector2d.setVector(angle,movementSpeed);
		this.radius = radius;
		this.lifeSpan = lifeSpan;
		this.color = color;
		this.MaxRange = 1f;
	}

	@Override
	public void updateMovement() {
		time += delta;
		float distance = pos.distance(this.pos.add(vel));
		range+= distance;
		this.pos = this.pos.add(vel);
	}

	
	@Override
	public void update(float delta) {
		this.delta = delta; 
		updateMovement();

	}


	
	public boolean hasDied(){
		if(lifeSpan <= time || range >= MaxRange)
			return true;
		else 
			return false;
	}




	
	/**
	 * @return the lifeSpan
	 */
	public float getLifeSpan() {
		return lifeSpan;
	}

	/**
	 * @param lifeSpan the lifeSpan to set
	 */
	public void setLifeSpan(float lifeSpan) {
		this.lifeSpan = lifeSpan;
	}

	/**
	 * @return the time
	 */
	public float getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(float time) {
		this.time = time;
	}

	/**
	 * @return the radius
	 */
	public float getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the vel
	 */
	public Vector2d getVel() {
		return vel;
	}

	/**
	 * @param vel the vel to set
	 */
	public void setVel(Vector2d vel) {
		this.vel = vel;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public void setMaxRange(float f) {
		this.MaxRange = f;
		
	}
	
	
	
}
