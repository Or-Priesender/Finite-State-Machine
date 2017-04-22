package components.concretes.states;

import components.abstracts.State;
import components.concretes.Printer;

//This is an example state for tests 1 and 2, where we need to print the name of the sequence,
//and we do this by injecting a runnable.
public class RegularSeqState extends State{
	
	public RegularSeqState(){
		super();
	}
	public RegularSeqState(String name) {
		super(name);
	}
	
}
