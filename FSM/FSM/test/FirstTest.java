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
 * Make all states singletons
 */

public class FirstTest {

	public static void main(String[] args) {
		
		//creating the machine's states, runs injected task when reaching this state
		State startState = new StartState(null);
		State stateA = new StateA(null);
		State stateB = new StateB(null);
		State stateC = new StateC(null);
		
		/* add transitions to each state
		 	e.g. event with the name "EventA" occurred, then go to stateA
		 */
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
		
		//define a sequence - seq1
		Sequence seq1 = new MySequence("seq1",new RegularSeqState("Seq1",new Printer("Seq1")));
		seq1.addToSequence("EventA");
		seq1.addToSequence("EventA");
		seq1.addToSequence("EventB");
		seq1.addToSequence("EventC");
		seq1.addToSequence("EventA");
		
		//define a sequence - seq2
		Sequence seq2 = new MySequence("seq2",new RegularSeqState("Seq2", new Printer("Seq2")));
		seq2.addToSequence("EventA");
		seq2.addToSequence("EventB");
		for(int i = 0;i<1000;i++)
			seq2.addToSequence("EventC");
		seq2.addToSequence("EventA");
		
		//define a sequence - seq3
		Sequence seq3 = new MySequence("seq3",new SpecialSeqState("Seq3"));
		seq3.addToSequence("EventA");
		seq3.addToSequence("EventC");
		seq3.addToSequence("*");
	
		//tell the machine to monitor these sequences
		machine.addSequence(seq1);
		machine.addSequence(seq2);
		machine.addSequence(seq3);
		
		//add configured states to machine
		machine.addState("StateA", stateA);
		machine.addState("StateB", stateB);
		machine.addState("StateC", stateC);
		
		//create events
		Event eventA = new EventA(null);
		Event eventB = new EventB(null);
		Event eventC = new EventC(null);
		
		//configure which events the machine will listen to
		machine.ListenToEvent(eventA);
		machine.ListenToEvent(eventC);
		machine.ListenToEvent(eventB);
		/*
		 * If Event.doAction() is overridden it must notifyObservers(),
		 *  but you should inject a Runnable instead
		 */
		
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
				
		//third sequence
		eventA.doAction();
		eventC.doAction();
		
		
		try {
			machine.closeAndPersist(new FileOutputStream("machine.obj"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
