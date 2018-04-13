package pixelCombat.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.gamephases.GamePlayView;

public abstract class InterfaceElement {

	protected Vector2d pos;
	protected Image image;
	private GraphicsContext g;
	
	public InterfaceElement(GraphicsContext g, String imagePath)
	{
		this.g  	= g;
		init(imagePath);
	}

	
	public void setPos(Vector2d pos){
		this.pos = pos;
	}
		
	private void init(String imagePath) {		
		this.image = GamePlayView.loadImage(imagePath);
	}
	
	public void draw(){
		g.drawImage(image, pos.x*GamePlayView.SCALEFACTOR*GamePlayView.FIELD_SIZE, pos.y*GamePlayView.SCALEFACTOR*GamePlayView.FIELD_SIZE,
						image.getWidth()*0.5*GamePlayView.SCALEFACTOR,image.getHeight()*0.5*GamePlayView.SCALEFACTOR);
	}
	
	
}
