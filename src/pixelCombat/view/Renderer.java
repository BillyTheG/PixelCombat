package pixelCombat.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.gamephases.GamePlayView;

/**
 * Renderer
 * 
 * @author BillyG
 *@version 0.1
 */
public abstract class Renderer {

	public static final int FIELD_SIZE = 50;
    public static final int HALF_FIELD_SIZE = FIELD_SIZE / 2;
    public static final int SCREEN_WIDTH = PXMapHandler.X_FIELDS * FIELD_SIZE;
    public static final int SCREEN_HEIGHT = PXMapHandler.Y_FIELDS * FIELD_SIZE;
    protected Image MENU_BACKGROUND = GamePlayView.loadImage("/gamePhase/backgrounds/background_1.png");
    public Canvas canvas;
    public GraphicsContext graphicsContext;

    
    public Renderer(Canvas canvas)
    {
    	this.canvas				= canvas;
    	this.canvas.setHeight(GamePlayView.SCREEN_HEIGHT);
    	this.canvas.setWidth(GamePlayView.SCREEN_WIDTH);
		this.graphicsContext 	= this.canvas.getGraphicsContext2D();
    }
	
	
	public abstract void render(long delta);
}
