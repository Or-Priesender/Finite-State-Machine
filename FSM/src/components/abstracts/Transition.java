package components.abstracts;

import java.io.Serializable;

//This class describes which state to move to given the event name
public class Transition implements Serializable{
	
	private String eventName; 
	private State nextHop;
	
	public Transition(){
		eventName="N/A";
		nextHop=null;
	}
	public Transition(String eventName,State nextHop) {
		this.eventName = eventName;
		this.nextHop = nextHop;	
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public State getNextHop() {
		return nextHop;
	}

	public void setNextHop(State nextHop) {
		this.nextHop = nextHop;
	}

	
	
}
