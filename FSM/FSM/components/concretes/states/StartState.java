package components.concretes.states;

import components.abstracts.State;

public class StartState extends State {
	
	public StartState(){
		super();
	}
	
	public StartState(Runnable action) {
		super("StateStart",action);
	}

}
