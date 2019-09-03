package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterActionListener implements ActionListener {
	MyPiano myPiano;
	@Override
	
	public void actionPerformed(ActionEvent e) {
		new RegisterFrame(myPiano);

	}

}
