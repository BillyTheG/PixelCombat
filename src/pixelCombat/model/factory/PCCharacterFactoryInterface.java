package pixelCombat.model.factory;

import java.util.Map;

import pixelCombat.model.Character;
import pixelCombat.projectiles.ProjectileManager;

public interface PCCharacterFactoryInterface<T extends Character> {
	
	public T createCharacter(Map<String, Object> stats, ProjectileManager projectile_manager);
}
