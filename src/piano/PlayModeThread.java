package piano;

import java.awt.Color;
import java.awt.print.Printable;

import javax.sound.midi.MidiUnavailableException;

public class PlayModeThread extends Thread{
	int index;
	Note[] note;
	MyPiano myPiano;
	Music music;
	public PlayModeThread(MyPiano myPiano,Music music,int index) {
		// TODO Auto-generated constructor stub
		this.myPiano = myPiano;
		this.music = music;
		this.index = index;
		note = new Note[music.maxLength];
		for(int i=0;i<music.maxLength;i++) {
			this.note[i] = music.note[index][i];

		}
	}

	public void run() {
		//music.playing.object.notifyAll();
		try {
			int i=-1;
			while (note[++i]!=null){
				note[i].playNote();
				//System.out.println("note= "+note[i]);
				if(!note[i].isPrint)myPiano.lblWuXianPu.addNote(String.valueOf(note[i] .character)+note[i] .durationString);
				note[i].isPrint = true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}

