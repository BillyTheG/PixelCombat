package pixelCombat.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;

public class Spirit {

	private 			Vector2d 	pos;
	private 			float 		durationBuffer 	= 0f;
	public final static float		MAXDURATION 	= 1f;
	public 				Image 		image;
	private				boolean		dead			= false;
	private 			boolean 	faceRight 		= false;
	private 			BoxBlur 	blur			= new BoxBlur(2, 2, 3);
	private 			double 		OPACITYMAX			= 0.6;
	private				double		OPACITY				=OPACITYMAX;
	
	public Spirit(Image image, Vector2d pos, boolean faceRight)
	{
		this.image = image;
		this.pos   = new Vector2d(pos.x,pos.y);
		this.faceRight = faceRight;
	}
	
	public void update(float delta)
	{
		durationBuffer+= delta;
		
		OPACITY = ((double)MAXDURATION-(double)durationBuffer/(double)MAXDURATION)*(double)OPACITYMAX;
		
		if(durationBuffer>MAXDURATION)
		{
			this.dead = true;
		}
	}

	public boolean isDead() {
		return dead;
	}

	public Vector2d getPos() {
		return pos;
	}

	public void draw(GraphicsContext graphicsContext, float x, float y) {
		graphicsContext.setGlobalAlpha(OPACITY);
		graphicsContext.setEffect(blur);	
		
		if (this.faceRight)
			drawRight(graphicsContext,x, y);
		else
			drawLeft(graphicsContext,x, y);
		
		graphicsContext.setGlobalAlpha(1);
		graphicsContext.setEffect(null);
	}

	private void drawLeft(GraphicsContext graphicsContext, float x, float y) {
		graphicsContext.save();
		graphicsContext.translate((2*x), 0);
		graphicsContext.scale(-1, 1);
		graphicsContext.drawImage(image,
				x - (float) image.getWidth() / 2f,
				y - (float) image.getHeight() / 2f - fixPic(image));
		graphicsContext.restore();
		
	}

	private void drawRight(GraphicsContext graphicsContext, float x, float y) {
		graphicsContext.drawImage(image,
				x - (float) image.getWidth() / 2f,
				y - (float) image.getHeight() / 2f - fixPic(image));
		
	}
	
	private float fixPic(Image i) {
		float halfHeight = (float) (i.getHeight() / 2);
		float diff = 0f;
		if (halfHeight >= 300f)
			return 0f;
		if (100 < halfHeight)
			diff = halfHeight - 100;
		if (100 > halfHeight)
			diff = halfHeight - 100;
		return diff;
	}
	
	
	
}
