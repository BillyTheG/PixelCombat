package pixelCombat.stage;

import javafx.scene.Parent;
import javafx.scene.Scene;
import pixelCombat.view.gamephases.GamePlayView;

/**
 * MyScene
 * 
 * @author BillyG
  * @version 0.1
 */
class MyScene extends Scene {
	private String name;
	
	/**
	 * Constructor of MyScene
	 * 
	 * 
	 * @param group, Group
	 */
	public MyScene(Parent group) {
		super(group);
	}

	public String getName(){
		return name;
	}
	
	@Override
	public boolean equals(Object obj){
		return (this.name == ((MyScene)obj).getName());
	}
	/**
	 * Constructor of MyScene
	 * 
	 * 
	 * @param group, Group
	 * @param name, Name
	 */
	public MyScene(Parent group,String name) {
		super(group, GamePlayView.SCREEN_WIDTH + 1,GamePlayView.SCREEN_HEIGHT + 23);
		this.name = name;
	}

	
	
}
