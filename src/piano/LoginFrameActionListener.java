package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrameActionListener implements ActionListener{
	MyPiano myPiano;
	public LoginFrameActionListener(MyPiano myPiano){
		this.myPiano=myPiano;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			
		
		new LoginFrame(myPiano);
			
	}
}
