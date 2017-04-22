package components.abstracts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class State implements Serializable{
	
	protected String name;
	protected Map<String,Transition> transitions;
	protected Runnable action; 
	protected String triggerEvent;
	
	public State(){
		this.name = "N/A";
		
	}
	
	public State(String name,Runnable action){
		this.name = name;
		this.action = action;
		transitions = new HashMap<String,Transition>();
	}
	
	
	public String getTriggerEvent() {
		return triggerEvent;
	}


	public void setTriggerEvent(String triggeredBy) {
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
