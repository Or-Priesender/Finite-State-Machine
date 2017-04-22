package components.concretes.events;

import components.abstracts.Event;

public class EventB extends Event {

	public EventB(){
		super("EventB",null);
	}
	
	public EventB( Runnable action) {
		super("EventB", action);
		
	}
	
	

}
