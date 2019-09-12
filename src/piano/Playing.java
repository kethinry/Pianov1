package piano;

import javax.swing.JOptionPane;

import org.jfugue.*;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xerces.internal.impl.dv.ValidatedInfo;

public class Playing extends Thread {// ��ʼ����ʱ������һ��Playing����
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
		p = music.getMusicFromFile();// ��ȡ��������ɵ�pattern������������Ļ���ţ���Ϊ��������̫��
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
		//JOptionPane.showMessageDialog(null, "�������");
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
		player.play(p);// ���ڲ�������
		stopPlaying();
	}
	
}
