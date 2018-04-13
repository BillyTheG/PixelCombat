package pixelCombat.view;

import javafx.scene.canvas.GraphicsContext;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.gamephases.GamePlayView;

public class ComboNumber extends InterfaceElement {

	private float width;
	private Vector2d target;
	
	public ComboNumber(GraphicsContext g, String imagePath) {
		super(g,imagePath);
		initSpec();
	}

	private void initSpec() {
		width = (float) (image.getWidth()/GamePlayView.FIELD_SIZE)*0.5f;
	}
	/**
	 * draws the Combo Numbers on both of the inner left/right sides from the game panel.
	 * Player1's combo numbers are stated left and vice versa.
	 * 
	 * @param player1 , the player
	 * @param offset , the offset for x coord., succeeding numbers must be drawn with a shift to right
	 */
	public void draw(boolean player1, float offset) {

		if(player1){
			if(pos != null) pos.x = 1f;
			pos = new Vector2d(1f+offset,5f);
			
			if(target != null) target.x = -2f;
			target = new Vector2d(-2f,5f);			
		}
		else{
			if(pos != null) pos.x = 1f;
			pos = new Vector2d(22f-offset,5f);
			
			if(target != null) target.x = 1f;
			target = new Vector2d(26f,5f);			
		}
			
		super.draw();
	}

	public float getWidth() {
		return width;
	}

	
}
