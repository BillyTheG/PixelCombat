package pixelCombat.view.gamephases;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import pixelCombat.gamephase.MainMenue;
import pixelCombat.gamephase.gamephaseelement.GamePhasePointer;
import pixelCombat.view.Renderer;

public class MainMenueView extends Renderer 
{

	public 		MainMenue		mainMenue;
	protected 	Image 			MAIN_MENUE = GamePlayView.loadImage("/gamePhase/backgrounds/Menu.png");

	public MainMenueView(MainMenue mainMenue, Canvas canvas) 
    {
    	super(canvas);
		this.mainMenue			= mainMenue;
		
    }

    public void render(long delta) 
    {

    	float 	 x					= 0;	
    	float 	 y					= 0;

    	
    	// render background
    	if (MENU_BACKGROUND != null) 
    		graphicsContext.drawImage(MENU_BACKGROUND,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	if (MAIN_MENUE 		!= null) 
    		graphicsContext.drawImage(MAIN_MENUE,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	
    	GamePhasePointer pointer 	= mainMenue.getPointer();
    	x							= pointer.getPos().x * GamePlayView.FIELD_SIZE;
    	y							= pointer.getPos().y * GamePlayView.FIELD_SIZE;
    	if(pointer.isHorizontal())
    	{
    		graphicsContext.save();
    		rotate(graphicsContext,90,x,y);
    		graphicsContext.drawImage(pointer.draw(),x,y,pointer.draw().getWidth()*GamePlayView.SCALEFACTOR,pointer.draw().getHeight()*GamePlayView.SCALEFACTOR);
    		graphicsContext.restore();
    	}
    	else
    		graphicsContext.drawImage(pointer.draw(),x,y);
    }
    
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
