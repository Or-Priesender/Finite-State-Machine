package components.concretes.states;

import components.abstracts.State;

public class StateB extends State {
	
	
	public StateB(){
		super();
	}
	
	public StateB(Runnable action) {
		super("StateB",action);
	}

}
