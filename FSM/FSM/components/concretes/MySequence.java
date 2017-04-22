package components.concretes;

import components.abstracts.Sequence;
import components.abstracts.State;

public class MySequence extends Sequence {

	public MySequence(){
		super();
	}
	public MySequence(String name,State stateIfDiscovered) {
		super(name,stateIfDiscovered);
	}
	


}
