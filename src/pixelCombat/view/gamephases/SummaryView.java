package pixelCombat.view.gamephases;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import pixelCombat.gamephase.Summary;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.gamephase.gamephaseelement.GamePhaseFigure;
import pixelCombat.model.EffectManager;
import pixelCombat.view.Renderer;

public class SummaryView extends Renderer 
{

	public 				Summary				summary;
	public				EffectManager		effectManager 		 = new EffectManager();
	
	protected 			Image 				SUMMARY_SELECTION 	 = GamePlayView.loadImage("/gamePhase/backgrounds/Summary.png");
	protected 			Image 				SUMMARY_PLAYER1WIN	 = GamePlayView.loadImage("/gamePhase/backgrounds/Summary_Player1_Wins.png");
	protected 			Image 				SUMMARY_PLAYER2WIN	 = GamePlayView.loadImage("/gamePhase/backgrounds/Summary_Player2_Wins.png");
	
	private 			float 				figure2_x 			 = 14.5f;
	private 			float 				figure2_y 			 = 8.25f;
	private 			float 				SCALEFACTOR		 	 = -0.2f;
	private 	static 	float 				BOTTOM_LINE			 = 12f;

    public SummaryView(Summary summary,Canvas canvas) 
    {
    	super(canvas);
    	this.summary = summary;
    }

    public void render(long delta) 
    {
    	GamePhaseFigure figure;
    	float 	 x										= 0;	
    	float 	 y										= 0;
    	    	
    	// render background
    	if (MENU_BACKGROUND != null) 
    		graphicsContext.drawImage(MENU_BACKGROUND,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	if (SUMMARY_SELECTION 		!= null) 
    		graphicsContext.drawImage(SUMMARY_SELECTION,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	figure = summary.getWinnerFigure(); 
    		    	
    	x	= figure2_x*GamePlayView.FIELD_SIZE;
    	y   = figure2_y*GamePlayView.FIELD_SIZE;
    	leftDraw(figure.draw(),x,y);
    	
    	x 	= 3f*GamePlayView.FIELD_SIZE;
    	y 	= 3f*GamePlayView.FIELD_SIZE;
    	
    	if(summary.isPlayer1Wins())
    		graphicsContext.drawImage(SUMMARY_PLAYER1WIN,x,y);
    	else
    		graphicsContext.drawImage(SUMMARY_PLAYER2WIN,x,y);
    	
    	for(GamePhaseElement el : summary.elements)
    	{
    		float offset_x		= (float) (el.getAnimator().getImage().getWidth()/2);
    		float offset_y		= (float) (el.getAnimator().getImage().getHeight()/2);
    		
    		x					= el.getPos().x*GamePlayView.FIELD_SIZE;
    		y					= el.getPos().y*GamePlayView.FIELD_SIZE; 		
    		graphicsContext.drawImage(el.draw(),x-offset_x,y-offset_y);		
    	}
    	
    	
    	
    }
    
    
    public void leftDraw(Image p, float x, float y) {
		graphicsContext.drawImage(p,x +fixPic2(p), 
	    			y+ fixBottomOfPictures((float) (p.getHeight()*(GamePlayView.SCALEFACTOR+SCALEFACTOR))),
		    		-p.getWidth()	*(GamePlayView.SCALEFACTOR+SCALEFACTOR),
		    		 p.getHeight()	*(GamePlayView.SCALEFACTOR+SCALEFACTOR)
		    		);
	}
    	
   
    public float fixPic2(Image i) {
    	
    	float halfHeight = (float) (i.getWidth() / 2)*(GamePlayView.SCALEFACTOR+SCALEFACTOR);
    	float diff = 0f;
//    	if(halfHeight >= 300f*(GamePlayView.SCALEFACTOR+SCALEFACTOR))
//    		return 0f;
    	if (100*(GamePlayView.SCALEFACTOR+SCALEFACTOR) < halfHeight)
    	    diff = halfHeight - 100*(GamePlayView.SCALEFACTOR+SCALEFACTOR);
    	if (100*(GamePlayView.SCALEFACTOR+SCALEFACTOR) > halfHeight)
    	    diff = halfHeight - 100*(GamePlayView.SCALEFACTOR+SCALEFACTOR);
    	return diff;
        }
    
    public float fixBottomOfPictures(float y)
    {
    	float diff = BOTTOM_LINE-y;
    	return diff;
    }
    
    
}
