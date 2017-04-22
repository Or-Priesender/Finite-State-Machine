package components.concretes.states;

import components.abstracts.State;

//This is an example of a special sequence that was implemented in a different way,
//without injecting a runnable.
public class SpecialSeqState extends State{
	
	public SpecialSeqState(){
		super();
	}
	
	public SpecialSeqState(String name) {
		super(name);
		
	}
	
	//In this special sequence we want to perform a different action then the regular states.
	//We can do so by overriding doAction function.
	@Override
	public void doAction() {
		System.out.println("Seq3 : " + getTriggerEvent().getEventName());
		
	}
	
	
}
