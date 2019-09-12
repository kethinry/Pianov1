package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;


import org.jfugue.*;

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
		if (!myPiano.isPlaying) {
			Playing playing = new Playing(myPiano);
			if(playing.p!=null)playing.start();			
		}else {
			JOptionPane.showMessageDialog(null, "已有歌曲正在播放！");
		}
	}

}
