package components.concretes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import components.abstracts.FSM;
import components.abstracts.FSMSaver;

//Saves a FSM to an object file
public class FSMObjectSaver implements FSMSaver {

	@Override
	public void saveFSM(FSM machine, OutputStream out) {
		ObjectOutputStream obj = null;
		try {
			obj = new ObjectOutputStream(out);
			obj.writeObject(machine);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		finally{
			try {
				obj.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
