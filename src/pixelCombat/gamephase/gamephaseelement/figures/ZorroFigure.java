package pixelCombat.gamephase.gamephaseelement.figures;

import javafx.scene.canvas.Canvas;
import pixelCombat.Math.Vector2d;
import pixelCombat.enums.FigureState;
import pixelCombat.gamephase.gamephaseelement.GamePhaseFigure;
import pixelCombat.utils.GamePhase;
import pixelCombat.view.FigureAnimation;
import pixelCombat.view.gamephases.GamePlayView;

public class ZorroFigure extends GamePhaseFigure {

	private int blabberings = 3;

	public ZorroFigure(GamePhase gamePhase, Vector2d pos, Canvas canvas) {
		super(gamePhase, pos, canvas);

		this.stand.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Stand_1.png"));
		this.stand.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Stand_2.png"));
		this.stand.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Stand_3.png"));

		this.selected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_1.png"));
		this.selected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_2.png"));
		this.selected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_3.png"));
		this.selected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_4.png"));
		this.selected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_5.png"));
		this.selected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_6.png"));
		this.selected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_7.png"));
		this.selected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_8.png"));
		this.selected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_9.png"));

		this.deselected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_4.png"));
		this.deselected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_3.png"));
		this.deselected.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Intro_2.png"));

		this.victory.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Victory_1.png"));
		this.victory.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Victory_2.png"));
		this.victory.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Victory_3.png"));
		this.victory.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Victory_4.png"));
		this.victory.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Victory_5.png"));
		this.victory.add(GamePlayView.loadImage("/images/zorro/IMG_Zorro_Victory_6.png"));
		
		
		this.stand_times.add(350f);
		this.stand_times.add(350f);
		this.stand_times.add(350f);

		this.selected_times.add(150f);
		this.selected_times.add(250f);
		this.selected_times.add(150f);
		this.selected_times.add(250f);
		this.selected_times.add(280f);
		this.selected_times.add(150f);
		this.selected_times.add(250f);
		this.selected_times.add(150f);
		this.selected_times.add(250f);

		this.deselected_times.add(350f);
		this.deselected_times.add(450f);
		this.deselected_times.add(750f);
		
		this.victory_times.add(350f);
		this.victory_times.add(450f);
		this.victory_times.add(750f);
		this.victory_times.add(350f);
		this.victory_times.add(450f);
		this.victory_times.add(750f);

		this.selectedSound = "/audio/Zorro_selected_sound.wav";
		this.deselectedSound = "/audio/Zorro_deselected_sound.wav";
		this.victorySound = "/audio/Zorro_Victory.wav";

		
		this.animator = new FigureAnimation(stand, stand_times, false, this);
	}

	@Override
	public void updateDeselected() {

		switch (animator.getCurrFrameIndex()) {
		case 0:
			if (this.switcher) {
				sound(deselectedSound);
				switcher = false;
			}
			break;
		case 1:
			if (!this.switcher) {
				sound("/audio/Zorro_Sword_ReturnToSheath.wav");
				switcher = true;
			}
			break;
		default:
			break;

		}

	}

	@Override
	public void updateSelected() {
		switch (animator.getCurrFrameIndex()) {
		case 3:
			if (this.switcher) {
				sound("/audio/Zorro_Sword_OutOfSheath.wav");
				switcher = false;
			}
			break;
		case 7:
			if (!this.switcher) {
				sound(selectedSound);
				switcher = true;
			}
			break;
		case 8:
			if (this.switcher && animator.isAlmostFinished(8) && blabberings > 0) {
				blabberings -= 1;
				animator.resetToIndex(7);
			}
			break;
		default:
			break;

		}

	}

	@Override
	public void updateVictory() {
		switch (animator.getCurrFrameIndex()) {
		case 0:
			if (this.switcher) {
				sound(victorySound);
				switcher = false;
			}
			break;

		}
	}

	@Override
	public void resetStats() {
		this.switcher = true;
		this.blabberings = 3;

	}

	@Override
	public void changeToVictoryAnim() {	
		//	Character.sound(deselectedSound);
		resetStats();
		setState(FigureState.VICTORY);	
		getAnimator().setup(victory, victory_times, false,4);

	}
	
}
