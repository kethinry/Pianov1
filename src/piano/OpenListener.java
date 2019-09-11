package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfugue.pattern.Pattern;
import org.jfugue.realtime.RealtimePlayer;

public class OpenListener implements ActionListener{
	MyPiano myPiano;
	//WuXianPu wuXianPu;
	JFileChooser chooser;
	public OpenListener(MyPiano myPiano){
		this.myPiano=myPiano;
		//this.wuXianPu=wuXianPu;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!myPiano.isPlaying) {
			Music music=new Music(myPiano);
			try {
				RealtimePlayer player = new RealtimePlayer();
				Pattern p=music.getMusicFromFile();//读取出音符组成的pattern，但是先让字幕播放，因为声音播放太快
				music.play();
				myPiano.isPlaying=true;
				player.play(p);//现在播放声音
				
			} catch (MidiUnavailableException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}
		else{
			JOptionPane.showMessageDialog(null, "已有歌曲正在播放！");
		}
	}

}
