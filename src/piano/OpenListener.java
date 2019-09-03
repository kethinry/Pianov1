package piano;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

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
			if(music.getMusicFromFile()){
				music.play();
				myPiano.isPlaying=true;
			}
			
		}
		else{
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
