package components.abstracts;

import java.io.OutputStream;

//General FSM saver interface
public interface FSMSaver {
	public void saveFSM(FSM machine,OutputStream out);
}
