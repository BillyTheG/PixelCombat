package pixelCombat.view.gamephases;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import pixelCombat.gamephase.Help;
import pixelCombat.view.Renderer;

public class HelpView extends Renderer 
{

	public 		Help		help;
	
	
	protected 	Image 		HELP1 		= GamePlayView.loadImage("/gamePhase/backgrounds/IMG_MENU_Help_Player1.png");	
	protected 	Image 		HELP2 		= GamePlayView.loadImage("/gamePhase/backgrounds/IMG_MENU_Help_Player2.png");	
	protected 	Image 		HELP3 		= GamePlayView.loadImage("/gamePhase/backgrounds/IMG_MENU_Help_Combos.png");	
	protected   List<Image> helpPages 	= new ArrayList<>();
	
	public HelpView(Help help, Canvas canvas) 
    {
    	super(canvas);
		this.help			= help;
		this.helpPages.add(HELP1);
		this.helpPages.add(HELP2);
		this.helpPages.add(HELP3);
    }

    public void render(long delta) 
    {

    	
    	// render background
    	if (MENU_BACKGROUND != null) 
    		graphicsContext.drawImage(MENU_BACKGROUND,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	Image currentHelpPage = helpPages.get(help.getCurrentPage());
    	if (currentHelpPage 		!= null) 
    		graphicsContext.drawImage(currentHelpPage,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	

    }

}
