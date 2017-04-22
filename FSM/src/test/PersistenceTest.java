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
			Event eventA = new EventA(new Printer("Event A Occurred"));
			Event eventB = new EventB(new Printer("Event B Occurred"));
			Event eventC = new EventC(new Printer("Event C Occurred"));
			
			FSM machine =new MyFSM(new FileInputStream("machine.obj"));
			
			//The machine must re-listen to the new events because they are not the same objects as before.
			machine.listenToEvent(eventA);
			machine.listenToEvent(eventB);
			machine.listenToEvent(eventC);
			
			//this event completes seq3 from the last test
			eventB.doAction();
			
			//another seq3 event
			eventA.doAction();
			eventC.doAction();
			eventA.doAction();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
