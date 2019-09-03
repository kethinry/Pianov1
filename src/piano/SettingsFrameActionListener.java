package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrameActionListener implements ActionListener{
	MyPiano myPiano;
	public SettingsFrameActionListener(MyPiano myPiano){
		this.myPiano=myPiano;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			new SettingsFrame(myPiano);
		
	}
}
