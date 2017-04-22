package components.abstracts;

import java.io.OutputStream;

public interface FSMSaver {
	public void saveFSM(FSM machine,OutputStream out);
}
