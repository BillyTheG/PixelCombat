package pixelCombat.eventhandling;

import pixelCombat.enums.GameEvent;

public interface EventListener 
{
	public void listen(EventHandler eventHandler);
	public void setEvent(EventHandler eventHandler, GameEvent event);
}
