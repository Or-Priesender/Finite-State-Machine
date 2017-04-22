package components.abstracts;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import components.concretes.FSMObjectLoader;
import components.concretes.FSMObjectSaver;


public abstract class FSM implements Observer,Serializable{
	
	protected String machineName;
	protected State startState;
	protected State currentState;
	protected Map<String,State> states;
	protected List<String> eventHistory;
	protected List<Sequence> sequences;
	protected List<Event> listenedEvents;
	
	public FSM(){
		machineName = "Unspecified machine";
	}
	
	public FSM(String name,State startState){
		this.machineName = name;
		this.startState = startState;
		states = new HashMap<String,State>();
		states.put(startState.getName(), startState);
		sequences = new ArrayList<Sequence>();
		eventHistory = new ArrayList<String>();
		listenedEvents = new ArrayList<Event>();
		currentState = startState;
		currentState.doAction();
		
	}
	
	public FSM(InputStream in){
		FSMLoader loader = new FSMObjectLoader();
		FSM machine = loader.loadFSM(in);
		if(machine != null)
			setFSM(machine);
		else {
			System.out.println("Failed to load machine");
			
		}
	}
	
	public void closeAndPersist(OutputStream out){
		FSMSaver saver = new FSMObjectSaver();
		saver.saveFSM(this, out);
		
	}
	public void setFSM(FSM machine){
		
		setState(machine.getCurrentState());
		setStates(machine.getStates());
		setSequences(machine.getSequences());
		setStartState(machine.getStartState());
		setEventHistory(machine.getEventHistory());
		listenedEvents = new ArrayList<Event>();

	}
	public void addEventToHistory(String eventName){
		if(eventHistory!=null)
			eventHistory.add(eventName);
	}
		
	public void setEventHistory(List<String> eventHistory) {
		this.eventHistory = eventHistory;
	}

	public List<String> getEventHistory() {
		return eventHistory;
	}

	public void addSequence(Sequence seq){
		if(sequences!=null)
			sequences.add(seq);
	}
	
	public List<Event> getListenedEvents() {
		return listenedEvents;
	}

	public void setListenedEvents(List<Event> listenedEvents) {
		this.listenedEvents = listenedEvents;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public State getStartState() {
		return startState;
	}

	public void setStartState(State startState) {
		this.startState = startState;
	}

	public State getCurrentState() {
		return currentState;
	}
	
	public void setState(State current) {
		this.currentState = current;
	}

	public Map<String, State> getStates() {
		return states;
	}

	public void setStates(Map<String, State> states) {
		this.states = states;
	}

	public List<Sequence> getSequences() {
		return sequences;
	}

	public void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}
	
	public State getState(){
		return currentState;
	}
	
	public void addState(String name,State state){
		if(states!=null){
			states.put(name, state);
		}
	}
	//Moves the machine to the state specified, and adds this state to the states map if needed.
	public void setCurrentState(State state){
		if(state == null)
			return;
		
		if(!currentState.getName().equals(state.getName())){
			currentState = state;
		}
		
		if(!states.containsKey(state.getName())){
			states.put(state.getName(),state);
			currentState = state;
		}
	}
	/*This method occurs every time a listened event occurs.
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		String eventName = (String) arg;
		
		//add the trigger event to history
		addEventToHistory(eventName);
		
		 //check if this event completes a sequence the machine knows, if so go to the specified state.
		 //if the specified state is null the machine will remain in the current state.	
		State temp = currentState;
		for(Sequence s : sequences){
			if(s.hasAccurred(eventHistory)){
				setCurrentState(s.getStateAfterSequence());
				currentState.setTriggerEvent(eventName);
				currentState.doAction();
				
			}
		//go back to the previous state to continue as normal
		setCurrentState(temp);
		
		//getting the transition according to the event that called this method
		Transition t = currentState.getTransitions().get(eventName);
		if(t!=null){
			State newState = t.getNextHop();
			setCurrentState(newState);
			currentState.setTriggerEvent(eventName);
			currentState.doAction();
			
			}
		//if there is no transition for the specified event then remain in the current state
		else currentState.doAction();
			
		
		}
	}
	
	public void ListenToEvent(Event e){
		listenedEvents.add(e);
		e.addObserver(this);
	}
	
	
	

}
