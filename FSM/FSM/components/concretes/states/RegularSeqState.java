package components.concretes.states;

import components.abstracts.State;

public class RegularSeqState extends State{
	
	public RegularSeqState(){
		super();
	}
	public RegularSeqState(String name,Runnable action) {
		super(name, action);
	}
	
}
