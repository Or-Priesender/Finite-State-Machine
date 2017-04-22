package components.concretes.states;

import components.abstracts.State;

public class StateC extends State {
	
	public StateC(){
		super();
	}
	
	public StateC(Runnable action) {
		super("StateC",action);
	}

}
