package components.abstracts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

//This class describes a state the machine can be in
public abstract class State implements Serializable{
	//the state's name
	protected String name;
	//A transition map with event name as a key
	protected Map<String,Transition> transitions;
	//this action happens when the machine reaches this state
	protected Runnable action; 
	//this event is the event that brought the machine to this state
	protected Event triggerEvent;
	
	public State(){
		this.name = "N/A";
		action = null;
		
	}
	
	public State(String name){
		this.name = name;
		transitions = new HashMap<String,Transition>();
		action = null;
	}
	
	
	public Event getTriggerEvent() {
		return triggerEvent;
	}


	public void setTriggerEvent(Event triggeredBy) {
		this.triggerEvent = triggeredBy;
	}


	public void addTransition(Transition t){
		if(transitions!=null)
			transitions.put(t.getEventName(), t);
	}
	
	public void setAction(Runnable action){
		this.action = action;
	}
	
	public void doAction(){
		if(action!=null)	
			action.run();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(Map<String, Transition> transitions) {
		this.transitions = transitions;
	}

	public Runnable getAction() {
		return action;
	}
	
	
	

}
