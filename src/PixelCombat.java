import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pixelCombat.controller.InputController;
import pixelCombat.stage.ConsoleStage;
import pixelCombat.stage.GameStage;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.utils.SceneSizeChangeListener;
import pixelCombat.utils.WordWrapConsole;

/**
 * 
 * The Main Class of this Game. It includes the initial process for
 * beginning the game and starting the gameloop.
 * 
 * 
 * @author BillyG
 *
 */
public class PixelCombat extends Application {

	private GameEngine engine;
	private InputController inputController;
	private long deltaTime = 0;
	private long previousTime = 0;
	private GameStage gameStage;
	private Pane mainViewGroup;
	
	private int 	FPS = 120;
	private float 	frameBuffer = 0f;	
	private float 	FPS_Duration = 1f/(float)FPS;
	
	
	private ConsoleStage consoleStage;
	private Console console;
	private Scene 	consoleScene;
	private Pane 	consoleRoot;
	private Canvas 	consoleCanvas;

	public static void main(String[] args) {
		// Redraw canvas when size changes.		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) 
	{
		console 		= new WordWrapConsole();
		consoleRoot 	= new Pane();			
		consoleCanvas 	= new Canvas();
		consoleRoot.getChildren().add(consoleCanvas);			
		consoleRoot.getChildren().add(console);	
		consoleStage 	= new ConsoleStage(consoleRoot);
		letterbox(consoleStage.getScene(),consoleRoot);
		consoleStage.setResizable(false);
		
		
		// show game
		gameStage = new GameStage();		
		letterbox(gameStage.rootScene, gameStage.getGroup()); //scale
		gameStage.setResizable(false);

		gameStage.setFullScreen(true);
		mainViewGroup = gameStage.getGroup();
		engine = new GameEngine(mainViewGroup, gameStage,console,consoleStage);
		inputController = new InputController(engine);
		gameStage.addEventHandler(InputEvent.ANY, inputController);
		mainViewGroup.setCache(true);
		mainViewGroup.setCacheShape(true);
		mainViewGroup.setCacheHint(CacheHint.SPEED);
		
		System.gc();
		gameLoop();
		
	}

	private void gameLoop() {

		new AnimationTimer() {
			@Override
			public void handle(long currentTime) {

				deltaTime = currentTime - previousTime;
				previousTime = currentTime;

				if (!gameStage.isShowing())
				{
					engine.close();
					consoleStage.close();
				}
				
				
				//buffer time must be greater than the 1-FPS time
				frameBuffer+=(float)deltaTime/1000000000f;
				
				
				// perform step when buffer time surpasses the 1-FPS time
				if(frameBuffer>= FPS_Duration)
				{
					float calibrated_deltaTime = 2*FPS_Duration*1000000000f;
					inputController.update((long) calibrated_deltaTime);
					engine.update((long) calibrated_deltaTime);	
					frameBuffer = 0f;
				}
				
			}

		}.start();
	}
	
	


	private void letterbox(final Scene scene, final Pane contentPane) {
		final double initWidth = scene.getWidth();
		final double initHeight = scene.getHeight();
		final double ratio = initWidth / initHeight;

		SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(
				scene, ratio, initHeight, initWidth, contentPane);
		scene.widthProperty().addListener(sizeListener);
		scene.heightProperty().addListener(sizeListener);
	}

	public Scene getConsoleScene() {
		return consoleScene;
	}

	public void setConsoleScene(Scene consoleScene) {
		this.consoleScene = consoleScene;
	}

}
