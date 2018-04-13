package pixelCombat.view.gamephases;


import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import pixelCombat.gamephase.Credit;
import pixelCombat.view.Renderer;

public class CreditView extends Renderer 
{

	public 		Credit		credit;
	protected 	Image 		CREDIT = GamePlayView.loadImage("/gamePhase/backgrounds/IMG_Menu_Credit.png");	
	
	
	public CreditView(Credit credit, Canvas canvas) 
    {
    	super(canvas);
		this.credit			= credit;
		
    }

    public void render(long delta) 
    {
    	// render background
    	if (MENU_BACKGROUND != null) 
    		graphicsContext.drawImage(MENU_BACKGROUND,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	if (CREDIT 		!= null) 
    		graphicsContext.drawImage(CREDIT,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	

    }

}
