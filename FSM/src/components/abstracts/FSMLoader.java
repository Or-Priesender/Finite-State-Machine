package components.abstracts;

import java.io.InputStream;

//General FSM loader interface
public interface FSMLoader {
	public FSM loadFSM(InputStream in);
}
