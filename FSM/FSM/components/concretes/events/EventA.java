package components.concretes.events;

import components.abstracts.Event;

public class EventA extends Event {

	public EventA(){
		super("EventA",null);
	}
	
	public EventA(Runnable action) {
		super("EventA", action);
		
	}

}
