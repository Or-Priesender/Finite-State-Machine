package components.concretes;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import components.abstracts.FSM;
import components.abstracts.FSMLoader;

//Loads a FSM from an object file
public class FSMObjectLoader implements FSMLoader {

	@Override
	public FSM loadFSM(InputStream in) {
		FSM loaded = null;
		try {
			ObjectInputStream obj = new ObjectInputStream(in);
			loaded = (FSM) obj.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return loaded;
	}

}
