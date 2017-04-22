package components.abstracts;

import java.io.Serializable;
import java.util.Observable;

import javax.sound.midi.Synthesizer;

//This class describes a general event
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
	
	//When the event happens, the machine is notified
	public void doAction(){
		if(action != null)
			action.run();
		setChanged();
		notifyObservers();
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

}
