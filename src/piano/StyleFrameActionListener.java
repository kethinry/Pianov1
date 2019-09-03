package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StyleFrameActionListener implements ActionListener{
	MyPiano myPiano;
	public StyleFrameActionListener(MyPiano myPiano){
		this.myPiano=myPiano;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			new StyleFrame(myPiano);
		
	}
}
