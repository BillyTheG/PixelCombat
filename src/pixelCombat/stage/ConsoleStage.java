package pixelCombat.stage;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pixelCombat.view.gamephases.GamePlayView;


/**
 * Representation of the Console Stage
 * 
 * @author BillyG
  * @version 1.1
 */

@SuppressWarnings("unused")
public class ConsoleStage extends Stage {

	private Pane rootGroup;
	public MyScene rootScene;

	/**
	 * Creates a game
	 */
	public ConsoleStage(Pane group) {
		super();
		this.rootGroup = group;
		setTitle("Pixel Combat Console");
		setHeight(GamePlayView.SCREEN_HEIGHT + 23);
		setWidth(GamePlayView.SCREEN_WIDTH + 1);		
		rootScene = new MyScene(group,"Console");
		setScene(rootScene);
		show();
	}




}