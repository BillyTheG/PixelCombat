package pixelCombat.view.gamephases;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import pixelCombat.gamephase.Title;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.view.Renderer;

public class TitleView extends Renderer 
{
	
    private GraphicsContext graphicsContext;
    private Image			title_writing		= GamePlayView.loadImage("/gamePhase/titles/Title_1.png");
    private Title			title;
    private Canvas			canvas;

	public TitleView(Title title,Canvas canvas) 
    {
		super(canvas);
    	this.title				= title;
    	this.canvas				= canvas;
    	this.canvas.setHeight(GamePlayView.SCREEN_HEIGHT);
    	this.canvas.setWidth(GamePlayView.SCREEN_WIDTH);
		this.graphicsContext 	= this.canvas.getGraphicsContext2D();
    }



    public void render(long delta) 
    {
    	
    	float 	 x		= 0;	
    	float 	 y		= 0;
    	
    	// render background
    	if (MENU_BACKGROUND != null) 
    	    graphicsContext.drawImage(MENU_BACKGROUND,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	// render title  
    	if(title_writing 	!= null)
    		graphicsContext.drawImage(title_writing,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	for(GamePhaseElement el : title.elements)
    	{
    		float offset_x		= (float) (el.getAnimator().getImage().getWidth()/2);
    		float offset_y		= (float) (el.getAnimator().getImage().getHeight()/2);
    		
    		x					= el.getPos().x*GamePlayView.FIELD_SIZE;
    		y					= el.getPos().y*GamePlayView.FIELD_SIZE; 		
    		graphicsContext.drawImage(el.draw(),x-offset_x,y-offset_y);		
    	}
    	
    }

}
