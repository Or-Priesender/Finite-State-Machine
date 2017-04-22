package components.concretes.states;

import components.abstracts.State;

public class StateA extends State {
	
	public StateA(){
		super();
	}
	public StateA(Runnable action) {
		super("StateA",action);
		
	}
	
	

}
