package pixelCombat.view.gamephases;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import pixelCombat.Math.Vector2d;
import pixelCombat.gamephase.Loading;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.model.EffectManager;
import pixelCombat.view.Renderer;

public class LoadingView extends Renderer 
{

	public 				Loading				loading;
	public				EffectManager		effectManager 		 = new EffectManager();
	
	
	
	protected 			Image 				LOADING 	 = GamePlayView.loadImage("/gamePhase/backgrounds/Loading.png");
	protected 			Image 				FUNFACT		 = null;
	private int x;
	private int y;

    public LoadingView(Loading loading,Canvas canvas) 
    {
    	super(canvas);
    	this.loading = loading;
    }

    public void setFunFact(int number){
    	String funFact_url = "/gamePhase/elements/IMG_Fun_Facts_"+number+".png";
    	FUNFACT = GamePlayView.loadImage(funFact_url);
    }
    
    public void render(long delta) 
    {
    	float 	 x										= 0;	
    	float 	 y										= 0;
    	    	
    	// render background
    	if (MENU_BACKGROUND != null) 
    		graphicsContext.drawImage(MENU_BACKGROUND,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	if (LOADING != null) 
    		graphicsContext.drawImage(LOADING,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	if (FUNFACT 		!= null) 
    		graphicsContext.drawImage(	FUNFACT,GamePlayView.SCREEN_WIDTH/2*GamePlayView.SCALEFACTOR-110,40,
    									FUNFACT.getWidth()*GamePlayView.SCALEFACTOR,FUNFACT.getHeight()*GamePlayView.SCALEFACTOR);
    	
  
    	
    	for(GamePhaseElement el : loading.elements)
    	{
    		float offset_x		= (float) (el.getAnimator().getImage().getWidth()/2);
    		float offset_y		= (float) (el.getAnimator().getImage().getHeight()/2);
    		
    		x					= el.getPos().x*GamePlayView.FIELD_SIZE;
    		y					= el.getPos().y*GamePlayView.FIELD_SIZE; 		
    		graphicsContext.drawImage(el.draw(),x-offset_x,y-offset_y);		
    	}
    	
    	drawLoadingBar();
    	
    }
    

    private void drawLoadingBar() {
    	float barWidth 	=	8f * GamePlayView.FIELD_SIZE;
    	float scale 	= 	(float)loading.getLoading() / 100f * barWidth * GamePlayView.SCALEFACTOR;
		
    	
    	Vector2d pos = new Vector2d(8f,8.605f);
    	x = (int) (pos.x * GamePlayView.FIELD_SIZE * GamePlayView.SCALEFACTOR);
		y = (int) (pos.y * GamePlayView.FIELD_SIZE * GamePlayView.SCALEFACTOR);
		graphicsContext.setFill(Color.DARKBLUE);
		graphicsContext.fillRect(x, y, scale, 47 * GamePlayView.SCALEFACTOR);
		graphicsContext.setFill(Color.BLACK);
		
    }
    
}
