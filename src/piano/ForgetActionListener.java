package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgetActionListener implements ActionListener {
	MyPiano myPiano;
	@Override
	
	public void actionPerformed(ActionEvent e) {
		new ForgetFrame(myPiano);

	}

}
