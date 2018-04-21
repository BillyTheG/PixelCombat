package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.artworks.RuffyEpic;
import pixelCombat.dusts.DashWind;
import pixelCombat.dusts.JetPistoleBigDust;
import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyJetGatling extends Attack {
	private Ruffy ruffy;
	private boolean canSound = true;
	private RuffyEpic luffyEpic;
	private DashWind dashWind;

	
	public RuffyJetGatling(Ruffy ruffy, int id) {
		super(ruffy, id);
		this.ruffy = ruffy;
		this.luffyEpic = new RuffyEpic();
		setRequiredEnergy(65.0F);
		 this.dashWind = new DashWind(new Vector2d(), true);
	}

	public void process() {
		switch (getUser().picManager.getCurrFrameIndex()) {
		case 0:
			if (checkGear()) {
				return;
			}
			if (getUser().isSwitcher()) {
				ruffy.getEngine().stopMP3();
				ruffy.getEngine().musicMP3("/audio/letsBattle.mp3");
				luffyEpic.reset();
				this.ruffy.releasedArtWorks.add(luffyEpic);
				getUser().setSwitcher(false);
				focus(true);
			}
			
			break;
		case 1:
			
			if (ruffy.picManager.isAlmostFinished(1) && !luffyEpic.dead)
			{
				ruffy.picManager.resetToIndex(0);
				getUser().setSwitcher(false);
				return;
			}
			getUser().setSwitcher(true);
			
			break;
		case 2:
			if (getUser().isSwitcher()) {
				getUser().sound("/audio/Gomu_hard.wav");
				getUser().setSwitcher(false);
			}

			break;
		case 4:
			if (!this.ruffy.isSwitcher()) {
				getUser().sound("/audio/Ruffy_Jet_Cry.wav");
				activateAllDusts();
				this.ruffy.setSwitcher(true);
			}
			break;
		case 5:
			if (this.ruffy.isSwitcher()) {
				focus(false);
				makeJetGatlingDust();
				makeJetGatlingDust();
				makeJetGatlingDust();
				if (this.canSound) {
					this.ruffy.getJetGatlingArtWork().reset();
					this.ruffy.releasedArtWorks.add(this.ruffy.getJetGatlingArtWork());

					getUser().sound("/audio/Ruffy_Jet_Gatling_Cry.wav");
					this.canSound = false;
				}
				this.ruffy.setSwitcher(false);
			}
			break;
		case 7:
			if ((!this.ruffy.isSwitcher()) && (this.ruffy.picManager.isAlmostFinished(7)) && (this.ruffy.gatling > 0)) {
				getUser().sound("/audio/JetGatlingBigDustSound.wav");
				ruffy.picManager.resetToIndex(5);
				this.ruffy.statusLogic.setAHitDelay(false);
				this.ruffy.gatling -= 1;
				this.ruffy.setSwitcher(true);
			}
			break;
		case 9:
			if (!this.ruffy.isSwitcher()) {
				this.ruffy.releasedDusts.remove(this.dashWind);
				this.ruffy.releasedDusts.remove(this.ruffy.getJetBigDustCircle());
				this.ruffy.releasedDusts.remove(this.ruffy.getJetBigDustBackDust());
				getUser().setSwitcher(true);
			}
			break;
		}
		this.ruffy.getUpAndDownBorder().update(this.ruffy.delta);
	}

	private void focus(boolean isFocus) {
		this.ruffy.freeze = isFocus;
		this.ruffy.freeze_loop = isFocus;
		this.ruffy.statusLogic.setFocused(isFocus);
		this.ruffy.borderEffecting = isFocus;
		if(isFocus)this.ruffy.getUpAndDownBorder().reset();
	}

	private void makeJetGatlingDust() {
		JetPistoleBigDust currentDust = (JetPistoleBigDust) this.ruffy.getJetPistole_BigDusts()
				.get(this.ruffy.getJetPistoleBigDust_Id());
		this.ruffy.setJetPistoleBigDust_Id(
				(this.ruffy.getJetPistoleBigDust_Id() + 1) % this.ruffy.getJetPistole_BigDusts().size());
		int randX = this.ruffy.getRandom().nextInt(8);
		int randY = this.ruffy.getRandom().nextInt(4);
		currentDust.reset(
				new Vector2d(this.ruffy.pos.x + this.ruffy.getDir() * (6.5F + randX), this.ruffy.pos.y + 1.5F + randY),
				this.ruffy.statusLogic.isRight());
		this.ruffy.releasedDusts.add(currentDust);
	}

	private boolean checkGear() {
		if (!this.ruffy.isGear2On()) {
			this.ruffy.attackLogic.setAttackStatus(AttackStates.GigantoGatling);
			return true;
		}
		return false;
	}

	private void activateAllDusts() {
		this.ruffy.getJetBigDustCircle().reset(
				new Vector2d(this.ruffy.pos.x + this.ruffy.getDir() * 6.0F, this.ruffy.pos.y - 5.0F),
				this.ruffy.statusLogic.isRight());
		this.ruffy.getJetBigDustBackDust().reset(
				new Vector2d(this.ruffy.pos.x + -this.ruffy.getDir() * 5.75F, this.ruffy.getGroundLevel() - 0.5F),
				this.ruffy.statusLogic.isRight());

		this.dashWind.repositionate(new Vector2d(this.ruffy.pos.x, this.ruffy.pos.y + 1.75F), this.ruffy.statusLogic.isRight());	    
		this.ruffy.releasedDusts.add(this.dashWind);
		this.ruffy.releasedDusts.add(this.ruffy.getJetBigDustCircle());
		this.ruffy.releasedDusts.add(this.ruffy.getJetBigDustBackDust());
	}

	public void checkContent() {
		this.ruffy.enemy.damage(this.ruffy.getStrength() * 1.5F);
		getUser().sound("/audio/meleehit2.wav");
		getUser().sound(this.ruffy.enemy.cry());

		float randY = this.ruffy.getRandom().nextFloat();
		float randX = this.ruffy.getRandom().nextFloat();
		JetPistoleBlueHit currentBlueHit = (JetPistoleBlueHit) this.ruffy.getJetPistole_Blues()
				.get(this.ruffy.getJetPistoleBlue_Id());
		this.ruffy.setJetPistoleBlue_Id(
				(this.ruffy.getJetPistoleBlue_Id() + 1) % this.ruffy.getJetPistole_Blues().size());
		currentBlueHit.dustAnimator.start();
		currentBlueHit.pos.x = (this.ruffy.enemy.pos.x + randX);
		currentBlueHit.pos.y = (this.ruffy.enemy.pos.y + 2.0F + randY);
		currentBlueHit.dead = false;
		this.ruffy.releasedDusts.add(currentBlueHit);
		if (!this.ruffy.shaking) {
			this.ruffy.shaking = true;
		}
		if (!this.ruffy.enemy.statusLogic.isKnockback()) {
			this.ruffy.enemy.setKnockbackHeight(-7.0F);
			this.ruffy.enemy.checkOnAir();
			this.ruffy.enemy.statusLogic.setActionStates(ActionStates.STAND);
			this.ruffy.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
		} else {
			this.ruffy.comboTouch(this.ruffy.enemy, -3.52F, 4.5F);
		}
		this.ruffy.statusLogic.setAHitDelay(true);
	}

	public void checkFinished() {
		resetStats();
		ruffy.getEngine().stopMP3();
		ruffy.getEngine().musicPreviousMP3();
	}

	public boolean isAttacking() {
		return this.ruffy.getAttackLogic().isSpecialAttacking5();
	}

	public void resetStats() {

		this.ruffy.freeze = false;
		this.ruffy.freeze_loop = false;
		this.ruffy.borderEffecting = false;
		this.ruffy.statusLogic.setFocused(false);
		this.ruffy.releasedDusts.remove(this.dashWind);
		this.ruffy.releasedDusts.remove(this.ruffy.getJetBigDustCircle());
		this.ruffy.releasedDusts.remove(this.ruffy.getJetBigDustBackDust());
		this.ruffy.releasedDusts.remove(this.ruffy.getDashGatlingDust());
		this.ruffy.gatling = this.ruffy.getGatling_max();
		this.ruffy.SuperAttackIntro = 10;
		this.ruffy.superAttacking = false;
		this.canSound = true;
	}
}
