package piano;

import java.awt.Color;

import javax.print.attribute.standard.PrinterMessageFromOperator;
import javax.swing.ImageIcon;

public class PlayModeColorThread extends Thread {
	public PlayMode playMode;
	public MyPiano myPiano;
	public String noteOnlyNum;
	public String duration;
	public String note;
	public long time;
	public boolean done = false;
    int a;
	public PlayModeColorThread(MyPiano myPiano, PlayMode playMode, String noteOnlyNum, String duration, String note,
			long time) {
		this.playMode = playMode;
		this.myPiano = myPiano;
		this.noteOnlyNum = noteOnlyNum;
		this.duration = duration;
		this.note = note;
		this.time = time;

	}

	public void run() {
		while (playMode.on && !done) {
			
			int character = Integer.valueOf(noteOnlyNum);
			KeyProperty key = myPiano.km.findByCharacter(character);
			if(key.getIndex()==-1)
				key=myPiano.km.findByRiseCharacter(character);

			int buttonCode = key.getIndex();
			
			try {
				Thread.sleep(time);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			if (playMode.on) {
				myPiano.player.play(note);
				if(buttonCode!=-1)
					myPiano.btn[buttonCode].setBackground(Color.GREEN);
				//myPiano.btn[myPiano.transformDurationToButtonCode(duration)].setBackground(myPiano.myGay);
				try {
					Thread.sleep(myPiano.trans.transformDurationToTime(duration,80,false));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
				if(buttonCode!=-1)
					myPiano.btn[buttonCode].setBackground(Color.WHITE);
				//myPiano.btn[myPiano.transformDurationToButtonCode(duration)].setBackground(Color.WHITE);
				done = true;
			}
		}
	}
	
}
