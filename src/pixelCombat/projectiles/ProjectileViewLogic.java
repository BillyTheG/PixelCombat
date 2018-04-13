package pixelCombat.projectiles;

public class ProjectileViewLogic {

	// Bildsequenzen als Stütze
	public final int CREATION = 0;
	public final int MOVE = 1;
	public final int EXPLOSION = 2;
	public final int DEAD = 3;
	public final int SPECIALEFFECT1 = 4;
	public final int SPECIALEFFECT2 = 5;
    
	private Projectile projectile;

	public ProjectileViewLogic(Projectile projectile) {
		this.projectile = projectile;
	}

	public void update() {

		// Character ist aktiv
		if (projectile.statusLogic.isAlive()) {
			
		switch(projectile.statusLogic.getActionStates())
		{
		case CREATION:
			projectile.picManager.change(CREATION);
			break;
		case MOVE:
			projectile.picManager.change(MOVE);
			break;
		case EXPLOSION:
			projectile.picManager.change(EXPLOSION);
			break;
		case SPECIALEFFECT1:
			projectile.picManager.change(SPECIALEFFECT1);
			break;
		case SPECIALEFFECT2:
			projectile.picManager.change(SPECIALEFFECT2);
			break;
		default:
			break;
		}
					
	}
}
}