package pixelCombat.model.chars.natsu.attacks;

import pixelCombat.artworks.NatsuKenKakuArtWork;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Natsu;

public class NatsuKaryuNoTekken extends Attack {
	private Natsu natsu;
	private NatsuKenKakuArtWork tekkenArtWork = new NatsuKenKakuArtWork();

	public NatsuKaryuNoTekken(Natsu natsu, int id) {
		super(natsu, id);
		this.natsu = natsu;
		setRequiredEnergy(20.0F);
	}

	public void process() {
		switch (getUser().picManager.getCurrFrameIndex()) {
		case 0:
			if (getUser().isSwitcher()) {
				tekkenArtWork.reset();
				natsu.releasedArtWorks.add(tekkenArtWork);
				this.natsu.sound("/audio/natsu_karyuu.wav");
				this.natsu.sound("/audio/natsu_fire.wav");
				getUser().setSwitcher(false);
			}
			break;
		case 3:
			if (!getUser().isSwitcher()) {
				this.natsu.statusLogic.setAHitDelay(false);
				this.natsu.sound("/audio/natsu_fire.wav");
				getUser().setSwitcher(true);
			}
			break;
		case 5:
			if (getUser().isSwitcher()) {
				this.natsu.statusLogic.setAHitDelay(false);
				getUser().setSwitcher(false);
			}
			break;
		case 7:
			if (!getUser().isSwitcher()) {
				this.natsu.statusLogic.setAHitDelay(false);
				getUser().setSwitcher(true);
			}
			break;
		case 9:
			if (getUser().isSwitcher()) {
				this.natsu.statusLogic.setAHitDelay(false);
				getUser().setSwitcher(false);
			}
			break;
		case 11:
			if (!getUser().isSwitcher()) {
				this.natsu.statusLogic.setAHitDelay(false);
				getUser().setSwitcher(true);
			}
			break;
		}
		if ((this.natsu.picManager.getCurrFrameIndex() < 11) && (this.natsu.picManager.getCurrFrameIndex() >= 3)) {
			this.natsu.physics.VX = (this.natsu.getDir() * 35.0F);
		}
	}

	public void checkContent() {
		this.natsu.enemy.damage(this.natsu.getStrength() * 5.0F);
		this.natsu.sound("/audio/meleehit2.wav");
		this.natsu.sound(this.natsu.enemy.cry());

		this.natsu.makeFireSpark(this.natsu.enemy);
		if (!this.natsu.enemy.statusLogic.isKnockback()) {
			this.natsu.enemy.statusLogic.setActionStates(ActionStates.STAND);
			this.natsu.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
			this.natsu.enemy.setKnockbackHeight(-7.0F);
			this.natsu.enemy.setKnockbackRange(10.0F);
		} else {
			this.natsu.comboTouch(this.natsu.enemy, -4.0F, 10.0F);
		}
		this.natsu.statusLogic.setAHitDelay(true);
	}

	public void checkFinished() {
	}

	public boolean isAttacking() {
		return this.natsu.attackLogic.isSpecialAttacking2();
	}

	public void resetStats() {
	}
}
