package piano;

import javax.swing.JOptionPane;

import org.jfugue.*;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

public class Playing extends Thread {// 开始播放时，创建一个Playing对象
	MyPiano myPiano;
	Player player;
	Pattern p;
	Music music;
	PlayPanel panel;
	Object object;

	public Playing(MyPiano myPiano) {
		this.myPiano = myPiano;
		this.object = new Object();
		music = new Music(myPiano,this);
		p = music.getMusicFromFile();// 读取出音符组成的pattern，但是先让字幕播放，因为声音播放太快
		if(p!=null) {
			panel = new PlayPanel(this);
			myPiano.f.add(panel);
			myPiano.f.validate();
			myPiano.isPlaying = true;
			music.play();
		}
		
	}

	public void stopPlaying() {		
		if(player.isPlaying())player.stop();
		music.stop();
		panel.remove(panel);
		panel.setVisible(false);
		myPiano.isPlaying = false;
		myPiano.refresh();
		myPiano.f.validate();
		//JOptionPane.showMessageDialog(null, "播放完毕");
	}

	public void pause() {
		player.pause();
		music.pause();
	}

	public void wake() {
		player.resume();
		music.wake();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		player = new Player();
		player.play(p);// 现在播放声音
		stopPlaying();
	}
	
}
