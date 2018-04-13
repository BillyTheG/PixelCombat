package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class Fight extends ArtWork {

	public 	float 	duration 		= 0.85f;
	public 	float 	durationBuffer	= 0f;
	private boolean canDie		= false;
	private boolean canSound2   = true;
	
	
	public Fight() {
		super(new Vector2d(-PXMapHandler.X_FIELDS/2f,PXMapHandler.Y_FIELDS/2),new Vector2d(PXMapHandler.X_FIELDS/2f,PXMapHandler.Y_FIELDS/2),45f,0f);		
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/images/IMG_Misc_Fight.png"));
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.35f);			
		this.dustAnimator = new Animation(images,times,true);

	}

	@Override
	public void update(float delta) {
		super.update(delta);
		
		if(target.distance(pos) == 0f)
		{
			if(canSound2)
			{
				sound("/audio/RoundFight.wav");
				canSound2 = false;
			}
			if(durationBuffer<duration)
				durationBuffer+=delta;
			else
			{
				target.x =  PXMapHandler.X_FIELDS + PXMapHandler.X_FIELDS/2f;
				target.y =	PXMapHandler.Y_FIELDS/2f;	
				canDie 	= true;
				
			}				
			
		}
		
		if(canDie && target.distance(pos) == 0f)
			this.dead = true;
		
		
	}
	
	public void reset(){
		this.dead = false;
		this.durationBuffer = 0f;
		this.repositionate();
		this.target.x = PXMapHandler.X_FIELDS/2f;
		this.target.y = PXMapHandler.Y_FIELDS/2f;
		this.canDie = false;
		canSound2   = true;
	}
	
	
}
