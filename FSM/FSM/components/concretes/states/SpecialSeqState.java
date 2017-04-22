package components.concretes.states;

import components.abstracts.State;

public class SpecialSeqState extends State{
	
	public SpecialSeqState(){
		super();
	}
	
	public SpecialSeqState(String name) {
		super(name,null);
		
	}
	
	//In this special sequence we want to perform a different action then the regular states.
	//We can do so by overriding doAction function.
	@Override
	public void doAction() {
		System.out.println("Seq3 : " + getTriggerEvent());
		
	}
	
	
}
