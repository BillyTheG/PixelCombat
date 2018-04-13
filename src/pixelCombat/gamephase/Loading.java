package pixelCombat.gamephase;

import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import pixelCombat.Math.Vector2d;
import pixelCombat.controller.LoadingController;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.gamephase.gamephaseelement.LoadingButton;
import pixelCombat.model.PXMapHandler;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.utils.GamePhase;
import pixelCombat.utils.GamePhaseVisitor;
import pixelCombat.view.gamephases.LoadingView;

public class Loading extends GamePhase {

	private LoadingView loadingView;
	private LoadingController loadingController;
	private LoadingButton	loadingButton;
	private volatile int loading;
	private boolean atBegin;
	public  int		selectedFunFact = 0;
	public  Random  random = new Random();
	private float shortWaitTime;
	private float waitTime  = 1.25f;
	
	// all figures

	public Loading(GameEngine engine, Canvas canvas, Console console) {
		super(engine, console);
		this.loadingView 					= new LoadingView(this, canvas);
		this.loadingController 				= new LoadingController(engine, loadingView, this);
		this.render 						= loadingView;
		this.controller 					= loadingController;
		this.loadingButton					= new LoadingButton(this,new Vector2d(),true);
		this.loadingButton.getPos().x		= PXMapHandler.X_FIELDS*	1f/2f;				
		this.loadingButton.getPos().y		= PXMapHandler.Y_FIELDS* 	6f/7f;
		this.elements.add(loadingButton);

	}

	public void init(boolean atBegin) {
		this.setLoading(0);
		this.selectedFunFact = random.nextInt(20)+1;
		loadingView.setFunFact(selectedFunFact);
		this.atBegin = atBegin;
	}

	@Override
	public void update(long deltaTime) {
		float delta = ((float) deltaTime) / 1000000000.0f;

		for (GamePhaseElement el : this.elements)
			el.update(delta);

		if (getLoading() >= 100 ) {
			
			shortWaitTime += delta;
			
			if(shortWaitTime >= waitTime)
			{
				setLoading(0);
				shortWaitTime = 0;
				accept(engine.getVisitor(), atBegin);
			}
			
		}
	}

	@Override
	public void handleCommand(KeyCode kc, boolean hold) {
	
	}


	@Override
	public void accept(GamePhaseVisitor visitor, boolean isAtBegin) {
		
		if(isAtBegin)
			engine.setGamePhase(visitor.exit(this));
		else
			engine.setGamePhase(visitor.visit(this));
		
	}

	@Override
	public void reset() {
		this.setLoading(0);
		
	}

	public synchronized void setLoading(int loading){
		this.loading= loading;
//		System.out.println("Loading:= " + this.loading);
	}

	public int getLoading() {
		return loading;
	}
	

	

}
