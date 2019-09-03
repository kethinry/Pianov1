package piano;

import javax.swing.JOptionPane;

public class EndThread extends Thread{
	Music music;
	public EndThread(Music music) {
		this.music=music;
	}
	public void run(){
		long maxTime=music.note[0][music.noteCount[0]-1].beginTime+music.note[0][music.noteCount[0]-1].durationInt;
		
		for(int i=0;i<music.part;i++){
			if((music.note[i][music.noteCount[i]-1].beginTime
					+music.note[i][music.noteCount[i]-1].durationInt)>maxTime)
				maxTime=music.note[i][music.noteCount[i]-1].beginTime
						+music.note[i][music.noteCount[i]-1].durationInt;
		}
		
		try {
			Thread.sleep(maxTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "≤•∑≈ÕÍ±œ");
		music.stop();
	}
}
