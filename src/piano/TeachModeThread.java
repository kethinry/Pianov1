package piano;

import java.io.IOException;

public class TeachModeThread extends Thread{
	MyPiano myPiano;

	public TeachModeThread(MyPiano myPiano) {
		this.myPiano=myPiano;
	}
	public void run() {
		try {
			new TeachMode(myPiano);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
