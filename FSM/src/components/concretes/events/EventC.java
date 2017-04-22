package components.concretes.events;

import components.abstracts.Event;

public class EventC extends Event {

	public EventC(){
		super("EventC",null);
	}
	
	public EventC(Runnable action) {
		super("EventC", action);
		
	}

}
