package components.abstracts;

import java.io.Serializable;
import java.util.Observable;

public abstract class Event extends Observable implements Serializable{
	
	
	String eventName;
	Runnable action;
	
	public Event(){
		eventName = "N/A";
		action = null;
	}
	
	public Event(String eventName,Runnable action){
		this.eventName = eventName;
		this.action = action;
	}

	public void doAction(){
		if(action != null)
			action.run();
		setChanged();
		notifyObservers(eventName);
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Runnable getAction() {
		return action;
	}

	public void setAction(Runnable action) {
		this.action = action;
	}
	
	@Override
	public boolean equals(Object obj) {
		Event e = (Event) obj;
		return eventName.equals(e.getEventName());
	}
	
	@Override
	public int hashCode() {
		
		return eventName.hashCode();
	}
}
