package pixelCombat.artworks;

import pixelCombat.Math.Vector2d;

public abstract class MovableArtWork extends ArtWork {
	
	private Vector2d secondTarget;
	public 	float 	duration 		= 0.4f;
	public 	float 	durationBuffer	= 0f;
	private Vector2d currentTarget;

	
	
	public MovableArtWork(Vector2d pos,Vector2d firstTarget,Vector2d secondTarget, float VX, float VY) 
	{
		super(pos, VX, VY);
		this.pos = pos;
		this.firstTarget = firstTarget;
		this.secondTarget = secondTarget;
		this.startPos = new Vector2d(pos.x,pos.y);
		this.VX = VX;
		this.VY = VY;
		currentTarget = firstTarget;
	}

	@Override
	public void update(float delta) {
		dustAnimator.update(delta);
		
		move(delta);
		checkIfTargetReached(currentTarget);
		if(currentTarget.distance(pos) == 0 && duration > durationBuffer)
		{
			durationBuffer+=delta;
			if(duration <= durationBuffer)
				currentTarget = secondTarget;
			
			return;
		}
		
		
				
		if(currentTarget.distance(pos) == 0 && duration <= durationBuffer)
		{
			this.dead = true;
			this.durationBuffer = 0f;
		}
		
		
	}
	
	@Override
	public void move(float delta) {
		this.pos.x += VX*delta;
		this.pos.y += VY*delta;
	}
	
	@Override
	public void reset() {
		sound("/audio/signalForSpecial.wav");
		durationBuffer = 0f;
		currentTarget = firstTarget;
		super.reset();
	}
	
}
