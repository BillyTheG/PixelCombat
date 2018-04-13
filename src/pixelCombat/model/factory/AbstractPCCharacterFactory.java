package pixelCombat.model.factory;

import java.util.HashMap;
import java.util.Map;

import pixelCombat.model.Character;
import pixelCombat.projectiles.ProjectileManager;

public class AbstractPCCharacterFactory {
	
	private Map<String, PCCharacterFactoryInterface<?>> factories = new HashMap<>();
	public ProjectileManager projectile_manager;
	
	public AbstractPCCharacterFactory(ProjectileManager projectile_manager) {
		this.projectile_manager = projectile_manager;
	}

	public <T extends Character>void registerFactory(String type, PCCharacterFactoryInterface<T> factory){
		factories.put(type, factory);
	}
	
	public Character createCharacter(Map<String, Object> stats){
		System.out.println("creating type " + stats.get("type"));
		String type = (String)stats.get("type");
		if(type == null){
			throw new RuntimeException("No type for character defined");
		}
		PCCharacterFactoryInterface<?> f = factories.get(type);
		if(f== null){
			throw new RuntimeException("No factory for type "+ type +" defined");
		}
		return f.createCharacter(stats,projectile_manager);
	}
}
