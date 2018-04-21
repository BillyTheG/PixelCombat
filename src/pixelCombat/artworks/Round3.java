package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class Round3 extends ArtWork {

	public 	float 	duration 		= 1f;
	public 	float 	durationBuffer	= 0f;
	private boolean canDie		= false;
	private boolean canSound    = true;
	private boolean canSound2= true;
	
	
	public Round3() {
		super(new Vector2d(-PXMapHandler.X_FIELDS_STANDARD/2f,PXMapHandler.Y_FIELDS_STANDARD/2),new Vector2d(PXMapHandler.X_FIELDS_STANDARD/2f,PXMapHandler.Y_FIELDS_STANDARD/2),30f,0f);		
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/images/IMG_Misc_Round3_Writing.png"));
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.35f);			
		this.dustAnimator = new Animation(images,times,true);
		setSpecialArtWork(true);
	}

	@Override
	public void update(float delta) {
		super.update(delta);
		
		if(canSound)
		{
			sound("/audio/Round.wav");
			canSound = false;
		}
		
		if(firstTarget.distance(pos) == 0f && !canDie)
		{
			if(canSound2 )
			{
				sound("/audio/Round3.wav");
				canSound2 = false;
			}
			if(durationBuffer<duration)
				durationBuffer+=delta;
			else
			{
				firstTarget.x =  PXMapHandler.X_FIELDS_STANDARD + PXMapHandler.X_FIELDS_STANDARD/2f;
				firstTarget.y =	PXMapHandler.Y_FIELDS_STANDARD/2f;	
				canDie 	= true;
			}				
			
		}	
		
		if(canDie && firstTarget.distance(pos) == 0f)
			this.dead = true;
		
		
	}
	
	public void reset(){
		this.dead = false;
		this.durationBuffer = 0f;
		this.repositionate();
		this.firstTarget.x = PXMapHandler.X_FIELDS_STANDARD/2f;
		this.firstTarget.y = PXMapHandler.Y_FIELDS_STANDARD/2f;
		this.canDie = false;
		canSound2= true;
		canSound    = true;
	}
	
	
}
