package test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import components.abstracts.*;
import components.concretes.*;
import components.concretes.events.EventA;
import components.concretes.events.EventB;
import components.concretes.events.EventC;
import components.concretes.states.RegularSeqState;
import components.concretes.states.SpecialSeqState;
import components.concretes.states.StartState;
import components.concretes.states.StateA;
import components.concretes.states.StateB;
import components.concretes.states.StateC;

/*
 * The first test configures the machine from within the code.
 * It tests sequences 1 and 2, and a part of sequence 3.
 * 
 */

public class FirstTest {

	public static void main(String[] args) {
		
		//creating the machine's states
		State startState = new StartState();
		State stateA = new StateA();
		State stateB = new StateB();
		State stateC = new StateC();
		
		// adding transitions to each state
		// e.g. event with the name "EventA" occurred, then go to stateA
		 
		//start state event definition
		startState.addTransition(new Transition("EventA",stateA));
		startState.addTransition(new Transition("EventB",stateB));
		startState.addTransition(new Transition("EventC",stateC));
		//State A's event definition
		stateA.addTransition(new Transition("EventA",stateA));
		stateA.addTransition(new Transition("EventB",stateB));
		stateA.addTransition(new Transition("EventC", stateC));
		//State B's event definition
		stateB.addTransition(new Transition("EventA",stateA));
		stateB.addTransition(new Transition("EventB",stateB));
		stateB.addTransition(new Transition("EventC",stateC));
		//State C's event definition
		stateC.addTransition(new Transition("EventA",stateA));
		stateC.addTransition(new Transition("EventB",stateB));
		stateC.addTransition(new Transition("EventC",stateC));
		//create the machine with configured start state
		FSM machine = new MyFSM("Machine1",startState);
		
		//add configured states to machine
		machine.addState("StateA", stateA);
		machine.addState("StateB", stateB);
		machine.addState("StateC", stateC);
		
		//define sequence 1 and the state to move when it is discovered
		State afterSeq1 = new RegularSeqState("Seq1");
		afterSeq1.setAction(new Printer("Seq1"));
		Sequence seq1 = new MySequence("seq1",afterSeq1);
		seq1.addToSequence("EventA");
		seq1.addToSequence("EventA");
		seq1.addToSequence("EventB");
		seq1.addToSequence("EventC");
		seq1.addToSequence("EventA");
		
		//define sequence 2 and the state to move to when it is discovered
		State afterSeq2 = new RegularSeqState("Seq2");
		afterSeq2.setAction(new Printer("Seq2"));
		Sequence seq2 = new MySequence("seq2",afterSeq2);
		seq2.addToSequence("EventA");
		seq2.addToSequence("EventB");
		for(int i = 0;i<1000;i++)
			seq2.addToSequence("EventC");
		seq2.addToSequence("EventA");
		
		//define sequence 3 and the special state to move to when it is discovered.
		State afterSeq3 = new SpecialSeqState("Seq3");
		Sequence seq3 = new MySequence("seq3",afterSeq3);
		seq3.addToSequence("EventA");
		seq3.addToSequence("EventC");
		seq3.addToSequence("*");
	
		//tell the machine to monitor these sequences
		machine.addSequence(seq1);
		machine.addSequence(seq2);
		machine.addSequence(seq3);
		
		
		
		//create events
		Event eventA = new EventA(new Printer("Event A Occurred"));
		Event eventB = new EventB(new Printer("Event B Occurred"));
		Event eventC = new EventC(new Printer("Event C Occurred"));
		
		//configure which events the machine will listen to
		machine.listenToEvent(eventA);
		machine.listenToEvent(eventC);
		machine.listenToEvent(eventB);
		
		System.out.println("The FSM is configured and waiting for events");
		//If Event.doAction() is overridden it must notify the observers,
		//but it is recommended to inject a Runnable instead
		
		//first sequence
		eventA.doAction();
		eventA.doAction();
		eventB.doAction();
		eventC.doAction();
		eventA.doAction();
		
		//second sequence
		eventA.doAction();
		eventB.doAction();
		for(int i=0;i<1000;i++)
			eventC.doAction();
		eventA.doAction();
				
		//start of the third sequence
		eventA.doAction();
		eventC.doAction();
		
		
		try {
			machine.saveAsObject(new FileOutputStream("machine.obj"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
