package piano;

import java.awt.Color;

import javax.sound.midi.MidiUnavailableException;

public class PlayModeThread extends Thread{
	Note note;
	MyPiano myPiano;
	public PlayModeThread(MyPiano myPiano,Note note) {
		// TODO Auto-generated constructor stub
		this.myPiano = myPiano;
		this.note=note;
		
	}

	public void run() {
		try {
			note.playNote();
			
			if(!note.isPrint)myPiano.lblWuXianPu.addNote(String.valueOf(note.character)+note.durationString);
			note.isPrint = true;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}

