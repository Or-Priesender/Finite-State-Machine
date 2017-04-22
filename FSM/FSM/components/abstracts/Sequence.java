package components.abstracts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
 * Defining a sequence of events by their distinct name. 
 * The client of this code can define a state to transition to after the sequence is discovered.
 * 
 */
public abstract class Sequence implements Serializable{
	
	protected String seqName;
	protected List<String> sequence;
	protected State stateAfterSequence;
	
	//Constructors
	public Sequence(String name,State afterSeq){
		this.seqName = name;
		this.stateAfterSequence = afterSeq;
		sequence = new ArrayList<String>();
	}
	
	public Sequence(){
		this.seqName= "N/A";
		
	}
	
	public Sequence(String name, List<String> sequence,State afterSeq){
		this.seqName = name;
		this.stateAfterSequence = afterSeq;
		this.sequence = sequence;
	}
	
	//Getters and setters
	public State getStateAfterSequence() {
		return stateAfterSequence;
	}

	public void setStateAfterSequence(State afterSequence) {
		this.stateAfterSequence = afterSequence;
	}
	
	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public List<String> getSequence() {
		return sequence;
	}

	public void setSequence(List<String> sequence) {
		this.sequence = sequence;
	}
	
	//Add an event name to the sequence
	public void addToSequence(String added){
		if(sequence != null){
			sequence.add(added);
		}
	}
	//Check if the sequence contains wild cards
	public boolean containsWildCard(){
		if(sequence!=null)
			return sequence.contains("*");
		else return false;
	}
	
	//A way to implement "Any Event" is to replace events in a temporary list with the wild card string
	private	List<String> replaceEventsWithWildCard(List<String> toReplace,List<String> wildCardList){
		for(int i = 0;i<wildCardList.size();i++){
			if(wildCardList.get(i).equals("*")){
				toReplace.set(i, "*");
			}
		}
			
		return toReplace;
	}
	
	//This method checks if according to eventHistory, the sequence has occurred
	public boolean hasAccurred(List<String> eventHistory){
		List<String> events = new ArrayList<String>(eventHistory);
		
		//There is no need for the full size array.
		int indexToDelete = events.size()-sequence.size();
		if(events.size()<sequence.size())
			return false;
		else{
			for(int i = 0; i < indexToDelete ; i++)
				events.remove(0);
			
			//First check on the shortened array
			if(events.equals(sequence))
				return true;
			
			//Replace events in the shortened list with wild cards, if the sequence contains wild cards
			else if(sequence.contains("*")){
				events = replaceEventsWithWildCard(events, sequence);
				
				//Last check after we got the new array with wild cards in it
				if(events.equals(sequence))
					return true;
			}
			
		}
		return false;
	}

	
}
