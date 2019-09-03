package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkinActionListener implements ActionListener{

		MyPiano myPiano;
		Samp samp;
		public SkinActionListener(MyPiano myPiano){
			this.myPiano=myPiano;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new SkinFrame(myPiano);
			
		}

}
