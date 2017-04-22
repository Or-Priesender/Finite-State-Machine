package components.concretes;

import java.io.InputStream;

import components.abstracts.FSM;
import components.abstracts.State;

//This class was made to test the abstract FSM class
public class MyFSM extends FSM {
	
	public MyFSM(){
		super();
	}
	
	public MyFSM(InputStream in){
		super(in);
	}
	public MyFSM(String name,State startState) {
		super(name,startState);
	}
	
	

}
