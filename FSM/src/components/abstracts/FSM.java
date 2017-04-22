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
	
	private static final long serialVersionUID = 1L;
	
	protected String machineName;
	protected State startState;
	protected State currentState;
	//A map of states with state name as a key
	protected Map<String,State> states;
	//A list of the events that occurred since the machine began working
	protected List<String> eventHistory;
	//A list of monitored event sequences
	protected List<Sequence> sequences;
	//A list of events that the machine listens to
	protected List<Event> listenedEvents;
	//Default constructor
	public FSM(){
		machineName = "Unspecified machine";
	}
	//A constructor to create a new machine
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
	//A constructor to load a machine from an input stream
	public FSM(InputStream in){
		FSMLoader loader = new FSMObjectLoader();
		FSM machine = loader.loadFSM(in);
		if(machine != null)
			setFSM(machine);
		else {
			System.out.println("Failed to load machine");
			
		}
	}
	//Saves the machine to an object file 
	public void saveAsObject(OutputStream out){
		FSMSaver saver = new FSMObjectSaver();
		saver.saveFSM(this, out);
		
	}
	//Performs a deep copy
	public void setFSM(FSM machine){
		
		setState(machine.getCurrentState());
		setStates(machine.getStates());
		setSequences(machine.getSequences());
		setStartState(machine.getStartState());
		setEventHistory(machine.getEventHistory());
		listenedEvents = new ArrayList<Event>();

	}
	//Adds an event to the history list
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
		
		else if(!currentState.getName().equals(state.getName())){
			currentState = state;
		}
		
		else if(!states.containsKey(state.getName())){
			states.put(state.getName(),state);
			currentState = state;
		}
	}
	//Activates when a listened event occurred
	@Override
	public void update(Observable o, Object arg) {
		Event event = (Event) o;
		//add the trigger event to history
		addEventToHistory(event.getEventName());
		
		 //check if this event completes a sequence the machine knows, if so go to the specified state.
		 //if the specified state is null the machine will remain in the current state.	
		State temp = currentState;
		for(Sequence s : sequences){
			if(s.hasAccurred(eventHistory)){
				setCurrentState(s.getStateAfterSequence());
				currentState.setTriggerEvent(event);
				currentState.doAction();
				
			}
		//go back to the previous state to continue as normal
		setCurrentState(temp);
		
		//getting the transition according to the event that called this method
		Transition t = currentState.getTransitions().get(event.getEventName());
		if(t!=null){
			State newState = t.getNextHop();
			setCurrentState(newState);
			currentState.setTriggerEvent(event);
			currentState.doAction();
			
			}
		//if there is no transition for the specified event then remain in the current state
		else currentState.doAction();
			
		
		}
	}
	
	public void listenToEvent(Event e){
		listenedEvents.add(e);
		e.addObserver(this);
	}
	
	
	

}
