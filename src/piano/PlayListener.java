package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

import org.jfugue.*;

public class PlayListener implements ActionListener {
	MyPiano myPiano;
	JFileChooser chooser;
	public PlayListener (MyPiano myPiano) {
		this.myPiano=myPiano;
	}
	public void actionPerformed(ActionEvent e){
		myPiano.mode=4;
		myPiano.itemFreeplay.setText("自由弹奏");
		myPiano.itemTeachplay.setText("教学模式");
		myPiano.itemRecord.setText("乐谱记录");
		myPiano.itemCreate.setText("自由创作");
		myPiano.itemPlay.setText("播放  √");
		chooser=new JFileChooser(".");
		
		if (!myPiano.isPlaying) {
			Playing playing = new Playing(myPiano);
			playing.start();			
		}else {
			JOptionPane.showMessageDialog(null, "已有歌曲正在播放！");
		}
		/*

		//如果不需要显示过滤的文件格式，下面3行可以不要
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("显示Gif图片文件","gif","GIF"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("显示JPeg图片文件","jpg","jpeg"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("显示doc文件","doc","DOC"));
		int ret=chooser.showOpenDialog(null);
*/
	}

}
