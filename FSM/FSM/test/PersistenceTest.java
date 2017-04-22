package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import components.abstracts.*;
import components.concretes.*;
import components.concretes.events.EventA;
import components.concretes.events.EventB;
import components.concretes.events.EventC;

/*
 * The persistence test loads the machine (the machine we created in "FirstTest") from a file 
 * and completes sequence 3 to prove the machine is persistent.
 */
public class PersistenceTest {

	public static void main(String[] args) {
		
		try {
			Event eventA = new EventA();
			Event eventB = new EventB();
			Event eventC = new EventC();
			
			FSM machine =new MyFSM(new FileInputStream("machine.obj"));
			
			//The machine must re-listen to the new events because they are not the same objects as before.
			machine.ListenToEvent(eventA);
			machine.ListenToEvent(eventB);
			machine.ListenToEvent(eventC);
			
			//this event completes seq3 from the last test
			eventB.doAction();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
