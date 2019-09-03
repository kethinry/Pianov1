package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpFrameActionListener implements ActionListener{
	MyPiano myPiano;
	
	public void actionPerformed(ActionEvent e) {
		new HelpFrame(myPiano);
	}
	public HelpFrameActionListener(MyPiano myPiano) {
		this.myPiano=myPiano;
	}
}
