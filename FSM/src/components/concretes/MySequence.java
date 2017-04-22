package components.concretes;

import components.abstracts.Sequence;
import components.abstracts.State;

//This class was made to test the abstract sequence class
public class MySequence extends Sequence {

	public MySequence(){
		super();
	}
	public MySequence(String name,State stateIfDiscovered) {
		super(name,stateIfDiscovered);
	}
	


}
