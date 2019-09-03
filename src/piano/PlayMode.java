package piano;


import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.MidiUnavailableException;

import org.jfugue.realtime.RealtimePlayer;



public class PlayMode {
	MyPiano myPiano;
	public boolean on = true;
	String[] note;
	long[] time;
	String musicString;
	String timeString;
	PlayModeColorThread[] threads;
	public PlayMode(MyPiano myPiano) throws InterruptedException, MidiUnavailableException {
		this.myPiano=myPiano;

		readString(myPiano.remember);

		note = new String[myPiano.numOfNote];
		time = new long[myPiano.numOfNote];
		String[] noteOnlyNum = new String[myPiano.numOfNote];

		String[] durations = new String[myPiano.numOfNote];
		RealtimePlayer player = new RealtimePlayer();
		note = musicString.split("  ");
		String[] subTimeString = timeString.split(" ");
		threads = new PlayModeColorThread[myPiano.numOfNote];

		for (int i = 0; i < myPiano.numOfNote; i++) {
			noteOnlyNum[i]=note[i].replaceFirst("V"+"\\d+"+" "+"I"+"\\d+"+" ", "");

			durations[i]=noteOnlyNum[i].replaceAll("\\d+", "");
			noteOnlyNum[i]=noteOnlyNum[i].replaceAll("[a-zA-Z]", "");
			time[i] = Long.valueOf(subTimeString[i]);
			threads[i]= new PlayModeColorThread(myPiano,this, noteOnlyNum[i], durations[i],note[i],time[i]);
		}			
		for(int i=0;i<myPiano.numOfNote;i++) {
			threads[i].start();
		}
	}
	public void stopAllThreads() {
		on=false;
		for(int i=0;i<myPiano.numOfNote;i++)
			threads[i].interrupt();
		
	}
	public void readString(String fileString) {
		

		String[] subString = fileString.split("\r\n");

		myPiano.title = subString[1].replaceFirst("#title:", "");
		myPiano.date = subString[2].replaceFirst("#date:", "");
		myPiano.author = subString[3].replaceFirst("#author:", "");
		musicString = subString[4].replaceFirst("#", "");
		timeString = subString[5].replaceFirst("#", "");
		myPiano.numOfNote = Integer.valueOf(subString[6].replaceFirst("#", ""));
		
	}
}
